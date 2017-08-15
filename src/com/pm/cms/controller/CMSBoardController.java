package com.pm.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.cms.bean.CMSBoard;
import com.pm.cms.busin.CMSBoardBusin;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;

@Controller
@RequestMapping("/common/board")
public class CMSBoardController extends OrganizeBaseController {

    @Autowired
    private CMSBoardBusin cmsBoardBusin;

    /**
     * 分页查看留言信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCMSBoard")
    public String findCMSColumns(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);

        pageBean.addQuerySQL("SELECT  a.id,a.name,a.tel,a.company,a.email,a.stitle,a.content,a.isRead,a.readDate ,a.createDate,a.updateDate  FROM CMSBoard a");
        pageBean.addQueryStr("a.name,a.tel,a.company,a.email,a.stitle", searchBean.getSearchName1(), true);
        pageBean.addOrderBy("a.isRead,createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/cms/board/findCMSBoard";
    }

    /**
     * 添加栏目信息
     * 
     * @return listOrgRole
     */
    @RequestMapping(value = "/addCmsBoard")
    public String addCmsColumns(CMSBoard cmsBoard) {
        cmsBoardBusin.saveCMSBoard(cmsBoard);
        return "/internet/index/indexHomePage";
    }

    /**
     * 展示详情页面
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */

    @RequestMapping(value = "/detailCmsBoard/{id}")
    public String detailCmsColumns(@PathVariable String id) {
        cmsBoardBusin.updateCMSBoard(id);
        model.addAttribute("board", cmsBoardBusin.getCMSBoard(id));
        return "/cms/board/detailCmsBoard";
    }

}
