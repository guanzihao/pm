package com.sup.company.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.ComRoleUser;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.Contexts;
import com.pm.framework.models.CheckSubmit;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.util.MD5Util;
import com.pm.right.models.FunAuth;
import com.sup.company.busin.CompanyInfoUserBusin;

/**
 * 公司人员管理 WangWei
 */

@Controller
@RequestMapping("/sup/supcompany/supcompanyuser")
public class SupCompanyInfoUserController extends SupBaseController {

    @Autowired
    public CompanyInfoUserBusin companyInfoUserBusin;
    
    @Autowired
    public CompanyInfoBusin companyInfoBusin;

    /**
     * 显示人员列表
     * 
     * @return viewCompanyInfo
     */
    @FunAuth("company_listSupCompanyInfoUser")
    @RequestMapping("/listSupCompanyInfoUser")
    public String listCompanyInfoUser() {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if (companyInfoUser != null) {
            model.addAttribute("supcompanyInfo", companyInfoUser.getSupCompanyInfo());
        }
        return "/sup/supcompany/supcompanyuser/listSupCompanyInfoUser";
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
    @FunAuth("company_editSupCompanyInfoUser")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editSupCompanyInfoUser/{id}")
    public String editCompanyInfoUser(@PathVariable String id) {
        SupCompanyInfo supcompanyInfo = companyInfoUserBusin.getSupCompanyInfoUser(getCurrSup().getId()).getSupCompanyInfo();
        if (supcompanyInfo != null) {
            CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(id);
            if(companyInfoUser != null){
                model.addAttribute("companyInfoUser", companyInfoUser);
                List<ComRoleUser> comRoleUserList = companyInfoBusin.getComRoleUserList(companyInfoUser);
                if(!comRoleUserList.isEmpty() && comRoleUserList.size() > 0){
                    model.addAttribute("comRoleUser", comRoleUserList.get(0));
                }
            }
        }
        model.addAttribute("supcompanyInfo", supcompanyInfo);
        model.addAttribute("orgRoleList", organizeBusin.getOrgRoleList(2));
        return "/sup/supcompany/supcompanyuser/editSupCompanyInfoUser";
    }

    /**
     * 保存联系人
     */
    @FunAuth("company_editSupCompanyInfoUser")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveSupCompanyInfoUser")
    public String saveCompanyInfoUser(CompanyInfoUser companyInfoUser, String orgRoleId) {
        SupCompanyInfo companyInfo = companyInfoUserBusin.getSupCompanyInfoUser(getCurrSup().getId()).getSupCompanyInfo();
        if (companyInfo != null) {
            CompanyInfoUser infoUser = (CompanyInfoUser) companyInfoUserBusin.getCompanyInfoUser(companyInfoUser.getId());
            if (infoUser != null) {
                infoUser.setSupCompanyInfo(companyInfo);
                infoUser.setUserFax(companyInfoUser.getUserFax());
                infoUser.setUserMail(companyInfoUser.getUserMail());
                infoUser.setUserName(companyInfoUser.getUserName());
                infoUser.setUserTel(companyInfoUser.getUserTel());
                infoUser.setUserNumber(companyInfoUser.getUserNumber());
                businApi.save(infoUser);
            } else {
                companyInfoUser.setSupCompanyInfo(companyInfo);
                companyInfoUser.setUserState(Contexts.Y);
                companyInfoUser.setLoginPwd(MD5Util.sign(companyInfoUser.getLoginPwd()));
                businApi.save(companyInfoUser);
            }
            OrgRole orgRole = organizeBusin.getOrgRole(orgRoleId);
            if(orgRole != null){
                ComRoleUser comRoleUser = companyInfoBusin.getComRoleUser(companyInfoUser);
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
        return "redirect:/sup/supcompany/supcompanyuser/listSupCompanyInfoUser";
    }

    /**
     * 修改联系人密码
     */
    @FunAuth("company_editSupCompanyInfoUserPwd")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editSupCompanyInfoUserPwd/{id}")
    public String editCompanyInfoUserPwd(@PathVariable String id) {
        SupCompanyInfo companyInfo = companyInfoUserBusin.getSupCompanyInfoUser(getCurrSup().getId()).getSupCompanyInfo();
        if (companyInfo != null) {
            model.addAttribute("companyInfoUser", companyInfoUserBusin.getCompanyInfoUser(id));
        }
        model.addAttribute("companyInfo", companyInfo);
        return "/sup/supcompany/supcompanyuser/editSupCompanyInfoUserPwd";
    }

    /**
     * 保存联系人密码
     */
    @FunAuth("company_editSupCompanyInfoUserPwd")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveSupCompanyInfoUserPwd")
    public String saveCompanyInfoUserPwd(String id, String loginPwd) {
        SupCompanyInfo companyInfo = companyInfoUserBusin.getSupCompanyInfoUser(getCurrSup().getId()).getSupCompanyInfo();
        if (companyInfo != null) {
            CompanyInfoUser infoUser = (CompanyInfoUser) companyInfoUserBusin.getCompanyInfoUser(id);
            if (infoUser != null) {
                infoUser.setLoginPwd(MD5Util.sign(loginPwd));
                businApi.save(infoUser);
            }
        }
        return "redirect:/sup/supcompany/supcompanyuser/listSupCompanyInfoUser";
    }

    /**
     * 删除用户信息
     */
    @FunAuth("company_removeSupCompanyInfoUser")
    @RequestMapping("/removeSupCompanyInfoUser")
    @ResponseBody
    public void removeCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) companyInfoUserBusin.getSupCompanyInfoUser(id);
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
     * 启用联系人
     */
    @FunAuth("company_enableSupCompanyInfoUser")
    @RequestMapping("/enableSupCompanyInfoUser")
    @ResponseBody
    public void enableCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) companyInfoUserBusin.getSupCompanyInfoUser(id);
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
    @FunAuth("company_disableSupCompanyInfoUser")
    @RequestMapping("/disableSupCompanyInfoUser")
    @ResponseBody
    public void disableCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) companyInfoUserBusin.getSupCompanyInfoUser(id);
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
}
