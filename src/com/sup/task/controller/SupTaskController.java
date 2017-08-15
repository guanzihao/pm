package com.sup.task.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.Contexts;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.model.TaskState;
import com.pm.core.util.DateUtil;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.TreeModel;
import com.pm.order.busin.OrderFromBusin;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.sysconfig.busin.EnumBusin;
import com.pm.task.bean.AjaxDomain;
import com.pm.task.bean.Task;
import com.sun.org.apache.xml.internal.security.Init;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.CustomsDeAgreement;
import com.sup.order.bean.ExportContract;
import com.sup.order.bean.ExportCustomsDeAgreement;
import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.ExportShippingExCommission;
import com.sup.order.bean.ImportContract;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.PurchaseOrderProduction;
import com.sup.order.bean.ShippingExCommission;
import com.sup.order.bean.TropShippingExCommission;
import com.sup.task.busin.SupTaskBusin;

import net.sf.json.JSONObject;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/sup/task")
public class SupTaskController extends SupBaseController {

    @Autowired
    private CompanyInfoBusin companyInfoBusin;

    @Autowired
    private EnumBusin enumBusin;

    @Autowired
    private SupTaskBusin supTaskBusin;

    @Autowired
    private OrderFromBusin orderFromBusin;

    /**
     * 查询所有的任务
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping("/listTask")
    public String listTask(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql = "SELECT   DISTINCT a.obj_Id,a.task_id,a.tack_type,a.issue_date,pcc.com_Name from PM_Task a ,PM_OrderFrom b  ,PM_COMPANY_CompanyInfo pcc" + "  where a.obj_Id=b.task_id and a.user_id=pcc.obj_Id and a.user_id=? ";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(getCurrSup().getCompanyInfo().getId());
        pageBean.addQueryStr("a.task_id,pcc.com_Name", searchBean.getSearchName1(), true);
        pageBean.addOrderBy("a.issue_date", true);
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        return "/sup/task/listTask";
    }

    /**
     * 添加任务初始化数据
     * 
     * @return
     */
    @RequestMapping("/initSzveTask/{id}")
    public String initSzveTask(@PathVariable String id) {
        //查询供应商的枚举
        Enumtype enumType = enumBusin.getEnumtypeByName("供应商枚举信息");
        if (enumType != null) {
            //查询出所有供应商的类型
            List<Enumitems> enumitems = enumBusin.getEnumitemList(enumType);
            for (Enumitems enumitems2 : enumitems) {
                //查询出不同类型的供应商
                VocationalWorkEnum WL = VocationalWorkEnum.WL;
                VocationalWorkEnum BG = VocationalWorkEnum.BG;
                VocationalWorkEnum WM = VocationalWorkEnum.WM;
                VocationalWorkEnum CC = VocationalWorkEnum.CC;
                //查询出不同类型的供应商
                /*if (enumitems2.getId() != null && enumitems2.getId().equals(WL.getId())) {
                    List<SupCompanyInfo> supCompanyInfowuliu = companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                    model.addAttribute("supCompanyInfowuliu", supCompanyInfowuliu);
                }
                if (enumitems2.getId() != null && enumitems2.getId().equals(CC.getId())) {
                    List<SupCompanyInfo> supCompanyInfocangchu = companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                    model.addAttribute("supCompanyInfocangchu", supCompanyInfocangchu);
                }
                if (enumitems2.getId() != null && enumitems2.getId().equals(BG.getId())) {
                    List<SupCompanyInfo> supCompanyInfobaoguan = companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                    model.addAttribute("supCompanyInfobaoguan", supCompanyInfobaoguan);
                }
                if (enumitems2.getId() != null && enumitems2.getId().equals(WM.getId())) {
                    List<SupCompanyInfo> supCompanyInfowaimao = companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                    model.addAttribute("supCompanyInfowaimao", supCompanyInfowaimao);
                }*/
            }
        }
        List<FlowType> flowTypes = businApi.getList(FlowType.class);
        String taskId = supTaskBusin.getTaskId();
        Task task = (Task) businApi.get(Task.class, id);
        model.addAttribute("task", task);
        model.addAttribute("flowTypes", flowTypes);
        model.addAttribute("user", getCurrSup());
        model.addAttribute("taskId", taskId);
        return "/sup/task/editTask";
    }

    /**
     * 保存一个任务
     * 
     * @param task
     * @return
     */
    @RequestMapping("/saveTask")
    public String saveTask(Task task, String[] tackFiles, String userId) {
        supTaskBusin.saveTask(task, userId);
        frameworkBusin.saveUploadFileOwner(task, tackFiles);
        return "redirect:/sup/task/viewTask/" + task.getId();
    }

