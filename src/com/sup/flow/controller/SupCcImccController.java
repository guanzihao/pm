package com.sup.flow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;
/**
 * 查询显示仓储入库的单据详情信息
 * @author litao
 *
 */
@Controller
@RequestMapping("/sup/Ccimcc")
public class SupCcImccController extends OrganizeBaseController  {
    
    /**
     * 查询仓库入库进口的单据
     * @param id 订单ID
     * @param searchBean
     * @return
     */
    @RequestMapping("/listCcimcc/{id}")
    public String listCcexcc(@PathVariable String id,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        System.out.println(id+"id");
        String sql="select c.bill_name,t.task_name,ty.flow_name,c.order_bill_no "
                + " ,c.node_state,c.node_date,n.node_name"
                + " ,c.obj_id as ob,ty.obj_id as tyid from CC_FLOW_IMCC c inner join PM_OrderFrom o on c.order_from_id=o.obj_id"
                + " left join PM_task t on c.task_id=t.obj_id"
                + " left join PM_FlowType ty on ty.obj_id=o.tack_type_id"
                + " left join PM_FlowNode n on n.obj_id=c.node_id"
                + " where c.order_from_id= ? ";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/sup/order/orderfrom/listCcimcc";
    }
    /**
     * 查看仓储入库单据流程节点
     * @param id
     * @param searchBean
     * @return
     */
    @RequestMapping("/listCcImccNode/{id}")
    public String listBgExccNode(@PathVariable String id,SearchBean searchBean){
        System.out.println("id"+id);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="SELECT ccImccNode.obj_Id,ccImccNode.state,node.node_name,"
                +" ccImccNode.bussiness_date,ccImccNode.stored_finish_date,ccImccNode.actual_storing_date,ccImccNode.in_addr"
                +" FROM CC_FLOW_IMCC_NODE ccImccNode"
                +" LEFT JOIN PM_FlowNode node ON ccImccNode.node_id = node.obj_Id"
                +" where  ccImccNode.bill_id=? and ccImccNode.flag!=-1";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("billId",id);
        return "/sup/order/orderfrom/listCcImccNode";
    }
}
