package com.pm.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pm.core.util.StringUtil;
import com.pm.framework.models.CheckSubmit;

/**
 * 避免重复提交
 * 
 * @author youliang.fang
 */

public class CheckSubmitInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handler2 = (HandlerMethod) handler;
        CheckSubmit checkSubmit = handler2.getMethodAnnotation(CheckSubmit.class);
        if (null == checkSubmit) {
            return true;
        } else {
            boolean saveSession = checkSubmit.saveToken();
            if (saveSession) {
                request.getSession().setAttribute("token", StringUtil.getUUID());
            }
            boolean removeSession = checkSubmit.removeToken();
            if (removeSession) {
                if (isRepeatSubmit(request)) {
                    request.getRequestDispatcher("/error/error.jsp").forward(request, response);
                    return false;
                }
                request.getSession().removeAttribute("token");
            }
        }
        return true;
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession().getAttribute("token");
        if (null == serverToken) {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return false;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
}
