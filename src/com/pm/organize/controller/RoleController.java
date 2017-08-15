package com.pm.organize.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.models.CheckSubmit;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.bean.OrgRoleUser;
import com.pm.organize.bean.UserAccount;
import com.pm.right.busin.RightBusin;
import com.pm.right.models.FunAuth;

@Controller
@RequestMapping("/organize/role")
public class RoleController extends OrganizeBaseController {

    
    @Autowired
    private RightBusin rightBusin;
    
    /**
     * AJAX检查角色是否冲突
     */
    @RequestMapping(value = "/ajaxCheckOrgRole")
    @ResponseBody
    public void ajaxCheckOrgRole(String roleId, String roleCode, String roleName) {
        int count = businApi.getQueryPageSize("select a.id from OrgRole a where id <> ? and (roleCode = ? or roleName = ?)", new Object[] { roleId, roleCode, roleName });
        writer(count > 0 ? "false" : "true");
    }

    /**
     * 角色管理
     * 
     * @return listOrgRole
     */
    @FunAuth("role_listOrgRole")
    @RequestMapping("/listOrgRole")
    public String listOrgRole(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.roleCode, a.roleName, a.roleType, a.createDate from OrgRole a");
        pageBean.addQueryStr("a.roleCode,a.roleName", searchBean.getSearchName1(), true);
        pageBean.addQueryInt("a.roleType", 1);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/organize/role/listOrgRole";
    }

    /**
     * 查看角色信息
     * 
     * @param roleId RoleId
     * @return viewOrgRole
     */
    @FunAuth("role_listOrgRole")
    @RequestMapping("/viewOrgRole/{id}")
    public String viewOrgRole(@PathVariable String id) {
        model.addAttribute("orgRole", organizeBusin.getOrgRole(id));
        return "/organize/role/viewOrgRole";
    }

    /**
     * 修改角色信息
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */
    @FunAuth("role_editOrgRole")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editOrgRole/{id}/{roleType}")
    public String editOrgRole(@PathVariable String id,@PathVariable int roleType) {
        model.addAttribute("roleType", roleType);
        model.addAttribute("orgRole", organizeBusin.getOrgRole(id));
        return "/organize/role/editOrgRole";
    }

    /**
     * 保存角色信息
     * 
     * @return listOrgRole
     */
    @FunAuth("role_editOrgRole")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveOrgRole")
    public String saveOrgRole(OrgRole orgRole, int roleType) {
        orgRole.setRoleType(roleType);
        businApi.save(orgRole);
        return "redirect:/organize/role/viewOrgRole/" + orgRole.getId();
    }

    /**
     * 删除角色
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @FunAuth("role_removeOrgRole")
    @RequestMapping("/removeOrgRole")
    @ResponseBody
    public void removeOrgRole(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    OrgRole role = organizeBusin.getOrgRole(id);
                    if (role != null) {
                        businApi.remove(organizeBusin.getOrgRoleUserList(role));
                        businApi.remove(rightBusin.getAuthRoleList(role));
                        businApi.remove(role);

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
     * 编辑角色人员关系
     * 
     * @return editOrgRoleUser
     */
    @FunAuth("role_editOrgRoleUser")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editOrgRoleUser/{id}")
    public String editOrgRoleUser(@PathVariable String id) {
        OrgRole orgRole = organizeBusin.getOrgRole(id);
        if (orgRole != null) {
            model.addAttribute("orgRole", orgRole);
            model.addAttribute("orgRoleUserList", organizeBusin.getOrgRoleUserList(orgRole));
            model.addAttribute("userAccountList", organizeBusin.getUserAccountList());
        }
        return "/organize/role/editOrgRoleUser";
    }

    /**
     * 保存角色人员关系
     * 
     * @return editOrgRoleUser
     */
    @FunAuth("role_editOrgRoleUser")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveOrgRoleUser")
    public String saveOrgRoleUser(String roleId, String[] userIds) {
        OrgRole orgRole = organizeBusin.getOrgRole(roleId);
        if (orgRole != null) {
            businApi.remove(organizeBusin.getOrgRoleUserList(orgRole));
            if (userIds != null && userIds.length > 0) {
                for (String userId : userIds) {
                    UserAccount userAccount = organizeBusin.getUserAccount(userId);
                    if (userAccount != null) {
                        OrgRoleUser orgRoleUser = organizeBusin.getOrgRoleUser(userAccount, orgRole);
                        if (orgRoleUser == null) {
                            orgRoleUser = new OrgRoleUser();
                            orgRoleUser.setOrgRole(orgRole);
                            orgRoleUser.setUserAccount(userAccount);
                            businApi.save(orgRoleUser);
                        }
                    }
                }
            }
        }
        return "redirect:/organize/role/editOrgRoleUser/" + roleId;
    }
}