    /**
     * 提交一个任务信息
     * 
     * @param id
     */
    @RequestMapping("/submitTask/{id}")
    public String submitTask(@PathVariable String id) {

        orderFromBusin.saveTaskIdOrderFrom(id);
        supTaskBusin.submitTask(id);
        return "redirect:/sup/task/viewTask/" + id;
    }

    @RequestMapping("/viewTask/{id}")
    public String viewTask(@PathVariable String id) {
        Task task = (Task) businApi.get(Task.class, id);
        String flowTypeName = "";
        if (task != null) {
            String oldAllId = task.getTackType();
            String[] newAllId = oldAllId.split(",");

            for (String string : newAllId) {
                FlowType flowType = (FlowType) businApi.get(FlowType.class, string);
                flowTypeName += flowType.getFlowName() + " ";
            }
        }
        model.addAttribute("task", task);
        model.addAttribute("flowTypeName", flowTypeName);
        return "/sup/task/viewTask";
    }

    /**
     * 删除一个人任务信息
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @RequestMapping("/removeTask")
    @ResponseBody
    public void removeTask(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    supTaskBusin.removeTask(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

    /**
     * 业务流程树形菜单
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping("/listTree/{taskId}")
    public String listTree(@PathVariable String taskId) {
        TreeModel treeModel = supTaskBusin.getTreeModel(taskId);
        model.addAttribute("treeData", JSONObject.fromObject(treeModel));
        Task task=(Task)businApi.get(Task.class, taskId);
        List<OrderFrom> orderFrom=supTaskBusin.getorderForm(taskId);
        model.addAttribute("orderFrom", orderFrom);
        model.addAttribute("task", task);
        return "/sup/task/listTree";
    }

    /**
     * 任务处理
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping("/listTaskDispose/{id}/{hierarchy}/{taskTypeId}")
    public String listTaskDispose(@PathVariable String id, @PathVariable String hierarchy, @PathVariable String taskTypeId) {
        return supTaskBusin.TaskDispose(id, hierarchy, taskTypeId);
    }

    /**
     * 跳转至下单预备页面
     * 
     * @return
     */
    @RequestMapping(value = "/orderPrep")
    public String toOrderPrepPage() {
        VocationalWorkEnum bgImcc = VocationalWorkEnum.BG_IMCC;
        VocationalWorkEnum bgExcc = VocationalWorkEnum.BG_EXCC;
        VocationalWorkEnum wmImcc = VocationalWorkEnum.WM_IMCC;
        VocationalWorkEnum wmExcc = VocationalWorkEnum.WM_EXCC;
        VocationalWorkEnum wlImcc = VocationalWorkEnum.WL_IMCC;
        VocationalWorkEnum wlExcc = VocationalWorkEnum.WL_EXCC;
        VocationalWorkEnum wlTransport = VocationalWorkEnum.WL_TRANSPORT;
        VocationalWorkEnum ccImcc = VocationalWorkEnum.CC_IMCC;
        VocationalWorkEnum ccExcc = VocationalWorkEnum.CC_EXCC;

        model.addAttribute("bgImcc", bgImcc.getId());
        model.addAttribute("bgExcc", bgExcc.getId());
        model.addAttribute("wmImcc", wmImcc.getId());
        model.addAttribute("wmExcc", wmExcc.getId());
        model.addAttribute("wlImcc", wlImcc.getId());
        model.addAttribute("wlExcc", wlExcc.getId());
        model.addAttribute("wlTransport", wlTransport.getId());
        model.addAttribute("ccImcc", ccImcc.getId());
        model.addAttribute("ccExcc", ccExcc.getId());
        return "/sup/task/orderPrep";
    }

    /**
     * 跳转至合同书详情页
     * 
     * @return
     */
    @RequestMapping(value = "/constractDetail")
    public String toConstractPage(String flowTypeId[]) {
        Task task = new Task();
        String taskTypeId = "";
        if (flowTypeId != null && flowTypeId.length > 0) {
            String flowTypeIds = StringUtil.getArrayToString(flowTypeId);
            String taskId = supTaskBusin.getTaskId();
            task.setTackType(flowTypeIds);
            task.setTaskId(taskId);
            task.setIssueDate(new Date(System.currentTimeMillis()));
            String id = getCurrSup().getCompanyInfo().getId();
            supTaskBusin.saveTask(task, id);
            ArrayList<Map<String, Object>> billNames = new ArrayList<Map<String, Object>>();
            String url = "";
            for (String string : flowTypeId) {
                VocationalWorkEnum workEnum = VocationalWorkEnum.getName(string);

                if (workEnum != null) {
                    if ("".equals(url)) {
                        url = workEnum.getUrl();
                        taskTypeId = workEnum.getId();
                    }
                }
            }
            model.addAttribute("taskTypeId", taskTypeId);
        }

        return "redirect:/sup/task/taskTypeToPage/" + task.getId() + "/" + taskTypeId;
    }

