package com.sup.company.busin;

import java.util.List;

/**
 * 服务报价 ServiceQuotationBusin
 * @author litao
 *
 */
public interface ServiceQuotationBusin {
    /**
     * 根据供应商公司Id查询服务报价信息
     * @param supplierId
     * @return
     */
    List<Object[]> getSupplierConsignationQuote();
//    /**
//     * 查询报关报价管理信息
//     * @return
//     */
//    List<Object[]> getBgQuotationList();
//    /**
//     * 查询物流报价管理信息
//     * @return
//     */
//    List<Object[]> getWlQuotationList();
//    /**
//     * 查询仓储报价管理信息
//     * @return
//     */
//    List<Object[]> getCcQuotationList();
//    /**
//     * 查询外贸报价管理信息
//     * @return
//     */
//    List<Object[]> getWmQuotationList();
    
}
