package com.pm.task.busin.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
import com.pm.task.busin.TaskBusin;
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
import com.sup.order.bean.OrderFrom;

@SuppressWarnings("unchecked")
@Service
public class TaskBusinImpl implements TaskBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public void saveTask(Task task,String userId) {
        if (task != null) {
            task.setIsIssue("0");
            CompanyInfo user = (CompanyInfo) businApi.get(CompanyInfo.class, userId);
            if (user != null) {
                String newDateNo = DateUtil.getStringDate(new Date(), "yyyyMMdd");
                task.setTaskName(newDateNo + user.getComName());
            }
            task.setTaskClassify("0");
            task.setCompanyInfo(user);
            businApi.save(task);
        }
    }

    @Override
    public void submitTask(String id) {
        Task task = (Task) businApi.get(Task.class, id);
        if (task != null) {
            task.setIsIssue("1");
            businApi.save(task);
        }
    }

  
    
    /**
     * 根据订单ID获取订单信息
     * @param orderId
     * @return
     */
    public TreeModel  getOrderTreeModel(String orderId){
        TreeStateModel taskTreeStateModel = new TreeStateModel(true, true);
        TreeStateModel ofTreeStateModel = new TreeStateModel(true, true);
        OrderFrom orderForm=(OrderFrom) businApi.get(OrderFrom.class, orderId);
        List<TreeModel> ofModelList = new ArrayList<TreeModel>();
        String tableName=orderForm.getFlowType().getTableName();
        switch (tableName) {
        case "BG_Flow_IMCC":
            List<Object[]> BgFlowImccs = businApi.getQueryList("SELECT a.id,a.name FROM BgFlowImcc a WHERE a.orderFrom.id=?", new Object[]{orderForm.getId()});
            for (Object[] bill : BgFlowImccs) {
                TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 2, orderForm.getFlowType().getId());
                ofModelList.add(billTreeModel);
            }
            break;
        case "BG_Flow_EXCC":
            List<Object[]> BgFlowExccs = businApi.getQueryList("SELECT a.id,a.name FROM BgFlowExcc a WHERE a.orderFrom.id=?", new Object[]{orderForm.getId()});
            for (Object[] bill : BgFlowExccs) {
                TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 2, orderForm.getFlowType().getId());
                ofModelList.add(billTreeModel);
            }
            break;
        case "WL_Flow_IMCC":
            List<Object[]> WlFlowImccs = businApi.getQueryList("SELECT a.id,a.name FROM WlFlowImcc a WHERE a.orderFromId=?", new Object[]{orderForm.getId()});
            for (Object[] bill : WlFlowImccs) {
                TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 2, orderForm.getFlowType().getId());
                ofModelList.add(billTreeModel);
            }
            
            break;
        case "WL_Flow_EXCC":
            List<Object[]> WlFlowEmccs = businApi.getQueryList("SELECT a.id,a.name FROM WlFlowExcc a WHERE  a.orderFromId=?", new Object[]{orderForm.getId()});
            for (Object[] bill : WlFlowEmccs) {
                TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 2, orderForm.getFlowType().getId());
                ofModelList.add(billTreeModel);
            }
            break;
        case "WL_Flow_Transport":
            List<Object[]> WlFlowTransports = businApi.getQueryList("SELECT a.id,a.name FROM WlFlowTransport a WHERE a.orderFromId=?", new Object[]{orderForm.getId()});
            for (Object[] bill : WlFlowTransports) {
                TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 2, orderForm.getFlowType().getId());
                ofModelList.add(billTreeModel);
            }
            break;
        case "CC_Flow_IMCC":
            List<Object[]> CcFlowImccs = businApi.getQueryList("SELECT a.id,a.name FROM CcFlowImcc a WHERE a.orderFromId=?", new Object[]{orderForm.getId()});
            for (Object[] bill : CcFlowImccs) {
                TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 2, orderForm.getFlowType().getId());
                ofModelList.add(billTreeModel);
            }
            break;
        case "CC_Flow_EXCC":
            List<Object[]> CcFlowExccs = businApi.getQueryList("SELECT a.id,a.name FROM CcFlowExcc a WHERE a.orderFromId=?", new Object[]{orderForm.getId()});
            for (Object[] bill : CcFlowExccs) {
                TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 2, orderForm.getFlowType().getId());
                ofModelList.add(billTreeModel);
            }
            break;
        case "WM_Flow_IMCC":
            List<Object[]> WmFlowImccs = businApi.getQueryList("SELECT a.id,a.name FROM WmFlowImcc a WHERE a.orderFromId=?", new Object[]{orderForm.getId()});
            for (Object[] bill : WmFlowImccs) {
                TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 2, orderForm.getFlowType().getId());
                ofModelList.add(billTreeModel);
            }
            break;
        case "WM_Flow_EXCC":
            List<Object[]> WmFlowExccs = businApi.getQueryList("SELECT a.id,a.name FROM WmFlowExcc a WHERE a.orderFromId=?", new Object[]{orderForm.getId()});
            for (Object[] bill : WmFlowExccs) {
                TreeModel billTreeModel = new TreeModel((String)bill[0], (String)bill[1], ofTreeStateModel, null, 2, orderForm.getFlowType().getId());
                ofModelList.add(billTreeModel);
            }
            break;
        }
        TreeModel treeModel = new TreeModel(orderForm.getId(), "订单:"+orderForm.getOrderCode(), taskTreeStateModel, ofModelList, 1, orderForm.getFlowType().getId());
        return treeModel;
    }
    /**
     * 客服权限的根据订单id对数据进行处理，并进行重定向
     * @param id
     * @param hierarchy
     * @param taskTypeId
     * @return
     */
    public String OrderDisposeByPm(String id,String hierarchy, String taskTypeId){
        StringBuffer url = new StringBuffer();
        FlowType flowType = (FlowType) businApi.get(FlowType.class, taskTypeId);
        String tableName = "";
        if(flowType!=null){
            tableName = flowType.getTableName();
        }
        switch (hierarchy) {
        case "1":
            switch (tableName) {
                case "BG_Flow_IMCC":
                    url.append("redirect:/Bgimcc/listBgImcc/");
                    break;
                case "BG_Flow_EXCC":
                    url.append("redirect:/Bgexcc/listBgexcc/");
                    break;
                case "WL_Flow_IMCC":
                    url.append("redirect:/Wlimcc/listWlimcc/");
                    break;
                case "WL_Flow_EXCC":
                    url.append("redirect:/Wlexcc/listWlexcc/");
                    break;
                case "WL_Flow_Transport":
                    url.append("redirect:/Wltransport/listWltransport/");
                    break;
                case "CC_Flow_EXCC":
                    url.append("redirect:/Ccexcc/listCcexcc/");
                    break;
                case "CC_Flow_IMCC":
                    url.append("redirect:/Ccimcc/listCcimcc/");
                    break;
                case "WM_Flow_IMCC":
                    url.append("redirect:/Wmimcc/listWmimcc/");
                    break;
                case "WM_Flow_EXCC":
                    url.append("redirect:/Wmexcc/listWmexcc/");
                    break;
            }
            break;
        case "2":
            switch (tableName) {
                case "BG_Flow_IMCC":
                    url.append("redirect:/Bgimcc/listBgimccNode/");
                    break;
                case "BG_Flow_EXCC":
                    url.append("redirect:/Bgexcc/listBgExccNode/");
                    break;
                case "WL_Flow_IMCC":
                    url.append("redirect:/Wlimcc/listWlImccNode/");
                    break;
                case "WL_Flow_EXCC":
                    url.append("redirect:/Wlexcc/listWlExccNode/");
                    break;
                case "WL_Flow_Transport":
                    url.append("redirect:/Wltransport/listWltransportNode/");
                    break;
                case "CC_Flow_EXCC":
                    url.append("redirect:/Ccexcc/listCcExccNode/");
                    break;
                case "CC_Flow_IMCC":
                    url.append("redirect:/Ccimcc/listCcImccNode/");
                    break;
                case "WM_Flow_IMCC":
                    url.append("redirect:/Wmimcc/listWmImccNode/");
                    break;
                case "WM_Flow_EXCC":
                    url.append("redirect:/Wmexcc/listWmExccNode/");
                    break;
            }
            break;
        }
        url.append(id);
        return url.toString();
    }
    /**
     * 根据订单id对数据进行处理，并进行重定向
     */
    public String OrderDispose(String id,String hierarchy, String taskTypeId){
        StringBuffer url = new StringBuffer();
        FlowType flowType = (FlowType) businApi.get(FlowType.class, taskTypeId);
        String tableName = "";
        if(flowType!=null){
            tableName = flowType.getTableName();
        }
        switch (hierarchy) {
        case "1":
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
        case "2":
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
    public void removeTask(String id) {
        Task task = (Task) businApi.get(Task.class, id);
        if (task != null) {
            businApi.remove(task);
        }
    }

    @Override
    public Task getTask(String id) {

        return (Task) businApi.get(Task.class, id);
    }
}
