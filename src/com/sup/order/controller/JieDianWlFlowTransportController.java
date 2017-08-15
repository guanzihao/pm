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
import com.sup.flow.bean.WlFlowTransport;
import com.sup.flow.bean.WlFlowTransportNode;
import com.sup.flow.busin.BgFlowExccBusin;
import com.sup.flow.busin.WlFlowTransportBusin;

/**
 * @Description: 物流运输模块
 * @author Chu Zhaocheng
 * @date 2017年6月13日 下午4:19:20
 */
@Controller
@RequestMapping("/sup/flow/wltransports")
public class JieDianWlFlowTransportController extends SupBaseController{
    @Autowired
    private BgFlowExccBusin bgFlowExccBusin;
   @Autowired
   private WlFlowTransportBusin wlFlowTransportBusin;
    

    /**
     * @Description 查看物流运输流程单据
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:20:10
     * @action listWlFlowTransport
     * @return String
     */
    @RequestMapping(value="/listWlFlowTransport/{billId}")
    public String listWlFlowTransport(@PathVariable String billId){
        String result="";
        String str = wlFlowTransportBusin.getWlFlowTransport(billId);
           switch (str) {
            case "接单":
                result="redirect:/sup/flow/wltransports/listWlFlowTransportOrderReceiving/"+billId+"/"+0;
                break;
            case "运输":
                result="redirect:/sup/flow/wltransports/listWlFlowTransporting/"+billId+"/"+0;
                break;
            case "到货签收":
                result="redirect:/sup/flow/wltransports/listWlFlowTransportArrivalReceipt/"+billId+"/"+0;
                break;
           }
            return result;
        }
    
    /**
     * @Description 接单节点查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:05:11
     * @action listWlFlowTransportOrderReceiving
     * @return String
     */
    @RequestMapping(value="/listWlFlowTransportOrderReceiving/{billId}/{close}")
    public String listWlFlowTransportOrderReceiving(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("物流运输", "接单");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT  a.id,a.endDate,a.state,a.oneAccessory,a.businessDate FROM WlFlowTransportNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        WlFlowTransport wlFlowExcc=(WlFlowTransport)businApi.get(WlFlowTransport.class, billId);
        model.addAttribute("orderid", wlFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/wltrop/listWlFlowTransportOrderReceiving";
    }

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
        return "/sup/order/wltrop/editWlFlowTransportNode";
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
        return "/sup/order/wltrop/viewWlFlowTransportNode";
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
        //执行报关出口节点状态存储过程
        WlFlowTransport wlFlowImcc=(WlFlowTransport)businApi.get(WlFlowTransport.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("wltrop", wlFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/wltransports/listWlFlowTransportOrderReceiving/"+billId+"/"+"close";
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
                    WlFlowTransportNode wlFlowTransportNode=(WlFlowTransportNode)businApi.get(WlFlowTransportNode.class, id);
                    //执行报关出口节点状态存储过程
                    WlFlowTransport wlFlowImcc=(WlFlowTransport)businApi.get(WlFlowTransport.class, wlFlowTransportNode.getBillId());
                    //执行存储过程
                    bgFlowExccBusin.updateorderstate("wltrop", wlFlowImcc.getOrderFromId());
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    
    /**
     * @Description 运输节点信息查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:59:52
     * @action listWlFlowTransporting
     * @return String
     */
    @RequestMapping(value="/listWlFlowTransporting/{billId}/{close}")
    public String listWlFlowTransporting(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("物流运输", "运输");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.vehicleDate,a.transDate,a.origin,a.destination FROM WlFlowTransportNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        WlFlowTransport wlFlowExcc=(WlFlowTransport)businApi.get(WlFlowTransport.class, billId);
        model.addAttribute("orderid", wlFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/wltrop/listWlFlowTransporting";
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
        return "/sup/order/wltrop/editWlFlowTransportNodeTransporting";
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
        return "/sup/order/wltrop/viewWlFlowTransportNodeTransporting";
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
      //执行报关出口节点状态存储过程
        WlFlowTransport wlFlowImcc=(WlFlowTransport)businApi.get(WlFlowTransport.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("wltrop", wlFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/wltransports/listWlFlowTransporting/"+billId+"/"+"close";
    }
    
    
    /**
     * @Description 到货签收节点信息查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:59:52
     * @action listWlFlowTransportArrivalReceipt
     * @return String
     */
    @RequestMapping(value="/listWlFlowTransportArrivalReceipt/{billId}/{close}")
    public String listWlFlowTransportArrivalReceipt(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("物流运输", "到货签收");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.transDate FROM WlFlowTransportNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        WlFlowTransport wlFlowExcc=(WlFlowTransport)businApi.get(WlFlowTransport.class, billId);
        model.addAttribute("orderid", wlFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/wltrop/listWlFlowTransportArrivalReceipt";
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
        return "/sup/order/wltrop/editWlFlowTransportNodeArrivalReceipt";
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
        return "/sup/order/wltrop/viewWlFlowTransportNodeArrivalReceipt";
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
      //执行报关出口节点状态存储过程
        WlFlowTransport wlFlowImcc=(WlFlowTransport)businApi.get(WlFlowTransport.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("wltrop", wlFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/wltransports/listWlFlowTransportArrivalReceipt/"+billId+"/"+"close";
    }
    /**
     * 跳转至物流运输订单页面
     * @return
     */
    @RequestMapping(value="/toWlTransportOrder")
    public String toWlTransportOrderPage(){
        model.addAttribute("flowId",VocationalWorkEnum.WL_TRANSPORT.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        return "flow/wltransport/listWlFlowTransportOrder";
    }
    /**
     *  显示物流运输的列表信息
     * @param nodeType   状态类型ID
     * @param searchBean 参数列表
     * @return
     */
    @RequestMapping(value="/showWlTransportOrderList/{nodeType}/{flowId}")
    public  String showWlTransportOrderlist(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state \n");
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
        model.addAttribute("flowId",flowId);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("pageBean", pageBean);
        return "/flow/wltransport/listWlFlowTransportOrderDetail";
        
    }
    
}
