package com.pm.right.models;

import java.util.ArrayList;
import java.util.List;

import com.pm.right.bean.Authority;

public class AuthorityModel {

    /**
     * 系统权限缓存
     */
    private static List<Authority> authorities = new ArrayList<Authority>();

    public static List<Authority> getAuthorities() {
        return authorities;
    }

    public static void setAuthorities(List<Authority> authorities) {
        AuthorityModel.authorities = authorities;
    }
}
