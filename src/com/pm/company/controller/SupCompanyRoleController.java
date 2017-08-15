package com.pm.company.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.models.CheckSubmit;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.right.busin.RightBusin;
import com.pm.right.models.FunAuth;

/**
 * 供应商角色管理
 * 
 * @author ZZY_SIVE
 *
 */
@Controller
@RequestMapping("/supcompany/role")
public class SupCompanyRoleController extends OrganizeBaseController {

    @Autowired
    private CompanyInfoBusin companyInfoBusin;
    
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
     * @return listSupRole
     */
    @FunAuth("suprole_listSupRole")
    @RequestMapping("/listSupRole")
    public String listSupRole(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.roleCode, a.roleName, a.roleType, a.createDate from OrgRole a");
        pageBean.addQueryStr("a.roleCode,a.roleName", searchBean.getSearchName1(), true);
        pageBean.addQueryInt("a.roleType", 3);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/supcompany/role/listSupRole";
    }

    /**
     * 查看角色信息
     * 
     * @param roleId RoleId
     * @return viewOrgRole
     */
    @FunAuth("suprole_listSupRole")
    @RequestMapping("/viewSupRole/{id}")
    public String viewSupRole(@PathVariable String id) {
        model.addAttribute("orgRole", organizeBusin.getOrgRole(id));
        return "/supcompany/role/viewSupRole";
    }

    /**
     * 修改角色信息
     * 
     * @param roleId RoleId
     * @return editSupRole
     */
    @FunAuth("suprole_editSupRole")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editSupRole/{id}/{roleType}")
    public String editSupRole(@PathVariable String id, @PathVariable int roleType) {
        model.addAttribute("roleType", roleType);
        model.addAttribute("orgRole", organizeBusin.getOrgRole(id));
        return "/supcompany/role/editSupRole";
    }

    /**
     * 保存角色信息
     * 
     * @return saveSupRole
     */
    @FunAuth("suprole_editSupRole")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveSupRole")
    public String saveSupRole(OrgRole orgRole, int roleType) {
        orgRole.setRoleType(roleType);
        businApi.save(orgRole);
        return "redirect:/supcompany/role/viewSupRole/" + orgRole.getId();
    }

    /**
     * 删除角色
     * 
     * @param ids IDS
     * @return removeSupRole
     */
    @FunAuth("suprole_removeSupRole")
    @RequestMapping("/removeSupRole")
    @ResponseBody
    public void removeSupRole(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    OrgRole role = organizeBusin.getOrgRole(id);
                    if (role != null) {
                        businApi.remove(companyInfoBusin.getComRoleUserList(role));
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
}
