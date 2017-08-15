

ALTER  proc  [dbo].[proc_wlImcc]
	--(@syndate datetime	)
AS
	
begin
		
		--�м��ı���
		declare	@id varchar(50)
		declare	@orderNum varchar(50)
		declare	@norderno varchar(50)
		declare	@customcode varchar(50)
		declare	@Department varchar(50)
		declare	@cselfNo varchar(50)
		declare	@itemName varchar(50)
		declare	@items int
		declare	@gross decimal(18,4)
		declare	@volume decimal(18,4)
		declare	@Deliveryaddr varchar(100)
		declare	@lastupdate datetime
		declare	@counts int
		declare	@shipper varchar(50)
		declare	@s_address,departure_port varchar(50)
		declare	@discharge_port varchar(50)
		declare	@discharge_port varchar(50)
		declare	@destination_port varchar(50)
		declare	@xhs_no varchar(50)
		declare	@consignee varchar(50)
		declare	@c_address varchar(50)
		declare	@arrival_date varchar(50)
		declare	@pay_way varchar(50)
		declare	@container varchar(50)
		declare	@blt varchar(50)
		declare	@notifier varchar(50)
		declare	@marks varchar(200)
		declare	@trans_expense_charge varchar(200)
		declare	@trans_clause varchar(200)
		declare	@cp_phone varchar(200)
		declare	@cp_fax varchar(200)
		declare	@pre_flight varchar(50)
		declare	@self_full varchar(50)
		declare	@special_notes varchar(200)
		declare	@cp_phone varchar(20)
		declare	@cp_fax varchar(20)
		declare	@pre_flight varchar(50)
		declare	@c_address varchar(50)



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

		 
		 set @taskTypeId='B04FA3B7-E0AE-4559-92C3-F6A65EC40BC7';--ҵ������Id
	
	 --����һ���α�
	 Declare wlImcc cursor for
	select  	id 
			,orderNum 
			,norderno 
			,customcode 
			,Department 
			,cselfNo 
			,itemName
			,items 
			,gross 
			,volume 
			,Deliveryaddr
			,lastupdate 
			,shipper,
			s_address,departure_port,
			discharge_port,
			discharge_port,
			destination_port,
			xhs_no,
			consignee,
			c_address,
			arrival_date,
			pay_way,
			container,
			blt,
			notifier,
			marks,
			trans_expense_charge,
			trans_clause,
			cp_phone,
			cp_fax,
			pre_flight,
			self_full,
			special_notes,
			cp_phone,
			cp_fax,
			pre_flight,
			c_address
	from [oujian_pmsyn].[dbo].freightimport where is_syn=0;
	--��һ���α�
	open wlImcc

	fetch next from wlImcc into  @id 
			,@orderNum 
			,@norderno 
			,@customcode 
			,@Department 
			,@cselfNo 
			,@itemName
			,@items 
			,@gross 
			,@volume 
			,@Deliveryaddr 
			,@lastupdate ,@shipper,@s_address,@departure_port,@discharge_port,@discharge_port,@destination_port,@xhs_no,@consignee,@c_address,@arrival_date,@pay_way,@container,@blt,@notifier,@marks,@trans_expense_charge,@trans_clause,@cp_phone,@cp_fax,@pre_flight,@self_full,@special_notes,@cp_phone,@cp_fax,pre_flight,c_address

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
																	insert into oujian_pm.dbo.WL_Flow_IMCC
																								(
																									obj_Id,
																									obj_createDate,
																									obj_updateDate,
																									task_id,
																									order_from_id,
																									--node_id,
																									--node_state,
																									order_num,
																									norder_no,
																									custom_code,
																									department,
																									cself_no,
																									item_name,
																									items,
																									gross,
																									volume,
																									delivery_addr,
																									last_update,
																									flag,
																									bill_name
																								)
																								VALUES
																								(
																									REPLACE(NEWID(),'-',''),
																									GETDATE(),
																									GETDATE(),
																									@taskid,
																									@orderfromid,
																									--@nodeid,
																									--@nodestate,
																									@ordernum,
																									@norderno,
																									@customcode,
																									@Department,
																									@cselfno,
																									@itemName,
																									@items,
																									@gross,
																									@volume,
																									@Deliveryaddr,
																									@lastupdate,
																									'0',
																									@norderno

																								);
																	select  @counts=count(obj_id) from SHIPPING_Trop_COMMISSION where order_from_id=@orderfromid;
																	IF @counts>0
																		BEGIN
																			insert into SHIPPING_Trop_COMMISSION(
																							obj_id,
																							obj_update,
																							obj_create,
																							task_id,
																							order_from_id,
																							shipper,
																							s_address,departure_port,
																							discharge_port,
																							discharge_port,
																							destination_port,
																							xhs_no,
																							consignee,
																							c_address,
																							arrival_date,
																							pay_way,
																							container,
																							blt,
																							notifier,
																							marks,
																							trans_expense_charge,
																							trans_clause,
																							cp_phone,
																							cp_fax,
																							pre_flight,
																							self_full,
																							special_notes,
																							cp_phone,
																							cp_fax,
																							pre_flight,
																							c_address


																							)VALUES(
																							REPLACE(NEWID(),'-','')��
																							GETDATE(),
																							GETDATE(),
																							@taskid	,
																							@orderfromid,

																							@shipper,
																							@s_address,departure_port,
																							@discharge_port,
																							@discharge_port,
																							@destination_port,
																							@xhs_no,
																							@consignee,
																							@c_address,
																							@arrival_date,
																							@pay_way,
																							@container,
																							@blt,
																							@notifier,
																							@marks,
																							@trans_expense_charge,
																							@trans_clause,
																							@cp_phone,
																							@cp_fax,
																							@pre_flight,
																							@self_full,
																							@special_notes,
																							@cp_phone,
																							@cp_fax,
																							@pre_flight,
																							@c_address
																							);
																		END	
	

										--ִ�гɹ�֮���޸����ݿ�״̬
										update [oujian_pmsyn].[dbo].freightimport SET is_syn=1 where ID=@id;
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
																				insert into oujian_pm.dbo.WL_Flow_IMCC
																								(
																									obj_Id,
																									obj_createDate,
																									obj_updateDate,
																									task_id,
																									order_from_id,
																									--node_id,
																									--node_state,
																									order_num,
																									norder_no,
																									custom_code,
																									department,
																									cself_no,
																									item_name,
																									items,
																									gross,
																									volume,
																									delivery_addr,
																									last_update,
																									flag,
																									bill_name
																								)
																								VALUES
																								(
																									REPLACE(NEWID(),'-',''),
																									GETDATE(),
																									GETDATE(),
																									@taskid,
																									@orderfromid,
																									--@nodeid,
																									--@nodestate,
																									@ordernum,
																									@norderno,
																									@customcode,
																									@Department,
																									@cselfno,
																									@itemName,
																									@items,
																									@gross,
																									@volume,
																									@Deliveryaddr,
																									@lastupdate,
																									'0',
																									@norderno

																								);
																	);
																	select  @counts=count(obj_id) from SHIPPING_Trop_COMMISSION where order_from_id=@orderfromid;
																	IF @counts>0
																		BEGIN
																			insert into SHIPPING_Trop_COMMISSION(
																							obj_id,
																							obj_update,
																							obj_create,
																							task_id,
																							order_from_id,
																							shipper,
																							s_address,departure_port,
																							discharge_port,
																							discharge_port,
																							destination_port,
																							xhs_no,
																							consignee,
																							c_address,
																							arrival_date,
																							pay_way,
																							container,
																							blt,
																							notifier,
																							marks,
																							trans_expense_charge,
																							trans_clause,
																							cp_phone,
																							cp_fax,
																							pre_flight,
																							self_full,
																							special_notes,
																							cp_phone,
																							cp_fax,
																							pre_flight,
																							c_address


																							)VALUES(
																							REPLACE(NEWID(),'-','')��
																							GETDATE(),
																							GETDATE(),
																							@taskid	,
																							@orderfromid,

																							@shipper,
																							@s_address,departure_port,
																							@discharge_port,
																							@discharge_port,
																							@destination_port,
																							@xhs_no,
																							@consignee,
																							@c_address,
																							@arrival_date,
																							@pay_way,
																							@container,
																							@blt,
																							@notifier,
																							@marks,
																							@trans_expense_charge,
																							@trans_clause,
																							@cp_phone,
																							@cp_fax,
																							@pre_flight,
																							@self_full,
																							@special_notes,
																							@cp_phone,
																							@cp_fax,
																							@pre_flight,
																							@c_address
																							);
																		END	
	
										--ִ�гɹ�֮���޸����ݿ�״̬
										update [oujian_pmsyn].[dbo].freightimport SET is_syn=1 where ID=@id;

									end
						end

					--2-2�Ѿ������˾�ֱ�Ӻ�����������а�
					Else 
						begin
							--��������Id����������Id,�õ�һ��������Ϣ
							select  @orderFromId=obj_Id from oujian_pm.dbo.PM_OrderFrom where task_id=@isObjId  and tack_type_id=@taskTypeId;
							--Ʊ�ݺͶ������й���
								insert into oujian_pm.dbo.WL_Flow_IMCC
														(
															obj_Id,
															obj_createDate,
															obj_updateDate,
															task_id,
															order_from_id,
															--node_id,
															--node_state,
															order_num,
															norder_no,
															custom_code,
															department,
															cself_no,
															item_name,
															items,
															gross,
															volume,
															delivery_addr,
															last_update,
															flag,
															bill_name
														)
														VALUES
														(
															REPLACE(NEWID(),'-',''),
															GETDATE(),
															GETDATE(),
															@taskid,
															@orderfromid,
															--@nodeid,
															--@nodestate,
															@ordernum,
															@norderno,
															@customcode,
															@Department,
															@cselfno,
															@itemName,
															@items,
															@gross,
															@volume,
															@Deliveryaddr,
															@lastupdate,
															'0',
															@norderno

																	);
							select  @counts=count(obj_id) from SHIPPING_Trop_COMMISSION where order_from_id=@orderfromid;
							IF @counts>0
								BEGIN
									insert into SHIPPING_Trop_COMMISSION(
													obj_id,
													obj_update,
													obj_create,
													task_id,
													order_from_id,
													shipper,
													s_address,departure_port,
													discharge_port,
													discharge_port,
													destination_port,
													xhs_no,
													consignee,
													c_address,
													arrival_date,
													pay_way,
													container,
													blt,
													notifier,
													marks,
													trans_expense_charge,
													trans_clause,
													cp_phone,
													cp_fax,
													pre_flight,
													self_full,
													special_notes,
													cp_phone,
													cp_fax,
													pre_flight,
													c_address


													)VALUES(
													REPLACE(NEWID(),'-','')��
													GETDATE(),
													GETDATE(),
													@taskid	,
													@orderFromid,

													@shipper,
													@s_address,departure_port,
													@discharge_port,
													@discharge_port,
													@destination_port,
													@xhs_no,
													@consignee,
													@c_address,
													@arrival_date,
													@pay_way,
													@container,
													@blt,
													@notifier,
													@marks,
													@trans_expense_charge,
													@trans_clause,
													@cp_phone,
													@cp_fax,
													@pre_flight,
													@self_full,
													@special_notes,
													@cp_phone,
													@cp_fax,
													@pre_flight,
													@c_address
													);
								END	
							--ִ�гɹ�֮���޸����ݿ�״̬
							update [oujian_pmsyn].[dbo].freightimport SET is_syn=1 where ID=@id;
						end
				End	
			
		ELSE
			
			begin
				--1-2����Else˵����������ˮ��,����������ˮ�Ų�ѯ������Ϣ,ֱ�ӹ�������
				select @taskId=obj_Id  from oujian_pm.dbo.PM_Task where task_id=@orderNum;

				--����������Ϣ��ѯ������Ϣ,
				select @orderFromId=obj_Id  from [oujian_pm].dbo.PM_OrderFrom po where task_id=@taskId and tack_type_id=@taskTypeId;
				
				--��Ʊ����Ϣ
				insert into oujian_pm.dbo.WL_Flow_IMCC
						(
							obj_Id,
							obj_createDate,
							obj_updateDate,
							task_id,
							order_from_id,
							--node_id,
							--node_state,
							order_num,
							norder_no,
							custom_code,
							department,
							cself_no,
							item_name,
							items,
							gross,
							volume,
							delivery_addr,
							last_update,
							flag,
							bill_name
						)
						VALUES
						(
							REPLACE(NEWID(),'-',''),
							GETDATE(),
							GETDATE(),
							@taskid,
							@orderfromid,
							--@nodeid,
							--@nodestate,
							@ordernum,
							@norderno,
							@customcode,
							@Department,
							@cselfno,
							@itemName,
							@items,
							@gross,
							@volume,
							@Deliveryaddr,
							@lastupdate,
							'0',
							@norderno

						);
				select  @counts=count(obj_id) from SHIPPING_Trop_COMMISSION where order_from_id=@orderfromid;
				IF @counts>0
					BEGIN
						insert into SHIPPING_Trop_COMMISSION(
										obj_id,
										obj_update,
										obj_create,
										task_id,
										order_from_id,
										shipper,
										s_address,departure_port,
										discharge_port,
										discharge_port,
										destination_port,
										xhs_no,
										consignee,
										c_address,
										arrival_date,
										pay_way,
										container,
										blt,
										notifier,
										marks,
										trans_expense_charge,
										trans_clause,
										cp_phone,
										cp_fax,
										pre_flight,
										self_full,
										special_notes,
										cp_phone,
										cp_fax,
										pre_flight,
										c_address


										)VALUES(
										REPLACE(NEWID(),'-','')��
										GETDATE(),
										GETDATE(),
										@taskid	,
										@orderfromid,

										@shipper,
										@s_address,departure_port,
										@discharge_port,
										@discharge_port,
										@destination_port,
										@xhs_no,
										@consignee,
										@c_address,
										@arrival_date,
										@pay_way,
										@container,
										@blt,
										@notifier,
										@marks,
										@trans_expense_charge,
										@trans_clause,
										@cp_phone,
										@cp_fax,
										@pre_flight,
										@self_full,
										@special_notes,
										@cp_phone,
										@cp_fax,
										@pre_flight,
										@c_address
										);
					END
	
					--ִ�гɹ�֮���޸����ݿ�״̬
					update [oujian_pmsyn].[dbo].freightimport SET is_syn=1 where ID=@id;

			end
		 Fetch Next From wlImcc into    @id 
			,@orderNum 
			,@norderno 
			,@customcode 
			,@Department 
			,@cselfNo 
			,@itemName
			,@items 
			,@gross 
			,@volume 
			,@Deliveryaddr
			,@lastupdate,@shipper,@s_address,@departure_port,@discharge_port,@discharge_port,@destination_port,@xhs_no,@consignee,@c_address,@arrival_date,@pay_way,@container,@blt,@notifier,@marks,@trans_expense_charge,@trans_clause,@cp_phone,@cp_fax,@pre_flight,@self_full,@special_notes,@cp_phone,@cp_fax,pre_flight,c_address

		
		end
	close wlImcc

	deallocate wlImcc
										 
end 

--exec [proc_wlImcc]

--drop procedure proc_wmImcc;

--select * from [oujian_pmsyn].[dbo].[customexport]






