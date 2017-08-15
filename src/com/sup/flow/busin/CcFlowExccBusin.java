package com.sup.flow.busin;

import com.sup.flow.bean.CcFlowExccNode;

/**
 * @Description: 仓库出库流程单据
 * @author Chu Zhaocheng
 * @date 2017年6月13日 下午3:39:29
 */
public interface CcFlowExccBusin {
    
    /**
     * @Description 根据票据id查看票据信息,返回节点名称
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:41:55
     * @action getCcFlowExcc
     * @return String
     */
    String getCcFlowExcc(String id);
    
    /**
     * @Description 根据标号得到一条票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:42:20
     * @action getCcFlowExccNode
     * @return CcFlowExccNode
     */
    CcFlowExccNode getCcFlowExccNode(String id);
    
    /**
     * @Description 保存票据信息(接单)
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:42:36
     * @action saveCcFlowExccNode
     * @return void
     */
    void saveCcFlowExccNode(CcFlowExccNode CcFlowExccNode, String billId);
    
    /**
     * @Description 删除票据信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午11:42:50
     * @action removeCcFlowExccNode
     * @return void
     */
    void removeCcFlowExccNode(String id);
}
