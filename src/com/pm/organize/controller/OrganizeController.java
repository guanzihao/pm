package com.pm.organize.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.model.Contexts;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.CheckSubmit;
import com.pm.organize.bean.OrgDeptUser;
import com.pm.organize.bean.OrgRoleUser;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.models.DeptTreeModel;
import com.pm.organize.util.MD5Util;
import com.pm.right.models.FunAuth;

@Controller
@RequestMapping("/organize/organize")
public class OrganizeController extends OrganizeBaseController {

    /**
     * AJAX检查用户名是否冲突
     * 
     * @param userId UserID
     * @param userMail 邮箱地址
     * @param userTel 电话
     */
    @RequestMapping(value = "/ajaxCheckUser")
    @ResponseBody
    public void ajaxCheckUser(String userId, String userMail, String userTel) {
        writer(organizeBusin.checkUserAccount(userId, userMail, userTel) ? "true" : "false");
    }

    /**
     * 查询人员List列表
     * 
     * @param searchBean
     * @return
     */
    @FunAuth("organize_listUserAccount")
    @RequestMapping("/listUserAccount")
    public String listUserAccount(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.userMail, a.userName, a.userTel, a.userState, a.createDate from UserAccount a");
        pageBean.addQueryStr("a.userMail,a.userName,a.userTel", searchBean.getSearchName1(), true);
        pageBean.addQueryStr("a.userState", searchBean.getSearchName2(), false);
        pageBean.addQueryDate("a.createDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/organize/organize/listUserAccount";
    }

    /**
     * 修改用户信息
     */
    @FunAuth("organize_editUserAccount")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editUserAccount/{id}")
    public String editUserAccount(@PathVariable String id) {
        UserAccount userAccount = organizeBusin.getUserAccount(id);

        List<String> selectedIds = new ArrayList<String>();
        if (userAccount != null) {
            //部门
            OrgDeptUser orgDeptUser = organizeBusin.getOrgDeptUser(userAccount);
            if (orgDeptUser != null) {
                model.addAttribute("deptIds", orgDeptUser.getOrgDept().getId());
                selectedIds.add(orgDeptUser.getOrgDept().getId());
            }

            //角色
            List<OrgRoleUser> orgRoleUserList = organizeBusin.getOrgRoleUserList(userAccount);
            StringBuffer selectList = new StringBuffer();
            if (orgRoleUserList != null) {
                for (OrgRoleUser orgRoleUser : orgRoleUserList) {
                    if (selectList.length() > 0) {
                        selectList.append(",");
                    }
                    selectList.append(StringUtil.getSqlId(orgRoleUser.getOrgRole().getId()));
                }
            }
            model.addAttribute("selectList", selectList.toString());

            //返回页面
            model.addAttribute("userAccount", userAccount);
        }
        model.addAttribute("treeData", DeptTreeModel.ToJosn(organizeBusin.getOrgDeptList(), selectedIds, true));
        model.addAttribute("orgRoleList", organizeBusin.getOrgRoleList(1));
        return "/organize/organize/editUserAccount";
    }

    /**
     * 保存用户信息
     */
    @FunAuth("organize_editUserAccount")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveUserAccount")
    public String saveUserAccount(UserAccount userAccount, String[] roleIds, String deptIds) {
        UserAccount account = organizeBusin.getUserAccount(userAccount.getId());
        if (account != null) {
            account.setUserName(userAccount.getUserName());
            account.setUserTel(userAccount.getUserTel());
            account.setUserState(Contexts.W);
            businApi.save(account);

            //保存角色
            if (roleIds != null && roleIds.length > 0) {
                organizeBusin.saveOrgRoleUser(account, roleIds);
            }
            //保存部门
            if (!StringUtil.isEmpty(deptIds)) {
                organizeBusin.saveOrgDeptUser(account, deptIds);
            }
            account.setUserState(Contexts.Y);
            businApi.save(account);
        } else {
            userAccount.setLoginPwd(MD5Util.sign(userAccount.getLoginPwd()));
            userAccount.setUserState(Contexts.W);
            businApi.save(userAccount);

            //保存角色
            if (roleIds != null && roleIds.length > 0) {
                organizeBusin.saveOrgRoleUser(userAccount, roleIds);
            }
            //保存部门
            if (!StringUtil.isEmpty(deptIds)) {
                organizeBusin.saveOrgDeptUser(userAccount, deptIds);
            }
            userAccount.setUserState(Contexts.Y);
            businApi.save(account);
        }
        return "redirect:/organize/organize/viewUserAccount/" + userAccount.getId();
    }

    /**
     * 用户审批显示页面
     */
    @RequestMapping("/viewUserAccountTask/{id}")
    public String viewUserAccountTask(@PathVariable String id) {
        UserAccount userAccount = organizeBusin.getUserAccount(id);
        if (userAccount != null) {
            model.addAttribute("userAccount", userAccount);
            model.addAttribute("orgDeptUser", organizeBusin.getOrgDeptUser(userAccount));
            model.addAttribute("orgRoleUserList", organizeBusin.getOrgRoleUserList(userAccount));
        }
        return "/organize/organize/task/viewUserAccountTask";
    }

    /**
     * 查询用户审批记录
     */
    @RequestMapping("/viewAppHisList/{id}")
    public String viewAppHisList(@PathVariable String id) {
        UserAccount userAccount = organizeBusin.getUserAccount(id);
        if (userAccount != null) {
            model.addAttribute("metaObject", userAccount);
        }
        return "/workflow/entry/viewAppHisList";
    }

    /**
     * 显示用户信息
     */
    @FunAuth("organize_listUserAccount")
    @RequestMapping("/viewUserAccount/{id}")
    public String viewUserAccount(@PathVariable String id) {
        UserAccount userAccount = organizeBusin.getUserAccount(id);
        if (userAccount != null) {
            model.addAttribute("userAccount", userAccount);
            model.addAttribute("orgDeptUser", organizeBusin.getOrgDeptUser(userAccount));
            model.addAttribute("orgRoleUserList", organizeBusin.getOrgRoleUserList(userAccount));
        }
        return "/organize/organize/viewUserAccount";
    }

    /**
     * 删除用户信息
     */
    @FunAuth("organize_removeUserAccount")
    @RequestMapping("/removeUserAccount")
    @ResponseBody
    public void removeUserAccount(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    UserAccount userAccount = organizeBusin.getUserAccount(id);
                    if (userAccount != null) {
                        businApi.remove(organizeBusin.getOrgRoleUserList(userAccount));
                        businApi.remove(organizeBusin.getOrgDeptUser(userAccount));
                        businApi.remove(userAccount);

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
     * 启用用户
     */
    @FunAuth("organize_enableUserAccount")
    @RequestMapping("/enableUserAccount")
    @ResponseBody
    public void enableUserAccount(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    UserAccount userAccount = organizeBusin.getUserAccount(id);
                    if (userAccount != null) {
                        userAccount.setUserState(Contexts.Y);
                        businApi.save(userAccount);

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
     * 禁用 用户
     */
    @FunAuth("organize_disableUserAccount")
    @RequestMapping("/disableUserAccount")
    @ResponseBody
    public void disableUserAccount(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    UserAccount userAccount = organizeBusin.getUserAccount(id);
                    if (userAccount != null) {
                        userAccount.setUserState(Contexts.N);
                        businApi.save(userAccount);

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
     * 修改用户密码
     */
    @FunAuth("organize_editUserAccountPwd")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editUserAccountPwd/{id}")
    public String editUserAccountPwd(@PathVariable String id) {
        model.addAttribute("userAccount", organizeBusin.getUserAccount(id));
        return "/organize/organize/editUserAccountPwd";
    }

    /**
     * 保存用户密码
     * 
     * @param userId userId
     * @param loginPwd 用户密码
     */
    @FunAuth("organize_editUserAccountPwd")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveUserAccountPwd/{id}")
    public String saveUserAccountPwd(@PathVariable String id, String loginPwd) {
        UserAccount userAccount = organizeBusin.getUserAccount(id);
        if (userAccount != null) {
            userAccount.setLoginPwd(MD5Util.sign(loginPwd));
            businApi.save(userAccount);
            return "redirect:/organize/organize/viewUserAccount/" + userAccount.getId();
        }
        return this.editUserAccountPwd(id);
    }

    /**
     * 查询自己登录记录列表
     */
    @FunAuth("organize_listLoginLog")
    @RequestMapping("/listLoginLog/{id}")
    public String listLoginLog(@PathVariable String id, SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.ipInfo, a.hostInfo, a.userBrowse, a.updateDate from LoginLog a where a.userAccount.id = ?");
        pageBean.addParams(id);
        pageBean.addQueryStr("a.ipInfo,a.hostInfo,a.userBrowse", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("userId", id);
        model.addAttribute("pageBean", pageBean);
        return "/organize/organize/listLoginLog";
    }
    
    /**
     * 查询所有用户登录记录列表
     */
    @FunAuth("organize_listAllLoginLog")
    @RequestMapping("/listAllLoginLog")
    public String listAllLoginLog(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, b.userName, b.userMail, a.ipInfo, a.hostInfo, a.userBrowse, a.updateDate from LoginLog a join a.userAccount b");
        pageBean.addQueryStr("a.ipInfo,a.hostInfo,a.userBrowse,b.userName,b.userMail", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/organize/organize/listAllLoginLog";
    }
}
