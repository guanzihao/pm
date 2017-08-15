package com.pm.right.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.right.models.FunAuth;

/**
 * 权限控制器
 * 
 * @author youliang.fang
 */

public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handler2 = (HandlerMethod) handler;
        FunAuth funAuth = handler2.getMethodAnnotation(FunAuth.class);
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute(OrganizeBaseController.USER_SESSION);
        if (null == funAuth) {
            return true;
        } else if (userAccount == null) {
            request.getRequestDispatcher("/error/error-nosession.jsp").forward(request, response);
            return false;
        } else if (userAccount.getUserAdmin()) {
            return true;
        } else if (!userAccount.auth(funAuth.value())) {
            request.getRequestDispatcher("/error/error-noright.jsp").forward(request, response);
            return false;
        }
        return true;
    }
}
