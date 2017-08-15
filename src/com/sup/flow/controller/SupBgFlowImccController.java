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
import com.sup.flow.bean.BgFlowImccNode;
import com.sup.flow.busin.BgFlowImccBusin;
/**
 * 客户,供应商权限的报关进口单据流程节点操作类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/sup/flow/bgimcc")
public class SupBgFlowImccController  extends SupBaseController{
    @Autowired
    private BgFlowImccBusin bgFlowImccBusin; 
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
        return "/sup/flow/bgimcc/viewFlowImccNode";
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
        return "/sup/flow/bgimcc/viewFlowImccNodeCheck";
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
        return "/sup/flow/bgimcc/viewFlowImccNodeGreenLight";
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
        return "/sup/flow/bgimcc/editFlowImccNodeGreenLight";
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
        return "/sup/flow/bgimcc/editFlowImccNodeCheck";
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
        return "/sup/flow/bgimcc/editFlowImccNode";
    }
    
    /**
     * 添加报关信息(放行)
     * @param flowImccNode
     * @param billId
     * @return
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/saveFlowImccNodeGreenLight")
    public String saveFlowImccNodeGreenLight(BgFlowImccNode flowImccNode,String billId, String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeid=enumFlowNode.flownode("通关进口", "放行");
        flowImccNode.setNodeId(nodeid);
        CompanyInfoUser user=getCurrSup();
        flowImccNode.setOperator(user.getId());
        flowImccNode.setOperatorName(user.getUserName());
        flowImccNode.setLastUpdate(new Date());
        flowImccNode.setFlowNote(1);
        flowImccNode.setFlag(0);
        bgFlowImccBusin.saveFlowImccNode(flowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowImccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgimcc/viewFlowImccNodeGreenLight/"+flowImccNode.getId()+"/"+billId;
    }
    
    /**
     * 添加报关信息(查验)
     * @param flowImccNode
     * @param billId
     * @return
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/saveFlowImccNodeCheck")
    public String saveFlowImccNodeCheck(BgFlowImccNode flowImccNode,String billId, String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeid=enumFlowNode.flownode("通关进口", "查验");
        flowImccNode.setNodeId(nodeid);
        CompanyInfoUser user=getCurrSup();
        flowImccNode.setOperator(user.getId());
        flowImccNode.setOperatorName(user.getUserName());
        flowImccNode.setLastUpdate(new Date());
        flowImccNode.setFlowNote(1);
        flowImccNode.setFlag(0);
        bgFlowImccBusin.saveFlowImccNode(flowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowImccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgimcc/viewFlowImccNodeCheck/"+flowImccNode.getId()+"/"+billId;
    }
    
    /**
     * 添加报关信息
     * @param flowImccNode
     * @param billId
     * @return
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/saveFlowImccNode")
    public String saveFlowImccNode(BgFlowImccNode flowImccNode,String billId, String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeid=enumFlowNode.flownode("通关进口", "接单");
        flowImccNode.setNodeId(nodeid);
        CompanyInfoUser user=getCurrSup();
        flowImccNode.setOperator(user.getId());
        flowImccNode.setOperatorName(user.getUserName());
        flowImccNode.setLastUpdate(new Date());
        flowImccNode.setFlowNote(1);
        flowImccNode.setFlag(0);
        bgFlowImccBusin.saveFlowImccNode(flowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowImccNode, flowImccNodeFiles);
        return "redirect:/sup/flow/bgimcc/viewFlowImccNode/"+flowImccNode.getId()+"/"+billId;
    }
    
    
}
