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
import com.sup.flow.bean.CcFlowImcc;
import com.sup.flow.bean.CcFlowImccNode;
import com.sup.flow.busin.BgFlowExccBusin;
import com.sup.flow.busin.CcFlowImccBusin;

/**
 * @Description: 仓库入库模块
 * @author Chu Zhaocheng
 * @date 2017年6月13日 下午4:19:20
 */
@Controller
@RequestMapping("/sup/flow/ccimccs")
public class JieDianCcFlowImccController extends SupBaseController{
    @Autowired
    private BgFlowExccBusin bgFlowExccBusin;
   @Autowired
   private CcFlowImccBusin ccFlowImccBusin;
    
    /**
     * @Description 查看仓储入库流程单据
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:20:10
     * @action listCcFlowImcc
     * @return String
     */
    @RequestMapping(value="/listCcFlowImcc/{billId}")
    public String listCcFlowImcc(@PathVariable String billId){
        String result="";
        String str = ccFlowImccBusin.getCcFlowImcc(billId);
           switch (str) {
            case "接单登记":
                result="redirect:/sup/flow/ccimccs/listCcFlowImccOrderReceiving/"+billId+"/"+0;
                break;
            case "车辆到达":
                result="redirect:/sup/flow/ccimccs/listCcFlowImccVehicleArrives/"+billId+"/"+0;
                break;
            case "入库签收":
                result="redirect:/sup/flow/ccimccs/listCcFlowImccStoredSign/"+billId+"/"+0;
                break;
           }
            return result;
        }
    
    /**
     * @Description 接单节点查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:05:11
     * @action listCcFlowImccOrderReceiving
     * @return String
     */
    @RequestMapping(value="/listCcFlowImccOrderReceiving/{billId}/{close}")
    public String listCcFlowImccOrderReceiving(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储进库", "接单登记");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT  a.id,a.endDate,a.state,a.oneAccessory,a.orderReceivingDate FROM CcFlowImccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        CcFlowImcc ccim=(CcFlowImcc)businApi.get(CcFlowImcc.class, billId);
        model.addAttribute("orderid", ccim);
        model.addAttribute("close", close);
        return "/sup/order/ccimcc/listCcFlowImccOrderReceiving";
    }

    /**
     * @Description 接单节点编辑回显信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:55:34
     * @action editCcFlowImccNode
     * @return String
     */
    @RequestMapping("/editCcFlowImccNode/{id}/{billId}")
    public String editCcFlowImccNode(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccimcc/editCcFlowImccNode";
    }
    
    /**
     * @Description 接单节点详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:56:55
     * @action viewCcFlowImccNode
     * @return String
     */
    @RequestMapping("/viewCcFlowImccNode/{id}/{billId}")
    public String viewCcFlowImccNode(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccimcc/viewCcFlowImccNode";
    }
    
