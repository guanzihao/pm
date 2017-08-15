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
import com.sup.flow.bean.CcFlowExcc;
import com.sup.flow.bean.CcFlowExccNode;
import com.sup.flow.busin.BgFlowExccBusin;
import com.sup.flow.busin.CcFlowExccBusin;

/**
 * @Description: 仓库出库模块
 * @author Chu Zhaocheng
 * @date 2017年6月13日 下午4:19:20
 */
@Controller
@RequestMapping("/sup/flow/ccexccs")
public class JieDianCcFlowExccController extends SupBaseController{
    
   @Autowired
   private CcFlowExccBusin ccFlowExccBusin;
   @Autowired
   private BgFlowExccBusin bgFlowExccBusin;
    /**
     * @Description 查看仓储出库流程单据
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:20:10
     * @action listCcFlowExcc
     * @return String
     */
    @RequestMapping(value="/listCcFlowExcc/{billId}")
    public String listCcFlowExcc(@PathVariable String billId){
        String result="";
        String str = ccFlowExccBusin.getCcFlowExcc(billId);
           switch (str) {
            case "接单登记":
                result="redirect:/sup/flow/ccexccs/listCcFlowExccOrderReceiving/" + billId+"/"+0;
                break;
            case "出库":
                result="redirect:/sup/flow/ccexccs/listCcFlowExccExport/" + billId+"/"+0;
                break;
            case "签收":
                result="redirect:/sup/flow/ccexccs/listCcFlowExccSigning/" + billId+"/"+0;
                break;
           }
            return result;
        }
    
