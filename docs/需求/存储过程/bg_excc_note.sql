create  PROC proc_bgEmccNote
	(@syndate datetime	)

as	
	

begin
  --声明一个游标

		declare  @id varchar(50)
		declare		 @bussinessNo varchar(50)
		declare		 @orderNum varchar(50)
		declare		 @CustomNo varchar(50)
		declare		@customcode varchar(50)
		declare	 @flownote varchar(50)
		declare	 @status varchar(50)
		declare		 @remark varchar(50)
		declare		 @note varchar(50)
		declare		 @BussinessDate varchar(50)
		declare @inspectionDate varchar(50)
		declare @releaseDate varchar(50)
		declare @startdate varchar(50)
		declare	@enddate varchar(50)
		declare		@type varchar(50)
		declare		@opecomp varchar(50)
		declare		@firstdept varchar(50)
		declare		@secdept varchar(50)
		declare		@operator varchar(50)
		declare		@operatorName varchar(50)
		declare		@lastupdate varchar(50)
		declare		@flag  varchar(50)
		declare		@taskId  varchar(50)
		declare		@billId varchar(50)
		declare		@notdId varchar(50)
		declare		@isSendEmail int

	 Declare curKeyforImportCustom cursor for
	 select ID, bussinessNo,orderNum,CustomNo,customcode,flownote,status,remark,note,BussinessDate,inspectionDate,
	releaseDate,startdate,enddate,type,opecomp,firstdept,secdept,operator,operatorName,lastupdate,flag
	 from oujian_pmsyn.dbo.KeyforExportCustom where is_syn=0;

	 open curKeyforImportCustom

	fetch next from curKeyforExportCustom into  @id, @bussinessNo,@orderNum,@CustomNo,@customcode,@flownote,@status,@remark,@note,@BussinessDate,@inspectionDate,@releaseDate,@startdate,@enddate,@type,@opecomp,@firstdept,@secdept,@operator,@operatorName,@lastupdate,@flag
	while (@@fetch_status=0)
		begin
			select @taskId=task_id,@billId=obj_Id from oujian_pm.dbo.BG_Flow_EMCC where bussiness_no=@bussinessNo;

			IF @taskId is  null
				begin 
					update oujian_pmsyn.dbo.KeyforExportCustom SET flag=1 where ID=@id;
				end
			else
				
					begin
						--进口报关(接单)
						IF @flownote =1
										begin
							set @notdId='3718CFB999084F648541C8E94EC884F6';
							set @isSendEmail=0;
						end
						--进口报关(查验)
						else if @flownote =2
										begin
							set @notdId='BEB022BCE87D40BB8EF48DDB9FD559C0';
							set @isSendEmail=1;
						end
						--进口报关(放行)
						else 
										begin
							set @notdId='2B25DA1092D5455B8BF99B909E2BDF18';
							set @isSendEmail=1;
						end
					end
				insert INTO oujian_pm.dbo.BG_Flow_EXCC_Node(
														obj_Id,
														obj_createDate,--创建日期
														obj_updateDate,--更新日期
														task_id,--任务ID
														node_id,--节点ID
														bill_id,--单据ID
														start_date,--开始时间
														end_date,--完成时间
														flag,--状态
														remarks,--完成情况说明
														is_send_email,--是否邮件已发送
														bussiness_no,--业务流水号
														custom_no,--报关单号
														custom_code,--客户纳税识别码
														flow_note,--流程节点
														status,--流程状态
														note,--备注
														bussiness_date,--接单时间
														inspection_date,--查验时间
														release_date,--海关放行时间
														type,--供应商类型
														opecomp,--操作公司
														first_dept,--操作一级部门
														sec_dept,--操作二级部门
														operator_name--操作人姓名

													)
													VALUES(
														REPLACE(NEWID(),'-',''),
														GETDATE(),
														GETDATE(),
														@taskId,
														@notdId,
														@billId,
													@startdate,
													@enddate,
													@status,
													@remark,
													@isSendEmail,
													@bussinessNo,
													@CustomNo,
													@customcode,
													@flownote,
													@status,
													@note,
													@BussinessDate,
													@inspectionDate,
													@releaseDate,
													@type,
													@opecomp,
													@firstdept,
													@secdept,
													@operatorName
													);
					--根据任务Id和Id修改订单表最新节点状态
					update oujian_pm.dbo.PM_OrderFrom SET node_id=@notdId,node_date=GETDATE(),node_state=@status where task_id=@taskId and tack_type_id=@notdId;
					--根据任务Id修改任务表最新节点状态
					update oujian_pm.dbo.PM_Task SET node_id=@notdId,node_date=GETDATE(),node_state=@status where obj_Id=@taskId;
					----根据任务Id修改单据表最新节点状态
					update oujian_pm.dbo.BG_Flow_EMCC SET node_id=@notdId,node_date=GETDATE(),node_state=@status  where bill_id=@billId;


					update oujian_pmsyn.dbo.KeyforExportCustom SET is_syn=1 where ID=@id;
				
			 Fetch Next From curKeyforExportCustom into  @id,@bussinessNo,@orderNum,@CustomNo,@customcode,@flownote,@status,@remark,@note,@BussinessDate,@inspectionDate,@releaseDate,@startdate,@enddate,@type,@opecomp,@firstdept,@secdept,@operator,@operatorName,@lastupdate,@flag
		end
	close curKeyforExportCustom

	deallocate curKeyforExportCustom
end





--exec  proc_bgEmccNote;

--drop procedure proc_bgEmccNote;


