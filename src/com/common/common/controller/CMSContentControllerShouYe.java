package com.common.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pm.cms.bean.CMSColumns;
import com.pm.cms.bean.CMSContent;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.task.bean.AjaxDomain;

@Controller
@RequestMapping("/common/content")
public class CMSContentControllerShouYe extends OrganizeBaseController {

    /**
     * 分页查询所有的栏目信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCMSContents")
    public String findCMSContent(SearchBean searchBean)
        {
        searchBean.setPageSize(6);
        String lanmu="c294231b-5165-4895-a5dd-d28d5143c3c2";
           
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql=" select a.obj_id,a.tile,a.content,a.sort,a.Img,a.big_img,a.obj_updateDate,a.key_word from CMS_Content a where a.status=0 and a.column_ID='"+lanmu+"' ";
        pageBean.addQuerySQL(sql);
        pageBean.addQuerySQL(" order by a.sort desc");
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("zhengce", "1");
        return "/internet/index/lawsRegulations";
    }
    
    @RequestMapping(value = "/findCMSContentss/{zhengce}")
    public String findCMSContents(SearchBean searchBean,@PathVariable String zhengce)
        {
        searchBean.setPageSize(6);
        String lanmu=null;
        if(zhengce.equals("1")){
            lanmu="c294231b-5165-4895-a5dd-d28d5143c3c2";
        }else if(zhengce.equals("2")){
            lanmu="d9972fcb-d9ea-4737-940c-5cc6a5891017";
        }else{
            lanmu="f1546a79-cd01-48d8-a214-e73fc24c54b8";
        }
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql=" select a.obj_id,a.tile,a.content,a.sort,a.Img,a.big_img,a.obj_updateDate,a.key_word from CMS_Content a where a.status=0 and a.column_ID='"+lanmu+"' ";
        pageBean.addQuerySQL(sql);
        pageBean.addQuerySQL(" order by a.sort desc");
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("zhengce", zhengce);
        return "/internet/index/lawsRegulations";
    }

   
    /*
     * ajax请求数据
     * 
     */
    @RequestMapping(value = "/ajaxfindCMSContentss")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public void ajaxfindCMSContents( String zhengce) throws IOException
        {
            String lanmu=null;
            if(zhengce.equals("1")){
                lanmu="c294231b-5165-4895-a5dd-d28d5143c3c2";
            }else if(zhengce.equals("2")){
                lanmu="d9972fcb-d9ea-4737-940c-5cc6a5891017";
            }else{
                lanmu="f1546a79-cd01-48d8-a214-e73fc24c54b8";
            }
            List<CMSContent> list = businApi.getQueryList("From CMSContent a where a.status=0 and a.cmsColumns.id=? order by sort desc ", new Object[]{lanmu});
            JSONArray arrays=new JSONArray();
            for (CMSContent cmsContent : list) {
                JSONObject obj=new JSONObject();
                obj.put("Tile", cmsContent.getTile());
                obj.put("getContent", cmsContent.getContent());
                obj.put("CreateDate", cmsContent.getCreateDate());
                obj.put("Sort", cmsContent.getSort());
                obj.put("Id", cmsContent.getId());
                obj.put("Img", cmsContent.getImg());
                obj.put("KeyWord", cmsContent.getKeyWord());
                arrays.put(obj);
            }
           
            writer(arrays.toString());
            
    }
    
    /*
     * ajax请求数据
     * 
     */
    @RequestMapping(value = "/ajaxfindCMSColumns")
    @ResponseBody
    public void ajaxfindCMSColumns( String zhengce) throws IOException
        {
            String lanmu=null;
            if(zhengce.equals("1")){
                lanmu="c294231b-5165-4895-a5dd-d28d5143c3c2";
            }else if(zhengce.equals("2")){
                lanmu="d9972fcb-d9ea-4737-940c-5cc6a5891017";
            }else{
                lanmu="f1546a79-cd01-48d8-a214-e73fc24c54b8";
            }
            
            CMSColumns cmsColumns=(CMSColumns)businApi.get(CMSColumns.class, lanmu);
            JSONArray arrays=new JSONArray();
            writer(arrays.put(cmsColumns.getImg()).toString());
    }
    /*
     * 进入政策法规。。。。。详情页面
     * 
     */
    @RequestMapping(value = "/findCMSContentsdetail/{id}")
    public String findCMSContentsdetail(@PathVariable String id)
        {
        CMSContent cMSContent=(CMSContent)businApi.get(CMSContent.class,id);
        model.addAttribute("cMSContent", cMSContent);
        return "/internet/userconfig/newsDetails";
    }
    
}
