package com.sup.flow.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 出口物流流程单据
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="WL_Flow_IMCC")
public class WlFlowImcc extends MetaObject{
    
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
     * 任务单流水号
     */
    @Column(name = "order_num")
    private String orderNum;
    /**
     * 业务流水号
     */
    @Column(name = "norder_no")
    private String norderNo;
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
     * 品名
     */
    @Column(name = "item_name")
    private String itemName;
    /**
     * 件数
     */
    @Column(name = "items")
    private Integer items;
    /**
     * 毛重
     */
    @Column(name = "gross")
    private Double gross;
    /**
     * 体积
     */
    @Column(name = "volume")
    private String volume;
    /**
     * 送货地址
     */
    @Column(name = "delivery_addr")
    private String deliveryAddr;
    /**
     * 最后更新时间
     */
    @Column(name = "last_update")
    private String lastUpdate;
    /**
     * 状态
     */
    @Column(name = "flag")
    private Integer flag;
    
    /**
     * 接单时间
     */
    @Column(name="accept_date")
    private Date acceptDate;
    
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
     * 出运时间
     */
    @Column(name="shipment_date")
    private Date shipmentDate;
    
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public Double getGross() {
        return gross;
    }

    public void setGross(Double gross) {
        this.gross = gross;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
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

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }
}
