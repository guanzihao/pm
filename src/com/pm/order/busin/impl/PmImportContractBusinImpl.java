package com.pm.order.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.ImContractDetail;
import com.sup.order.bean.ImportContract;
import com.sup.order.bean.OrderFrom;
import com.pm.order.busin.PmImportContractBusin;

import net.sf.json.JSONArray;

/**
 * @Description: 进口合同业务实现
 * @author Chu Zhaocheng
 * @date 2017年6月26日 上午11:20:18
 */
@SuppressWarnings("unchecked")
@Service
public class PmImportContractBusinImpl implements PmImportContractBusin {
    
    @Autowired
    private BusinApi businApi;

    @Override
    public ImportContract getImportContract(String id) {
        return (ImportContract) businApi.get(ImportContract.class, id);
    }

    @Override
    public List<ImContractDetail> getImContractDetail(String id) {
       /* List<ImContractDetail> contractDetails=businApi.getQueryList("FROM ImContractDetail a where a.imContractId=?", new Object{id});
        
        return contractDetails;*/
        return null;
    }

    @Override
    public void saveImContract(ImportContract importContract, String taskTypeId,
            String comwaimao,String commodityName, String model,
            String quantity, String unitPrice, String totalAmount) {
        JSONArray commodityNameArray = JSONArray.fromObject(commodityName);
        JSONArray modelArray = JSONArray.fromObject(model);
        JSONArray quantityArray = JSONArray.fromObject(quantity);
        JSONArray unitPriceArray = JSONArray.fromObject(unitPrice);
        JSONArray totalAmountArray = JSONArray.fromObject(totalAmount);
       if (importContract!=null) {
           OrderFrom orderFrom=new OrderFrom();
           Task tk=new Task();
           tk.setId(importContract.getTaskId());
           orderFrom.setTask(tk);
           FlowType flowType=new FlowType();
           flowType.setId(taskTypeId);
           orderFrom.setFlowType(flowType);
           orderFrom.setOrderCheck(1);
           SupCompanyInfo companyInfo=new SupCompanyInfo();
           companyInfo.setId(comwaimao);
           businApi.save(orderFrom);
           importContract.setOrderFromId(orderFrom.getId());
           businApi.save(importContract);
           for (int i = 0; i < commodityNameArray.size(); i++) {
               ImContractDetail contractDetail=new ImContractDetail();
               contractDetail.setImContractId(importContract.getId());
               contractDetail.setCommodityName((String)commodityNameArray.get(i));
               contractDetail.setModel((String)modelArray.get(i));
               contractDetail.setQuantity(Integer.parseInt((String)quantityArray.get(i)));
               contractDetail.setUnitPrice(Double.parseDouble((String)unitPriceArray.get(i)));
               contractDetail.setTotalAmount(Double.parseDouble((String)totalAmountArray.get(i)));
               businApi.save(contractDetail);
           }
       }
    }
}
