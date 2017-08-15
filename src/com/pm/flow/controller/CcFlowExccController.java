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
import com.sup.flow.bean.CcFlowExccNode;
import com.sup.flow.busin.CcFlowExccBusin;

/**
 * @Description: 仓库出库模块
 * @author Chu Zhaocheng
 * @date 2017年6月13日 下午4:19:20
 */
@Controller
@RequestMapping("/flow/ccexcc")
public class CcFlowExccController extends OrganizeBaseController{
    
   @Autowired
   private CcFlowExccBusin ccFlowExccBusin;
   
    /**
     * @Description 查看仓储出库流程单据
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:20:10
     * @action listCcFlowExcc
     * @return String
     */
    @RequestMapping(value="/listCcFlowExcc/{billId}")
    public String listCcFlowExcc(@PathVariable String billId){
        String result="";
        String str = ccFlowExccBusin.getCcFlowExcc(billId);
           switch (str) {
            case "接单登记":
                result="redirect:/sup/flow/ccexcc/listCcFlowExccOrderReceiving/" + billId;
                break;
            case "出库":
                result="redirect:/sup/flow/ccexcc/listCcFlowExccExport/" + billId;
                break;
            case "签收":
                result="redirect:/sup/flow/ccexcc/listCcFlowExccSigning/" + billId;
                break;
           }
            return result;
        }
    
    /**
     * @Description 接单节点查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:05:11
     * @action listCcFlowExccOrderReceiving
     * @return String
     */
    @RequestMapping(value="/listCcFlowExccOrderReceiving/{billId}")
    public String listCcFlowExccOrderReceiving(@PathVariable String billId,SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储出库", "接单登记");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.orderReceivingDate FROM CcFlowExccNode a where a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/ccexcc/listCcFlowExccOrderReceiving";
    }

    /**
     * @Description 接单节点编辑回显信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:55:34
     * @action editCcFlowExccNode
     * @return String
     */
    @RequestMapping("/editCcFlowExccNode/{id}/{billId}")
    public String editCcFlowExccNode(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/ccexcc/editCcFlowExccNode";
    }
    
    /**
     * @Description 接单节点详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:56:55
     * @action viewCcFlowExccNode
     * @return String
     */
    @RequestMapping("/viewCcFlowExccNode/{id}/{billId}")
    public String viewCcFlowExccNode(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/ccexcc/viewCcFlowExccNode";
    }
    
    /**
     * @Description 接单节点添加仓储出库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowExccNode
     * @return String
     */
    @RequestMapping("/saveCcFlowExccNode")
    public String saveCcFlowExccNode(CcFlowExccNode ccFlowExccNode,String billId, String[] ccFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储出库", "接单登记");
        ccFlowExccNode.setNodeId(nodeId);
        ccFlowExccBusin.saveCcFlowExccNode(ccFlowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowExccNode, ccFlowExccNodeFiles);
        return "redirect:/sup/flow/ccexcc/viewCcFlowExccNode/" + ccFlowExccNode.getId() + "/" + billId;
    }
    
