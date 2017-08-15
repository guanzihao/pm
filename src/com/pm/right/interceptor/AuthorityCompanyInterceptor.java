package com.pm.right.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.right.models.FunAuth;
import com.sup.company.controller.SupBaseController;

/**
 * 权限控制器
 * 
 * @author youliang.fang
 */

public class AuthorityCompanyInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handler2 = (HandlerMethod) handler;
        FunAuth funAuth = handler2.getMethodAnnotation(FunAuth.class);
        CompanyInfoUser companyInfoUser = (CompanyInfoUser) request.getSession().getAttribute(SupBaseController.SUP_SESSION);
        if (null == funAuth) {
            return true;
        } else if (companyInfoUser == null) {
            request.getRequestDispatcher("/error/error-nosession.jsp").forward(request, response);
            return false;
        } else if (companyInfoUser.getUserAdmin()) {
            return true;
        } else if (!companyInfoUser.auth(funAuth.value())) {
            request.getRequestDispatcher("/error/error-noright.jsp").forward(request, response);
            return false;
        }
        return true;
    }
}
