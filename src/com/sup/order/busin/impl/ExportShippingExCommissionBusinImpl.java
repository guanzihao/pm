package com.sup.order.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.core.model.TaskState;
import com.pm.framework.util.ClassReflection;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.ExportShippingExCommission;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.ShippingExCommission;
import com.sup.order.busin.ExportShippingExCommissionBusin;
@Service
public class ExportShippingExCommissionBusinImpl implements ExportShippingExCommissionBusin {

    @Autowired
    private BusinApi businApi;
    
    @Override
    public ExportShippingExCommission getShippingExCommission(String id) {
       
        return (ExportShippingExCommission) businApi.get(ExportShippingExCommission.class, id);
    }

    @Override
    public void saveShippingExCommission(ExportShippingExCommission shippingExCommission, String taskTypeId, String combaoguan,String orderFromId) {
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
                }
                orderFrom.setProjectAccomplishDate(shippingExCommission.getEndDate());
                tk.setId(shippingExCommission.getTaskId());
                orderFrom.setTask(tk);
                FlowType flowType=new FlowType();
                flowType.setId(taskTypeId);
                orderFrom.setFlowType(flowType);
                orderFrom.setOrderState(TaskState.CG);
                if (combaoguan!=null) {
                    SupCompanyInfo companyInfo = new SupCompanyInfo();
                    companyInfo.setId(combaoguan);
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
    public ExportShippingExCommission getObject(String id) {
        return (ExportShippingExCommission) businApi.getQueryObject("FROM ExportShippingExCommission a where a.orderFromId=?", new Object[]{id});
    }

    @Override
    public void saveObject(ExportShippingExCommission exportShippingExCommission) {
       if (exportShippingExCommission!=null) {
           businApi.save(exportShippingExCommission);
       }
    }
}
