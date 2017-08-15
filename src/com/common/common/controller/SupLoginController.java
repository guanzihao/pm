package com.common.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.pm.company.bean.ComLoginLog;
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.Contexts;
import com.pm.core.util.StringUtil;
import com.pm.framework.bean.MailLog;
import com.pm.framework.models.CheckSubmit;
import com.pm.framework.util.IpUtil;
import com.pm.framework.util.MailTempUtil;
import com.pm.organize.util.MD5Util;
import com.pm.organize.util.RSAUtils;
import com.pm.right.busin.RightBusin;
import com.sup.company.busin.CompanyInfoUserBusin;
import com.sup.company.controller.SupBaseController;

/**
 * 供应商登录
 * 
 * @author guoce
 */

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/common/suplogin")
public class SupLoginController extends SupBaseController {

    @Autowired
    public CompanyInfoUserBusin companyInfoUserBusin;
    
    @Autowired
    public CompanyInfoBusin companyInfoBusin;
    
    @Autowired
    public RightBusin rightBusin;
    
    /**
     * 供应商登录
     * 
     * @return
     */
    @RequestMapping("/supLogin")
    public String supLogin(RedirectAttributes attr) {
        boolean loginStatus = false;

        //获取密钥
        HashMap<String, Object> map = (HashMap<String, Object>) getSessionAttribute(IndexController.SECURITYKEY);
        request.getSession().removeAttribute("SecurityKey");

        // 检查验证码
        String parm = getRequestParameter("kaptchaId");
        String kaptcha = (String) getSessionAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (StringUtil.isEmpty(parm, kaptcha)) {
            loginStatus = true;
        } else {
            model.addAttribute("wwxx", "验证码错误");
        }
       
        //判断用户是否存在
        if (loginStatus) {
            String loginName = getRequestParameter("loginName");
            String loginPwd = getRequestParameter("loginPwd");
            if (!StringUtil.isEmpty(loginName) && !StringUtil.isEmpty(loginPwd)) {
               
                //获得用户
                CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUserByMail(loginName);
                if (companyInfoUser != null && !StringUtil.isEmpty(companyInfoUser.getLoginPwd()) && companyInfoUser.getLoginPwd().equals(MD5Util.sign(loginPwd))) {
                    if(companyInfoUser.getCompanyInfo() != null){
                        if (companyInfoUser.getCompanyInfo().getComState().equals("Y")||companyInfoUser.getCompanyInfo().getComState().equals("C")||companyInfoUser.getCompanyInfo().getComState().equals("L")||companyInfoUser.getCompanyInfo().getComState().equals("W")) {
                            //设置用户
                            setCurrSup(companyInfoUser);
                            
                            //登录日志
                            ComLoginLog loginLog = new ComLoginLog();
                            loginLog.setIpInfo(IpUtil.getIpInfo(request));
                            loginLog.setUserBrowse(IpUtil.getUserBrowse(request));
                            loginLog.setHostInfo(request.getRemoteHost());
                            loginLog.setCompanyInfoUser(companyInfoUser);
                            businApi.save(loginLog);
                            companyInfoUser.setAuthorityList(rightBusin.getAuthorityList(companyInfoUser));
                            String urlMenthod=getRequestParameter("urlMenthod");
                            //保存
                            if(urlMenthod.equals("orderImmediately")){
                                    attr.addAttribute("url","/pm/sup/task/orderPrep");
                            }
                            //attr.addAttribute("loginName", loginName);
                            return "redirect:/sup/index/supHome";
                        }else{
                            setCurrSup(companyInfoUser);
                            return "redirect:/common/suplogin/viewCompanyInfo/" + companyInfoUser.getId();
                        }
                    }else{
                        if (companyInfoUser.getSupCompanyInfo().getComState().equals("Y")||companyInfoUser.getSupCompanyInfo().getComState().equals("W")||companyInfoUser.getSupCompanyInfo().getComState().equals("C")||companyInfoUser.getSupCompanyInfo().getComState().equals("L")) {
                            //设置用户
                            setCurrSup(companyInfoUser);
                            
                            //登录日志
                            ComLoginLog loginLog = new ComLoginLog();
                            loginLog.setIpInfo(IpUtil.getIpInfo(request));
                            loginLog.setUserBrowse(IpUtil.getUserBrowse(request));
                            loginLog.setHostInfo(request.getRemoteHost());
                            loginLog.setCompanyInfoUser(companyInfoUser);
                            businApi.save(loginLog);
                            
                            companyInfoUser.setAuthorityList(rightBusin.getAuthorityList(companyInfoUser));
                            
                            return "redirect:/sup/index/supHome";
                        }else{
                            model.addAttribute("wwxx", "用户已被禁用");
                        }
                    }
                    
                } else {
                    model.addAttribute("usercode", true);
                    request.getSession().setAttribute("usercode", true);
                    model.addAttribute("wwxx", "用户名或密码错误");
                }
            }
        }
        return "/internet/userconfig/userLogin";
    }

