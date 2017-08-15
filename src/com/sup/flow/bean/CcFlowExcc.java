package com.sup.flow.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * @Description: 仓储出库流程单据
 * @author Chu Zhaocheng
 * @date 2017年6月14日 上午11:01:49
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CC_FLOW_EXCC")
public class CcFlowExcc extends MetaObject{
    
    /**
     * 任务ID
     */
    @Column(name="task_id")
    private String taskId;
    
    /**
     * 订单ID
     */
    @Column(name="order_from_id")
    private String orderFromId;
    
    /**
     * 单据名称
     */
    @Column(name = "bill_name")
    private String name;
    
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
    @Column(name="order_num")
    private String  orderNum;
    /**
     * 客户纳税识别码
     */
    @Column(name="custom_code")
    private String  customCode;
    /**
     * 合同号
     */
    @Column(name="contract_num")
    private String  contractNum;
    /**
     * 出库托单号
     */
    @Column(name="order_bill_id")
    private String  orderBillId;
    /**
     * 委托客户
     */
    @Column(name="customer")
    private String  customer;
    /**
     * 制单日期
     */
    @Column(name="making_date")
    private Date  makingDate;
    /**
     * 制单人
     */
    @Column(name="making_person")
    private String  makingPerson;
    /**
     * 操作公司
     */
    @Column(name="department")
    private String  department;
    /**
     * 操作仓库
     */
    @Column(name="ware_house")
    private String  wareHouse;
    /**
     * 客户自编号
     */
    @Column(name="cself_no")
    private String  cselfNo;
    /**
     * 品名
     */
    @Column(name="item_name")
    private String  itemName;
    /**
     * 件数
     */
    @Column(name="items")
    private Integer  items;
    /**
     * 毛重
     */
    @Column(name="gross")
    private Double  gross;
    /**
     * 报关单号
     */
    @Column(name="custom_cl")
    private String  customCl;
    /**
     * 船名
     */
    @Column(name="ship_name")
    private String  shipName;
    /**
     * 航次
     */
    @Column(name="flight")
    private String  flight;
    /**
     * 提运单号
     */
    @Column(name="way_bill_no")
    private String  wayBillNo;
    /**
     * 最后更新时间
     */
    @Column(name="last_update")
    private Date  lastUpdate;
    /**
     * 标识
     */
    @Column(name="flag")
    private Integer  flag;
    
    /**
     * 接单时间
     */
    @Column(name="order_receiving_date")
    private Date orderReceivingDate;
    
    /**
     * 接单登记时间
     */
    @Column(name="bussiness_date")
    private Date bussinessDate;
    
    /**
     * 司机签收时间
     */
    @Column(name="sign_date")
    private Date signDate;
    
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String tackId) {
        this.taskId = tackId;
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

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getOrderBillId() {
        return orderBillId;
    }

    public void setOrderBillId(String orderBillId) {
        this.orderBillId = orderBillId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getMakingDate() {
        return makingDate;
    }

    public void setMakingDate(Date makingDate) {
        this.makingDate = makingDate;
    }

    public String getMakingPerson() {
        return makingPerson;
    }

    public void setMakingPerson(String makingPerson) {
        this.makingPerson = makingPerson;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
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

    public String getCustomCl() {
        return customCl;
    }

    public void setCustomCl(String customCl) {
        this.customCl = customCl;
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

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrderReceivingDate() {
        return orderReceivingDate;
    }

    public void setOrderReceivingDate(Date orderReceivingDate) {
        this.orderReceivingDate = orderReceivingDate;
    }

    public Date getBussinessDate() {
        return bussinessDate;
    }

    public void setBussinessDate(Date bussinessDate) {
        this.bussinessDate = bussinessDate;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
}
