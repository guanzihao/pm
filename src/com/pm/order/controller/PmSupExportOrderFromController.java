package com.pm.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.TreeModel;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.task.bean.AjaxDomain;
import com.pm.task.bean.Task;
import com.pm.task.busin.TaskBusin;
import com.sup.company.controller.SupBaseController;
import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.ImportContract;
import com.sup.order.bean.OrderFrom;
import com.pm.order.busin.PmSupExportOrderFromBusin;


/**
 * 订单进口（仓储出口）
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/pm/order/ExportOrderFrom")
public class PmSupExportOrderFromController extends SupBaseController{
    
    @Autowired
    private PmSupExportOrderFromBusin supExportOrderFromBusin;
    
    @Autowired
    private TaskBusin taskBusin;
  
    @RequestMapping("/saveExportOrderFrom")
    @ResponseBody
    public AjaxDomain saveOrderFrom(ExportPurchaseOrderProduction exportPurchaseOrderProduction,String taskTypeId ){
        AjaxDomain ajaxDomain=new AjaxDomain();
        try {
            supExportOrderFromBusin.saveOrderFroms(exportPurchaseOrderProduction,taskTypeId, getCurrSup().getCompanyInfo().getComwaimao());
            String taskId=exportPurchaseOrderProduction.getTaskId();
            String taskType="";
            
           if (!StringUtil.isEmpty(taskId)) {
               Task task=(Task) businApi.get(Task.class, taskId);
               if (task!=null) {
                   String type = task.getTackType();
                   String[] types = type.split(",");
                   for (String str : types) {
                       List list = businApi.getQueryList("FROM OrderFrom a where a.flowType.id=? and a.task.id=?", new Object[] { str, taskId });
                       if (list != null && list.size() == 0) {
                           if ("".equals(taskType)) {
                               taskType=str;
                           }
                       }
                   }
               }
           }
           if ("".equals(taskType)) {
               taskType="1";
           }
           
           Map<String, Object> map=new HashMap<String, Object>();
           map.put("isTaskType", taskType);
           ajaxDomain.setMap(map);
            ajaxDomain.setStatusCode("200");
        } catch (Exception e) {
            ajaxDomain.setStatusCode("400");
           
            e.printStackTrace();
        }
        return ajaxDomain;
    }
    
    

    @RequestMapping("/viewCustomsDeAgreement/{orderid}")
    public String listImportContract(SearchBean searchBean,@PathVariable String orderid){
        OrderFrom orderFrom=(OrderFrom) businApi.get(OrderFrom.class, orderid);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="select c.obj_id,ci.com_Name,o.order_code,o.task_id,ci.obj_updateDate,c.pur_order_no,c.buyer"
                + " ,c.total_amount,c.issue_date,c.project,c.b_telphone,c.b_fax,c.supplier"
                + " ,c.supplier_no,c.contact_person,c.cp_address,c.cp_telephone,c.cp_fax,c.contract_term"
                + " ,c.delivery_date,c.instr_destination,c.payment_term,c.trade_condition,c.ref_ocuments"
                + " ,c.pcl"
                + " ,c.currency,c.tooling_cost,c.pay_way,c.tooling_life,c.share_amount,c.share_per_price,c.comments,c.is_delegation,c.consignor,c.start_date,c.end_date ,c.explain,c.obj_createDate"
                + " from PURCHASE_EXPORTRYORDER_PRODUCTION c ,PM_OrderFrom o,PM_COMPANY_CompanyInfo ci"
                + " where c.order_from_id=o.obj_id"
                + " and c.user_id=ci.obj_id  and o.obj_id='"+orderid+"'";
        pageBean.addQuerySQL(sql);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        System.out.println( orderFrom.getOrderCode()+"sadasdas");
        model.addAttribute("order_code", orderFrom);
        ExportPurchaseOrderProduction exportPurchaseOrderProduction=(ExportPurchaseOrderProduction) businApi.getQueryObject("from ExportPurchaseOrderProduction where orderFromId = ? ", new Object[]{orderid});
        if(exportPurchaseOrderProduction!=null){
            List imContractDetail= businApi.getQueryList("select d.description,d.unit,d.priceVilidityPeriod,d.perPrice,d.remark from ExportPurchaseOrderProductionDetail d   where d.detailNo = ?", new Object[]{exportPurchaseOrderProduction.getId()});
            model.addAttribute("imContractDetail", imContractDetail);
        }
        TreeModel treeModel = taskBusin.getOrderTreeModel(orderid);
        model.addAttribute("treeData",JSONObject.fromObject(treeModel));
        return "/order/ccexcc/listCustomsDeAgreement";
    }
    
    /**
     * 跳转单据详情列表页面
     * @param id
     * @param hierarchy
     * @param taskTypeId
     * @return
     */
    @RequestMapping("/listCcFlowExccOrderDesc/{id}/{hierarchy}/{taskTypeId}")
    public String listCcFlowExccOrderDetail(@PathVariable String id,@PathVariable String hierarchy,@PathVariable String taskTypeId){
        return taskBusin.OrderDisposeByPm(id,hierarchy,taskTypeId);
    }
    
}
