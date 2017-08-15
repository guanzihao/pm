CREATE  PROC proc_wmExccNote
	--(@syndate datetime	)

as	
	

begin
  --����һ���α�

		--���еı���
		declare @ID					int--
		declare @ordernum			varchar(50)--
 		declare @contractnum 		varchar(50)--
		declare @bussinessNo		varchar(50)--
		declare @customcode			varchar(50)--
		declare @flownote			int--
		declare @status				int--
 		declare @remark				varchar(50)--
		declare	@note				varchar(500)--
		declare @contractstartdate	datetime--
		declare @LCstartdate		datetime--
		declare @Divdate			datetime--
		declare @recamount			decimal(18,2)---
 		declare @reccurrency		varchar(50)--
		declare @receivedate			datetime
		declare @waybilldate			decimal(18,2)
		declare @Balancedate		decimal
		declare @Refuntime		datetime
		declare @startdate			datetime
		declare @enddate			datetime
		declare @type				int
		declare @opecomp			varchar(50)
		declare @firstdept			int
		declare @secdept			int
		declare @operator			int
		declare @operatorName		varchar(50)
		
		declare @notdId varchar(36)--�ڵ�Id
		declare @isSendEmail varchar(36)--�Ƿ����ʼ�
		declare @taskId varchar(36)--����Id
		declare @billId varchar(36) --Ʊ��Id
		declare	@orderFromId varchar(50)




	 Declare curKeyforImportCustom cursor for
	 select 	 ID	,ordernum,contractnum,bussinessNo,customcode,flownote,status,remark	,note,contractstartdate,LCstartdate,Divdate,recamount,reccurrency		
		,receivedate,waybilldate,Balancedate,Refuntime,startdate,enddate,type,opecomp,firstdept,secdept,operator,operatorName		
	 from oujian_pmsyn.dbo.Keyfortradeexport where is_syn=0

	 open curKeyforImportCustom

	fetch next from curCustomimport into  @ID,@ordernum,@contractnum,@bussinessNo,@customcode,@flownote,@status,@remark	,@note,@contractstartdate,@LCstartdate,@Divdate,@recamount,@reccurrency,@receivedate,@waybilldate,@Balancedate,@Refuntime,@startdate,@enddate,@type,@opecomp,@firstdept,@secdept,@operator,@operatorName
	while (@@fetch_status=0)
		begin
			select @taskId=task_id,@billId=obj_Id,@orderFromId=order_from_id from oujian_pm.dbo.WM_Flow_EXCC where business_no=@bussinessNo;

			IF @taskId is  null
				begin 
					update oujian_pmsyn.dbo.Keyfortradeexport SET flag=1 where ID=@id;
				end
			else
				begin
				
					--1��ó(��֤����)
					IF @flownote =1
						begin
							set @notdId='05CADA4CB572454CA68A07D7551C1748';
							set @isSendEmail=0;
						end
					--2��ó(����֤��֤)
					else if @flownote =2
						begin
							set @notdId='D3799907448F4883A13918EAF7DB2DD4';
							set @isSendEmail=0;
						end
					--3��ó(�ջ�)
					else if @flownote =3
						begin
							set @notdId='43E2C6CA8D4544AC881CE89B54B68543';
							set @isSendEmail=1;
						end
					
					--4��ó(�������)
					else if @flownote =4
						begin
							set @notdId='FFD3DC40B4CC4D19904772418BB8742F';
							set @isSendEmail=0;
						end
					--5��ó(���㿪Ʊ)
					else if @flownote =5
						begin
							set @notdId='5075AD51F6704CE5B280D1582629351D';
							set @isSendEmail=0;
						end
					--6��ó(��˰����)
					else if @flownote =6
						begin
							set @notdId='10A487E8CE9E4B0E85F05C4027CC1BFE';
							set @isSendEmail=1;
						end
					
				end
					--����ڵ���ϸ
						insert into oujian_pm.dbo.WM_Flow_EXCC_Node
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
																	
																	way_bill_date,
																	balance_date,
																	refund_time,
																	type,
																	ope_comp,
																	first_dept,
																	sec_dept,
																	operator,
																	operator_name

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
																	
																	@waybilldate,
																	@Balancedate,
																	@Refuntime,
																	@type,
																	@opecomp,
																	@firstdept,
																	@secdept,
																	@operator,
																	@operatorName
																	
																)

					--��������Id��Id�޸Ķ��������½ڵ�״̬
					update oujian_pm.dbo.PM_OrderFrom SET node_id=@notdId,node_date=GETDATE(),node_state=@status where task_id=@taskId and tack_type_id=@notdId;
					--��������Id�޸���������½ڵ�״̬
					update oujian_pm.dbo.PM_Task SET node_id=@notdId,node_date=GETDATE(),node_state=@status where obj_Id=@taskId;
					----��������Id�޸ĵ��ݱ����½ڵ�״̬
					update oujian_pm.dbo.WM_Flow_EXCC SET node_id=@notdId,node_date=GETDATE(),node_state=@status  where obj_id=@billId;

					--����д��֮��,�����޸Ĳ���
					update oujian_pmsyn.dbo.KeyforImportCustom SET is_syn=1 where ID=@id;
				
			 Fetch Next From curCustomimport into  @ID,@ordernum,@contractnum,@bussinessNo,@customcode,@flownote,@status,@remark	,@note,@contractstartdate,@LCstartdate,@Divdate,@recamount,@reccurrency,@receivedate,@waybilldate,@Balancedate,@Refuntime,@startdate,@enddate,@type,@opecomp,@firstdept,@secdept,@operator,@operatorName	
		end
	close curCustomimport

	deallocate curCustomimport
end





--exec  proc_bgImccNote;

--drop procedure proc_bgImccNote;


