package com.common.common.controller;

import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pm.cms.bean.CMSContent;
import com.pm.cms.bean.HelpContent;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;



/**
 * 首页栏目信息
 * 
 * @author 
 */

@Controller
@RequestMapping("/common/help")
public class HelpController extends OrganizeBaseController  {

    /**
     * 分页查询所有的栏目信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCMSContents")
    public String findCMSContent(SearchBean searchBean)
        {
           
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="select a.obj_id,a.Name,a.column_parent from Help_Columns a where  a.column_status =0";
        pageBean.addQuerySQL(sql);
        pageBean.addQuerySQL(" order by a.column_Sort desc");
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("zhengce", "1");
        return "/internet/index/helpCenter";
    }
    
    
    
    /**
     * 查询不同栏目的内容信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCMSConter")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public void findCMSConter(SearchBean searchBean,String id)
        {

        List<HelpContent> list = businApi.getQueryList("From HelpContent a where a.status=0 and a.cmsColumns.id=? order by sort desc ", new Object[]{id});
        JSONArray arrays=new JSONArray();
        for (HelpContent helpContent : list) {
            JSONObject obj=new JSONObject();
            obj.put("Tile", helpContent.getTile());
            obj.put("getContent", helpContent.getContent());
            obj.put("CreateDate", helpContent.getCreateDate());
            obj.put("Sort", helpContent.getSort());
            obj.put("Id", helpContent.getId());
            obj.put("Img", helpContent.getImg());
            obj.put("KeyWord", helpContent.getKeyWord());
            arrays.put(obj);
        }
        writer(arrays.toString());
    }
  
    
    /**
     * 查询4中不同类型的内容信息
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCMSConterType/{id}")
    public String findCMSConterType(SearchBean searchBean,@PathVariable String id)
        {
            PageBean pageBean = new PageBean(searchBean, businApi);
            String type="";
            switch (id) {
            case "1":
                type="21611225-9a9a-493d-bb84-12ebcfe84d4d";
                break;
            case "3":
                type="c07f738f-e265-46cf-924b-3eeada27610d";
                break;
            case "2":
                type="3c271f82-fb41-4ca5-8779-41f4ec43ae61";
                break;
            case "4":
                type="ee11009c-11e3-41cc-ac24-f71c6cc2acfb";
                break;
           
            }
            pageBean.addQuerySQL("select a.id, a.img, a.bigImg, a.tile, a.content,a.createDate"
                    + " ,a.sort,a.keyWord from CMSContent a where a.status=0 and a.cmsColumns.id=?");
            pageBean.addParams(type);
            pageBean.addOrderBy("a.sort", true);
            pageBean.query();
            model.addAttribute("pageBean", pageBean);
            switch (id) {
            case "1":
                return "/internet/server/wlServer";
            case "3":
                return "/internet/server/wmServer";
            case "2":
                return "/internet/server/ccServer";
            case "4":
                return "/internet/server/bgServer";
            }
            return "";
        }
      
    
    /**
     * 查询4中不同类型的内容信息mingxi
     * 
     * @return
     */
    @RequestMapping(value = "/findCMSConterTypeDetail/{id}")
    public String findCMSConterTypeDetail(@PathVariable String id){
        CMSContent cMSContent=(CMSContent)businApi.get(CMSContent.class, id);
        model.addAttribute("cMSContent", cMSContent);
        return "/internet/userconfig/newsDetails";
    }
    
    
    
    
 }
