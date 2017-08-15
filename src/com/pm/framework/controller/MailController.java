package com.pm.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.bean.MailConfig;
import com.pm.framework.bean.MailLog;
import com.pm.framework.models.CheckSubmit;
import com.pm.framework.util.MailTempUtil;
import com.pm.right.models.FunAuth;

/**
 * 邮件处理类
 * 
 * @author FYL
 *
 */

@Controller
@RequestMapping("/framework/mail")
public class MailController extends FileBaseController {

    /**
     * 编辑邮箱配置
     * 
     * @return editMailConfig
     */
    @FunAuth("mail_editMailConfig")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editMailConfig")
    public String editMailConfig() {
        model.addAttribute("mailConfig", frameworkBusin.getMailConfig());
        return "/framework/mail/editMailConfigPop3";
    }

    /**
     * 保存邮箱配置
     * 
     * @param mailConfig mailConfig
     * @return viewMailConfig
     */
    @FunAuth("mail_editMailConfig")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveMailConfig")
    public String saveMailConfig(MailConfig mailConfig) {
        if (mailConfig != null) {
            businApi.save(mailConfig);
        }
        return "redirect:/framework/mail/viewMailConfig";
    }

    /**
     * 显示邮件管理
     * 
     * @return viewMailConfig
     */
    @FunAuth("mail_viewMailConfig")
    @RequestMapping("/viewMailConfig")
    public String viewMailConfig() {
        model.addAttribute("mailConfig", frameworkBusin.getMailConfig());
        return "/framework/mail/viewMailConfig";
    }

    /**
     * 删除邮件管理
     * 
     * @return viewMailConfig
     */
    @FunAuth("mail_removeMailConfig")
    @RequestMapping("/removeMailConfig")
    public String removeMailConfig() {
        MailConfig mailConfig = frameworkBusin.getMailConfig();
        if (mailConfig != null) {
            businApi.remove(mailConfig);
        }
        return "redirect:/framework/mail/viewMailConfig";
    }

    /**
     * 发送测试邮件
     * 
     * @return viewMailConfig
     */
    @FunAuth("mail_viewMailLogTest")
    @RequestMapping("/viewMailLogTest")
    public String viewMailLogTest() {
        MailConfig mailConfig = frameworkBusin.getMailConfig();
        if (mailConfig != null) {

            //邮件内容
            String mailText = MailTempUtil.getValue("mail_viewMailLogTest.txt");
            mailText = MailTempUtil.fillParams(mailText, "mailName", mailConfig.getMailName());

            MailLog mailLog = new MailLog();
            mailLog.setMailSubject("云贸通-测试邮件");
            mailLog.setMailBody(mailText);
            mailLog.setMailToId(mailConfig.getMailName());
            frameworkBusin.sendMailLog(mailLog);
        }
        return "redirect:/framework/mail/viewMailConfig";
    }

    /**
     * 查看邮件日志
     * 
     * @return listMailLog
     */
    @FunAuth("mail_listMailLog")
    @RequestMapping("/listMailLog")
    public String listMailLog(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.mailSubject, a.mailToId, a.mailBody, a.updateDate from MailLog a");
        pageBean.addQueryStr("a.mailSubject,a.mailToId", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/framework/mail/listMailLog";
    }
}
