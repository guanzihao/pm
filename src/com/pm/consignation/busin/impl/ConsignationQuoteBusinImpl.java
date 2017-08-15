package com.pm.consignation.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.consignation.busin.ConsignationQuoteBusin;
import com.pm.core.busin.BusinApi;
import com.sup.company.bean.SupplierConsignationQuote;
@Service
public class ConsignationQuoteBusinImpl implements ConsignationQuoteBusin {
    
    @Autowired
    private BusinApi businApi;
    
    @Override
    public void saveSupplierConsignationQuote(String id) {
        SupplierConsignationQuote supplierConsignationQuote=(SupplierConsignationQuote) businApi.get(SupplierConsignationQuote.class,id);
        supplierConsignationQuote.setCheckState(1);
        if (supplierConsignationQuote!=null) {
            businApi.save(supplierConsignationQuote);
        }
    }

}