    /**
     * 进口合同跳转页面
     * 
     * @param taskId
     * @param taskTypeId
     * @return
     */
    @RequestMapping(value = "/taskTypeToPage/{taskId}/{taskTypeId}")
    public String taskTypeToPage(@PathVariable String taskId, @PathVariable String taskTypeId) {
        VocationalWorkEnum bgImcc = VocationalWorkEnum.BG_IMCC;
        VocationalWorkEnum bgExcc = VocationalWorkEnum.BG_EXCC;
        VocationalWorkEnum wmImcc = VocationalWorkEnum.WM_IMCC;
        VocationalWorkEnum wmExcc = VocationalWorkEnum.WM_EXCC;
        VocationalWorkEnum wlImcc = VocationalWorkEnum.WL_IMCC;
        VocationalWorkEnum wlExcc = VocationalWorkEnum.WL_EXCC;
        VocationalWorkEnum wlTransport = VocationalWorkEnum.WL_TRANSPORT;
        VocationalWorkEnum ccImcc = VocationalWorkEnum.CC_IMCC;
        VocationalWorkEnum ccExcc = VocationalWorkEnum.CC_EXCC;
        ArrayList<Map<String, Object>> billNames = new ArrayList<Map<String, Object>>();
        Task task = (Task) businApi.get(Task.class, taskId);
        if (task != null) {
            model.addAttribute("taskTypes", task.getTackType());
            String[] flowTypeId = task.getTackType().split(",");
            int flag=0;
            for (String typeId : flowTypeId) {
                VocationalWorkEnum workEnum = VocationalWorkEnum.getName(typeId);
                if (workEnum != null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", workEnum.getName());
                    map.put("id", workEnum.getId());
                    map.put("url", workEnum.getUrl());
                    model.addAttribute(workEnum.getUrl(), workEnum.getUrl());
                    billNames.add(map);
                }
                if (flag==0) {
                    if (StringUtil.isEmpty(typeId, wlImcc.getId())||StringUtil.isEmpty(typeId, wlExcc.getId())||StringUtil.isEmpty(typeId, wlTransport.getId())) {
                        flag=1;
                        VocationalWorkEnum wlzffs = VocationalWorkEnum.WLZFFS;
                        VocationalWorkEnum wltdlx = VocationalWorkEnum.WLTDLX;
                        List<Enumitems> wlzffsList = enumBusin.getEnumTypeIdByEnumitemList(wlzffs.getId());
                        List<Enumitems> wltdlxList = enumBusin.getEnumTypeIdByEnumitemList(wltdlx.getId());
                        model.addAttribute("wlzffsList1", wlzffsList);
                        model.addAttribute("wltdlxList1", wltdlxList);
                        model.addAttribute("wlzffsList2", wlzffsList);
                        model.addAttribute("wltdlxList2", wltdlxList);
                        model.addAttribute("wlzffsList3", wlzffsList);
                        model.addAttribute("wltdlxList3", wltdlxList);
                    }
                }
                if (StringUtil.isEmpty(typeId, bgImcc.getId())) {
                    model.addAttribute("customsDeAgreement", businApi.get(CustomsDeAgreement.class, typeId));
                } else if (StringUtil.isEmpty(typeId, bgExcc.getId())) {
                    model.addAttribute("exportCustomsDeAgreement", businApi.get(ExportCustomsDeAgreement.class, typeId));
                } else if (StringUtil.isEmpty(typeId, wmImcc.getId())) {
                    model.addAttribute("importContract", businApi.get(ImportContract.class, typeId));
                } else if (StringUtil.isEmpty(typeId, wmExcc.getId())) {
                    model.addAttribute("exportContract", businApi.get(ExportContract.class, typeId));
                } else if (StringUtil.isEmpty(typeId, wlImcc.getId())) {
                    model.addAttribute("shippingExCommission", businApi.get(ShippingExCommission.class, typeId));
                } else if (StringUtil.isEmpty(typeId, wlExcc.getId())) {
                    model.addAttribute("exportShippingExCommission", businApi.get(ExportShippingExCommission.class, typeId));
                } else if (StringUtil.isEmpty(typeId, wlTransport.getId())) {
                    model.addAttribute("tropShippingExCommission", businApi.get(TropShippingExCommission.class, typeId));
                } else if (StringUtil.isEmpty(typeId, ccImcc.getId())) {
                    model.addAttribute("purchaseOrderProduction", businApi.get(PurchaseOrderProduction.class, typeId));
                } else if (StringUtil.isEmpty(typeId, ccExcc.getId())) {
                    model.addAttribute("exportPurchaseOrderProduction", businApi.get(ExportPurchaseOrderProduction.class, typeId));
                }
            }
        }
        model.addAttribute("companyInfoUser", getCurrSup());
        model.addAttribute("companyInfo", getCurrSup().getCompanyInfo());
        model.addAttribute("names", billNames);
        model.addAttribute("taskTypeId", taskTypeId);
        model.addAttribute("newDate", DateUtil.getStringDate(DateUtil.getDate(), DateUtil.DateFormat1));
        model.addAttribute("task", businApi.get(Task.class, taskId));
        return "/sup/order/customer/editContract";
    }

