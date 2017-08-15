package com.pm.right.busin.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.busin.BusinApi;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.bean.UserAccount;
import com.pm.right.bean.AuthRole;
import com.pm.right.bean.Authority;
import com.pm.right.busin.RightBusin;

/**
 * 系统权限管理
 * 
 * @author FYL
 */

@Service
@SuppressWarnings("unchecked")
public class RightBusinImpl implements RightBusin {

    @Autowired
    private BusinApi businApi;

    public Authority getAuthority(String id) {
        return (Authority) businApi.get(Authority.class, id);
    }

    public Authority getAuthorityAuthCode(String authCode) {
        return (Authority) businApi.getQueryObject("from Authority where authCode = ?", new Object[] { authCode });
    }

    public List<Authority> getAuthorityList(int orgtype) {
        /*if (AuthorityModel.getAuthorities() != null && AuthorityModel.getAuthorities().size() > 0) {
            return AuthorityModel.getAuthorities();
        } else {
            List<Authority> authorities = businApi.getQueryList("from Authority where parent is NULL and orgtype = ? order by authIndex asc", new Object[] { orgtype});
            AuthorityModel.setAuthorities(authorities);
            return authorities;
        }*/
        List<Authority> authorities = businApi.getQueryList("from Authority where parent is NULL and orgtype = ? order by authIndex asc", new Object[] { orgtype});
        return authorities;
    }

    public void removeAuthority(Authority authority) {
        if (authority != null) {
            List<Authority> list = authority.getChildren();
            for (Authority auth : list) {
                removeAuthority(auth);
            }
            removeAuthority(authority);
        }
    }

    public HashSet<String> getAuthorityList(UserAccount userAccount) {
        HashSet<String> authorities = new HashSet<String>();
        List<Authority> authoritieList = businApi
                .getQueryList(
                        "from Authority where id in (select authority.id from AuthRole where orgRole.id in (select orgRole.id from OrgRoleUser where userAccount.id = ?))",
                        new Object[] { userAccount.getId() });
        for (Authority authority : authoritieList) {
            authorities.addAll(addAuthorityList(authority, authorities));
        }
        return authorities;
    }

    private HashSet<String> addAuthorityList(Authority authority, HashSet<String> authorities) {
        if (authority != null) {
            authorities.add(authority.getAuthCode());
            if (authority.getParent() != null) {
                authorities.addAll(addAuthorityList(authority.getParent(), authorities));
            }
        }
        return authorities;
    }

    public List<Authority> getAuthorityList(OrgRole orgRole) {
        return businApi.getQueryList(
                "from Authority where id in (select authority.id from AuthRole where orgRole.id = ?)",
                new Object[] { orgRole.getId() });
    }

    public AuthRole getAuthRole(OrgRole orgRole, Authority authority) {
        return (AuthRole) businApi.getQueryObject("from AuthRole where orgRole.id = ? and authority.id = ?",
                new Object[] { orgRole.getId(), authority.getId() });
    }

    public List<AuthRole> getAuthRoleList(OrgRole orgRole) {
        return businApi.getQueryList("from AuthRole where orgRole.id = ?", new Object[] { orgRole.getId() });
    }

    public void saveAuthRole(OrgRole orgRole, List<String> authIds) {
        businApi.remove(this.getAuthRoleList(orgRole));
        if (authIds != null && authIds.size() > 0) {
            for (String authId : authIds) {
                Authority authority = this.getAuthority(authId);
                if (authority != null) {
                    AuthRole authRole = this.getAuthRole(orgRole, authority);
                    if (authRole == null) {
                        authRole = new AuthRole();
                        authRole.setOrgRole(orgRole);
                        authRole.setAuthority(authority);
                        businApi.save(authRole);
                    }
                }
            }
        }
    }

    @Override
    public HashSet<String> getAuthorityList(CompanyInfoUser companyInfoUser) {
        HashSet<String> authorities = new HashSet<String>();
        List<Authority> authoritieList = businApi
                .getQueryList(
                        "from Authority where id in (select authority.id from AuthRole where orgRole.id in (select orgRole.id from ComRoleUser where companyInfoUser.id = ?))",
                        new Object[] { companyInfoUser.getId() });
        for (Authority authority : authoritieList) {
            authorities.addAll(addAuthorityList(authority, authorities));
        }
        return authorities;
    }
}
