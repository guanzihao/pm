package com.pm.right.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.busin.GlobalContext;
import com.pm.core.util.StringUtil;
import com.pm.right.bean.Authority;
import com.pm.right.busin.RightBusin;
import com.sup.company.controller.SupBaseController;

/**
 * 输出菜单
 * 
 * @author ZZY_SIVE
 *
 */

public class TreeViewComTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(TreeViewComTag.class);
    private static final String[] LEVELS = new String[] { "nav-second-level", "nav-third-level" };

    private int orgtype;
    
    public int doStartTag() throws JspException {
        try {
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            CompanyInfoUser companyInfoUser = (CompanyInfoUser) request.getSession().getAttribute(SupBaseController.SUP_SESSION);
            RightBusin rightBusin = (RightBusin) GlobalContext.getSpringContext().getBean(RightBusin.RIGHTBUSIN);
            String bathPath = (String) request.getAttribute("basePath");
            pageContext.getOut().println(drawTree(rightBusin.getAuthorityList(orgtype), bathPath, companyInfoUser, 0));
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        super.release();
        return TagSupport.SKIP_BODY;
    }

    private String drawTree(List<Authority> menus, String basePath, CompanyInfoUser companyInfoUser, int level)
            throws IOException {
        StringBuffer menuStr = new StringBuffer();
       
        menuStr.append(" <div class='mo-mod-menu-77'><ul>");
        for (Authority menu : menus) {
            if (chechRight(menu, companyInfoUser)) {
               /* menuStr.append("<li>");
                if (!StringUtil.isEmpty(menu.getAuthUrl())) {
                    menuStr.append("<a class=\"J_menuItem\" href=\"" + basePath + menu.getAuthUrl() + "\">");
                } else {
                    menuStr.append("<a href=\"javascript:;\">");
                }
                if (!StringUtil.isEmpty(menu.getAuthIcon())) {
                    menuStr.append("<i class=\"fa fa-" + menu.getAuthIcon() + "\"></i>");
                    menuStr.append("<span class=\"nav-label\">" + menu.getAuthName() + " </span>");
                } else {
                    menuStr.append(menu.getAuthName());
                }
                if (chechChildren(menu)) {
                    menuStr.append("<span class=\"fa arrow\"></span>");
                }
                menuStr.append("</a>");
                if (chechChildren(menu)) {
                    menuStr.append("<ul class=\"nav " + LEVELS[level] + " collapse\">");
                    menuStr.append(drawTree(menu.getChildren(), basePath, companyInfoUser, level + 1));
                    menuStr.append("</ul>");
                }
                menuStr.append("</li>");*/
             
//                menuStr.append("<h3 class='menu_head  ' >"+"<i class='iconfont icon_loc'  >"+"&#xe601;"+"</i>"+menu.getAuthName()+"</h3>"+
//                        " <div style='display:none' class='menu_body'>");
                menuStr.append(" <li class='sidebar-main-menu-item' data-role='sidebar-main-menu' item-role='home'>");
                if (!StringUtil.isEmpty(menu.getAuthUrl())) {
                    menuStr.append("<a data-role='sidebar-main-menu-a' class='sidebar-main-menu-a' href=\"" + basePath + menu.getAuthUrl() + "\" target='iframe_PM'>");
                } else {
                    menuStr.append("<a data-role='sidebar-main-menu-a' class='sidebar-main-menu-a' href=\"javascript:;\" target='iframe_PM'>");
                }
                menuStr.append(menu.getAuthName());
                menuStr.append("</a>");
                if (chechChildren(menu)) {
                    menuStr.append(drawChildTree(menu.getChildren(), basePath, companyInfoUser, level + 1));
                }
                menuStr.append("</li>");
            }
            
        }
        menuStr.append("</ul></div>");
        return menuStr.toString();
    }
    
    private String drawChildTree(List<Authority> menus, String basePath, CompanyInfoUser companyInfoUser, int level)
            throws IOException {
        StringBuffer menuStr = new StringBuffer();
        menuStr.append(" <ul data-role='sidebar-sub-menu' class='sidebar-sub-menu' style='display: none;'>");
        for (int i=0;i<menus.size();i++) {
            if (chechRight(menus.get(i), companyInfoUser)) {
                menuStr.append(" <li item-role='exportOrder'> <a target='iframe_PM' class=\"caidan\" href=\"" + basePath + menus.get(i).getAuthUrl() + "\">");
                menuStr.append(menus.get(i).getAuthName());
                menuStr.append("</a>");
                menuStr.append("</li>");
            }
        }
        menuStr.append("</ul>");
        return menuStr.toString();
    }

    private boolean chechRight(Authority menu, CompanyInfoUser companyInfoUser) {
        if (menu.getAuthType() == 1) {
            if (companyInfoUser.getUserAdmin()) {
                return true;
            } else {
                return companyInfoUser.auth(menu.getAuthCode());
            }
        }
        return false;
    }

    private boolean chechChildren(Authority menu) {
        if (menu.getChildren().size() > 0) {
            for (Authority authority : menu.getChildren()) {
                if (authority.getAuthType() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(int orgtype) {
        this.orgtype = orgtype;
    }
}
