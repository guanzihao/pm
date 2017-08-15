package com.sup.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.model.TaskState;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.TreeModel;
import com.pm.right.models.FunAuth;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.task.bean.AjaxDomain;
import com.pm.task.bean.Task;
import com.pm.task.busin.TaskBusin;
import com.sup.company.controller.SupBaseController;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.PurchaseOrderProduction;
import com.sup.order.bean.PurchaseOrderProductionDetail;
import com.sup.order.busin.SupOrderFromBusin;

import net.sf.json.JSONArray;

/**
 * 订单进口（仓储进口）
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/sup/order/OrderFrom")
public class SupOrderFromController extends SupBaseController {

    @Autowired
    private SupOrderFromBusin suporderFromBusin;

    @Autowired
    private TaskBusin taskBusin;

    @RequestMapping("/saveOrderFrom")
    @ResponseBody
    public AjaxDomain saveOrderFrom(PurchaseOrderProduction purchaseOrderProduction, String orderFromId, String taskTypeId, String detailNos, String descriptions, String units, String priceVilidityPeriods, String perPrices, String remarks, String fileArray) {
        AjaxDomain ajaxDomain = new AjaxDomain();
        try {
            String fileArrayArray[] = null;
            if (fileArray != null) {
                fileArrayArray = fileArray.split(",");
            }
            suporderFromBusin.saveOrderFroms(purchaseOrderProduction, taskTypeId, getCurrSup().getCompanyInfo().getComcangchu(), detailNos, descriptions, units, priceVilidityPeriods, perPrices, remarks,orderFromId);
            frameworkBusin.saveUploadFileOwner(purchaseOrderProduction, fileArrayArray);
            String taskId = purchaseOrderProduction.getTaskId();
            String taskType = "";
            String oneTaskId = "";
            if (!StringUtil.isEmpty(taskId)) {
                Task task = (Task) businApi.get(Task.class, taskId);
                if (task != null) {
                    String type = task.getTackType();
                    String[] types = type.split(",");
                    for (String str : types) {
                        if ("".equals(oneTaskId)) {
                            oneTaskId = str;
                        }
                        List<OrderFrom> list = businApi.getQueryList("FROM OrderFrom a where a.flowType.id=? and a.task.id=?", new Object[] { str, taskId });
                        if (list != null && list.size() == 0) {
                            if ("".equals(taskType)) {
                                taskType = str;
                            }
                        }
                    }
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            if ("".equals(taskType)) {
                taskType = "1";
                map.put("oneTaskId", oneTaskId);
            }
            map.put("isTaskType", taskType);
            if (orderFromId!=null&&!"".equals(orderFromId)) {
                map.put("orderFromId", orderFromId);
             
            }else{
                map.put("orderFromId", purchaseOrderProduction.getId());
            }
            ajaxDomain.setMap(map);
            ajaxDomain.setStatusCode("200");
        } catch (Exception e) {
            ajaxDomain.setStatusCode("400");

            e.printStackTrace();
        }
        return ajaxDomain;
    }

    /**
     * 修改是保存数据
     * 
     * @param customsDeAgreement
     * @param taskTypeId
     * @param fileArray
     * @return
     */
    @RequestMapping("/saveObject/{nodeType}/{flowId}/{isCompany}")
    public String saveObject(PurchaseOrderProduction purchaseOrderProduction, String fileArray[], @PathVariable String nodeType, @PathVariable String flowId, @PathVariable String isCompany, String ids[], String description[], String unit[], String priceVilidityPeriod[], String perPrice[], String remark[]) {
        suporderFromBusin.saveObject(purchaseOrderProduction);
        frameworkBusin.saveUploadFileOwner(purchaseOrderProduction, fileArray);
        suporderFromBusin.saveObjectDetail(purchaseOrderProduction.getId(), ids, description, unit, priceVilidityPeriod, perPrice, remark);
        return "redirect:/sup/order/OrderFrom/viewCustomsDeAgreement/" + purchaseOrderProduction.getOrderFromId() + "/" + nodeType + "/" + flowId + "/" + isCompany;
    }

    /**
     * 修改操作
     * 
     * @param id
     * @return
     */
    @RequestMapping("/editPurchaseOrderProduction/{id}/{nodeType}/{flowId}/{isCompany}")
    public String editPurchaseOrderProduction(@PathVariable String id, @PathVariable String nodeType, @PathVariable String flowId, @PathVariable String isCompany) {
        PurchaseOrderProduction pop = suporderFromBusin.getObject(id);
        if (pop != null) {
            List<PurchaseOrderProductionDetail> popd = businApi.getQueryList("FROM PurchaseOrderProductionDetail a where a.detailNo=?", new Object[] { pop.getId() });
            JSONArray jsonArray = JSONArray.fromObject(popd);
            model.addAttribute("popd", jsonArray.toString());
        }
        SupCompanyInfo sci = getCurrSup().getSupCompanyInfo();
        if (sci!=null) {
            model.addAttribute("supCompanyInFo", "1");
        }else {
            model.addAttribute("supCompanyInFo", "0");
        }
        model.addAttribute("pop", pop);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany", isCompany);
        return "/sup/order/customer/editPurchaseOrderProduction";
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping("/viewCustomsDeAgreement/{orderid}/{nodeType}/{flowId}/{isCompany}")
    public String listImportContract(SearchBean searchBean, @PathVariable String orderid, @PathVariable String nodeType, @PathVariable String flowId, @PathVariable String isCompany) {
        OrderFrom orderFrom = (OrderFrom) businApi.get(OrderFrom.class, orderid);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql = "select c.obj_id,ci.com_Name,o.order_code,o.task_id,ci.obj_updateDate,c.pur_order_no,c.buyer" 
        + " ,c.total_amount,c.issue_date,c.consignor,c.project,c.b_telphone,c.b_fax,c.supplier" 
                + " ,c.supplier_no,c.contact_person,c.cp_address,c.cp_telephone,c.cp_fax,c.contract_term" 
        + " ,c.delivery_date,c.instr_destination,c.payment_term,c.trade_condition,c.ref_ocuments" 
                + " ,c.pcl" 
        + " ,c.currency,c.tooling_cost,c.pay_way,c.tooling_life"
        + " ,c.share_amount,c.share_per_price,c.comments,c.is_delegation,c.start_date,c.end_date,c.explain,c.obj_createDate " 
                + " from PURCHASE_ORDER_PRODUCTION c ,PM_OrderFrom o,PM_COMPANY_CompanyInfo ci"
        + " where c.order_from_id=o.obj_id" + " and c.user_id=ci.obj_id  and o.obj_id='" + orderid + "'";
        pageBean.addQuerySQL(sql);
        pageBean.sqlquery();
        TreeModel treeModel = taskBusin.getOrderTreeModel(orderid);
        model.addAttribute("treeData", JSONObject.fromObject(treeModel));
        String usertype = "";
        if (this.getCurrSup().getCompanyInfo() != null) {
            usertype = "com";

        } else if (this.getCurrSup().getSupCompanyInfo() != null) {
            usertype = "sup";
        }
        model.addAttribute("usertype", usertype);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany", isCompany);
        model.addAttribute("order_code", orderFrom);

        PurchaseOrderProduction exportPurchaseOrderProduction = (PurchaseOrderProduction) businApi.getQueryObject("from PurchaseOrderProduction where orderFromId = ? ", new Object[] { orderid });
        if (exportPurchaseOrderProduction != null) {
            List imContractDetail = businApi.getQueryList("select d.description,d.unit,d.priceVilidityPeriod,d.perPrice,d.remark from PurchaseOrderProductionDetail d   where d.detailNo = ?", new Object[] { exportPurchaseOrderProduction.getId() });
            model.addAttribute("imContractDetail", imContractDetail);
        }
        return "/sup/order/ccimcc/listCustomsDeAgreement";
    }

    /**
     * 跳转单据详情列表页面
     * 
     * @param id
     * @param hierarchy
     * @param taskTypeId
     * @return
     */
    @RequestMapping("/listCcFlowImccOrderDesc/{id}/{hierarchy}/{taskTypeId}")
    public String listCcFlowImccOrderDetail(@PathVariable String id, @PathVariable String hierarchy, @PathVariable String taskTypeId) {
        return taskBusin.OrderDispose(id, hierarchy, taskTypeId);
    }

    /**
     * 保存草稿后提交所有订单
     * 
     * @param taskId
     * @return
     */
    @RequestMapping("/submitOrderFrom")
    @ResponseBody
    public AjaxDomain submitOrderFrom(String taskId) {
        AjaxDomain ajaxDomain = new AjaxDomain();
        try {
            suporderFromBusin.submitOrderFrom(taskId);
            ajaxDomain.setStatusCode("200");
        } catch (Exception e) {
            ajaxDomain.setStatusCode("400");
            e.printStackTrace();
        }
        return ajaxDomain;
    }

    /**
     * 更具任务查询订单
     * 
     * @param id 任务id
     * @return
     */
    @RequestMapping("/listOrder/{id}")
    public String listOrder(@PathVariable String id, SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql = "select o.obj_id,o.order_num,f.flow_name,t.task_name,o.node_id,o.node_date,f.obj_id as ob ,o.order_code from PM_OrderFrom o " + " inner join PM_FlowType f on f.obj_id=o.tack_type_id" + " inner join PM_task t on t.obj_id=o.task_id " + " where o.task_id= ?";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/sup/order/orderfrom/listOrder";
    }
    /**
     * 客户点击首页的信息
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listStatusOrder")
    public String listStatusOrder(SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("SELECT o.obj_Id,o.obj_createDate,o.order_code,o.order_state,ft.flow_name,ci.com_Name ,suci.comName,o.node_date,o.node_state FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id  WHERE  t.user_id=?");
        
        if (!"88".equals(searchBean.getSearchName1())) {
            sql.append(" and order_state=?");
        }
        pageBean.addQuerySQL(sql.toString());
        pageBean.addParams(getCurrSup().getCompanyInfo().getId());
        if (!"88".equals(searchBean.getSearchName1())) {
            pageBean.addParams(searchBean.getSearchName1());
        }
        pageBean.addQueryStr("o.order_code,ft.flow_name,ci.com_Name ,suci.comName", searchBean.getSearchName2(), true);
        pageBean.addOrderBy("o.order_code", true);
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        return "/sup/order/orderfrom/listStatusOrder";
    }
    /**
     * 客户点击首页的信息
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listSupStatusOrder")
    public String listSupStatusOrder(SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("SELECT o.obj_Id,o.obj_createDate,o.order_code,o.order_state,ft.flow_name,ci.com_Name ,suci.comName,o.node_date,o.node_state FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id  WHERE o.order_state!=5 AND o.supplier=?");
        
        if (!"88".equals(searchBean.getSearchName1())) {
            sql.append(" and order_state=?");
        }
        pageBean.addQuerySQL(sql.toString());
        pageBean.addParams(getCurrSup().getSupCompanyInfo().getId());
        if (!"88".equals(searchBean.getSearchName1())) {
            pageBean.addParams(searchBean.getSearchName1());
        }
        pageBean.addQueryStr("o.order_code,ft.flow_name,ci.com_Name ,suci.comName", searchBean.getSearchName2(), true);
        pageBean.addOrderBy("o.order_code", true);
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        return "/sup/order/orderfrom/listSupStatusOrder";
    }
    
    /**
     * 首页订单数据点击查看详情进行跳转的页面
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping("/toPageDetailUrl/{orderFromId}")
    public String toPageDetailUrl(@PathVariable String orderFromId){
        StringBuffer url=new StringBuffer();
        SupCompanyInfo supCompanyInfo = this.getCurrSup().getSupCompanyInfo();
        StringBuffer isCompany=new StringBuffer();
        if (supCompanyInfo!=null) {
            isCompany.append("0");
        }else {
            isCompany.append("1");
        }
        OrderFrom orderFrom = suporderFromBusin.getOrderFrom(orderFromId);
        String flowId="";
        if (orderFrom!=null&&orderFrom.getFlowType()!=null) {
            url.append("redirect:");
            String typeId = orderFrom.getFlowType().getId();
            VocationalWorkEnum bgImcc = VocationalWorkEnum.BG_IMCC;
            VocationalWorkEnum bgExcc = VocationalWorkEnum.BG_EXCC;
            VocationalWorkEnum wmImcc = VocationalWorkEnum.WM_IMCC;
            VocationalWorkEnum wmExcc = VocationalWorkEnum.WM_EXCC;
            VocationalWorkEnum wlImcc = VocationalWorkEnum.WL_IMCC;
            VocationalWorkEnum wlExcc = VocationalWorkEnum.WL_EXCC;
            VocationalWorkEnum wlTransport = VocationalWorkEnum.WL_TRANSPORT;
            VocationalWorkEnum ccImcc = VocationalWorkEnum.CC_IMCC;
            VocationalWorkEnum ccExcc = VocationalWorkEnum.CC_EXCC;
            if (StringUtil.isEmpty(typeId, bgImcc.getId())) {
                flowId=bgImcc.getId();
                url.append("/sup/order/CustomsDeAgreementimcc/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, bgExcc.getId())) {
                flowId=bgExcc.getId();
                url.append("/sup/order/ExportCustomsDeAgreementexcc/viewExportCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, wmImcc.getId())) {
                flowId=wmImcc.getId();
                url.append("/sup/order/ic/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, wmExcc.getId())) {
                flowId=wmExcc.getId();
                url.append("/sup/order/ex/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, wlImcc.getId())) {
                flowId=wlImcc.getId();
                url.append("/sup/wuliuimcc/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, wlExcc.getId())) {
                flowId=wlExcc.getId();
                url.append("/sup/wuliuexcc/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, wlTransport.getId())) {
                flowId=wlTransport.getId();
                url.append("/sup/wuliutrop/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, ccImcc.getId())) {
                flowId=ccImcc.getId();
                url.append("/sup/order/OrderFrom/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, ccExcc.getId())) {
                flowId=ccExcc.getId();
                url.append("/sup/order/ExportOrderFrom/editExportPurchaseOrderProduction/");
            }
            url.append(orderFromId+"/0"+"/"+flowId+"/"+isCompany);
        }
        return url.toString();
    }
    @RequestMapping("/saveFlow/{orderFromId}/{typeId}")
    @ResponseBody
    public void saveFlow(@PathVariable String orderFromId,@PathVariable String typeId){
        try {
            suporderFromBusin.saveFlow(typeId,orderFromId);
        } catch (Exception e) {
        }
        writer("88");
    }
    
    /**
     * 客户加载所有未提交订单
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listNoSubmitOrder")
    public String listNoSubmitOrder(SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("SELECT o.obj_Id,o.obj_createDate,o.order_code,o.order_state,ft.flow_name,ci.com_Name ,suci.comName,o.node_date,o.node_state FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id  WHERE  t.user_id=?  and order_state='5'");
        pageBean.addQuerySQL(sql.toString());
        pageBean.addParams(getCurrSup().getCompanyInfo().getId());
        pageBean.addQueryStr("o.order_code,ft.flow_name,ci.com_Name ,suci.comName", searchBean.getSearchName2(), true);
        pageBean.addOrderBy("o.order_code", true);
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        return "/sup/order/orderfrom/listNoSubmitOrder";
    }
    /**
     * 服务商加载所有未接收订单
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listSupNoReceiveOrder")
    public String listNoReceiveOrder(SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("SELECT o.obj_Id,o.obj_createDate,o.order_code,o.order_state,ft.flow_name,ci.com_Name ,suci.comName,o.node_date,o.node_state FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id  WHERE  o.supplier=? and (o.order_state='13' or o.order_state='9') ");
        pageBean.addQuerySQL(sql.toString());
        pageBean.addParams(getCurrSup().getSupCompanyInfo().getId());
        pageBean.addQueryStr("o.order_code,ft.flow_name,ci.com_Name ,suci.comName", searchBean.getSearchName2(), true);
        pageBean.addOrderBy("o.order_code", true);
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        
        
        return "/sup/order/orderfrom/listSupNoReceiveOrder";
    }
    /**
     * 服务商接收订单
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @RequestMapping("/saveSubmitOrder")
    @ResponseBody
    public void saveSubmitOrder(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    suporderFromBusin.saveSubmitOrder(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    /**
     * 服务商接收订单
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @RequestMapping("/saveSupReceiveOrder")
    @ResponseBody
    public void saveSupReceiveOrder(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    suporderFromBusin.saveSupReceiveOrder(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
}