    /**
     * @Description 接单节点添加仓储入库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowImccNode
     * @return String
     */
    @RequestMapping("/saveCcFlowImccNode")
    public String saveCcFlowImccNode(CcFlowImccNode ccFlowImccNode,String billId,String[] ccFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储进库", "接单登记");
        ccFlowImccNode.setNodeId(nodeId);
        ccFlowImccNode.setFlag(0);
        ccFlowImccBusin.saveCcFlowImccNode(ccFlowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowImccNode, ccFlowImccNodeFiles);
        //执行报关出口节点状态存储过程
        CcFlowImcc ccFlowImcc=(CcFlowImcc)businApi.get(CcFlowImcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("ccim", ccFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/ccimccs/listCcFlowImccOrderReceiving/"+billId+"/"+"close";
    }
    
    /**
     * @Description 删除接单节点明细
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:13:13
     * @action removeCcFlowImccNode
     * @return void
     */
    @RequestMapping("/removeCcFlowImccNode")
    @ResponseBody
    public void removeCcFlowImccNode(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    ccFlowImccBusin.removeCcFlowImccNode(id);
                    CcFlowImccNode ccFlowImccNode=(CcFlowImccNode)businApi.get(CcFlowImccNode.class, id);
                    //执行报关出口节点状态存储过程
                    CcFlowImcc ccFlowImcc=(CcFlowImcc)businApi.get(CcFlowImcc.class, ccFlowImccNode.getBillId());
                    //执行存储过程
                    bgFlowExccBusin.updateorderstate("ccim", ccFlowImcc.getOrderFromId());
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    
    /**
     * @Description 车辆到达节点信息查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:59:52
     * @action listCcFlowImccVehicleArrives
     * @return String
     */
    @RequestMapping(value="/listCcFlowImccVehicleArrives/{billId}/{close}")
    public String listCcFlowImccVehicleArrives(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储进库", "车辆到达");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.inAddr,a.actualStoringDate FROM CcFlowImccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        CcFlowImcc ccim=(CcFlowImcc)businApi.get(CcFlowImcc.class, billId);
        model.addAttribute("orderid", ccim);
        model.addAttribute("close", close);
        return "/sup/order/ccimcc/listCcFlowImccVehicleArrives";
    }
    
    /**
     * @Description 车辆到达节点信息回显
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午7:30:31
     * @action editFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/editCcFlowImccNodeVehicleArrives/{id}/{billId}")
    public String editCcFlowImccNodeVehicleArrives(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccimcc/editCcFlowImccNodeVehicleArrives";
    }
    
    /**
     * @Description 车辆到达节点信息详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午8:04:30
     * @action viewFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/viewCcFlowImccNodeVehicleArrives/{id}/{billId}")
    public String viewCcFlowImccNodeVehicleArrives(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccimcc/viewCcFlowImccNodeVehicleArrives";
    }
    
    /**
     * @Description 车辆到达节点添加仓储入库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowImccNodeVehicleArrives
     * @return String
     */
    @RequestMapping("/saveCcFlowImccNodeVehicleArrives")
    public String saveCcFlowImccNodeVehicleArrives(CcFlowImccNode ccFlowImccNode,String billId, String[] ccFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储进库", "车辆到达");
        ccFlowImccNode.setNodeId(nodeId);
        ccFlowImccNode.setFlag(0);
        ccFlowImccBusin.saveCcFlowImccNode(ccFlowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowImccNode, ccFlowImccNodeFiles);
        //执行报关出口节点状态存储过程
        CcFlowImcc ccFlowImcc=(CcFlowImcc)businApi.get(CcFlowImcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("ccim", ccFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/ccimccs/listCcFlowImccVehicleArrives/"+billId+"/"+"close";
    }
    
    
    /**
     * @Description 入库签收节点信息查询
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:59:52
     * @action listCcFlowImccStoredSign
     * @return String
     */
    @RequestMapping(value="/listCcFlowImccStoredSign/{billId}/{close}")
    public String listCcFlowImccStoredSign(@PathVariable String billId,SearchBean searchBean,@PathVariable String close){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储进库", "入库签收");
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.endDate,a.state,a.oneAccessory,a.storedFinishDate FROM CcFlowImccNode a where a.flag=0 and a.billId=? and nodeId=?");
        pageBean.addParams(billId);
        pageBean.addParams(nodeId);
        pageBean.addOrderBy("a.state,a.endDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("billId", billId);
        CcFlowImcc ccim=(CcFlowImcc)businApi.get(CcFlowImcc.class, billId);
        model.addAttribute("orderid", ccim);
        model.addAttribute("close", close);
        return "/sup/order/ccimcc/listCcFlowImccStoredSign";
    }
    
    /**
     * @Description 入库签收节点信息回显
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午7:30:31
     * @action editCcFlowImccNodeStoredSign
     * @return String
     */
    @RequestMapping("/editCcFlowImccNodeStoredSign/{id}/{billId}")
    public String editCcFlowImccNodeStoredSign(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccimcc/editCcFlowImccNodeStoredSign";
    }
    
    /**
     * @Description 入库签收节点信息详情
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午8:04:30
     * @action viewFlowExccNodeCheck
     * @return String
     */
    @RequestMapping("/viewCcFlowImccNodeStoredSign/{id}/{billId}")
    public String viewCcFlowImccNodeStoredSign(@PathVariable String id, @PathVariable String billId) {
        CcFlowImccNode ccFlowImccNode = ccFlowImccBusin.getCcFlowImccNode(id);
        model.addAttribute("ccFlowImccNode",ccFlowImccNode);
        model.addAttribute("billId", billId);
        return "/sup/order/ccimcc/viewCcFlowImccNodeStoredSign";
    }
    
    /**
     * @Description 入库签收节点添加仓储入库信息
     * @author Chu Zhaocheng
     * @date 2017年6月13日 下午4:14:29
     * @action saveCcFlowImccNodeStoredSign
     * @return String
     */
    @RequestMapping("/saveCcFlowImccNodeStoredSign")
    public String saveCcFlowImccNodeStoredSign(CcFlowImccNode ccFlowImccNode,String billId, String[] ccFlowImccNodeFiles){
        EnumFlowNode enumFlowNode=new EnumFlowNode();
        String nodeId=enumFlowNode.flownode("仓储进库", "入库签收");
        ccFlowImccNode.setNodeId(nodeId);
        ccFlowImccNode.setFlag(0);
        ccFlowImccBusin.saveCcFlowImccNode(ccFlowImccNode,billId);
        frameworkBusin.saveUploadFileOwner(ccFlowImccNode, ccFlowImccNodeFiles);
      //执行报关出口节点状态存储过程
        CcFlowImcc ccFlowImcc=(CcFlowImcc)businApi.get(CcFlowImcc.class, billId);
        //执行存储过程
        bgFlowExccBusin.updateorderstate("ccim", ccFlowImcc.getOrderFromId());
        return "redirect:/sup/flow/ccimccs/listCcFlowImccStoredSign/"+billId+"/"+"close";
    }
    /**
     * 跳转至仓储进库订单页面
     * @return
     */
    @RequestMapping("/toCCFlowImccOrder")
    public String toCcFlowImccOrderPage(){
        model.addAttribute("flowId",VocationalWorkEnum.CC_IMCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        return "/flow/ccimcc/listCcFlowImccOrder";
    }
    /**
     * 显示仓储入库订单列表信息
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/showCcFlowImccOrderList/{nodeType}/{flowId}")
    public String showCcFlowImccOrderList(@PathVariable String nodeType,@PathVariable String flowId,SearchBean searchBean){
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
        return "/flow/ccimcc/listCcFlowImccOrderDetail";
        
    }
}
