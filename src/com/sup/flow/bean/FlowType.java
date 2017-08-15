package com.sup.flow.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 流程对象
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="PM_FlowType")
public class FlowType extends MetaObject{
    
    /**
     * 流程名称
     */
    @Column(name="flow_name")
    private String flowName;
    
    /**
     * 单据表名
     */
    @Column(name="table_name")
    private String tableName;
    
    /**
     * 单据类型
     */
    @Column(name="flow_type")
    private String flowType;
    
    /**
     * 节点记录表名
     */
    @Column(name="nodetable_name")
    private String nodetableName;
    
    public String getFlowName() {
        return flowName;
    }
    
    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getNodetableName() {
        return nodetableName;
    }

    public void setNodetableName(String nodetableName) {
        this.nodetableName = nodetableName;
    }
    
}
