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

import com.pm.cms.bean.HelpColumns;
import com.pm.cms.bean.HelpContent;
import com.pm.cms.busin.HelpContentBusin;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;

@Controller
@RequestMapping("/cms/Helpcontent")
public class HelpContentController extends OrganizeBaseController {

    @Autowired
    private HelpContentBusin helpContentBusin;

    /**
     * 分页查询所有的栏目信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCMSContent")
    public String findCMSContent(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);

        pageBean.addQuerySQL("SELECT  a.id,a.tile,a.content,a.sort,a.status,a.keyWord,b.name,a.createDate,a.updateDate  FROM HelpContent a inner join a.cmsColumns b  ");
        pageBean.addQueryStr("a.keyWord,b.name,a.tile", searchBean.getSearchName1(), true);
        pageBean.addOrderBy("a.sort", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/cms/Helpcontent/findCMSContent";
    }

    /**
     * 添加网站内容对数据进行初始化
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/initCMSContent")
    public String initCMSContent() {
        model.addAttribute("pageBean", businApi.getList(HelpColumns.class));
        return "/cms/Helpcontent/addCmsContent";
    }

    /**
     * 添网站内容
     * 
     * @return listOrgRole
     */
    @RequestMapping(value = "/addCmsContent")
    public String addCmsContent(HelpContent helpContent, String columnID, @RequestParam(name = "imgFile") MultipartFile imgFile, @RequestParam(name = "bigImgFile") MultipartFile bigImgFile, HttpServletRequest request, HttpServletResponse response

    ) {
        helpContentBusin.saveCMSContent(helpContent, columnID, imgFile, bigImgFile);
        return "redirect:/cms/Helpcontent/detailCmsContent/" + helpContent.getId();
    }

    /**
     * 修改网站内容
     * 
     * @return listOrgRole
     */
    @RequestMapping(value = "/updateCMSContent")
    public String updateCMSContent(HelpContent helpContent, String columnID, @RequestParam(name = "imgFile") MultipartFile imgFile, @RequestParam(name = "bigImgFile") MultipartFile bigImgFile) {
        
        helpContentBusin.updateCMSContent(helpContent, columnID, imgFile, bigImgFile);
        return "redirect:/cms/Helpcontent/detailCmsContent/" + helpContent.getId();
    }

    /**
     * 展示网站内容详情页面
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */

    @RequestMapping(value = "/detailCmsContent/{id}")
    public String detailCmsContent(@PathVariable String id) {
        model.addAttribute("cmsContent", helpContentBusin.getCMSContent(id));
        return "/cms/Helpcontent/detailCmsContent";
    }

    /**
     * 得到一个编号所对应的网站内容
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */

    @RequestMapping(value = "/editCmsContent/{id}")
    public String editCmsContent(@PathVariable String id) {
        model.addAttribute("pageBean", businApi.getList(HelpColumns.class));
        model.addAttribute("cmsContent", helpContentBusin.getCMSContent(id));
        return "/cms/Helpcontent/editCmsContent";
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
                    helpContentBusin.removeCMSContent(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

}
