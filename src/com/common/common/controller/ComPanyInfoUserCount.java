package com.common.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.model.Contexts;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.task.bean.AjaxDomain;
import com.sup.order.bean.OrderFrom;

@Controller
@RequestMapping("/common/ComPanyCount")
public class ComPanyInfoUserCount extends OrganizeBaseController {

   
    /**
     * 获取已经认证的客户以及服务商数量
     * @throws IOException 
     */
    @RequestMapping(value = "/companycount")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public AjaxDomain companycount()  {
        AjaxDomain ajaxDomain=new AjaxDomain();
        int comcount=businApi.getQueryPageSize("select a.id from CompanyInfo a  where a.comState = ? ",new Object[] {Contexts.Y });
        int supcount=businApi.getQueryPageSize("select a.id from SupCompanyInfo a  where a.comState = ? ",new Object[] {Contexts.Y });
        
        List<OrderFrom> ordercount=businApi.getQueryList("from OrderFrom a  where a.orderState = ? ",new Object[] {7});
        List<OrderFrom> ordercountinfo=businApi.getQueryList("from OrderFrom a  where a.orderState <> ? ",new Object[] {5});
        Map<String, Object> map=new HashMap<String, Object>();
        
        map.put("com", comcount);
        map.put("sup", supcount);
        map.put("order", ordercount.size());
        map.put("ordercountinfo", ordercountinfo.size());
        ajaxDomain.setMap(map);
        return ajaxDomain;
        
    }
}
