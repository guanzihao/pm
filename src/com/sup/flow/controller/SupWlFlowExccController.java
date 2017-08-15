package com.sup.flow.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.WlFlowExccNode;
import com.sup.flow.busin.WlFlowExccBusin;

@Controller
@RequestMapping("/sup/flow/wlexcc")
public class SupWlFlowExccController extends SupBaseController {
    @Autowired
    private WlFlowExccBusin wlFlowExccBusin;
    
     
     /**
      * @Description 编辑接单节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午10:56:08
      * @action editOrderReceiving
      * @return String
      */
     @RequestMapping("/editOrderReceiving/{id}/{billId}")
     public String editOrderReceiving(@PathVariable String id, @PathVariable String billId) {
         WlFlowExccNode wlFlowExccNode = wlFlowExccBusin.getWlFlowExccNode(id);
         model.addAttribute("wlFlowExccNode", wlFlowExccNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wlexcc/editOrderReceiving";
     }
     
     /**
      * @Description 接单节点详情信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午10:58:04
      * @action viewOrderReceiving
      * @return String
      */
     @RequestMapping("/viewOrderReceiving/{id}")
     public String viewOrderReceiving(@PathVariable String id) {
         WlFlowExccNode wlFlowExccNode = wlFlowExccBusin.getWlFlowExccNode(id);
         model.addAttribute("wlFlowExccNode",wlFlowExccNode);
         return "/sup/flow/wlexcc/viewOrderReceiving";
     }
     
     /**
      * @Description 添加接单节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:03:36
      * @action saveOrderReceiving
      * @return String
      */
     @RequestMapping("/saveOrderReceiving")
     public String saveOrderReceiving(WlFlowExccNode wlFlowExccNode, String billId, String[] wlFlowExccNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流出口", "接单");
         wlFlowExccBusin.saveWlFlowExccNode(wlFlowExccNode, billId, nodeId);
         frameworkBusin.saveUploadFileOwner(wlFlowExccNode, wlFlowExccNodeFiles);
         return "redirect:/sup/flow/wlexcc/viewOrderReceiving/" + wlFlowExccNode.getId();
     }
     
     /**
      * @Description 删除物流出口节点
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:05:22
      * @action removeWlFlowExccNode
      * @return void
      */
     @RequestMapping("/removeWlFlowExccNode")
     @ResponseBody
     public void removeWlFlowExccNode(String[] ids) {
         JSONArray jsonArray = new JSONArray();
         try {
             if (ids != null && ids.length > 0) {
                 for (String id : ids) {
                     wlFlowExccBusin.removeWlFlowExccNode(id);
                 }
             }
         } catch (Exception e) {
             addLogger(e);
         }
         writer(jsonArray.toString());
     }
     
     
     
     
     /**
      * @Description 编辑订舱节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:31:18
      * @action editBookingSpace
      * @return String
      */
     @RequestMapping("/editBookingSpace/{id}/{billId}")
     public String editBookingSpace(@PathVariable String id, @PathVariable String billId) {
         WlFlowExccNode wlFlowExccNode = wlFlowExccBusin.getWlFlowExccNode(id);
         model.addAttribute("wlFlowExccNode", wlFlowExccNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wlexcc/editBookingSpace";
     }
     
     /**
      * @Description 添加订舱节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:34:42
      * @action saveBookingSpace
      * @return String
      */
     @RequestMapping("/saveBookingSpace")
     public String saveBookingSpace(WlFlowExccNode wlFlowExccNode, String billId, String[] wlFlowExccNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流出口", "订舱");
         wlFlowExccBusin.saveWlFlowExccNode(wlFlowExccNode, billId, nodeId);
         frameworkBusin.saveUploadFileOwner(wlFlowExccNode, wlFlowExccNodeFiles);
         return "redirect:/sup/flow/wlexcc/viewBookingSpace/" + wlFlowExccNode.getId();
     }
     
     /**
      * @Description 订舱节点详情信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:39:07
      * @action viewBookingSpace
      * @return String
      */
     @RequestMapping("/viewBookingSpace/{id}")
     public String viewBookingSpace(@PathVariable String id) {
         WlFlowExccNode wlFlowExccNode = wlFlowExccBusin.getWlFlowExccNode(id);
         model.addAttribute("wlFlowExccNode", wlFlowExccNode);
         return "/sup/flow/wlexcc/viewBookingSpace";
     }
     
     
     
     
     /**
      * @Description 编辑做箱/装货信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:44:39
      * @action editCargoHandling
      * @return String
      */
     @RequestMapping("/editCargoHandling/{id}/{billId}")
     public String editCargoHandling(@PathVariable String id, @PathVariable String billId) {
         WlFlowExccNode wlFlowExccNode = wlFlowExccBusin.getWlFlowExccNode(id);
         model.addAttribute("wlFlowExccNode", wlFlowExccNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wlexcc/editCargoHandling";
     }
     
     /**
      * @Description 添加做箱/装货节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:47:02
      * @action saveCargoHandling
      * @return String
      */
     @RequestMapping("/saveCargoHandling")
     public String saveCargoHandling(WlFlowExccNode wlFlowExccNode, String billId, String[] wlFlowExccNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流出口", "做箱/装货");
         wlFlowExccBusin.saveWlFlowExccNode(wlFlowExccNode, billId, nodeId);
         frameworkBusin.saveUploadFileOwner(wlFlowExccNode, wlFlowExccNodeFiles);
         return "redirect:/sup/flow/wlexcc/viewCargoHandling/" + wlFlowExccNode.getId();
     }
     
     /**
      * @Description 做箱/装货节点详情信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:47:14
      * @action viewCargoHandling
      * @return String
      */
     @RequestMapping("/viewCargoHandling/{id}")
     public String viewCargoHandling(@PathVariable String id) {
         WlFlowExccNode wlFlowExccNode = wlFlowExccBusin.getWlFlowExccNode(id);
         model.addAttribute("wlFlowExccNode", wlFlowExccNode);
         return "/sup/flow/wlexcc/viewCargoHandling";
     }
     
     
     
     
     /**
      * @Description 编辑报关节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:51:27
      * @action editCustomsDeclaration
      * @return String
      */
     @RequestMapping("/editCustomsDeclaration/{id}/{billId}")
     public String editCustomsDeclaration(@PathVariable String id, @PathVariable String billId) {
         WlFlowExccNode wlFlowExccNode = wlFlowExccBusin.getWlFlowExccNode(id);
         model.addAttribute("wlFlowExccNode", wlFlowExccNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wlexcc/editCustomsDeclaration";
     }
     
     /**
      * @Description 添加报关节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:52:56
      * @action saveCustomsDeclaration
      * @return String
      */
     @RequestMapping("/saveCustomsDeclaration")
     public String saveCustomsDeclaration(WlFlowExccNode wlFlowExccNode, String billId, String[] wlFlowExccNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流出口", "报关");
         wlFlowExccBusin.saveWlFlowExccNode(wlFlowExccNode, billId, nodeId);
         frameworkBusin.saveUploadFileOwner(wlFlowExccNode, wlFlowExccNodeFiles);
         return "redirect:/sup/flow/wlexcc/viewCustomsDeclaration/" + wlFlowExccNode.getId();
     }
     
     /**
      * @Description 报关节点详情信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 下午12:26:03
      * @action viewCustomsDeclaration
      * @return String
      */
     @RequestMapping("/viewCustomsDeclaration/{id}")
     public String viewCustomsDeclaration(@PathVariable String id) {
         WlFlowExccNode wlFlowExccNode = wlFlowExccBusin.getWlFlowExccNode(id);
         model.addAttribute("wlFlowExccNode", wlFlowExccNode);
         return "/sup/flow/wlexcc/viewCustomsDeclaration";
     }
     
     /**
      * @Description 编辑出运节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 下午12:31:57
      * @action editShipping
      * @return String
      */
     @RequestMapping("/editShipping/{id}/{billId}")
     public String editShipping(@PathVariable String id, @PathVariable String billId) {
         WlFlowExccNode wlFlowExccNode = wlFlowExccBusin.getWlFlowExccNode(id);
         model.addAttribute("wlFlowExccNode", wlFlowExccNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wlexcc/editShipping";
     }
     
     /**
      * @Description 添加出运节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 下午12:33:08
      * @action saveShipping
      * @return String
      */
     @RequestMapping("/saveShipping")
     public String saveShipping(WlFlowExccNode wlFlowExccNode, String billId, String[] wlFlowExccNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流出口", "出运");
         wlFlowExccBusin.saveWlFlowExccNode(wlFlowExccNode, billId, nodeId);
         frameworkBusin.saveUploadFileOwner(wlFlowExccNode, wlFlowExccNodeFiles);
         return "redirect:/sup/flow/wlexcc/viewShipping/" + wlFlowExccNode.getId();
     }
     
     /**
      * @Description 出运节点详情
      * @author Chu Zhaocheng
      * @date 2017年6月20日 下午12:34:02
      * @action viewShipping
      * @return String
      */
     @RequestMapping("/viewShipping/{id}")
     public String viewShipping(@PathVariable String id) {
         WlFlowExccNode wlFlowExccNode = wlFlowExccBusin.getWlFlowExccNode(id);
         model.addAttribute("wlFlowExccNode", wlFlowExccNode);
         return "/sup/flow/wlexcc/viewShipping";
     }
     
}
