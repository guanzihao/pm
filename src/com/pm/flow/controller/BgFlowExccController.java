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
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.model.TaskState;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.sup.flow.bean.BgFlowExccNode;
import com.sup.flow.busin.BgFlowExccBusin;

/**
 * @Description: 报关出口模块
 * @author Chu Zhaocheng
 * @date 2017年6月14日 上午8:40:21
 */
@Controller
@RequestMapping("/flow/bgexcc")
public class BgFlowExccController extends OrganizeBaseController{
    
   @Autowired
   private BgFlowExccBusin bgFlowExccBusin;
    
   
   /**
    * @Description 查看报关出口流程单据
    * @author Chu Zhaocheng
    * @date 2017年6月14日 上午8:41:28
    * @action listBgFlowExcc
    * @return String
    */
    @RequestMapping(value="/listBgFlowExcc/{billId}")
    public String listBgFlowExcc(@PathVariable String billId){
        String result="";
        String str = bgFlowExccBusin.getBgFlowExcc(billId);
        switch (str) {
            case "接单":
                result="redirect:/sup/flow/bgexcc/listBgFlowExccOrderReceiving/"+billId;
                break;
            case "查验":
                result="redirect:/sup/flow/bgexcc/listBgFlowExccCheck/"+billId;
                break;
            case "放行":
                result="redirect:/sup/flow/bgexcc/listBgFlowExccGreenLight/"+billId;
                break;
           }
        return result;
    }
    
    /**
     * @Description 接单节点列表查询
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:55:51
     * @action listBgFlowExccOrderReceiving
     * @return String
     */
    @RequestMapping(value="/listBgFlowExccOrderReceiving/{billId}")
    public String listBgFlowExccOrderReceiving(@PathVariable String billId,SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeid=enumFlowNode.flownode("通关出口", "接单");
        System.out.println("sssssssssssssssssssss"+billId);
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,a.startDate,a.endDate,a.status,a.oneAccessory,a.bussinessDate from BgFlowExccNode a where a.flag=0 and  a.billId=? and a.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeid);
        pageBean.addOrderBy("a.status,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/bgexcc/listBgFlowExccOrderReceiving";
    }
    
    /**
     * @Description 接单节点编辑回显
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:53:19
     * @action editFlowExccNode
     * @return String
     */
    @RequestMapping("/editFlowExccNode/{id}/{billId}")
    public String editFlowExccNode(@PathVariable String id,@PathVariable String billId ) {
        BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
        List<SupCompanyInfo> supplierList=bgFlowExccBusin.getSupplierList();
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/flow/bgexcc/editFlowExccNode";
    }
    
