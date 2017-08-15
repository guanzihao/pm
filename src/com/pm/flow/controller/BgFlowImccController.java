package com.pm.flow.controller;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.model.BillNode;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.model.TaskState;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.task.bean.AjaxDomain;
import com.sup.flow.bean.BgFlowImccNode;
import com.sup.flow.busin.BgFlowImccBusin;
import com.sup.order.bean.OrderFrom;

@Controller
@RequestMapping("/flow/bgimcc")
public class BgFlowImccController extends OrganizeBaseController{
    
   @Autowired
   private BgFlowImccBusin bgFlowImccBusin;
    
    /**
     * 查看报关进口通关流程单据
     * @return
     */
    @RequestMapping(value="/listBgFlowImcc/{billId}")
    public String listBgFlowImcc(@PathVariable String billId){
        String result="";
        String str = bgFlowImccBusin.getBgFlowImcc(billId);
        System.err.println(str);
           switch (str) {
            case "接单":
                result="redirect:/sup/flow/bgimcc/listBgFlowImccOrderReceiving/"+billId;
                break;
            case "查验":
                result="redirect:/sup/flow/bgimcc/listBgFlowImccCheck/"+billId;
                break;
            case "放行":
                result="redirect:/sup/flow/bgimcc/listBgFlowImccGreenLight/"+billId;
                break;
           }
            return result;
        }
    /**
     * 查看报关进口通关流程单据(接单)
     * @return
     * nodeId 接单节点ID
     */
    @RequestMapping(value="/listBgFlowImccOrderReceiving/{billId}")
    public String listBgFlowImccOrderReceiving(@PathVariable String billId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select b.id,a.cselfNo,a.gross,a.items,a.customNo,a.flight,a.waybillNo,a.operatingUnit,a.itemName,b.bussinessDate from BgFlowImcc a,BgFlowImccNode b where a.taskId=b.taskId and b.flag=0 and  b.billId=? and b.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(BillNode.orderReceiving);
        pageBean.addOrderBy("b.status,b.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/bgimcc/listBgFlowImccOrderReceiving";
    }
    /**
     * 根据编号得到一个报关信息
     * @param id
     * @return
     */
    @RequestMapping("/editFlowImccNode/{id}/{billId}")
    public String editFlowImccNode(@PathVariable String id
            ,@PathVariable String billId ) {
         BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
         List<SupCompanyInfo> supplierList=bgFlowImccBusin.getSupplierList();
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/flow/bgimcc/editFlowImccNode";
    }
    /**
     * 接单详情页面
     * @param id
     * @return
     */
    @RequestMapping("/viewFlowImccNode/{id}/{billId}")
    public String viewFlowImccNode(@PathVariable String id
            ,@PathVariable String billId ) {
        BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
        SupCompanyInfo firstSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getSecDept());
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/flow/bgimcc/viewFlowImccNode";
    }
    
