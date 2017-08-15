package com.sup.flow.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.WlFlowTransportNode;
import com.sup.flow.busin.WlFlowTransportBusin;

@Controller
@RequestMapping("/sup/flow/wltransport")
public class SupWlFlowTransportController extends SupBaseController{
    @Autowired
    private WlFlowTransportBusin wlFlowTransportBusin;
    
    
     

     /**
      * @Description 接单节点编辑回显信息
      * @author Chu Zhaocheng
      * @date 2017年6月13日 下午4:55:34
      * @action editWlFlowTransportNode
      * @return String
      */
     @RequestMapping("/editWlFlowTransportNode/{id}/{billId}")
     public String editWlFlowTransportNode(@PathVariable String id,@PathVariable String billId ) {
         WlFlowTransportNode wlFlowTransportNode = wlFlowTransportBusin.getWlFlowTransportNode(id);
         model.addAttribute("wlFlowTransportNode",wlFlowTransportNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wltransport/editWlFlowTransportNode";
     }
     
     /**
      * @Description 接单节点详情
      * @author Chu Zhaocheng
      * @date 2017年6月13日 下午4:56:55
      * @action viewWlFlowTransportNode
      * @return String
      */
     @RequestMapping("/viewWlFlowTransportNode/{id}/{billId}")
     public String viewWlFlowTransportNode(@PathVariable String id,@PathVariable String billId ) {
         WlFlowTransportNode wlFlowTransportNode = wlFlowTransportBusin.getWlFlowTransportNode(id);
         model.addAttribute("wlFlowTransportNode",wlFlowTransportNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wltransport/viewWlFlowTransportNode";
     }
     
     /**
      * @Description 接单节点添加物流运输信息
      * @author Chu Zhaocheng
      * @date 2017年6月13日 下午4:14:29
      * @action saveWlFlowTransportNode
      * @return String
      */
     @RequestMapping("/saveWlFlowTransportNode")
     public String saveWlFlowTransportNode(WlFlowTransportNode wlFlowTransportNode,String billId, String[] wlFlowTransportNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流运输", "接单");
         wlFlowTransportNode.setNodeId(nodeId);
         wlFlowTransportBusin.saveWlFlowTransportNode(wlFlowTransportNode,billId);
         frameworkBusin.saveUploadFileOwner(wlFlowTransportNode, wlFlowTransportNodeFiles);
         return "redirect:/sup/flow/wltransport/viewWlFlowTransportNode/"+wlFlowTransportNode.getId()+"/"+billId;
     }
     
     /**
      * @Description 删除接单节点明细
      * @author Chu Zhaocheng
      * @date 2017年6月13日 下午4:13:13
      * @action removeWlFlowTransportNode
      * @return void
      */
     @RequestMapping("/removeWlFlowTransportNode")
     @ResponseBody
     public void removeWlFlowTransportNode(String[] ids) {
         JSONArray jsonArray = new JSONArray();
         try {
             if (ids != null && ids.length > 0) {
                 for (String id : ids) {
                     wlFlowTransportBusin.removeWlFlowTransportNode(id);
                 }
             }
         } catch (Exception e) {
             addLogger(e);
         }
         writer(jsonArray.toString());
     }
     
    
     
     /**
      * @Description 运输节点信息回显
      * @author Chu Zhaocheng
      * @date 2017年6月13日 下午7:30:31
      * @action editFlowExccNodeCheck
      * @return String
      */
     @RequestMapping("/editWlFlowTransportNodeTransporting/{id}/{billId}")
     public String editWlFlowTransportNodeTransporting(@PathVariable String id,@PathVariable String billId ) {
         WlFlowTransportNode wlFlowTransportNode = wlFlowTransportBusin.getWlFlowTransportNode(id);
         model.addAttribute("wlFlowTransportNode",wlFlowTransportNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wltransport/editWlFlowTransportNodeTransporting";
     }
     
     /**
      * @Description 运输节点信息详情
      * @author Chu Zhaocheng
      * @date 2017年6月13日 下午8:04:30
      * @action viewFlowExccNodeCheck
      * @return String
      */
     @RequestMapping("/viewWlFlowTransportNodeTransporting/{id}/{billId}")
     public String viewWlFlowTransportNodeTransporting(@PathVariable String id,@PathVariable String billId ) {
         WlFlowTransportNode wlFlowTransportNode = wlFlowTransportBusin.getWlFlowTransportNode(id);
         model.addAttribute("wlFlowTransportNode",wlFlowTransportNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wltransport/viewWlFlowTransportNodeTransporting";
     }
     
     /**
      * @Description 运输节点添加物流运输信息
      * @author Chu Zhaocheng
      * @date 2017年6月13日 下午4:14:29
      * @action saveWlFlowTransportNodeTransporting
      * @return String
      */
     @RequestMapping("/saveWlFlowTransportNodeTransporting")
     public String saveWlFlowTransportNodeTransporting(WlFlowTransportNode wlFlowTransportNode,String billId, String[] wlFlowTransportNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流运输", "运输");
         wlFlowTransportNode.setNodeId(nodeId);
         wlFlowTransportBusin.saveWlFlowTransportNode(wlFlowTransportNode,billId);
         frameworkBusin.saveUploadFileOwner(wlFlowTransportNode, wlFlowTransportNodeFiles);
         return "redirect:/sup/flow/wltransport/viewWlFlowTransportNodeTransporting/"+wlFlowTransportNode.getId()+"/"+billId;
     }
    
     
     /**
      * @Description 到货签收节点信息回显
      * @author Chu Zhaocheng
      * @date 2017年6月13日 下午7:30:31
      * @action editWlFlowTransportNodeArrivalReceipt
      * @return String
      */
     @RequestMapping("/editWlFlowTransportNodeArrivalReceipt/{id}/{billId}")
     public String editWlFlowTransportNodeArrivalReceipt(@PathVariable String id,@PathVariable String billId ) {
         WlFlowTransportNode wlFlowTransportNode = wlFlowTransportBusin.getWlFlowTransportNode(id);
         model.addAttribute("wlFlowTransportNode",wlFlowTransportNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wltransport/editWlFlowTransportNodeArrivalReceipt";
     }
     
     /**
      * @Description 到货签收节点信息详情
      * @author Chu Zhaocheng
      * @date 2017年6月13日 下午8:04:30
      * @action viewFlowExccNodeCheck
      * @return String
      */
     @RequestMapping("/viewWlFlowTransportNodeArrivalReceipt/{id}/{billId}")
     public String viewWlFlowTransportNodeArrivalReceipt(@PathVariable String id,@PathVariable String billId ) {
         WlFlowTransportNode wlFlowTransportNode = wlFlowTransportBusin.getWlFlowTransportNode(id);
         model.addAttribute("wlFlowTransportNode",wlFlowTransportNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wltransport/viewWlFlowTransportNodeArrivalReceipt";
     }
     
     /**
      * @Description 到货签收节点添加物流运输信息
      * @author Chu Zhaocheng
      * @date 2017年6月13日 下午4:14:29
      * @action saveWlFlowTransportNodeArrivalReceipt
      * @return String
      */
     @RequestMapping("/saveWlFlowTransportNodeArrivalReceipt")
     public String saveWlFlowTransportNodeArrivalReceipt(WlFlowTransportNode wlFlowTransportNode,String billId, String[] wlFlowTransportNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流运输", "到货签收");
         wlFlowTransportNode.setNodeId(nodeId);
         wlFlowTransportBusin.saveWlFlowTransportNode(wlFlowTransportNode,billId);
         frameworkBusin.saveUploadFileOwner(wlFlowTransportNode, wlFlowTransportNodeFiles);
         return "redirect:/sup/flow/wltransport/viewWlFlowTransportNodeArrivalReceipt/"+wlFlowTransportNode.getId()+"/"+billId;
     }
     
}