    /**
     * @Description 删除接单节点明细
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:13:13
     * @action removeCcFlowExccNode
     * @return void
     */
    @RequestMapping("/removeCcFlowExccNode")
    @ResponseBody
    public void removeCcFlowExccNode(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    ccFlowExccBusin.removeCcFlowExccNode(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    
    /**
     * @Description 出库节点信息查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:59:52
     * @action listCcFlowExccExport
     * @return String
     */
    @RequestMapping(value="/listCcFlowExccExport/{billId}")
    public String listCcFlowExccExport(@PathVariable String billId,SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储出库", "出库");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.deliveryAddr,a.driverSignDate FROM CcFlowExccNode a where a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/ccexcc/listCcFlowExccExport";
    }
    
    /**
     * @Description 出库节点信息回显
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午7:30:31
     * @action editCcFlowExccNodeExport
     * @return String
     */
    @RequestMapping("/editCcFlowExccNodeExport/{id}/{billId}")
    public String editCcFlowExccNodeExport(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/ccexcc/editCcFlowExccNodeExport";
    }
    
    /**
     * @Description 出库节点信息详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午8:04:30
     * @action viewFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/viewCcFlowExccNodeExport/{id}/{billId}")
    public String viewCcFlowExccNodeExport(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/ccexcc/viewCcFlowExccNodeExport";
    }
    
    /**
     * @Description 出库节点添加仓储出库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowExccNodeExport
     * @return String
     */
    @RequestMapping("/saveCcFlowExccNodeExport")
    public String saveCcFlowExccNodeExport(CcFlowExccNode ccFlowExccNode,String billId, String[] ccFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储出库", "出库");
        ccFlowExccNode.setNodeId(nodeId);
        
        ccFlowExccBusin.saveCcFlowExccNode(ccFlowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowExccNode, ccFlowExccNodeFiles);
        return "redirect:/sup/flow/ccexcc/viewCcFlowExccNodeExport/" + ccFlowExccNode.getId() + "/" + billId;
    }
    
    
    /**
     * @Description 签收节点信息查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:59:52
     * @action listCcFlowExccSigning
     * @return String
     */
    @RequestMapping(value="/listCcFlowExccSigning/{billId}")
    public String listCcFlowExccSigning(@PathVariable String billId,SearchBean searchBean){
        String nodeId="73E3A3CC-BB8B-44CD-9E52-1424E8E57900";
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.customerSignDate FROM CcFlowExccNode a where a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/ccexcc/listCcFlowExccSigning";
    }
    
    /**
     * @Description 签收节点信息回显
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午7:30:31
     * @action editCcFlowExccNodeSigning
     * @return String
     */
    @RequestMapping("/editCcFlowExccNodeSigning/{id}/{billId}")
    public String editCcFlowExccNodeSigning(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/ccexcc/editCcFlowExccNodeSigning";
    }
    
    /**
     * @Description 签收节点信息详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午8:04:30
     * @action viewFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/viewCcFlowExccNodeSigning/{id}/{billId}")
    public String viewCcFlowExccNodeSigning(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/flow/ccexcc/viewCcFlowExccNodeSigning";
    }
    
    /**
     * @Description 签收节点添加仓储出库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowExccNodeSigning
     * @return String
     */
    @RequestMapping("/saveCcFlowExccNodeSigning")
    public String saveCcFlowExccNodeSigning(CcFlowExccNode ccFlowExccNode,String billId, String[] ccFlowExccNodeFiles){
        ccFlowExccNode.setNodeId("73E3A3CC-BB8B-44CD-9E52-1424E8E57900");
        ccFlowExccBusin.saveCcFlowExccNode(ccFlowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowExccNode, ccFlowExccNodeFiles);
        return "redirect:/sup/flow/ccexcc/viewCcFlowExccNodeSigning/" + ccFlowExccNode.getId() + "/" + billId;
    }
    /**
     * 跳转至can订单页面
     * @return
     */
    @RequestMapping("/toCCFlowExccOrder/{companyid}")
    public String toCcFlowExccOrderPage(@PathVariable String companyid){
        model.addAttribute("flowId",VocationalWorkEnum.CC_EXCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("yjs", TaskState.FWSYJS);
        model.addAttribute("jjorder",TaskState.YZZ);
        return "/flow/ccexcc/listCcFlowOrder";
    }
    /**
     * 显示仓储出口订单列表信息
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/showCcFlowExccOrderList/{nodeType}/{flowId}/{companyid}")
    public String showCcFlowExccOrderList(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean,@PathVariable String companyid){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name \n");
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
        model.addAttribute("flowId",flowId);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("searchBean",searchBean);
        return "/flow/ccexcc/listCcFlowOrderDetail";
        
    }
}