    /**
     * 用户退出
     * 
     * @return 退出
     */
    @RequestMapping("/supExit")
    public String supExit() {
        request.getSession().invalidate();
        return "/internet/userconfig/userLogin";
    }

    /**
     * 找回密码
     */
    @RequestMapping("/forgotPwd")
    public String forgotPwd() {
        return "/common/forgotPwdSup";
    }
    /**
     * AJAX检查客户登录名是否冲突
     * 
     * @param userMail 供应商登录名
     * @throws IOException 
     */
    @RequestMapping(value = "/ajaxCheckCompanyInfoUser")
    @ResponseBody
    public void ajaxCheckCompanyInfoUser(String userMail) throws IOException {
        int count = businApi.getQueryPageSize("select a.id from CompanyInfoUser a where userMail = ?", new Object[] { userMail.replaceAll(" ", "") });
        if(count ==0){
           
            PrintWriter out=response.getWriter();
            out.println("0");
        }else{
            PrintWriter out=response.getWriter();
            out.println("1");
        }
    }
    /**
     * 找回密码
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(String userMail, String kaptchaId) {
        String kaptcha = (String) getSessionAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (StringUtil.isEmpty(kaptchaId, kaptcha)) {
            request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

            CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUserByMail(userMail);
            if (companyInfoUser != null) {

                //更新密码
                String pwd = StringUtil.getRandomStr(6);
                companyInfoUser.setLoginPwd(MD5Util.sign(pwd));
                businApi.save(companyInfoUser);

                //邮件内容
                String mailText = MailTempUtil.getValue("organize_forgotPwd.txt");
                mailText = MailTempUtil.fillParams(mailText, "userName", companyInfoUser.getUserName());
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
        return "/common/forgotPwdSup";
    }
    
    /**
     * 显示供应商公司信息
     * 
     * @return viewCompanyInfo
     */
    @RequestMapping("/viewCompanyInfo/{id}")
    public String viewCompanyInfo(@PathVariable String id) {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(id);
        if (companyInfoUser != null) {
            model.addAttribute("companyInfo", companyInfoUser.getCompanyInfo());
            model.addAttribute("companyInfoUser", companyInfoUser);
        }
        return "/common/viewCompanyInfo";
    }

    /**
     * 编辑公司信息
     * 
     * @return editCompanyInfo
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editCompanyInfo/{id}")
    public String editCompanyInfo(@PathVariable String id) {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(id);
        if (companyInfoUser != null) {
            CompanyInfo companyInfo = companyInfoUser.getCompanyInfo();
            if (companyInfo != null) {
                model.addAttribute("companyInfo", companyInfo);
                model.addAttribute("companyInfoUser", companyInfoUser);
            }
        }
        return "/common/editCompanyInfo";
    }

    /**
     * 保存公司信息
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/saveCompanyInfo")
    public String saveCompanyInfo(String companyInfoUserId, CompanyInfo companyInfo, String[] typeIds) {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(companyInfoUserId);
        if (companyInfoUser != null) {
            CompanyInfo info = companyInfoUser.getCompanyInfo();
            if (info != null) {
                info.setComAddress(companyInfo.getComAddress());
                info.setComAssets(companyInfo.getComAssets());
                info.setComBank(companyInfo.getComBank());
                info.setComBankaccount(companyInfo.getComBankaccount());
                info.setComCapital(companyInfo.getComCapital());
                info.setComDutynum(companyInfo.getComDutynum());
                info.setComFoundingtime(companyInfo.getComFoundingtime());
                info.setComPerson(companyInfo.getComPerson());
                info.setComWebsite(companyInfo.getComWebsite());
                info.setComState(Contexts.W);
                businApi.save(info);
            }
        }
        
        return "redirect:/common/suplogin/viewCompanyInfo/" + companyInfoUserId;
    }
}
