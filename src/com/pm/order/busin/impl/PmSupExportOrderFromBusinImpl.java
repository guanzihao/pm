package com.pm.order.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.OrderFrom;
import com.pm.order.busin.PmSupExportOrderFromBusin;
@Service
public class PmSupExportOrderFromBusinImpl implements PmSupExportOrderFromBusin {

    @Autowired
    private BusinApi businApi;
    
    @Override
    public void saveOrderFrom(String taskId) {
       Task task=(Task) businApi.get(Task.class, taskId);
       
       if (task!=null) {
          String oldAllId = task.getTackType();
          if (oldAllId!=null) {
              String[] newAllId = oldAllId.split(",");
              for (String string : newAllId) {
                OrderFrom orderFrom=new OrderFrom();
                orderFrom.setTask(task);
                FlowType flowType=new FlowType();
                flowType.setId(string);
                orderFrom.setFlowType(flowType);
                businApi.save(orderFrom);
              }
        }
       }
    }

    @Override
    public String saveOrderFrom(String taskId,String taskTypeId,String fusId) {
        
        OrderFrom orderFrom=new OrderFrom();
        Task tk=new Task();
        tk.setId(taskId);
        orderFrom.setTask(tk);
        FlowType flowType=new FlowType();
        flowType.setId(taskTypeId);
        orderFrom.setFlowType(flowType);
        orderFrom.setOrderCheck(1);
        SupCompanyInfo companyInfo=new SupCompanyInfo();
        companyInfo.setId(fusId);
        businApi.save(orderFrom);
        
        return orderFrom.getId();
    }

    @Override
    public void saveOrderFroms(ExportPurchaseOrderProduction exportPurchaseOrderProduction, String taskTypeId, String comwaimao) {
        if (exportPurchaseOrderProduction!=null) {
            OrderFrom orderFrom=new OrderFrom();
            Task tk=new Task();
            tk.setId(exportPurchaseOrderProduction.getTaskId());
            orderFrom.setTask(tk);
            FlowType flowType=new FlowType();
            flowType.setId(taskTypeId);
            orderFrom.setFlowType(flowType);
            orderFrom.setOrderCheck(1);
            SupCompanyInfo companyInfo=new SupCompanyInfo();
            companyInfo.setId(comwaimao);
            businApi.save(orderFrom);
            exportPurchaseOrderProduction.setOrderFromId(orderFrom.getId());
            businApi.save(exportPurchaseOrderProduction);
        }
    }
    
    
    @Override
    public ExportPurchaseOrderProduction getinfo(String id) {
        return (ExportPurchaseOrderProduction)businApi.get(ExportPurchaseOrderProduction.class, id);
    }
}
