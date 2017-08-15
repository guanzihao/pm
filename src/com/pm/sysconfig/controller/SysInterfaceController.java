package com.pm.sysconfig.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.right.models.FunAuth;
import com.pm.sysconfig.bean.Interface;
import com.pm.sysconfig.busin.SysInterfaceService;

public class SysInterfaceController extends OrganizeBaseController {

    @Autowired
    private SysInterfaceService interfaceService;

    /**
     * 分页查询所有的接口信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findInterface")
    public String findInterface(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        System.out.println("=================================================");
        pageBean.addQuerySQL("SELECT a.id,a.name,a.content,a.sort,a.checkstate,a.createDate  FROM Interface a");
        pageBean.addOrderBy("a.columnSort", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/sysconfig/interface/listInterface";
    }
    
    /**
     * 添加接口信息
     * 
     * @return listOrgRole
     */
    @RequestMapping(value="/addInterface")
    public String addInterface(Interface Interface,
            @RequestParam(name="files") MultipartFile multipartFile,
            HttpServletRequest request,HttpServletResponse response
            
            ) {
        interfaceService.saveInterface(Interface,multipartFile);
        return "redirect:/sysconfig/interface/viewInterface/"+Interface.getId();
    }
    
    /**
     * 修改接口信息
     * 
     * @return listOrgRole
     */
    @RequestMapping(value="/updateInterface")
    public String updateInterface(Interface Interface,
            @RequestParam(name="files") MultipartFile multipartFile
            ) {
        
        interfaceService.updateInterface(Interface,multipartFile);
        return "redirect:/sysconfig/interface/viewInterface/"+Interface.getId();
    }
    
    /**
     * 展示详情页面
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */
   
    @RequestMapping(value="/detailInterface/{id}")
    public String detailInterface(@PathVariable String id) {
        model.addAttribute("Interface", interfaceService.getInterface(id));
        return "/sysconfig/interface/viewInterface";
    }
    
    /**
     * 得到一个编号所对应的接口信息
     * 
     * @param roleId RoleId
     * @return editOrgRole
     */
   
    @RequestMapping(value="/editInterface/{id}")
    public String editInterface(@PathVariable String id) {
        model.addAttribute("Interface", interfaceService.getInterface(id));
        return "/sysconfig/interface/editInterface";
    }
    
    
    /**
     * 删除接口信息
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @FunAuth("role_removeOrgRole")
    @RequestMapping("/removeInterface")
    @ResponseBody
    public void removeOrgRole(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    interfaceService.removeInterface(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    
    

}
