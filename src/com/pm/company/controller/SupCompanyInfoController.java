package com.pm.company.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pm.company.bean.ComRoleUser;
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.Contexts;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.models.CheckSubmit;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.organize.util.MD5Util;
import com.pm.right.models.FunAuth;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;
import com.pm.sysconfig.busin.EnumBusin;

/**
 * 公司管理
 * 
 * @author youliang.fang
 */

@Controller
@RequestMapping("/supcompany/info")
public class SupCompanyInfoController extends OrganizeBaseController {

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
    @FunAuth("info_listSupCompanyInfo")
    @RequestMapping("/listSupCompanyInfo")
    public String listSupCompanyInfo(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.comCode, a.comName, a.comState, a.createDate from SupCompanyInfo a");
        pageBean.addQueryStr("a.comState", searchBean.getSearchName1(), true);
        pageBean.addQueryStr("a.comCode,a.comName", searchBean.getSearchName2(), true);
        pageBean.addQueryDate("a.createDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/supcompany/info/listSupCompanyInfo";
    }

    /**
     * 查看供应商
     */
    @FunAuth("info_listSupCompanyInfo")
    @RequestMapping("/viewSupCompanyInfo/{id}")
    public String viewCompanyInfo(@PathVariable String id) {
        SupCompanyInfo supCompanyInfo= companyInfoBusin.getSupCompanyInfo(id);
        if(supCompanyInfo.getComType()!=null){
            Enumitems enumitems=enumBusin.getEnumitems(supCompanyInfo.getComType());
            model.addAttribute("enumitems", enumitems);
        }
        model.addAttribute("SupCompanyInfo", supCompanyInfo);
        return "/supcompany/info/viewSupCompanyInfo";
    }
    
  
    /**
     * 修改供应商
     */
    @FunAuth("info_editsupCompanyInfo")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editSupCompanyInfo/{id}")
    public String editSupCompanyInfo(@PathVariable String id) {
        SupCompanyInfo supCompanyInfo = companyInfoBusin.getSupCompanyInfo(id);
        Enumtype enumType = enumBusin.getEnumtypeByName("供应商枚举信息");
        List<Enumitems> enumitems = enumBusin.getEnumitemList(enumType);
        if(enumType != null){
            model.addAttribute("enumType",  enumType);
            model.addAttribute("enumitems",  enumitems);
        }
        if (supCompanyInfo != null) {
          
            model.addAttribute("supCompanyInfo", supCompanyInfo);
           
            
        }
        return "/supcompany/info/editSupCompanyInfo";
    }

    /**
     * 保存供应商信息
     */
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveSupCompanyInfo")
    public String saveSupCompanyInfo(SupCompanyInfo supCompanyInfo, String[] typeIds, String comType) {
        SupCompanyInfo info = companyInfoBusin.getSupCompanyInfo(supCompanyInfo.getId());
        if (info != null) {
            info.setComName(supCompanyInfo.getComName());
            info.setComCode(supCompanyInfo.getComCode());
            info.setComEmail(supCompanyInfo.getComEmail());
            info.setComTel(supCompanyInfo.getComTel());
            info.setComType(comType);
            businApi.save(info);
        } else {
            supCompanyInfo.setComCode(companyInfoBusin.getCompanyInfoCode());
            supCompanyInfo.setComState(Contexts.Y);
            businApi.save(supCompanyInfo);
        }
        return "redirect:/supcompany/info/viewSupCompanyInfo/"+ supCompanyInfo.getId();
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
                    SupCompanyInfo companyInfo = companyInfoBusin.getSupCompanyInfo(id);
                    if (companyInfo != null) {
                        companyInfo.setComState(Contexts.Y);
                        businApi.save(companyInfo);
                        for(CompanyInfoUser companyInfoUser : companyInfo.getCompanyInfoUsers()){
                            companyInfoUser.setUserState(Contexts.Y);
                            businApi.save(companyInfoUser);
                        }
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
                    SupCompanyInfo companyInfo = companyInfoBusin.getSupCompanyInfo(id);
                    if (companyInfo != null) {
                        companyInfo.setComState(Contexts.N);
                        businApi.save(companyInfo);
                        for(CompanyInfoUser companyInfoUser : companyInfo.getCompanyInfoUsers()){
                            companyInfoUser.setUserState(Contexts.N);
                            businApi.save(companyInfoUser);
                        }
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
     * AJAX检查供应商名称是否冲突
     * 
     * @param id UserID
     * @param comName 供应商名称
     * @param comCode 供应商编码
     */
    @RequestMapping(value = "/ajaxCheckSupCompanyInfo")
    @ResponseBody
    public void ajaxCheckSupCompanyInfo(String id, String comName) {
        int count = businApi.getQueryPageSize("select a.id from SupCompanyInfo a where id <> ?  and comState=?  and  (comName = ?)", new Object[] {id,Contexts.Y, comName.replaceAll(" ", "") });
        writer(count > 0 ? "false" : "true");
    }
    
   
    
    /**
     * 删除用户信息
     */
    @FunAuth("info_removeSupCompanyInfoUser")
    @RequestMapping("/removeSupCompanyInfoUser")
    @ResponseBody
    public void removeCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) businApi.get(CompanyInfoUser.class, id);
                    if (infoUser != null) {
                        ComRoleUser comRoleUser = companyInfoBusin.getComRoleUser( infoUser);
                        businApi.remove(comRoleUser);
                        businApi.remove(infoUser);

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
     * AJAX检查联系人是否冲突
     * 
     * @param id UserID
     * @param userMail 邮箱地址
     * @param userTel 电话
     */
    @RequestMapping(value = "/ajaxCheckSupCompanyInfoUser")
    @ResponseBody
    public void ajaxCheckCompanyInfoUser(String id, String userMail, String userTel) {
        int count = businApi.getQueryPageSize("select a.id from CompanyInfoUser a where id <> ? and (userMail = ? or userTel = ?)", new Object[] { id, userMail, userTel });
        writer(count > 0 ? "false" : "true");
    }

    /**
     * 修改联系人
     */
    @FunAuth("info_editSupCompanyInfoUser")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editSupCompanyInfoUser/{comId}/{id}")
    public String editCompanyInfoUser(@PathVariable String comId, @PathVariable String id) {
        SupCompanyInfo supcompanyInfo = companyInfoBusin.getSupCompanyInfo(comId);
        if (supcompanyInfo != null) {
            CompanyInfoUser companyInfoUser = companyInfoBusin.getCompanyInfoUser(id);
            if(companyInfoUser != null){
                model.addAttribute("companyInfoUser", companyInfoUser);
                List<ComRoleUser> comRoleUserList = companyInfoBusin.getComRoleUserList(companyInfoUser);
                if(!comRoleUserList.isEmpty() && comRoleUserList.size() > 0){
                    model.addAttribute("comRoleUser", comRoleUserList.get(0));
                }
            }
        }
        model.addAttribute("supcompanyInfo", supcompanyInfo);
        model.addAttribute("orgRoleList", organizeBusin.getOrgRoleList(3));
        
        return "/supcompany/info/editSupCompanyInfoUser";
    }

    /**
     * 保存联系人
     */
    @FunAuth("info_editSupCompanyInfoUser")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveSupCompanyInfoUser")
    public String saveCompanyInfoUser(CompanyInfoUser companyInfoUser, String comId, String orgRoleId) {
        SupCompanyInfo supcompanyInfo = companyInfoBusin.getSupCompanyInfo(comId);
        if (supcompanyInfo != null) {
            CompanyInfoUser infoUser = (CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfoUser.getId());
            if (infoUser != null) {
                infoUser.setSupCompanyInfo(supcompanyInfo);
                infoUser.setUserFax(companyInfoUser.getUserFax());
                infoUser.setUserMail(companyInfoUser.getUserMail());
                infoUser.setUserName(companyInfoUser.getUserName());
                infoUser.setUserTel(companyInfoUser.getUserTel());
                infoUser.setUserNumber(companyInfoUser.getUserNumber());
                infoUser.setUserState(Contexts.Y);
                businApi.save(infoUser);
            } else {
                companyInfoUser.setLoginPwd(MD5Util.sign(companyInfoUser.getLoginPwd()));
                companyInfoUser.setSupCompanyInfo(supcompanyInfo);
                companyInfoUser.setUserState(Contexts.Y);
                businApi.save(companyInfoUser);
            }
            OrgRole orgRole = organizeBusin.getOrgRole(orgRoleId);
            if(orgRole != null){
                ComRoleUser comRoleUser = companyInfoBusin.getComRoleUser( companyInfoUser);
                if(comRoleUser==null){
                    comRoleUser=new ComRoleUser();
                    comRoleUser.setCompanyInfoUser(companyInfoUser);
                    comRoleUser.setOrgRole(orgRole);
                    businApi.save(comRoleUser);
                }else{
                    comRoleUser.setCompanyInfoUser(companyInfoUser);
                    comRoleUser.setOrgRole(orgRole);
                    businApi.save(comRoleUser);
                }
            }
        }
        return "redirect:/supcompany/info/viewSupCompanyInfo/" + comId;
    }

    /**
     * 修改联系人密码
     */
    @FunAuth("info_editSupCompanyInfoUserPwd")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editSupCompanyInfoUserPwd/{comId}/{id}")
    public String editCompanyInfoUserPwd(@PathVariable String comId, @PathVariable String id) {
        SupCompanyInfo SupcompanyInfo = companyInfoBusin.getSupCompanyInfo(comId);
        if (SupcompanyInfo != null) {
            model.addAttribute("companyInfoUser", businApi.get(CompanyInfoUser.class, id));
        }
        model.addAttribute("supcompanyInfo", SupcompanyInfo);
        return "/supcompany/info/editSupCompanyInfoUserPwd";
    }

    /**
     * 保存联系人密码
     */
    @FunAuth("info_editSupCompanyInfoUserPwd")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveSupCompanyInfoUserPwd")
    public String saveCompanyInfoUserPwd(CompanyInfoUser companyInfoUser, String comId) {
        CompanyInfo companyInfo = companyInfoBusin.getCompanyInfo(comId);
        if (companyInfo != null) {
            CompanyInfoUser infoUser = (CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfoUser.getId());
            if (infoUser != null) {
                infoUser.setLoginPwd(MD5Util.sign(companyInfoUser.getLoginPwd()));
                businApi.save(infoUser);
            }
        }
        return "redirect:/supcompany/info/viewSupCompanyInfo/" + comId;
    }

    /**
     * 启用联系人
     */
    @FunAuth("info_enableSupCompanyInfoUser")
    @RequestMapping("/enableSupCompanyInfoUser")
    @ResponseBody
    public void enableCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) businApi.get(CompanyInfoUser.class, id);
                    if (infoUser != null) {
                        infoUser.setUserState(Contexts.Y);
                        businApi.save(infoUser);

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
     * 禁用联系人
     */
    @FunAuth("info_disableSupCompanyInfoUser")
    @RequestMapping("/disableSupCompanyInfoUser")
    @ResponseBody
    public void disableCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) businApi.get(CompanyInfoUser.class, id);
                    if (infoUser != null) {
                        infoUser.setUserState(Contexts.N);
                        businApi.save(infoUser);

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
     * 设置供应商主联系人
     */
    @FunAuth("info_editSupCompanyInfoUser")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/saveSupCompanyInfoUserAdmin/{comId}/{id}")
    public String saveCompanyInfoUserAdmin(@PathVariable String comId, @PathVariable String id) {
        SupCompanyInfo supcompanyInfo = companyInfoBusin.getSupCompanyInfo(comId);
        if (supcompanyInfo != null) {
            CompanyInfoUser companyInfoUser_edit = (CompanyInfoUser)businApi.get(CompanyInfoUser.class, id);
            if(companyInfoUser_edit != null){
                CompanyInfoUser companyInfoUserAdmin = companyInfoBusin.getSupCompanyInfoUserAdmin(supcompanyInfo);
                if(companyInfoUserAdmin != null){
                    companyInfoUserAdmin.setUserAdmin(false);
                    businApi.save(companyInfoUserAdmin);
                }
                companyInfoUser_edit.setUserAdmin(true);
                businApi.save(companyInfoUser_edit);
            }
        }
        model.addAttribute("supcompanyInfo", supcompanyInfo);
        return "redirect:/supcompany/info/viewSupCompanyInfo/" + supcompanyInfo.getId();
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
