package com.sup.flow.busin;

import com.sup.flow.bean.WlFlowTransportNode;

/**
 * @Description: 物流运输流程单据
 * @author Chu Zhaocheng
 * @date 2017年6月13日 下午3:39:29
 */
public interface WlFlowTransportBusin {
    
    /**
     * @Description 根据票据id查看票据信息,返回节点名称
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:41:55
     * @action getWlFlowTransport
     * @return String
     */
    String getWlFlowTransport(String id);
    
    /**
     * @Description 根据标号得到一条票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:42:20
     * @action getWlFlowTransportNode
     * @return WlFlowTransportNode
     */
    WlFlowTransportNode getWlFlowTransportNode(String id);
    
    /**
     * @Description 保存票据信息(接单)
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:42:36
     * @action saveWlFlowTransportNode
     * @return void
     */
    void saveWlFlowTransportNode(WlFlowTransportNode WlFlowTransportNode, String billId);
    
    /**
     * @Description 删除票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:42:50
     * @action removeWlFlowTransportNode
     * @return void
     */
    void removeWlFlowTransportNode(String id);
}
