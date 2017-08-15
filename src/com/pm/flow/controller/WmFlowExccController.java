package com.pm.flow.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.model.TaskState;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.sup.flow.bean.WmFlowExccNode;
import com.sup.flow.busin.WmFlowExccBusin;

/**
 * @Description: 外贸出口模块
 * @author Chu Zhaocheng
 * @date 2017年6月20日 下午5:46:27
 */

@Controller
@RequestMapping("/flow/wmexcc")
public class WmFlowExccController extends OrganizeBaseController{
    @Autowired
    private WmFlowExccBusin wmFlowExccBusin;
    
    /**
     * @Description 查看外贸出口流程单据
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:48:18
     * @action listWmFlowExcc
     * @return String
     */
    @RequestMapping(value="/listWmFlowExcc/{billId}")
    public String listWmFlowExcc(@PathVariable String billId){
        String result="";
        String str = wmFlowExccBusin.getWmFlowExcc(billId);
        switch (str) {
            case "单证制作":
                result="redirect:/sup/flow/wmexcc/listDocsProduce/" + billId;
                break;
            case "信用证开证":
                result="redirect:/sup/flow/wmexcc/listCreditCard/" + billId;
                break;
            case "收汇":
                result="redirect:/sup/flow/wmexcc/listExchangeEarnings/" + billId;
                break;
            case "出口清关":
                result="redirect:/sup/flow/wmexcc/listExportCleaning/" + billId;
                break;
            case "结算开票":
                result="redirect:/sup/flow/wmexcc/listSettlementInvoice/" + billId;
                break;
            case "退税申请":
                result="redirect:/sup/flow/wmexcc/listTaxRefund/" + billId;
                break;
        }
        return result;
    }
    
    
    /**
     * @Description 单证制作列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:57:30
     * @action listDocsProduce
     * @return String
     */
    @RequestMapping(value="/listDocsProduce/{billId}")
    public String listDocsProduce(@PathVariable String billId, SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "单证制作");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.contractStartDate FROM WmFlowExccNode a where a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/listDocsProduce";
    }
    
    /**
     * @Description 编辑单证制作信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:39:06
     * @action editDocsProduce
     * @return String
     */
    @RequestMapping("/editDocsProduce/{id}/{billId}")
    public String editDocsProduce(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/editDocsProduce";
    }
    
    /**
     * @Description 添加单证制作信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveDocsProduce
     * @return String
     */
    @RequestMapping("/saveDocsProduce")
    public String saveDocsProduce(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "单证制作");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewDocsProduce/" + wmFlowExccNode.getId();
    }
    
    /**
     * @Description 单证制作详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewDocsProduce
     * @return String
     */
    @RequestMapping("/viewDocsProduce/{id}")
    public String viewDocsProduce(@PathVariable String id) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/flow/wmexcc/viewDocsProduce";
    }
    
    /**
     * @Description 删除外贸出口流程节点
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:52:41
     * @action removeWmFlowExccNode
     * @return void
     */
    @RequestMapping("/removeWmFlowExccNode")
    @ResponseBody
    public void removeWmFlowExccNode(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    wmFlowExccBusin.removeWmFlowExccNode(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    
    /**
     * @Description 信用证开证列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listCreditCard
     * @return String
     */
    @RequestMapping(value="/listCreditCard/{billId}")
    public String listCreditCard(@PathVariable String billId,SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "信用证开证");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.lcStartDate FROM WmFlowExccNode a where a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/listCreditCard";
    }
    
    /**
     * 编辑信用证开证信息
     * @param id
     * @return
     */
    @RequestMapping("/editCreditCard/{id}/{billId}")
    public String editCreditCard(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/editCreditCard";
    }
    
    /**
     * @Description 添加信用证开证信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveCreditCard
     * @return String
     */
    @RequestMapping("/saveCreditCard")
    public String saveCreditCard(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "信用证开证");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewCreditCard/" + wmFlowExccNode.getId();
    }
    
    /**
     * @Description 信用证开证详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewCreditCard
     * @return String
     */
    @RequestMapping("/viewCreditCard/{id}")
    public String viewCreditCard(@PathVariable String id) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/flow/wmexcc/viewCreditCard";
    }

    
    /**
     * @Description 收汇列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listExchangeEarnings
     * @return String
     */
    @RequestMapping(value="/listExchangeEarnings/{billId}")
    public String listExchangeEarnings(@PathVariable String billId,SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "收汇");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.receiveDate,a.recAmount,a.recCurrency FROM WmFlowExccNode a where a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/listExchangeEarnings";
    }
    
    /**
     * @Description 编辑收汇信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:37
     * @action editExchangeEarnings
     * @return String
     */
    @RequestMapping("/editExchangeEarnings/{id}/{billId}")
    public String editExchangeEarnings(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/editExchangeEarnings";
    }
    
    /**
     * @Description 添加收汇信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveExchangeEarnings
     * @return String
     */
    @RequestMapping("/saveExchangeEarnings")
    public String saveExchangeEarnings(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "收汇");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewExchangeEarnings/" + wmFlowExccNode.getId();
    }
    
    /**
     * @Description 收汇详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewExchangeEarnings
     * @return String
     */
    @RequestMapping("/viewExchangeEarnings/{id}")
    public String viewExchangeEarnings(@PathVariable String id) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/flow/wmexcc/viewExchangeEarnings";
    }
    
    
    /**
     * @Description 出口清关列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listExportCleaning
     * @return String
     */
    @RequestMapping(value="/listExportCleaning/{billId}")
    public String listExportCleaning(@PathVariable String billId,SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "出口清关");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.wayBillDate FROM WmFlowExccNode a where a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/listExportCleaning";
    }
    
    /**
     * @Description 编辑出口清关信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:21
     * @action editExportCleaning
     * @return String
     */
    @RequestMapping("/editExportCleaning/{id}/{billId}")
    public String editExportCleaning(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/editExportCleaning";
    }
    
    /**
     * @Description 添加出口清关信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveExportCleaning
     * @return String
     */
    @RequestMapping("/saveExportCleaning")
    public String saveExportCleaning(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "出口清关");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewExportCleaning/" + wmFlowExccNode.getId();
    }
    
    /**
     * @Description 出口清关详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewExportCleaning
     * @return String
     */
    @RequestMapping("/viewExportCleaning/{id}")
    public String viewExportCleaning(@PathVariable String id) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/flow/wmexcc/viewExportCleaning";
    }
    
    
    /**
     * @Description 结算开票列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listSettlementInvoice
     * @return String
     */
    @RequestMapping(value="/listSettlementInvoice/{billId}")
    public String listSettlementInvoice(@PathVariable String billId,SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "结算开票");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.balanceDate FROM WmFlowExccNode a where a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/listSettlementInvoice";
    }
    
    /**
     * @Description 编辑结算开票信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:21
     * @action editSettlementInvoice
     * @return String
     */
    @RequestMapping("/editSettlementInvoice/{id}/{billId}")
    public String editSettlementInvoice(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/editSettlementInvoice";
    }
    
    /**
     * @Description 添加结算开票信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveSettlementInvoice
     * @return String
     */
    @RequestMapping("/saveSettlementInvoice")
    public String saveSettlementInvoice(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "结算开票");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewSettlementInvoice/" + wmFlowExccNode.getId();
    }
    
    /**
     * @Description 结算开票详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewSettlementInvoice
     * @return String
     */
    @RequestMapping("/viewSettlementInvoice/{id}")
    public String viewSettlementInvoice(@PathVariable String id) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/flow/wmexcc/viewSettlementInvoice";
    }
    
    
    /**
     * @Description 退税申请列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listTaxRefund
     * @return String
     */
    @RequestMapping(value="/listTaxRefund/{billId}")
    public String listTaxRefund(@PathVariable String billId,SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "退税申请");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.refundTime FROM WmFlowExccNode a where a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/listTaxRefund";
    }
    
    /**
     * @Description 编辑退税申请信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:21
     * @action editTaxRefund
     * @return String
     */
    @RequestMapping("/editTaxRefund/{id}/{billId}")
    public String editTaxRefund(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/wmexcc/editTaxRefund";
    }
    
    /**
     * @Description 添加退税申请信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveTaxRefund
     * @return String
     */
    @RequestMapping("/saveTaxRefund")
    public String saveTaxRefund(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "退税申请");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewTaxRefund/" + wmFlowExccNode.getId();
    }
    
    /**
     * @Description 退税申请详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewTaxRefund
     * @return String
     */
    @RequestMapping("/viewTaxRefund/{id}")
    public String viewTaxRefund(@PathVariable String id) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/flow/wmexcc/viewTaxRefund";
    }
    /**
     * 跳转至外贸出口订单页面
     * @return
     */
    @RequestMapping("/toWmFlowExccOrderPage/{companyid}")
    public  String toWmFlowExccOrderPage(@PathVariable String companyid){
        model.addAttribute("flowId",VocationalWorkEnum.WM_EXCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("yjs", TaskState.FWSYJS);
        model.addAttribute("jjorder",TaskState.YZZ);
        model.addAttribute("companyid",companyid);
        return "flow/wmexcc/listWmFlowExccOrder";
    }
    /**
     * 显示外贸出口订单列表信息
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/showWmFlowExccOrder/{nodeType}/{flowId}/{companyid}")
    public String showWmFlowExccOrder(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean,@PathVariable String companyid){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,\n");
        sql.append(" o.node4_state,o.node4_desc,o.node5_state,o.node5_desc ");
        sql.append(" ,o.node6_state,o.node6_desc,o.node7_state,o.node7_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name ");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id \n");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        if(companyid.equals("0")){
            sql.append(" where   o.order_state not in(?,?) and b.flow_name=?\n");
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" where  task.user_id=?  and ");
            pageBean.addParams(companyid);
            sql.append("    o.order_state not in(?,?) and b.flow_name=?\n");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            sql.append(" and o.obj_createDate>=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            sql.append(" and o.obj_createDate<=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName2())){
            sql.append(" and ci.com_Name like'%"+searchBean.getSearchName2()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName3())){
            sql.append(" and sup.comName like'%"+searchBean.getSearchName3()+"%'");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        pageBean.addParams(TaskState.CG);
        pageBean.addParams(TaskState.WTJ);
        VocationalWorkEnum enums=VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            pageBean.addParams(searchBean.getSearchDate2()+" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("searchBean",searchBean);
        return "/flow/wmexcc/listWmFlowExccOrderDetail";
    }
    
    
}
