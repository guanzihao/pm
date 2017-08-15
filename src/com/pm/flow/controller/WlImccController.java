package com.pm.flow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.sysconfig.bean.EnumFlowNode;


/**
 * 查询物流进口的单据
 * @author hly
 *
 */

@Controller
@RequestMapping("/Wlimcc")
public class WlImccController  extends OrganizeBaseController{
    
    /**
     * 查询物流进口订单的单据
     * @param id 订单ID
     * @param searchBean
     * @return
     */
    @RequestMapping("/listWlimcc/{id}")
    public String listCcexcc(@PathVariable String id,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        System.out.println(id+"id");
        EnumFlowNode ss=new EnumFlowNode();
        String sql="select c.bill_name,t.task_name,ty.flow_name,c.node_state,c.node_date,n.node_name,c.mblNo,c.hblNo"
                + " ,c.obj_id as ob,ty.obj_id as tyid from WL_FLOW_IMCC c inner join PM_OrderFrom o on c.order_from_id=o.obj_id"
                + " left join PM_task t on c.task_id=t.obj_id "
                + " left join PM_FlowNode n on n.obj_id=c.node_id"
                + " left join PM_FlowType ty on ty.obj_id=o.tack_type_id"
                + " left join WL_FLOW_EXCC_Node no on no.bill_id=c.obj_Id and no.node_id='"+ss.flownode("物流出口", "订舱")+"' "
                + " where c.order_from_id= ? ";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/order/orderfrom/listWlimcc";
    }
    
    /**
     * 查看物流进口单据流程节点
     * @param id
     * @param searchBean
     * @return
     */
    @RequestMapping("/listWlImccNode/{id}")
    public String listWlImccNode(@PathVariable String id,SearchBean searchBean){
        System.out.println("id"+id);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="SELECT a.obj_Id,a.state,node.node_name,"
                +" a.accept_date,a.sail_date,a.arrival_date,a.switch_bill_date,a.release_date,"
                +" a.vehicle_date,a.trans_date"
                +" FROM WL_Flow_IMCC_Node a"
                +" LEFT JOIN PM_FlowNode node ON a.node_id = node.obj_Id"
                +" where  a.bill_id=? and  a.flag!=-1";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("billId",id);
        return "/order/orderfrom/listWlImccNode";
    }
    
}
