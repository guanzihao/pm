package com.sup.company.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.CheckSubmit;
import com.pm.notice.bean.Notice;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;
import com.sup.company.busin.ComNoticeBusin;

/**
 * 通知管理
 * 
 * @author youliang.fang
 */

@Controller
@RequestMapping("/sup/company/notice/notice")
public class CompanyNoticeController extends SupBaseController {

    @Autowired
    private ComNoticeBusin noticeBusin;

    /**
     * 查询收件箱列表
     */
    @RequestMapping("/listNotice")
    public String listNotice(SearchBean searchBean) {
        String userid=this.getCurrSup().getId();

        PageBean pageBean = new PageBean(searchBean, businApi);
        String addsql="";
        if (searchBean.getSearchInt1() == 1) {
            addsql="and a.notice_IsRead = 0";
        } else if (searchBean.getSearchInt1() == 2) {
            addsql="and a.notice_IsRead = 1";
        }
        String name="";
        if( searchBean.getSearchName1()!=null&&searchBean.getSearchName1().length()>0){
                name="and a.notice_Title like '"+searchBean.getSearchName1()+"'";
        }
        String updatetime="";
        
        String sql="select a.obj_id,a.notice_Title,a.notice_IsFile,a.obj_updateDate,u.user_Name,a.notice_IsRead,a.notice_Draft from PM_NOTICE_Notice a"
                
                + " inner join  PM_ORGANIZE_UserAccount u on a.user_Account= u.obj_Id where a.notice_Draft =0 and a.obj_Id in (select nu.user_Notice "
                
                + " from PM_NOTICE_NoticeUser nu where nu.companyInfoUser='"+userid+"') and a.user_Account is not NULL "+addsql+" "+name+" "+updatetime
               
                + " UNION all"
                +" select a.obj_id,a.notice_Title,a.notice_IsFile,a.obj_updateDate,u.user_Name,a.notice_IsRead,a.notice_Draft from PM_NOTICE_Notice a"
                
                + " inner join  PM_COMPANY_CompanyInfoUser u on a.companyInfoUser= u.obj_Id where a.notice_Draft =0 and a.obj_Id in (select nu.user_Notice "
                
                + " from PM_NOTICE_NoticeUser nu where nu.companyInfoUser='"+userid+"') and a.companyInfoUser is not NULL "+addsql+" "+name+" "+updatetime; 
         
        pageBean.addQuerySQL(sql);
         pageBean.sqlquerys();
        
        model.addAttribute("pageBean", pageBean);
       
        return "/sup/company/company/listNotice";
    }




    /**
     * 显示信息
     * 
     * @return viewNotice
     */
    @RequestMapping("/viewNotice/{id}")
    public String viewNotice(@PathVariable String id) {
        Notice notice = noticeBusin.getNotice(id);
        if (notice != null && !notice.getNoticeDraft() && !notice.getNoticeIsRead()) {
            String isRead = this.getRequestParameter("isRead");
            if (!StringUtil.isEmpty(isRead)) {
                notice.setNoticeIsRead(true);
                businApi.save(notice);
            }
        }
        model.addAttribute("notice", notice);
        return "/sup/company/company/viewNotice";
    }

    /**
     * 转发邮件
     * 
     * @param noticeCode
     * @return editNotice
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/forwardNotice/{id}")
    public String forwardNotice(@PathVariable String id) {
        Notice notice = noticeBusin.getNotice(id);
        if (notice != null) {
            model.addAttribute("notice", notice);
            model.addAttribute("userAccountList", organizeBusin.getCompanyInfoUser());
        }
        return "/sup/company/company/forwardNotice";
    }
}
