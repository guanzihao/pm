package com.sup.flow.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.pm.core.bean.MetaObject;

/**
 * @Description: 外贸出口流程节点记录
 * @author Chu Zhaocheng
 * @date 2017年6月21日 下午2:58:40
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WM_Flow_EXCC_Node")
public class WmFlowExccNode extends MetaObject {

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
     * 流程状态
     */
    @Column(name = "state")
    private String state;

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
     * 合同号
     */
    @Column(name = "contract_num")
    private String contractNum;
    /**
     * 合同流水号
     */
    @Column(name = "business_no")
    private String businessNo;
    
    /**
     * 合同流水号
     */
    @Column(name = "order_from_id")
    private String orderFromId;
    
    /**
     * 客户纳税识别码
     */
    @Column(name = "custom_code")
    private String customCode;
    /**
     * 流程节点
     */
    @Column(name = "flow_node")
    private Integer flowNode;
    /**
     * 备注
     */
    @Column(name = "note")
    private String note;
    
    /**
     * 任务流水号
     */
    @Column(name = "order_num")
    private String orderNum;

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
     * 收汇分拆确认时间
     */
    @Column(name = "div_date")
    private Date divDate;
    /**
     * 收汇金额
     */
    @Column(name = "rec_amount")
    private Double recAmount;
    /**
     * 收汇外币币种
     */
    @Column(name = "rec_currency")
    private String recCurrency;
    /**
     * 收汇时间
     */
    @Column(name = "receive_date")
    private Date receiveDate;
    /**
     * 出运单生效时间
     */
    @Column(name = "way_bill_date")
    private Date wayBillDate;
    
    /**
     * 结算单生效日期
     */
    @Column(name = "balance_date")
    private Date balanceDate;
    
    /**
     * 退税申请单生效日期
     */
    @Column(name = "refund_time")
    private Date refundTime;
    /**
     * 供应商类型
     */
    @Column(name = "type")
    private Integer type;
    /**
     * 操作公司
     */
    @Column(name = "ope_comp")
    private String opeComp;
    /**
     * 操作一级部门
     */
    @Column(name = "first_dept")
    private Integer firstDept;
    /**
     * 操作二级部门
     */
    @Column(name = "sec_dept")
    private Integer secDept;
    /**
     * 操作人ID
     */
    @Column(name = "operator")
    private String operator;
    /**
     * 操作人姓名
     */
    @Column(name = "operator_name")
    private String operatorName;
    /**
     * 最后更新日期
     */
    @Column(name = "last_update")
    private Date lastUpdate;
    /**
     * 状态
     */
    @Column(name = "flag")
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
    public Integer getIsSendEmail() {
        return isSendEmail;
    }
    public void setIsSendEmail(Integer isSendEmail) {
        this.isSendEmail = isSendEmail;
    }
    public String getContractNum() {
        return contractNum;
    }
    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }
    public String getBusinessNo() {
        return businessNo;
    }
    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
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
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
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
    public Date getDivDate() {
        return divDate;
    }
    public void setDivDate(Date divDate) {
        this.divDate = divDate;
    }
    public Double getRecAmount() {
        return recAmount;
    }
    public void setRecAmount(Double recAmount) {
        this.recAmount = recAmount;
    }
    public String getRecCurrency() {
        return recCurrency;
    }
    public void setRecCurrency(String recCurrency) {
        this.recCurrency = recCurrency;
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
    public Date getBalanceDate() {
        return balanceDate;
    }
    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }
    public Date getRefundTime() {
        return refundTime;
    }
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
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
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
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
    public String getOrderFromId() {
        return orderFromId;
    }
    public void setOrderFromId(String orderFromId) {
        this.orderFromId = orderFromId;
    }
    public String getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    
    
}
