package com.pm.order.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.CustomsDeAgreement;
import com.sup.order.bean.OrderFrom;
import com.pm.order.busin.PmCustomsDeAgreementBusin;
@Service
public class PmCustomsDeAgreementBusinImpl implements PmCustomsDeAgreementBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public CustomsDeAgreement getCustomsDeAgreement(String id) {
        return (CustomsDeAgreement) businApi.get(CustomsDeAgreement.class, id);
    }

    @Override
    public void deleteCustomsDeAgreement(String id) {
        CustomsDeAgreement customsDeAgreement = (CustomsDeAgreement) businApi.get(CustomsDeAgreement.class, id);
        if(customsDeAgreement != null){
            businApi.remove(customsDeAgreement);
        }
    }

    @Override
    public void saveExportContract(CustomsDeAgreement customsDeAgreement, String taskTypeId, String combaoguan) {
      if (customsDeAgreement!=null) {
          OrderFrom orderFrom=new OrderFrom();
          Task tk=new Task();
          tk.setId(customsDeAgreement.getTaskId());
          orderFrom.setTask(tk);
          FlowType flowType=new FlowType();
          flowType.setId(taskTypeId);
          orderFrom.setFlowType(flowType);
          orderFrom.setOrderCheck(1);
          SupCompanyInfo companyInfo=new SupCompanyInfo();
          companyInfo.setId(combaoguan);
          businApi.save(orderFrom);
          customsDeAgreement.setOrderFromId(orderFrom.getId());
          businApi.save(customsDeAgreement);
      }
        
    }

}
