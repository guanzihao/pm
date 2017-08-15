package com.pm.inter.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.StringUtil;
import com.pm.inter.bean.Interface;
import com.pm.inter.bean.InterfaceConfig;
import com.pm.inter.busin.InterfaceConfigBusin;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.sysconfig.bean.Enumitems;

@Controller
@RequestMapping("/inter/interConfig")
public class InterfaceConfigController extends OrganizeBaseController {
    
    @Autowired
    private InterfaceConfigBusin interConfigBusin;
    /**
     * 分页显示所有的接口配置信息
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findInterfaceConfigs")
    public String findInterfaceConfigs(SearchBean searchBean){
        PageBean pageBean =new PageBean(searchBean, businApi);
        String sql="select a.id,inter.intername,a.interfaceUrl,joinType.name,"
                  +"a.status,a.sort,a.content"
                  +" from InterfaceConfig a,"
                  +"Interface inter,"
                  +"Enumitems joinType"
                  +" where a.intefaceId=inter.id and a.joinType=joinType.id";
        pageBean.addQuerySQL(sql);
        pageBean.addQueryStr("inter.intername", searchBean.getSearchName1(), true);
        pageBean.addQueryStr("a.status", searchBean.getSearchName2(), true);
        pageBean.addOrderBy("a.sort", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/inter/interConfig/listInterConfig";
    }
    /**
     * 显示接口配置详细信息
     * @param id
     * @return
     */
    @RequestMapping(value="/detailInterfaceConfig/{id}")
    public String detailInterfaceConfig(@PathVariable String id){
        model.addAttribute("interConfig", interConfigBusin.getInterfaceConfigModel(id));
        return "/inter/interConfig/detailInterfaceConfig";
    }
    /**
     * 保存接口配置信息
     * @param interConfig
     * @param enumtype
     * @param suppers
     * @param companys
     * @param interfaces
     * @param joinType
     * @return
     */
    @RequestMapping(value="/saveInterfaceConfig")
    public String saveInterfaceConfig(InterfaceConfig interConfig,String startTime,String endTime){
        
        try {
        if(!StringUtil.isEmpty(interConfig.getId())){
            InterfaceConfig editInterConfig=interConfigBusin.getInterfaceConfig(interConfig.getId());
            editInterConfig.setAccountTypeId(interConfig.getAccountTypeId());
            editInterConfig.setSuppersId(interConfig.getCompanyId());
            editInterConfig.setCompanyId(interConfig.getCompanyId());
            editInterConfig.setIntefaceId(interConfig.getIntefaceId());
            editInterConfig.setJoinType(interConfig.getJoinType());
            editInterConfig.setCheckState(1);
            editInterConfig.setUpdateDate(new Date());
            editInterConfig.setStartDate(DateUtils.parseDate(startTime,"yyyy-MM-dd"));
            editInterConfig.setEndDate(DateUtils.parseDate(endTime,"yyyy-MM-dd"));
            editInterConfig.setContent(interConfig.getContent());
            editInterConfig.setSort(interConfig.getSort());
            editInterConfig.setStatus(interConfig.getStatus());
            editInterConfig.setInterfaceUrl(interConfig.getInterfaceUrl());
            interConfigBusin.saveInterfaceConfig(editInterConfig);
        }else{
            //添加操作
            
            interConfig.setCheckState(1);
            interConfig.setCreateDate(new Date());
            interConfig.setStartDate(DateUtils.parseDate(startTime,"yyyy-MM-dd"));
            interConfig.setEndDate(DateUtils.parseDate(endTime,"yyyy-MM-dd"));
            interConfigBusin.saveInterfaceConfig(interConfig);
        }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:/inter/interConfig/detailInterfaceConfig/"+interConfig.getId();
    }
    
    /**
     * 跳转至添加页面
     * @return
     */
    @RequestMapping(value = "/openAddInterfaceConfig")
    public String openAddInterfaceConfig(){
        List<Interface> interfaceList=interConfigBusin.findAllUseInterface();
        List<CompanyInfo> companyList=interConfigBusin.findAllUseCompanyInfo();
        List<SupCompanyInfo> supList=interConfigBusin.findAllUseSup();
        List<Enumitems> accountTypeList=interConfigBusin.findAllUseEnumitems("c0a384ee-8354-4490-afa0-88f6556e6eed");
        List<Enumitems> enumitemList=interConfigBusin.findAllUseEnumitems("4252bd30-f2b3-4e44-a1b3-d2cf208769e4");
        model.addAttribute("interfaces", interfaceList);
        model.addAttribute("companys", companyList);
        model.addAttribute("sups", supList);
        model.addAttribute("enumitems", enumitemList);
        model.addAttribute("accountType", accountTypeList);
        return "/inter/interConfig/saveInterConfig";
    }
    /**
     * 删除接口配置
     * @param ids
     */
    @RequestMapping(value = "/deleteInterfaceConfig")
    public void deleteInterface(String[] ids){
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    interConfigBusin.deleteIntefaceConfig(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
        
    }
    /**
     * 跳转至编辑接口配置页面
     * @param id
     * @return
     */
    @RequestMapping(value="/editInterfaceConfig/{id}")
    public String getEditInterfaceConfig(@PathVariable String id){
        model.addAttribute("editInterConfig", interConfigBusin.getInterfaceConfig(id));
        List<Interface> interfaceList=interConfigBusin.findAllUseInterface();
        List<CompanyInfo> companyList=interConfigBusin.findAllUseCompanyInfo();
        List<SupCompanyInfo> supList=interConfigBusin.findAllUseSup();
        List<Enumitems> accountTypeList=interConfigBusin.findAllUseEnumitems("c0a384ee-8354-4490-afa0-88f6556e6eed");
        List<Enumitems> enumitemList=interConfigBusin.findAllUseEnumitems("4252bd30-f2b3-4e44-a1b3-d2cf208769e4");
        model.addAttribute("interfaces", interfaceList);
        model.addAttribute("companys", companyList);
        model.addAttribute("sups", supList);
        model.addAttribute("enumitems", enumitemList);
        model.addAttribute("accountType", accountTypeList);
        return "/inter/interConfig/saveInterConfig";
        
    }
}
