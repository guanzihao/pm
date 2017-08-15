package com.sup.company.controller;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.integrate.bean.Integrate;
import com.pm.task.bean.AjaxDomain;
import com.sup.integrate.bean.Integrates;
import com.sup.integrate.busin.IntegrateBusins;




/**
 * 客户首页单点登录
 */

@Controller
@RequestMapping("/common/company/dandianLogin")
public class CompanyDanDianLoginController extends SupBaseController {
    
    
    @Autowired
    private IntegrateBusins integrateBusins;
    
    
    /**
     * 首页单点登录
     * @return
     * @throws IOException 
     */
    @RequestMapping("/DanDianLogin")
    @ResponseBody
    public AjaxDomain showCompanyIndexss(String interType) {
        AjaxDomain ajaxDomain=new AjaxDomain();
        switch (interType) {
        case "1":
            interType="970cfc1c-c92d-417d-984e-1c33d961798e";
            break;
        case "2":
            interType="b3b9ec85-17df-40e4-9763-b7f553de7ed4";
            break;
        case "3":
            interType="ed1f3808-b2b9-4b35-8e27-c4f996c99826";
            break;
        case "4":
            interType="fe17f885-8a57-4024-9523-6ba0b446b2e6";
            break;
        case "5":
            interType="5b66ca89-8935-4306-a641-80da3579888a";
            break;
        case "6":
            interType="7e4042bb-d4de-42d9-b3de-7b16c46afb6d";
            break;
        case "7":
            interType="ccbf9cef-166e-4fe0-9ea9-6c069dc8f6fb";
            break;
        }
        System.out.println("sssssssssssssssssssss"+interType);
        Integrate integrate=integrateBusins.getIntegrateName(interType);
        CompanyInfoUser user=getCurrSup();
        String url="";
        try {
            if(user!=null  ){
            Integrates integrates=integrateBusins.getIntegrates(this.getCurrSup().getId(), interType);
                if(integrates!=null){
                    url=""+integrate.getLogininnerurl()+"?"+integrate.getLoginusercode()+"="+integrates.getLoginusercode()+"&"+integrate.getLoginpassword()+"="+integrates.getLoginpassword()+"";
                }else{
                    url=""+integrate.getLogininnerurl()+"";
                }
            }else{
                url=""+integrate.getLogininnerurl()+"";
            }   
            System.out.println("sssssssssssssssssssss"+url);
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("map", url);
            ajaxDomain.setMap(map);
            ajaxDomain.setStatusCode("200");
        } catch (Exception e) {
            ajaxDomain.setStatusCode("400");
            e.printStackTrace();
        }
        return ajaxDomain;
    }
}
