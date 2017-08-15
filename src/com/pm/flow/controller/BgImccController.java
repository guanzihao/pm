package com.pm.flow.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pm.core.busin.BusinApi;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.order.busin.OrderFromBusin;
import com.pm.organize.controller.OrganizeBaseController;
import com.sup.flow.busin.BgFlowImccBusin;



/**
 * 查询报关进口的单据
 * @author hly
 *
 */

@Controller
@RequestMapping("/Bgimcc")
public class BgImccController  extends OrganizeBaseController{
    @Autowired
    private BusinApi businApi;
    @Autowired
    private OrderFromBusin orderFormBusin;
    @Autowired
    private BgFlowImccBusin bgFlowImccBusin;
    /**
     * 查询报关进口订单的单据节点信息
     * @param id 订单ID
     * @param searchBean
     * @return
     */
    @RequestMapping("/listBgimccNode/{id}")
    public String listBgFlowImccNode(@PathVariable String id,SearchBean searchBean){
        System.out.println("id"+id);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="SELECT imccNode.obj_Id,imccNode.status,node.node_name,"
                +" imccNode.bussiness_date,imccNode.inspection_date,imccNode.release_date,imccNode.bill_id"
                +" FROM BG_Flow_IMCC_Node imccNode"
                +" LEFT JOIN PM_FlowNode node ON imccNode.node_id = node.obj_Id"
                +" where  imccNode.bill_id=? and imccNode.flag!=-1";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean",pageBean);
        return "/order/orderfrom/listBgImccNode";
    }
    /**
     * 查询报关进口单据信息
     * @param id
     * @param searchBean
     * @return
     */
    @RequestMapping("/listBgImcc/{id}")
    public String list(@PathVariable String id,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        System.out.println(id+"id");
        String sql="select c.bill_name,t.task_name,ty.flow_name "
                + " ,c.bussiness_no,c.custom_no,c.node_state,c.node_date,n.node_name,c.obj_id as ob"
                + " from BG_FLOW_IMCC c"
                + " inner join PM_OrderFrom o on c.order_from_id=o.obj_id"
                + " left join PM_task t on c.task_id=t.obj_id "
                + " left join PM_FlowNode n on n.obj_id=c.node_id"
                + " left join PM_FlowType ty on ty.obj_id=o.tack_type_id"
                + " where c.order_from_id= ? ";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/order/orderfrom/listBgimcc";
    }
    
}
