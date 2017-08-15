package com.pm.consignation.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.consignation.busin.ConsignationQuoteBusin;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.sysconfig.bean.VocationalWorkEnum;

@Controller
@RequestMapping("/pm/consignationQuote")
public class ConsignationQuoteController extends OrganizeBaseController{
    
    @Autowired
    private ConsignationQuoteBusin consignationQuoteBusin;
    
    @RequestMapping(value ="/listBgConsignationQuote")
    public String listBgConsignationQuote(SearchBean searchBean){
        VocationalWorkEnum yw = VocationalWorkEnum.BG;
        String bgId = yw.getId();
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,b.flowName,a.site,a.purposePlace,a.originPlace,a.enterPort,a.export,a.price,a.tonnage,a.createDate,a.checkState  from SupplierConsignationQuote a ,FlowType b ");
        pageBean.addQuerySQL(" where a.supplierType=b.id and a.checkState!=-1 and a.type=?");
       pageBean.addParams(bgId);
        pageBean.addQueryStr("a.site,b.flowName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/consignationQuote/listBgConsignationQuote";
    }
    
    @RequestMapping(value ="/listWlConsignationQuote")
    public String listWlConsignationQuote(SearchBean searchBean){
        VocationalWorkEnum yw = VocationalWorkEnum.WL;
        String wlId = yw.getId();
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,b.flowName,a.site,a.purposePlace,a.originPlace,a.enterPort,a.export,a.price,a.tonnage,a.createDate,a.checkState  from SupplierConsignationQuote a ,FlowType b ");
        pageBean.addQuerySQL(" where a.supplierType=b.id and a.checkState!=-1 and a.type=?");
       pageBean.addParams(wlId);
        pageBean.addQueryStr("a.originPlace,a.purposePlace,b.flowName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/consignationQuote/listWlConsignationQuote";
    }
    
    @RequestMapping(value ="/listCcConsignationQuote")
    public String listCcConsignationQuote(SearchBean searchBean){
        VocationalWorkEnum yw = VocationalWorkEnum.CC;
        String ccId = yw.getId();
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,b.flowName,a.site,a.purposePlace,a.originPlace,a.enterPort,a.export,a.price,a.tonnage,a.createDate,a.checkState  from SupplierConsignationQuote a ,FlowType b ");
        pageBean.addQuerySQL(" where a.supplierType=b.id and a.checkState!=-1 and a.type=?");
       pageBean.addParams(ccId);
        pageBean.addQueryStr("a.site,b.flowName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/consignationQuote/listCcConsignationQuote";
    }
    
    @RequestMapping(value ="/listWmConsignationQuote")
    public String listWmConsignationQuote(SearchBean searchBean){
        VocationalWorkEnum yw = VocationalWorkEnum.WM;
        String wmId = yw.getId();
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,b.flowName,a.site,a.purposePlace,a.originPlace,a.enterPort,a.export,a.price,a.tonnage,a.createDate,a.checkState  from SupplierConsignationQuote a ,FlowType b ");
        pageBean.addQuerySQL(" where a.supplierType=b.id and a.checkState!=-1 and a.type=?");
       pageBean.addParams(wmId);
        pageBean.addQueryStr("a.originPlace,a.purposePlace,b.flowName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/consignationQuote/listWmConsignationQuote";
    }
   
    /**
     * 删除委托报价信息
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @RequestMapping("/saveSupplierConsignationQuote")
    @ResponseBody
    public void saveSupplierConsignationQuote(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    consignationQuoteBusin.saveSupplierConsignationQuote(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
}
