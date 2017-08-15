package com.sup.flow.busin;

import com.sup.flow.bean.CcFlowImccNode;

/**
 * @Description: 仓库入库流程单据
 * @author Chu Zhaocheng
 * @date 2017年6月13日 下午3:39:29
 */
public interface CcFlowImccBusin {
    
    /**
     * @Description 根据票据id查看票据信息,返回节点名称
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:41:55
     * @action getCcFlowImcc
     * @return String
     */
    String getCcFlowImcc(String id);
    
    /**
     * @Description 根据标号得到一条票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:42:20
     * @action getCcFlowImccNode
     * @return CcFlowImccNode
     */
    CcFlowImccNode getCcFlowImccNode(String id);
    
    /**
     * @Description 保存票据信息(接单)
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:42:36
     * @action saveCcFlowImccNode
     * @return void
     */
    void saveCcFlowImccNode(CcFlowImccNode CcFlowImccNode, String billId);
    
    /**
     * @Description 删除票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:42:50
     * @action removeCcFlowImccNode
     * @return void
     */
    void removeCcFlowImccNode(String id);
}
