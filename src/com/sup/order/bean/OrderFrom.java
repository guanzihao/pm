package com.sup.order.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.bean.MetaObject;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;

/**
 * 订单
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="PM_OrderFrom")
public class OrderFrom extends MetaObject {
    
    
    
    /**
     * 任务类型ID
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="tack_type_id")
    private FlowType flowType;
  
    /**
     *所属任务
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "task_id")
    private Task task;
    
   
    /**
     * 供应商
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supplier")
    private SupCompanyInfo  suppers;
    
    /**
     * 是否分配
     */
    @Column(name = "order_check")
    private Integer orderCheck;
    
    /**
     * 分配人
     */
    @Column(name = "order_auditor")
    private String orderAuditor;
    /**
     * 任务描述
     */
    @Column(name = "order_opinion")
    private String orderOpinion;
    
    /**
     * 分配日期
     */
    @Column(name = "order_check_date")
    private Date orderCheckDate;
    
    /**
     * 实际完成日期
     */
    @Column(name = "accomplish_date")
    private Date accomplishDate;
    /**
     * 计划完成日期
     */
    @Column(name = "project_accomplish_date")
    private Date projectAccomplishDate;
    
    /**
     * 最新节点
     */
    @Column(name = "node_id")
    private String nodeId;
    /**
     * 节点状态
     */
    @Column(name = "node_state")
    private String nodeState;
    /**
     * 订单状态
     */
    @Column(name = "order_state")
    private Integer orderState;
    /**
     * 订单号
     */
    @Column(name = "order_code")
    private String orderCode;
    /**
     * 节点时间
     */
    @Column(name = "node_date")
    private Date nodeDate;
    
    /**
     * 订单附件
     */
    @Column(name = "orderFrom_file")
    private String  orderFromFile;
    /**
     * 节点状态1
     */
    @Column(name = "node1_state")
    private Integer node1State;
    /**
     * 节点描述1
     */
    @Column(name = "node1_desc")
    private String node1Desc;
    /**
     * 节点状态2
     */
    @Column(name = "node2_state")
    private Integer node2State;
    /**
     * 节点描述2
     */
    @Column(name = "node2_desc")
    private String node2Desc;
    /**
     * 节点状态3
     */
    @Column(name = "node3_state")
    private Integer node3State;
    /**
     * 节点描述3
     */
    @Column(name = "node3_desc")
    private String node3Desc;
    /**
     * 节点状态4
     */
    @Column(name = "node4_state")
    private Integer node4State;
    /**
     * 节点描述4
     */
    @Column(name = "node4_desc")
    private String node4Desc;
    /**
     * 节点状态5
     */
    @Column(name = "node5_state")
    private Integer node5State;
    /**
     * 节点描述5
     */
    @Column(name = "node5_desc")
    private String node5Desc;
    /**
     * 节点状态6
     */
    @Column(name = "node6_state")
    private Integer node6State;
    /**
     * 节点描述6
     */
    @Column(name = "node6_desc")
    private String node6Desc;
    /**
     * 节点状态7
     */
    @Column(name = "node7_state")
    private Integer node7State;
    /**
     * 节点描述7
     */
    @Column(name = "node7_desc")
    private String node7Desc;
    
    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }
    
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    

    public SupCompanyInfo getSuppers() {
        return suppers;
    }

    public void setSuppers(SupCompanyInfo suppers) {
        this.suppers = suppers;
    }
   
    public String getOrderFromFile() {
        return orderFromFile;
    }

    public void setOrderFromFile(String orderFromFile) {
        this.orderFromFile = orderFromFile;
    }

    public Integer getOrderCheck() {
        return orderCheck;
    }

    public void setOrderCheck(Integer orderCheck) {
        this.orderCheck = orderCheck;
    }

    public String getOrderAuditor() {
        return orderAuditor;
    }

    public void setOrderAuditor(String orderAuditor) {
        this.orderAuditor = orderAuditor;
    }

    public String getOrderOpinion() {
        return orderOpinion;
    }

    public void setOrderOpinion(String orderOpinion) {
        this.orderOpinion = orderOpinion;
    }

    public Date getOrderCheckDate() {
        return orderCheckDate;
    }

    public void setOrderCheckDate(Date orderCheckDate) {
        this.orderCheckDate = orderCheckDate;
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

    public Date getNodeDate() {
        return nodeDate;
    }

    public void setNodeDate(Date nodeDate) {
        this.nodeDate = nodeDate;
    }

    public Integer getNode1State() {
        return node1State;
    }

    public void setNode1State(Integer node1State) {
        this.node1State = node1State;
    }

    public String getNode1Desc() {
        return node1Desc;
    }

    public void setNode1Desc(String node1Desc) {
        this.node1Desc = node1Desc;
    }

    public Integer getNode2State() {
        return node2State;
    }

    public void setNode2State(Integer node2State) {
        this.node2State = node2State;
    }

    public String getNode2Desc() {
        return node2Desc;
    }

    public void setNode2Desc(String node2Desc) {
        this.node2Desc = node2Desc;
    }

    public Integer getNode3State() {
        return node3State;
    }

    public void setNode3State(Integer node3State) {
        this.node3State = node3State;
    }

    public String getNode3Desc() {
        return node3Desc;
    }

    public void setNode3Desc(String node3Desc) {
        this.node3Desc = node3Desc;
    }

    public Integer getNode4State() {
        return node4State;
    }

    public void setNode4State(Integer node4State) {
        this.node4State = node4State;
    }

    public String getNode4Desc() {
        return node4Desc;
    }

    public void setNode4Desc(String node4Desc) {
        this.node4Desc = node4Desc;
    }

    public Integer getNode5State() {
        return node5State;
    }

    public void setNode5State(Integer node5State) {
        this.node5State = node5State;
    }

    public String getNode5Desc() {
        return node5Desc;
    }

    public void setNode5Desc(String node5Desc) {
        this.node5Desc = node5Desc;
    }

    public Integer getNode6State() {
        return node6State;
    }

    public void setNode6State(Integer node6State) {
        this.node6State = node6State;
    }

    public String getNode6Desc() {
        return node6Desc;
    }

    public void setNode6Desc(String node6Desc) {
        this.node6Desc = node6Desc;
    }

    public Integer getNode7State() {
        return node7State;
    }

    public void setNode7State(Integer node7State) {
        this.node7State = node7State;
    }

    public String getNode7Desc() {
        return node7Desc;
    }

    public void setNode7Desc(String node7Desc) {
        this.node7Desc = node7Desc;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getAccomplishDate() {
        return accomplishDate;
    }

    public void setAccomplishDate(Date accomplishDate) {
        this.accomplishDate = accomplishDate;
    }

    public Date getProjectAccomplishDate() {
        return projectAccomplishDate;
    }

    public void setProjectAccomplishDate(Date projectAccomplishDate) {
        this.projectAccomplishDate = projectAccomplishDate;
    }
}
