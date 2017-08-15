package com.sup.flow.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;
/**
 * 出口物流单据表
 * @author litao
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WL_Flow_EXCC")
public class WlFlowExcc extends MetaObject{
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
     * 单据名称
     */
    @Column(name = "bill_name")
    private String name;
    
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
    private Double volume;
    /**
     * 提货地址
     */
    @Column(name = "pickup_addr")
    private String pickupAddr;
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
     * 实际出运时间
     */
    @Column(name="shipping_date")
    private Date shippingDate;
    
    /**
     * 报关放行时间
     */
    @Column(name="release_date")
    private Date releaseDate;
    
    /**
     * 接单时间
     */
    @Column(name="order_receiving_date")
    private Date orderReceivingDate;
    
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
    
    public String getTaskId() {
        return taskId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNodeState() {
        return nodeState;
    }

    public void setNodeState(String nodeState) {
        this.nodeState = nodeState;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getPickupAddr() {
        return pickupAddr;
    }

    public void setPickupAddr(String pickupAddr) {
        this.pickupAddr = pickupAddr;
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

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getOrderReceivingDate() {
        return orderReceivingDate;
    }

    public void setOrderReceivingDate(Date orderReceivingDate) {
        this.orderReceivingDate = orderReceivingDate;
    }
}
