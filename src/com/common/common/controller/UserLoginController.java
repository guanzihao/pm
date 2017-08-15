package com.common.common.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pm.core.model.Contexts;
import com.pm.core.util.StringUtil;
import com.pm.framework.bean.MailLog;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.framework.util.IpUtil;
import com.pm.framework.util.MailTempUtil;
import com.pm.organize.bean.LoginLog;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.organize.util.MD5Util;
import com.pm.right.busin.RightBusin;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/common/userlogin")
public class UserLoginController extends OrganizeBaseController {

    @Autowired
    public RightBusin rightBusin;

    @Autowired
    public FrameworkBusin frameworkBusin;

    /**
     * 用户登录
     * 
     * @return 成功页面
     */
    @RequestMapping("/userLogin")
    public String userLogin(RedirectAttributes attr) {
        boolean loginStatus = true;
        model.addAttribute("cwxx", "");
        //获取密钥
        HashMap<String, Object> map = (HashMap<String, Object>) getSessionAttribute(IndexController.SECURITYKEY);
        request.getSession().removeAttribute("SecurityKey");

        // 检查验证码
        String parm = getRequestParameter("kaptchaId");
        String kaptcha = (String) getSessionAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (StringUtil.isEmpty(parm, kaptcha)) {
            loginStatus = true;
        }
        else
        {
            model.addAttribute("cwxx", "验证码错误");
        }

        //判断用户是否存在
        if (loginStatus) {
            String loginName = getRequestParameter("loginName");
            String loginPwd = getRequestParameter("loginPwd");
            if (!StringUtil.isEmpty(loginName) && !StringUtil.isEmpty(loginPwd)) {

               
                //获得用户
                UserAccount userAccount = organizeBusin.getUserAccountByMail(loginName);
                if(userAccount == null)
                {
                    userAccount = organizeBusin.getUserAccountByPhone(loginName);
                }
                
                if (userAccount != null && !StringUtil.isEmpty(userAccount.getLoginPwd()) && userAccount.getLoginPwd().equals(MD5Util.sign(loginPwd))) {
                    //设置用户
                    setCurrUser(userAccount);

                    //记录日志
                    LoginLog loginLog = new LoginLog();
                    loginLog.setIpInfo(IpUtil.getIpInfo(request));
                    loginLog.setUserBrowse(IpUtil.getUserBrowse(request));
                    loginLog.setHostInfo(request.getRemoteHost());
                    loginLog.setUserAccount(userAccount);
                    businApi.save(loginLog);

                    //设置权限
                    if (userAccount.getUserState().equals(Contexts.Y)) {
                        userAccount.setAuthorityList(rightBusin.getAuthorityList(userAccount));
                    }
                    String urlMenthod=getRequestParameter("urlMenthod");
                    String flowType=getRequestParameter("flowType");
                    if(urlMenthod.equals("consignationQuote")){
                        if(flowType.equals("Bg")){
                            attr.addAttribute("url","/pm/pm/consignationQuote/listBgConsignationQuote");
                        }else if(flowType.equals("Cc")){
                            attr.addAttribute("url", "/pm/pm/consignationQuote/listCcConsignationQuote");
                        }else if(flowType.equals("Wl")){
                            attr.addAttribute("url","/pm/pm/consignationQuote/listWlConsignationQuote");
                        }else if(flowType.equals("Wm")){
                            attr.addAttribute("Wm", "/pm/pm/consignationQuote/listWmConsignationQuote");
                        }                     
                    }
                    return "redirect:/organize/index/userHome";
                }
                else
                {
                    model.addAttribute("cwxx", "用户名或密码错误");
                }    
            }
        }
        return "/internet/userconfig/adminLogin";
    }

    /**
     * 用户退出
     * 
     * @return 退出
     */
    @RequestMapping("/userExit")
    public String userExit() {
        request.getSession().invalidate();
        return "/internet/userconfig/adminLogin";
    }

    /**
     * 找回密码
     */
    @RequestMapping("/forgotPwd")
    public String forgotPwd() {
        return "/common/forgotPwdUser";
    }

    /**
     * 找回密码
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(String userMail, String kaptchaId) {
        String kaptcha = (String) getSessionAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (StringUtil.isEmpty(kaptchaId, kaptcha)) {
            request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

            UserAccount userAccount = organizeBusin.getUserAccountByMail(userMail);
            if (userAccount != null) {

                //更新密码
                String pwd = StringUtil.getRandomStr(6);
                userAccount.setLoginPwd(MD5Util.sign(pwd));
                businApi.save(userAccount);

                //邮件内容
                String mailText = MailTempUtil.getValue("organize_forgotPwd.txt");
                mailText = MailTempUtil.fillParams(mailText, "userName", userAccount.getUserName());
                mailText = MailTempUtil.fillParams(mailText, "userMail", userMail);
                mailText = MailTempUtil.fillParams(mailText, "userPwd", pwd);
                MailLog log = new MailLog();
                log.setMailBody(mailText);
                log.setMailToId(userMail);
                log.setMailSubject("云贸通-用户密码找回");
                frameworkBusin.sendMailLog(log);

                return "/common/forgotPwdSuccess";
            }
        }
        return "/common/forgotPwdUser";
    }
}
