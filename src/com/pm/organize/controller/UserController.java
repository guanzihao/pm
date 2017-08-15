package com.pm.organize.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.models.CheckSubmit;
import com.pm.notice.busin.NoticeBusin;
import com.pm.organize.bean.OrgDeptUser;
import com.pm.organize.bean.OrgRoleUser;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.util.MD5Util;

@Controller
@RequestMapping("/organize/user")
public class UserController extends OrganizeBaseController {

    @Autowired
    private NoticeBusin noticeBusin;

    /**
     * 用户首页
     * 
     * @return 首页
     */
    @RequestMapping("/index")
    public String index() {
        model.addAttribute("noticeList", noticeBusin.getNoticeList(this.getCurrUser(), false));
        return "/organize/user/index";
    }

    /**
     * 显示用户信息
     * 
     * @return viewUserAccount
     */
    @RequestMapping("/viewUserAccount")
    public String viewUserAccount() {
        UserAccount userAccount = getCurrUser();
        if (userAccount != null) {
            OrgDeptUser orgDeptUser = organizeBusin.getOrgDeptUser(userAccount);
            List<OrgRoleUser> orgRoleUserList = organizeBusin.getOrgRoleUserList(userAccount);
            model.addAttribute("userAccount", userAccount);
            model.addAttribute("orgDeptUser", orgDeptUser);
            model.addAttribute("orgRoleUserList", orgRoleUserList);
        }
        return "/organize/user/viewUserAccount";
    }

    /**
     * 修改用户密码
     * 
     * @return editUserAccountPwd
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editUserAccountPwd")
    public String editUserAccountPwd() {
        return "/organize/user/editUserAccountPwd";
    }

    /**
     * 保存用户密码
     * 
     * @param loginPwd 用户新密码
     * @param userPwd 用户旧密码
     * @return viewUserAccount
     */
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveUserAccountPwd")
    public String saveUserAccountPwd(String loginPwd, String userPwd) {
        UserAccount userAccount = getCurrUser();
        if (userAccount != null) {
            if (MD5Util.sign(userPwd).equals(userAccount.getLoginPwd())) {
                userAccount.setLoginPwd(MD5Util.sign(loginPwd));
                businApi.save(userAccount);

                setCurrUser(userAccount);
                return "redirect:/organize/user/viewUserAccount";
            }
        }
        return this.editUserAccountPwd();
    }

    /**
     * 修改基本信息
     * 
     * @return editUserAccount
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editUserAccount")
    public String editUserAccount() {
        model.addAttribute("userAccount", getCurrUser());
        return "/organize/user/editUserAccount";
    }

    /**
     * 保存基本信息
     * 
     * @return viewUserAccount
     */
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveUserAccount")
    public String saveUserAccount(UserAccount userAccount) {
        UserAccount account = getCurrUser();
        if (account != null) {
            account.setUserName(userAccount.getUserName());
            account.setUserTel(userAccount.getUserTel());
            businApi.save(account);

            setCurrUser(account);
        }
        return "redirect:/organize/user/viewUserAccount";
    }

    /**
     * 查询List列表
     * 
     * @return listUserAccount
     */
    @RequestMapping("/listLoginLog")
    public String listLoginLog(SearchBean searchBean) {
        UserAccount userAccount = getCurrUser();
        if (userAccount != null) {
            PageBean pageBean = new PageBean(searchBean, businApi);
            pageBean.addQuerySQL(
                    "select a.id, a.ipInfo, a.hostInfo, a.userBrowse, a.updateDate from LoginLog a where a.userAccount.id = ?");
            pageBean.addParams(userAccount.getId());
            pageBean.addQueryStr("a.ipInfo,a.hostInfo,a.userBrowse", searchBean.getSearchName1(), true);
            pageBean.query();
            model.addAttribute("pageBean", pageBean);
        }
        return "/organize/user/listLoginLog";
    }
}
