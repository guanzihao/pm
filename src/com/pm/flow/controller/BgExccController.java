package com.pm.flow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;


/**
 * 查询报关出口的单据
 * @author hly
 *
 */

@Controller
@RequestMapping("/Bgexcc")
public class BgExccController  extends OrganizeBaseController{
    
    /**
     * 查询报关出口订单的单据
     * @param id 订单ID
     * @param searchBean
     * @return
     */
    @RequestMapping("/listBgexcc/{id}")
    public String listCcexcc(@PathVariable String id,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        System.out.println(id+"id");
        String sql="select c.bill_name,t.task_name,ty.flow_name "
                + " ,c.bussiness_no,c.custom_no,c.noe_state,c.node_date,n.node_name,c.obj_id as ob,ty.obj_id as tyid"
                + " from BG_FLOW_EMCC c inner join PM_OrderFrom o on c.order_from_id=o.obj_id"
                + " inner join PM_task t on c.task_id=t.obj_id "
                + " left join PM_FlowNode n on n.obj_id=c.node_id"
                + " left join PM_FlowType ty on ty.obj_id=o.tack_type_id"
                + " where c.order_from_id= ? ";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/order/orderfrom/listBgexcc";
    }
    
    /**
     * 查看出口报关单据流程节点
     * @param id
     * @param searchBean
     * @return
     */
    @RequestMapping("/listBgExccNode/{id}")
    public String listBgExccNode(@PathVariable String id,SearchBean searchBean){
        System.out.println("id"+id);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="SELECT exccNode.obj_Id,exccNode.status,node.node_name,"
                +" exccNode.bussiness_date,exccNode.inspection_date,exccNode.release_date"
                +" FROM BG_Flow_EXCC_Node exccNode"
                +" LEFT JOIN PM_FlowNode node ON exccNode.node_id = node.obj_Id"
                +" where  exccNode.bill_id=? and exccNode.flag!=-1";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("billId",id);
        return "/order/orderfrom/listBgExccNode";
    }
    
}
