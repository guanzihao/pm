package com.sup.flow.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.pm.core.bean.MetaObject;

/**
 * @Description: 物流运输流程节点记录
 * @author Chu Zhaocheng
 * @date 2017年6月13日 下午3:15:43
 */
@SuppressWarnings("serial")
@Entity
@Table(name="WL_Flow_Transport_Node")
public class WlFlowTransportNode extends MetaObject{
    
    /**
     * 任务ID
     */
    @Column(name="task_id")
    private String taskId;
    /**
     * 数据状态
     */
    @Column(name="flag")
    private int flag;
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
     * 派车时间
     */
    @Column(name="send_car_date")
    private Date sendCarDate;
    
    /**
     * 运输完成时间
     */
    @Column(name="transport_finish_date")
    private Date transportFinishDate;
    
    
    /**
     * 是否邮件已发送
     */
    @Column(name = "is_send_email")
    private Integer isSendEmail;
    /**
     * 任务单流水号
     */
    @Column(name="order_num")
    private String orderNum;
    /**
     * 业务流水号
     */
    @Column(name="norder_no")
    private String norderNo;
    /**
     * 客户纳税识别码
     */
    @Column(name="custom_code")
    private String  customCode;
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
     * 接单时间
     */
    @Column(name="business_date")
    private Date businessDate;
    /**
     * 起运地
     */
    @Column(name="origin")
    private String origin;
    /**
     * 目的地
     */
    @Column(name="destination")
    private String destination;
    /**
     * 派车时间
     */
    @Column(name="vehicle_date")
    private Date vehicleDate;
    /**
     * 运输完成时间
     */
    @Column(name="trans_date")
    private Date transDate;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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

    public Date getSendCarDate() {
        return sendCarDate;
    }

    public void setSendCarDate(Date sendCarDate) {
        this.sendCarDate = sendCarDate;
    }

    public Date getTransportFinishDate() {
        return transportFinishDate;
    }

    public void setTransportFinishDate(Date transportFinishDate) {
        this.transportFinishDate = transportFinishDate;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getNorderNo() {
        return norderNo;
    }

    public void setNorderNo(String norderNo) {
        this.norderNo = norderNo;
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

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getVehicleDate() {
        return vehicleDate;
    }

    public void setVehicleDate(Date vehicleDate) {
        this.vehicleDate = vehicleDate;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
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

    public Integer getIsSendEmail() {
        return isSendEmail;
    }

    public void setIsSendEmail(Integer isSendEmail) {
        this.isSendEmail = isSendEmail;
    }
    
}
