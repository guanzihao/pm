package com.sup.flow.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.WmFlowImccNode;
import com.sup.flow.busin.WmFlowImccBusin;
@Controller
@RequestMapping("/sup/flow/wmimcc")
public class SupWmFlowImccController extends SupBaseController {
    @Autowired
    private WmFlowImccBusin wmFlowImccBusin;
    
    
    
   
    
    /**
     * @Description 编辑单证制作信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:39:06
     * @action editDocsProduce
     * @return String
     */
    @RequestMapping("/editDocsProduce/{id}/{billId}")
    public String editDocsProduce(@PathVariable String id, @PathVariable String billId) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmimcc/editDocsProduce";
    }
    
    /**
     * @Description 添加单证制作信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveDocsProduce
     * @return String
     */
    @RequestMapping("/saveDocsProduce")
    public String saveDocsProduce(WmFlowImccNode wmFlowImccNode,String billId, String[] wmFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "单证制作");
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
        return "redirect:/sup/flow/wmimcc/viewDocsProduce/" + wmFlowImccNode.getId();
    }
    
    /**
     * @Description 单证制作详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewDocsProduce
     * @return String
     */
    @RequestMapping("/viewDocsProduce/{id}")
    public String viewDocsProduce(@PathVariable String id) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        return "/sup/flow/wmimcc/viewDocsProduce";
    }
    
    /**
     * @Description 删除外贸进口流程节点
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:52:41
     * @action removeWmFlowImccNode
     * @return void
     */
    @RequestMapping("/removeWmFlowImccNode")
    @ResponseBody
    public void removeWmFlowImccNode(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    wmFlowImccBusin.removeWmFlowImccNode(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    /**
     * 编辑信用证开证信息
     * @param id
     * @return
     */
    @RequestMapping("/editCreditCard/{id}/{billId}")
    public String editCreditCard(@PathVariable String id, @PathVariable String billId) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmimcc/editCreditCard";
    }
    
    /**
     * @Description 添加信用证开证信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveCreditCard
     * @return String
     */
    @RequestMapping("/saveCreditCard")
    public String saveCreditCard(WmFlowImccNode wmFlowImccNode,String billId, String[] wmFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "信用证开证");
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
        return "redirect:/sup/flow/wmimcc/viewCreditCard/" + wmFlowImccNode.getId();
    }
    
    /**
     * @Description 信用证开证详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewCreditCard
     * @return String
     */
    @RequestMapping("/viewCreditCard/{id}")
    public String viewCreditCard(@PathVariable String id) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        return "/sup/flow/wmimcc/viewCreditCard";
    }

    
   
    
    /**
     * @Description 编辑收货款信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:37
     * @action editReceipts
     * @return String
     */
    @RequestMapping("/editReceipts/{id}/{billId}")
    public String editReceipts(@PathVariable String id, @PathVariable String billId) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmimcc/editReceipts";
    }
    
    /**
     * @Description 添加收货款信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveReceipts
     * @return String
     */
    @RequestMapping("/saveReceipts")
    public String saveReceipts(WmFlowImccNode wmFlowImccNode,String billId, String[] wmFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "收货款");
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
        return "redirect:/sup/flow/wmimcc/viewReceipts/" + wmFlowImccNode.getId();
    }
    
    /**
     * @Description 收货款详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewReceipts
     * @return String
     */
    @RequestMapping("/viewReceipts/{id}")
    public String viewReceipts(@PathVariable String id) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        return "/sup/flow/wmimcc/viewReceipts";
    }
    
    
    
    
    /**
     * @Description 编辑付货款信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:21
     * @action editPayments
     * @return String
     */
    @RequestMapping("/editPayments/{id}/{billId}")
    public String editPayments(@PathVariable String id, @PathVariable String billId) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmimcc/editPayments";
    }
    
    /**
     * @Description 添加付货款信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action savePayments
     * @return String
     */
    @RequestMapping("/savePayments")
    public String savePayments(WmFlowImccNode wmFlowImccNode,String billId, String[] wmFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "付货款");
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
        return "redirect:/sup/flow/wmimcc/viewPayments/" + wmFlowImccNode.getId();
    }
    
    /**
     * @Description 付货款详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewPayments
     * @return String
     */
    @RequestMapping("/viewPayments/{id}")
    public String viewPayments(@PathVariable String id) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        return "/sup/flow/wmimcc/viewPayments";
    }
    /**
     * @Description 编辑进口到货信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:21
     * @action editArrivals
     * @return String
     */
    @RequestMapping("/editArrivals/{id}/{billId}")
    public String editArrivals(@PathVariable String id, @PathVariable String billId) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmimcc/editArrivals";
    }
    
    /**
     * @Description 添加进口到货信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveArrivals
     * @return String
     */
    @RequestMapping("/saveArrivals")
    public String saveArrivals(WmFlowImccNode wmFlowImccNode,String billId, String[] wmFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "进口到货");
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
        return "redirect:/sup/flow/wmimcc/viewArrivals/" + wmFlowImccNode.getId();
    }
    
    /**
     * @Description 进口到货详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewArrivals
     * @return String
     */
    @RequestMapping("/viewArrivals/{id}")
    public String viewArrivals(@PathVariable String id) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        return "/sup/flow/wmimcc/viewArrivals";
    }
    
    
    
    
    /**
     * @Description 编辑进口清关信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:21
     * @action editCleaning
     * @return String
     */
    @RequestMapping("/editCleaning/{id}/{billId}")
    public String editCleaning(@PathVariable String id, @PathVariable String billId) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmimcc/editCleaning";
    }
    
    
    
    /**
     * @Description 进口清关详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewCleaning
     * @return String
     */
    @RequestMapping("/viewCleaning/{id}")
    public String viewCleaning(@PathVariable String id) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        return "/sup/flow/wmimcc/viewCleaning";
    }
    
    
   
    
    /**
     * @Description 编辑业务结算信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:21
     * @action editSettlement
     * @return String
     */
    @RequestMapping("/editSettlement/{id}/{billId}")
    public String editSettlement(@PathVariable String id, @PathVariable String billId) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmimcc/editSettlement";
    }
    
    /**
     * @Description 添加业务结算信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveSettlement
     * @return String
     */
    @RequestMapping("/saveSettlement")
    public String saveSettlement(WmFlowImccNode wmFlowImccNode,String billId, String[] wmFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "业务结算");
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
        return "redirect:/sup/flow/wmimcc/viewSettlement/" + wmFlowImccNode.getId();
    }
    
    /**
     * @Description 业务结算详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewSettlement
     * @return String
     */
    @RequestMapping("/viewSettlement/{id}")
    public String viewSettlement(@PathVariable String id) {
        WmFlowImccNode wmFlowImccNode = wmFlowImccBusin.getWmFlowImccNode(id);
        model.addAttribute("wmFlowImccNode", wmFlowImccNode);
        return "/sup/flow/wmimcc/viewSettlement";
    }
    
}
