package com.sup.flow.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 报关进口通关流程节点记录
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BG_Flow_IMCC_Node")
public class BgFlowImccNode extends MetaObject {

    /**
     * 任务ID
     */
    @Column(name = "task_id")
    private String taskId;

    /**
     * 节点ID
     */
    @Column(name = "node_id")
    private String nodeId;

    /**
     * 单据Id
     */
    @Column(name = "bill_id")
    private String billId;

    /**
     * 开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 完成时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 状态
     */
    @Column(name = "flag")
    private Integer flag;

    /**
     * 完成情况说明
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * 附件1
     */
    @Column(name = "one_accessory")
    private String oneAccessory;

    /**
     * 附件2
     */
    @Column(name = "two_accessory")
    private String twoAccessory;
    /**
     * 是否邮件已发送
     */
    @Column(name = "is_send_email")
    private Integer isSendEmail;
    /**
     * 业务流水号
     */
    @Column(name = "bussiness_no")
    private String bussinessNo;
    /**
     * 报关单号
     */
    @Column(name = "custom_no")
    private String customNo;

    /**
     * 客户纳税识别码
     */
    @Column(name = "custom_code")
    private String customCode;
    /**
     * 流程节点
     */
    @Column(name = "flow_note")
    private Integer flowNote;
    /**
     * 流程状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "note")
    private String note;

    /**
     * 接单时间
     */
    @Column(name = "bussiness_date")
    private Date bussinessDate;

    /**
     * 查验时间
     */
    @Column(name = "inspection_date")
    private Date inspectionDate;
    /**
     * 海关放行时间
     */
    @Column(name = "release_date")
    private Date releaseDate;

    /**
     * 任务单流水号
     */
    @Column(name="order_num")
    private String  orderNum;
    /**
     * 供应商类型
     */
    @Column(name = "type")
    private Integer type;
    /**
     * 操作公司
     */
    @Column(name = "opecomp")
    private String opeComp;
    /**
     * 操作一级部门
     */
    @Column(name = "first_dept")
    private String firstDept;
    /**
     * 操作二级部门
     */
    @Column(name = "sec_dept")
    private String secDept;
    /**
     * 操作人姓名
     */
    @Column(name = "operator_name")
    private String operatorName;
    /**
     * 操作人ID
     */
    @Column(name = "operator")
    private String operator;
    /**
     * 最后更新时间
     */
    @Column(name = "last_update")
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

    public String getBussinessNo() {
        return bussinessNo;
    }

    public void setBussinessNo(String bussinessNo) {
        this.bussinessNo = bussinessNo;
    }

    public String getCustomNo() {
        return customNo;
    }

    public void setCustomNo(String customNo) {
        this.customNo = customNo;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    public Integer getFlowNote() {
        return flowNote;
    }

    public void setFlowNote(Integer flowNote) {
        this.flowNote = flowNote;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public String getOpeComp() {
        return opeComp;
    }

    public void setOpeComp(String opeComp) {
        this.opeComp = opeComp;
    }

    
    public String getFirstDept() {
        return firstDept;
    }

    public void setFirstDept(String firstDept) {
        this.firstDept = firstDept;
    }

    public String getSecDept() {
        return secDept;
    }

    public void setSecDept(String secDept) {
        this.secDept = secDept;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getBussinessDate() {
        return bussinessDate;
    }

    public void setBussinessDate(Date bussinessDate) {
        this.bussinessDate = bussinessDate;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsSendEmail() {
        return isSendEmail;
    }

    public void setIsSendEmail(Integer isSendEmail) {
        this.isSendEmail = isSendEmail;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    
}
