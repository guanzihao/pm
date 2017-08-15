package com.pm.sms.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.right.models.FunAuth;

/**
 * 短信列表
 * @author ZZY_SIVE
 *
 */
@Controller
@RequestMapping("/sms")
public class SmsController extends OrganizeBaseController {
    protected static final Logger LOGGER = Logger.getLogger(SmsController.class);

    /**
     * 发送短信列表
     * 
     * @param searchBean
     * @return
     */
    @FunAuth("sms_listSmsMsg")
    @RequestMapping("/listSmsMsg")
    public String listSmsMsg(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.phone, a.content, a.submitDate, a.smsState, a.msgId, a.createDate from SmsMsg a");
        pageBean.addQueryStr("a.phone", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.createDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/sms/listSmsMsg";
    }
    
    /**
     * 短信接收列表
     * 
     * @param searchBean
     * @return
     */
    @FunAuth("sms_listSmsMsg")
    @RequestMapping("/listSmsInbox")
    public String listSmsInbox(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.phone, a.content, a.submitDate, a.createDate from SmsInbox a");
        pageBean.addQueryStr("a.phone", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.createDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/sms/listSmsInbox";
    }
    
  
}
