package com.sup.order.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * @Description: 空海运运输委托书
 * @author Chu Zhaocheng
 * @date 2017年6月25日 下午6:45:03
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SHIPPING_Trop_COMMISSION")
public class TropShippingExCommission extends MetaObject {

    /**
     * 客户ID
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 任务ID
     */
    @Column(name = "task_id")
	private String taskId;
	/**
	 * 订单ID
	 */
    @Column(name = "order_from_id")
	private String orderFromId;
    
    /**
     * 委托方
     */
    @Column(name = "consignor")
    private String consignor;
    
	/**
	 * 托运人
	 */
    @Column(name = "shipper")
	private String shipper;
	/**
	 * 托运人地址
	 */
    @Column(name = "s_address")
	private String sAddress;
	/**
	 * 起运港
	 */
    @Column(name = "departure_port")
	private String departurePort;
	/**
	 * 卸货港
	 */
    @Column(name = "discharge_port")
	private String dischargePort;
	/**
	 * 最终目的地
	 */
    @Column(name = "destination_port")
	private String destinationPort;
	/**
	 * 编号
	 */
    @Column(name = "xhs_no")
	private String xhsNo;
	/**
	 * 收货人
	 */
    @Column(name = "consignee")
	private String consignee;
	/**
	 * 收货人地址
	 */
    @Column(name = "c_address")
	private String cAddress;
	/**
	 * 货到时间
	 */
    @Column(name = "arrival_date")
	private Date arrivalDate;
	/**
	 * 支付方式
	 */
    @Column(name = "pay_way")
	private String payWay;
	/**
	 * 箱型/箱量
	 */
    @Column(name = "container")
	private String container;
    /**
     * 提单类型
     */
    @Column(name = "blt")
    private String blt;
	/**
	 * 通知人
	 */
    @Column(name = "notifier")
	private String notifier;
	/**
	 * 件数
	 */
    @Column(name = "item")
	private Integer item;
	/**
	 * 毛重
	 */
    @Column(name = "weight")
	private String weight;
	/**
	 * 体积
	 */
    @Column(name = "volume")
	private Integer volume;
	/**
	 * 唛头
	 */
    @Column(name = "marks")
	private String marks;
	/**
	 * 品名
	 */
    @Column(name = "goods_name")
	private String goodsName;
	/**
	 * 运费确认
	 */
    @Column(name = "trans_expense_charge")
	private String transExpenseCharge;
	/**
	 * 运费条款
	 */
    @Column(name = "trans_clause")
	private String transClause;
	/**
	 * 托运联系人
	 */
    @Column(name = "contact_person")
	private String contactPerson;
	/**
	 * 电话
	 */
    @Column(name = "cp_phone")
	private String cpPhone;
	/**
	 * 传真
	 */
    @Column(name = "cp_fax")
	private String cpFax;
	/**
	 * 邮箱
	 */
    @Column(name = "cp_mail")
	private String cpMail;
	/**
	 * 预配航班
	 */
    @Column(name = "pre_flight")
	private String preFlight;
	/**
	 * 自拉自报
	 */
    @Column(name = "self_full")
	private String selfFull;
	/**
	 * 特殊要求
	 */
    @Column(name = "special_notes")
	private String specialNotes;
	/**
	 * 签名盖章
	 */
    @Column(name = "signature")
	private String signature;
	
    /**
     * 是否全权委托 0:自助委托,1:全权委托
     */
    @Column(name = "is_delegation")
    private Integer isDelegation;
    
    /**
     * 开始时间
     */
    @Column(name = "start_date")
    private Date startDate;
    
    /**
     * 开始时间
     */
    @Column(name = "end_date")
    private Date endDate;
    
    /**
     * 备注说明
     */
    @Column(name = "explain")
    private String explain;
    
	public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getExplain() {
        return explain;
    }
    public void setExplain(String explain) {
        this.explain = explain;
    }
    public Integer getIsDelegation() {
        return isDelegation;
    }
    public void setIsDelegation(Integer isDelegation) {
        this.isDelegation = isDelegation;
    }
    public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getOrderFromId() {
		return orderFromId;
	}
	public void setOrderFromId(String orderFromId) {
		this.orderFromId = orderFromId;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	public String getDeparturePort() {
		return departurePort;
	}
	public void setDeparturePort(String departurePort) {
		this.departurePort = departurePort;
	}
	public String getDischargePort() {
		return dischargePort;
	}
	public void setDischargePort(String dischargePort) {
		this.dischargePort = dischargePort;
	}
	public String getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}
	public String getXhsNo() {
		return xhsNo;
	}
	public void setXhsNo(String xhsNo) {
		this.xhsNo = xhsNo;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getcAddress() {
		return cAddress;
	}
	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public String getPayWay() {
        return payWay;
    }
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
    public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
	
	public String getBlt() {
        return blt;
    }
    public void setBlt(String blt) {
        this.blt = blt;
    }
    public String getNotifier() {
		return notifier;
	}
	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getTransExpenseCharge() {
		return transExpenseCharge;
	}
	public void setTransExpenseCharge(String transExpenseCharge) {
		this.transExpenseCharge = transExpenseCharge;
	}
	public String getTransClause() {
		return transClause;
	}
	public void setTransClause(String transClause) {
		this.transClause = transClause;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getCpPhone() {
		return cpPhone;
	}
	public void setCpPhone(String cpPhone) {
		this.cpPhone = cpPhone;
	}
	public String getCpFax() {
		return cpFax;
	}
	public void setCpFax(String cpFax) {
		this.cpFax = cpFax;
	}
	public String getCpMail() {
		return cpMail;
	}
	public void setCpMail(String cpMail) {
		this.cpMail = cpMail;
	}
	public String getPreFlight() {
		return preFlight;
	}
	public void setPreFlight(String preFlight) {
		this.preFlight = preFlight;
	}
	public String getSelfFull() {
		return selfFull;
	}
	public void setSelfFull(String selfFull) {
		this.selfFull = selfFull;
	}
	public String getSpecialNotes() {
		return specialNotes;
	}
	public void setSpecialNotes(String specialNotes) {
		this.specialNotes = specialNotes;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getConsignor() {
        return consignor;
    }
    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }
}
