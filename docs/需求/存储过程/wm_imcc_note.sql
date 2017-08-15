CREATE  PROC proc_wmImccNote
	--(@syndate datetime	)

as	
	

begin
  --声明一个游标

		--表中的变量
		declare @ID					int
		declare @ordernum			varchar(50)
 		declare @contractnum 		varchar(50)
		declare @bussinessNo		varchar(50)
		declare @customcode			varchar(50)
		declare @flownote			int
		declare @status				int
 		declare @remark				varchar(50)
		declare	@note				varchar(500)
		declare @contractstartdate	datetime
		declare @LCstartdate		datetime
		declare @Divdate			datetime
		declare @recamount			decimal(18,2)
 		declare @reccurrency		varchar(50)
		declare @Paytime			datetime
		declare @Payamount			decimal(18,2)
		declare @Paycurrency		varchar(50)
		declare @Arrivaldate		datetime
		declare @Customcleardate	datetime
 		declare @Balancedate		datetime
		declare @startdate			datetime
		declare @enddate			datetime
		declare @type				int
		declare @opecomp			varchar(50)
 		declare @firstdept			int
		declare @secdept			int
		declare @operator			int
		declare @operatorName		varchar(50)
		
 		
		
		


		declare @notdId varchar(36)--节点Id
		declare @isSendEmail varchar(36)--是否发送邮件
		declare @taskId varchar(36)--任务Id
		declare @billId varchar(36) --票据Id
		declare	@orderFromId varchar(50)




	 Declare curKeyforImportCustom cursor for
	 select ID,ordernum,contractnum ,bussinessNo,customcode,flownote,status	,remark	,note,contractstartdate,LCstartdate		
		,Divdate,recamount,reccurrency,Paytime,Payamount,Paycurrency,Arrivaldate,Customcleardate,Balancedate,startdate,enddate,type	,opecomp,firstdept,secdept,operator,operatorName		
	 from oujian_pmsyn.dbo.Keyfortradeimport where is_syn=0

	 open curKeyforImportCustom

	fetch next from curCustomimport into @id,@ordernum,@contractnum ,@bussinessNo,@customcode,@flownote,@status	,@remark	,@note,@contractstartdate,@LCstartdate	,@Divdate,@recamount,@reccurrency,@Paytime,@Payamount,@Paycurrency,@Arrivaldate,@Customcleardate,@Balancedate,@startdate,@enddate,@type	,@opecomp,@firstdept,@secdept,@operator,@operatorName		
	while (@@fetch_status=0)
		begin
			select @taskId=task_id,@billId=obj_Id,@orderFromId=order_from_id from oujian_pm.dbo.BG_Flow_IMCC where bussiness_no=@bussinessNo;

			IF @taskId is  null
				begin 
					update oujian_pmsyn.dbo.KeyforImportCustom SET flag=1 where ID=@id;
				end
			else
				begin
				
					--外贸(单证制作)
					IF @flownote =1
						begin
							set @notdId='A801E587E5544D13AB9E1586F6C6ED1B';
							set @isSendEmail=0;
						end
					--外贸(信用证开证)
					else if @flownote =2
						begin
							set @notdId='ED8DEDD3CE574C778525BAAFC7CF481F';
							set @isSendEmail=0;
						end
					--外贸(收货款)
					else if @flownote =3
						begin
							set @notdId='D4D4EE5F7E1F40F0A08D47CA1604DE97';
							set @isSendEmail=1;
						end
						--外贸(付货款)
					else if @flownote =4
						begin
							set @notdId='3EEE8970CFE343029FC165F6D0106098';
							set @isSendEmail=0;
						end
					--外贸(进口到货)
					else if @flownote =5
						begin
							set @notdId='1D80EB8CAD48484F86A9260E3EED2A19';
							set @isSendEmail=0;
						end
					--外贸(进口清关)
					else if @flownote =6
						begin
							set @notdId='AC68BD3B48994422A8B86E85BB4BDE7F';
							set @isSendEmail=1;
						end
					--外贸(业务结算)
					else if @flownote =7
						begin
							set @notdId='B8745135DF4A45A994134AE1331C626E';
							set @isSendEmail=0;
						end
					
				end
					--插入节点明细
						insert into oujian_pm.dbo.WM_Flow_IMCC_Node
																(
																	obj_Id,
																	obj_createDate,
																	obj_updateDate,
																	task_id,
																	order_from_id,
																	node_id,
																	bill_id,
																	start_date,
																	end_date,
																	flag,
																	note,
																	is_send_email,
																	order_num,
																	contract_num,
																	business_no,
																	custom_code,
																	flow_node,
																	state,
																	remarks,
																	contract_start_date,
																	lc_start_date,
																	div_date,
																	rec_amount,
																	rec_currency,
																	arrival_date,
																	custom_clear_date,
																	balance_date,
																	type,
																	operator_comp,
																	first_dept,
																	sec_dept,
																	operator,
																	operator_name,
																	last_update
																	
																)
																VALUES
																(
																	REPLACE(NEWID(),'-',''),
																	GETDATE(),
																	GETDATE(),
																	@taskId,
																	@orderFromId,
																	@notdId,
																	@billId,
																	@startdate,
																	@enddate,
																	'0',
																	@note,
																	@isSendEmail,
																	@ordernum,
																	@contractnum,
																	
																	@bussinessNo,
																	@customcode,
																	@flownote,
																	@status,
																	@remark,
																	@contractstartdate,
																	@LCstartdate,
																	@Divdate,
																	@recamount,
																	@reccurrency,
																	@Arrivaldate,
																	@Customcleardate,
																	@Balancedate,
																	@type,
																	@opecomp,
																	@firstdept,
																	@secdept,
																	@operator,
																	@operatorName,
																	GETDATE()
																)

					--根据任务Id和Id修改订单表最新节点状态
					update oujian_pm.dbo.PM_OrderFrom SET node_id=@notdId,node_date=GETDATE(),node_state=@status where task_id=@taskId and tack_type_id=@notdId;
					--根据任务Id修改任务表最新节点状态
					update oujian_pm.dbo.PM_Task SET node_id=@notdId,node_date=GETDATE(),node_state=@status where obj_Id=@taskId;
					----根据任务Id修改单据表最新节点状态
					update oujian_pm.dbo.BG_Flow_IMCC SET node_id=@notdId,node_date=GETDATE(),node_state=@status  where bill_id=@billId;

					--数据写完之后,进行修改操作
					update oujian_pmsyn.dbo.KeyforImportCustom SET is_syn=1 where ID=@id;
				
			 Fetch Next From curCustomimport into   @id,@ordernum,@contractnum ,@bussinessNo,@customcode,@flownote,@status	,@remark	,@note,@contractstartdate,@LCstartdate	,@Divdate,@recamount,@reccurrency,@Paytime,@Payamount,@Paycurrency,@Arrivaldate,@Customcleardate,@Balancedate,@startdate,@enddate,@type	,@opecomp,@firstdept,@secdept,@operator,@operatorName		
		end
	close curCustomimport

	deallocate curCustomimport
end





--exec  proc_bgImccNote;

--drop procedure proc_bgImccNote;


