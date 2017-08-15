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
		declare	@taskId varchar(36)--任务Id
		declare	@orderFromId varchar(36)--订单Id
		declare	@CompanyInfoId varchar(36)--客户Id
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

	
	 --声明一个游标
	 Declare curCustomexport cursor for
	select  id,orderNum,BussinessNo,CustomNo,customcode,Department,cselfNo,items,gross,flight,waybillno,Operatingunit,itemName,lastupdate,flag
	from [oujian_pmsyn].[dbo].customexport where is_syn=0;
	
	--打开一个游标
	open curCustomexport

	fetch next from curCustomexport into  @id,@orderNum,@BussinessNo,@CustomNo,@customcode,@Department,@cselfNo,@items,@gross,@flight,@waybillno,@Operatingunit,@itemName,@lastupdate,@flag
	while (@@fetch_status=0)
		begin 
	
		select @num=MAX(obj_Id) from oujian_pm.dbo.PM_Task where task_id=@orderNum
		

		--判断任务流水号是否为空
		IF @orderNum  is null or @num is null
		   begin 	
			
			--查询接单公司

			select @CompanyInfoName=comName from oujian_pm.dbo.PM_COMPANY_SupCompanyInfo where comCode=@Department

			--根据客户纳税识别码去客户表中查询对应的客户Id
			select @userId=obj_Id from oujian_pm.dbo.PM_COMPANY_CompanyInfo  where tax_num=@customcode;
			

			--根据业务类型,客户Id,当前时间去任务表中查询这个类型的任务是否存在
			select @isObjId=pt.obj_Id,@orderNum=pt.task_id from [oujian_pm].[dbo].[PM_Task] pt where pt.tack_type='AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C' AND pt.issue_date=convert(varchar(10),GETDATE(),121) AND pt.user_id=@userId and task_classify=1;
			
			

			--如果不存在
			IF @isObjId is null
				begin 	
				--生成任务流水号
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
						
						--执行新增任务,并新增订单信息

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
															'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C',
															@newId,
															@objId,
															'1',
															GETDATE()
													
													);
							--插入票据信息
							insert INTO [oujian_pm].dbo.BG_Flow_EMCC(
													obj_Id,
													obj_createDate,
													obj_updateDate,
													bill_name,
													task_id,--任务Id
													order_from_id,--订单Id
													node_id,--最新节点
													noe_state,--节点状态
													bussiness_no,--业务流水号
													custom_no,--报关单号
													custom_code,--客户纳税识别码
													department,--操作公司
													cself_no,--客户自编号
													items,--件数(PKG)
													gross,--毛重(kgs)
													flight,--船名/航次
													waybill_no,--提运单号
													operating_unit,--经营单位
													item_name,--品名
													last_update--最后更新时间
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
							--插入数据完成,更新报关表中的处理标记
							update oujian_pmsyn.dbo.[customexport] SET is_syn='1' where ID=@id


					end
				 ELSE
					begin 	
							print'++++++++++++++++++++++++++++++++++++++++++++++++++++';
							set @newNum=@newDate+'00001';
				
							set @newId= REPLACE(NEWID(),'-','');
				
						--执行新增任务,并新增订单信息
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
															'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C',
															@newId,
															@objId,
															'1',
															GETDATE()
													
													);
							--插入票据信息
							insert INTO [oujian_pm].dbo.BG_Flow_EMCC(
													obj_Id,
													obj_createDate,
													obj_updateDate,
													bill_name,
													task_id,--任务Id
													order_from_id,--订单Id
													node_id,--最新节点
													noe_state,--节点状态
													bussiness_no,--业务流水号
													custom_no,--报关单号
													custom_code,--客户纳税识别码
													department,--操作公司
													cself_no,--客户自编号
													items,--件数(PKG)
													gross,--毛重(kgs)
													flight,--船名/航次
													waybill_no,--提运单号
													operating_unit,--经营单位
													item_name,--品名
													last_update--最后更新时间
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
							--插入数据完成,更新报关表中的处理标记
							update oujian_pmsyn.dbo.[customexport] SET is_syn='1' where ID=@id
					end
				end
			Else 
			begin
				print'==============================++++++++++++++++++++++++++++======================================';
				select  @orderFromId=obj_Id from oujian_pm.dbo.PM_OrderFrom where task_id=@isObjId  and tack_type_id= 'AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C';
				--插入票据信息
					insert INTO [oujian_pm].dbo.BG_Flow_EMCC(
											obj_Id,
											obj_createDate,
											obj_updateDate,
											bill_name,
											task_id,--任务Id
											order_from_id,--订单Id
											node_id,--最新节点
											noe_state,--节点状态
											bussiness_no,--业务流水号
											custom_no,--报关单号
											custom_code,--客户纳税识别码
											department,--操作公司
											cself_no,--客户自编号
											items,--件数(PKG)
											gross,--毛重(kgs)
											flight,--船名/航次
											waybill_no,--提运单号
											operating_unit,--经营单位
											item_name,--品名
											last_update--最后更新时间
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
					--插入数据完成,更新报关表中的处理标记
					update oujian_pmsyn.dbo.[customexport] SET is_syn='1' where ID=@id;
				
				
				end
			end	
			
		ELSE
			begin
				
				--进入Else说明有任务流水号,根据任务流水号查询任务信息,
				select @taskId=obj_Id  from oujian_pm.dbo.PM_Task where task_id=@orderNum;

				--根据任务信息查询订单信息,
				select @orderFromId=obj_Id  from [oujian_pm].dbo.PM_OrderFrom po where task_id=@taskId and tack_type_id='AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C';
		
				insert INTO [oujian_pm].dbo.BG_Flow_EMCC(
											obj_Id,
											obj_createDate,
											obj_updateDate,
											bill_name,
											task_id,--任务Id
											order_from_id,--订单Id
											node_id,--最新节点
											noe_state,--节点状态
											bussiness_no,--业务流水号
											custom_no,--报关单号
											custom_code,--客户纳税识别码
											department,--操作公司
											cself_no,--客户自编号
											items,--件数(PKG)
											gross,--毛重(kgs)
											flight,--船名/航次
											waybill_no,--提运单号
											operating_unit,--经营单位
											item_name,--品名
											last_update--最后更新时间
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
					--插入数据完成,更新报关表中的处理标记
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




