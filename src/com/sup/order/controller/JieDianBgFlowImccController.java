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
import com.sup.flow.bean.BgFlowImcc;
import com.sup.flow.bean.BgFlowImccNode;
import com.sup.flow.busin.BgFlowExccBusin;
import com.sup.flow.busin.BgFlowImccBusin;
import com.sup.order.bean.OrderFrom;

@Controller
@RequestMapping("/sup/flow/bgimccs")
public class JieDianBgFlowImccController extends SupBaseController{
    
   @Autowired
   private BgFlowImccBusin bgFlowImccBusin;
   @Autowired
   private BgFlowExccBusin bgFlowExccBusin;
    /**
     * 查看报关进口通关流程单据
     * @return
     */
    @RequestMapping(value="/listBgFlowImcc/{billId}")
    public String listBgFlowImcc(@PathVariable String billId){
        String result="";
        String str = bgFlowImccBusin.getBgFlowImcc(billId);
        System.err.println(str);
           switch (str) {
            case "接单":
                result="redirect:/sup/flow/bgimccs/listBgFlowImccOrderReceiving/"+billId+"/"+0;
                break;
            case "查验":
                result="redirect:/sup/flow/bgimccs/listBgFlowImccCheck/"+billId+"/"+0;
                break;
            case "放行":
                result="redirect:/sup/flow/bgimccs/listBgFlowImccGreenLight/"+billId+"/"+0;
                break;
           }
            return result;
        }
    /**
     * 查看报关进口通关流程单据(接单)
     * @return
     * nodeId 接单节点ID
     */
    @RequestMapping(value="/listBgFlowImccOrderReceiving/{billId}/{close}")
    public String listBgFlowImccOrderReceiving(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        PageBean pageBean = new PageBean(searchBean, businApi);
        EnumFlowNode node=new EnumFlowNode();
        pageBean.addQuerySQL("select a.id,a.startDate,a.endDate,a.status,a.oneAccessory,a.bussinessDate from BgFlowImccNode a where  a.flag=0 and  a.billId=? and a.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(node.flownode("通关进口", "接单"));
        pageBean.addOrderBy("a.status,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        BgFlowImcc bgFlowImcc=(BgFlowImcc)businApi.get(BgFlowImcc.class, billId);
        model.addAttribute("orderid", bgFlowImcc);
        model.addAttribute("close", close);
        return "/sup/order/bgimcc/listBgFlowImccOrderReceiving";
    }
    /**
     * 根据编号得到一个报关信息
     * @param id
     * @return
     */
    @RequestMapping("/editFlowImccNode/{id}/{billId}")
    public String editFlowImccNode(@PathVariable String id
            ,@PathVariable String billId ) {
         BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
         List<SupCompanyInfo> supplierList=bgFlowImccBusin.getSupplierList();
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/sup/order/bgimcc/editFlowImccNode";
    }
    /**
     * 接单详情页面
     * @param id
     * @return
     */
    @RequestMapping("/viewFlowImccNode/{id}/{billId}")
    public String viewFlowImccNode(@PathVariable String id
            ,@PathVariable String billId ) {
        BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
        SupCompanyInfo firstSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getSecDept());
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/sup/order/bgimcc/viewFlowImccNode";
    }
    
    /**
     * 添加报关信息
     * @param flowImccNode
     * @param billId
     * @return
     */
    @RequestMapping("/saveFlowImccNode")
    public String saveFlowImccNode(BgFlowImccNode flowImccNode,String billId, String[] flowImccNodeFiles){
        EnumFlowNode node=new EnumFlowNode();
        flowImccNode.setNodeId(node.flownode("通关进口", "接单"));
SupCompanyInfo sup=this.getCurrSup().getSupCompanyInfo();
        
flowImccNode.setOperator(sup.getId());
flowImccNode.setOperatorName(sup.getComName());
        flowImccNode.setLastUpdate(new Date());
        flowImccNode.setFlowNote(1);
        flowImccNode.setFlag(0);
        bgFlowImccBusin.saveFlowImccNode(flowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowImccNode, flowImccNodeFiles);
      //执行报关出口节点状态存储过程
        BgFlowImcc BgFlowImcc=(BgFlowImcc)businApi.get(BgFlowImcc.class, billId);
        //执行存储过程
        OrderFrom order=(OrderFrom)businApi.get(OrderFrom.class, BgFlowImcc.getOrderFrom());
        bgFlowExccBusin.updateorderstate("bgim", order.getId());
        return "redirect:/sup/flow/bgimccs/listBgFlowImccOrderReceiving/"+billId+"/"+"close";
    }
    
    /**
     * 删除节点明细
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @RequestMapping("/removeFlowImccNode")
    @ResponseBody
    public void removeFlowImccNode(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    bgFlowImccBusin.removeFlowImccNode(id);
                    BgFlowImccNode bgFlowImccNode=(BgFlowImccNode)businApi.get(BgFlowImccNode.class, id);
                  //执行报关出口节点状态存储过程
                    BgFlowImcc BgFlowImcc=(BgFlowImcc)businApi.get(BgFlowImcc.class, bgFlowImccNode.getBillId());
                    //执行存储过程
                    OrderFrom order=(OrderFrom)businApi.get(OrderFrom.class, BgFlowImcc.getOrderFrom());
                    bgFlowExccBusin.updateorderstate("bgim", order.getId());
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    /**
     *  查看报关进口通关流程单据(查验)
     * @return
     */
    @RequestMapping(value="/listBgFlowImccCheck/{billId}/{close}")
    public String listBgFlowImccCheck(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        PageBean pageBean = new PageBean(searchBean, businApi);
        EnumFlowNode node=new EnumFlowNode();
        pageBean.addQuerySQL("select a.id,a.startDate,a.endDate,a.status,a.oneAccessory,a.inspectionDate from BgFlowImccNode a where  a.flag=0 and  a.billId=? and a.nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(node.flownode("通关进口", "查验"));
        pageBean.addOrderBy("a.status,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        BgFlowImcc bgFlowImcc=(BgFlowImcc)businApi.get(BgFlowImcc.class, billId);
        model.addAttribute("orderid", bgFlowImcc);
        model.addAttribute("close", close);
        return "/sup/order/bgimcc/listBgFlowImccCheck";
    }
    
    /**
     * 根据编号得到一个报关信息(查验)
     * @param id
     * @return
     */
    @RequestMapping("/editFlowImccNodeCheck/{id}/{billId}")
    public String editFlowImccNodeCheck(@PathVariable String id
            ,@PathVariable String billId ) {
         BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
         List<SupCompanyInfo> supplierList=bgFlowImccBusin.getSupplierList();
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/sup/order/bgimcc/editFlowImccNodeCheck";
    }
    
    /**
     * 添加报关信息(查验)
     * @param flowImccNode
     * @param billId
     * @return
     */
    @RequestMapping("/saveFlowImccNodeCheck")
    public String saveFlowImccNodeCheck(BgFlowImccNode flowImccNode,String billId, String[] flowImccNodeFiles){
        EnumFlowNode node=new EnumFlowNode();
        flowImccNode.setNodeId(node.flownode("通关进口", "查验"));
        SupCompanyInfo sup=this.getCurrSup().getSupCompanyInfo();
        
        flowImccNode.setOperator(sup.getId());
        flowImccNode.setOperatorName(sup.getComName());
        flowImccNode.setLastUpdate(new Date());
        flowImccNode.setFlowNote(1);
        flowImccNode.setFlag(0);
        bgFlowImccBusin.saveFlowImccNode(flowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowImccNode, flowImccNodeFiles);
      //执行报关出口节点状态存储过程
        BgFlowImcc BgFlowImcc=(BgFlowImcc)businApi.get(BgFlowImcc.class, billId);
        //执行存储过程
        OrderFrom order=(OrderFrom)businApi.get(OrderFrom.class, BgFlowImcc.getOrderFrom());
        bgFlowExccBusin.updateorderstate("bgim", order.getId());
        return "redirect:/sup/flow/bgimccs/listBgFlowImccCheck/"+billId+"/"+"close";
    }
    
    /**
     * 详情页面(查验)
     * @param id
     * @return
     */
    @RequestMapping("/viewFlowImccNodeCheck/{id}/{billId}")
    public String viewFlowImccNodeCheck(@PathVariable String id
            ,@PathVariable String billId ) {
        BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
        SupCompanyInfo firstSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getSecDept());
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/sup/order/bgimcc/viewFlowImccNodeCheck";
    }
    
    /**
     *  查看报关进口通关流程单据(放行)
     * @return
     */
    @RequestMapping(value="/listBgFlowImccGreenLight/{billId}/{close}")
    public String listBgFlowImccGreenLight(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,a.startDate,a.endDate,a.status,a.oneAccessory,a.releaseDate from BgFlowImccNode a where  a.flag=0 and  a.billId=? and a.nodeId=?");
        pageBean.addParams(billId);
        EnumFlowNode node=new EnumFlowNode();
        pageBean.addParams(node.flownode("通关进口", "放行"));
        pageBean.addOrderBy("a.status,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        BgFlowImcc bgFlowImcc=(BgFlowImcc)businApi.get(BgFlowImcc.class, billId);
        model.addAttribute("orderid", bgFlowImcc);
        model.addAttribute("close", close);
        return "/sup/order/bgimcc/listBgFlowImccGreenLight";
    }
    /**
     * 根据编号得到一个报关信息(放行)
     * @param id
     * @return
     */
    @RequestMapping("/editFlowImccNodeGreenLight/{id}/{billId}")
    public String editFlowImccNodeGreenLight(@PathVariable String id
            ,@PathVariable String billId ) {
         BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
         List<SupCompanyInfo> supplierList=bgFlowImccBusin.getSupplierList();
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("billId", billId);
        return "/sup/order/bgimcc/editFlowImccNodeGreenLight";
    }
    
    /**
     * 添加报关信息(放行)
     * @param flowImccNode
     * @param billId
     * @return
     */
    @RequestMapping("/saveFlowImccNodeGreenLight")
    public String saveFlowImccNodeGreenLight(BgFlowImccNode flowImccNode,String billId, String[] flowImccNodeFiles){
        EnumFlowNode node=new EnumFlowNode();
        flowImccNode.setNodeId(node.flownode("通关进口", "放行"));
        SupCompanyInfo sup=this.getCurrSup().getSupCompanyInfo();
        
        flowImccNode.setOperator(sup.getId());
        flowImccNode.setOperatorName(sup.getComName());
        flowImccNode.setLastUpdate(new Date());
        flowImccNode.setFlowNote(1);
        flowImccNode.setFlag(0);
        bgFlowImccBusin.saveFlowImccNode(flowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(flowImccNode, flowImccNodeFiles);
      //执行报关出口节点状态存储过程
        BgFlowImcc BgFlowImcc=(BgFlowImcc)businApi.get(BgFlowImcc.class, billId);
        //执行存储过程
        OrderFrom order=(OrderFrom)businApi.get(OrderFrom.class, BgFlowImcc.getOrderFrom());
        bgFlowExccBusin.updateorderstate("bgim", order.getId());
        return "redirect:/sup/flow/bgimccs/listBgFlowImccGreenLight/"+billId+"/"+"close";
    }
    
    /**
     * 详情页面(放行)
     * @param id
     * @return
     */
    @RequestMapping("/viewFlowImccNodeGreenLight/{id}/{billId}")
    public String viewFlowImccNodeGreenLight(@PathVariable String id
            ,@PathVariable String billId ) {
        BgFlowImccNode flowImccNode = bgFlowImccBusin.getFlowImccNode(id);
        SupCompanyInfo firstSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getFirstDept());
        SupCompanyInfo secondSupplier=bgFlowImccBusin.getSupplierById(flowImccNode.getSecDept());
        model.addAttribute("flowImccNode",flowImccNode);
        model.addAttribute("firstDept",firstSupplier);
        model.addAttribute("secondDept",secondSupplier);
        model.addAttribute("billId", billId);
        return "/sup/order/bgimcc/viewFlowImccNodeGreenLight";
    }
    /**
     * 跳转至进口报关订单页面
     * @return
     */
    @RequestMapping(value="/toFlowImccPage")
    public String toImccOrderPage(){
         model.addAttribute("flowId",VocationalWorkEnum.BG_IMCC.getId());
         model.addAttribute("complete",TaskState.YWC);
         model.addAttribute("handle",TaskState.CLZ);
         model.addAttribute("cansole",TaskState.QX);
         model.addAttribute("draft", TaskState.CG);
        return "/flow/bgimcc/FlowImccOrder";
    }
    /**
     * 查询进口报关订单列表
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listFlowImccOrder/{nodeType}/{flowId}")
    public String showFlowImccOrder(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean){
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
        return "/flow/bgimcc/listFlowImccOrder";
    }
    
}
