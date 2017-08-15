package com.sup.flow.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * @Description: 仓储进库流程节点记录
 * @author Chu Zhaocheng
 * @date 2017年6月14日 上午11:04:51
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CC_FLOW_IMCC_NODE")
public class CcFlowImccNode extends MetaObject{
    
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
     * 实际入库时间
     */
    @Column(name="actual_storing_date")
    private Date actualStoringDate;
    
    /**
     * 入库完成时间
     */
    @Column(name="stored_finish_date")
    private Date storedFinishDate;
    
    /**
     * 是否邮件已发送
     */
    @Column(name = "is_send_email")
    private Integer isSendEmail;
    /**
     * 实际入库号
     */
    @Column(name="item_id")
    private Integer itemId;
    /**
     * 入库托单号
     */
    @Column(name="order_bill_no")
    private String orderBillNo;
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
     * 入库地址
     */
    @Column(name="in_addr")
    private String inAddr;
    /**
     * 实际入库时间
     */
    @Column(name="actual_in_date")
    private Date actualInDate;
    /**
     * 入库完成时间
     */
    @Column(name="in_finish_date")
    private Date inFinishDate;
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

    public Date getOrderReceivingDate() {
        return orderReceivingDate;
    }

    public void setOrderReceivingDate(Date orderReceivingDate) {
        this.orderReceivingDate = orderReceivingDate;
    }

    public Date getActualStoringDate() {
        return actualStoringDate;
    }

    public void setActualStoringDate(Date actualStoringDate) {
        this.actualStoringDate = actualStoringDate;
    }

    public Date getStoredFinishDate() {
        return storedFinishDate;
    }

    public void setStoredFinishDate(Date storedFinishDate) {
        this.storedFinishDate = storedFinishDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getOrderBillNo() {
        return orderBillNo;
    }

    public void setOrderBillNo(String orderBillNo) {
        this.orderBillNo = orderBillNo;
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

    public String getInAddr() {
        return inAddr;
    }

    public void setInAddr(String inAddr) {
        this.inAddr = inAddr;
    }

    public Date getActualInDate() {
        return actualInDate;
    }

    public void setActualInDate(Date actualInDate) {
        this.actualInDate = actualInDate;
    }

    public Date getInFinishDate() {
        return inFinishDate;
    }

    public void setInFinishDate(Date inFinishDate) {
        this.inFinishDate = inFinishDate;
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
    
}
