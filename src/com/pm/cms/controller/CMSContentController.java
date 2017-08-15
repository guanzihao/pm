package com.pm.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pm.cms.bean.CMSColumns;
import com.pm.cms.bean.CMSContent;
import com.pm.cms.busin.CMSContentBusin;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.util.IpUtil;
import com.pm.organize.controller.OrganizeBaseController;

@Controller
@RequestMapping("/cms/content")
public class CMSContentController extends OrganizeBaseController {

    @Autowired
    private CMSContentBusin cmsContentBusin;

    /**
     * 分页查询所有的栏目信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCMSContent")
    public String findCMSContent(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT  a.id,a.tile,a.content,a.sort,a.status,a.checkState,a.keyWord,b.name,a.createDate,a.updateDate  FROM CMSContent a inner join a.cmsColumns b");
        pageBean.addQueryStr("a.keyWord,b.name,a.tile", searchBean.getSearchName1(), true);
        pageBean.addOrderBy("a.sort", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/cms/content/findCMSContent";
    }

    /**
     * 添加网站内容对数据进行初始化
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/initCMSContent")
    public String initCMSContent() {
        model.addAttribute("pageBean", businApi.getList(CMSColumns.class));
        return "/cms/content/addCmsContent";
    }

    /**
     * 添网站内容
     * 
     * @return listOrgRole
     */
    @RequestMapping(value = "/addCmsContent")
    public String addCmsContent(CMSContent cmsContent, String columnID, @RequestParam(name = "imgFile") MultipartFile imgFile, @RequestParam(name = "bigImgFile") MultipartFile bigImgFile, HttpServletRequest request, HttpServletResponse response

    ) {
        cmsContentBusin.saveCMSContent(cmsContent, columnID, imgFile, bigImgFile);
        return "redirect:/cms/content/detailCmsContent/" + cmsContent.getId();
    }

    /**
     * 修改网站内容
     * 
     * @return listOrgRole
     */
    @RequestMapping(value = "/updateCMSContent")
    public String updateCMSContent(CMSContent cmsContent, String columnID, @RequestParam(name = "imgFile") MultipartFile imgFile, @RequestParam(name = "bigImgFile") MultipartFile bigImgFile) {
        
        cmsContentBusin.updateCMSContent(cmsContent, columnID, imgFile, bigImgFile);
        return "redirect:/cms/content/detailCmsContent/" + cmsContent.getId();
    }

    /**
     * 展示网站内容详情页面
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */

    @RequestMapping(value = "/detailCmsContent/{id}")
    public String detailCmsContent(@PathVariable String id) {
        model.addAttribute("cmsContent", cmsContentBusin.getCMSContent(id));
        return "/cms/content/detailCmsContent";
    }

    /**
     * 得到一个编号所对应的网站内容
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */

    @RequestMapping(value = "/editCmsContent/{id}")
    public String editCmsContent(@PathVariable String id) {
        model.addAttribute("pageBean", businApi.getList(CMSColumns.class));
        model.addAttribute("cmsContent", cmsContentBusin.getCMSContent(id));
        return "/cms/content/editCmsContent";
    }

    /**
     * 删除网站内容
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @RequestMapping("/removeCMSContent")
    @ResponseBody
    public void removeOrgRole(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    cmsContentBusin.removeCMSContent(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

}
