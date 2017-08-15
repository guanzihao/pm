package com.sup.flow.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.pm.core.bean.MetaObject;

/**
 * @Description: 仓储出库流程节点记录
 * @author Chu Zhaocheng
 * @date 2017年6月14日 上午11:04:51
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CC_FLOW_EXCC_NODE")
public class CcFlowExccNode extends MetaObject{
    
    /**
     * 任务ID
     */
    @Column(name="task_id")
    private String taskId;
    
    /**
     * 节点ID
     */
    @Column(name="node_id")
    private String nodeId;
    
    /**
     * 单据Id
     */
    @Column(name="bill_id")
    private String billId;
    
    /**
     * 开始时间
     */
    @Column(name="start_date")
    private Date startDate;
    
    /**
     * 完成时间
     */
    @Column(name="end_date")
    private Date endDate;
    
    /**
     * 状态
     */
    @Column(name="state")
    private String state;
    
    /**
     * 备注
     */
    @Column(name="remarks")
    private String remarks;
    

    /**
     * 附件1
     */
    @Column(name="one_accessory")
    private String oneAccessory;
    
    /**
     * 附件2
     */
    @Column(name="two_accessory")
    private String twoAccessory;
    
    /**
     * 接单时间
     */
    @Column(name="order_receiving_date")
    private Date orderReceivingDate;

    /**
     * 司机签收时间
     */
    @Column(name="driver_sign_date")
    private Date driverSignDate;
    
    /**
     * 客户签收时间
     */
    @Column(name="customer_sign_date")
    private Date customerSignDate;
    
    
    /**
     * 是否邮件已发送
     */
    @Column(name = "is_send_email")
    private Integer isSendEmail;
    /**
     * 实际出库号
     */
    @Column(name="item_id")
    private Integer itemId;

    /**
     * 出库托单号
     */
    @Column(name="order_bill_id")
    private String orderBillId;
    /**
     * 合同号
     */
    @Column(name="contract_num")
    private String contractNum;
    /**
     * 任务单流水号
     */
    @Column(name="order_num")
    private String orderNum;
    /**
     * 客户纳税识别码
     */
    @Column(name="custom_code")
    private String customCode;
    /**
     * 流程节点
     */
    @Column(name="flow_node")
    private Integer flowNode;
    /**
     * 流程状态
     */
    @Column(name="status")
    private Integer status;
    /**
     * 完成情况说明
     */
    @Column(name="remark")
    private String remark;
    /**
     * 接单登记时间
     */
    @Column(name="bussiness_date")
    private Date bussinessDate;
    /**
     * 送货地址
     */
    @Column(name="delivery_addr")
    private String deliveryAddr;
    /**
     * 司机签收时间
     */
    @Column(name="sign_date")
    private Date signDate;
    /**
     * 供应商类型
     */
    @Column(name="type")
    private Integer type;
    /**
     * 操作公司
     */
    @Column(name="ope_comp")
    private String opeComp;
    /**
     * 操作一级部门
     */
    @Column(name="first_dept")
    private Integer firstDept;
    /**
     * 操作二级部门
     */
    @Column(name="sec_dept")
    private Integer secDept;
    /**
     * 操作人ID
     */
    @Column(name="operator")
    private Integer operator;
    /**
     * 操作人姓名
     */
    @Column(name="operator_name")
    private String operatorName;
    /**
     * 最后更新时间
     */
    @Column(name="last_update")
    private Date lastUpdate;
    /**
     * 标识
     */
    @Column(name="flag")
    private Integer flag;
    

    public String getOneAccessory() {
        return oneAccessory;
    }

    public void setOneAccessory(String oneAccessory) {
        this.oneAccessory = oneAccessory;
    }

    public String getTwoAccessory() {
        return twoAccessory;
    }

    public void setTwoAccessory(String twoAccessory) {
        this.twoAccessory = twoAccessory;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getDriverSignDate() {
        return driverSignDate;
    }

    public void setDriverSignDate(Date driverSignDate) {
        this.driverSignDate = driverSignDate;
    }

    public Date getCustomerSignDate() {
        return customerSignDate;
    }

    public void setCustomerSignDate(Date customerSignDate) {
        this.customerSignDate = customerSignDate;
    }
    
    public Date getOrderReceivingDate() {
        return orderReceivingDate;
    }

    public void setOrderReceivingDate(Date orderReceivingDate) {
        this.orderReceivingDate = orderReceivingDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getOrderBillId() {
        return orderBillId;
    }

    public void setOrderBillId(String orderBillId) {
        this.orderBillId = orderBillId;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    public Integer getFlowNode() {
        return flowNode;
    }

    public void setFlowNode(Integer flowNode) {
        this.flowNode = flowNode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getBussinessDate() {
        return bussinessDate;
    }

    public void setBussinessDate(Date bussinessDate) {
        this.bussinessDate = bussinessDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOpeComp() {
        return opeComp;
    }

    public void setOpeComp(String opeComp) {
        this.opeComp = opeComp;
    }

    public Integer getFirstDept() {
        return firstDept;
    }

    public void setFirstDept(Integer firstDept) {
        this.firstDept = firstDept;
    }

    public Integer getSecDept() {
        return secDept;
    }

    public void setSecDept(Integer secDept) {
        this.secDept = secDept;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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
    public Integer getIsSendEmail() {
        return isSendEmail;
    }

    public void setIsSendEmail(Integer isSendEmail) {
        this.isSendEmail = isSendEmail;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
    
    
}
