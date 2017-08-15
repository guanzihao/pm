package com.pm.integrate.controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.Contexts;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.models.CheckSubmit;
import com.pm.integrate.bean.Integrate;
import com.pm.integrate.busin.IntegrateBusin;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.right.models.FunAuth;

/**
 * 集成登录管理
 * 
 * @author youliang.fang
 */

@Controller
@RequestMapping("/integrate/integrate")
public class IntegrateController extends OrganizeBaseController {

    @Autowired
    private CompanyInfoBusin companyInfoBusin;
    
    @Autowired
    private IntegrateBusin integrateBusin;

    /**
     * 查看集成登录名称是否冲突
     * @param id
     * @param loginname
     */
    @RequestMapping(value = "/ajaxCheckintegrate")
    @ResponseBody
    public void ajaxCheckintegrate(String id, String loginname) {
        int count = businApi.getQueryPageSize("select a.id from Integrate a where id <> ? and (loginname = ? ) and loginstate = ? ", new Object[] { id, loginname,Contexts.Y });
        writer(count > 0 ? "false" : "true");
    }

    /**
     * 查询集成登录List列表
     * 
     * @param searchBean
     * @return
     */
    @FunAuth("info_listintegrate")
    @RequestMapping("/listintegrate")
    public String listintegrate(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.loginname, a.logininnerurl, a.loginouturl,a.loginstate from Integrate a");
        
        pageBean.addQueryStr("a.loginname,a.logininnerurl", searchBean.getSearchName1(), true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/integrate/integrate/listintegrate";
    }

    /**
     * 查看客户配置登录
     */
    @FunAuth("info_listintegrate")
    @RequestMapping("/viewintegrate/{id}")
    public String viewintegrate(@PathVariable String id) {
        model.addAttribute("integrate", integrateBusin.getIntegrate(id));
        return "/integrate/integrate/viewintegrate";
    }
    
    
    /**
     * 修改客户配置登录
     */
    @FunAuth("info_editintegrate")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editintegrate/{id}")
    public String editintegrate(@PathVariable String id) {
        model.addAttribute("integrate", integrateBusin.getIntegrate(id));
        return "/integrate/integrate/editintegrate";
    }
    
    /**
     * 保存客户配置登录
     */
    @FunAuth("info_editintegrate")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveintegrate")
    public String saveCompanyInfo(Integrate integrate, String[] typeIds) {
        Integrate info = integrateBusin.getIntegrate(integrate.getId());
        if (info != null) {
            info.setLoginname(integrate.getLoginname());
            info.setLogininnerurl(integrate.getLogininnerurl());
            info.setLoginouturl(integrate.getLoginouturl());
            info.setLoginpassword(integrate.getLoginpassword());
            info.setLoginpostType(integrate.getLoginpostType());
            info.setLoginusercode(integrate.getLoginusercode());
            businApi.save(info);
        } else {
            integrate.setLoginstate(Contexts.Y);
            businApi.save(integrate);
        }
        return "redirect:/integrate/integrate/viewintegrate/" + integrate.getId();
    }
   
    
    /**
     * 启用登录
     */
    @FunAuth("info_enableintegrate")
    @RequestMapping("/enableintegrate")
    @ResponseBody
    public void enableintegrate(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Integrate Integrate = integrateBusin.getIntegrate(id);
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
    @FunAuth("info_disableintegrate")
    @RequestMapping("/disableintegrate")
    @ResponseBody
    public void disableintegrate(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Integrate Integrate = integrateBusin.getIntegrate(id);
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
