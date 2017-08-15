package com.sup.flow.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 报关进口通关流程单据
 * @author Administrator
 *
 */
/**
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BG_Flow_IMCC")
public class BgFlowImcc extends MetaObject {

    /**
     * 任务ID
     */
    @Column(name = "task_id")
    private String taskId;

    /**
     * 单据名称
     */
    @Column(name = "bill_name")
    private String name;

    /**
     * 订单ID 所属订单
     */
    @Column(name = "order_from_id")
    private String orderFrom;

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    /**
     * 节点状态
     */
    @Column(name = "node_state")
    private String nodeState;

    /**
     * 所属节点
     */
    @Column(name = "node_id")
    private String nodeId;
    
    /**
     * 单据Id
     */
    @Column(name = "bill_id")
    private String billId;

    /**
     * 票据名称
     */
    @Column(name = "bill_name2")
    private String billName;
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
     * 操作公司
     */
    @Column(name = "department")
    private String department;
    /**
     * 客户自编号
     */
    @Column(name = "cself_no")
    private String cselfNo;
    /**
     * 件数(PKG)
     */
    @Column(name = "items")
    private Integer items;
    /**
     * 毛重(kgs)
     */
    @Column(name = "gross")
    private Double gross;
    /**
     * 船名/航次
     */
    @Column(name = "flight")
    private String flight;
    /**
     * 提运单号
     */
    @Column(name = "waybill_no")
    private String waybillNo;
    /**
     * 经营单位
     */
    @Column(name = "operating_unit")
    private String operatingUnit;
    /**
     * 品名
     */
    @Column(name = "item_name")
    private String itemName;
    /**
     * 最后更新时间
     */
    @Column(name = "last_update")
    private Date lastUpdate;
    /**
     * 状态
     */
    @Column(name = "flag")
    private Integer flag;
    
    /**
     * 任务单流水号
     */
    @Column(name="order_num")
    private String  orderNum;
    
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

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCselfNo() {
        return cselfNo;
    }

    public void setCselfNo(String cselfNo) {
        this.cselfNo = cselfNo;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }
    
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Double getGross() {
        return gross;
    }

    public void setGross(Double gross) {
        this.gross = gross;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(String operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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
}
