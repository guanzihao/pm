package com.pm.sysconfig.controller;


import java.util.List;

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
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.right.models.FunAuth;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;
import com.pm.sysconfig.bean.Systemplate;
import com.pm.sysconfig.busin.EnumBusin;
import com.pm.sysconfig.busin.SystemplateBusin;

/**
 * 枚举项配置
 * 
 * @author youliang.fang
 */

@Controller
@RequestMapping("/sysconfig/Systemplate")
public class SystemPlateController extends OrganizeBaseController {

    @Autowired
    private CompanyInfoBusin companyInfoBusin;
    
    
    @Autowired
    private EnumBusin enumBusin;
    
    
    @Autowired
    private SystemplateBusin systemplateBusin;
  

    /**
     * 查询邮件信息模板List列表
     * 
     * @param searchBean
     * @return
     */
    @FunAuth("info_listSystemplate")
    @RequestMapping("/listSystemplate")
    public String listSupCompanyInfo(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,a.tempcontet, a.tempname, a.tempstatus from Systemplate a");
        pageBean.addQueryStr("a.id", searchBean.getSearchName3(), true);
        pageBean.addQueryStr("a.tempstatus", searchBean.getSearchName1(), true);
        pageBean.addQueryStr("a.tempname", searchBean.getSearchName2(), true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/sysconfig/systemplate/listSystemplate";
    }

    /**
     * 查看邮件信息模板
     */
    @FunAuth("info_viewSystemplate")
    @RequestMapping("/viewSystemplate/{id}")
    public String viewsystemplate(@PathVariable String id) {
        Systemplate systemplate=systemplateBusin.getSystemplate(id);
        model.addAttribute("systemplate",systemplate);
        if(systemplate.getTemptype()!=null){
            Enumitems enumitems=enumBusin.getEnumitems(systemplate.getTemptype());
            model.addAttribute("enumitems", enumitems);
        }
        return "/sysconfig/systemplate/viewSystemplate";
    }
    
  
    /**
     * 修改邮件信息模板
     */
    @FunAuth("info_editSystemplate")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editSystemplate/{id}")
    public String editEnumInfo(@PathVariable String id) {
        Systemplate systemplate = systemplateBusin.getSystemplate(id);
        Enumtype enumType = enumBusin.getEnumtypeByName("邮件信息枚举");
        List<Enumitems> enumitems = enumBusin.getEnumitemList(enumType);
        if(enumitems != null){
            model.addAttribute("enumitems",  enumitems);
        }
        if (systemplate != null) {
          
            model.addAttribute("systemplate", systemplate);
        }
        return "/sysconfig/systemplate/editSystemplate";
    }

    /**
     * 保存模板信息
     */
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveSystemplate")
    public String saveSystemplate(Systemplate systemplate, String[] typeIds) {
        Systemplate systemplateInfo = systemplateBusin.getSystemplate(systemplate.getId());
        if (systemplateInfo != null) {
            systemplateInfo.setTempname(systemplate.getTempname());
            systemplateInfo.setTempcontet(systemplate.getTempcontet());
            systemplateInfo.setTemptype(systemplate.getTemptype());
            businApi.save(systemplateInfo);
        } else {
            
            systemplate.setTempstatus(Contexts.Y);
            businApi.save(systemplate);
        }       
        return "redirect:/sysconfig/Systemplate/viewSystemplate/"+ systemplate.getId();
    }
    

    
    
    /**
     * 启用模板
     */
    @FunAuth("info_enableSystemplate")
    @RequestMapping("/enableSystemplate")
    @ResponseBody
    public void enableSystemplate(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Systemplate systemplate = systemplateBusin.getSystemplate(id);
                    if (systemplate != null) {
                        systemplate.setTempstatus(Contexts.Y);
                        businApi.save(systemplate);
                      
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
     * 禁用模板
     */
    @FunAuth("info_disableSystemplate")
    @RequestMapping("/disableSystemplate")
    @ResponseBody
    public void disableCompanyInfo(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Systemplate systemplate = systemplateBusin.getSystemplate(id);
                    if (systemplate != null) {
                        systemplate.setTempstatus(Contexts.N);
                        businApi.save(systemplate);
                       
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
     * AJAX检查模板名称是否冲突
     * 
     * @param id ID
     * @param temName 供应商名称
     */
    @RequestMapping(value = "/ajaxCheckSystemplate")
    @ResponseBody
    public void ajaxCheckSupCompanyInfo(String id, String tempname) {
        int count = businApi.getQueryPageSize("select a.id from Systemplate a where id <> ?  and   tempstatus=?  and  (tempname = ?)", new Object[] {id,Contexts.Y, tempname.replaceAll(" ", "") });
        writer(count > 0 ? "false" : "true");
    }
    
 
  
    /**
     * 查询所有用户登录记录列表
     */
    @FunAuth("sup_listAllComLoginLog")
    @RequestMapping("/listAllComLoginLog")
    public String listAllComLoginLog(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, c.comName, b.userName, b.userMail, a.ipInfo, a.hostInfo, a.userBrowse, a.updateDate from ComLoginLog a join a.companyInfoUser b join b.supCompanyInfo c");
        pageBean.addQueryStr("a.ipInfo,a.hostInfo,a.userBrowse,b.userName,b.userMail,c.comName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/supcompany/info/listAllComLoginLog";
    }
}
