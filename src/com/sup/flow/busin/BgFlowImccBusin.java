package com.sup.flow.busin;
/**
 * 报关流程单据
 * @author Administrator
 *
 */


import java.util.List;

import com.pm.company.bean.SupCompanyInfo;
import com.sup.flow.bean.BgFlowImcc;
import com.sup.flow.bean.BgFlowImccNode;
import com.sup.flow.bean.FlowNode;

public interface BgFlowImccBusin {
    
    /**
     * 根据票据id查看票据信息,返回节点名称
     * @param id
     * @return
     */
    String getBgFlowImcc(String id);
    /**
     * 根据票据id查询报关进口票据信息
     * @param id
     * @return
     */
    BgFlowImcc getBgFlowImccInfo(String id);
    
    
    
    /**
     * 根据标号得到一条票据信息
     * @param id
     * @return
     */
    BgFlowImccNode getFlowImccNode(String id);
    /**
     * 根据流程id显示流程节点信息
     * @param id
     * @return
     */
    List<FlowNode> getFlowNode(String id);
    
    /**
     * 保存票据信息(接单)
     * @param flowImccNode
     * @param billId
     */
    void saveFlowImccNode(BgFlowImccNode flowImccNode, String billId);
    /**
     * 删除票据信息
     * @param id
     */
    void removeFlowImccNode(String id);
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
     * 获取报关进口节点列表
     * @return
     */
    List<Object[]> getBgFlowImccNodeList(String id);
  
   
}
