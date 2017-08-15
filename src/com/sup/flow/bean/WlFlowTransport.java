package com.sup.flow.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.pm.core.bean.MetaObject;

/**
 * @Description: 物流运输流程单据
 * @author Chu Zhaocheng
 * @date 2017年6月13日 下午2:52:19
 */
@SuppressWarnings("serial")
@Entity
@Table(name="WL_Flow_Transport")
public class WlFlowTransport extends MetaObject{
    
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
     * 业务流水号
     */
    @Column(name="norder_no")
    private String  norderNo;
    /**
     * 客户纳税识别码
     */
    @Column(name="custom_code")
    private String  customCode;
    /**
     * 操作公司
     */
    @Column(name="department")
    private String  department;
    /**
     * 最后更新时间
     */
    @Column(name="last_update")
    private String  lastUpdate;
    /**
     * 状态
     */
    @Column(name="flag")
    private Integer flag;
    

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

    public String getNodeState() {
        return nodeState;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setOrderFromId(String orderFromId) {
        this.orderFromId = orderFromId;
    }
    
    
}
