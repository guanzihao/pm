package com.pm.organize.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;

/**
 * 判断用户是否登录
 * 
 * @author youliang.fang
 */

public class SessionInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute(OrganizeBaseController.USER_SESSION);
        if (userAccount == null) {
            request.getRequestDispatcher("/error/error-nosession.jsp").forward(request, response);
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