    @RequestMapping(value = "/add")
    public String task() {
        return "/sup/editBidProductList";
    }

    @RequestMapping(value = "/listCompanyInfo/{taskTypeId}/{flag}")
    public String listCompanyInfo(@PathVariable String taskTypeId, SearchBean searchBean, @PathVariable String flag) {
        StringBuffer url = new StringBuffer();
        FlowType flowType = (FlowType) businApi.get(FlowType.class, taskTypeId);
        if (flowType != null) {
            String type = flowType.getFlowType();
            PageBean pageBean = new PageBean(searchBean, businApi);
            pageBean.addQuerySQL("select a.id, a.comCode, a.comName,a.comTel,a.comAddress,a.comState,a.createDate from SupCompanyInfo a where comState = ? and a.comType=?");
            pageBean.addParams(Contexts.Y);
            pageBean.addParams(type);
            pageBean.addQueryStr("a.comState", searchBean.getSearchName1(), false);
            pageBean.addQueryStr("a.comCode,a.comName", searchBean.getSearchName2(), true);
            pageBean.addQueryDate("a.createDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
            pageBean.addOrderBy("a.createDate", true);
            pageBean.query();
            model.addAttribute("pageBean", pageBean);
            model.addAttribute("taskTypeId", taskTypeId);

        }
        switch (flag) {
        case "wmIc":
            model.addAttribute("editWm", "wmIc");
            url.append("/sup/task/listCompanyInfo");
            break;
        case "editWmIc":
            model.addAttribute("editWm", "editWmIc");
            url.append("/sup/task/listCompanyInfo");
            break;
        case "editWmEc":
            model.addAttribute("editWm", "editWmEc");
            url.append("/sup/task/listCompanyInfo");
            break;
        case "wmEc":
            model.addAttribute("editWm", "wmEc");
            url.append("/sup/task/listCompanyInfoWmEc");
            break;
        }
        model.addAttribute("flag", flag);
        return url.toString();
    }

    @RequestMapping(value = "/listProduct/{taskTypeId}/{flag}")
    public String listProduct(@PathVariable String taskTypeId, SearchBean searchBean, @PathVariable String flag, String productTypeId, String productTypeName) {

        String keHuId = getCurrSup().getId();

        FlowType flowType = (FlowType) businApi.get(FlowType.class, taskTypeId);
        if (flowType != null) {
            PageBean pageBean = new PageBean(searchBean, businApi);
            pageBean.addQuerySQL("select p.id, p.productName, p.productCode, p.productSpec,  p.productUnit, p.productPrice,p.productType,p.productstate from Product p where p.companyInfoUser=? ");
            pageBean.addParams(this.getCurrSup().getId());
            pageBean.addQueryStr("p.productName", searchBean.getSearchName1(), true);
            pageBean.addQueryStr("p.productCode", searchBean.getSearchName2(), true);
            if (!StringUtil.isEmpty(searchBean.getSearchName1()) || !StringUtil.isEmpty(searchBean.getSearchName2())) {
                pageBean.addQuerySQL(" and 1=1");
            } else {
                pageBean.addQuerySQL(" and 1=1");
            }

            StringBuffer sf = new StringBuffer();
            if (!StringUtil.isEmpty(productTypeId)) {
                sf.append(" and p.productType.id ='" + productTypeId + " ' ");
            }

            pageBean.addQuerySQL(sf.toString());
            pageBean.addOrderBy("p.productIndex", true);
            pageBean.query();
            model.addAttribute("pageBean", pageBean);
            model.addAttribute("taskTypeId", taskTypeId);
        }
        StringBuffer url = new StringBuffer();
        switch (flag) {
        case "wmIc":
            model.addAttribute("updatewm", "wmIc");
            url.append("/sup/task/listProduct");
            break;
        case "wmEc":
            model.addAttribute("updatewm", "wmEc");
            url.append("/sup/task/listProductWmEc");
            break;
        case "updatewmEc":
            model.addAttribute("updatewm", "updatewmEc");
            url.append("/sup/task/listProduct");
            break;
        case "updatewmIc":
            model.addAttribute("updatewm", "updatewmIc");
            url.append("/sup/task/listProduct");
            break;
        }
        model.addAttribute("flag", flag);

        return url.toString();
    }
    
    @RequestMapping("/userAgreement")
    public String userAgreement(){
        return "/sup/task/userAgreement";
    }
    
}
