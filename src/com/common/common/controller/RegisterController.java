package com.common.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.ComRoleUser;
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.Contexts;
import com.pm.framework.bean.IdentifyingCode;
import com.pm.framework.bean.MailLog;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.framework.models.CheckSubmit;
import com.pm.framework.util.MailTempUtil;
import com.pm.notice.bean.Notice;
import com.pm.notice.bean.NoticeUser;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.organize.util.MD5Util;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;
import com.pm.sysconfig.bean.Systemplate;
import com.pm.sysconfig.busin.EnumBusin;
import com.pm.sysconfig.busin.SystemplateBusin;
import com.pm.task.bean.AjaxDomain;
import com.sup.company.busin.CompanyInfoUserBusin;

/**
 * 供应商注册
 * 
 * @author FYL5728
 */

@Controller
@RequestMapping("/common/register")
public class RegisterController extends OrganizeBaseController {

    @Autowired
    public CompanyInfoBusin companyInfoBusin;
    
    @Autowired
    public CompanyInfoUserBusin companyInfoUserBusin;
    
    @Autowired
    private EnumBusin enumBusin;
    
    @Autowired
    public FrameworkBusin frameworkBusin;
    
    @Autowired
    private SystemplateBusin systemplateBusin;
    /**
     * 客户注册须知
     * 
     * @return registerRead
     */
    @RequestMapping("/registerRead")
    public String registerRead() {
        return "/common/registerRead";
    }

    /**
     *客户注册
     * 
     * @return registerRead
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/registerCompany")
    public String registerCompany() {
        return "/common/registerCompany";
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
        String code="";
        if(count>0){
            
            code="1";
        }else{
            code="0";
        }
        PrintWriter out = response.getWriter();
        out.println(code);
       
    }
    /**
     * 生成6位数的验证码
     * @return
     */
    public static String generateWord() {
        
        Random r = new Random();
        String res = "";
        for (int i = 0; i < 6; i++) {
            int temp = r.nextInt(9);
            res = res + temp;
        }
        return res;
    }
    /**
     *发送邮件
     * 
     * @return registerRead
     * @throws IOException 
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/yanzhengma")
    @ResponseBody
    public void yanzhengma(String email) throws IOException {
        PrintWriter out = response.getWriter();
        int count = businApi.getQueryPageSize("select a.id from CompanyInfoUser a where userMail = ?", new Object[] { email});
        if(count>0){
            out.println("300");
        }else{
            IdentifyingCode identifyingCode =  frameworkBusin.getIdentifyingCode(email);
            IdentifyingCode identifyingCodes=new IdentifyingCode();
            String code=generateWord();
            if(identifyingCode!=null){
                identifyingCode.setRegistcode(code);
                businApi.save(identifyingCode);
                
            }else{
                identifyingCodes.setRegistemail(email);
                identifyingCodes.setRegistcode(code);
                businApi.save(identifyingCodes);
            }
            if (email != null) {
                //邮件内容
                Enumtype enumType = enumBusin.getEnumtypeByName("邮件信息枚举");
                List<Enumitems> enumitems = enumBusin.getEnumitemList(enumType);
                for (Enumitems enumitems2 : enumitems) {
                    if(enumitems2.getName().equals("注册邮件")){
                        Systemplate systemplate=systemplateBusin.getSystemplatetype(enumitems2.getId());
                        String mailText = MailTempUtil.getValue("mail_viewMailLogTest.txt");
                        mailText = MailTempUtil.fillParams(mailText, "codes", systemplate.getTempcontet());
                        mailText = MailTempUtil.fillParams(mailText, "code", code);
                        MailLog mailLog = new MailLog();
                        mailLog.setMailSubject("云贸通-注册邮件");
                        mailLog.setMailBody(mailText);
                        mailLog.setMailToId(email);
                        frameworkBusin.sendMailLog(mailLog);
                        out.println(code);
                    }
                }
            }
        }
    }

    /**
     *发送找回密码邮件
     * 
     * @return registerRead
     * @throws IOException 
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/yanzhengmapass")
    @ResponseBody
    public void yanzhengmapass(String email) throws IOException {
        PrintWriter out = response.getWriter();
        int count = businApi.getQueryPageSize("select a.id from CompanyInfoUser a where userMail = ?", new Object[] { email});
        System.out.println(count);
        if(count==0){
            out.println("300");
        }else{
            IdentifyingCode identifyingCode =  frameworkBusin.getIdentifyingCode(email);
            IdentifyingCode identifyingCodes=new IdentifyingCode();
            String code=generateWord();
            if(identifyingCode!=null){
                identifyingCode.setRegistcode(code);
                businApi.save(identifyingCode);
                
            }else{
                identifyingCodes.setRegistemail(email);
                identifyingCodes.setRegistcode(code);
                businApi.save(identifyingCodes);
            }
            if (email != null) {
                //邮件内容
                Enumtype enumType = enumBusin.getEnumtypeByName("邮件信息枚举");
                List<Enumitems> enumitems = enumBusin.getEnumitemList(enumType);
                for (Enumitems enumitems2 : enumitems) {
                    if(enumitems2.getName().equals("注册邮件")){
                        Systemplate systemplate=systemplateBusin.getSystemplatetype(enumitems2.getId());
                        String mailText = MailTempUtil.getValue("mail_viewMailLogTest.txt");
                        mailText = MailTempUtil.fillParams(mailText, "codes", systemplate.getTempcontet());
                        mailText = MailTempUtil.fillParams(mailText, "code", code);
                        MailLog mailLog = new MailLog();
                        mailLog.setMailSubject("EPS电子采购系统-测试邮件");
                        mailLog.setMailBody(mailText);
                        mailLog.setMailToId(email);
                        frameworkBusin.sendMailLog(mailLog);
                        out.println(code);
                    }
                }
            }
        }
    }
    
  
    @RequestMapping("/password")
    public String password(CompanyInfoUser companyuser)  {
        CompanyInfoUser com=companyInfoBusin.getCompanyInfouser(companyuser.getUserMail());
        if(com!=null){
            com.setLoginPwd(MD5Util.sign(companyuser.getLoginPwd()));
            businApi.save(com);
        }
        model.addAttribute("userName", companyuser.getUserMail());
        model.addAttribute("info", "1");
        return "/internet/userconfig/RegisterOk";
    }
    
    
    
    /**
     * AJAX检查公司是否冲突
     * 
     * @param id ComID
     * @param comName 公司名称
     * @param comCode 公司编号
     */
    @RequestMapping(value = "/ajaxCheckCompanyInfo")
    @ResponseBody
    public void ajaxCheckCompanyInfo(String id, String comName, String comCode) {
        int count = businApi.getQueryPageSize("select a.id from CompanyInfo a where  comState=?  and  (comName = ? or comCode = ?)", new Object[] {Contexts.Y, comName.replaceAll(" ", ""), comCode });
        writer(count > 0 ? "false" : "true");
    }

   
    
