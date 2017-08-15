package com.common.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;

@Controller
@RequestMapping("/common/orderquotation")
public class OrderQuotationController  extends OrganizeBaseController {
    /**
     * 服务报价跳转页面
     * @param flowType
     * @return
     */
    @RequestMapping(value = "/orderQutation/{flowType}")
    public String toQutationLogin(@PathVariable String flowType){
        
        UserAccount user=getCurrUser();
        if(user!=null){
            if(flowType.equals("1")){
                model.addAttribute("url", "/pm/pm/consignationQuote/listBgConsignationQuote");
            }else if(flowType.equals("2")){
                model.addAttribute("url", "/pm/pm/consignationQuote/listWlConsignationQuote");
            }else if(flowType.equals("3")){
                model.addAttribute("url","/pm/pm/consignationQuote/listCcConsignationQuote");
            }else if(flowType.equals("4")){
                model.addAttribute("url","/pm/pm/consignationQuote/listWmConsignationQuote");
            }
            return "/organize/index";
        }else{
            model.addAttribute("urlMenthod","consignationQuote");
            if(flowType.equals("1")){
                model.addAttribute("flowType","Bg");
            }else if(flowType.equals("3")){
                model.addAttribute("flowType", "Cc");
            }else if(flowType.equals("2")){
                model.addAttribute("flowType","Wl");
            }else if(flowType.equals("4")){
                model.addAttribute("flowType","Wm");
            }
            return "/internet/userconfig/adminLogin";
        }
    }
}
