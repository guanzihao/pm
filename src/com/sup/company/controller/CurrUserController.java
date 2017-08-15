package com.sup.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.SearchBean;
import com.pm.framework.models.CheckSubmit;
import com.pm.organize.util.MD5Util;
import com.sup.company.busin.CompanyInfoUserBusin;

/**
 * 登录人个人信息管理
 */

@Controller
@RequestMapping("/sup/company/user")
public class CurrUserController extends SupBaseController {

    @Autowired
    public CompanyInfoUserBusin companyInfoUserBusin;

    @Autowired
    public CompanyInfoBusin companyInfoBusin;

    /**
     * 显示首页
     * 
     * @return 首页
     */
    @RequestMapping("/index")
    public String index(SearchBean searchBean) {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        model.addAttribute("companyInfoUser", companyInfoUser);
        
        return "/sup/company/user/index";
    }

    /**
     * 个人信息
     * 
     * @return viewUser
     */
    @RequestMapping("/viewUser")
    public String viewUser() {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if (companyInfoUser != null) {
            model.addAttribute("companyInfoUser", companyInfoUser);
        }
        return "/sup/company/user/viewUser";
    }

    /**
     * 修改用户密码
     * 
     * @return
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editUserPwd")
    public String editCompanyInfoUserPwd() {
        return "/sup/company/user/editUserPwd";
    }

    /**
     * AJAX验证当前登录人新密码是否正确
     */
    @RequestMapping(value = "/ajaxCheckUserNewPwd")
    @ResponseBody
    public void ajaxCheckUserNewPwd(String userPwd) {
        writer(this.getCurrSup().getLoginPwd().equals(MD5Util.sign(userPwd)) ? "true" : "false");
    }

    /**
     * 保存修改密码
     * 
     * @param loginPwd
     * @param userPwd
     * @return
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/saveUserPwd")
    public String saveUserPwd(String loginPwd, String userPwd) {
        CompanyInfoUser companyInfoUser = getCurrSup();
        if (companyInfoUser != null) {
            if (MD5Util.sign(userPwd).equals(companyInfoUser.getLoginPwd())) {
                companyInfoUser.setLoginPwd(MD5Util.sign(loginPwd));
                businApi.save(companyInfoUser);

                setCurrSup(companyInfoUser);
                return "redirect:/sup/company/user/viewUser";
            }
        }
        return "redirect:/sup/company/user/editUserPwd";
    }

    /**
     * 修改个人信息
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editUser")
    public String editUser() {
        model.addAttribute("companyInfoUser", this.getCurrSup());
        return "/sup/company/user/editUser";
    }

    /**
     * AJAX检查是否冲突
     * 
     * @param id UserID
     * @param userMail 邮箱地址
     * @param userTel 电话
     */
    @RequestMapping(value = "/ajaxCheckUser")
    @ResponseBody
    public void ajaxCheckUser(String id, String userMail, String userTel) {
        int count = businApi.getQueryPageSize("select a.id from CompanyInfoUser a where id <> ? and (userMail = ? or userTel = ?)", new Object[] { id, userMail, userTel });
        writer(count > 0 ? "false" : "true");
    }

    /**
     * 保存个人信息
     */
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveUser")
    public String saveUser(CompanyInfoUser companyInfoUser) {
        CompanyInfoUser infoUser = this.getCurrSup();
        if (infoUser != null) {
            infoUser.setUserFax(companyInfoUser.getUserFax());
            infoUser.setUserName(companyInfoUser.getUserName());
            infoUser.setUserTel(companyInfoUser.getUserTel());
            infoUser.setUserNumber(companyInfoUser.getUserNumber());
            businApi.save(infoUser);
        }
        return "redirect:/sup/company/user/viewUser";
    }
}
