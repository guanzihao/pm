package com.sup.flow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.busin.BgFlowExccBusin;
import com.sup.order.bean.OrderFrom;


/**
 * 查询报关出口的单据
 * @author hly
 *
 */

@Controller
@RequestMapping("/sup/orderCccontroller")
public class OrderCcController  extends SupBaseController{
    
    @Autowired
    private BgFlowExccBusin bgFlowExccBusin;
    
   /**
    * 查看子订单下的单据
    * @param orderid 子订单id
    * @param searchBean
    * @return
    */
    @RequestMapping("/orderCc/{orderid}/{type}")
    public String listCcexcc(@PathVariable String orderid,SearchBean searchBean,@PathVariable String type){
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="";
        String cctype="";
        switch (type) {
        case "bgex":
            sql="select obj_id,bill_name  from BG_Flow_EMCC where order_from_id =?";
            cctype="/sup/flow/bgexccs/listBgFlowExcc";
            break;
        case "bgim":
            sql="select obj_id,bill_name  from BG_Flow_IMCC where order_from_id =?";
            cctype="/sup/flow/bgimccs/listBgFlowImcc";
            break;
        case "wmim":
            sql="select obj_id,bill_name  from WM_Flow_IMCC where order_from_id =?";
            cctype="/sup/flow/wmimccs/listWmFlowImcc";
            break;
        case "wmex":
            sql="select obj_id,bill_name  from WM_Flow_EXCC where order_from_id =?";
            cctype="/sup/flow/wmexccs/listWmFlowExcc";
            break;
        case "wlim":
            sql="select obj_id,bill_name  from WL_Flow_IMCC where order_from_id =?";
            cctype="/sup/flow/wlimccs/listWlFlowImcc";
            break;
        case "wlex":
            sql="select obj_id,bill_name  from WL_Flow_EXCC where order_from_id =?";
            cctype="/sup/flow/wlexccs/listWlFlowExcc";
            break;
        case "wltrop":
            sql="select obj_id,bill_name  from WL_Flow_Transport where order_from_id =?";
            cctype="/sup/flow/wltransports/listWlFlowTransport";
            break;
        case "ccim":
            sql="select obj_id,bill_name  from CC_FLOW_IMCC where order_from_id =?";
            cctype="/sup/flow/ccimccs/listCcFlowImcc";
            break;
        case "ccex":
            sql="select obj_id,bill_name  from CC_FLOW_EXCC where order_from_id =?";
            cctype="/sup/flow/ccexccs/listCcFlowExcc";
            break;
        }
        pageBean.addQuerySQL(sql);
        pageBean.addParams(orderid);
        if(searchBean.getSearchName1() !=null && searchBean.getSearchName1().length()>0){
            pageBean.addQuerySQL(" and bill_name like '%"+searchBean.getSearchName1()+"%'");
        }
        pageBean.sqlquery();
        model.addAttribute("type", type);
       
        model.addAttribute("orderid",  businApi.get(OrderFrom.class, orderid));
       
        model.addAttribute("cctype", cctype);
        model.addAttribute("pageBean", pageBean);
        return "/sup/flow/orderCc";
    }
    
}
