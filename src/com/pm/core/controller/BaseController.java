package com.pm.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pm.core.busin.BusinApi;

public class BaseController {
    private static Logger LOGGER = Logger.getLogger(BaseController.class);

    @Autowired
    public BusinApi businApi;

    /**
     * 获得request
     */
    public HttpServletRequest request;

    /**
     * 获得response
     */
    public HttpServletResponse response;

    /**
     * 获得model
     */
    public Model model;

    /**
     * 统一初始化参数
     * 
     * @param request
     * @param response
     */
    @ModelAttribute
    public void initModel(HttpServletRequest request, HttpServletResponse response, Model model) {
        this.request = request;
        this.response = response;
        this.model = model;
    }

    /**
     * 统一异常处理类
     * 
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler
    public String exceptionHandler(HttpServletRequest request, Exception ex) {
        LOGGER.error(ex, ex);
        request.setAttribute("exception", ex);
        return "/resource/error.jsp";
    }

    /**
     * 获得Request,Parameter
     * 
     * @param name
     * @return
     */
    public String getRequestParameter(String name) {
        return request.getParameter(name);
    }

    /**
     * 获得Request,Attribute
     * 
     * @param name
     * @return
     */
    public Object getRequestAttribute(String name) {
        return request.getAttribute(name);
    }

    /**
     * 获得Session,Attribute
     * 
     * @param name
     * @return
     */
    public Object getSessionAttribute(String name) {
        return request.getSession().getAttribute(name);
    }

    /**
     * 页面输出信息
     * 
     * @param object 输出值
     */
    public void writer(Object obj) {
        try {
            if (obj != null) {
                response.getWriter().write(String.valueOf(obj));
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
    }

    /**
     * 抛出异常信息
     * 
     * @param e
     */
    public void addLogger(Exception e) {
        LOGGER.error(e, e);
    }
}
