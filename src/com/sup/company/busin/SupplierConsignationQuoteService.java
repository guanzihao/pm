package com.sup.company.busin;
/**
 * 服务商委托报价接口
 * @author Administrator
 *
 */

import java.util.List;

import com.sup.company.bean.SupplierConsignationQuote;
import com.sup.flow.bean.FlowNode;
import com.sup.flow.bean.FlowType;

public interface SupplierConsignationQuoteService {
    
    /**
     * 根据Id得到一个数据
     * @param id
     * @return
     */
    SupplierConsignationQuote getSupplierConsignationQuote(String id);
    
    /**
     * 保存一条数据
     * @param consignationQuote
     */
    void saveSupplierConsignationQuote(SupplierConsignationQuote consignationQuote);
    
    /**
     * 删除一条数据
     * @param id
     */
    void removeSupplierConsignationQuote(String id);
    
    List<FlowType> getFlowNote(String flowId);
    
    FlowType getFlowType(String id);
    
    
    
}
