package com.pm.notice.controller;

import java.util.List;

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
import com.pm.notice.bean.NoticeUser;
import com.pm.notice.busin.NoticeBusin;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;

/**
 * 通知管理
 * 
 * @author youliang.fang
 */

@Controller
@RequestMapping("/notice/notice")
public class NoticeController extends OrganizeBaseController {

    @Autowired
    private NoticeBusin noticeBusin;

    /**
     * 查询收件箱列表
     */
    @RequestMapping("/listNotice")
    public String listNotice(SearchBean searchBean) {
        String userid=this.getCurrUser().getId();
        PageBean pageBean = new PageBean(searchBean, businApi);
       String addsql="";
       if (searchBean.getSearchInt1() == 1) {
           addsql="and a.notice_IsRead = 0";
       } else if (searchBean.getSearchInt1() == 2) {
           addsql="and a.notice_IsRead = 1";
       }
       String name="";
       if(searchBean.getSearchName1()!=null&&searchBean.getSearchName1().length()>0){
               name="and a.notice_Title like '"+searchBean.getSearchName1()+"'";
       }
      
       String sql="select a.obj_id,a.notice_Title,a.notice_IsFile,a.obj_updateDate,u.user_Name as name,a.notice_IsRead,a.notice_Draft from PM_NOTICE_Notice a"
               
               + " inner join  PM_ORGANIZE_UserAccount u on a.user_Account= u.obj_Id where a.notice_Draft =0 and a.obj_Id in (select nu.user_Notice "
               
               + " from PM_NOTICE_NoticeUser nu where nu.user_Account='"+userid+"') and a.user_Account is not NULL "+addsql+" "+name
              
               + " UNION all"
               +" select a.obj_id,a.notice_Title,a.notice_IsFile,a.obj_updateDate,u.user_Name as name,a.notice_IsRead,a.notice_Draft from PM_NOTICE_Notice a"
               
               + " inner join  PM_COMPANY_CompanyInfoUser u on a.companyInfoUser= u.obj_Id where a.notice_Draft =0 and a.obj_Id in (select nu.user_Notice "
               
               + " from PM_NOTICE_NoticeUser nu where nu.user_Account='"+userid+"') and a.companyInfoUser is not NULL "+addsql+" "+name; 
        
       pageBean.addQuerySQL(sql);
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
       
        return "/notice/notice/listNotice";
    }

    /**
     * 查询已发送箱列表
     */
    @RequestMapping("/listSendNotice")
    public String listSendNotice(SearchBean searchBean) {
        String userid=this.getCurrUser().getId();
        PageBean pageBean = new PageBean(searchBean, businApi);
        String addsql="";
        if (searchBean.getSearchInt1() == 1) {
            addsql="and a.notice_IsRead = 0";
        } else if (searchBean.getSearchInt1() == 2) {
            addsql="and a.notice_IsRead = 1";
        }
        String name="";
        if(searchBean.getSearchName1()!=null &&searchBean.getSearchName1().length()>0){
                name="and a.notice_Title like '%"+searchBean.getSearchName1()+"%'";
        }
        String sql= "  select a.obj_id,a.notice_Title,a.notice_IsFile"
                    
                    +" ,a.obj_updateDate,cm.user_Name as name,a.notice_IsRead,a.notice_Draft from PM_NOTICE_Notice a,PM_NOTICE_NoticeUser c,PM_COMPANY_CompanyInfoUser cm"
                   
                    +" where a.obj_Id=c.user_Notice and c.companyInfoUser is not null  and c.companyInfoUser=cm.obj_Id and a.user_Account='"+userid+"' "+addsql+" "+name
                
                    + " UNION all"
                   
                    +" select a.obj_id,a.notice_Title,a.notice_IsFile"
                
                    +" ,a.obj_updateDate,cm.user_Name as name,a.notice_IsRead,a.notice_Draft from PM_NOTICE_Notice a,PM_NOTICE_NoticeUser c,PM_ORGANIZE_UserAccount cm"
                    
                    +" where a.obj_Id=c.user_Notice and c.user_Account is not null  and c.user_Account=cm.obj_Id and a.user_Account='"+userid+"' "+addsql+" "+name ;
                    
        
        //pageBean.addQueryStr("a.noticeTitle", searchBean.getSearchName1(), true);
        //pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        //pageBean.addOrderBy("a.updateDate", true);
        
        pageBean.addQuerySQL(sql);
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        return "/notice/notice/listSendNotice";
    }

    /**
     * 查询草稿箱列表
     */
    @RequestMapping("/listDraftNotice")
    public String listDraftNotice(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL(
                "select a.id, a.noticeTitle, a.noticeIsFile, a.updateDate, b.userName, a.noticeIsRead, a.noticeDraft from Notice a , UserAccount b");
        pageBean.addQuerySQL("where a.noticeDraft = ? and b.id = ? and a.userAccount = b.id");
        pageBean.addParams(true);
        pageBean.addParams(this.getCurrUser().getId());
        pageBean.addQueryStr("a.noticeTitle,b.userName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/notice/notice/listDraftNotice";
    }

    /**
     * 编辑信息
     * 
     * @return editNotice
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editNotice/{id}")
    public String editNotice(@PathVariable String id) {
        Notice notice = noticeBusin.getNotice(id);
        if (notice != null) {
            StringBuffer selectList = new StringBuffer();
            for (NoticeUser noticeUser : notice.getNoticeUserList()) {
                if (selectList.length() > 0) {
                    selectList.append(",");
                }
               
                selectList.append(StringUtil.getSqlId(organizeBusin.getUserAccount(noticeUser.getId()).toString()));
            }
            model.addAttribute("selectList", selectList);
            model.addAttribute("notice", notice);
        }
        model.addAttribute("userAccountList", organizeBusin.getCompanyInfoUser());
        return "/notice/notice/editNotice";
    }

    /**
     * 保存信息
     * 
     * @return saveNotice
     */
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveNotice")
    public String saveNotice(String userIds, Notice notice, String[] noticeFiles) {
        notice.setUserAccount(this.getCurrUser());
        if (!StringUtil.isEmpty(userIds)) {
            noticeBusin.saveNotice(notice, userIds.split(","));
            frameworkBusin.saveUploadFileOwner(notice, noticeFiles);
        }
        if (notice.getNoticeDraft()) {
            return "redirect:/notice/notice/listDraftNotice";
        } else {
            return "redirect:/notice/notice/listSendNotice";
        }
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
                notice.setNoticeIsRead(true);
                businApi.save(notice);
        }
        model.addAttribute("notice", notice);
        return "/notice/notice/viewNotice";
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
        return "/notice/notice/forwardNotice";
    }
}
