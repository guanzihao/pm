package com.sup.order.controller;


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
import com.pm.sysconfig.bean.EnumFlowNode;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.WmFlowImcc;
import com.sup.flow.bean.WmFlowImccNode;
import com.sup.flow.busin.BgFlowExccBusin;
import com.sup.flow.busin.WmFlowImccBusin;

/**
 * @Description: 外贸进口模块
 * @author Chu Zhaocheng
 * @date 2017年6月20日 下午5:46:27
 */
@Controller
@RequestMapping("/sup/flow/wmimccs")
public class JieDianWmFlowImccController extends SupBaseController{
    @Autowired
    private BgFlowExccBusin bgFlowExccBusin;
    @Autowired
    private WmFlowImccBusin wmFlowImccBusin;
    /**
     * @Description 查看外贸进口流程单据
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:48:18
     * @action listWmFlowImcc
     * @return String
     */
    @RequestMapping(value="/listWmFlowImcc/{billId}")
    public String listWmFlowImcc(@PathVariable String billId){
        String result="";
        String str = wmFlowImccBusin.getWmFlowImcc(billId);
        switch (str) {
            case "单证制作":
                result="redirect:/sup/flow/wmimccs/listDocsProduce/" + billId+"/"+0;
                break;
            case "信用证开证":
                result="redirect:/sup/flow/wmimccs/listCreditCard/" + billId+"/"+0;
                break;
            case "收货款":
                result="redirect:/sup/flow/wmimccs/listReceipts/" + billId+"/"+0;
                break;
            case "付货款":
                result="redirect:/sup/flow/wmimccs/listPayments/" + billId+"/"+0;
                break;
            case "进口到货":
                result="redirect:/sup/flow/wmimccs/listArrivals/" + billId+"/"+0;
                break;
            case "进口清关":
                result="redirect:/sup/flow/wmimccs/listCleaning/" + billId+"/"+0;
                break;
            case "业务结算":
                result="redirect:/sup/flow/wmimccs/listSettlement/" + billId+"/"+0;
                break;
        }
        return result;
    }
    
    
    /**
     * @Description 单证制作列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:57:30
     * @action listDocsProduce
     * @return String
     */
    @RequestMapping(value="/listDocsProduce/{billId}/{close}")
    public String listDocsProduce(@PathVariable String billId, SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "单证制作");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.contractStartDate FROM WmFlowImccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        WmFlowImcc wlFlowExcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        model.addAttribute("orderid", wlFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/ic/listDocsProduce";
    }
    
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
        return "/sup/order/ic/editDocsProduce";
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
        wmFlowImccNode.setFlag(0);
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
      //执行报关出口节点状态存储过程
        WmFlowImcc WmFlowImcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("wmim", WmFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/wmimccs/listDocsProduce/" + billId+"/"+"close";
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
        return "/sup/order/ic/viewDocsProduce";
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
                    WmFlowImccNode wmFlowImccNode=(WmFlowImccNode)businApi.get(WmFlowImccNode.class, id);
                  //执行报关出口节点状态存储过程
                    WmFlowImcc WmFlowImcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, wmFlowImccNode.getBillId());
                    //执行存储过程
                    bgFlowExccBusin.updateorderstate("wmim", WmFlowImcc.getOrderFromId());
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    
    /**
     * @Description 信用证开证列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listCreditCard
     * @return String
     */
    @RequestMapping(value="/listCreditCard/{billId}/{close}")
    public String listCreditCard(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "信用证开证");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.lcStartDate FROM WmFlowImccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        WmFlowImcc wlFlowExcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        model.addAttribute("orderid", wlFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/ic/listCreditCard";
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
        return "/sup/order/ic/editCreditCard";
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
        wmFlowImccNode.setFlag(0);
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
      //执行报关出口节点状态存储过程
        WmFlowImcc WmFlowImcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("wmim", WmFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/wmimccs/listCreditCard/" +billId+"/"+"close";
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
        return "/sup/order/ic/viewCreditCard";
    }

    
    /**
     * @Description 收货款列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listReceipts
     * @return String
     */
    @RequestMapping(value="/listReceipts/{billId}/{close}")
    public String listReceipts(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "收货款");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.divDate,a.recAmount,a.recCurrency FROM WmFlowImccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        WmFlowImcc wlFlowExcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        model.addAttribute("orderid", wlFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/ic/listReceipts";
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
        return "/sup/order/ic/editReceipts";
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
        wmFlowImccNode.setFlag(0);
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
      //执行报关出口节点状态存储过程
        WmFlowImcc WmFlowImcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("wmim", WmFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/wmimccs/listReceipts/" + billId+"/"+"close";
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
        return "/sup/order/ic/viewReceipts";
    }
    
    
    /**
     * @Description 付货款列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listPayments
     * @return String
     */
    @RequestMapping(value="/listPayments/{billId}/{close}")
    public String listPayments(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "付货款");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.payTime,a.payAmount,a.payCurrency FROM WmFlowImccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        WmFlowImcc wlFlowExcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        model.addAttribute("orderid", wlFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/ic/listPayments";
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
        return "/sup/order/ic/editPayments";
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
        wmFlowImccNode.setFlag(0);
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
        //执行报关出口节点状态存储过程
        WmFlowImcc WmFlowImcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("wmim", WmFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/wmimccs/listPayments/" + billId+"/"+"close";
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
        return "/sup/order/ic/viewPayments";
    }
    
    
    /**
     * @Description 进口到货列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listArrivals
     * @return String
     */
    @RequestMapping(value="/listArrivals/{billId}/{close}")
    public String listArrivals(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "进口到货");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.arrivalDate FROM WmFlowImccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        WmFlowImcc wlFlowExcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        model.addAttribute("orderid", wlFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/ic/listArrivals";
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
        return "/sup/order/ic/editArrivals";
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
        wmFlowImccNode.setFlag(0);
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
        //执行报关出口节点状态存储过程
        WmFlowImcc WmFlowImcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("wmim", WmFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/wmimccs/listArrivals/" + billId+"/"+"close";
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
        return "/sup/order/ic/viewArrivals";
    }
    
    
    /**
     * @Description 进口清关列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listCleaning
     * @return String
     */
    @RequestMapping(value="/listCleaning/{billId}/{close}")
    public String listCleaning(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "进口清关");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.customClearDate FROM WmFlowImccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        WmFlowImcc wlFlowExcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        model.addAttribute("orderid", wlFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/ic/listCleaning";
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
        return "/sup/order/ic/editCleaning";
    }
    
    /**
     * @Description 添加进口清关信息
     * @author Chu Zhaocheng
     * @date 2017年6月21日 上午9:41:06
     * @action saveCleaning
     * @return String
     */
    @RequestMapping("/saveCleaning")
    public String saveCleaning(WmFlowImccNode wmFlowImccNode,String billId, String[] wmFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "进口清关");
        wmFlowImccNode.setFlag(0);
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
        //执行报关出口节点状态存储过程
        WmFlowImcc WmFlowImcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("wmim", WmFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/wmimccs/listCleaning/" + billId+"/"+"close";
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
        return "/sup/order/ic/viewCleaning";
    }
    
    
    /**
     * @Description 业务结算列表
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午5:58:12
     * @action listSettlement
     * @return String
     */
    @RequestMapping(value="/listSettlement/{billId}/{close}")
    public String listSettlement(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("外贸进口", "业务结算");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.balanceDate FROM WmFlowImccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        WmFlowImcc wlFlowExcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        model.addAttribute("orderid", wlFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/ic/listSettlement";
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
        return "/sup/order/ic/editSettlement";
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
        wmFlowImccNode.setFlag(0);
        wmFlowImccBusin.saveWmFlowImccNode(wmFlowImccNode, billId, nodeId);
        frameworkBusin.saveUploadFileOwner(wmFlowImccNode, wmFlowImccNodeFiles);
        //执行报关出口节点状态存储过程
        WmFlowImcc WmFlowImcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("wmim", WmFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/wmimccs/listSettlement/" + billId+"/"+"close";
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
        return "/sup/order/ic/viewSettlement";
    }
    /**
     * 跳转至外贸进口订单页面
     * @return
     */
    @RequestMapping("/toWmFlowImccOrderPage")
    public  String toWmFlowImccOrderPage(){
        model.addAttribute("flowId",VocationalWorkEnum.WM_IMCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        return "flow/wmimcc/listWmFlowImccOrder";
    }
    /**
     * 查询外贸进口订单列表信息
     * @param nodeType 节点类型
     * @param searchBean 参数
     * @return
     */
    @RequestMapping(value="/listWmFlowImccOrder/{nodeType}/{flowId}")
    public String showWmFlowImccOrder(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,\n");
        sql.append(" o.node4_state,o.node4_desc,o.node5_state,o.node5_desc ");
        sql.append(" ,o.node6_state,o.node6_desc,o.node7_state,o.node7_desc,o.order_state");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id \n");
        sql.append(" where  b.flow_name=? \n");
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            sql.append(" and o.order_code=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            sql.append(" and o.obj_createDate>=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            sql.append(" and o.obj_createDate<=?");
        }
        if(!nodeType.equals("0")){
            sql.append(" and o.order_state=?");
        }
        pageBean.addQuerySQL(sql.toString());
        VocationalWorkEnum enums=VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            pageBean.addParams(searchBean.getSearchName1());
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            pageBean.addParams(searchBean.getSearchDate2());
        }
        if(!nodeType.equals("0")){
            pageBean.addParams(nodeType);
        }
        pageBean.sqlquery();
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("pageBean", pageBean);
        return "/flow/wmimcc/listWmFlowImccOrderDetail";
    }
    
}
