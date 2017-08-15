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
import com.sup.order.bean.CustomsDeAgreement;
import com.sup.order.bean.OrderFrom;
import com.pm.order.busin.PmCustomsDeAgreementBusin;

/**
 * 委托报关协议（报关进口）
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/pm/order/CustomsDeAgreementimcc")
public class PmCustomsDeAgreementController extends SupBaseController {

    @Autowired
    private PmCustomsDeAgreementBusin customsDeAgreementBusin;
    
    @Autowired
    private TaskBusin taskBusin;
    
    @RequestMapping("/viewCustomsDeAgreement/{orderid}")
    public String listImportContract(SearchBean searchBean,@PathVariable String orderid){
        OrderFrom orderFrom=(OrderFrom) businApi.get(OrderFrom.class, orderid);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="select c.obj_id,ci.com_Name,o.order_code,o.task_id,ci.obj_updateDate,c.consignor,c.goods_name,c.hs_code"
                + " ,c.weight,c.pack_detail,c.contract_no,c.license_file_no,c.goods_total_price,c.im_ex_date"
                + " ,c.delivery_number,c.trade_type,c.origin_place,c.others,c.consignee"
                + " ,c.customs_code,c.receive_docs_date,c.receive_docs_condition,c.docs_others,c.customs_charge"
                + " ,c.com_illustration ,o.node_id,o.node_state,o.node_date,c.is_delegation,c.start_date,c.end_date,c.explain,c.obj_createDate"
                + " from CUSTOMS_DECLARATION_AGREEMENT c ,PM_OrderFrom o,PM_COMPANY_CompanyInfo ci"
                + " where c.order_from_id=o.obj_id"
                + " and c.user_id=ci.obj_id and o.obj_id='"+orderid+"'";
        pageBean.addQuerySQL(sql);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        System.out.println( orderFrom.getOrderCode()+"sadasdas");
        model.addAttribute("order_code", orderFrom);
        TreeModel treeModel = taskBusin.getOrderTreeModel(orderid);
        model.addAttribute("treeData",JSONObject.fromObject(treeModel));
        return "/order/bgimcc/listCustomsDeAgreement";
    }
    
    /**
     * 显示报关进口订单单据详情
     * @return
     */
    @RequestMapping("/listBgFlowImccOrderDesc/{id}/{hierarchy}/{taskTypeId}")
    public String listBgFlowImccOrderDetail(@PathVariable String id,@PathVariable String hierarchy,@PathVariable String taskTypeId){
        return taskBusin.OrderDisposeByPm(id,hierarchy,taskTypeId);
    }  
    
    
    @RequestMapping("/editCustomsDeAgreement/{id}")
    public String editImportContract(@PathVariable String id) {
        CustomsDeAgreement customsDeAgreement = customsDeAgreementBusin.getCustomsDeAgreement(id);
        model.addAttribute("customsDeAgreement", customsDeAgreement);
        return "/order/bg/editCustomsDeAgreement";
    }
    
    @RequestMapping("/saveCustomsDeAgreement")
    @ResponseBody
    public AjaxDomain saveImportContract(CustomsDeAgreement customsDeAgreement,String taskTypeId){
        AjaxDomain ajaxDomain=new AjaxDomain();
        try {
            customsDeAgreementBusin.saveExportContract(customsDeAgreement,taskTypeId, getCurrSup().getCompanyInfo().getCombaoguan());
            String taskId=customsDeAgreement.getTaskId();
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
    @RequestMapping("/delete")
    @ResponseBody
    public void deleteImportContract(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    customsDeAgreementBusin.deleteCustomsDeAgreement(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
}
