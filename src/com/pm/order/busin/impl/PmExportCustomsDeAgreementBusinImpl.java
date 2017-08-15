package com.pm.order.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.ExportCustomsDeAgreement;
import com.sup.order.bean.OrderFrom;
import com.pm.order.busin.PmExportCustomsDeAgreementBusin;
@Service
public class PmExportCustomsDeAgreementBusinImpl implements PmExportCustomsDeAgreementBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public ExportCustomsDeAgreement getCustomsDeAgreement(String id) {
        return (ExportCustomsDeAgreement) businApi.get(ExportCustomsDeAgreement.class, id);
    }

    @Override
    public void saveCustomsDeAgreement(ExportCustomsDeAgreement exportCustomsDeAgreement, String taskTypeId, String combaoguan) {
        if (exportCustomsDeAgreement!=null) {
            OrderFrom orderFrom=new OrderFrom();
            Task tk=new Task();
            tk.setId(exportCustomsDeAgreement.getTaskId());
            orderFrom.setTask(tk);
            FlowType flowType=new FlowType();
            flowType.setId(taskTypeId);
            orderFrom.setFlowType(flowType);
            orderFrom.setOrderCheck(1);
            SupCompanyInfo companyInfo=new SupCompanyInfo();
            companyInfo.setId(combaoguan);
            businApi.save(orderFrom);
            exportCustomsDeAgreement.setOrderFromId(orderFrom.getId());
            businApi.save(exportCustomsDeAgreement);
        }
    }

    @Override
    public void deleteCustomsDeAgreement(String id) {
        ExportCustomsDeAgreement exportCustomsDeAgreement = (ExportCustomsDeAgreement) businApi.get(ExportCustomsDeAgreement.class, id);
        if(exportCustomsDeAgreement != null){
            businApi.remove(exportCustomsDeAgreement);
        }
    }

}
