package com.sup.order.bean;
/**
 * 单据详情
 * @author litao
 *
 */
public class OrderModel {
     //操作环节
     private String optionName;
     //节点状态
     private Integer nodeState=0;
     //节点描述
     private String nodeDesc;
     
    public String getOptionName() {
        return optionName;
    }
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
    public Integer getNodeState() {
        return nodeState;
    }
    public void setNodeState(Integer nodeState) {
        this.nodeState = nodeState;
    }
    public String getNodeDesc() {
        return nodeDesc;
    }
    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }
    
   
}
