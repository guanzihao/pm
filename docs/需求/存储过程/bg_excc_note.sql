create  PROC proc_bgEmccNote
	(@syndate datetime	)

as	
	

begin
  --����һ���α�

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
						--���ڱ���(�ӵ�)
						IF @flownote =1
										begin
							set @notdId='3718CFB999084F648541C8E94EC884F6';
							set @isSendEmail=0;
						end
						--���ڱ���(����)
						else if @flownote =2
										begin
							set @notdId='BEB022BCE87D40BB8EF48DDB9FD559C0';
							set @isSendEmail=1;
						end
						--���ڱ���(����)
						else 
										begin
							set @notdId='2B25DA1092D5455B8BF99B909E2BDF18';
							set @isSendEmail=1;
						end
					end
				insert INTO oujian_pm.dbo.BG_Flow_EXCC_Node(
														obj_Id,
														obj_createDate,--��������
														obj_updateDate,--��������
														task_id,--����ID
														node_id,--�ڵ�ID
														bill_id,--����ID
														start_date,--��ʼʱ��
														end_date,--���ʱ��
														flag,--״̬
														remarks,--������˵��
														is_send_email,--�Ƿ��ʼ��ѷ���
														bussiness_no,--ҵ����ˮ��
														custom_no,--���ص���
														custom_code,--�ͻ���˰ʶ����
														flow_note,--���̽ڵ�
														status,--����״̬
														note,--��ע
														bussiness_date,--�ӵ�ʱ��
														inspection_date,--����ʱ��
														release_date,--���ط���ʱ��
														type,--��Ӧ������
														opecomp,--������˾
														first_dept,--����һ������
														sec_dept,--������������
														operator_name--����������

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
					--��������Id��Id�޸Ķ��������½ڵ�״̬
					update oujian_pm.dbo.PM_OrderFrom SET node_id=@notdId,node_date=GETDATE(),node_state=@status where task_id=@taskId and tack_type_id=@notdId;
					--��������Id�޸���������½ڵ�״̬
					update oujian_pm.dbo.PM_Task SET node_id=@notdId,node_date=GETDATE(),node_state=@status where obj_Id=@taskId;
					----��������Id�޸ĵ��ݱ����½ڵ�״̬
					update oujian_pm.dbo.BG_Flow_EMCC SET node_id=@notdId,node_date=GETDATE(),node_state=@status  where bill_id=@billId;


					update oujian_pmsyn.dbo.KeyforExportCustom SET is_syn=1 where ID=@id;
				
			 Fetch Next From curKeyforExportCustom into  @id,@bussinessNo,@orderNum,@CustomNo,@customcode,@flownote,@status,@remark,@note,@BussinessDate,@inspectionDate,@releaseDate,@startdate,@enddate,@type,@opecomp,@firstdept,@secdept,@operator,@operatorName,@lastupdate,@flag
		end
	close curKeyforExportCustom

	deallocate curKeyforExportCustom
end





--exec  proc_bgEmccNote;

--drop procedure proc_bgEmccNote;


