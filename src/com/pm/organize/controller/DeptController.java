package com.pm.organize.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.util.StringUtil;
import com.pm.framework.models.CheckSubmit;
import com.pm.organize.bean.OrgDept;
import com.pm.organize.bean.OrgDeptUser;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.models.DeptTreeModel;
import com.pm.right.models.FunAuth;

@Controller
@RequestMapping("/organize/dept")
public class DeptController extends OrganizeBaseController {

    /**
     * AJAX检查部门是否冲突
     */
    @RequestMapping(value = "/ajaxCheckOrgDept")
    @ResponseBody
    public void ajaxCheckOrgDept(String deptId, String deptCode) {
        int count = businApi.getQueryPageSize("select a.id from OrgDept a where id <> ? and deptCode = ?", new Object[] { deptId, deptCode });
        writer(count > 0 ? "false" : "true");
    }

    /**
     * 部门管理
     * 
     * @return listOrgDept
     */
    @FunAuth("dept_listOrgDept")
    @RequestMapping("/listOrgDept")
    public String listOrgDept() {
        model.addAttribute("treeData", DeptTreeModel.ToJosn(organizeBusin.getOrgDeptList(), null, true));
        return "/organize/dept/listOrgDept";
    }

    /**
     * 添加部门
     * 
     * @return addOrgDept
     */
    @FunAuth("dept_editOrgDept")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/addOrgDept")
    public String addOrgDept(String deptId) {
        model.addAttribute("parent", organizeBusin.getOrgDept(deptId));
        return "/organize/dept/editOrgDept";
    }

    /**
     * 编辑部门
     * 
     * @return editOrgDept
     */
    @FunAuth("dept_editOrgDept")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editOrgDept")
    public String editOrgDept(String deptId) {
        OrgDept orgDept = organizeBusin.getOrgDept(deptId);
        if (orgDept != null && orgDept.getParent() != null) {
            OrgDept parent = organizeBusin.getOrgDept(orgDept.getParent().getId());
            model.addAttribute("parent", parent);
        }
        model.addAttribute("orgDept", orgDept);
        return "/organize/dept/editOrgDept";
    }

    /**
     * 保存部门
     * 
     * @param parentId
     * @param orgDept
     * @return listOrgDept
     */
    @FunAuth("dept_editOrgDept")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveOrgDept")
    public String saveOrgDept(String parentId, OrgDept orgDept) {
        OrgDept parent = organizeBusin.getOrgDept(parentId);
        if (!StringUtil.isEmpty(orgDept.getId())) {
            OrgDept dept = organizeBusin.getOrgDept(orgDept.getId());
            dept.setDeptCode(orgDept.getDeptCode());
            dept.setDeptName(orgDept.getDeptName());
            dept.setParent(parent);
            businApi.save(dept);
        } else {
            orgDept.setParent(parent);
            businApi.save(orgDept);
        }
        return "redirect:/organize/dept/listOrgDept";
    }

    /**
     * 删除部门
     * 
     * @param model
     * @return addOrgDept
     */
    @FunAuth("dept_removeOrgDept")
    @RequestMapping("/removeOrgDept")
    public String removeOrgDept(String deptId) {
        OrgDept orgDept = organizeBusin.getOrgDept(deptId);
        if (orgDept != null) {
            if (orgDept.getChildren() == null || orgDept.getChildren().size() == 0) {
                businApi.remove(organizeBusin.getOrgDeptUserList(orgDept));
                businApi.remove(orgDept);
            }
        }
        return "redirect:/organize/dept/listOrgDept";
    }

    /**
     * 编辑部门人员信息
     * 
     * @return editOrgDeptUser
     */
    @FunAuth("dept_editOrgDeptUser")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editOrgDeptUser")
    public String editOrgDeptUser(String deptId) {
        OrgDept orgDept = organizeBusin.getOrgDept(deptId);
        if (orgDept != null) {
            model.addAttribute("orgDept", orgDept);
            model.addAttribute("orgDeptUserList", organizeBusin.getOrgDeptUserList(orgDept));
            model.addAttribute("userAccountList", organizeBusin.getUserAccountList());
        }
        return "/organize/dept/editOrgDeptUser";
    }

    /**
     * 保存部门人员信息
     * 
     * @return saveOrgDeptUser
     */
    @FunAuth("dept_editOrgDeptUser")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveOrgDeptUser")
    public String saveOrgDeptUser(String deptId, String[] userIds) {
        OrgDept orgDept = organizeBusin.getOrgDept(deptId);
        if (orgDept != null) {
            List<OrgDeptUser> orgDeptList = organizeBusin.getOrgDeptUserList(orgDept);
            businApi.remove(orgDeptList);
            if (userIds != null && userIds.length > 0) {
                for (String userId : userIds) {
                    UserAccount userAccount = organizeBusin.getUserAccount(userId);
                    if (userAccount != null) {
                        OrgDeptUser orgDeptUser = organizeBusin.getOrgDeptUser(userAccount);
                        if (orgDeptUser == null) {
                            orgDeptUser = new OrgDeptUser();
                            orgDeptUser.setOrgDept(orgDept);
                            orgDeptUser.setUserAccount(userAccount);
                            businApi.save(orgDeptUser);
                        }
                    }
                }
            }
        }
        return "redirect:/organize/dept/editOrgDeptUser?deptId=" + deptId;
    }
}
