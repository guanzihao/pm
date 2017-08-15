package com.sup.company.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.sup.company.bean.SupplierConsignationQuote;
import com.sup.company.busin.SupplierConsignationQuoteService;
import com.sup.flow.bean.FlowNode;
import com.sup.flow.bean.FlowType;

@SuppressWarnings("unchecked")
@Service
public class SupplierConsignationQuoteServiceImpl implements SupplierConsignationQuoteService {

    @Autowired
    private BusinApi businApi;

    @Override
    public SupplierConsignationQuote getSupplierConsignationQuote(String id) {

        return (SupplierConsignationQuote) businApi.get(SupplierConsignationQuote.class, id);
    }

    @Override
    public void saveSupplierConsignationQuote(SupplierConsignationQuote consignationQuote) {
        if (consignationQuote != null) {
            businApi.save(consignationQuote);
        }
    }

    @Override
    public void removeSupplierConsignationQuote(String id) {
        SupplierConsignationQuote quote = (SupplierConsignationQuote) businApi.get(SupplierConsignationQuote.class, id);
        if (quote != null) {
            businApi.remove(quote);
        }
    }

    @Override
    public List<FlowType> getFlowNote(String flowId) {
        return businApi.getQueryList("from FlowType a where a.flowType=? ", new Object[] { flowId });
    }

    @Override
    public FlowType getFlowType(String id) {
        return (FlowType) businApi.get(FlowType.class, id);
    }
}
