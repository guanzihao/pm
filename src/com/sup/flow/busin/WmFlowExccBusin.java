package com.sup.flow.busin;

import com.sup.flow.bean.WmFlowExccNode;

/**
 * @Description: 外贸进口业务层接口
 * @author Chu Zhaocheng
 * @date 2017年6月21日 下午2:16:29
 */
public interface WmFlowExccBusin {
    
    /**
     * @Description 保存外贸票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 下午2:06:37
     * @action saveWmFlowExccNode
     * @return void
     */
    void saveWmFlowExccNode(WmFlowExccNode wmFlowExccNode, String billId,String nodeId);
    
    /**
     * @Description 根据标号得到一条票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 下午2:07:07
     * @action getWmFlowExccNode
     * @return WmFlowExccNode
     */
    WmFlowExccNode getWmFlowExccNode(String id);
    
    /**
     * @Description 删除票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 下午2:07:21
     * @action removeWmFlowExccNode
     * @return void
     */
    void removeWmFlowExccNode(String id);
    
    /**
     * @Description 根据票据id查询票据信息,返回节点名称
     * @author Chu Zhaocheng
     * @date 2017年6月21日 下午2:12:52
     * @action getWmFlowExcc
     * @return String
     */
    String getWmFlowExcc(String billId);

}
