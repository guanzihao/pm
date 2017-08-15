package com.sup.flow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;
@Controller
@RequestMapping("/sup/Wmimcc")
public class SupWmImccController extends OrganizeBaseController {
    /**
     * 查询外贸进口订单的单据
     * @param id 订单ID
     * @param searchBean
     * @return
     */
    @RequestMapping("/listWmimcc/{id}")
    public String listCcexcc(@PathVariable String id,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        System.out.println(id+"id");
        String sql="select c.bill_name,t.task_name,ty.flow_name ,c.node_state,c.node_date,n.node_name,c.payamount "
                + " ,c.obj_id as ob,ty.obj_id as tyid from WM_FLOW_IMCC c inner join PM_OrderFrom o on c.order_from_id=o.obj_id"
                + " left join PM_task t on c.task_id=t.obj_id "
                + " left join PM_FlowNode n on n.obj_id=c.node_id"
                + " left join PM_FlowType ty on ty.obj_id=o.tack_type_id"
                + " where c.order_from_id= ? ";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/sup/order/orderfrom/listWmimcc";
    }
    
    /**
     * 查看外贸进口单据流程节点
     * @param id
     * @param searchBean
     * @return
     */
    @RequestMapping("/listWmImccNode/{id}")
    public String listWmImccNode(@PathVariable String id,SearchBean searchBean){
        System.out.println("id"+id);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="SELECT a.obj_Id,a.state,node.node_name,"
                +" a.arrival_date,a.custom_clear_date,a.lc_start_date,a.contract_start_date,"
                +" a.pay_time,a.pay_amount,a.rec_currency,a.pay_currency,a.div_date,a.rec_amount,a.balance_date"
                +" FROM WM_Flow_IMCC_Node a"
                +" LEFT JOIN PM_FlowNode node ON a.node_id = node.obj_Id"
                +" where  a.bill_id=? and a.flag!=-1";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("billId",id);
        return "/sup/order/orderfrom/listWmImccNode";
    }
}
