package com.sup.flow.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.BgFlowExccNode;
import com.sup.flow.busin.BgFlowExccBusin;
/**
 * 客户,供应商权限的报关出口单据流程节点操作类
 * @author litao
 *
 */
@Controller
@RequestMapping("/sup/flow/bgexcc")
public class SupBgFlowExccController extends SupBaseController{
    @Autowired
    private BgFlowExccBusin bgFlowExccBusin;
    
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
        return "/sup/flow/bgexcc/editFlowExccNode";
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
        return "/sup/flow/bgexcc/viewFlowExccNode";
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
        return "/sup/flow/bgexcc/editFlowExccNodeCheck";
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
        return "/sup/flow/bgexcc/viewFlowExccNodeCheck";
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
        return "/sup/flow/bgexcc/viewFlowExccNodeGreenLight";
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
        return "/sup/flow/bgexcc/editFlowExccNodeGreenLight";
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
        CompanyInfoUser user=getCurrSup();
        flowExccNode.setOperator(user.getId());
        flowExccNode.setOperatorName(user.getUserName());
        flowExccNode.setLastUpdate(new Date());
        flowExccNode.setFlowNode(1);
        flowExccNode.setFlag(0);
        bgFlowExccBusin.saveFlowExccNode(flowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowExccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgexcc/viewFlowExccNodeGreenLight/"+flowExccNode.getId()+"/"+billId;
    }
    
    /**
     * @Description 添加接单节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:52:44
     * @action saveFlowExccNode
     * @return String
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/saveFlowExccNode")
    public String saveFlowExccNode(BgFlowExccNode flowExccNode,String billId,String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeid=enumFlowNode.flownode("通关出口", "接单");
        flowExccNode.setNodeId(nodeid);
        CompanyInfoUser user=getCurrSup();
        flowExccNode.setOperator(user.getId());
        flowExccNode.setOperatorName(user.getUserName());
        flowExccNode.setLastUpdate(new Date());
        flowExccNode.setFlowNode(1);
        flowExccNode.setFlag(0);
        bgFlowExccBusin.saveFlowExccNode(flowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowExccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgexcc/viewFlowExccNode/"+flowExccNode.getId()+"/"+billId;
    }
    
    /**
     * @Description 添加查验节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:49:09
     * @action saveFlowExccNodeCheck
     * @return String
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/saveFlowExccNodeCheck")
    public String saveFlowExccNodeCheck(BgFlowExccNode flowExccNode,String billId,String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("通关出口", "查验");
        flowExccNode.setNodeId(nodeId);
        CompanyInfoUser user=getCurrSup();
        flowExccNode.setOperator(user.getId());
        flowExccNode.setOperatorName(user.getUserName());
        flowExccNode.setLastUpdate(new Date());
        flowExccNode.setFlowNode(1);
        flowExccNode.setFlag(0);
        bgFlowExccBusin.saveFlowExccNode(flowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowExccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgexcc/viewFlowExccNodeCheck/"+flowExccNode.getId()+"/"+billId;
    }
}
