CREATE  PROC proc_bgImccNote
	(@syndate datetime	)

as	
	

begin
  --声明一个游标

		--表中的变量

		declare @notdId varchar(36)--节点Id
		declare @isSendEmail varchar(36)--是否发送邮件
		declare @taskId varchar(36)--任务Id
		declare @billId varchar(36) --票据Id




	 Declare curKeyforImportCustom cursor for
	 select 
	 from oujian_pmsyn.dbo.KeyforImportCustom where is_syn=0

	 open curKeyforImportCustom

	fetch next from curCustomimport into 
	while (@@fetch_status=0)
		begin
			select @taskId=task_id,@billId=obj_Id from oujian_pm.dbo.BG_Flow_IMCC where bussiness_no=@bussinessNo;

			IF @taskId is  null
				begin 
					update oujian_pmsyn.dbo.KeyforImportCustom SET flag=1 where ID=@id;
				end
			else
				begin
					--进口报关(接单)
					IF @flownote =1
						begin
							set @notdId='2C0422A4E717442098B88ECA3ACE7077';
							set @isSendEmail=0;
						end
					--进口报关(查验)
					else if @flownote =2
						begin
							set @notdId='FF81ADE9E1B74FCEB81F11DE88BED991';
							set @isSendEmail=1;
						end
					--进口报关(放行)
					else 
						begin
							set @notdId='F152A84287EC49E385BB763346021EC4';
							set @isSendEmail=1;
						end
				end
					--插入节点明细


					--根据任务Id和Id修改订单表最新节点状态
					update oujian_pm.dbo.PM_OrderFrom SET node_id=@notdId,node_date=GETDATE(),node_state=@status where task_id=@taskId and tack_type_id=@notdId;
					--根据任务Id修改任务表最新节点状态
					update oujian_pm.dbo.PM_Task SET node_id=@notdId,node_date=GETDATE(),node_state=@status where obj_Id=@taskId;
					----根据任务Id修改单据表最新节点状态
					update oujian_pm.dbo.BG_Flow_IMCC SET node_id=@notdId,node_date=GETDATE(),node_state=@status  where bill_id=@billId;


					update oujian_pmsyn.dbo.KeyforImportCustom SET is_syn=1 where ID=@id;
				
			 Fetch Next From curCustomimport into  @id,@bussinessNo,@orderNum,@CustomNo,@customcode,@flownote,@status,@remark,@note,@BussinessDate,@inspectionDate,@releaseDate,@startdate,@enddate,@type,@opecomp,@firstdept,@secdept,@operator,@operatorName,@lastupdate,@flag
		end
	close curCustomimport

	deallocate curCustomimport
end





--exec  proc_bgImccNote;

--drop procedure proc_bgImccNote;


