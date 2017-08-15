package com.sup.flow.busin;

import com.sup.flow.bean.WmFlowImccNode;

/**
 * @Description: 外贸进口业务层接口
 * @author Chu Zhaocheng
 * @date 2017年6月21日 下午2:16:29
 */
public interface WmFlowImccBusin {
    
    /**
     * @Description 保存外贸票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 下午2:06:37
     * @action saveWmFlowImccNode
     * @return void
     */
    void saveWmFlowImccNode(WmFlowImccNode wmFlowImccNode, String billId,String nodeId);
    
    /**
     * @Description 根据标号得到一条票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 下午2:07:07
     * @action getWmFlowImccNode
     * @return WmFlowImccNode
     */
    WmFlowImccNode getWmFlowImccNode(String id);
    
    /**
     * @Description 删除票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 下午2:07:21
     * @action removeWmFlowImccNode
     * @return void
     */
    void removeWmFlowImccNode(String id);
    
    /**
     * @Description 根据票据id查询票据信息,返回节点名称
     * @author Chu Zhaocheng
     * @date 2017年6月21日 下午2:12:52
     * @action getWmFlowImcc
     * @return String
     */
    String getWmFlowImcc(String billId);

}
