ALTER  proc  proc_wmImcc
	--(@syndate datetime	)
AS
	
begin
		
		--�м��ı���
		declare	@id varchar(50)
		declare	@orderNum varchar(50)
		declare	@itemName varchar(50)
		declare	@contractnum varchar(50)
		declare	@bussinessNo varchar(50)
		declare	@customcode varchar(50)
		declare	@Department varchar(50)
		declare	@Contractamount decimal(18,2)
		declare	@Contractcurrency varchar(50)
		

		--�ر�����
		declare @num varchar(36)
		declare @CompanyInfoName varchar(36)
		declare @userId varchar(36)--�û�Id
		declare @isObjId varchar(36)
		declare @taskTypeId varchar(36)--��������Id
		declare @taskSerialNumber varchar(36)--��������������ˮ��
		declare @oldDate varchar(36) --���ݿ������
		declare @oldNum varchar(36) --��ˮ��
		declare @newDate varchar(36) --��ǰ����
		declare	@newNum varchar(36) --�µ���ˮ��
		declare @newId varchar(36) --�µ�����Id
		declare @orderFromId varchar(36)--����Id
		declare @taskId varchar(36) --����Id
		declare @objId varchar(36) --
		declare @newOrderFromId varchar(36)

		 
		 set @taskTypeId='';--ҵ������Id
	
	 --����һ���α�
	 Declare curCustomimport cursor for
	select  id,ordernum,itemName,contractnum,bussinessNo,customcode,Department,Contractamount,Contractcurrency
	from [oujian_pmsyn].[dbo].tradeexport where is_syn=0;
	
	--��һ���α�
	open curCustomimport

	fetch next from curCustomimport into  @id,@ordernum,@itemName,@contractnum,@bussinessNo,@customcode,@Department,@Contractamount,@Contractcurrency
	while (@@fetch_status=0)
		begin 
			
			--����������ˮ�Ų�һ����������Ƿ���Ч
			select @num=MAX(obj_Id) from oujian_pm.dbo.PM_Task where task_id=@orderNum

		--1-1�ж�������ˮ���Ƿ�Ϊ��,��������ˮ����ҵ��ϵͳ���Ƿ����
			IF @orderNum  is null or @num is null
			   begin 	
					
					--1-1-A��ѯ�ӵ���˾
					select @CompanyInfoName=comName from oujian_pm.dbo.PM_COMPANY_SupCompanyInfo where comCode=@Department

					--1-1-B���ݿͻ���˰ʶ����ȥ�ͻ����в�ѯ��Ӧ�Ŀͻ�Id
					select @userId=obj_Id from oujian_pm.dbo.PM_COMPANY_CompanyInfo  where tax_num=@customcode;
			

					--1-1-C����ҵ������,�ͻ�Id,��ǰʱ��ȥ������в�ѯ������͵������Ƿ����
					select @isObjId=pt.obj_Id,@orderNum=pt.task_id from [oujian_pm].[dbo].[PM_Task] pt where pt.tack_type=@taskTypeId AND pt.issue_date=convert(varchar(10),GETDATE(),121) AND pt.user_id=@userId and task_classify=1;
			


					--2-1��һ������ͻ�����һ���Ƿ��Ѿ�����ͬ���͵�����,���û�д���,�ʹ���
					IF @isObjId is null
						begin 	

								--ȡ��������ˮ�ŵ����ֵ
								select  @taskSerialNumber=MAX(task_id) from oujian_pm.dbo.PM_Task;
								
								set @oldDate =SUBSTRING(@taskSerialNumber,1,8);
								set @oldNum=SUBSTRING(@taskSerialNumber,9,12);
								set @newDate=REPLACE(CONVERT(varchar(100), GETDATE(), 23),'-',''); 

							
								--3-1�ж������Ƿ����,������,������ˮ�ż�1
								IF @oldDate=@newDate
									begin 	
										--������ˮ��
										set @newNum=cast(@oldDate as varchar)+ RIGHT('0000' + CAST(CAST(@oldNum AS int)+1 AS VARCHAR),5);
										--����id,���ںͶ���,���ݹ���
										set @newId= REPLACE(NEWID(),'-','');
										--��������
												insert INTO oujian_pm.dbo.PM_Task(
																			obj_Id,
																			obj_createDate,
																			obj_updateDate,
																			task_id,
																			task_name,
																			start_date,
																			is_issue,
																			issue_date,
																			user_id,
																			task_classify,
																			tack_type,
																			allocation_date,
																			orderReceiving_company
																		)
																		VALUES(
																				@newId,
																				GETDATE(),
																				GETDATE(),
																				@newNum,
																				@newDate+@CompanyInfoName,
																				GETDATE(),
																				'3',
																				convert(varchar(10),GETDATE(),121),
																				@userId,
																				'1',
																				@taskTypeId,
																				GETDATE(),
																				@CompanyInfoName

																		);

										--���붩��			
															select  @objId=obj_Id from oujian_pm.dbo.PM_COMPANY_SupCompanyInfo where comCode=@Department
															set @newOrderFromId= REPLACE(NEWID(),'-','');
																insert INTO oujian_pm.dbo.PM_OrderFrom(
																							obj_Id,
																							obj_createDate,
																							obj_updateDate,
																							tack_type_id,
																							task_id,
																							supplier,
																							order_check,
																							order_check_date	
																						)
																						VALUES
																						(
																							@newOrderFromId,
																								GETDATE(),
																								GETDATE(),
																								@taskTypeId,
																								@newId,
																								@objId,
																								'1',
																								GETDATE()
													
																						);
										--����Ʊ��
																	insert into oujian_pm.dbo.WM_Flow_EXCC
																								(
																									obj_Id,
																									obj_createDate,
																									obj_updateDate,
																									bill_name,
																									task_id,
																									order_from_id,
																									item_name,
																									contract_num,--
																									business_no,
																									custom_code,
																									department,
																									contract_amount,
																									contract_currency,
																									last_update,
																									flag
																								)
																								VALUES
																								(
																									REPLACE(NEWID(),'-',''),
																									GETDATE(),
																									GETDATE(),
																									@contractnum,
																									@newId,
																									@newOrderFromId,
																									@itemName,
																									@contractnum,
																									@bussinessNo,
																									@customcode,
																									@Department,
																									@Contractamount,
																									@Contractcurrency,
																									GETDATE(),
																									'0'

																								);	

										--ִ�гɹ�֮���޸����ݿ�״̬
										update [oujian_pmsyn].[dbo].tradeexport SET is_syn=1 where ID=@id;
										end
								
								ELSE
									--3-2��������ȡ��ǰ����+00001Ϊ������ˮ��
									begin 	
										--������ˮ��
										set @newNum=@newDate+'00001';
										--����id,���ںͶ���,���ݹ���
										set @newId= REPLACE(NEWID(),'-','');
										--��������
												insert INTO oujian_pm.dbo.PM_Task(
																			obj_Id,
																			obj_createDate,
																			obj_updateDate,
																			task_id,
																			task_name,
																			start_date,
																			is_issue,
																			issue_date,
																			user_id,
																			task_classify,
																			tack_type,
																			allocation_date,
																			orderReceiving_company
																		)
																		VALUES(
																				@newId,
																				GETDATE(),
																				GETDATE(),
																				@newNum,
																				@newDate+@CompanyInfoName,
																				GETDATE(),
																				'3',
																				convert(varchar(10),GETDATE(),121),
																				@userId,
																				'1',
																				@taskTypeId,
																				GETDATE(),
																				@CompanyInfoName

																		);

										--���붩��			
															select  @objId=obj_Id from oujian_pm.dbo.PM_COMPANY_SupCompanyInfo where comCode=@Department
															set @newOrderFromId= REPLACE(NEWID(),'-','');
																insert INTO oujian_pm.dbo.PM_OrderFrom(
																							obj_Id,
																							obj_createDate,
																							obj_updateDate,
																							tack_type_id,
																							task_id,
																							supplier,
																							order_check,
																							order_check_date	
																						)
																						VALUES
																						(
																							@newOrderFromId,
																								GETDATE(),
																								GETDATE(),
																								@taskTypeId,
																								@newId,
																								@objId,
																								'1',
																								GETDATE()
													
																						);

										--����Ʊ��
															insert into oujian_pm.dbo.WM_Flow_IMCC
																								(
																									obj_Id,
																									obj_createDate,
																									obj_updateDate,
																									bill_name,
																									task_id,
																									order_from_id,
																									item_name,
																									contract_num,
																									business_no,
																									custom_code,
																									department,
																									contract_amount,
																									contract_currency,
																									last_update,
																									flag
																								)
																								VALUES
																								(
																									REPLACE(NEWID(),'-',''),
																									GETDATE(),
																									GETDATE(),
																									@contractnum,
																									@newId,
																									@newOrderFromId,
																									@itemName,
																									@contractnum,
																									@bussinessNo,
																									@customcode,
																									@Department,
																									@Contractamount,
																									@Contractcurrency,
																									GETDATE(),
																									'0'

																								);	
										--ִ�гɹ�֮���޸����ݿ�״̬
										update [oujian_pmsyn].[dbo].tradeexport SET is_syn=1 where ID=@id;

									end
						end

					--2-2�Ѿ������˾�ֱ�Ӻ�����������а�
					Else 
						begin
							--��������Id����������Id,�õ�һ��������Ϣ
							select  @orderFromId=obj_Id from oujian_pm.dbo.PM_OrderFrom where task_id=@isObjId  and tack_type_id=@taskTypeId;
							--Ʊ�ݺͶ������й���
								insert into oujian_pm.dbo.WM_Flow_IMCC
												(
													obj_Id,
													obj_createDate,
													obj_updateDate,
													bill_name,
													task_id,
													order_from_id,
													item_name,
													contract_num,
													business_no,
													custom_code,
													department,
													contract_amount,
													contract_currency,
													last_update,
													flag
												)
												VALUES
												(
													REPLACE(NEWID(),'-',''),
													GETDATE(),
													GETDATE(),
													@contractnum,
													@isObjId,
													@orderFromId,
													@itemName,
													@contractnum,
													@bussinessNo,
													@customcode,
													@Department,
													@Contractamount,
													@Contractcurrency,
													GETDATE(),
													'0'

												);
							--ִ�гɹ�֮���޸����ݿ�״̬
							update [oujian_pmsyn].[dbo].tradeexport SET is_syn=1 where ID=@id;
						end
				End	
			
		ELSE
			
			begin
				--1-2����Else˵����������ˮ��,����������ˮ�Ų�ѯ������Ϣ,ֱ�ӹ�������
				select @taskId=obj_Id  from oujian_pm.dbo.PM_Task where task_id=@orderNum;

				--����������Ϣ��ѯ������Ϣ,
				select @orderFromId=obj_Id  from [oujian_pm].dbo.PM_OrderFrom po where task_id=@taskId and tack_type_id=@taskTypeId;
				
				--��Ʊ����Ϣ
				insert into oujian_pm.dbo.WM_Flow_EXCC
												(
													obj_Id,
													obj_createDate,
													obj_updateDate,
													bill_name,
													task_id,
													order_from_id,
													item_name,
													contract_num,
													business_no,
													custom_code,
													department,
													contract_amount,
													contract_currency,
													last_update,
													flag
												)
												VALUES
												(
													REPLACE(NEWID(),'-',''),
													GETDATE(),
													GETDATE(),
													@contractnum,
													@taskId,
													@orderFromId,
													@itemName,
													@contractnum,
													@bussinessNo,
													@customcode,
													@Department,
													@Contractamount,
													@Contractcurrency,
													GETDATE(),
													'0'

												);
					--ִ�гɹ�֮���޸����ݿ�״̬
					update [oujian_pmsyn].[dbo].tradeexport SET is_syn=1 where ID=@id;

			end
		 Fetch Next From curCustomimport into   @id,@ordernum,@itemName,@contractnum,@bussinessNo,@customcode,@Department,@Contractamount,@Contractcurrency
		
		end
	close curCustomimport

	deallocate curCustomimport
										 
end 

--exec proc_wmImcc

--drop procedure proc_wmImcc;

--select * from [oujian_pmsyn].[dbo].[customexport]




