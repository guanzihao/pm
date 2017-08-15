CREATE  proc  proc_bgEmcc
	--(@syndate datetime	)
AS
	
begin
	 
		declare	@id varchar(50)
		declare	@orderNum varchar(50)
		declare	@BussinessNo varchar(50)
		declare	@CustomNo varchar(50)
		declare	@customcode varchar(50)
		declare	@Department varchar(50)
		declare	@cselfNo varchar(50)
		declare	@items int
		declare	@gross decimal(18, 4)
		declare	@flight varchar(50)
		declare	@waybillno varchar(50)
		declare	@Operatingunit varchar(50)
		declare	@itemName varchar(50)
		declare	@lastupdate datetime
		declare	@flag int
		declare	@taskId varchar(36)--����Id
		declare	@orderFromId varchar(36)--����Id
		declare	@CompanyInfoId varchar(36)--�ͻ�Id
		declare	@oldDate varchar(36)
		declare	@newDate varchar(36)
		declare	@oldNum varchar(36)
		declare	@newNum varchar(36)
		declare	@newId varchar(36)
		declare	@newOrderFromId varchar(36)
		declare	@objId varchar(36)
		declare @num int
		declare @isObjId varchar(36)
		declare @userId varchar(36)
		declare @CompanyInfoName varchar(50)

	
	 --����һ���α�
	 Declare curCustomexport cursor for
	select  id,orderNum,BussinessNo,CustomNo,customcode,Department,cselfNo,items,gross,flight,waybillno,Operatingunit,itemName,lastupdate,flag
	from [oujian_pmsyn].[dbo].customexport where is_syn=0;
	
	--��һ���α�
	open curCustomexport

	fetch next from curCustomexport into  @id,@orderNum,@BussinessNo,@CustomNo,@customcode,@Department,@cselfNo,@items,@gross,@flight,@waybillno,@Operatingunit,@itemName,@lastupdate,@flag
	while (@@fetch_status=0)
		begin 
	
		select @num=MAX(obj_Id) from oujian_pm.dbo.PM_Task where task_id=@orderNum
		

		--�ж�������ˮ���Ƿ�Ϊ��
		IF @orderNum  is null or @num is null
		   begin 	
			
			--��ѯ�ӵ���˾

			select @CompanyInfoName=comName from oujian_pm.dbo.PM_COMPANY_SupCompanyInfo where comCode=@Department

			--���ݿͻ���˰ʶ����ȥ�ͻ����в�ѯ��Ӧ�Ŀͻ�Id
			select @userId=obj_Id from oujian_pm.dbo.PM_COMPANY_CompanyInfo  where tax_num=@customcode;
			

			--����ҵ������,�ͻ�Id,��ǰʱ��ȥ������в�ѯ������͵������Ƿ����
			select @isObjId=pt.obj_Id,@orderNum=pt.task_id from [oujian_pm].[dbo].[PM_Task] pt where pt.tack_type='AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C' AND pt.issue_date=convert(varchar(10),GETDATE(),121) AND pt.user_id=@userId and task_classify=1;
			
			

			--���������
			IF @isObjId is null
				begin 	
				--����������ˮ��
				print'@@id  is null';
				select  @orderNum=MAX(task_id) from oujian_pm.dbo.PM_Task;

				set @oldDate =SUBSTRING(@orderNum,1,8);
				set @oldNum=SUBSTRING(@orderNum,9,12);
				set @newDate=REPLACE(CONVERT(varchar(100), GETDATE(), 23),'-',''); 
				
				 IF @oldDate=@newDate
				
					begin 	
						 print'@oldDate=@newDate';
						--2017062200001
						set @newNum=cast(@oldDate as varchar)+ RIGHT('0000' + CAST(CAST(@oldNum AS int)+1 AS VARCHAR),5);
						
						--ִ����������,������������Ϣ

						set @newId= REPLACE(NEWID(),'-','');
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
												'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C',
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
															'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C',
															@newId,
															@objId,
															'1',
															GETDATE()
													
													);
							--����Ʊ����Ϣ
							insert INTO [oujian_pm].dbo.BG_Flow_EMCC(
													obj_Id,
													obj_createDate,
													obj_updateDate,
													bill_name,
													task_id,--����Id
													order_from_id,--����Id
													node_id,--���½ڵ�
													noe_state,--�ڵ�״̬
													bussiness_no,--ҵ����ˮ��
													custom_no,--���ص���
													custom_code,--�ͻ���˰ʶ����
													department,--������˾
													cself_no,--�ͻ��Ա��
													items,--����(PKG)
													gross,--ë��(kgs)
													flight,--����/����
													waybill_no,--���˵���
													operating_unit,--��Ӫ��λ
													item_name,--Ʒ��
													last_update--������ʱ��
													)
													VALUES(
															REPLACE(NEWID(),'-',''),
															GETDATE(),
															GETDATE(),
															@BussinessNo,
															@newId,
															@newOrderFromId,
															NULL,
															NULL,
															@BussinessNo,
															@CustomNo,
															@customcode,
															@Department,
															@cselfNo,
															@items,
															@gross,
															@flight,
															@waybillno,
															@Operatingunit,
															@itemName,
															@lastupdate

													);
							--�����������,���±��ر��еĴ�����
							update oujian_pmsyn.dbo.[customexport] SET is_syn='1' where ID=@id


					end
				 ELSE
					begin 	
							print'++++++++++++++++++++++++++++++++++++++++++++++++++++';
							set @newNum=@newDate+'00001';
				
							set @newId= REPLACE(NEWID(),'-','');
				
						--ִ����������,������������Ϣ
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
												'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C',
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
															'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C',
															@newId,
															@objId,
															'1',
															GETDATE()
													
													);
							--����Ʊ����Ϣ
							insert INTO [oujian_pm].dbo.BG_Flow_EMCC(
													obj_Id,
													obj_createDate,
													obj_updateDate,
													bill_name,
													task_id,--����Id
													order_from_id,--����Id
													node_id,--���½ڵ�
													noe_state,--�ڵ�״̬
													bussiness_no,--ҵ����ˮ��
													custom_no,--���ص���
													custom_code,--�ͻ���˰ʶ����
													department,--������˾
													cself_no,--�ͻ��Ա��
													items,--����(PKG)
													gross,--ë��(kgs)
													flight,--����/����
													waybill_no,--���˵���
													operating_unit,--��Ӫ��λ
													item_name,--Ʒ��
													last_update--������ʱ��
													)
													VALUES(
															REPLACE(NEWID(),'-',''),
															GETDATE(),
															GETDATE(),
															@BussinessNo,
															@newId,
															@newOrderFromId,
															NULL,
															NULL,
															@BussinessNo,
															@CustomNo,
															@customcode,
															@Department,
															@cselfNo,
															@items,
															@gross,
															@flight,
															@waybillno,
															@Operatingunit,
															@itemName,
															@lastupdate

													);
							--�����������,���±��ر��еĴ�����
							update oujian_pmsyn.dbo.[customexport] SET is_syn='1' where ID=@id
					end
				end
			Else 
			begin
				print'==============================++++++++++++++++++++++++++++======================================';
				select  @orderFromId=obj_Id from oujian_pm.dbo.PM_OrderFrom where task_id=@isObjId  and tack_type_id= 'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C';
				--����Ʊ����Ϣ
					insert INTO [oujian_pm].dbo.BG_Flow_EMCC(
											obj_Id,
											obj_createDate,
											obj_updateDate,
											bill_name,
											task_id,--����Id
											order_from_id,--����Id
											node_id,--���½ڵ�
											noe_state,--�ڵ�״̬
											bussiness_no,--ҵ����ˮ��
											custom_no,--���ص���
											custom_code,--�ͻ���˰ʶ����
											department,--������˾
											cself_no,--�ͻ��Ա��
											items,--����(PKG)
											gross,--ë��(kgs)
											flight,--����/����
											waybill_no,--���˵���
											operating_unit,--��Ӫ��λ
											item_name,--Ʒ��
											last_update--������ʱ��
											)
											VALUES(
												 REPLACE(NEWID(),'-',''),
												 GETDATE(),
												 GETDATE(),
												 @BussinessNo,
												 @isObjId,
												 @orderFromId,
												 NULL,
												 NULL,
												 @BussinessNo,
												 @CustomNo,
												 @customcode,
												 @Department,
												 @cselfNo,
												 @items,
												 @gross,
												 @flight,
												 @waybillno,
												 @Operatingunit,
												 @itemName,
												 @lastupdate

											);
					--�����������,���±��ر��еĴ�����
					update oujian_pmsyn.dbo.[customexport] SET is_syn='1' where ID=@id;
				
				
				end
			end	
			
		ELSE
			begin
				
				--����Else˵����������ˮ��,����������ˮ�Ų�ѯ������Ϣ,
				select @taskId=obj_Id  from oujian_pm.dbo.PM_Task where task_id=@orderNum;

				--����������Ϣ��ѯ������Ϣ,
				select @orderFromId=obj_Id  from [oujian_pm].dbo.PM_OrderFrom po where task_id=@taskId and tack_type_id='AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C';
		
				insert INTO [oujian_pm].dbo.BG_Flow_EMCC(
											obj_Id,
											obj_createDate,
											obj_updateDate,
											bill_name,
											task_id,--����Id
											order_from_id,--����Id
											node_id,--���½ڵ�
											noe_state,--�ڵ�״̬
											bussiness_no,--ҵ����ˮ��
											custom_no,--���ص���
											custom_code,--�ͻ���˰ʶ����
											department,--������˾
											cself_no,--�ͻ��Ա��
											items,--����(PKG)
											gross,--ë��(kgs)
											flight,--����/����
											waybill_no,--���˵���
											operating_unit,--��Ӫ��λ
											item_name,--Ʒ��
											last_update--������ʱ��
											)
											VALUES(
												 REPLACE(NEWID(),'-',''),
												 GETDATE(),
												 GETDATE(),
												 @BussinessNo,
												 @taskId,
												 @orderFromId,
												 NULL,
												 NULL,
												 @BussinessNo,
												 @CustomNo,
												 @customcode,
												 @Department,
												 @cselfNo,
												 @items,
												 @gross,
												 @flight,
												 @waybillno,
												 @Operatingunit,
												 @itemName,
												 @lastupdate

											);
					--�����������,���±��ر��еĴ�����
					update oujian_pmsyn.dbo.[customexport] SET is_syn='1' where ID=@id;


		end
		 Fetch Next From curCustomexport into  @id,@orderNum,@BussinessNo,@CustomNo,@customcode,@Department,@cselfNo,@items,@gross,@flight,@waybillno,@Operatingunit,@itemName,@lastupdate,@flag
		
		end
	close curCustomexport

	deallocate curCustomexport
										 
end 

--exec proc_bgEmcc

--drop procedure proc_bgEmcc;

--select * from [oujian_pmsyn].[dbo].[customexport]




