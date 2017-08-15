package com.sup.company.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.sup.company.bean.SupplierConsignationQuote;
import com.sup.company.busin.ServiceQuotationBusin;
/**
 * 服务报价 ServiceQuotationBusinImpl
 * @author litao 
 *
 */
@Service
public class ServiceQuotationBusinImpl implements ServiceQuotationBusin {

    @Autowired
    private BusinApi businApi;
    
    /**
     * 根据供应商公司Id查询服务报价信息
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> getSupplierConsignationQuote(){
        String sql="select a.obj_Id,a.origin_place,a.purpose_place,a.price,a.tonnage,sup.comName from PM_Supplier_Consignation_Quote a"
                  +" left join PM_COMPANY_SupCompanyInfo sup on a.supplier_id=sup.obj_Id";
                  
        return (List<Object[]>)businApi.getSQLQuery(sql, new Object[]{null});
    }
}
