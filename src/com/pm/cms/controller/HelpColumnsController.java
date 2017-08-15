package com.pm.cms.controller;


import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pm.cms.bean.CMSColumns;
import com.pm.cms.bean.HelpColumns;
import com.pm.cms.busin.HelpColumnsBusin;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;

@Controller
@RequestMapping("/cms/HelpColumns")
public class HelpColumnsController extends OrganizeBaseController {

    @Autowired
    private HelpColumnsBusin  helpColumns;

    /**
     * 分页查询所有的栏目信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCMSColumns")
    public String findCMSContent(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);

        pageBean.addQuerySQL("SELECT  a.id,a.name,a.columnparent,a.colnumDesc,a.columnSort,a.columnstatus,a.createDate,a.updateDate FROM HelpColumns a");
        pageBean.addQueryStr("a.Name", searchBean.getSearchName1(), true);
        pageBean.addOrderBy("a.columnSort", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/cms/HelpColumns/findCMSColumns";
    }


    /**
     * 添网站内容
     * 
     * @return listOrgRole
     */
    @RequestMapping(value = "/addCmsColumns")
    public String addCmsContent(HelpColumns HelpColumns,String columnID, @RequestParam(name = "files") MultipartFile imgFile){ 
        
        helpColumns.savehelpColumns(HelpColumns,columnID,imgFile);
        return "redirect:/cms/HelpColumns/detailCmsColumns/" + HelpColumns.getId();
    }

    /**
     * 修改网站内容
     * 
     * @return listOrgRole
     */
    @RequestMapping(value = "/updateCMSColumns")
    public String updateCMSContent(HelpColumns HelpColumns,String columnID, @RequestParam(name = "files") MultipartFile imgFile) {
        helpColumns.updatehelpColumns(HelpColumns,columnID,imgFile);
        return "redirect:/cms/HelpColumns/detailCmsColumns/" + HelpColumns.getId();
    }

    /**
     * 展示网站内容详情页面
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */

    @RequestMapping(value = "/detailCmsColumns/{id}")
    public String detailCmsContent(@PathVariable String id) {
        model.addAttribute("helpColumns", helpColumns.getHelpColumns(id));
        return "/cms/HelpColumns/detailCmsColumns";
    }

    /**
     * 得到一个编号所对应的网站内容
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */

    @RequestMapping(value = "/editCmsColumns/{id}")
    public String editCmsContent(@PathVariable String id) {
        model.addAttribute("pageBean", businApi.getList(HelpColumns.class));
        model.addAttribute("cmsContent", helpColumns.getHelpColumns(id));
        return "/cms/HelpColumns/editCmsColumns";
    }

    /**
     * 删除网站内容
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @RequestMapping("/removeCMSColumns")
    @ResponseBody
    public void removeOrgRole(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    helpColumns.removehelpColumns(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    
    /**
     * 添加跳转页面
     * @return
     */
    @RequestMapping(value="/cmsColumns")
    public String cmsColumns() {
        model.addAttribute("pageBean", businApi.getList(HelpColumns.class));
        return "/cms/HelpColumns/addCmsColumns";
    }
    
}
