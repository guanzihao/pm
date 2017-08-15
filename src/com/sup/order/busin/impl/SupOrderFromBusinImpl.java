package com.sup.order.busin.impl;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.core.model.TaskState;
import com.pm.core.util.StringUtil;
import com.pm.framework.util.ClassReflection;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.task.bean.Task;
import com.sup.flow.bean.BgFlowExcc;
import com.sup.flow.bean.BgFlowImcc;
import com.sup.flow.bean.CcFlowExcc;
import com.sup.flow.bean.CcFlowImcc;
import com.sup.flow.bean.FlowType;
import com.sup.flow.bean.WlFlowExcc;
import com.sup.flow.bean.WlFlowImcc;
import com.sup.flow.bean.WlFlowTransport;
import com.sup.flow.bean.WmFlowExcc;
import com.sup.flow.bean.WmFlowImcc;
import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.PurchaseOrderProduction;
import com.sup.order.bean.PurchaseOrderProductionDetail;
import com.sup.order.bean.TropShippingExCommission;
import com.sup.order.busin.SupOrderFromBusin;

import net.sf.json.JSONArray;
@SuppressWarnings("unchecked")
@Service
public class SupOrderFromBusinImpl implements SupOrderFromBusin {

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
    public void saveOrderFroms(PurchaseOrderProduction purchaseOrderProduction, String taskTypeId, String comwaimao, String detailNos, String descriptions, String units, String priceVilidityPeriods, String perPrices, String remarks, String orderFromId) {
        if (purchaseOrderProduction != null) {
            if (orderFromId!=null&&!"".equals(orderFromId)) {
                PurchaseOrderProduction cd=(PurchaseOrderProduction) businApi.get(PurchaseOrderProduction.class, orderFromId);
                if (cd!=null) {
                    try {
                        ClassReflection.reflectionAttr(purchaseOrderProduction, cd);
                        businApi.save(cd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                OrderFrom orderFrom = new OrderFrom();
                Task tk= (Task) businApi.get(Task.class, purchaseOrderProduction.getTaskId());
                if (tk!=null) {
                    tk.setId(purchaseOrderProduction.getTaskId());
                    orderFrom.setOrderCode(tk.getTaskId());
                }
                orderFrom.setProjectAccomplishDate(purchaseOrderProduction.getEndDate());
                tk.setId(purchaseOrderProduction.getTaskId());
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
                purchaseOrderProduction.setOrderFromId(orderFrom.getId());
                businApi.save(purchaseOrderProduction);
                if (detailNos != null && descriptions != null && units != null && priceVilidityPeriods != null && perPrices != null && remarks != null) {
                    JSONArray descriptionArray = JSONArray.fromObject(descriptions);
                    JSONArray unitArray = JSONArray.fromObject(units);
                    JSONArray priceVilidityPeriodArray = JSONArray.fromObject(priceVilidityPeriods);
                    JSONArray perPriceArray = JSONArray.fromObject(perPrices);
                    JSONArray remarkArray = JSONArray.fromObject(remarks);
                    for (int i = 0; i < descriptionArray.size(); i++) {
                        PurchaseOrderProductionDetail contractDetail = new PurchaseOrderProductionDetail();
                        try {
                            contractDetail.setDetailNo(purchaseOrderProduction.getId());
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
    public ExportPurchaseOrderProduction info(String id) {

        return (ExportPurchaseOrderProduction) businApi.get(ExportPurchaseOrderProduction.class, id);
    }

    @Override
    public PurchaseOrderProduction infos(String id) {

        return (PurchaseOrderProduction) businApi.get(PurchaseOrderProduction.class, id);
    }

    @Override
    public PurchaseOrderProduction getObject(String id) {

        return (PurchaseOrderProduction) businApi.getQueryObject("FROM PurchaseOrderProduction a where a.orderFromId=?", new Object[] { id });
    }

    @Override
    public void saveObject(PurchaseOrderProduction purchaseOrderProduction) {
        if (purchaseOrderProduction != null) {
            businApi.save(purchaseOrderProduction);
        }
    }
    @Override
    public void submitOrderFrom(String taskId) {
    List<OrderFrom> orderFroms =businApi.getQueryList("FROM OrderFrom a where a.task.id=?", new Object[]{taskId});
        if (orderFroms!=null) {
            for (OrderFrom orderFrom : orderFroms) {
                if (orderFrom!=null&&orderFrom.getSuppers()!=null) {
                    orderFrom.setOrderState(TaskState.YTJ);
                }else {
                    orderFrom.setOrderState(TaskState.WFP);
                }
                businApi.save(orderFrom);
            }
        }
    }

    @Override
    public void saveObjectDetail(String fid,String[] ids, String[] description, String[] unit,
            String[] priceVilidityPeriod, String[] perPrice, String[] remark) {
        
        List<PurchaseOrderProductionDetail> productionDetails=businApi.getQueryList("FROM PurchaseOrderProductionDetail where detailNo=?", new Object[]{fid});
        businApi.remove(productionDetails);
        if (description!=null&&unit!=null&&priceVilidityPeriod!=null&&perPrice!=null&&remark!=null 
                ) {
            for (int i = 0; i < description.length; i++) {
                    PurchaseOrderProductionDetail contractDetail = new PurchaseOrderProductionDetail();
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
    @Override
    public List<OrderFrom> listOrderFrom(String taskId) {
        return businApi.getQueryList("from OrderFrom a where a.task.id = ? ", new Object[] { taskId });
    }

    @Override
    public OrderFrom getOrderFrom(String orderFromId) {
       
        return (OrderFrom) businApi.get(OrderFrom.class, orderFromId);
    }
    @Override
    public void saveFlow(String typeId, String orderFromId) {
        VocationalWorkEnum bgImcc = VocationalWorkEnum.BG_IMCC;
        VocationalWorkEnum bgExcc = VocationalWorkEnum.BG_EXCC;
        VocationalWorkEnum wmImcc = VocationalWorkEnum.WM_IMCC;
        VocationalWorkEnum wmExcc = VocationalWorkEnum.WM_EXCC;
        VocationalWorkEnum wlImcc = VocationalWorkEnum.WL_IMCC;
        VocationalWorkEnum wlExcc = VocationalWorkEnum.WL_EXCC;
        VocationalWorkEnum wlTransport = VocationalWorkEnum.WL_TRANSPORT;
        VocationalWorkEnum ccImcc = VocationalWorkEnum.CC_IMCC;
        VocationalWorkEnum ccExcc = VocationalWorkEnum.CC_EXCC;
        OrderFrom of= (OrderFrom) businApi.get(OrderFrom.class, orderFromId);
        if (of!=null) {
            if (StringUtil.isEmpty(typeId, bgImcc.getId())) {
                BgFlowImcc flow=new BgFlowImcc();
                flow.setTaskId(of.getTask().getId());
                flow.setOrderFrom(of.getId());
                flow.setName(of.getOrderCode());
                flow.setNodeId(typeId);
                businApi.save(flow);
            } else if (StringUtil.isEmpty(typeId, bgExcc.getId())) {
                BgFlowExcc flow=new BgFlowExcc();
                flow.setTaskId(of.getTask().getId());
                flow.setOrderFrom(of.getId());
                flow.setName(of.getOrderCode());
                flow.setNodeId(typeId);
                businApi.save(flow);
            } else if (StringUtil.isEmpty(typeId, wmImcc.getId())) {
                WmFlowImcc flow=new WmFlowImcc();
                flow.setTaskId(of.getTask().getId());
                flow.setOrderFromId(of.getId());
                flow.setName(of.getOrderCode());
                flow.setNodeId(typeId);
                businApi.save(flow);
            } else if (StringUtil.isEmpty(typeId, wmExcc.getId())) {
                WmFlowExcc flow=new WmFlowExcc();
                flow.setTaskId(of.getTask().getId());
                flow.setOrderFromId(of.getId());
                flow.setName(of.getOrderCode());
                flow.setNodeId(typeId);
                businApi.save(flow);
            } else if (StringUtil.isEmpty(typeId, wlImcc.getId())) {
                WlFlowImcc flow=new WlFlowImcc();
                flow.setTaskId(of.getTask().getId());
                flow.setOrderFromId(of.getId());
                flow.setName(of.getOrderCode());
                flow.setNodeId(typeId);
                businApi.save(flow);
            } else if (StringUtil.isEmpty(typeId, wlExcc.getId())) {
                WlFlowExcc flow=new WlFlowExcc();
                flow.setTaskId(of.getTask().getId());
                flow.setOrderFromId(of.getId());
                flow.setName(of.getOrderCode());
                flow.setNodeId(typeId);
                businApi.save(flow);
            } else if (StringUtil.isEmpty(typeId, wlTransport.getId())) {
                WlFlowTransport flow=new WlFlowTransport();
                flow.setTaskId(of.getTask().getId());
                flow.setOrderFromId(of.getId());
                flow.setName(of.getOrderCode());
                flow.setNodeId(typeId);
                businApi.save(flow);
            } else if (StringUtil.isEmpty(typeId, ccImcc.getId())) {
                CcFlowImcc flow=new CcFlowImcc();
                flow.setTaskId(of.getTask().getId());
                flow.setOrderFromId(of.getId());
                flow.setName(of.getOrderCode());
                flow.setNodeId(typeId);
                businApi.save(flow);
            } else if (StringUtil.isEmpty(typeId, ccExcc.getId())) {
                CcFlowExcc flow=new CcFlowExcc();
                flow.setTaskId(of.getTask().getId());
                flow.setOrderFromId(of.getId());
                flow.setName(of.getOrderCode());
                flow.setNodeId(typeId);
                businApi.save(flow);
            }
        }
        
    }

    @Override
    public void saveSupReceiveOrder(String id) {
        OrderFrom orderFrom=(OrderFrom) businApi.get(OrderFrom.class, id);
        orderFrom.setOrderState(TaskState.FWSYJS);
        businApi.save(orderFrom);
    }
    @Override
    public void saveSubmitOrder(String id) {
        OrderFrom orderFrom=(OrderFrom) businApi.get(OrderFrom.class, id);
        orderFrom.setOrderState(TaskState.YTJ);
        businApi.save(orderFrom);
    }
}
