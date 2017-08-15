package com.sup.order.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.core.model.TaskState;
import com.pm.core.util.StringUtil;
import com.pm.framework.util.ClassReflection;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.CustomsDeAgreement;
import com.sup.order.bean.OrderFrom;
import com.sup.order.busin.CustomsDeAgreementBusin;
@Service
public class CustomsDeAgreementBusinImpl implements CustomsDeAgreementBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public CustomsDeAgreement getCustomsDeAgreement(String id) {
        return (CustomsDeAgreement)  businApi.get(CustomsDeAgreement.class, id);
    }

    @Override
    public void deleteCustomsDeAgreement(String id) {
        CustomsDeAgreement customsDeAgreement = (CustomsDeAgreement) businApi.get(CustomsDeAgreement.class, id);
        if(customsDeAgreement != null){
            businApi.remove(customsDeAgreement);
        }
    }

    @Override
    public void saveExportContract(CustomsDeAgreement customsDeAgreement, String taskTypeId, String combaoguan,String orderFromId) {
        if (customsDeAgreement!=null) {
                if (orderFromId!=null&&!"".equals(orderFromId)) {
                    CustomsDeAgreement cd=(CustomsDeAgreement) businApi.get(CustomsDeAgreement.class, orderFromId);
                    if (cd!=null) {
                        try {
                            ClassReflection.reflectionAttr(customsDeAgreement, cd);
                            businApi.save(cd);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    OrderFrom orderFrom=new OrderFrom();
                    Task tk= (Task) businApi.get(Task.class, customsDeAgreement.getTaskId());
                    if (tk!=null) {
                        orderFrom.setOrderCode(tk.getTaskId());
                    }
                    orderFrom.setProjectAccomplishDate(customsDeAgreement.getEndDate());
                    tk.setId(customsDeAgreement.getTaskId());
                    orderFrom.setTask(tk);
                    FlowType flowType=new FlowType();
                    flowType.setId(taskTypeId);
                    orderFrom.setFlowType(flowType);
                    orderFrom.setOrderState(TaskState.CG);
                   if (combaoguan!=null) {
                       SupCompanyInfo companyInfo=new SupCompanyInfo();
                       companyInfo.setId(combaoguan);
                       orderFrom.setSuppers(companyInfo);
                   }
                    orderFrom.setNode1State(3);
                    orderFrom.setNode2State(3);
                    orderFrom.setNode3State(3);
                    businApi.save(orderFrom);
                    customsDeAgreement.setOrderFromId(orderFrom.getId());
                    businApi.save(customsDeAgreement);
                }
         }
    }

    @Override
    public CustomsDeAgreement getCObject(String id) {
        return (CustomsDeAgreement)  businApi.getQueryObject("FROM CustomsDeAgreement a where a.orderFromId=?", new Object[]{id});
    }

    @Override
    public void saveObject(CustomsDeAgreement customsDeAgreement) {
       if (customsDeAgreement!=null) {
           businApi.save(customsDeAgreement);
       }
        
    }

}
