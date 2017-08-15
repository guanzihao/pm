package com.sup.order.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * @Description: 采购进口订单
 * @author Chu Zhaocheng
 * @date 2017年6月25日 下午7:11:26
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PURCHASE_ORDER_PRODUCTION")
public class PurchaseOrderProduction extends MetaObject {
    
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
	 * 采购单编号
	 */
    @Column(name = "pur_order_no")
	private String purOrderNo;
	/**
	 * 买方
	 */
    @Column(name = "buyer")
	private String buyer;
	/**
	 * 买方地址
	 */
    @Column(name = "total_amount")
	private String bAddress;
	/**
	 * 发布日期
	 */
    @Column(name = "issue_date")
	private Date issueDate;
    
    /**
     * 委托方
     */
    @Column(name = "consignor")
    private String consignor;
    
	/**
	 * 项目
	 */
    @Column(name = "project")
	private String project;
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
	 * 供应商
	 */
    @Column(name = "supplier")
	private String supplier;
	/**
	 * 供应商编号
	 */
    @Column(name = "supplier_no")
	private String supplierNo;
	/**
	 * 联系人
	 */
    @Column(name = "contact_person")
	private String contactPerson;
	/**
	 * 联系人地址
	 */
    @Column(name = "cp_address")
	private String cpAddress;
	/**
	 * 联系人电话
	 */
    @Column(name = "cp_telephone")
	private String cpTelephone;
	/**
	 * 联系人传真
	 */
    @Column(name = "cp_fax")
	private String cpFax;
	/**
	 * 合同期限
	 */
    @Column(name = "contract_term")
	private String contractTerm;
	/**
	 * 交付日期
	 */
    @Column(name = "delivery_date")
	private Date deliveryDate;
	/**
	 * 交付说明
	 */
    @Column(name = "instr_destination")
	private String instrDestination;
	/**
	 * 交付条款
	 */
    @Column(name = "payment_term")
	private String paymentTerm;
	/**
	 * 国际贸易条件
	 */
    @Column(name = "trade_condition")
	private String tradeCondition;
	/**
	 * 相关文件
	 */
    @Column(name = "ref_ocuments")
	private String refDocuments;
	/**
	 * 项目产能要求
	 */
    @Column(name = "pcl")
	private String pcl;
	
	/**
	 * 币种
	 */
    @Column(name = "currency")
	private String currency;
	/**
	 * 工装费用
	 */
    @Column(name = "tooling_cost")
	private String toolingCost;
	/**
	 * 支付方式
	 */
    @Column(name = "pay_way")
	private String payWay;
	/**
	 * 工装寿命
	 */
    @Column(name = "tooling_life")
	private Integer toolingLife;
	/**
	 * 分摊量
	 */
    @Column(name = "share_amount")
	private Integer shareAmount;
	/**
	 * 分摊单价
	 */
    @Column(name = "share_per_price")
	private Double sharePerPrice;
	/**
	 * 说明
	 */
    @Column(name = "comments")
	private String comments;
    
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
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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
    public String getPurOrderNo() {
        return purOrderNo;
    }
    public void setPurOrderNo(String purOrderNo) {
        this.purOrderNo = purOrderNo;
    }
    public String getBuyer() {
        return buyer;
    }
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
    public String getbAddress() {
        return bAddress;
    }
    public void setbAddress(String bAddress) {
        this.bAddress = bAddress;
    }
    public Date getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    public String getProject() {
        return project;
    }
    public void setProject(String project) {
        this.project = project;
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
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public String getSupplierNo() {
        return supplierNo;
    }
    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }
    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public String getCpAddress() {
        return cpAddress;
    }
    public void setCpAddress(String cpAddress) {
        this.cpAddress = cpAddress;
    }
    public String getCpTelephone() {
        return cpTelephone;
    }
    public void setCpTelephone(String cpTelephone) {
        this.cpTelephone = cpTelephone;
    }
    public String getCpFax() {
        return cpFax;
    }
    public void setCpFax(String cpFax) {
        this.cpFax = cpFax;
    }
    public String getContractTerm() {
        return contractTerm;
    }
    public void setContractTerm(String contractTerm) {
        this.contractTerm = contractTerm;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getInstrDestination() {
        return instrDestination;
    }
    public void setInstrDestination(String instrDestination) {
        this.instrDestination = instrDestination;
    }
    public String getPaymentTerm() {
        return paymentTerm;
    }
    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }
    public String getTradeCondition() {
        return tradeCondition;
    }
    public void setTradeCondition(String tradeCondition) {
        this.tradeCondition = tradeCondition;
    }
    public String getRefDocuments() {
        return refDocuments;
    }
    public void setRefDocuments(String refDocuments) {
        this.refDocuments = refDocuments;
    }
    public String getPcl() {
        return pcl;
    }
    public void setPcl(String pcl) {
        this.pcl = pcl;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getToolingCost() {
        return toolingCost;
    }
    public void setToolingCost(String toolingCost) {
        this.toolingCost = toolingCost;
    }
    public String getPayWay() {
        return payWay;
    }
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
    public Integer getToolingLife() {
        return toolingLife;
    }
    public void setToolingLife(Integer toolingLife) {
        this.toolingLife = toolingLife;
    }
    public Integer getShareAmount() {
        return shareAmount;
    }
    public void setShareAmount(Integer shareAmount) {
        this.shareAmount = shareAmount;
    }
    public Double getSharePerPrice() {
        return sharePerPrice;
    }
    public void setSharePerPrice(Double sharePerPrice) {
        this.sharePerPrice = sharePerPrice;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getConsignor() {
        return consignor;
    }
    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }
}
