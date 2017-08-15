package com.sup.order.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.core.model.TaskState;
import com.pm.framework.util.ClassReflection;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.CustomsDeAgreement;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.ShippingExCommission;
import com.sup.order.bean.TropShippingExCommission;
import com.sup.order.busin.TropShippingExCommissionBusin;
@Service
public class TropShippingExCommissionBusinImpl implements TropShippingExCommissionBusin {

    @Autowired
    private BusinApi businApi;
    
    @Override
    public TropShippingExCommission getShippingExCommission(String id) {
       
        return (TropShippingExCommission) businApi.get(TropShippingExCommission.class, id);
    }


    @Override
    public void removeShippingExCommission(String id) {
        ShippingExCommission commission=(ShippingExCommission) businApi.get(ShippingExCommission.class, id);
           if (commission!=null) {
               businApi.remove(commission);
           }
    }

    @Override
    public void saveShippingExCommission(TropShippingExCommission shippingExCommission, String taskTypeId, String comwaimao,String orderFromId) {
        if (shippingExCommission!=null) {
            if (orderFromId!=null&&!"".equals(orderFromId)) {
                TropShippingExCommission cd=(TropShippingExCommission) businApi.get(TropShippingExCommission.class, orderFromId);
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
                }
                orderFrom.setProjectAccomplishDate(shippingExCommission.getEndDate());
                orderFrom.setTask(tk);
                FlowType flowType=new FlowType();
                flowType.setId(taskTypeId);
                orderFrom.setFlowType(flowType);
                orderFrom.setOrderState(TaskState.CG);
                if (comwaimao!=null) {
                    SupCompanyInfo companyInfo=new SupCompanyInfo();
                    companyInfo.setId(comwaimao);
                    orderFrom.setSuppers(companyInfo);
                }
                orderFrom.setNode1State(3);
                orderFrom.setNode2State(3);
                orderFrom.setNode3State(3);
                businApi.save(orderFrom);
                shippingExCommission.setOrderFromId(orderFrom.getId());
                businApi.save(shippingExCommission);
            }
        }
        
    }


    @Override
    public TropShippingExCommission getObject(String id) {
       
        return (TropShippingExCommission) businApi.getQueryObject("FROM TropShippingExCommission  a where a.orderFromId=?", new Object[]{id});
    }


    @Override
    public void saveObject(TropShippingExCommission shippingExCommission) {
        if (shippingExCommission!=null) {
            businApi.save(shippingExCommission);
        }
        
    }
}
