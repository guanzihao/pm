package com.sup.flow.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.WmFlowExccNode;
import com.sup.flow.busin.WmFlowExccBusin;
@Controller
@RequestMapping("/sup/flow/wmexcc")
public class SupWmFlowExccController extends SupBaseController{
    @Autowired
    private WmFlowExccBusin wmFlowExccBusin;
    
    /**
     * @Description 编辑单证制作信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:39:06
     * @action editDocsProduce
     * @return String
     */
    @RequestMapping("/editDocsProduce/{id}/{billId}")
    public String editDocsProduce(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmexcc/editDocsProduce";
    }
    
    /**
     * @Description 添加单证制作信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveDocsProduce
     * @return String
     */
    @RequestMapping("/saveDocsProduce")
    public String saveDocsProduce(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "单证制作");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewDocsProduce/" + wmFlowExccNode.getId();
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
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/sup/flow/wmexcc/viewDocsProduce";
    }
    
    /**
     * @Description 删除外贸出口流程节点
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:52:41
     * @action removeWmFlowExccNode
     * @return void
     */
    @RequestMapping("/removeWmFlowExccNode")
    @ResponseBody
    public void removeWmFlowExccNode(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    wmFlowExccBusin.removeWmFlowExccNode(id);
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
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmexcc/editCreditCard";
    }
    
    /**
     * @Description 添加信用证开证信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveCreditCard
     * @return String
     */
    @RequestMapping("/saveCreditCard")
    public String saveCreditCard(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "信用证开证");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewCreditCard/" + wmFlowExccNode.getId();
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
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/sup/flow/wmexcc/viewCreditCard";
    }
    
    /**
     * @Description 编辑收汇信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:37
     * @action editExchangeEarnings
     * @return String
     */
    @RequestMapping("/editExchangeEarnings/{id}/{billId}")
    public String editExchangeEarnings(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmexcc/editExchangeEarnings";
    }
    
    /**
     * @Description 添加收汇信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveExchangeEarnings
     * @return String
     */
    @RequestMapping("/saveExchangeEarnings")
    public String saveExchangeEarnings(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "收汇");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewExchangeEarnings/" + wmFlowExccNode.getId();
    }
    
    /**
     * @Description 收汇详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewExchangeEarnings
     * @return String
     */
    @RequestMapping("/viewExchangeEarnings/{id}")
    public String viewExchangeEarnings(@PathVariable String id) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/sup/flow/wmexcc/viewExchangeEarnings";
    }
    
    
    
    
    /**
     * @Description 编辑出口清关信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:21
     * @action editExportCleaning
     * @return String
     */
    @RequestMapping("/editExportCleaning/{id}/{billId}")
    public String editExportCleaning(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmexcc/editExportCleaning";
    }
    
    /**
     * @Description 添加出口清关信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveExportCleaning
     * @return String
     */
    @RequestMapping("/saveExportCleaning")
    public String saveExportCleaning(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "出口清关");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewExportCleaning/" + wmFlowExccNode.getId();
    }
    
    /**
     * @Description 出口清关详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewExportCleaning
     * @return String
     */
    @RequestMapping("/viewExportCleaning/{id}")
    public String viewExportCleaning(@PathVariable String id) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/sup/flow/wmexcc/viewExportCleaning";
    }
    
    /**
     * @Description 编辑结算开票信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:21
     * @action editSettlementInvoice
     * @return String
     */
    @RequestMapping("/editSettlementInvoice/{id}/{billId}")
    public String editSettlementInvoice(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmexcc/editSettlementInvoice";
    }
    
    /**
     * @Description 添加结算开票信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveSettlementInvoice
     * @return String
     */
    @RequestMapping("/saveSettlementInvoice")
    public String saveSettlementInvoice(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "结算开票");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewSettlementInvoice/" + wmFlowExccNode.getId();
    }
    
    /**
     * @Description 结算开票详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewSettlementInvoice
     * @return String
     */
    @RequestMapping("/viewSettlementInvoice/{id}")
    public String viewSettlementInvoice(@PathVariable String id) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/sup/flow/wmexcc/viewSettlementInvoice";
    }
    
    
   
    
    /**
     * @Description 编辑退税申请信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午10:58:21
     * @action editTaxRefund
     * @return String
     */
    @RequestMapping("/editTaxRefund/{id}/{billId}")
    public String editTaxRefund(@PathVariable String id, @PathVariable String billId) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/flow/wmexcc/editTaxRefund";
    }
    
    /**
     * @Description 添加退税申请信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveTaxRefund
     * @return String
     */
    @RequestMapping("/saveTaxRefund")
    public String saveTaxRefund(WmFlowExccNode wmFlowExccNode,String billId, String[] wmFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸出口", "退税申请");
        wmFlowExccBusin.saveWmFlowExccNode(wmFlowExccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowExccNode, wmFlowExccNodeFiles);
        return "redirect:/sup/flow/wmexcc/viewTaxRefund/" + wmFlowExccNode.getId();
    }
    
    /**
     * @Description 退税申请详情信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:43:13
     * @action viewTaxRefund
     * @return String
     */
    @RequestMapping("/viewTaxRefund/{id}")
    public String viewTaxRefund(@PathVariable String id) {
        WmFlowExccNode wmFlowExccNode = wmFlowExccBusin.getWmFlowExccNode(id);
        model.addAttribute("wmFlowExccNode", wmFlowExccNode);
        return "/sup/flow/wmexcc/viewTaxRefund";
    }
    
    
}
