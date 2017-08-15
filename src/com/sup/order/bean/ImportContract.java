package com.sup.order.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * @Description: 进口合同(外贸进口)
 * @author Chu Zhaocheng
 * @date 2017年6月25日 下午6:37:59
 */
@SuppressWarnings("serial")
@Entity
@Table(name="PM_IMPORT_CONTRACT")
public class ImportContract extends MetaObject {

    /**
     * 客户ID
     */
    @Column(name = "user_id")
    private String userId;
    
    /**
     * 附件1
     */
    @Column(name="one_accessory")
    private String oneAccessory;
    /**
     * 任务ID
     */
    @Column(name = "task_id")
	private String taskId;
    
    /**
     * 委托方
     */
    @Column(name = "consignor")
    private String consignor;
    
	/**
	 * 订单ID
	 */
    @Column(name = "order_from_id")
	private String orderFromId;
	/**
	 * 合同日期
	 */
    @Column(name = "contract_date")
	private Date contractDate;
	/**
	 * 合同号
	 */
    @Column(name = "contract_no")
	private String contractNo;
	/**
	 * 卖方
	 */
    @Column(name = "sellers")
	private String sellers;
	/**
	 * 卖方地址
	 */
    @Column(name = "s_address")
	private String sAddress;
	/**
	 * 卖方联系电话
	 */
    @Column(name = "s_telphone")
	private String sTelphone;
	/**
	 * 卖方传真
	 */
    @Column(name = "s_fax")
	private String sFax;
	/**
	 * 买方
	 */
    @Column(name = "buyers")
	private String buyers;
	/**
	 * 买方地址
	 */
    @Column(name = "b_address")
	private String bAddress;
	/**
	 * 买方联系电话
	 */
    @Column(name = "b_telphone")
	private String bTelphone;
	/**
	 * 买方传真
	 */
    @Column(name = "b_fax")
	private String bFax;
    
    /**
     * 币种
     */
    @Column(name = "currency")
    private String currency;
    
    /**
     * 成交方式
     */
    @Column(name = "pay_way")
    private String payWay;
    
	/**
	 * 装运时间
	 */
    @Column(name = "shipment_time")
	private Date shipmentTime;
	/**
	 * 交货时间
	 */
    @Column(name = "delivery_time")
	private Date deliveryTime;
	/**
	 * 装运口岸
	 */
    @Column(name = "port_shipment")
	private String portShipment;
	/**
	 * 目的口岸
	 */
    @Column(name = "port_destination")
	private String portDestination;
	/**
	 * 包装
	 */
    @Column(name = "packing")
	private String packing;
	/**
	 * 保险
	 */
    @Column(name = "insurance")
	private String insurance;
	/**
	 * 制造商
	 */
    @Column(name = "manufactory")
	private String manufactory;
	/**
	 * 付款条件
	 */
    @Column(name = "term_payment")
	private String termPayment;    
	/**
	 * 检查条款
	 */
    @Column(name = "inspection")
	private String inspection;
	/**
	 * 仲裁条款
	 */
    @Column(name = "arbitration")
	private String arbitration;    
	/**
	 * 其他
	 */
    @Column(name = "others")
	private String others;
	
    /**
     * 附件1
     */
    @Column(name = "accessory")
    private String accessory;
	
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
    public String getAccessory() {
        return accessory;
    }
    public void setAccessory(String accessory) {
        this.accessory = accessory;
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
	public String getOneAccessory() {
        return oneAccessory;
    }
    public void setOneAccessory(String oneAccessory) {
        this.oneAccessory = oneAccessory;
    }
    public Date getContractDate() {
		return contractDate;
	}
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getSellers() {
		return sellers;
	}
	public void setSellers(String sellers) {
		this.sellers = sellers;
	}
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	public String getsFax() {
		return sFax;
	}
	public void setsFax(String sFax) {
		this.sFax = sFax;
	}
	public String getBuyers() {
		return buyers;
	}
	public void setBuyers(String buyers) {
		this.buyers = buyers;
	}
	public String getbAddress() {
		return bAddress;
	}
	public void setbAddress(String bAddress) {
		this.bAddress = bAddress;
	}
	public String getbTelphone() {
		return bTelphone;
	}
	public void setbTelphone(String bTelphone) {
		this.bTelphone = bTelphone;
	}
	public String getbFax() {
		return bFax;
	}
	public void setbFax(String bFax) {
		this.bFax = bFax;
	}
	public Date getShipmentTime() {
		return shipmentTime;
	}
	public void setShipmentTime(Date shipmentTime) {
		this.shipmentTime = shipmentTime;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getPortShipment() {
		return portShipment;
	}
	public void setPortShipment(String portShipment) {
		this.portShipment = portShipment;
	}
	public String getPortDestination() {
		return portDestination;
	}
	public void setPortDestination(String portDestination) {
		this.portDestination = portDestination;
	}
	public String getPacking() {
		return packing;
	}
	public void setPacking(String packing) {
		this.packing = packing;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public String getManufactory() {
		return manufactory;
	}
	public void setManufactory(String manufactory) {
		this.manufactory = manufactory;
	}
	public String getTermPayment() {
		return termPayment;
	}
	public void setTermPayment(String termPayment) {
		this.termPayment = termPayment;
	}
	public String getInspection() {
		return inspection;
	}
	public void setInspection(String inspection) {
		this.inspection = inspection;
	}
	public String getArbitration() {
		return arbitration;
	}
	public void setArbitration(String arbitration) {
		this.arbitration = arbitration;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getsTelphone() {
        return sTelphone;
    }
    public void setsTelphone(String sTelphone) {
        this.sTelphone = sTelphone;
    }
    public String getConsignor() {
        return consignor;
    }
    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getPayWay() {
        return payWay;
    }
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
    
}
