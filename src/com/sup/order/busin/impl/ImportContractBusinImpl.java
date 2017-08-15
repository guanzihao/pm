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
import com.sup.order.bean.ImContractDetail;
import com.sup.order.bean.ImportContract;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.ShippingExCommission;
import com.sup.order.busin.ImportContractBusin;

import net.sf.json.JSONArray;

/**
 * @Description: 进口合同业务实现
 * @author Chu Zhaocheng
 * @date 2017年6月26日 上午11:20:18
 */
@SuppressWarnings("unchecked")
@Service
public class ImportContractBusinImpl implements ImportContractBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public ImportContract getImportContract(String id) {
        return (ImportContract) businApi.get(ImportContract.class, id);
    }

    @Override
    public List<ImContractDetail> getImContractDetail(String id) {
        List<ImContractDetail> contractDetails = businApi.getQueryList("FROM ImContractDetail a where a.imContractId=?", new Object[] { id });

        return contractDetails;
    }

    @Override
    public void saveImContract(ImportContract importContract, String taskTypeId, String comwaimao, String commodityNameId, String commodityName, String model, String quantity, String unitPrice, String totalAmount,String orderFromId) {
        if (orderFromId!=null&&!"".equals(orderFromId)) {
            importContract.setId(orderFromId);
        }
        if (importContract != null) {
            if (orderFromId!=null&&!"".equals(orderFromId)) {
                ImportContract cd=(ImportContract) businApi.get(ImportContract.class, orderFromId);
                if (cd!=null) {
                    try {
                        ClassReflection.reflectionAttr(importContract, cd);
                        businApi.save(cd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                OrderFrom orderFrom = new OrderFrom();
                Task tk = (Task) businApi.get(Task.class, importContract.getTaskId());
                if (tk != null) {
                    tk.setId(importContract.getTaskId());
                    orderFrom.setOrderCode(tk.getTaskId());
                }
                orderFrom.setProjectAccomplishDate(importContract.getEndDate());
                tk.setId(importContract.getTaskId());
                orderFrom.setTask(tk);
                FlowType flowType = new FlowType();
                flowType.setId(taskTypeId);
                orderFrom.setFlowType(flowType);
                orderFrom.setOrderState(TaskState.CG);
                if (comwaimao != null) {
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
                importContract.setOrderFromId(orderFrom.getId());
                businApi.save(importContract);
                if (commodityName != null && model != null && quantity != null && unitPrice != null && totalAmount != null) {
                    JSONArray commodityNameArray = JSONArray.fromObject(commodityName);
                    JSONArray commodityNameIdArray = JSONArray.fromObject(commodityNameId);
                    JSONArray modelArray = JSONArray.fromObject(model);
                    JSONArray quantityArray = JSONArray.fromObject(quantity);
                    JSONArray unitPriceArray = JSONArray.fromObject(unitPrice);
                    JSONArray totalAmountArray = JSONArray.fromObject(totalAmount);
                    for (int i = 0; i < quantityArray.size(); i++) {
                        ImContractDetail contractDetail = null;
                        try {
                            contractDetail = new ImContractDetail();
                            contractDetail.setImContractId(importContract.getId());
                            contractDetail.setCommodityId((String) commodityNameIdArray.get(i));
                            contractDetail.setCommodityName((String) commodityNameArray.get(i));
                            contractDetail.setModel((String) modelArray.get(i));
                            contractDetail.setQuantity(Integer.parseInt((String) quantityArray.get(i)));
                            contractDetail.setUnitPrice(Double.parseDouble((String) unitPriceArray.get(i)));
                            contractDetail.setTotalAmount(Double.parseDouble((String) totalAmountArray.get(i)));
                            businApi.save(contractDetail);
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
        }
    }

    @Override
    public ImportContract getObject(String icId) {

        return (ImportContract) businApi.getQueryObject("FROM ImportContract a where a.orderFromId=?", new Object[] { icId });
    }

    @Override
    public void saveObject(ImportContract importContract) {
        if (importContract != null) {
            businApi.save(importContract);
        }

    }

    @Override
    public void saveObjectDetail(String fid, String[] ids, String[] commodityName, String[] models, String[] quantity, String[] unitPrice, String[] totalAmount, String commodityId[]) {
        
        List<ImContractDetail> imContract=businApi.getQueryList("FROM ImContractDetail where imContractId=?", new Object[]{fid});
        businApi.remove(imContract);
        if (ids != null && commodityName != null && models != null && quantity != null && unitPrice != null && totalAmount != null && commodityId != null) {
            for (int i = 0; i < quantity.length; i++) {
                    ImContractDetail contractDetail = null;
                    try {
                        contractDetail = new ImContractDetail();
                        contractDetail.setImContractId(fid);
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
