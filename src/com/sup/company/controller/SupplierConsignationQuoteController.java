package com.sup.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.right.models.FunAuth;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.sysconfig.busin.EnumBusin;
import com.sup.company.bean.SupplierConsignationQuote;
import com.sup.company.busin.SupplierConsignationQuoteService;
import com.sup.flow.bean.FlowNode;
import com.sup.flow.bean.FlowType;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/sup/consignationQuote")
public class SupplierConsignationQuoteController extends SupBaseController {

    @Autowired
    private SupplierConsignationQuoteService supplierConsignationQuoteService;

    @Autowired
    private EnumBusin enumBusin;

    @RequestMapping(value = "/listSupplierConsignationQuote/{consignationQuote}")
    public String listSupplierConsignationQuote(SearchBean searchBean,@PathVariable String consignationQuote) {
        
        String type="";
        SupCompanyInfo supCompanyInfo = getCurrSup().getSupCompanyInfo();
        StringBuffer url = new StringBuffer();
        switch (consignationQuote) {
        case "1":
            VocationalWorkEnum bg = VocationalWorkEnum.BG;
            type=bg.getId();
            model.addAttribute("comType", bg.getId());
            url.append("/sup/consignationQuote/listBgConsignationQuote");
            break;
        case "2":
            VocationalWorkEnum wl = VocationalWorkEnum.WL;
            type=wl.getId();
            model.addAttribute("comType", wl.getId());
            url.append("/sup/consignationQuote/listWlConsignationQuote");        
            break;
        case "3":
            VocationalWorkEnum wm = VocationalWorkEnum.WM;
            type=wm.getId();
            model.addAttribute("comType", wm.getId());
            url.append("/sup/consignationQuote/listWmConsignationQuote");
            break;
        case "4":
            VocationalWorkEnum cc = VocationalWorkEnum.CC;
            type=cc.getId();
            model.addAttribute("comType", cc.getId());
            url.append("/sup/consignationQuote/listCcConsignationQuote");
            break;
        }
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id,b.flowName,a.site,a.purposePlace,a.originPlace,a.enterPort,a.export,a.price,a.tonnage,a.createDate,a.checkState  from SupplierConsignationQuote a ,FlowType b ");
        pageBean.addQuerySQL(" where a.supplierType=b.id and a.supplierId=? and a.type=?");
        pageBean.addParams(supCompanyInfo.getId());
        pageBean.addParams(type);
        pageBean.addQueryStr("b.flowName,a.site,a.originPlace,a.purposePlace", searchBean.getSearchName1(), true);
        pageBean.addOrderBy("a.createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("consignationQuote", consignationQuote);
        return url.toString();
    }

    /**
     * 保存服务商委托报价
     * 
     * @return
     */
    @RequestMapping(value = "/saveSupplierConsignationQuote")
    public String saveSupplierConsignationQuote(SupplierConsignationQuote consignationQuote) {
        SupCompanyInfo supCompanyInfo = getCurrSup().getSupCompanyInfo();
        consignationQuote.setSupplierId(supCompanyInfo.getId());
        consignationQuote.setCheckState(-1);
        supplierConsignationQuoteService.saveSupplierConsignationQuote(consignationQuote);
        return "redirect:/sup/consignationQuote/viewSupplierConsignationQuote/" + consignationQuote.getId()+"/"+consignationQuote.getType();
    }
    
    /**
     * 保存服务商委托报价
     * 
     * @return
     */
    @RequestMapping(value = "/saveSubmitConsignationQuote/{id}")
    public String saveSubmitConsignationQuote(@PathVariable String id) {
        SupCompanyInfo supCompanyInfo = getCurrSup().getSupCompanyInfo();
        SupplierConsignationQuote consignationQuote = supplierConsignationQuoteService.getSupplierConsignationQuote(id);
        if (consignationQuote!=null) {
            consignationQuote.setCheckState(0);
            consignationQuote.setSupplierId(supCompanyInfo.getId());
        }
        supplierConsignationQuoteService.saveSupplierConsignationQuote(consignationQuote);
        return "redirect:/sup/consignationQuote/viewSupplierConsignationQuote/" + consignationQuote.getId()+"/"+consignationQuote.getType();
    }
    
    /**
     * 显示服务商委托报价
     * 
     * @return
     */
    @RequestMapping(value = "/viewSupplierConsignationQuote/{id}/{comType}")
    public String viewSupplierConsignationQuote(@PathVariable String id,@PathVariable String comType) {
        SupplierConsignationQuote consignationQuote = supplierConsignationQuoteService.getSupplierConsignationQuote(id);
        model.addAttribute("consignationQuote", consignationQuote);
        StringBuffer url = new StringBuffer();
        SupCompanyInfo supCompanyInfo = getCurrSup().getSupCompanyInfo();
            VocationalWorkEnum bg = VocationalWorkEnum.BG;
            VocationalWorkEnum cc = VocationalWorkEnum.CC;
            VocationalWorkEnum wl = VocationalWorkEnum.WL;
            VocationalWorkEnum wm = VocationalWorkEnum.WM;
            if (bg.getId().equals(comType)) {
                url.append("/sup/consignationQuote/viewBgConsignationQuote");
            } else if (cc.getId().equals(comType)) {
                url.append("/sup/consignationQuote/viewCcConsignationQuote");
            } else if (wl.getId().equals(comType)) {
                url.append("/sup/consignationQuote/viewWlConsignationQuote");
            } else if (wm.getId().equals(comType)) {
                url.append("/sup/consignationQuote/viewWmConsignationQuote");
            }
        return url.toString();
    }

    /**
     * 显示服务商委托报价
     * 
     * @return
     */
    @RequestMapping(value = "/editSupplierConsignationQuote/{id}/{comType}")
    public String editSupplierConsignationQuote(@PathVariable String id,@PathVariable String comType) {
        SupplierConsignationQuote consignationQuote = supplierConsignationQuoteService.getSupplierConsignationQuote(id);

        StringBuffer url = new StringBuffer();
        SupCompanyInfo supCompanyInfo = getCurrSup().getSupCompanyInfo();
        List<FlowType> flowTypes = new ArrayList<FlowType>();
            VocationalWorkEnum bg = VocationalWorkEnum.BG;
            VocationalWorkEnum cc = VocationalWorkEnum.CC;
            VocationalWorkEnum wl = VocationalWorkEnum.WL;
            VocationalWorkEnum wm = VocationalWorkEnum.WM;
            if (bg.getId().equals(comType)) {
                VocationalWorkEnum bggq = VocationalWorkEnum.BGGQ;
                List<Enumitems> bggqList = enumBusin.getEnumTypeIdByEnumitemList(bggq.getId());
                model.addAttribute("bggqList", bggqList);
                flowTypes = supplierConsignationQuoteService.getFlowNote(bg.getId());
                url.append("/sup/consignationQuote/editBgConsignationQuote");
            } else if (cc.getId().equals(comType)) {
                flowTypes = supplierConsignationQuoteService.getFlowNote(cc.getId());
                url.append("/sup/consignationQuote/editCcConsignationQuote");
            } else if (wl.getId().equals(comType)) {
                flowTypes = supplierConsignationQuoteService.getFlowNote(wl.getId());
                url.append("/sup/consignationQuote/editWlConsignationQuote");
            } else if (wm.getId().equals(comType)) {
                flowTypes = supplierConsignationQuoteService.getFlowNote(wm.getId());
                url.append("/sup/consignationQuote/editWmConsignationQuote");
            }
        model.addAttribute("flowTypes", flowTypes);
        model.addAttribute("type", comType);
        model.addAttribute("consignationQuote", consignationQuote);
        return url.toString();
    }

    /**
     * 删除委托报价信息
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @RequestMapping("/removeSupplierConsignationQuote")
    @ResponseBody
    public void removeSupplierConsignationQuote(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    supplierConsignationQuoteService.removeSupplierConsignationQuote(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
}
