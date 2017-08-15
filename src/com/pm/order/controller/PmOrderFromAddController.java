package com.pm.order.controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.order.bean.DingdanAddtion;
import com.pm.order.busin.OrderFromBusin;
import com.pm.organize.controller.OrganizeBaseController;
import com.sup.company.controller.SupBaseController;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.OrderFrom;

@Controller
@RequestMapping("/pm/order/add")
public class PmOrderFromAddController extends OrganizeBaseController {
    
    @Autowired
    private OrderFromBusin orderFromBusin;
    
    
    @RequestMapping("/listorderadd/{orderid}")
    public String listorderadd(@PathVariable String orderid,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="select a.obj_id, a.order_ID, a.order_type, a.create_user, a.obj_createDate,a.content,o.order_code from PM_OrderAddtion a "
                + "  inner join PM_OrderFrom o on o.obj_id=a.order_ID where  order_ID = '"+orderid+"'";
        pageBean.addQuerySQL(sql);
        pageBean.sqlquerys();
        model.addAttribute("orderid", orderid);
        OrderFrom order =(OrderFrom)businApi.get(OrderFrom.class, orderid);
        FlowType flowType=(FlowType)businApi.get(FlowType.class, order.getFlowType().getId());
        model.addAttribute("flowType", flowType);
        model.addAttribute("order", order);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("ids", this.getCurrUser().getId());
        if(this.getCurrUser().getId()!=null){
            model.addAttribute("user", "3");
        }else{
            model.addAttribute("user", "2");
        }
        return "/order/listorderAdd";
    }
    
    @RequestMapping("/editlistorderadd/{id}")
    public String addlistorder(@PathVariable String id){
        DingdanAddtion dingdanAddtions=(DingdanAddtion)businApi.get(DingdanAddtion.class, id);
        OrderFrom order=null;
        if(dingdanAddtions!=null){
            order =(OrderFrom)businApi.get(OrderFrom.class, dingdanAddtions.getOrderID());
        }else{
            order=(OrderFrom)businApi.get(OrderFrom.class, id);
        }
        FlowType flowType=(FlowType)businApi.get(FlowType.class, order.getFlowType().getId());
        model.addAttribute("flowType", flowType);
        model.addAttribute("order", order);
        if(dingdanAddtions!=null){
           
            model.addAttribute("dingdanAddtions", dingdanAddtions);
            
        }
        return "/order/editorderadd";
    }
    
    @RequestMapping("/savelistorderadd")
    public String savelistorder(DingdanAddtion dingdanAddtion, String[] typeIds, String[] infoFiles){
        DingdanAddtion dingdanAddtions=(DingdanAddtion)businApi.get(DingdanAddtion.class, dingdanAddtion.getId());
        if(dingdanAddtions!=null){
            dingdanAddtions.setContent(dingdanAddtion.getContent());
            frameworkBusin.saveUploadFileOwner(dingdanAddtions, infoFiles);
            businApi.save(dingdanAddtions);
        }else{
            dingdanAddtion.setCreateuser(this.getCurrUser().getId());
            businApi.save(dingdanAddtion);
            frameworkBusin.saveUploadFileOwner(dingdanAddtion, infoFiles);
        }
        return "redirect:/pm/order/add/viewlistorderadd/"+dingdanAddtion.getId();
    }
    
    
    @RequestMapping("/viewlistorderadd/{id}")
    public String viewlistorderadd(@PathVariable String id){
        DingdanAddtion dingdanAddtions=(DingdanAddtion)businApi.get(DingdanAddtion.class, id);
        OrderFrom order =(OrderFrom)businApi.get(OrderFrom.class, dingdanAddtions.getOrderID());
        FlowType flowType=(FlowType)businApi.get(FlowType.class, order.getFlowType().getId());
        model.addAttribute("flowType", flowType);
        model.addAttribute("order", order);
        model.addAttribute("dingdanAddtions", dingdanAddtions);
        return "/order/vieworderadd";
    }
    
    
    @RequestMapping("/removelistorderadd")
    @ResponseBody
    public void removeProduct(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    DingdanAddtion dingdanAddtion=(DingdanAddtion)businApi.get(DingdanAddtion.class, id);
                    if (dingdanAddtion != null) {
                        businApi.remove(dingdanAddtion);
                        JSONObject jo = new JSONObject();
                        jo.put("id", id);
                        jsonArray.put(jo);
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

}