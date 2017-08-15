package com.sup.flow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.sysconfig.bean.EnumFlowNode;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.CcFlowImccNode;
import com.sup.flow.busin.CcFlowImccBusin;
@Controller
@RequestMapping("/sup/flow/ccimcc")
public class SupCcFlowImccController extends SupBaseController {
     
    @Autowired
    private CcFlowImccBusin ccFlowImccBusin;
    
    /**
     * @Description 接单节点编辑回显信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:55:34
     * @action editCcFlowImccNode
     * @return String
     */
    @RequestMapping("/editCcFlowImccNode/{id}/{billId}")
    public String editCcFlowImccNode(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/ccimcc/editCcFlowImccNode";
    }
    
    /**
     * @Description 接单节点详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:56:55
     * @action viewCcFlowImccNode
     * @return String
     */
    @RequestMapping("/viewCcFlowImccNode/{id}/{billId}")
    public String viewCcFlowImccNode(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/ccimcc/viewCcFlowImccNode";
    }
    
    /**
     * @Description 接单节点添加仓储入库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowImccNode
     * @return String
     */
    @RequestMapping("/saveCcFlowImccNode")
    public String saveCcFlowImccNode(CcFlowImccNode ccFlowImccNode,String billId,String[] ccFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储进库", "接单登记");
        ccFlowImccNode.setNodeId(nodeId);
        ccFlowImccBusin.saveCcFlowImccNode(ccFlowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowImccNode, ccFlowImccNodeFiles);
        return "redirect:/sup/flow/ccimcc/viewCcFlowImccNode/"+ccFlowImccNode.getId()+"/"+billId;
    }
    
    /**
     * @Description 车辆到达节点信息回显
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午7:30:31
     * @action editFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/editCcFlowImccNodeVehicleArrives/{id}/{billId}")
    public String editCcFlowImccNodeVehicleArrives(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/ccimcc/editCcFlowImccNodeVehicleArrives";
    }
    
    /**
     * @Description 车辆到达节点信息详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午8:04:30
     * @action viewFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/viewCcFlowImccNodeVehicleArrives/{id}/{billId}")
    public String viewCcFlowImccNodeVehicleArrives(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/ccimcc/viewCcFlowImccNodeVehicleArrives";
    }
    
    /**
     * @Description 车辆到达节点添加仓储入库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowImccNodeVehicleArrives
     * @return String
     */
    @RequestMapping("/saveCcFlowImccNodeVehicleArrives")
    public String saveCcFlowImccNodeVehicleArrives(CcFlowImccNode ccFlowImccNode,String billId, String[] ccFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储进库", "车辆到达");
        ccFlowImccNode.setNodeId(nodeId);
        ccFlowImccBusin.saveCcFlowImccNode(ccFlowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowImccNode, ccFlowImccNodeFiles);
        return "redirect:/sup/flow/ccimcc/viewCcFlowImccNodeVehicleArrives/"+ccFlowImccNode.getId()+"/"+billId;
    }
    
    /**
     * @Description 入库签收节点信息回显
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午7:30:31
     * @action editCcFlowImccNodeStoredSign
     * @return String
     */
    @RequestMapping("/editCcFlowImccNodeStoredSign/{id}/{billId}")
    public String editCcFlowImccNodeStoredSign(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/ccimcc/editCcFlowImccNodeStoredSign";
    }
    
    /**
     * @Description 入库签收节点信息详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午8:04:30
     * @action viewFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/viewCcFlowImccNodeStoredSign/{id}/{billId}")
    public String viewCcFlowImccNodeStoredSign(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/ccimcc/viewCcFlowImccNodeStoredSign";
    }
    
    /**
     * @Description 入库签收节点添加仓储入库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowImccNodeStoredSign
     * @return String
     */
    @RequestMapping("/saveCcFlowImccNodeStoredSign")
    public String saveCcFlowImccNodeStoredSign(CcFlowImccNode ccFlowImccNode,String billId, String[] ccFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储进库", "入库签收");
        ccFlowImccNode.setNodeId(nodeId);
        ccFlowImccBusin.saveCcFlowImccNode(ccFlowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowImccNode, ccFlowImccNodeFiles);
        return "redirect:/sup/flow/ccimcc/viewCcFlowImccNodeStoredSign/"+ccFlowImccNode.getId()+"/"+billId;
    }
    
}