    /**
     * @Description 接单节点查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:05:11
     * @action listCcFlowExccOrderReceiving
     * @return String
     */
    @RequestMapping(value="/listCcFlowExccOrderReceiving/{billId}/{close}")
    public String listCcFlowExccOrderReceiving(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储出库", "接单登记");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.orderReceivingDate FROM CcFlowExccNode a where  a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        CcFlowExcc ccFlowExcc=(CcFlowExcc)businApi.get(CcFlowExcc.class, billId);
        model.addAttribute("orderid", ccFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/ccexcc/listCcFlowExccOrderReceiving";
    }

    /**
     * @Description 接单节点编辑回显信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:55:34
     * @action editCcFlowExccNode
     * @return String
     */
    @RequestMapping("/editCcFlowExccNode/{id}/{billId}")
    public String editCcFlowExccNode(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccexcc/editCcFlowExccNode";
    }
    
    /**
     * @Description 接单节点详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:56:55
     * @action viewCcFlowExccNode
     * @return String
     */
    @RequestMapping("/viewCcFlowExccNode/{id}/{billId}")
    public String viewCcFlowExccNode(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccexcc/viewCcFlowExccNode";
    }
    
    /**
     * @Description 接单节点添加仓储出库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowExccNode
     * @return String
     */
    @RequestMapping("/saveCcFlowExccNode")
    public String saveCcFlowExccNode(CcFlowExccNode ccFlowExccNode,String billId, String[] ccFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储出库", "接单登记");
        ccFlowExccNode.setNodeId(nodeId);
        ccFlowExccNode.setFlag(0);
        ccFlowExccBusin.saveCcFlowExccNode(ccFlowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowExccNode, ccFlowExccNodeFiles);
        //执行报关出口节点状态存储过程
        CcFlowExcc CcFlowExcc=(CcFlowExcc)businApi.get(CcFlowExcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("ccex", CcFlowExcc.getOrderFromId());
        return "redirect:/sup/flow/ccexccs/listCcFlowExccOrderReceiving/"  + billId+"/"+"close";
    }
    
    /**
     * @Description 删除接单节点明细
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:13:13
     * @action removeCcFlowExccNode
     * @return void
     */
    @RequestMapping("/removeCcFlowExccNode")
    @ResponseBody
    public void removeCcFlowExccNode(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    ccFlowExccBusin.removeCcFlowExccNode(id);
                    CcFlowExccNode ccFlowExccNode=(CcFlowExccNode)businApi.get(CcFlowExccNode.class, id);
                  //执行报关出口节点状态存储过程
                    CcFlowExcc CcFlowExcc=(CcFlowExcc)businApi.get(CcFlowExcc.class, ccFlowExccNode.getBillId());
                    //执行存储过程
                    bgFlowExccBusin.updateorderstate("ccex", CcFlowExcc.getOrderFromId());
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    
    /**
     * @Description 出库节点信息查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:59:52
     * @action listCcFlowExccExport
     * @return String
     */
    @RequestMapping(value="/listCcFlowExccExport/{billId}/{close}")
    public String listCcFlowExccExport(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储出库", "出库");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.deliveryAddr,a.driverSignDate FROM CcFlowExccNode a where  a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        CcFlowExcc ccFlowExcc=(CcFlowExcc)businApi.get(CcFlowExcc.class, billId);
        model.addAttribute("orderid", ccFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/ccexcc/listCcFlowExccExport";
    }
    
    /**
     * @Description 出库节点信息回显
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午7:30:31
     * @action editCcFlowExccNodeExport
     * @return String
     */
    @RequestMapping("/editCcFlowExccNodeExport/{id}/{billId}")
    public String editCcFlowExccNodeExport(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccexcc/editCcFlowExccNodeExport";
    }
    
    /**
     * @Description 出库节点信息详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午8:04:30
     * @action viewFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/viewCcFlowExccNodeExport/{id}/{billId}")
    public String viewCcFlowExccNodeExport(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccexcc/viewCcFlowExccNodeExport";
    }
    
    /**
     * @Description 出库节点添加仓储出库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowExccNodeExport
     * @return String
     */
    @RequestMapping("/saveCcFlowExccNodeExport")
    public String saveCcFlowExccNodeExport(CcFlowExccNode ccFlowExccNode,String billId, String[] ccFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储出库", "出库");
        ccFlowExccNode.setNodeId(nodeId);
        ccFlowExccNode.setFlag(0);
        ccFlowExccBusin.saveCcFlowExccNode(ccFlowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowExccNode, ccFlowExccNodeFiles);
      //执行报关出口节点状态存储过程
        CcFlowExcc CcFlowExcc=(CcFlowExcc)businApi.get(CcFlowExcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("ccex", CcFlowExcc.getOrderFromId());
        return "redirect:/sup/flow/ccexccs/listCcFlowExccExport/"  + billId+"/"+"close";
    }
    
    
    /**
     * @Description 签收节点信息查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:59:52
     * @action listCcFlowExccSigning
     * @return String
     */
    @RequestMapping(value="/listCcFlowExccSigning/{billId}/{close}")
    public String listCcFlowExccSigning(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储出库", "签收");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.customerSignDate FROM CcFlowExccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        CcFlowExcc ccFlowExcc=(CcFlowExcc)businApi.get(CcFlowExcc.class, billId);
        model.addAttribute("orderid", ccFlowExcc);
        model.addAttribute("close", close);
        return "/sup/order/ccexcc/listCcFlowExccSigning";
    }
    
    /**
     * @Description 签收节点信息回显
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午7:30:31
     * @action editCcFlowExccNodeSigning
     * @return String
     */
    @RequestMapping("/editCcFlowExccNodeSigning/{id}/{billId}")
    public String editCcFlowExccNodeSigning(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccexcc/editCcFlowExccNodeSigning";
    }
    
    /**
     * @Description 签收节点信息详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午8:04:30
     * @action viewFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/viewCcFlowExccNodeSigning/{id}/{billId}")
    public String viewCcFlowExccNodeSigning(@PathVariable String id, @PathVariable String billId) {
        CcFlowExccNode ccFlowExccNode = ccFlowExccBusin.getCcFlowExccNode(id);
        model.addAttribute("ccFlowExccNode",ccFlowExccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccexcc/viewCcFlowExccNodeSigning";
    }
    
    /**
     * @Description 签收节点添加仓储出库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowExccNodeSigning
     * @return String
     */
    @RequestMapping("/saveCcFlowExccNodeSigning")
    public String saveCcFlowExccNodeSigning(CcFlowExccNode ccFlowExccNode,String billId, String[] ccFlowExccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储出库", "签收");
        ccFlowExccNode.setNodeId(nodeId);
        ccFlowExccNode.setFlag(0);
        ccFlowExccBusin.saveCcFlowExccNode(ccFlowExccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowExccNode, ccFlowExccNodeFiles);
      //执行报关出口节点状态存储过程
        CcFlowExcc CcFlowExcc=(CcFlowExcc)businApi.get(CcFlowExcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("ccex", CcFlowExcc.getOrderFromId());
        return "redirect:/sup/flow/ccexccs/listCcFlowExccSigning/"  + billId+"/"+"close";
    }
    /**
     * 跳转至can订单页面
     * @return
     */
    @RequestMapping("/toCCFlowExccOrder")
    public String toCcFlowExccOrderPage(){
        model.addAttribute("flowId",VocationalWorkEnum.CC_EXCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        return "/flow/ccexcc/listCcFlowOrder";
    }
    /**
     * 显示仓储出口订单列表信息
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/showCcFlowExccOrderList/{nodeType}/{flowId}")
    public String showCcFlowExccOrderList(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state\n");
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
        return "/flow/ccexcc/listCcFlowOrderDetail";
        
    }
}
