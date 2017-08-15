package com.sup.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.pm.task.bean.AjaxDomain;
import com.pm.task.bean.Task;
import com.pm.task.busin.TaskBusin;
import com.sup.company.controller.SupBaseController;
import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.ExportPurchaseOrderProductionDetail;
import com.sup.order.bean.OrderFrom;
import com.sup.order.busin.SupExportOrderFromBusin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 订单进口（仓储出口）
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/sup/order/ExportOrderFrom")
public class SupExportOrderFromController extends SupBaseController{
    
    @Autowired
    private SupExportOrderFromBusin supExportOrderFromBusin;
    
    @Autowired
    private TaskBusin taskBusin;
    
    @RequestMapping("/saveExportOrderFrom")
    @ResponseBody
    public AjaxDomain saveOrderFrom(ExportPurchaseOrderProduction exportPurchaseOrderProduction,String taskTypeId,
            String detailNos,String descriptions,String units,String priceVilidityPeriods
            ,String perPrices,String remarks,String fileArray,String orderFromId
            ){
        AjaxDomain ajaxDomain=new AjaxDomain();
        try {
            String fileArrayArray []=null;
            if (fileArray!=null) {
                fileArrayArray = fileArray.split(",");
            }
            supExportOrderFromBusin.saveOrderFroms(exportPurchaseOrderProduction,taskTypeId, getCurrSup().getCompanyInfo().getComcangchu(),
                    detailNos,descriptions,units,priceVilidityPeriods
                    , perPrices, remarks,orderFromId
                    );
            frameworkBusin.saveUploadFileOwner(exportPurchaseOrderProduction,fileArrayArray);
            String taskId=exportPurchaseOrderProduction.getTaskId();
            String taskType="";
            String oneTaskId="";
           if (!StringUtil.isEmpty(taskId)) {
               Task task=(Task) businApi.get(Task.class, taskId);
               if (task!=null) {
                   String type = task.getTackType();
                   String[] types = type.split(",");
                   for (String str : types) {
                       if ("".equals(oneTaskId)) {
                           oneTaskId=str;
                       }
                       
                       List list = businApi.getQueryList("FROM OrderFrom a where a.flowType.id=? and a.task.id=?", new Object[] { str, taskId });
                       if (list != null && list.size() == 0) {
                           if ("".equals(taskType)) {
                               taskType=str;
                           }
                       }
                   }
               }
           }
           Map<String, Object> map=new HashMap<String, Object>();
           if ("".equals(taskType)) {
               taskType="1";
               map.put("oneTaskId", oneTaskId);
           }
           map.put("isTaskType", taskType);
           if (orderFromId!=null&&!"".equals(orderFromId)) {
               map.put("orderFromId", orderFromId);
            
           }else{
               map.put("orderFromId", exportPurchaseOrderProduction.getId());
           }
           ajaxDomain.setMap(map);
            ajaxDomain.setStatusCode("200");
        } catch (Exception e) {
            ajaxDomain.setStatusCode("400");
           
            e.printStackTrace();
        }
        return ajaxDomain;
    }
    
    /**
     * 修改操作
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/editExportPurchaseOrderProduction/{id}/{nodeType}/{flowId}/{isCompany}")
    public String editExportPurchaseOrderProduction(@PathVariable String id,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany) {
        ExportPurchaseOrderProduction epop = supExportOrderFromBusin.getObject(id);
        if (epop!=null) {
            List<ExportPurchaseOrderProductionDetail> popd = businApi.getQueryList("FROM ExportPurchaseOrderProductionDetail a where a.detailNo=?", new Object[]{epop.getId()});
            JSONArray jsonArray = JSONArray.fromObject(popd);
            model.addAttribute("epopd", jsonArray.toString());
       }
        SupCompanyInfo sci = getCurrSup().getSupCompanyInfo();
        if (sci!=null) {
            model.addAttribute("supCompanyInFo", "1");
        }else {
            model.addAttribute("supCompanyInFo", "0");
        }
        model.addAttribute("epop", epop);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
        return "/sup/order/customer/editExportPurchaseOrderProduction";
    }
    
    /**
     * 修改是保存数据
     * @param customsDeAgreement
     * @param taskTypeId
     * @param fileArray
     * @return
     */
    @RequestMapping("/saveObject/{nodeType}/{flowId}/{isCompany}")
    public String saveObject(ExportPurchaseOrderProduction  exportShippingExCommission,String fileArray[],@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany
            ,String ids[],String description[],String unit[],String priceVilidityPeriod[],
            String perPrices[],String remark[]
            ){
        supExportOrderFromBusin.saveObject(exportShippingExCommission);
        frameworkBusin.saveUploadFileOwner(exportShippingExCommission,fileArray);
        supExportOrderFromBusin.saveObjectDetail(exportShippingExCommission.getId(),ids, description, unit,priceVilidityPeriod,perPrices, remark);
        return "redirect:/sup/order/ExportOrderFrom/viewCustomsDeAgreement/"+exportShippingExCommission.getOrderFromId()+"/"+nodeType+"/"+flowId+"/"+isCompany;
    }

    @RequestMapping("/viewCustomsDeAgreement/{orderid}/{nodeType}/{flowId}/{isCompany}")
    public String listImportContract(SearchBean searchBean,@PathVariable String orderid,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany){
        OrderFrom orderFrom=(OrderFrom) businApi.get(OrderFrom.class, orderid);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="select c.obj_id,ci.com_Name,o.order_code,o.task_id,ci.obj_updateDate,c.pur_order_no,c.buyer"
                + " ,c.total_amount,c.issue_date,c.project,c.b_telphone,c.b_fax,c.supplier"
                + " ,c.supplier_no,c.contact_person,c.cp_address,c.cp_telephone,c.cp_fax,c.contract_term"
                + " ,c.delivery_date,c.instr_destination,c.payment_term,c.trade_condition,c.ref_ocuments"
                + " ,c.pcl"
                + " ,c.currency,c.tooling_cost,c.pay_way,c.tooling_life,c.share_amount,c.share_per_price,c.comments,c.is_delegation,c.consignor,c.start_date,c.end_date,c.explain,c.obj_createDate "
                + " from PURCHASE_EXPORTRYORDER_PRODUCTION c ,PM_OrderFrom o,PM_COMPANY_CompanyInfo ci"
                + " where c.order_from_id=o.obj_id"
                + " and c.user_id=ci.obj_id  and o.obj_id='"+orderid+"'";
        pageBean.addQuerySQL(sql);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("order_code", orderFrom);
        String usertype="";
        if(this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
            
        }else if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }
        model.addAttribute("usertype", usertype);
        ExportPurchaseOrderProduction exportPurchaseOrderProduction=(ExportPurchaseOrderProduction) businApi.getQueryObject("from ExportPurchaseOrderProduction where orderFromId = ? ", new Object[]{orderid});
        TreeModel treeModel = taskBusin.getOrderTreeModel(orderid);
        model.addAttribute("treeData",JSONObject.fromObject(treeModel));
        if(exportPurchaseOrderProduction!=null){
            List imContractDetail= businApi.getQueryList("select d.description,d.unit,d.priceVilidityPeriod,d.perPrice,d.remark from ExportPurchaseOrderProductionDetail d   where d.detailNo = ?", new Object[]{exportPurchaseOrderProduction.getId()});
            
            model.addAttribute("imContractDetail", imContractDetail);
            
        }
        return "/sup/order/ccexcc/listCustomsDeAgreement";
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
        return taskBusin.OrderDispose(id,hierarchy,taskTypeId);
    }
    
}