    /**
     * @Description 接单节点详情
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:54:45
     * @action viewFlowExccNode
     * @return String
     */
    @RequestMapping("/viewFlowExccNode/{id}/{billId}")
    public String viewFlowExccNode(@PathVariable String id,@PathVariable String billId ) {
        BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
        SupCompanyInfo firstSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getSecDept());
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/flow/bgexcc/viewFlowExccNode";
    }
    
    /**
     * @Description 添加接单节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:52:44
     * @action saveFlowExccNode
     * @return String
     */
    @RequestMapping("/saveFlowExccNode")
    public String saveFlowExccNode(BgFlowExccNode flowExccNode,String billId,String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeid=enumFlowNode.flownode("通关出口", "接单");
        flowExccNode.setNodeId(nodeid);
        UserAccount userAccount=getCurrUser();
        flowExccNode.setOperator(userAccount.getId());
        flowExccNode.setOperatorName(userAccount.getUserName());
        flowExccNode.setLastUpdate(new Date());
        flowExccNode.setFlowNode(1);
        flowExccNode.setFlag(0);
        bgFlowExccBusin.saveFlowExccNode(flowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowExccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgexcc/viewFlowExccNode/"+flowExccNode.getId()+"/"+billId;
    }
    
    /**
     * @Description 批量删除节点明细
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:51:39
     * @action removeFlowExccNode
     * @return void
     */
    @RequestMapping("/removeFlowExccNode")
    @ResponseBody
    public void removeFlowExccNode(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    bgFlowExccBusin.removeFlowExccNode(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

    
    /**
     * @Description 查验节点列表查询
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:50:48
     * @action listBgFlowExccCheck
     * @return String
     */
    @RequestMapping(value="/listBgFlowExccCheck/{billId}")
    public String listBgFlowExccCheck(@PathVariable String billId,SearchBean searchBean){
        
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("通关出口", "查验");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,a.startDate,a.endDate,a.status,a.oneAccessory,a.inspectionDate from BgFlowExccNode a where a.flag=0 and  a.billId=? and a.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.status,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/bgexcc/listBgFlowExccCheck";
    }
    
    /**
     * @Description 查验节点编辑回显
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:49:58
     * @action editFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/editFlowExccNodeCheck/{id}/{billId}")
    public String editFlowExccNodeCheck(@PathVariable String id,@PathVariable String billId ) {
        BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
        List<SupCompanyInfo> supplierList=bgFlowExccBusin.getSupplierList();
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/flow/bgexcc/editFlowExccNodeCheck";
    }
    
    /**
     * @Description 添加查验节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:49:09
     * @action saveFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/saveFlowExccNodeCheck")
    public String saveFlowExccNodeCheck(BgFlowExccNode flowExccNode,String billId,String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("通关出口", "查验");
        flowExccNode.setNodeId(nodeId);
        UserAccount userAccount=getCurrUser();
        flowExccNode.setOperator(userAccount.getId());
        flowExccNode.setOperatorName(userAccount.getUserName());
        flowExccNode.setLastUpdate(new Date());
        flowExccNode.setFlowNode(1);
        flowExccNode.setFlag(0);
        bgFlowExccBusin.saveFlowExccNode(flowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowExccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgexcc/viewFlowExccNodeCheck/"+flowExccNode.getId()+"/"+billId;
    }
    
    /**
     * @Description 查验节点详情
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:48:48
     * @action viewFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/viewFlowExccNodeCheck/{id}/{billId}")
    public String viewFlowExccNodeCheck(@PathVariable String id,@PathVariable String billId ) {
        BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
        SupCompanyInfo firstSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getSecDept());
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/flow/bgexcc/viewFlowExccNodeCheck";
    }
    
    
    /**
     * @Description 放行节点列表查询
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:47:37
     * @action listBgFlowExccGreenLight
     * @return String
     */
    @RequestMapping(value="/listBgFlowExccGreenLight/{billId}")
    public String listBgFlowExccGreenLight(@PathVariable String billId,SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("通关出口", "放行");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,a.startDate,a.endDate,a.status,a.oneAccessory,a.releaseDate from BgFlowExccNode a where a.flag=0 and  a.billId=? and a.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.status,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/bgexcc/listBgFlowExccGreenLight";
    }
    
    /**
     * @Description 放行节点编辑回显
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:46:56
     * @action editFlowExccNodeGreenLight
     * @return String
     */
    @RequestMapping("/editFlowExccNodeGreenLight/{id}/{billId}")
    public String editFlowExccNodeGreenLight(@PathVariable String id,@PathVariable String billId ) {
         BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
         List<SupCompanyInfo> supplierList=bgFlowExccBusin.getSupplierList();
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/flow/bgexcc/editFlowExccNodeGreenLight";
    }
    
    /**
     * @Description 添加放行节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:46:15
     * @action saveFlowExccNodeGreenLight
     * @return String
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/saveFlowExccNodeGreenLight")
    public String saveFlowExccNodeGreenLight(BgFlowExccNode flowExccNode, String billId,String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("通关出口", "放行");
        flowExccNode.setNodeId(nodeId);
        UserAccount userAccount=getCurrUser();
        flowExccNode.setOperator(userAccount.getId());
        flowExccNode.setOperatorName(userAccount.getUserName());
        flowExccNode.setLastUpdate(new Date());
        flowExccNode.setFlowNode(1);
        flowExccNode.setFlag(0);
        bgFlowExccBusin.saveFlowExccNode(flowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowExccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgexcc/viewFlowExccNodeGreenLight/"+flowExccNode.getId()+"/"+billId;
    }
    
    /**
     * @Description 放行节点详情
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:45:33
     * @action viewFlowExccNodeGreenLight
     * @return String
     */
    @RequestMapping("/viewFlowExccNodeGreenLight/{id}/{billId}")
    public String viewFlowExccNodeGreenLight(@PathVariable String id, @PathVariable String billId ) {
        BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
        SupCompanyInfo firstSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getSecDept());
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/flow/bgexcc/viewFlowExccNodeGreenLight";
    }
    /**
     * 跳转至报关出口订单页面
     * @return
     */
    @RequestMapping(value="/toBgFlowExccOrderPage/{companyid}")
    public String toBgFlowExccOrderPage(@PathVariable String companyid){
        model.addAttribute("flowId",VocationalWorkEnum.BG_EXCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("yjs", TaskState.FWSYJS);
        model.addAttribute("jjorder",TaskState.YZZ);
        model.addAttribute("companyid",companyid);
        return "/flow/bgexcc/toBgFlowExccOrder";
    }
    /**
     * 显示报关出口订单列表信息
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listBgFlowExccOrder/{nodeType}/{flowId}/{companyid}")
    public String showBgFlowExccOrder(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean,@PathVariable String companyid){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name \n");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id ");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id ");
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
        return "/flow/bgexcc/listBgFlowExccOrderDetail";
    }
    
}
