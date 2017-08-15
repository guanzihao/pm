package com.sup.integrate.controller;


import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.model.Contexts;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.models.CheckSubmit;
import com.pm.integrate.bean.Integrate;
import com.pm.integrate.busin.IntegrateBusin;
import com.pm.right.models.FunAuth;
import com.sup.company.controller.SupBaseController;
import com.sup.integrate.bean.Integrates;
import com.sup.integrate.busin.IntegrateBusins;

/**
 * 客户单点登录
 * 
 * @author youliang.fang
 */

@Controller
@RequestMapping("/sup/integrate/integrate")
public class IntegrateControllers extends SupBaseController {

    
    @Autowired
    private IntegrateBusins integrateBusin;
    

    @Autowired
    private IntegrateBusin integrateBusins;

    /**
     * 查询集成登录List列表
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping("/listintegrate")
    public String listintegrate(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,  a.loginusercode,b.loginname,a.loginpassword, a.userAccount,a.loginstate,b.loginusercode,b.loginpassword,b.loginouturl from Integrates a inner join a.integrate b  where a.userAccount=?");
        pageBean.addParams(this.getCurrSup().getId());
        pageBean.addQueryStr("b.loginname", searchBean.getSearchName1(), true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/sup/company/integrate/listintegrate";
    }

    /**
     * 查看客户配置登录${basePath }
     */
    @RequestMapping("/viewintegrate/{id}")
    public String viewintegrate(@PathVariable String id) {
        Integrates integrate=integrateBusin.getIntegrate(id);
        //Integrate Integrate =integrateBusins.getIntegrate(integrate.getIntegrate());
        model.addAttribute("integrate", integrateBusin.getIntegrate(id));
        return "/sup/company/integrate/viewintegrate";
    }
    
    
    /**
     * 修改客户配置登录
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editintegrate/{id}")
    public String editintegrate(@PathVariable String id) {
        //查看平台配置的系统
        List<Integrate> integrate=integrateBusins.getIntegrate();
        model.addAttribute("integrates",integrate);
        model.addAttribute("integrate", integrateBusin.getIntegrate(id));
        return "/sup/company/integrate/editintegrate";
    }
    
    /**
     * 保存客户配置登录
     */
    @FunAuth("info_editintegrate")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveintegrate")
    public String saveCompanyInfo(Integrates integrate, String[] typeIds,String interid) {
        Integrates info = integrateBusin.getIntegrate(integrate.getId());
        Integrate integrates=integrateBusins.getIntegrate(integrate.getIntegLogins());
        if (info != null) {
            info.setIntegrate(integrates);
            info.setLoginusercode(integrate.getLoginusercode());
            info.setLoginpassword(integrate.getLoginpassword());
            info.setUserAccount(getCurrSup().getId());
            businApi.save(info);
        } else {
            System.out.println("2");
            integrate.setIntegrate(integrates);
            integrate.setLoginstate(Contexts.Y);
            integrate.setUserAccount(getCurrSup().getId());
            businApi.save(integrate);
        }
        return "redirect:/sup/integrate/integrate/viewintegrate/" + integrate.getId();
    }
   
    
    /**
     * 启用登录
     */
    @RequestMapping("/enableintegrate")
    @ResponseBody
    public void enableintegrate(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Integrates Integrate = integrateBusin.getIntegrate(id);
                    if (Integrate != null) {
                        Integrate.setLoginstate(Contexts.Y);
                        businApi.save(Integrate);
                       

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
    
    /**
     * 禁用登录
     */
    @RequestMapping("/disableintegrate")
    @ResponseBody
    public void disableintegrate(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Integrates Integrate = integrateBusin.getIntegrate(id);
                    if (Integrate != null) {
                        Integrate.setLoginstate(Contexts.N);
                        businApi.save(Integrate);

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
