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
import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.ExportPurchaseOrderProductionDetail;
import com.sup.order.bean.ExportShippingExCommission;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.PurchaseOrderProduction;
import com.sup.order.bean.PurchaseOrderProductionDetail;
import com.sup.order.busin.SupExportOrderFromBusin;

import net.sf.json.JSONArray;

@Service
public class SupExportOrderFromBusinImpl implements SupExportOrderFromBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public void saveOrderFrom(String taskId) {
        Task task = (Task) businApi.get(Task.class, taskId);

        if (task != null) {
            String oldAllId = task.getTackType();
            if (oldAllId != null) {
                String[] newAllId = oldAllId.split(",");
                for (String string : newAllId) {
                    OrderFrom orderFrom = new OrderFrom();
                    orderFrom.setTask(task);
                    FlowType flowType = new FlowType();
                    flowType.setId(string);
                    orderFrom.setFlowType(flowType);
                    businApi.save(orderFrom);
                }
            }
        }
    }

    @Override
    public String saveOrderFrom(String taskId, String taskTypeId, String fusId) {

        OrderFrom orderFrom = new OrderFrom();
        Task tk = new Task();
        tk.setId(taskId);
        orderFrom.setTask(tk);
        FlowType flowType = new FlowType();
        flowType.setId(taskTypeId);
        orderFrom.setFlowType(flowType);
        orderFrom.setOrderState(TaskState.CG);
        SupCompanyInfo companyInfo = new SupCompanyInfo();
        companyInfo.setId(fusId);
        businApi.save(orderFrom);

        return orderFrom.getId();
    }

    @Override
    public void saveOrderFroms(ExportPurchaseOrderProduction exportPurchaseOrderProduction, String taskTypeId, String comwaimao, String detailNos, String descriptions, String units, String priceVilidityPeriods, String perPrices, String remarks,String orderFromId) {
        
        if (exportPurchaseOrderProduction != null) {
            if (orderFromId!=null&&!"".equals(orderFromId)) {
                ExportPurchaseOrderProduction cd=(ExportPurchaseOrderProduction) businApi.get(ExportPurchaseOrderProduction.class, orderFromId);
                if (cd!=null) {
                    try {
                        ClassReflection.reflectionAttr(exportPurchaseOrderProduction, cd);
                        businApi.save(cd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                OrderFrom orderFrom = new OrderFrom();
                Task tk= (Task) businApi.get(Task.class, exportPurchaseOrderProduction.getTaskId());
                if (tk!=null) {
                    tk.setId(exportPurchaseOrderProduction.getTaskId());
                    orderFrom.setOrderCode(tk.getTaskId());
                }
                orderFrom.setProjectAccomplishDate(exportPurchaseOrderProduction.getEndDate());
                tk.setId(exportPurchaseOrderProduction.getTaskId());
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
                businApi.save(orderFrom);
                exportPurchaseOrderProduction.setOrderFromId(orderFrom.getId());
                businApi.save(exportPurchaseOrderProduction);
                if (descriptions != null && units != null && priceVilidityPeriods != null && perPrices != null && remarks != null) {
                    JSONArray descriptionArray = JSONArray.fromObject(descriptions);
                    JSONArray unitArray = JSONArray.fromObject(units);
                    JSONArray priceVilidityPeriodArray = JSONArray.fromObject(priceVilidityPeriods);
                    JSONArray perPriceArray = JSONArray.fromObject(perPrices);
                    JSONArray remarkArray = JSONArray.fromObject(remarks);
                    for (int i = 0; i < descriptionArray.size(); i++) {
                        ExportPurchaseOrderProductionDetail contractDetail = new ExportPurchaseOrderProductionDetail();
                        try {
                            contractDetail.setDetailNo(exportPurchaseOrderProduction.getId());
                            contractDetail.setDescription((String) descriptionArray.get(i));
                            contractDetail.setUnit((String) unitArray.get(i));
                            contractDetail.setPriceVilidityPeriod((String) priceVilidityPeriodArray.get(i));
                            contractDetail.setPerPrice(Double.parseDouble((String) perPriceArray.get(i)));
                            contractDetail.setRemark((String) remarkArray.get(i));
                            businApi.save(contractDetail);
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
        }
    }

    @Override
    public ExportPurchaseOrderProduction getinfo(String id) {
        return (ExportPurchaseOrderProduction) businApi.get(ExportPurchaseOrderProduction.class, id);
    }

    @Override
    public ExportPurchaseOrderProduction getObject(String id) {

        return (ExportPurchaseOrderProduction) businApi.getQueryObject("FROM ExportPurchaseOrderProduction a where a.orderFromId=?", new Object[] { id });
    }

    @Override
    public void saveObject(ExportPurchaseOrderProduction exportShippingExCommission) {
        if (exportShippingExCommission != null) {
            businApi.save(exportShippingExCommission);
        }

    }

    @Override
    public void saveObjectDetail(String fid, String[] ids, String[] description, String[] unit, String[] priceVilidityPeriod, String[] perPrice, String[] remark) {
        
        List<ExportPurchaseOrderProductionDetail> productionDetails=businApi.getQueryList("FROM ExportPurchaseOrderProductionDetail where detailNo=?", new Object[]{fid});
        businApi.remove(productionDetails);
        if (description!=null&&unit!=null&&priceVilidityPeriod!=null&&perPrice!=null&&remark!=null 
                ) {
            for (int i = 0; i < description.length; i++) {
                    ExportPurchaseOrderProductionDetail contractDetail = new ExportPurchaseOrderProductionDetail();
                    try {
                        contractDetail.setDetailNo(fid);
                        contractDetail.setDescription(description[i]);
                        contractDetail.setUnit(unit[i]);
                        contractDetail.setPriceVilidityPeriod(priceVilidityPeriod[i]);
                        contractDetail.setPerPrice(Double.parseDouble(perPrice[i]));
                        contractDetail.setRemark(remark[i]);
                        businApi.save(contractDetail);
                    } catch (NumberFormatException e) {
                    }
            }
        }
        
    }
}
