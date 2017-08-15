

ALTER  proc  [dbo].[proc_wlImcc]
	--(@syndate datetime	)
AS
	
begin
		
		--中间库的变量
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



		--必备变量
		declare @num varchar(36)
		declare @CompanyInfoName varchar(36)
		declare @userId varchar(36)--用户Id
		declare @isObjId varchar(36)
		declare @taskTypeId varchar(36)--任务类型Id
		declare @taskSerialNumber varchar(36)--保存最大的任务流水号
		declare @oldDate varchar(36) --数据库的日期
		declare @oldNum varchar(36) --流水号
		declare @newDate varchar(36) --当前日期
		declare	@newNum varchar(36) --新的流水号
		declare @newId varchar(36) --新的任务Id
		declare @orderFromId varchar(36)--订单Id
		declare @taskId varchar(36) --任务Id
		declare @objId varchar(36) --
		declare @newOrderFromId varchar(36)

		 
		 set @taskTypeId='B04FA3B7-E0AE-4559-92C3-F6A65EC40BC7';--业务类型Id
	
	 --声明一个游标
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
	--打开一个游标
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
			
			--根据任务流水号查一下这个任务是否有效
			select @num=MAX(obj_Id) from oujian_pm.dbo.PM_Task where task_id=@orderNum

		--1-1判断任务流水号是否为空,此任务流水号在业务系统中是否存在
			IF @orderNum  is null or @num is null
			   begin 	
					
					--1-1-A查询接单公司
					select @CompanyInfoName=comName from oujian_pm.dbo.PM_COMPANY_SupCompanyInfo where comCode=@Department
					
					--1-1-B根据客户纳税识别码去客户表中查询对应的客户Id
					select @userId=obj_Id from oujian_pm.dbo.PM_COMPANY_CompanyInfo  where tax_num=@customcode;
			

					--1-1-C根据业务类型,客户Id,当前时间去任务表中查询这个类型的任务是否存在
					select @isObjId=pt.obj_Id,@orderNum=pt.task_id from [oujian_pm].[dbo].[PM_Task] pt where pt.tack_type=@taskTypeId AND pt.issue_date=convert(varchar(10),GETDATE(),121) AND pt.user_id=@userId and task_classify=1;
			


					--2-1看一下这个客户在这一天是否已经创建同类型的任务,如果没有创建,就创建
					IF @isObjId is null
						begin 	

								--取出任务流水号的最大值
								select  @taskSerialNumber=MAX(task_id) from oujian_pm.dbo.PM_Task;
								
								set @oldDate =SUBSTRING(@taskSerialNumber,1,8);
								set @oldNum=SUBSTRING(@taskSerialNumber,9,12);
								set @newDate=REPLACE(CONVERT(varchar(100), GETDATE(), 23),'-',''); 

							
								--3-1判断日期是否相等,如果相等,任务流水号加1
								IF @oldDate=@newDate
									begin 	
										--任务流水号
										set @newNum=cast(@oldDate as varchar)+ RIGHT('0000' + CAST(CAST(@oldNum AS int)+1 AS VARCHAR),5);
										--任务id,用于和订单,单据关联
										set @newId= REPLACE(NEWID(),'-','');
										--插入任务
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

										--插入订单			
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
										--插入票据
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
																							REPLACE(NEWID(),'-','')，
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
	

										--执行成功之后修改数据库状态
										update [oujian_pmsyn].[dbo].freightimport SET is_syn=1 where ID=@id;
										end
								
								ELSE
									--3-2如果不相等取当前日期+00001为任务流水号
									begin 	
										--任务流水号
										set @newNum=@newDate+'00001';
										--任务id,用于和订单,单据关联
										set @newId= REPLACE(NEWID(),'-','');
										--插入任务
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

										--插入订单			
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

										--插入票据
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
																							REPLACE(NEWID(),'-','')，
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
	
										--执行成功之后修改数据库状态
										update [oujian_pmsyn].[dbo].freightimport SET is_syn=1 where ID=@id;

									end
						end

					--2-2已经创建了就直接和这个订单进行绑定
					Else 
						begin
							--根据任务Id和任务类型Id,得到一条订单信息
							select  @orderFromId=obj_Id from oujian_pm.dbo.PM_OrderFrom where task_id=@isObjId  and tack_type_id=@taskTypeId;
							--票据和订单进行关联
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
													REPLACE(NEWID(),'-','')，
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
							--执行成功之后修改数据库状态
							update [oujian_pmsyn].[dbo].freightimport SET is_syn=1 where ID=@id;
						end
				End	
			
		ELSE
			
			begin
				--1-2进入Else说明有任务流水号,根据任务流水号查询任务信息,直接关联订单
				select @taskId=obj_Id  from oujian_pm.dbo.PM_Task where task_id=@orderNum;

				--根据任务信息查询订单信息,
				select @orderFromId=obj_Id  from [oujian_pm].dbo.PM_OrderFrom po where task_id=@taskId and tack_type_id=@taskTypeId;
				
				--绑定票据信息
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
										REPLACE(NEWID(),'-','')，
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
	
					--执行成功之后修改数据库状态
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






