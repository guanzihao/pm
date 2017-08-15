package com.sup.order.controller;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.model.TaskState;
import com.pm.sysconfig.bean.EnumFlowNode;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.BgFlowExcc;
import com.sup.flow.bean.BgFlowExccNode;
import com.sup.flow.busin.BgFlowExccBusin;
import com.sup.order.bean.OrderFrom;

/**
 * @Description: 报关出口模块
 * @author Chu Zhaocheng
 * @date 2017年6月14日 上午8:40:21
 */
@Controller
@RequestMapping("/sup/flow/bgexccs")
public class JieDianBgFlowExccController extends SupBaseController{
    
   @Autowired
   private BgFlowExccBusin bgFlowExccBusin;
    
   
   /**
    * @Description 查看报关出口流程单据
    * @author Chu Zhaocheng
    * @date 2017年6月14日 上午8:41:28
    * @action listBgFlowExcc
    * @return String
    */
    @RequestMapping(value="/listBgFlowExcc/{billId}")
    public String listBgFlowExcc(@PathVariable String billId){
        String result="";
        String str = bgFlowExccBusin.getBgFlowExcc(billId);
        switch (str) {
            case "接单":
                result="redirect:/sup/flow/bgexccs/listBgFlowExccOrderReceiving/"+billId+"/"+0;
                break;
            case "查验":
                result="redirect:/sup/flow/bgexccs/listBgFlowExccCheck/"+billId+"/"+0;
                break;
            case "放行":
                result="redirect:/sup/flow/bgexccs/listBgFlowExccGreenLight/"+billId+"/"+0;
                break;
           }
        return result;
    }
    
    /**
     * @Description 接单节点列表查询
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:55:51
     * @action listBgFlowExccOrderReceiving
     * @return String
     */
    @RequestMapping(value="/listBgFlowExccOrderReceiving/{billId}/{close}")
    public String listBgFlowExccOrderReceiving(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeid=enumFlowNode.flownode("通关出口", "接单");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,a.startDate,a.endDate,a.status,a.oneAccessory,a.bussinessDate from BgFlowExccNode a where a.flag=0 and  a.billId=? and a.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeid);
        pageBean.addOrderBy("a.status,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        BgFlowExcc bgFlowExcc=(BgFlowExcc)businApi.get(BgFlowExcc.class, billId);
        model.addAttribute("orderid", bgFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/bgexcc/listBgFlowExccOrderReceiving";
    }
    
    /**
     * @Description 接单节点编辑回显
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:53:19
     * @action editFlowExccNode
     * @return String
     */
    @RequestMapping("/editFlowExccNode/{id}/{billId}")
    public String editFlowExccNode(@PathVariable String id,@PathVariable String billId ) {
        BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
        List<SupCompanyInfo> supplierList=bgFlowExccBusin.getSupplierList();
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/sup/order/bgexcc/editFlowExccNode";
    }
    
    /**
     * @Description 接单节点详情
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:54:45
     * @action viewFlowExccNode
     * @return String
     */
    @RequestMapping("/viewFlowExccNode/{id}/{billId}")
    public String viewFlowExccNode(@PathVariable String id,@PathVariable String billId ) {
        BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
        SupCompanyInfo firstSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getSecDept());
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/sup/order/bgexcc/viewFlowExccNode";
    }
    
    /**
     * @Description 添加接单节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:52:44
     * @action saveFlowExccNode
     * @return String
     */
    @RequestMapping("/saveFlowExccNode")
    public String saveFlowExccNode(BgFlowExccNode flowExccNode,String billId,String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeid=enumFlowNode.flownode("通关出口", "接单");
        flowExccNode.setNodeId(nodeid);
        SupCompanyInfo sup=this.getCurrSup().getSupCompanyInfo();
        
        flowExccNode.setOperator(sup.getId());
        flowExccNode.setOperatorName(sup.getComName());
        flowExccNode.setLastUpdate(new Date());
        flowExccNode.setFlowNode(1);
        flowExccNode.setFlag(0);
        bgFlowExccBusin.saveFlowExccNode(flowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowExccNode, flowImccNodeFiles);
        //执行报关出口节点状态存储过程
        BgFlowExcc bgFlowExcc=(BgFlowExcc)businApi.get(BgFlowExcc.class, billId);
        //执行存储过程
        OrderFrom order=(OrderFrom)businApi.get(OrderFrom.class, bgFlowExcc.getOrderFrom());
        bgFlowExccBusin.updateorderstate("bgex", order.getId());
        return "redirect:/sup/flow/bgexccs/listBgFlowExccOrderReceiving/"+billId+"/"+"close";
    }
    
