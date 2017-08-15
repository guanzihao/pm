package com.sup.flow.busin;

import com.sup.flow.bean.WlFlowExccNode;

/**
 * @Description: 物流出口模块业务处理
 * @author Chu Zhaocheng
 * @date 2017年6月20日 下午3:00:50
 */
public interface WlFlowExccBusin {
    
    /**
     * @Description 根据票据id查询票据信息,返回节点名称
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午3:01:08
     * @action getWlFlowExcc
     * @return String
     */
    String getWlFlowExcc(String id);
    
    /**
     * @Description 根据标号得到一条票据节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午3:01:21
     * @action getWlFlowExccNode
     * @return WlFlowExccNode
     */
    WlFlowExccNode getWlFlowExccNode(String id);
    
    /**
     * @Description 保存物流出口流程节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午3:01:41
     * @action saveWlFlowExccNode
     * @return void
     */
    void saveWlFlowExccNode(WlFlowExccNode wlFlowExccNode, String billId,String nodeId);
    
    /**
     * @Description 删除物流出口流程节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午3:02:03
     * @action removeWlFlowExccNode
     * @return void
     */
    void removeWlFlowExccNode(String id);
}
