package com.pm.noticeinformed.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.models.CheckSubmit;
import com.pm.noticeinformed.bean.NoticeInformed;
import com.pm.noticeinformed.busin.NoticeInformedBusin;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.right.models.FunAuth;

/**
 * 公告通知管理
 * 
 * @author ZZY_SIVE
 *
 */

@Controller
@RequestMapping("/noticeinformed/noticeinformed")
public class NoticeInformedController extends OrganizeBaseController {

    @Autowired
    private NoticeInformedBusin noticeInformedBusin;

    /**
     * 查询公告通知列表,只查看对所有人公开，和平台公开的公告
     */
    @FunAuth("noticeinformed_listNoticeInformed")
    @RequestMapping("/listNoticeInformed")
    public String listNoticeInformed(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.noticeTitle, a.noticeType, a.noticeState, b.userName, a.updateDate from NoticeInformed a join a.userAccount b");
        pageBean.addQuerySQL("where a.noticeType in (1,2) and a.noticeState = 1");
        pageBean.addQueryStr("a.noticeTitle,b.userName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/noticeinformed/noticeinformed/listNoticeInformed";
    }
    
    /**
     * 显示信息
     * 
     * @return viewNoticeInformed
     */
    @FunAuth("noticeinformed_listNoticeInformed")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/viewSNoticeInformed/{id}")
    public String viewSNoticeInformed(@PathVariable String id) {
        model.addAttribute("noticeInformed", noticeInformedBusin.getNoticeInformed(id));
        return "/noticeinformed/noticeinformed/viewSNoticeInformed";
    }
    
    /**
     * 查询公告通知列表,只查看对所有人公开，和平台公开的公告
     */
    @FunAuth("noticeinformed_listMyNoticeInformed")
    @RequestMapping("/listMyNoticeInformed")
    public String listMyNoticeInformed(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.noticeTitle, a.noticeType, a.noticeState, b.userName, a.updateDate from NoticeInformed a join a.userAccount b");
        pageBean.addQueryStr("a.noticeTitle,b.userName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/noticeinformed/noticeinformed/listMyNoticeInformed";
    }
    
    /**
     * 编辑信息
     * 
     * @return editNoticeInformed
     */
    @FunAuth("noticeinformed_editNoticeInformed")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editNoticeInformed/{id}")
    public String editNoticeInformed(@PathVariable String id) {
        model.addAttribute("noticeInformed", noticeInformedBusin.getNoticeInformed(id));
        return "/noticeinformed/noticeinformed/editNoticeInformed";
    }
    
    /**
     * 保存信息
     * 
     * @return saveNoticeInformed
     */
    @FunAuth("noticeinformed_editNoticeInformed")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveNoticeInformed")
    public String saveNoticeInformed(NoticeInformed noticeInformed, String[] noticeinformedFiles) {
        noticeInformed.setUserAccount(this.getCurrUser());
        businApi.save(noticeInformed);
        frameworkBusin.saveUploadFileOwner(noticeInformed, noticeinformedFiles);
        return "redirect:/noticeinformed/noticeinformed/viewNoticeInformed/" + noticeInformed.getId();
    }
    
    /**
     * 显示信息
     * 
     * @return viewNoticeInformed
     */
    @FunAuth("noticeinformed_editNoticeInformed")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/viewNoticeInformed/{id}")
    public String viewNoticeInformed(@PathVariable String id) {
        model.addAttribute("noticeInformed", noticeInformedBusin.getNoticeInformed(id));
        return "/noticeinformed/noticeinformed/viewNoticeInformed";
    }
    
    /**
     * 删除信息
     */
    @FunAuth("noticeinformed_removeNoticeInformed")
    @RequestMapping("/removeNoticeInformed")
    @ResponseBody
    public void removeNoticeInformed(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    NoticeInformed noticeInformed = noticeInformedBusin.getNoticeInformed(id);
                    if (noticeInformed != null) {
                        businApi.remove(noticeInformed);

                        JSONObject jo = new JSONObject();
                        jo.put("id", id);
                        jsonArray.put(jo);
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

    /**
     * 发布信息
     */
    @FunAuth("noticeinformed_enableNoticeInformed")
    @RequestMapping("/enableNoticeInformed")
    @ResponseBody
    public void enableNoticeInformed(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    NoticeInformed noticeInformed = noticeInformedBusin.getNoticeInformed(id);
                    if (noticeInformed != null) {
                        noticeInformed.setNoticeState(true);
                        businApi.save(noticeInformed);

                        JSONObject jo = new JSONObject();
                        jo.put("id", id);
                        jsonArray.put(jo);
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
}
