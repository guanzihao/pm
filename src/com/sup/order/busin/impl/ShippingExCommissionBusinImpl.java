package com.sup.order.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.core.model.TaskState;
import com.pm.framework.util.ClassReflection;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.ImContractDetail;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.ShippingExCommission;
import com.sup.order.busin.ShippingExCommissionBusin;
@Service
public class ShippingExCommissionBusinImpl implements ShippingExCommissionBusin {

    @Autowired
    private BusinApi businApi;
    
    @Override
    public ShippingExCommission getShippingExCommission(String id) {
       
        return (ShippingExCommission) businApi.get(ShippingExCommission.class, id);
    }

    @Override
    public void saveShippingExCommission(ShippingExCommission shippingExCommission, String taskTypeId, String comwaimao,String orderFromId) {
        if (shippingExCommission!=null) {
            if (orderFromId!=null&&!"".equals(orderFromId)) {
                ShippingExCommission cd=(ShippingExCommission) businApi.get(ShippingExCommission.class, orderFromId);
                if (cd!=null) {
                    try {
                        ClassReflection.reflectionAttr(shippingExCommission, cd);
                        businApi.save(cd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                OrderFrom orderFrom=new OrderFrom();
                Task tk= (Task) businApi.get(Task.class, shippingExCommission.getTaskId());
                if (tk!=null) {
                    tk.setId(shippingExCommission.getTaskId());
                    orderFrom.setOrderCode(tk.getTaskId());
                    tk.setId(shippingExCommission.getTaskId());
                }
                orderFrom.setProjectAccomplishDate(shippingExCommission.getEndDate());
                orderFrom.setTask(tk);
                FlowType flowType=new FlowType();
                flowType.setId(taskTypeId);
                orderFrom.setFlowType(flowType);
                orderFrom.setOrderState(TaskState.CG);
                if (comwaimao!=null) {
                    SupCompanyInfo companyInfo = new SupCompanyInfo();
                    companyInfo.setId(comwaimao);
                    orderFrom.setSuppers(companyInfo);
                }
                orderFrom.setNode1State(3);
                orderFrom.setNode2State(3);
                orderFrom.setNode3State(3);
                orderFrom.setNode4State(3);
                orderFrom.setNode5State(3);
                businApi.save(orderFrom);
                shippingExCommission.setOrderFromId(orderFrom.getId());
                businApi.save(shippingExCommission);
            }
            }

    }

    @Override
    public void removeShippingExCommission(String id) {
        ShippingExCommission commission=(ShippingExCommission) businApi.get(ShippingExCommission.class, id);
           if (commission!=null) {
               businApi.remove(commission);
           }
    }

    @Override
    public ShippingExCommission getObject(String id) {
        return (ShippingExCommission)  businApi.getQueryObject("FROM ShippingExCommission a where a.orderFromId=?", new Object[]{id});
    }

    @Override
    public void saveObject(ShippingExCommission shippingExCommission) {
       if (shippingExCommission!=null) {
        businApi.save(shippingExCommission);
    }
        
    }
}
