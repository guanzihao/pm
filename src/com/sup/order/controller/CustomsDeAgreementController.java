package com.sup.order.controller;

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
import com.sup.order.bean.CustomsDeAgreement;
import com.sup.order.bean.OrderFrom;
import com.sup.order.busin.CustomsDeAgreementBusin;



/**
 * 委托报关协议（报关进口）
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/sup/order/CustomsDeAgreementimcc")
public class CustomsDeAgreementController extends SupBaseController {
    
    @Autowired
    private TaskBusin taskBusin;
    
    @Autowired
    private CustomsDeAgreementBusin customsDeAgreementBusin;

    @RequestMapping("/viewCustomsDeAgreement/{orderid}/{nodeType}/{flowId}/{isCompany}")
    public String listImportContract(SearchBean searchBean,@PathVariable String orderid,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany){
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
        String usertype="";
        if(this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
            
        }else if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }
        model.addAttribute("usertype", usertype);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("order_code", orderFrom);
        TreeModel treeModel = taskBusin.getOrderTreeModel(orderid);
        CustomsDeAgreement bgic = customsDeAgreementBusin.getCObject(orderid);
        model.addAttribute("bgic", bgic);
        model.addAttribute("treeData",JSONObject.fromObject(treeModel));
        return "/sup/order/bgimcc/listCustomsDeAgreement";
    }

    /**
     * 显示报关进口订单单据详情
     * @return
     */
    @RequestMapping("/listBgFlowImccOrderDesc/{id}/{hierarchy}/{taskTypeId}")
    public String listBgFlowImccOrderDetail(@PathVariable String id,@PathVariable String hierarchy,@PathVariable String taskTypeId){
        return taskBusin.OrderDispose(id,hierarchy,taskTypeId);
    }    
    

   /**
    * 修改操作
    * @param id
    * @return
    */
    @RequestMapping("/editCustomsDeAgreement/{id}/{nodeType}/{flowId}/{isCompany}")
    public String editCustomsDeAgreement(@PathVariable String id,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany
            
            ) {
        CustomsDeAgreement bgic = customsDeAgreementBusin.getCObject(id);
        SupCompanyInfo sci = getCurrSup().getSupCompanyInfo();
        if (sci!=null) {
            model.addAttribute("supCompanyInFo", "1");
        }else {
            model.addAttribute("supCompanyInFo", "0");
        }
        model.addAttribute("bgic", bgic);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
        return "/sup/order/customer/editCustomsDeAgreement";
    }
    
    @RequestMapping("/saveCustomsDeAgreement")
    @ResponseBody
    public AjaxDomain saveImportContract(CustomsDeAgreement customsDeAgreement,String taskTypeId
            ,String fileArray,String orderFromId
            ){
                AjaxDomain ajaxDomain=new AjaxDomain();
        try {
                String fileArrayArray []=null;
                if (fileArray!=null) {
                    fileArrayArray = fileArray.split(",");
                }
                customsDeAgreementBusin.saveExportContract(customsDeAgreement,taskTypeId, getCurrSup().getCompanyInfo().getCombaoguan(),orderFromId);
                frameworkBusin.saveUploadFileOwner(customsDeAgreement,fileArrayArray);
                String taskId=customsDeAgreement.getTaskId();
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
                   map.put("orderFromId", customsDeAgreement.getId());
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
     * 修改是保存数据
     * @param customsDeAgreement
     * @param taskTypeId
     * @param fileArray
     * @return
     */
    @RequestMapping("/saveObject/{nodeType}/{flowId}/{isCompany}")
    public String saveObject(CustomsDeAgreement customsDeAgreement,String fileArray[],@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany
            ){
        customsDeAgreementBusin.saveObject(customsDeAgreement);
        frameworkBusin.saveUploadFileOwner(customsDeAgreement,fileArray);
        return "redirect:/sup/order/CustomsDeAgreementimcc/viewCustomsDeAgreement/"+customsDeAgreement.getOrderFromId()+"/"+nodeType+"/"+flowId+"/"+isCompany;
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
