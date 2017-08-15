package com.pm.order.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.ExportShippingExCommission;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.ShippingExCommission;
import com.pm.order.busin.PmExportShippingExCommissionBusin;
@Service
public class PmExportShippingExCommissionBusinImpl implements PmExportShippingExCommissionBusin {

    @Autowired
    private BusinApi businApi;
    
    @Override
    public ExportShippingExCommission getShippingExCommission(String id) {
       
        return (ExportShippingExCommission) businApi.get(ExportShippingExCommission.class, id);
    }

    @Override
    public void saveShippingExCommission(ExportShippingExCommission shippingExCommission, String taskTypeId, String combaoguan) {
        if (shippingExCommission!=null) {
            OrderFrom orderFrom=new OrderFrom();
            Task tk=new Task();
            tk.setId(shippingExCommission.getTaskId());
            orderFrom.setTask(tk);
            FlowType flowType=new FlowType();
            flowType.setId(taskTypeId);
            orderFrom.setFlowType(flowType);
            orderFrom.setOrderCheck(1);
            SupCompanyInfo companyInfo=new SupCompanyInfo();
            companyInfo.setId(combaoguan);
            businApi.save(orderFrom);
            shippingExCommission.setOrderFromId(orderFrom.getId());
            businApi.save(shippingExCommission);
        }

    }

    @Override
    public void removeShippingExCommission(String id) {
        ShippingExCommission commission=(ShippingExCommission) businApi.get(ShippingExCommission.class, id);
           if (commission!=null) {
               businApi.remove(commission);
           }
    }
}
