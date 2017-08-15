package com.pm.inter.controller;

import java.util.Date;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.Contexts;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.inter.bean.Interface;
import com.pm.inter.busin.InterfaceBusin;
import com.pm.organize.controller.OrganizeBaseController;

@Controller
@RequestMapping("/inter/interColumn")
public class InterfaceController extends OrganizeBaseController {
    
    @Autowired
    private InterfaceBusin interBusin;
    /**
     * 分页查询所有的接口信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findInterfaceColumns")
    public String findInterfaces(SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT a.id,a.intername,a.intercontent,a.intersort,a.interstatus,a.createDate  FROM Interface a");
        pageBean.addQueryStr("a.intername",   searchBean.getSearchName1(), true);
        pageBean.addQueryStr("a.interstatus", searchBean.getSearchName2(), true);
        pageBean.addOrderBy("a.intersort", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/inter/interColumn/listInterface";
    }
    /**
     * 添加接口信息
     * 
     * @param inter
     * @return
     */
    @RequestMapping(value = "/saveInterfaceColumns")
    public String saveInterfaces(Interface inter){
        Interface editInter=interBusin.getInterfaceColumn(inter.getId());
        if(editInter!=null){
            //修改操作
            editInter.setUpdateDate(new Date());
            editInter.setInterstatus(inter.getInterstatus());
            editInter.setIntersort(inter.getIntersort());
            editInter.setIntername(inter.getIntername());
            editInter.setIntercontent(inter.getIntercontent());
            interBusin.saveInterfaceColumns(editInter);
        }else{
            inter.setCreateDate(new Date());
            inter.setIntercheckState(Contexts.W);
            interBusin.saveInterfaceColumns(inter);
        }
        return "redirect:/inter/interColumn/detailInterface/"+inter.getId();
    }
    /**
     * 添加跳转页面
     * @return
     */
    @RequestMapping(value = "/showOpenInterface")
    public String interColumns(){
        return "/inter/interColumn/saveInterface";
    }
    /**
     * 删除接口定义
     * @param ids
     */
    @RequestMapping(value = "/deleteInterface")
    public void deleteInterface(String[] ids){
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    interBusin.deleteIntefaceColumns(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    /**
     * 编辑接口定义
     * @param id
     * @return
     */
    @RequestMapping(value="/editInterfaceColumns/{id}")
    public String editInterfaceColumns(@PathVariable String id) {
            model.addAttribute("interColumn", interBusin.getInterfaceColumn(id));
            return "/inter/interColumn/saveInterface";
        }
    /**
     * 删除接口信息定义
     * @param id
     * @return
     */
    @RequestMapping(value="/detailInterface/{id}")
    public String detailInterfaceColumns(@PathVariable String id) {
        model.addAttribute("interColumn", interBusin.getInterfaceColumn(id));
        return "/inter/interColumn/detailInterface";
    }
    
    
}
