package com.sup.flow.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 流程节点字段定义
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="PM_FlowNode")
public class FlowNode extends MetaObject{
    
    /**
     * 流程ID
     */
    @Column(name="flow_id")
    private String flowId;
    
    /**
     * 节点名称
     */
    @Column(name="node_name")
    private String nodeName;
    
    /**
     * 节点顺序
     */
    @Column(name="node_sort")
    private String nodeSort;
    
    /**
     * 是否必须
     */
    @Column(name="is_necessary")
    private String isNecessary;

    /**
     * 是否发邮件
     */
    @Column(name="is_email")
    private String isEmail;
    
    /**
     * 是否有子流程
     */
    @Column(name="is_child")
    private String isChild;
    
    /**
     * 是否有子流程
     */
    @Column(name="child_id")
    private String childId;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeSort() {
        return nodeSort;
    }

    public void setNodeSort(String nodeSort) {
        this.nodeSort = nodeSort;
    }

    public String getIsNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(String isNecessary) {
        this.isNecessary = isNecessary;
    }

    public String getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(String isEmail) {
        this.isEmail = isEmail;
    }

    public String getIsChild() {
        return isChild;
    }

    public void setIsChild(String isChild) {
        this.isChild = isChild;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }
}