    /**
     * @Description 批量删除节点明细
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:51:39
     * @action removeFlowExccNode
     * @return void
     */
    @RequestMapping("/removeFlowExccNode")
    @ResponseBody
    public void removeFlowExccNode(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    bgFlowExccBusin.removeFlowExccNode(id);
                    BgFlowExccNode bgFlowExccnode=(BgFlowExccNode)businApi.get(BgFlowExccNode.class, id);
                    //执行报关出口节点状态存储过程
                    BgFlowExcc bgFlowExcc=(BgFlowExcc)businApi.get(BgFlowExcc.class, bgFlowExccnode.getBillId());
                    OrderFrom order=(OrderFrom)businApi.get(OrderFrom.class, bgFlowExcc.getOrderFrom());
                    bgFlowExccBusin.updateorderstate("bgex", order.getId());
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

    
    /**
     * @Description 查验节点列表查询
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:50:48
     * @action listBgFlowExccCheck
     * @return String
     */
    @RequestMapping(value="/listBgFlowExccCheck/{billId}/{close}")
    public String listBgFlowExccCheck(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("通关出口", "查验");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,a.startDate,a.endDate,a.status,a.oneAccessory,a.inspectionDate from BgFlowExccNode a where a.flag=0 and  a.billId=? and a.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.status,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        BgFlowExcc bgFlowExcc=(BgFlowExcc)businApi.get(BgFlowExcc.class, billId);
        model.addAttribute("orderid", bgFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/bgexcc/listBgFlowExccCheck";
    }
    
    /**
     * @Description 查验节点编辑回显
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:49:58
     * @action editFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/editFlowExccNodeCheck/{id}/{billId}")
    public String editFlowExccNodeCheck(@PathVariable String id,@PathVariable String billId ) {
        BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
        List<SupCompanyInfo> supplierList=bgFlowExccBusin.getSupplierList();
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/sup/order/bgexcc/editFlowExccNodeCheck";
    }
    
    /**
     * @Description 添加查验节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:49:09
     * @action saveFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/saveFlowExccNodeCheck")
    public String saveFlowExccNodeCheck(BgFlowExccNode flowExccNode,String billId,String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("通关出口", "查验");
        flowExccNode.setNodeId(nodeId);
        SupCompanyInfo sup=this.getCurrSup().getSupCompanyInfo();
        flowExccNode.setOperator(sup.getId());
        flowExccNode.setOperatorName(sup.getComName());
        flowExccNode.setLastUpdate(new Date());
        flowExccNode.setFlowNode(1);
        flowExccNode.setFlag(0);
       
        bgFlowExccBusin.saveFlowExccNode(flowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowExccNode, flowImccNodeFiles);
        //执行报关出口节点状态存储过程
        BgFlowExcc bgFlowExcc=(BgFlowExcc)businApi.get(BgFlowExcc.class, billId);
        //执行存储过程
        OrderFrom order=(OrderFrom)businApi.get(OrderFrom.class, bgFlowExcc.getOrderFrom());
        bgFlowExccBusin.updateorderstate("bgex", order.getId());
        return "redirect:/sup/flow/bgexccs/listBgFlowExccCheck/"+billId+"/"+"close";
    }
    
    /**
     * @Description 查验节点详情
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:48:48
     * @action viewFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/viewFlowExccNodeCheck/{id}/{billId}")
    public String viewFlowExccNodeCheck(@PathVariable String id,@PathVariable String billId ) {
        BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
        SupCompanyInfo firstSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getSecDept());
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/sup/order/bgexcc/viewFlowExccNodeCheck";
    }
    
    
    /**
     * @Description 放行节点列表查询
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:47:37
     * @action listBgFlowExccGreenLight
     * @return String
     */
    @RequestMapping(value="/listBgFlowExccGreenLight/{billId}/{close}")
    public String listBgFlowExccGreenLight(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("通关出口", "放行");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,a.startDate,a.endDate,a.status,a.oneAccessory,a.releaseDate from BgFlowExccNode a where a.flag=0 and  a.billId=? and a.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.status,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        BgFlowExcc bgFlowExcc=(BgFlowExcc)businApi.get(BgFlowExcc.class, billId);
        model.addAttribute("orderid", bgFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/bgexcc/listBgFlowExccGreenLight";
    }
    
    /**
     * @Description 放行节点编辑回显
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:46:56
     * @action editFlowExccNodeGreenLight
     * @return String
     */
    @RequestMapping("/editFlowExccNodeGreenLight/{id}/{billId}")
    public String editFlowExccNodeGreenLight(@PathVariable String id,@PathVariable String billId ) {
         BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
         List<SupCompanyInfo> supplierList=bgFlowExccBusin.getSupplierList();
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/sup/order/bgexcc/editFlowExccNodeGreenLight";
    }
    
    /**
     * @Description 添加放行节点信息
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:46:15
     * @action saveFlowExccNodeGreenLight
     * @return String
     */
    @RequestMapping("/saveFlowExccNodeGreenLight")
    public String saveFlowExccNodeGreenLight(BgFlowExccNode flowExccNode, String billId,String[] flowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("通关出口", "放行");
        flowExccNode.setNodeId(nodeId);
        SupCompanyInfo sup=this.getCurrSup().getSupCompanyInfo();
        flowExccNode.setOperator(sup.getId());
        flowExccNode.setOperatorName(sup.getComName());
        flowExccNode.setLastUpdate(new Date());
        flowExccNode.setFlowNode(1);
        flowExccNode.setFlag(0);
        bgFlowExccBusin.saveFlowExccNode(flowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowExccNode, flowImccNodeFiles);
        //执行报关出口节点状态存储过程
        BgFlowExcc bgFlowExcc=(BgFlowExcc)businApi.get(BgFlowExcc.class, billId);
        //执行存储过程
        OrderFrom order=(OrderFrom)businApi.get(OrderFrom.class, bgFlowExcc.getOrderFrom());
        bgFlowExccBusin.updateorderstate("bgex", order.getId());
      
        return "redirect:/sup/flow/bgexccs/listBgFlowExccGreenLight/"+billId+"/"+"close";
    }
    
    /**
     * @Description 放行节点详情
     * @author Chu Zhaocheng
     * @date 2017年6月14日 上午8:45:33
     * @action viewFlowExccNodeGreenLight
     * @return String
     */
    @RequestMapping("/viewFlowExccNodeGreenLight/{id}/{billId}")
    public String viewFlowExccNodeGreenLight(@PathVariable String id, @PathVariable String billId ) {
        BgFlowExccNode flowExccNode = bgFlowExccBusin.getFlowExccNode(id);
        SupCompanyInfo firstSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowExccBusin.getSupplierById(flowExccNode.getSecDept());
        model.addAttribute("flowExccNode",flowExccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/sup/order/bgexcc/viewFlowExccNodeGreenLight";
    }
    /**
     * 跳转至报关出口订单页面
     * @return
     */
    @RequestMapping(value="/toBgFlowExccOrderPage")
    public String toBgFlowExccOrderPage(){
        model.addAttribute("flowId",VocationalWorkEnum.BG_EXCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        return "/flow/bgexcc/toBgFlowExccOrder";
    }
    /**
     * 显示报关出口订单列表信息
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listBgFlowExccOrder/{nodeType}/{flowId}")
    public String showBgFlowExccOrder(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state \n");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id");
        sql.append(" where  b.flow_name=?\n");
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
        model.addAttribute("flowId",flowId);
        model.addAttribute("pageBean", pageBean);
        return "/flow/bgexcc/listBgFlowExccOrderDetail";
    }
    
}
