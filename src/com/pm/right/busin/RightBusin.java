package com.pm.right.busin;

import java.util.HashSet;
import java.util.List;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.bean.UserAccount;
import com.pm.right.bean.AuthRole;
import com.pm.right.bean.Authority;

public interface RightBusin {

    /**
     * SpringBean的名称
     */
    public static String RIGHTBUSIN = "rightBusinImpl";

    /**
     * 获得权限
     * 
     * @param authId
     * @return Authority
     */
    Authority getAuthority(String id);

    /**
     * 通过权限编号获得权限
     * 
     * @param authCode
     * @return Authority
     */
    Authority getAuthorityAuthCode(String authCode);

    /**
     * 获得权限列表
     * 
     * @return List<Authority>
     */
    List<Authority> getAuthorityList(int orgtype);

    /**
     * 删除权限
     * 
     * @param authority 权限
     */
    void removeAuthority(Authority authority);

    /**
     * 获取平台用户权限
     * 
     * @return List<Authority>
     */
    HashSet<String> getAuthorityList(UserAccount userAccount);

    /**
     * 获取角色权限
     * 
     * @return List<Authority>
     */
    List<Authority> getAuthorityList(OrgRole orgRole);

    /**
     * 获取角色权限关系
     * 
     * @param orgRole
     * @param authority
     * @return
     */
    AuthRole getAuthRole(OrgRole orgRole, Authority authority);

    /**
     * 查询角色和权限关系
     * 
     * @param orgRole 角色
     * @return List<AuthRole>
     */
    List<AuthRole> getAuthRoleList(OrgRole orgRole);

    /**
     * 保存角色权限
     * 
     * @param orgRole 角色
     * @param authIds 权限IDS
     */
    void saveAuthRole(OrgRole orgRole, List<String> authIds);
    
    /**
     * 获取客户供应商用户权限
     * 
     * @return List<Authority>
     */
    HashSet<String> getAuthorityList(CompanyInfoUser companyInfoUser);
}
