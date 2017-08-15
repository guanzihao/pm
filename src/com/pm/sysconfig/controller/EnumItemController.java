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
import com.pm.sysconfig.busin.EnumBusin;

/**
 * 枚举项配置
 * 
 * @author youliang.fang
 */

@Controller
@RequestMapping("/sysconfig/info")
public class EnumItemController extends OrganizeBaseController {

    @Autowired
    private CompanyInfoBusin companyInfoBusin;
    
    
    @Autowired
    private EnumBusin enumBusin;
  

    /**
     * 查询人员List列表
     * 
     * @param searchBean
     * @return
     */
    @FunAuth("info_listEnumInfo")
    @RequestMapping("/listEnumInfo")
    public String listSupCompanyInfo(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,a.comCode, a.name, a.status from Enumtype a");
        pageBean.addQueryStr("a.id", searchBean.getSearchName3(), true);
        pageBean.addQueryStr("a.status", searchBean.getSearchName1(), true);
        pageBean.addQueryStr("a.name", searchBean.getSearchName2(), true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/sysconfig/interface/listEnumInfo";
    }

    /**
     * 查看枚举信息
     */
    @FunAuth("info_viewEnumInfo")
    @RequestMapping("/viewEnumInfo/{id}")
    public String viewEnumInfo(@PathVariable String id) {
        Enumtype enumtype= enumBusin.getEnumtype(id);
        model.addAttribute("enumType",enumBusin.getEnumtype(id));
        List<Enumitems> enumitems = enumBusin.getEnumitemList(enumtype);
        model.addAttribute("enumitems",enumitems);
        //model.addAttribute("enumitemList",enumBusin.getEnumitemList(enumBusin.getEnumtype(id)));
        return "/sysconfig/interface/viewEnumInfo";
    }
    
  
    /**
     * 修改枚举信息
     */
    @FunAuth("info_editEnumInfo")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editEnumInfo/{id}")
    public String editEnumInfo(@PathVariable String id) {
        Enumtype enumtypeInfo = enumBusin.getEnumtype(id);
        List<Enumitems> enumitems = null;
        if(enumtypeInfo != null){
            enumitems = enumBusin.getEnumitemList(enumtypeInfo);
        }
       
        if (enumtypeInfo != null) {
            model.addAttribute("enumtype", enumtypeInfo);
            model.addAttribute("enumitems", enumitems);
        }
        return "/sysconfig/interface/editEnumInfo";
    }

    /**
     * 保存枚举信息
     */
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveEnumInfo")
    public String saveEnumInfo(Enumtype enumtype, String[] typeIds) {
        Enumtype info = enumBusin.getEnumtype(enumtype.getId());
        if (info != null) {
            info.setName(enumtype.getName());
            info.setSort(enumtype.getSort());
            businApi.save(info);
        } else {
            enumtype.setComCode(companyInfoBusin.getCompanyInfoCode());
            enumtype.setStatus(Contexts.Y);
            businApi.save(enumtype);
        }       
        return "redirect:/sysconfig/info/viewEnumInfo/"+ enumtype.getId();
    }
    

    
    
    /**
     * 启用供应商
     */
    @FunAuth("info_enableSubCompanyInfo")
    @RequestMapping("/enableSubCompanyInfo")
    @ResponseBody
    public void enableCompanyInfo(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Enumtype enumtype = enumBusin.getEnumtype(id);
                    if (enumtype != null) {
                        enumtype.setStatus(Contexts.Y);
                        businApi.save(enumtype);
                      
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
     * 禁用供应商
     */
    @FunAuth("info_disableSupCompanyInfo")
    @RequestMapping("/disableSupCompanyInfo")
    @ResponseBody
    public void disableCompanyInfo(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Enumtype enumtype = enumBusin.getEnumtype(id);
                    if (enumtype != null) {
                        enumtype.setStatus(Contexts.N);
                        businApi.save(enumtype);
                       
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
     * AJAX检查枚举名称是否冲突
     * 
     * @param id UserID
     * @param comName 供应商名称
     * @param comCode 供应商编码
     */
    @RequestMapping(value = "/ajaxCheckEnumInfo")
    @ResponseBody
    public void ajaxCheckSupCompanyInfo(String id, String name) {
        int count = businApi.getQueryPageSize("select a.id from Enumtype a where id <> ?  and   status=?  and  (name = ?)", new Object[] {id,Contexts.Y, name.replaceAll(" ", "") });
        writer(count > 0 ? "false" : "true");
    }
    
    /**
     * AJAX检查枚举名称是否冲突
     * 
     * @param id UserID
     * @param comName 供应商名称
     * @param comCode 供应商编码
     */
    @RequestMapping(value = "/ajaxCheckEnumitem")
    @ResponseBody
    public void ajaxCheckSupCompanyitem(String id, String name) {
        
        int count = businApi.getQueryPageSize("select a.id from Enumitems a where id <> ?  and   status=?  and  (name = ?)", new Object[] {id,Contexts.Y, name.replaceAll(" ", "") });
        writer(count > 0 ? "false" : "true");
    }
    
    
  

    /**
     * 修改枚举明细
     */
    @FunAuth("info_editEnumitemInfo")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editEnumitemInfo/{comId}/{id}")
    public String editCompanyInfoUser(@PathVariable String comId, @PathVariable String id) {
        Enumtype enumtype = enumBusin.getEnumtype(comId);
        if (enumtype != null) {
            Enumitems enumitems = enumBusin.getEnumitems(id);
            if(enumitems != null){
                model.addAttribute("enumitem", enumitems);
            }
        }
        model.addAttribute("enumtype", enumtype);
        
        return "/sysconfig/interface/editEnumitem";
    }

    /**
     * 保存枚举明细
     */
    @FunAuth("info_editAddEnumitems")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/editAddEnumitems")
    public String saveCompanyInfoUser(Enumitems enumitem, String comId) {
        Enumtype enumtype = enumBusin.getEnumtype(comId);
        
        if (enumtype != null) {
            enumitem.setStatus(Contexts.Y);
            enumitem.setTypeID(enumtype.getId());;
            
            businApi.save(enumitem);
        }
        return "redirect:/sysconfig/info/viewEnumInfo/" + comId;
    }

    /**
     * 启用枚举信息
     */
    @FunAuth("info_enableSupCompanyInfoUser")
    @RequestMapping("/enableEnumitems")
    @ResponseBody
    public void enableCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Enumitems Enumitems = (Enumitems) businApi.get(Enumitems.class, id);
                    if (Enumitems != null) {
                        Enumitems.setStatus(Contexts.Y);
                        businApi.save(Enumitems);

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
     * 禁用枚举信息
     */
    @FunAuth("info_disableSupCompanyInfoUser")
    @RequestMapping("/disableEnumitems")
    @ResponseBody
    public void disableCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Enumitems Enumitems = (Enumitems) businApi.get(Enumitems.class, id);
                    if (Enumitems != null) {
                        Enumitems.setStatus(Contexts.N);
                        businApi.save(Enumitems);

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
