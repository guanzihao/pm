package com.sup.flow.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.CcFlowExccNode;
import com.sup.flow.busin.CcFlowExccBusin;
@Controller
@RequestMapping("/sup/flow/ccexcc")
public class SupCcFlowExccController extends SupBaseController{
    @Autowired
    private CcFlowExccBusin ccFlowExccBusin;

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
         return "/sup/flow/ccexcc/editCcFlowExccNode";
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
         return "/sup/flow/ccexcc/viewCcFlowExccNode";
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
         return "/sup/flow/ccexcc/editCcFlowExccNodeExport";
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
         return "/sup/flow/ccexcc/viewCcFlowExccNodeExport";
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
         return "/sup/flow/ccexcc/editCcFlowExccNodeSigning";
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
         return "/sup/flow/ccexcc/viewCcFlowExccNodeSigning";
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
     
}
