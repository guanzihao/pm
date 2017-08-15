package com.pm.right.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.framework.models.CheckSubmit;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.right.bean.Authority;
import com.pm.right.busin.RightBusin;
import com.pm.right.models.AuthTreeModel;
import com.pm.right.models.FunAuth;

@Controller
@RequestMapping("/right/right")
public class RightController extends OrganizeBaseController {

    @Autowired
    public RightBusin rightBusin;

    /**
     * 绑定角色权限
     * 
     * @return bindAuthority
     */
    @FunAuth("right_bindAuthority")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/bindAuthority/{id}/{roleType}")
    public String bindAuthority(@PathVariable String id, @PathVariable int roleType) {
        OrgRole orgRole = organizeBusin.getOrgRole(id);

        //获取角色权限
        List<String> checkedIdList = new ArrayList<String>();
        StringBuffer checkedIds = new StringBuffer();
        List<Authority> authorityList = rightBusin.getAuthorityList(orgRole);
        if (authorityList != null && authorityList.size() > 0) {
            for (Authority authority : authorityList) {
                if (checkedIds.length() > 0) {
                    checkedIds.append(",");
                }
                checkedIds.append(authority.getId());
                checkedIdList.add(authority.getId());
            }
        }

        model.addAttribute("authorityIds", checkedIds);
        model.addAttribute("orgRole", orgRole);
        model.addAttribute("treeData", AuthTreeModel.ToJosn(rightBusin.getAuthorityList(roleType), checkedIdList, true));
        return "/right/right/bindAuthority";
    }
    
    /**
     * 绑定角色权限
     * 
     * @return bindAuthority
     */
    @FunAuth("right_bindAuthorityCom")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/bindAuthorityCom/{id}/{roleType}")
    public String bindAuthorityCom(@PathVariable String id, @PathVariable int roleType) {
        OrgRole orgRole = organizeBusin.getOrgRole(id);

        //获取角色权限
        List<String> checkedIdList = new ArrayList<String>();
        StringBuffer checkedIds = new StringBuffer();
        List<Authority> authorityList = rightBusin.getAuthorityList(orgRole);
        if (authorityList != null && authorityList.size() > 0) {
            for (Authority authority : authorityList) {
                if (checkedIds.length() > 0) {
                    checkedIds.append(",");
                }
                checkedIds.append(authority.getId());
                checkedIdList.add(authority.getId());
            }
        }

        model.addAttribute("authorityIds", checkedIds);
        model.addAttribute("orgRole", orgRole);
        model.addAttribute("treeData", AuthTreeModel.ToJosn(rightBusin.getAuthorityList(roleType), checkedIdList, true));
        return "/right/right/bindAuthority";
    }
    
    /**
     * 绑定角色权限
     * 
     * @return bindAuthority
     */
    @FunAuth("right_bindAuthoritySup")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/bindAuthoritySup/{id}/{roleType}")
    public String bindAuthoritySup(@PathVariable String id, @PathVariable int roleType) {
        OrgRole orgRole = organizeBusin.getOrgRole(id);

        //获取角色权限
        List<String> checkedIdList = new ArrayList<String>();
        StringBuffer checkedIds = new StringBuffer();
        List<Authority> authorityList = rightBusin.getAuthorityList(orgRole);
        if (authorityList != null && authorityList.size() > 0) {
            for (Authority authority : authorityList) {
                if (checkedIds.length() > 0) {
                    checkedIds.append(",");
                }
                checkedIds.append(authority.getId());
                checkedIdList.add(authority.getId());
            }
        }

        model.addAttribute("authorityIds", checkedIds);
        model.addAttribute("orgRole", orgRole);
        model.addAttribute("treeData", AuthTreeModel.ToJosn(rightBusin.getAuthorityList(roleType), checkedIdList, true));
        return "/right/right/bindAuthority";
    }

    /**
     * 绑定角色权限
     * 
     * @return saveAuthority
     */
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveAuthority")
    public String saveAuthority(String id, String authorityIds, int roleType) {
        OrgRole orgRole = organizeBusin.getOrgRole(id);
        List<String> authIdList = Arrays.asList(authorityIds.split(","));
        if (orgRole != null) {
            rightBusin.saveAuthRole(orgRole, authIdList);
        }
        if(roleType == 1){
            return "redirect:/right/right/bindAuthority/" + id +"/" + roleType;
        }else if(roleType == 2){
            return "redirect:/right/right/bindAuthorityCom/" + id +"/" + roleType;
        }else if(roleType == 3){
            return "redirect:/right/right/bindAuthoritySup/" + id +"/" + roleType;
        }
        return "redirect:/right/right/bindAuthority/" + id +"/" + roleType;
    }
}
