package com.pm.order.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.ExContractDetail;
import com.sup.order.bean.ExportContract;
import com.sup.order.bean.OrderFrom;
import com.pm.order.busin.PmExContractDetailBusin;

import net.sf.json.JSONArray;
@SuppressWarnings("unchecked")
@Service
public class PmExContractDetailBusinImpl implements PmExContractDetailBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public  List<ExContractDetail> getExContractDetail(String id) {
        List<ExContractDetail> contractDetails=businApi.getQueryList("FROM ExContractDetail a where a.exContractId=?", new Object[]{id});
        
        return contractDetails;
    }

    @Override
    public ExportContract getExportContract(String id) {
        return (ExportContract) businApi.get(ExportContract.class, id);
    }

   


    @Override
    public void saveExportContract(ExportContract exportContract, String taskTypeId, String comwaimao, String commodityName, String models, String quantity, String unitPrice, String totalAmount) {
       if (exportContract!=null) {
           JSONArray commodityNameArray = JSONArray.fromObject(commodityName);
           JSONArray modelArray = JSONArray.fromObject(models);
           JSONArray quantityArray = JSONArray.fromObject(quantity);
           JSONArray unitPriceArray = JSONArray.fromObject(unitPrice);
           JSONArray totalAmountArray = JSONArray.fromObject(totalAmount);
           OrderFrom orderFrom=new OrderFrom();
           Task tk=new Task();
           tk.setId(exportContract.getTaskId());
           orderFrom.setTask(tk);
           FlowType flowType=new FlowType();
           flowType.setId(taskTypeId);
           orderFrom.setFlowType(flowType);
           orderFrom.setOrderCheck(1);
           SupCompanyInfo companyInfo=new SupCompanyInfo();
           companyInfo.setId(comwaimao);
           businApi.save(orderFrom);
           exportContract.setOrderFromId(orderFrom.getId());
           businApi.save(exportContract);
     
           for (int i = 0; i < commodityNameArray.size(); i++) {
               ExContractDetail exContractDetail=new ExContractDetail();
               exContractDetail.setExContractId(exportContract.getId());
               exContractDetail.setCommodityName((String)commodityNameArray.get(i));
               exContractDetail.setModel((String)modelArray.get(i));
               exContractDetail.setQuantity(Integer.parseInt((String)quantityArray.get(i)));
               exContractDetail.setUnitPrice(Double.parseDouble((String)unitPriceArray.get(i)));
               exContractDetail.setTotalAmount(Double.parseDouble((String)totalAmountArray.get(i)));
               businApi.save(exContractDetail);
           }
    }
    }

}
