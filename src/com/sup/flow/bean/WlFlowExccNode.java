package com.sup.flow.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * @Description: 物流出口单据节点表
 * @author Chu Zhaocheng
 * @date 2017年6月20日 下午4:47:50
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WL_Flow_EXCC_Node")
public class WlFlowExccNode extends MetaObject {
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
     * 查验时间
     */
    @Column(name="check_date")
    private Date checkDate;
    
    /**
     * 放行时间
     */
    @Column(name="green_light_date")
    private Date greenLightDate;
    
    
    /**
     * 实际出运时间
     */
    @Column(name="shipping_date")
    private Date shippingDate;
    
    /**
     * 实际到达时间
     */
    @Column(name="reach_date")
    private Date reachDate;
    
    /**
     * 换单时间
     */
    @Column(name="Reinvoice_date")
    private Date reinvoiceDate;
    
    /**
     * 国内运输时间
     */
    @Column(name="domesticcarriage_date")
    private Date domesticcarriageDate;
    
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
     * 船名
     */
    @Column(name="ship_name")
    private String shipName;
    /**
     * 航次/航班
     */
    @Column(name="flight")
    private String flight;
    /**
     * 起运港
     */
    @Column(name="pol")
    private String pol;
    /**
     * 目的港
     */
    @Column(name="pod")
    private String pod;
    /**
     * 主单号
     */
    @Column(name="mbl_no")
    private String mblNo;
    /**
     * 分单号
     */
    @Column(name="hbl_no")
    private String hblNo;
    /**
     * 接单时间
     */
    @Column(name="accept_date")
    private Date acceptDate;
    /**
     * 开航日期
     */
    @Column(name="sail_date")
    private Date sailDate;
    /**
     * 实际到港时间
     */
    @Column(name="arrival_date")
    private Date arrivalDate;
    /**
     * 换单时间
     */
    @Column(name="switch_bill_date")
    private Date switchBillDate;
    /**
     * 报关放行时间
     */
    @Column(name="release_date")
    private Date releaseDate;
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
     * 数据状态
     */
    @Column(name="flag")
    private int flag;
    
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getGreenLightDate() {
        return greenLightDate;
    }

    public void setGreenLightDate(Date greenLightDate) {
        this.greenLightDate = greenLightDate;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Date getSailDate() {
        return sailDate;
    }

    public void setSailDate(Date sailDate) {
        this.sailDate = sailDate;
    }

    public Date getReachDate() {
        return reachDate;
    }

    public void setReachDate(Date reachDate) {
        this.reachDate = reachDate;
    }

    public Date getReinvoiceDate() {
        return reinvoiceDate;
    }

    public void setReinvoiceDate(Date reinvoiceDate) {
        this.reinvoiceDate = reinvoiceDate;
    }

    public Date getDomesticcarriageDate() {
        return domesticcarriageDate;
    }

    public void setDomesticcarriageDate(Date domesticcarriageDate) {
        this.domesticcarriageDate = domesticcarriageDate;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getMblNo() {
        return mblNo;
    }

    public void setMblNo(String mblNo) {
        this.mblNo = mblNo;
    }

    public String getHblNo() {
        return hblNo;
    }

    public void setHblNo(String hblNo) {
        this.hblNo = hblNo;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getSwitchBillDate() {
        return switchBillDate;
    }

    public void setSwitchBillDate(Date switchBillDate) {
        this.switchBillDate = switchBillDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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

    public Integer getIsSendEmail() {
        return isSendEmail;
    }

    public void setIsSendEmail(Integer isSendEmail) {
        this.isSendEmail = isSendEmail;
    }
    
    
}
