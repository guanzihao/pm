package com.pm.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.TreeModel;
import com.pm.task.bean.AjaxDomain;
import com.pm.task.bean.Task;
import com.pm.task.busin.TaskBusin;
import com.sup.company.controller.SupBaseController;
import com.sup.order.bean.ExportCustomsDeAgreement;
import com.sup.order.bean.OrderFrom;
import com.pm.order.busin.PmExportCustomsDeAgreementBusin;
import com.pm.organize.controller.OrganizeBaseController;

/**
 * 委托报关协议（报关出口）
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/pm/order/ExportCustomsDeAgreementexcc")
public class PmExportCustomsDeAgreementController extends OrganizeBaseController {

    @Autowired
    private PmExportCustomsDeAgreementBusin exportCustomsDeAgreementBusin;
    
    @Autowired
    private TaskBusin taskBusin;

    @RequestMapping("/viewExportCustomsDeAgreement/{orderid}")
    public String listImportContract(SearchBean searchBean,@PathVariable String orderid){
        OrderFrom orderFrom=(OrderFrom) businApi.get(OrderFrom.class, orderid);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="select c.obj_id,ci.com_Name,o.order_code,o.task_id,ci.obj_updateDate,c.consignor,c.goods_name,c.hs_code"
                + " ,c.weight,c.pack_detail,c.contract_no,c.license_file_no,c.goods_total_price,c.im_ex_date"
                + " ,c.delivery_number,c.trade_type,c.origin_place,c.others,c.consignee"
                + " ,c.customs_code,c.receive_docs_date,c.receive_docs_condition,c.docs_others,c.customs_charge"
                + " ,c.com_illustration,c.is_delegation,c.start_date,c.end_date,c.explain,c.obj_createDate "
                + " from CUSTOMS_EXPORTDECLARATION_AGREEMENT c ,PM_OrderFrom o,PM_COMPANY_CompanyInfo ci"
                + " where  c.order_from_id=o.obj_id"
                + " and c.user_id=ci.obj_id  and o.obj_id='"+orderid+"'";
        pageBean.addQuerySQL(sql);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        System.out.println( orderFrom.getOrderCode()+"sadasdas");
        model.addAttribute("order_code", orderFrom);
        TreeModel treeModel = taskBusin.getOrderTreeModel(orderid);
        model.addAttribute("treeData",JSONObject.fromObject(treeModel));
        return "/order/bgexcc/listCustomsDeAgreement";
    }
    
    @RequestMapping("/listBgFlowExccOrderDesc/{id}/{hierarchy}/{taskTypeId}")
    public String listBgFlowExccOrderDetail(@PathVariable String id,@PathVariable String hierarchy,@PathVariable String taskTypeId){
        return taskBusin.OrderDisposeByPm(id,hierarchy,taskTypeId);
    }
    
    @RequestMapping("/editExportCustomsDeAgreement/{id}")
    public String editImportContract(@PathVariable String id) {
        ExportCustomsDeAgreement exportCustomsDeAgreement = exportCustomsDeAgreementBusin.getCustomsDeAgreement(id);
        model.addAttribute("exportCustomsDeAgreement", exportCustomsDeAgreement);
        return "/bg/editCustomsDeAgreement";
    }
    
    
    @RequestMapping("/deleteExport")
    @ResponseBody
    public void deleteImportContract(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    exportCustomsDeAgreementBusin.deleteCustomsDeAgreement(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
}
