package com.sup.flow.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * @Description: 外贸进口流程单据
 * @author Chu Zhaocheng
 * @date 2017年6月21日 下午4:16:50
 */
@SuppressWarnings("serial")
@Entity
@Table(name="WM_Flow_IMCC")
public class WmFlowImcc extends MetaObject{
    
    /**
     * 任务ID
     */
    @Column(name="task_id")
    private String taskId;
    
    /**
     * 单据名称
     */
    @Column(name="bill_name")
    private String name;
    /**
     * 订单ID
     */
    @Column(name="order_from_id")
    private String orderFromId;
    
    /**
     * 合同号
     */
    @Column(name = "contract_num")
    private String contractNum;
    
    /**
     * 节点状态
     */
    @Column(name="node_state")
    private String nodeState;
    
    /**
     * 所属节点
     */
    @Column(name="node_id")
    private String  nodeId;
    /**
     * 品名
     */
    @Column(name="item_name")
    private String itemName;
    /**
     * 客户纳税识别码
     */
    @Column(name="custom_code")
    private String customCode;
    /**
     * 操作公司
     */
    @Column(name="department")
    private String department;
    /**
     * 合同金额
     */
    @Column(name="contract_amount")
    private Double contractAmount;
    /**
     * 合同币值
     */
    @Column(name="contract_currency")
    private String contractCurrency;
   
    /**
     * 合同流水号
     */
    @Column(name="business_no")
    private String businessNo;
    /**
     * 最后更新日期
     */
    @Column(name="last_update")
    private Date lastUpdate;
    /**
     * 状态
     */
    @Column(name="flag")
    private Integer flag;
    
    /**
     * 外贸出口合同生效时间
     */
    @Column(name = "contract_start_date")
    private Date contractStartDate;
    
    /**
     * 信用证开证生效时间
     */
    @Column(name = "lc_start_date")
    private Date lcStartDate;
    
    /**
     * 收汇时间
     */
    @Column(name = "receive_date")
    private Date receiveDate;
    
    /**
     * 进口到货生效时间
     */
    @Column(name = "arrival_date")
    private Date arrivalDate;
    
    /**
     * 出运单生效时间
     */
    @Column(name = "way_bill_date")
    private Date wayBillDate;
    
    /**
     * 进口清关生效时间
     */
    @Column(name = "custom_clear_date")
    private Date customClearDate;
    
    /**
     * 实付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 客户名称
     */
    @Column(name = "company_name")
    private String companyName;
    
    /**
     * 业务流水号
     */
    @Column(name = "task_nun")
    private String taskNun;
    
    /**
     * 业务类型
     */
    @Column(name = "task_type")
    private String taskType;
    
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getTaskNun() {
        return taskNun;
    }
    public void setTaskNun(String taskNun) {
        this.taskNun = taskNun;
    }
    public String getTaskType() {
        return taskType;
    }
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

 
    public String getOrderFromId() {
        return orderFromId;
    }

    public void setOrderFromId(String orderFromId) {
        this.orderFromId = orderFromId;
    }
    
    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getLcStartDate() {
        return lcStartDate;
    }

    public void setLcStartDate(Date lcStartDate) {
        this.lcStartDate = lcStartDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getWayBillDate() {
        return wayBillDate;
    }

    public void setWayBillDate(Date wayBillDate) {
        this.wayBillDate = wayBillDate;
    }

    public Date getCustomClearDate() {
        return customClearDate;
    }

    public void setCustomClearDate(Date customClearDate) {
        this.customClearDate = customClearDate;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getNodeState() {
        return nodeState;
    }

    public void setNodeState(String nodeState) {
        this.nodeState = nodeState;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getContractCurrency() {
        return contractCurrency;
    }

    public void setContractCurrency(String contractCurrency) {
        this.contractCurrency = contractCurrency;
    }


    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
