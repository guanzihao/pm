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
import com.pm.cms.busin.CMSColumnsBusin;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.right.models.FunAuth;

@Controller
@RequestMapping("/cms/columns")
public class CMSColumnsController extends OrganizeBaseController {

    @Autowired
    private CMSColumnsBusin cmsColumnsBusin;

    /**
     * 分页查询所有的栏目信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCMSColumns")
    public String findCMSColumns(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);

        pageBean.addQuerySQL("SELECT a.id,a.name,a.desc,a.columnSort,a.createDate,a.updateDate,a.columnStatus,a.img  FROM CMSColumns a");
        
        pageBean.addQueryStr("a.name", searchBean.getSearchName1(), true);
        pageBean.addOrderBy("a.columnSort", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/cms/columns/findCMSColumns";
    }
    
    /**
     * 添加栏目信息
     * 
     * @return listOrgRole
     */
    @RequestMapping(value="/addCmsColumns")
    public String addCmsColumns(CMSColumns cmsColumns,
            @RequestParam(name="files") MultipartFile multipartFile,
            HttpServletRequest request,HttpServletResponse response
            ) {
        
        cmsColumnsBusin.saveCMSColumns(cmsColumns,multipartFile);
        return "redirect:/cms/columns/detailCmsColumns/"+cmsColumns.getId();
    }
    
    /**
     * 修改栏目信息
     * 
     * @return listOrgRole
     */
    @RequestMapping(value="/updateCMSColumns")
    public String updateCMSColumns(CMSColumns cmsColumns,
            @RequestParam(name="files") MultipartFile multipartFile
            ) {
        
        cmsColumnsBusin.updateCMSColumns(cmsColumns,multipartFile);
        return "redirect:/cms/columns/detailCmsColumns/"+cmsColumns.getId();
    }
    
    /**
     * 展示详情页面
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */
   
    @RequestMapping(value="/detailCmsColumns/{id}")
    public String detailCmsColumns(@PathVariable String id) {
        model.addAttribute("cmsColumns", cmsColumnsBusin.getCMSColumns(id));
        return "/cms/columns/detailCmsColumns";
    }
    
    /**
     * 得到一个编号所对应的栏目信息
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */
   
    @RequestMapping(value="/editCmsColumns/{id}")
    public String editCmsColumns(@PathVariable String id) {
        model.addAttribute("cmsColumns", cmsColumnsBusin.getCMSColumns(id));
        return "/cms/columns/editCmsColumns";
    }
    
    /**
     * 删除栏目信息
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @FunAuth("role_removeOrgRole")
    @RequestMapping("/removeCMSColumns")
    @ResponseBody
    public void removeOrgRole(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    cmsColumnsBusin.removeCMSColumns(id);
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
        return "/cms/columns/addCmsColumns";
    }
    
}