    /**
     * AJAX检查税务登记证号是否冲突
     * 
     * @param comDutynum 税务登记证号
     */
    @RequestMapping(value = "/ajaxCheckCompanyInfoComDutynum")
    @ResponseBody
    public void ajaxCheckCompanyInfoComDutynum(String comDutynum) {
        int count = businApi.getQueryPageSize("select a.id from CompanyInfo a where comDutynum = ?", new Object[] { comDutynum.replaceAll(" ", "") });
        writer(count > 0 ? "false" : "true");
    }
    
    
    
    
    /**
     * ajax检查验证码是否正确
     * 
     * @param comDutynum 税务登记证号
     * @throws IOException 
     */
    @RequestMapping(value = "/ajaxyangzhengma")
    @ResponseBody
    public AjaxDomain ajaxCheckyanzhengma(String email,String registcode) {
        
        AjaxDomain ajaxDomain=new AjaxDomain();
       
        try {
            if(email!=null && email.length()>0){
              //获取正确的验证码
                IdentifyingCode identifyingCodes =  frameworkBusin.getIdentifyingCode(email);
                //查看客户所输入的验证码是否正确
                if(identifyingCodes.getRegistcode().equals(registcode) ){
                    ajaxDomain.setStatusCode("200");
                }else{
                    ajaxDomain.setStatusCode("300");
                }
            }
        } catch (Exception e) {
           ajaxDomain.setStatusCode("400");
            e.printStackTrace();
        }
        return ajaxDomain;
    }

    /**
     * 客户注册
     * 
     * @param companyInfo 公司信息
     * @param companyInfoUser 供应商信息
     * @return 登录页面
     * @throws IOException 
     */
    @RequestMapping("/saveCompany")
    public String saveCompany(CompanyInfo companyInfo, CompanyInfoUser companyInfoUser, String[] ids ) throws IOException {
        companyInfo.setComCode(companyInfoBusin.getCompanyInfoCode());
        companyInfo.setComName("新公司");
        //设置公司为资料不完整(s)完整(E)
        companyInfo.setComState(Contexts.C);
        companyInfo.setComLink(companyInfoUser.getUserName());
        companyInfo.setComTel(companyInfoUser.getUserTel());
        companyInfo.setComEmail(companyInfoUser.getUserMail());
        companyInfo.setComsource("平台注册");
        businApi.save(companyInfo);
        companyInfoUser.setCompanyInfo(companyInfo);
        companyInfoUser.setLoginPwd(MD5Util.sign(companyInfoUser.getLoginPwd()));
        companyInfoUser.setUserAdmin(false);
        companyInfoUser.setUserName("新用户");
        companyInfoUser.setUserState(Contexts.Y);
        businApi.save(companyInfoUser);
        //添加站内消息
        Notice notice=new Notice();
        notice.setNoticeTitle("用户注册");
        notice.setNoticeText("用户"+companyInfoUser.getUserMail()+"注册!");
        notice.setNoticeIsRead(true);
        notice.setCompanyInfoUser(companyInfoUser);
        businApi.save(notice);
        //添加noticeuser信息
        NoticeUser noticeUser=new NoticeUser();
        noticeUser.setNotice(notice);
        UserAccount userinfo=organizeBusin.getUserAccount("user_admin");
        noticeUser.setUserAccount(userinfo);
        businApi.save(noticeUser);
        //添加注册人员的基本权限CompanyInfoUser
        OrgRole orgRole =companyInfoBusin.getOrgRole("C");
        ComRoleUser ComRoleUser=new ComRoleUser();
        ComRoleUser.setOrgRole(orgRole);
        ComRoleUser.setCompanyInfoUser(companyInfoUser);
        businApi.save(ComRoleUser);
        model.addAttribute("userName", companyInfoUser.getUserMail());
        model.addAttribute("info", "0");
        return "/internet/userconfig/RegisterOk";
    }
}
