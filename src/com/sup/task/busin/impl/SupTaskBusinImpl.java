package com.sup.task.busin.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.busin.BusinApi;
import com.pm.core.util.DateUtil;
import com.pm.framework.models.TreeModel;
import com.pm.framework.models.TreeStateModel;
import com.pm.task.bean.Task;
import com.sup.flow.bean.BgFlowImcc;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.OrderFrom;
import com.sup.task.busin.SupTaskBusin;
@SuppressWarnings("unchecked")
@Service
public class SupTaskBusinImpl implements SupTaskBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public void saveTask(Task task,String userId) {
        if (task != null) {
            CompanyInfo user = (CompanyInfo) businApi.get(CompanyInfo.class, userId);
            if (user != null) {
                String newDateNo = DateUtil.getStringDate(new Date(), "yyyyMMdd");
                task.setTaskName(newDateNo + user.getComName());
            }
            task.setCompanyInfo(user);
            task.setTaskClassify("0");
            businApi.save(task);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public TreeModel getTreeModel(String taskId) {
        TreeStateModel taskTreeStateModel = new TreeStateModel(true, true);
        TreeStateModel ofTreeStateModel = new TreeStateModel(true, true);
        Task task = (Task) businApi.get(Task.class, taskId);
        List<TreeModel> treeModelList = new ArrayList<TreeModel>();
        if (task != null) {
            for (OrderFrom of : task.getOrderFroms()) {
                if (of!=null) {

                    List<TreeModel> ofModelList = new ArrayList<TreeModel>();
                    String tableName=of.getFlowType().getTableName();
                    switch (tableName) {
                    case "BG_Flow_IMCC":
                        List<Object[]> BgFlowImccs = businApi.getQueryList("SELECT a.id,a.name FROM BgFlowImcc a WHERE a.orderFrom.id=?", new Object[]{of.getId()});
                        for (Object[] bill : BgFlowImccs) {
                            TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 3, of.getFlowType().getId());
                            ofModelList.add(billTreeModel);
                        }
                        break;
                    case "BG_Flow_EXCC":
                        List<Object[]> BgFlowExccs = businApi.getQueryList("SELECT a.id,a.name FROM BgFlowExcc a WHERE a.orderFrom.id=?", new Object[]{of.getId()});
                        for (Object[] bill : BgFlowExccs) {
                            TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 3, of.getFlowType().getId());
                            ofModelList.add(billTreeModel);
                        }
                        break;
                    case "WL_Flow_IMCC":
                        List<Object[]> WlFlowImccs = businApi.getQueryList("SELECT a.id,a.name FROM WlFlowImcc a WHERE a.orderFromId=?", new Object[]{of.getId()});
                        for (Object[] bill : WlFlowImccs) {
                            TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 3, of.getFlowType().getId());
                            ofModelList.add(billTreeModel);
                        }
                        
                        break;
                    case "WL_Flow_EXCC":
                        List<Object[]> WlFlowEmccs = businApi.getQueryList("SELECT a.id,a.name FROM WlFlowExcc a WHERE  a.orderFromId=?", new Object[]{of.getId()});
                        for (Object[] bill : WlFlowEmccs) {
                            TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 3, of.getFlowType().getId());
                            ofModelList.add(billTreeModel);
                        }
                        break;
                    case "WL_Flow_Transport":
                        List<Object[]> WlFlowTransports = businApi.getQueryList("SELECT a.id,a.name FROM WlFlowTransport a WHERE a.orderFromId=?", new Object[]{of.getId()});
                        for (Object[] bill : WlFlowTransports) {
                            TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 3, of.getFlowType().getId());
                            ofModelList.add(billTreeModel);
                        }
                        break;
                    case "CC_Flow_IMCC":
                        List<Object[]> CcFlowImccs = businApi.getQueryList("SELECT a.id,a.name FROM CcFlowImcc a WHERE a.orderFromId=?", new Object[]{of.getId()});
                        for (Object[] bill : CcFlowImccs) {
                            TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 3, of.getFlowType().getId());
                            ofModelList.add(billTreeModel);
                        }
                        break;
                    case "CC_Flow_EXCC":
                        List<Object[]> CcFlowExccs = businApi.getQueryList("SELECT a.id,a.name FROM CcFlowExcc a WHERE a.orderFromId=?", new Object[]{of.getId()});
                        for (Object[] bill : CcFlowExccs) {
                            TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 3, of.getFlowType().getId());
                            ofModelList.add(billTreeModel);
                        }
                        break;
                    case "WM_Flow_IMCC":
                        List<Object[]> WmFlowImccs = businApi.getQueryList("SELECT a.id,a.name FROM WmFlowImcc a WHERE a.orderFromId=?", new Object[]{of.getId()});
                        for (Object[] bill : WmFlowImccs) {
                            TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 3, of.getFlowType().getId());
                            ofModelList.add(billTreeModel);
                        }
                        break;
                    case "WM_Flow_EXCC":
                        List<Object[]> WmFlowExccs = businApi.getQueryList("SELECT a.id,a.name FROM WmFlowExcc a WHERE a.orderFromId=?", new Object[]{of.getId()});
                        for (Object[] bill : WmFlowExccs) {
                            TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 3, of.getFlowType().getId());
                            ofModelList.add(billTreeModel);
                        }
                        break;
                    }
                    TreeModel ofTreeModel = new TreeModel(of.getId(), of.getFlowType().getFlowName(), ofTreeStateModel, ofModelList, 2, of.getFlowType().getId());
                    treeModelList.add(ofTreeModel);
                }
                
                
            }
        }
        TreeModel treeModel = new TreeModel(taskId, "任务 ", taskTreeStateModel, treeModelList, 1, "0");
        return treeModel;
    }

    @Override
    public String TaskDispose(String id, String hierarchy, String taskTypeId) {
        StringBuffer url = new StringBuffer();
        FlowType flowType = (FlowType) businApi.get(FlowType.class, taskTypeId);
        String tableName ="";
        if (flowType!=null) {
            tableName = flowType.getTableName();
       }
        switch (hierarchy) {
        case "1":
            url.append("redirect:/sup/order/OrderFrom/listOrder/");
            break;
        case "2":
              switch (tableName) {
                    case "BG_Flow_IMCC":
                        url.append("redirect:/sup/Bgimcc/listBgImcc/");
                        break;
                    case "BG_Flow_EXCC":
                        url.append("redirect:/sup/Bgexcc/listBgExcc/");
                        break;
                    case "WL_Flow_IMCC":
                        url.append("redirect:/sup/Wlimcc/listWlimcc/");
                        break;
                    case "WL_Flow_EXCC":
                        url.append("redirect:/sup/Wlexcc/listWlexcc/");
                        break;
                    case "WL_Flow_Transport":
                        url.append("redirect:/sup/Wltransport/listWltransport/");
                        break;
                    case "CC_Flow_EXCC":
                        url.append("redirect:/sup/Ccexcc/listCcexcc/");
                        break;
                    case "CC_Flow_IMCC":
                        url.append("redirect:/sup/Ccimcc/listCcimcc/");
                        break;
                    case "WM_Flow_IMCC":
                        url.append("redirect:/sup/Wmimcc/listWmimcc/");
                        break;
                    case "WM_Flow_EXCC":
                        url.append("redirect:/sup/Wmexcc/listWmexcc/");
                        break;
            }
            break;
        case "3":
                switch (tableName) {
                    case "BG_Flow_IMCC":
                        url.append("redirect:/sup/Bgimcc/listBgimccNode/");
                        break;
                    case "BG_Flow_EXCC":
                        url.append("redirect:/sup/Bgexcc/listBgExccNode/");
                        break;
                    case "WL_Flow_IMCC":
                        url.append("redirect:/sup/Wlimcc/listWlImccNode/");
                        break;
                    case "WL_Flow_EXCC":
                        url.append("redirect:/sup/Wlexcc/listWlExccNode/");
                        break;
                    case "WL_Flow_Transport":
                        url.append("redirect:/sup/Wltransport/listWltransportNode/");
                        break;
                    case "CC_Flow_EXCC":
                        url.append("redirect:/sup/Ccexcc/listCcExccNode/");
                        break;
                    case "CC_Flow_IMCC":
                        url.append("redirect:/sup/Ccimcc/listCcImccNode/");
                        break;
                    case "WM_Flow_IMCC":
                        url.append("redirect:/sup/Wmimcc/listWmImccNode/");
                        break;
                    case "WM_Flow_EXCC":
                        url.append("redirect:/sup/Wmexcc/listWmExccNode/");
                        break;
            }
            break;
    }
        url.append(id);
        return url.toString();
    }

    @Override
    public String getTaskId() {
        String newDateNo = DateUtil.getStringDate(new Date(), "yyyyMMdd");
        NumberFormat nf = NumberFormat.getInstance();
      //修改订单流水号的号
        nf.setMaximumIntegerDigits(5);
        nf.setMinimumIntegerDigits(5);
        nf.setGroupingUsed(false);
        int newNo = 1;
        Query query = businApi.getQuery("SELECT MAX(taskId) FROM Task", null);
        List<String> list = query.list();
        String taskId = "";
        if (list != null && list.size() > 0) {
            taskId = list.get(0);
            if (taskId!=null) {
                String oldDateNo = taskId.substring(0, 8);
                String oldNo = taskId.substring(8);
                if (oldDateNo.equals(newDateNo)) {
                    newNo = Integer.parseInt(oldNo) + 1;
                    taskId = oldDateNo + nf.format(newNo);
                    return taskId;
                }
            }
        }
        taskId = newDateNo + nf.format(newNo);
        return taskId;
        
    }

    @Override
    public void submitTask(String id) {
        Task task = (Task) businApi.get(Task.class, id);
        if (task != null) {
            task.setIsIssue("1");
            businApi.save(task);
        }
    }
    @Override
    public void removeTask(String id) {
        Task task=(Task) businApi.get(Task.class, id);
        if (task!=null) {
            businApi.remove(task);
        }
    }

    @Override
    public String getTaskIdByTaskName(String id) {
        StringBuffer taskTypeName=new StringBuffer();
         
        List<OrderFrom> orderFroms = businApi.getQueryList("FROM OrderFrom a where a.task.id=?", new Object[]{id});
         if (orderFroms!=null) {
            for (OrderFrom orderFrom : orderFroms) {
                if (orderFrom!=null) {
                    taskTypeName.append(orderFrom.getFlowType().getFlowName()+" ");
                }
            }
         }
        
        return taskTypeName.toString();
    }

    @Override
    public List<OrderFrom> getorderForm(String taskId) {
        List<OrderFrom> orderFroms = businApi.getQueryList("FROM OrderFrom a where a.task.id=?", new Object[]{taskId});
        return orderFroms;
    }
}
