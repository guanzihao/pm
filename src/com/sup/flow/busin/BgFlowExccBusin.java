package com.sup.flow.busin;

/**
 * 报关流程单据
 * @author Administrator
 *
 */

import java.util.List;

import com.pm.company.bean.SupCompanyInfo;
import com.sup.flow.bean.BgFlowExcc;
import com.sup.flow.bean.BgFlowExccNode;
import com.sup.flow.bean.FlowNode;

public interface BgFlowExccBusin {
    
    /**
     * 根据票据id查看票据信息,返回节点名称
     * @param id
     * @return
     */
    String getBgFlowExcc(String id);
    
    /**
     * 根据标号得到一条票据信息
     * @param id
     * @return
     */
    BgFlowExccNode getFlowExccNode(String id);
    
    /**
     * 保存票据信息(接单)
     * @param flowExccNode
     * @param billId
     */
    void saveFlowExccNode(BgFlowExccNode flowExccNode, String billId);
    /**
     * 删除票据信息
     * @param id
     */
    void removeFlowExccNode(String id);
    
    /**
     * 获取供应商信息
     * @return
     */
    List<SupCompanyInfo> getSupplierList();
    
    /**
     * 根据id查询供应商信息
     * @param id
     * @return
     */
    SupCompanyInfo getSupplierById(String id);
    

    /**
     * 根据票据id查询报关出口票据信息
     * @param id
     * @return
     */
    BgFlowExcc getBgFlowExccInfo(String id);
    

    
    /**
     * 子订单ID查询节点信息
     * @param id
     * @return
     */
    BgFlowExcc getOrderCc(String orderid);
    
    /**
    * 子订单ID查询节点信息
    * @param id
    * @return
    */
    FlowNode getFlowNode(String id);
    
    
    void updateorderstate(String danjuType,String orderId);
    
}
