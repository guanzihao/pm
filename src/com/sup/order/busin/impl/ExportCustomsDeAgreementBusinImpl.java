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
import com.sup.order.bean.ExportCustomsDeAgreement;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.ShippingExCommission;
import com.sup.order.busin.ExportCustomsDeAgreementBusin;
@Service
public class ExportCustomsDeAgreementBusinImpl implements ExportCustomsDeAgreementBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public ExportCustomsDeAgreement getCustomsDeAgreement(String id) {
        return (ExportCustomsDeAgreement) businApi.get(ExportCustomsDeAgreement.class, id);
    }

    @Override
    public void saveCustomsDeAgreement(ExportCustomsDeAgreement exportCustomsDeAgreement, String taskTypeId, String combaoguan,String orderFromId) {
        if (orderFromId!=null&&!"".equals(orderFromId)) {
            exportCustomsDeAgreement.setId(orderFromId);
        }
        if (exportCustomsDeAgreement!=null) {
            if (orderFromId!=null&&!"".equals(orderFromId)) {
                ExportCustomsDeAgreement cd=(ExportCustomsDeAgreement) businApi.get(ExportCustomsDeAgreement.class, orderFromId);
                if (cd!=null) {
                    try {
                        ClassReflection.reflectionAttr(exportCustomsDeAgreement, cd);
                        businApi.save(cd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                OrderFrom orderFrom=new OrderFrom();
                Task tk= (Task) businApi.get(Task.class, exportCustomsDeAgreement.getTaskId());
                if (tk!=null) {
                    tk.setId(exportCustomsDeAgreement.getTaskId());
                    orderFrom.setOrderCode(tk.getTaskId());
                }
                orderFrom.setProjectAccomplishDate(exportCustomsDeAgreement.getEndDate());
                tk.setId(exportCustomsDeAgreement.getTaskId());
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
                businApi.save(orderFrom);
                exportCustomsDeAgreement.setOrderFromId(orderFrom.getId());
                businApi.save(exportCustomsDeAgreement);
            }
            }
    }

    @Override
    public void deleteCustomsDeAgreement(String id) {
        ExportCustomsDeAgreement exportCustomsDeAgreement = (ExportCustomsDeAgreement) businApi.get(ExportCustomsDeAgreement.class, id);
        if(exportCustomsDeAgreement != null){
            businApi.remove(exportCustomsDeAgreement);
        }
    }

    @Override
    public ExportCustomsDeAgreement getObject(String id) {
       return (ExportCustomsDeAgreement) businApi.getQueryObject("FROM ExportCustomsDeAgreement a where a.orderFromId=?", new Object[]{id});
    }

    @Override
    public void saveObject(ExportCustomsDeAgreement customsDeAgreement) {
        if (customsDeAgreement!=null) {
            businApi.save(customsDeAgreement);
        }
        
    }

}
