package com.sup.flow.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.WlFlowImccNode;
import com.sup.flow.busin.WlFlowImccBusin;
@Controller
@RequestMapping("/sup/flow/wlimcc")
public class SupWlFlowImccController  extends SupBaseController {
    @Autowired
    private WlFlowImccBusin wlFlowImccBusin;

     /**
      * @Description 编辑接单节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午10:56:08
      * @action editOrderReceiving
      * @return String
      */
     @RequestMapping("/editOrderReceiving/{id}/{billId}")
     public String editOrderReceiving(@PathVariable String id, @PathVariable String billId ) {
         WlFlowImccNode wlFlowImccNode = wlFlowImccBusin.getWlFlowImccNode(id);
         model.addAttribute("wlFlowImccNode", wlFlowImccNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wlimcc/editOrderReceiving";
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
         WlFlowImccNode wlFlowImccNode = wlFlowImccBusin.getWlFlowImccNode(id);
         model.addAttribute("wlFlowImccNode",wlFlowImccNode);
         return "/sup/flow/wlimcc/viewOrderReceiving";
     }
     
     /**
      * @Description 添加接单节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:03:36
      * @action saveOrderReceiving
      * @return String
      */
     @RequestMapping("/saveOrderReceiving")
     public String saveOrderReceiving(WlFlowImccNode wlFlowImccNode, String billId, String[] wlFlowImccNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流进口", "接单");
         wlFlowImccBusin.saveWlFlowImccNode(wlFlowImccNode, billId, nodeId);
         frameworkBusin.saveUploadFileOwner(wlFlowImccNode, wlFlowImccNodeFiles);
         return "redirect:/sup/flow/wlimcc/viewOrderReceiving/" + wlFlowImccNode.getId();
     }
     
     /**
      * @Description 删除物流进口节点
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:05:22
      * @action removeWlFlowImccNode
      * @return void
      */
     @RequestMapping("/removeWlFlowImccNode")
     @ResponseBody
     public void removeWlFlowImccNode(String[] ids) {
         JSONArray jsonArray = new JSONArray();
         try {
             if (ids != null && ids.length > 0) {
                 for (String id : ids) {
                     wlFlowImccBusin.removeWlFlowImccNode(id);
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
         WlFlowImccNode wlFlowImccNode = wlFlowImccBusin.getWlFlowImccNode(id);
         model.addAttribute("wlFlowImccNode", wlFlowImccNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wlimcc/editBookingSpace";
     }
     
     /**
      * @Description 添加订舱节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:34:42
      * @action saveBookingSpace
      * @return String
      */
     @RequestMapping("/saveBookingSpace")
     public String saveBookingSpace(WlFlowImccNode wlFlowImccNode, String billId, String[] wlFlowImccNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流进口", "订舱");
         wlFlowImccBusin.saveWlFlowImccNode(wlFlowImccNode, billId, nodeId);
         frameworkBusin.saveUploadFileOwner(wlFlowImccNode, wlFlowImccNodeFiles);
         return "redirect:/sup/flow/wlimcc/viewBookingSpace/" + wlFlowImccNode.getId();
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
         WlFlowImccNode wlFlowImccNode = wlFlowImccBusin.getWlFlowImccNode(id);
         model.addAttribute("wlFlowImccNode", wlFlowImccNode);
         return "/sup/flow/wlimcc/viewBookingSpace";
     }
     
     /**
      * @Description 编辑国内换单信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:44:39
      * @action editExchangeOrder
      * @return String
      */
     @RequestMapping("/editExchangeOrder/{id}/{billId}")
     public String editExchangeOrder(@PathVariable String id, @PathVariable String billId) {
         WlFlowImccNode wlFlowImccNode = wlFlowImccBusin.getWlFlowImccNode(id);
         model.addAttribute("wlFlowImccNode", wlFlowImccNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wlimcc/editExchangeOrder";
     }
     
     /**
      * @Description 添加国内换单节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:47:02
      * @action saveExchangeOrder
      * @return String
      */
     @RequestMapping("/saveExchangeOrder")
     public String saveExchangeOrder(WlFlowImccNode wlFlowImccNode, String billId, String[] wlFlowImccNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流进口", "国内换单");
         wlFlowImccBusin.saveWlFlowImccNode(wlFlowImccNode, billId, nodeId);
         frameworkBusin.saveUploadFileOwner(wlFlowImccNode, wlFlowImccNodeFiles);
         return "redirect:/sup/flow/wlimcc/viewExchangeOrder/" + wlFlowImccNode.getId();
     }
     
     /**
      * @Description 国内换单节点详情信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:47:14
      * @action viewExchangeOrder
      * @return String
      */
     @RequestMapping("/viewExchangeOrder/{id}")
     public String viewExchangeOrder(@PathVariable String id) {
         WlFlowImccNode wlFlowImccNode = wlFlowImccBusin.getWlFlowImccNode(id);
         model.addAttribute("wlFlowImccNode", wlFlowImccNode);
         return "/sup/flow/wlimcc/viewExchangeOrder";
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
         WlFlowImccNode wlFlowImccNode = wlFlowImccBusin.getWlFlowImccNode(id);
         model.addAttribute("wlFlowImccNode", wlFlowImccNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wlimcc/editCustomsDeclaration";
     }
     
     /**
      * @Description 添加报关节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 上午11:52:56
      * @action saveCustomsDeclaration
      * @return String
      */
     @RequestMapping("/saveCustomsDeclaration")
     public String saveCustomsDeclaration(WlFlowImccNode wlFlowImccNode, String billId, String[] wlFlowImccNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流进口", "报关");
         wlFlowImccBusin.saveWlFlowImccNode(wlFlowImccNode, billId, nodeId);
         frameworkBusin.saveUploadFileOwner(wlFlowImccNode, wlFlowImccNodeFiles);
         return "redirect:/sup/flow/wlimcc/viewCustomsDeclaration/" + wlFlowImccNode.getId();
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
         WlFlowImccNode wlFlowImccNode = wlFlowImccBusin.getWlFlowImccNode(id);
         model.addAttribute("wlFlowImccNode", wlFlowImccNode);
         return "/sup/flow/wlimcc/viewCustomsDeclaration";
     }
     
     /**
      * @Description 编辑送货节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 下午12:31:57
      * @action editDeliverGoods
      * @return String
      */
     @RequestMapping("/editDeliverGoods/{id}/{billId}")
     public String editDeliverGoods(@PathVariable String id, @PathVariable String billId) {
         WlFlowImccNode wlFlowImccNode = wlFlowImccBusin.getWlFlowImccNode(id);
         model.addAttribute("wlFlowImccNode", wlFlowImccNode);
         model.addAttribute("billId", billId);
         return "/sup/flow/wlimcc/editDeliverGoods";
     }
     
     /**
      * @Description 添加送货节点信息
      * @author Chu Zhaocheng
      * @date 2017年6月20日 下午12:33:08
      * @action saveDeliverGoods
      * @return String
      */
     @RequestMapping("/saveDeliverGoods")
     public String saveDeliverGoods(WlFlowImccNode wlFlowImccNode, String billId, String[] wlFlowImccNodeFiles){
         EnumFlowNode enumFlowNode=new EnumFlowNode();
         String nodeId=enumFlowNode.flownode("物流进口", "送货");
         wlFlowImccBusin.saveWlFlowImccNode(wlFlowImccNode, billId, nodeId);
         frameworkBusin.saveUploadFileOwner(wlFlowImccNode, wlFlowImccNodeFiles);
         return "redirect:/sup/flow/wlimcc/viewDeliverGoods/" + wlFlowImccNode.getId();
     }
     
     /**
      * @Description 送货节点详情
      * @author Chu Zhaocheng
      * @date 2017年6月20日 下午12:34:02
      * @action viewDeliverGoods
      * @return String
      */
     @RequestMapping("/viewDeliverGoods/{id}")
     public String viewDeliverGoods(@PathVariable String id) {
         WlFlowImccNode wlFlowImccNode = wlFlowImccBusin.getWlFlowImccNode(id);
         model.addAttribute("wlFlowImccNode", wlFlowImccNode);
         return "/sup/flow/wlimcc/viewDeliverGoods";
     }
     
     
}