    /**
     * 添加报关信息
     * @param flowImccNode
     * @param billId
     * @return
     */
    @RequestMapping("/saveFlowImccNode")
    public String saveFlowImccNode(BgFlowImccNode flowImccNode,String billId, String[] flowImccNodeFiles){
        flowImccNode.setNodeId(BillNode.orderReceiving);
        UserAccount userAccount=getCurrUser();
        flowImccNode.setOperator(userAccount.getId());
        flowImccNode.setOperatorName(userAccount.getUserName());
        flowImccNode.setLastUpdate(new Date());
        flowImccNode.setFlowNote(1);
        flowImccNode.setFlag(0);
        bgFlowImccBusin.saveFlowImccNode(flowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowImccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgimcc/viewFlowImccNode/"+flowImccNode.getId()+"/"+billId;
    }
    
    /**
     * 删除节点明细
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @RequestMapping("/removeFlowImccNode")
    @ResponseBody
    public void removeFlowImccNode(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    bgFlowImccBusin.removeFlowImccNode(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    /**
     *  查看报关进口通关流程单据(查验)
     * @return
     */
    @RequestMapping(value="/listBgFlowImccCheck/{billId}")
    public String listBgFlowImccCheck(@PathVariable String billId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select b.id,a.cselfNo,a.gross,a.items,a.customNo,a.flight,a.waybillNo,a.operatingUnit,a.itemName,b.inspectionDate from BgFlowImcc a,BgFlowImccNode b where a.taskId=b.taskId and  b.flag=0 and   b.billId=? and b.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(BillNode.orderCheck);
        pageBean.addOrderBy("b.status,b.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/bgimcc/listBgFlowImccCheck";
    }
    
    /**
     * 根据编号得到一个报关信息(查验)
     * @param id
     * @return
     */
    @RequestMapping("/editFlowImccNodeCheck/{id}/{billId}")
    public String editFlowImccNodeCheck(@PathVariable String id
            ,@PathVariable String billId ) {
         BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
         List<SupCompanyInfo> supplierList=bgFlowImccBusin.getSupplierList();
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/flow/bgimcc/editFlowImccNodeCheck";
    }
    
    /**
     * 添加报关信息(查验)
     * @param flowImccNode
     * @param billId
     * @return
     */
    @RequestMapping("/saveFlowImccNodeCheck")
    public String saveFlowImccNodeCheck(BgFlowImccNode flowImccNode,String billId, String[] flowImccNodeFiles){
        flowImccNode.setNodeId(BillNode.orderCheck);
        UserAccount userAccount=getCurrUser();
        flowImccNode.setOperator(userAccount.getId());
        flowImccNode.setOperatorName(userAccount.getUserName());
        flowImccNode.setLastUpdate(new Date());
        flowImccNode.setFlowNote(1);
        flowImccNode.setFlag(0);
        bgFlowImccBusin.saveFlowImccNode(flowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowImccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgimcc/viewFlowImccNodeCheck/"+flowImccNode.getId()+"/"+billId;
    }
    
    /**
     * 详情页面(查验)
     * @param id
     * @return
     */
    @RequestMapping("/viewFlowImccNodeCheck/{id}/{billId}")
    public String viewFlowImccNodeCheck(@PathVariable String id
            ,@PathVariable String billId ) {
        BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
        SupCompanyInfo firstSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getSecDept());
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/flow/bgimcc/viewFlowImccNodeCheck";
    }
    
    /**
     *  查看报关进口通关流程单据(放行)
     * @return
     */
    @RequestMapping(value="/listBgFlowImccGreenLight/{billId}")
    public String listBgFlowImccGreenLight(@PathVariable String billId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select b.id,a.cselfNo,a.gross,a.items,a.customNo,a.flight,a.waybillNo,a.operatingUnit,a.itemName,b.releaseDate from BgFlowImcc a,BgFlowImccNode b where a.taskId=b.taskId  and b.flag=0and  b.billId=? and b.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(BillNode.orderGreenLight);
        pageBean.addOrderBy("b.status,b.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/bgimcc/listBgFlowImccGreenLight";
    }
    /**
     * 根据编号得到一个报关信息(放行)
     * @param id
     * @return
     */
    @RequestMapping("/editFlowImccNodeGreenLight/{id}/{billId}")
    public String editFlowImccNodeGreenLight(@PathVariable String id
            ,@PathVariable String billId ) {
         BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
         List<SupCompanyInfo> supplierList=bgFlowImccBusin.getSupplierList();
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/flow/bgimcc/editFlowImccNodeGreenLight";
    }
    
    /**
     * 添加报关信息(放行)
     * @param flowImccNode
     * @param billId
     * @return
     */
    @RequestMapping("/saveFlowImccNodeGreenLight")
    public String saveFlowImccNodeGreenLight(BgFlowImccNode flowImccNode,String billId, String[] flowImccNodeFiles){
        flowImccNode.setNodeId(BillNode.orderGreenLight);
        UserAccount userAccount=getCurrUser();
        flowImccNode.setOperator(userAccount.getId());
        flowImccNode.setOperatorName(userAccount.getUserName());
        flowImccNode.setLastUpdate(new Date());
        flowImccNode.setFlowNote(1);
        flowImccNode.setFlag(0);
        bgFlowImccBusin.saveFlowImccNode(flowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowImccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgimcc/viewFlowImccNodeGreenLight/"+flowImccNode.getId()+"/"+billId;
    }
    
    /**
     * 详情页面(放行)
     * @param id
     * @return
     */
    @RequestMapping("/viewFlowImccNodeGreenLight/{id}/{billId}")
    public String viewFlowImccNodeGreenLight(@PathVariable String id
            ,@PathVariable String billId ) {
        BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
        SupCompanyInfo firstSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getSecDept());
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/flow/bgimcc/viewFlowImccNodeGreenLight";
    }
    /**
     * 跳转至进口报关订单页面
     * @return
     */
    @RequestMapping(value="/toFlowImccPage/{companyid}")
    public String toImccOrderPage(@PathVariable String companyid){
         model.addAttribute("flowId",VocationalWorkEnum.BG_IMCC.getId());
         model.addAttribute("complete",TaskState.YWC);
         model.addAttribute("handle",TaskState.CLZ);
         model.addAttribute("cansole",TaskState.QX);
         model.addAttribute("yjs", TaskState.FWSYJS);
         model.addAttribute("jjorder",TaskState.YZZ);
         model.addAttribute("companyid",companyid);
        return "/flow/bgimcc/FlowImccOrder";
    }
    /**
     * 查询进口报关订单列表
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listFlowImccOrder/{nodeType}/{flowId}/{companyid}")
    public String showFlowImccOrder(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean,@PathVariable String companyid){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name \n");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id");
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
        model.addAttribute("flowId",flowId);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("searchBean",searchBean);
        return "/flow/bgimcc/listFlowImccOrder";
    }
    
    /**
     * 客服拒绝请求
     * @param id 订单ID
     * @param type 订单类型
     * @param nodeType 。。
     * @param flowId  。。
     * @param isCompany 。。
     * @return
     */
    @RequestMapping("/supupdateorder")
    @ResponseBody
    public AjaxDomain supupdatestateorder(String id) {
        AjaxDomain ajaxDomain=new AjaxDomain();
        try {
            if (id != null ) {
                OrderFrom orderFrom =(OrderFrom) businApi.get(OrderFrom.class, id);
                if (orderFrom != null ) {
                    orderFrom.setOrderState(TaskState.YZZ);
                    businApi.save(orderFrom);
                }
            }
            ajaxDomain.setStatusCode("200");
        } catch (Exception e) {
            ajaxDomain.setStatusCode("400");
        }
        return ajaxDomain;
     }
    
}
