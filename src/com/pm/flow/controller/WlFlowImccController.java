package com.pm.flow.controller;


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
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.sup.flow.bean.WlFlowImccNode;
import com.sup.flow.busin.WlFlowImccBusin;

/**
 * @Description: 物流进口模块
 * @author Chu Zhaocheng
 * @date 2017年6月20日 上午10:42:54
 */
@Controller
@RequestMapping("/flow/wlimcc")
public class WlFlowImccController extends OrganizeBaseController{
    
   @Autowired
   private WlFlowImccBusin wlFlowImccBusin;
    
    /**
     * @Description 查看物流进口流程单据
     * @author Chu Zhaocheng
     * @date 2017年6月20日 上午10:45:26
     * @action listWlFlowImcc
     * @return String
     */
    @RequestMapping(value="/listWlFlowImcc/{billId}")
    public String listWlFlowImcc(@PathVariable String billId){
        String result="";
        String str = wlFlowImccBusin.getWlFlowImcc(billId);
            switch (str) {
            case "接单":
                result="redirect:/sup/flow/wlimcc/listOrderReceiving/" + billId;
                break;
            case "订舱":
                result="redirect:/sup/flow/wlimcc/listBookingSpace/" + billId;
                break;
            case "国内换单":
                result="redirect:/sup/flow/wlimcc/listExchangeOrder/" + billId;
                break;
            case "报关":
                result="redirect:/sup/flow/wlimcc/listCustomsDeclaration/" + billId;
                break;
            case "送货":
                result="redirect:/sup/flow/wlimcc/listDeliverGoods/" + billId;
                break;
       }
        return result;
    }

    /**
     * @Description 接单节点查询
     * @author Chu Zhaocheng
     * @date 2017年6月20日 上午10:49:36
     * @action listOrderReceiving
     * @return String
     */
    @RequestMapping(value="/listOrderReceiving/{billId}")
    public String listOrderReceiving(@PathVariable String billId,SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("物流进口", "接单");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id, a.endDate, a.state, a.oneAccessory, a.acceptDate FROM WlFlowImccNode a where a.billId = ? and nodeId = ?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wlimcc/listOrderReceiving";
    }

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
        return "/flow/wlimcc/editOrderReceiving";
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
        return "/flow/wlimcc/viewOrderReceiving";
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
     * @Description 订舱节点查询
     * @author Chu Zhaocheng
     * @date 2017年6月20日 上午11:23:57
     * @action listBookingSpace
     * @return String
     */
    @RequestMapping(value="/listBookingSpace/{billId}")
    public String listBookingSpace(@PathVariable String billId, SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("物流进口", "订舱");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id, a.endDate, a.state, a.oneAccessory, a.pol, a.pod, a.shipName, a.flight, a.mblNo, a.hblNo, a.sailDate FROM WlFlowImccNode a where a.billId = ? and nodeId = ?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wlimcc/listBookingSpace";
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
        return "/flow/wlimcc/editBookingSpace";
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
        return "/flow/wlimcc/viewBookingSpace";
    }
    
    
    /**
     * @Description 国内换单节点查询
     * @author Chu Zhaocheng
     * @date 2017年6月20日 上午11:41:30
     * @action listExchangeOrder
     * @return String
     */
    @RequestMapping(value="/listExchangeOrder/{billId}")
    public String listExchangeOrder(@PathVariable String billId, SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("物流进口", "国内换单");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id, a.endDate, a.state, a.oneAccessory, a.arrivalDate, a.switchBillDate  FROM WlFlowImccNode a where a.billId = ? and nodeId = ?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wlimcc/listExchangeOrder";
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
        return "/flow/wlimcc/editExchangeOrder";
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
        return "/flow/wlimcc/viewExchangeOrder";
    }
    
    
    /**
     * @Description 报关节点查询
     * @author Chu Zhaocheng
     * @date 2017年6月20日 上午11:49:45
     * @action listCustomsDeclaration
     * @return String
     */
    @RequestMapping(value="/listCustomsDeclaration/{billId}")
    public String listCustomsDeclaration(@PathVariable String billId, SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("物流进口", "报关");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id, a.endDate, a.state, a.oneAccessory, a.releaseDate FROM WlFlowImccNode a where a.billId = ? and nodeId = ?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wlimcc/listCustomsDeclaration";
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
        return "/flow/wlimcc/editCustomsDeclaration";
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
        return "/flow/wlimcc/viewCustomsDeclaration";
    }
    
    
    /**
     * @Description 送货节点查询
     * @author Chu Zhaocheng
     * @date 2017年6月20日 下午12:28:29
     * @action listDeliverGoods
     * @return String
     */
    @RequestMapping(value="/listDeliverGoods/{billId}")
    public String listDeliverGoods(@PathVariable String billId, SearchBean searchBean){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("物流进口", "送货");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id, a.endDate, a.state, a.oneAccessory, a.vehicleDate, a.transDate FROM WlFlowImccNode a where a.billId = ? and nodeId = ?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        return "/flow/wlimcc/listDeliverGoods";
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
        return "/flow/wlimcc/editDeliverGoods";
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
        return "/flow/wlimcc/viewDeliverGoods";
    }
    /**
     * 跳转至物流进口订单页面
     * @return
     */
    @RequestMapping("/toWlFlowImccOrder/{companyid}")
    public String toWlFlowImccOrderPage(@PathVariable String companyid){
        model.addAttribute("flowId",VocationalWorkEnum.WL_IMCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("yjs", TaskState.FWSYJS);
        model.addAttribute("jjorder",TaskState.YZZ);
        model.addAttribute("companyid",companyid);
        return "flow/wlimcc/listWlFlowImccOrder";
    }
    /**
     * 显示物流进口订单列表信息
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listWlFlowImccOrder/{nodeType}/{flowId}/{companyid}")
    public String showWlFlowImccOrderList(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean,@PathVariable String companyid){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc \n");
        sql.append(" ,o.node4_state,o.node4_desc,o.node5_state,o.node5_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name \n");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id \n");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        if(companyid.equals("0")){
            sql.append(" where   o.order_state not in(?,?) and b.flow_name=?\n");
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" where  task.user_id=?  and ");
            pageBean.addParams(companyid);
            sql.append("    o.order_state not in(?,?) and b.flow_name=?\n");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            sql.append(" and o.obj_createDate>=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            sql.append(" and o.obj_createDate<=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName2())){
            sql.append(" and ci.com_Name like'%"+searchBean.getSearchName2()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName3())){
            sql.append(" and sup.comName like'%"+searchBean.getSearchName3()+"%'");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        pageBean.addParams(TaskState.CG);
        pageBean.addParams(TaskState.WTJ);
        VocationalWorkEnum enums=VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            pageBean.addParams(searchBean.getSearchDate2()+" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        model.addAttribute("flowId",flowId);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("searchBean",searchBean);
        return "/flow/wlimcc/listWlFlowImccOrderDetail";
    }
    
}
