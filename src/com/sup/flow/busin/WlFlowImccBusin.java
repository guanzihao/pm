package com.sup.flow.busin;

import com.sup.flow.bean.WlFlowImccNode;

/**
 * @Description: 物流进口模块业务处理
 * @author Chu Zhaocheng
 * @date 2017年6月20日 下午4:16:02
 */
public interface WlFlowImccBusin {
    
    /**
     * @Description 根据标号得到一条票据信息(物流)
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午4:16:33
     * @action getWlFlowImccNode
     * @return WlFlowImccNode
     */
    WlFlowImccNode getWlFlowImccNode(String id);
    
    
    /**
     * @Description 删除票据信息（物流）
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:50:44
     * @action removeWlFlowImccNode
     * @return void
     */
    void removeWlFlowImccNode(String id);
    
    /**
     * @Description 添加物流进口节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月20日 上午11:21:15
     * @action saveWlFlowImccNode
     * @return void
     */
    void saveWlFlowImccNode(WlFlowImccNode wlFlowImccNode, String billId, String nodeId);
    
    /**
     * 根据票据id查看票据信息,返回节点名称
     * @param id
     * @return
     */
    String getWlFlowImcc(String id);
}
