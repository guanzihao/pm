package com.sup.order.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.core.model.TaskState;
import com.pm.framework.util.ClassReflection;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.ExContractDetail;
import com.sup.order.bean.ExportContract;
import com.sup.order.bean.ExportCustomsDeAgreement;
import com.sup.order.bean.ExportPurchaseOrderProductionDetail;
import com.sup.order.bean.ImContractDetail;
import com.sup.order.bean.OrderFrom;
import com.sup.order.busin.ExContractDetailBusin;

import net.sf.json.JSONArray;

@SuppressWarnings("unchecked")
@Service
public class ExContractDetailBusinImpl implements ExContractDetailBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public List<ExContractDetail> getExContractDetail(String id) {
        List<ExContractDetail> contractDetails = businApi.getQueryList("FROM ExContractDetail a where a.exContractId=?", new Object[] { id });

        return contractDetails;
    }

    @Override
    public ExportContract getExportContract(String id) {
        return (ExportContract) businApi.get(ExportContract.class, id);
    }

    @Override
    public void saveExportContract(ExportContract exportContract, String taskTypeId, String comwaimao,String commodityNameId, String commodityName, String models, String quantity, String unitPrice, String totalAmount,String orderFromId) {
        if (orderFromId!=null&&!"".equals(orderFromId)) {
            exportContract.setId(orderFromId);
        }
        if (exportContract != null) {
            if (orderFromId!=null&&!"".equals(orderFromId)) {
                ExportContract cd=(ExportContract) businApi.get(ExportContract.class, orderFromId);
                if (cd!=null) {
                    try {
                        ClassReflection.reflectionAttr(exportContract, cd);
                        businApi.save(cd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                OrderFrom orderFrom = new OrderFrom();
                Task tk= (Task) businApi.get(Task.class, exportContract.getTaskId());
                if (tk!=null) {
                    tk.setId(exportContract.getTaskId());
                    orderFrom.setOrderCode(tk.getTaskId());
                }
                orderFrom.setProjectAccomplishDate(exportContract.getEndDate());
                tk.setId(exportContract.getTaskId());
                orderFrom.setTask(tk);
                FlowType flowType = new FlowType();
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
                orderFrom.setNode6State(3);
                orderFrom.setNode7State(3);
                
                businApi.save(orderFrom);
                exportContract.setOrderFromId(orderFrom.getId());
                businApi.save(exportContract);
                if (commodityName != null && models != null && quantity != null && unitPrice != null && totalAmount != null) {
                    JSONArray commodityNameArray = JSONArray.fromObject(commodityName);
                    JSONArray commodityNameIdArray = JSONArray.fromObject(commodityNameId);
                    JSONArray modelArray = JSONArray.fromObject(models);
                    JSONArray quantityArray = JSONArray.fromObject(quantity);
                    JSONArray unitPriceArray = JSONArray.fromObject(unitPrice);
                    JSONArray totalAmountArray = JSONArray.fromObject(totalAmount);
                    for (int i = 0; i < quantityArray.size(); i++) {
                        ExContractDetail exContractDetail = new ExContractDetail();
                        try {
                            exContractDetail.setExContractId(exportContract.getId());
                            exContractDetail.setCommodityId((String) commodityNameIdArray.get(i));
                            exContractDetail.setCommodityName((String) commodityNameArray.get(i));
                            exContractDetail.setModel((String) modelArray.get(i));
                            exContractDetail.setQuantity(Integer.parseInt((String) quantityArray.get(i)));
                            exContractDetail.setUnitPrice(Double.parseDouble((String) unitPriceArray.get(i)));
                            exContractDetail.setTotalAmount(Double.parseDouble((String) totalAmountArray.get(i)));
                            businApi.save(exContractDetail);
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            
            }
        }
    }

    @Override
    public ExportContract getObject(String exId) {
        return (ExportContract) businApi.getQueryObject("FROM ExportContract a where a.orderFromId=?", new Object[] { exId });
    }

    @Override
    public void saveObject(ExportContract exportContract) {
        if (exportContract != null) {
            businApi.save(exportContract);
        }
    }

    @Override
    public void saveObjectDetail(String fid, String[] ids, String[] commodityName, String[] models, String[] quantity, String[] unitPrice, String[] totalAmount,String commodityId[]) {
        
        List<ExContractDetail> productionDetails=businApi.getQueryList("FROM ExContractDetail where exContractId=?", new Object[]{fid});
        businApi.remove(productionDetails);
        if (ids!=null&& commodityName!=null&& models!=null&& quantity!=null&&
                unitPrice!=null&& totalAmount!=null&&commodityId!=null) {
            for (int i = 0; i < quantity.length; i++) {
                  ExContractDetail contractDetail = null;
                  try {
                      contractDetail = new ExContractDetail();
                         contractDetail.setExContractId(fid);
                         contractDetail.setCommodityId(commodityId[i]);
                         contractDetail.setCommodityName(commodityName[i]);
                         contractDetail.setModel(models[i]);
                         contractDetail.setQuantity(Integer.parseInt(quantity[i]));
                         contractDetail.setUnitPrice(Double.parseDouble(unitPrice[i]));
                         contractDetail.setTotalAmount(Double.parseDouble(totalAmount[i]));
                         businApi.save(contractDetail);
                  } catch (NumberFormatException e) {
                  }
           }
       }
        
    }

}
