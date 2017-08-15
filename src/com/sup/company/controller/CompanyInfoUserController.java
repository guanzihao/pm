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
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
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
@RequestMapping("/sup/company/companyuser")
public class CompanyInfoUserController extends SupBaseController {

    @Autowired
    public CompanyInfoUserBusin companyInfoUserBusin;
    
    @Autowired
    public CompanyInfoBusin companyInfoBusin;

    /**
     * 显示人员列表
     * 
     * @return viewCompanyInfo
     */
    @FunAuth("company_listCompanyInfoUser")
    @RequestMapping("/listCompanyInfoUser")
    public String listCompanyInfoUser() {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if (companyInfoUser != null) {
            model.addAttribute("companyInfo", companyInfoUser.getCompanyInfo());
        }
        return "/sup/company/companyuser/listCompanyInfoUser";
    }

    /**
     * AJAX检查联系人是否冲突
     * 
     * @param id UserID
     * @param userMail 邮箱地址
     * @param userTel 电话
     */
    @RequestMapping(value = "/ajaxCheckCompanyInfoUser")
    @ResponseBody
    public void ajaxCheckCompanyInfoUser(String id, String userMail, String userTel) {
        int count = businApi.getQueryPageSize("select a.id from CompanyInfoUser a where id <> ? and (userMail = ? or userTel = ?)", new Object[] { id, userMail, userTel });
        writer(count > 0 ? "false" : "true");
    }

    /**
     * 修改联系人
     */
    @FunAuth("company_editCompanyInfoUser")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editCompanyInfoUser/{id}")
    public String editCompanyInfoUser(@PathVariable String id) {
        CompanyInfo companyInfo = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId()).getCompanyInfo();
        if (companyInfo != null) {
            CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(id);
            if(companyInfoUser != null){
                model.addAttribute("companyInfoUser", companyInfoUser);
                List<ComRoleUser> comRoleUserList = companyInfoBusin.getComRoleUserList(companyInfoUser);
                if(!comRoleUserList.isEmpty() && comRoleUserList.size() > 0){
                    model.addAttribute("comRoleUser", comRoleUserList.get(0));
                }
            }
        }
        model.addAttribute("companyInfo", companyInfo);
        model.addAttribute("orgRoleList", organizeBusin.getOrgRoleList(2));
        return "/sup/company/companyuser/editCompanyInfoUser";
    }

    /**
     * 保存联系人
     */
    @FunAuth("company_editCompanyInfoUser")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveCompanyInfoUser")
    public String saveCompanyInfoUser(CompanyInfoUser companyInfoUser, String orgRoleId) {
        CompanyInfo companyInfo = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId()).getCompanyInfo();
        if (companyInfo != null) {
            CompanyInfoUser infoUser = (CompanyInfoUser) companyInfoUserBusin.getCompanyInfoUser(companyInfoUser.getId());
            if (infoUser != null) {
                infoUser.setCompanyInfo(companyInfo);
                infoUser.setUserFax(companyInfoUser.getUserFax());
                infoUser.setUserMail(companyInfoUser.getUserMail());
                infoUser.setUserName(companyInfoUser.getUserName());
                infoUser.setUserTel(companyInfoUser.getUserTel());
                infoUser.setUserNumber(companyInfoUser.getUserNumber());
                businApi.save(infoUser);
            } else {
                companyInfoUser.setCompanyInfo(companyInfo);
                companyInfoUser.setUserState(Contexts.Y);
                companyInfoUser.setLoginPwd(MD5Util.sign(companyInfoUser.getLoginPwd()));
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
        return "redirect:/sup/company/companyuser/listCompanyInfoUser";
    }

    /**
     * 修改联系人密码
     */
    @FunAuth("company_editCompanyInfoUserPwd")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editCompanyInfoUserPwd/{id}")
    public String editCompanyInfoUserPwd(@PathVariable String id) {
        CompanyInfo companyInfo = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId()).getCompanyInfo();
        if (companyInfo != null) {
            model.addAttribute("companyInfoUser", companyInfoUserBusin.getCompanyInfoUser(id));
        }
        model.addAttribute("companyInfo", companyInfo);
        return "/sup/company/companyuser/editCompanyInfoUserPwd";
    }

    /**
     * 保存联系人密码
     */
    @FunAuth("company_editCompanyInfoUserPwd")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveCompanyInfoUserPwd")
    public String saveCompanyInfoUserPwd(String id, String loginPwd) {
        CompanyInfo companyInfo = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId()).getCompanyInfo();
        if (companyInfo != null) {
            CompanyInfoUser infoUser = (CompanyInfoUser) companyInfoUserBusin.getCompanyInfoUser(id);
            if (infoUser != null) {
                infoUser.setLoginPwd(MD5Util.sign(loginPwd));
                businApi.save(infoUser);
            }
        }
        return "redirect:/sup/company/companyuser/listCompanyInfoUser";
    }

    /**
     * 删除用户信息
     */
    @FunAuth("company_removeCompanyInfoUser")
    @RequestMapping("/removeCompanyInfoUser")
    @ResponseBody
    public void removeCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) companyInfoUserBusin.getCompanyInfoUser(id);
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
    @FunAuth("company_enableCompanyInfoUser")
    @RequestMapping("/enableCompanyInfoUser")
    @ResponseBody
    public void enableCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) companyInfoUserBusin.getCompanyInfoUser(id);
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
    @FunAuth("company_disableCompanyInfoUser")
    @RequestMapping("/disableCompanyInfoUser")
    @ResponseBody
    public void disableCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) companyInfoUserBusin.getCompanyInfoUser(id);
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
