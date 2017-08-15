package com.sup.flow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;

@Controller
@RequestMapping("/sup/Ccexcc")
public class SupCcExccController extends OrganizeBaseController  {
    /**
     * 查询仓库出库订单的单据
     * @param id 订单ID
     * @param searchBean
     * @return
     */
    @RequestMapping("/listCcexcc/{id}")
    public String listCcexcc(@PathVariable String id,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        System.out.println(id+"id");
        String sql="select c.order_num,t.task_name,ty.flow_name,c.order_bill_id,c.node_state,c.node_date,n.node_name "
                + " ,c.obj_id as ob,ty.obj_id as tyid from CC_FLOW_EXCC c inner join PM_OrderFrom o on c.order_from_id=o.obj_id"
                + " left join PM_task t on c.task_id=t.obj_id "
                + " left join PM_FlowNode n on n.obj_id=c.node_id"
                + " left join PM_FlowType ty on ty.obj_id=o.tack_type_id"
                + " where c.order_from_id= ? ";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/sup/order/orderfrom/listCcexcc";
    }
    
    /**
     * 查看仓储出库单据流程节点
     * @param id
     * @param searchBean
     * @return
     */
    @RequestMapping("/listCcExccNode/{id}")
    public String listCcExccNode(@PathVariable String id,SearchBean searchBean){
        System.out.println("id"+id);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="SELECT a.obj_Id,a.state,node.node_name,"
                +"  a.order_receiving_date,a.customer_sign_date,a.driver_sign_date,a.delivery_addr"
                +" FROM CC_FLOW_EXCC_NODE a"
                +" LEFT JOIN PM_FlowNode node ON a.node_id = node.obj_Id"
                +" where  a.bill_id=?  and a.flag!=-1";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("billId",id);
        return "/sup/order/orderfrom/listCcExccNode";
    }
}
