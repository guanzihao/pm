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
import com.pm.core.busin.BusinApi;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.model.TaskState;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.TreeModel;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.sysconfig.busin.EnumBusin;
import com.pm.task.bean.AjaxDomain;
import com.pm.task.bean.Task;
import com.pm.task.busin.TaskBusin;
import com.sup.company.controller.SupBaseController;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.PurchaseOrderProduction;
import com.sup.order.bean.TropShippingExCommission;
import com.sup.order.busin.SupOrderFromBusin;
import com.sup.order.busin.TropShippingExCommissionBusin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 空海运委托单(物流运输)
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/sup/wuliutrop")
public class TropShippingExCommissionController extends SupBaseController   {
    
    @Autowired
    private TropShippingExCommissionBusin shippingExCommissionBusin;
    
    @Autowired
    private TaskBusin taskBusin;
    
    @Autowired
    private EnumBusin enumBusin;
    
    /**
     * 分页查询空海运出口委托书
     * @param searchBean
     * @return
     */
    @RequestMapping("/viewCustomsDeAgreement/{orderid}/{nodeType}/{flowId}/{isCompany}")
    public String listImportContract(SearchBean searchBean,@PathVariable String orderid,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany){
        OrderFrom orderFrom=(OrderFrom) businApi.get(OrderFrom.class, orderid);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="select c.obj_id,ci.com_Name,o.order_code,o.task_id,ci.obj_updateDate,c.shipper,c.s_address"
                + " ,c.departure_port,c.discharge_port,c.destination_port,c.xhs_no,c.consignee,c.c_address"
                + " ,c.arrival_date,c.pay_way,c.container,c.blt,c.notifier,c.item"
                + " ,c.weight,c.volume,c.marks,c.goods_name,c.trans_expense_charge"
                + " ,c.trans_clause,c.contact_person"
                + " ,c.cp_phone,c.cp_fax,c.cp_mail,c.pre_flight,c.self_full,c.special_notes,c.signature,c.is_delegation,c.start_date,c.end_date,c.consignor,c.explain,c.obj_createDate "
                + " from SHIPPING_Trop_COMMISSION c ,PM_OrderFrom o,PM_COMPANY_CompanyInfo ci"
                + " where  c.order_from_id=o.obj_id"
                + " and c.user_id=ci.obj_id  and o.obj_id='"+orderid+"'";
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
        model.addAttribute("treeData",JSONObject.fromObject(treeModel));
        return "/sup/order/wltrop/listCustomsDeAgreement";
    }
    
    /**
     * 跳转单据详情列表页面
     * @param id
     * @param hierarchy
     * @param taskTypeId
     * @return
     */
    @RequestMapping("/listWlTransportOrderDesc/{id}/{hierarchy}/{taskTypeId}")
    public String listWlTransportOrderDetail(@PathVariable String id,@PathVariable String hierarchy,@PathVariable String taskTypeId){
        return taskBusin.OrderDispose(id,hierarchy,taskTypeId);
    }
    
    /**
     * 修改是保存数据
     * @param customsDeAgreement
     * @param taskTypeId
     * @param fileArray
     * @return
     */
    @RequestMapping("/saveObject/{nodeType}/{flowId}/{isCompany}")
    public String saveObject(TropShippingExCommission shippingExCommission,String fileArray[],@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany
            ){
        shippingExCommissionBusin.saveObject(shippingExCommission);
        frameworkBusin.saveUploadFileOwner(shippingExCommission,fileArray);
        return "redirect:/sup/wuliutrop/viewCustomsDeAgreement/"+shippingExCommission.getOrderFromId()+"/"+nodeType+"/"+flowId+"/"+isCompany;
    }
    
    
    /**
     *保存空海运出口委托书
     * @param shippingExCommission
     * @return
     */
    @RequestMapping(value="/saveShippingExCommission")
    @ResponseBody
    public AjaxDomain saveShippingExCommission(TropShippingExCommission shippingExCommission,String taskTypeId
            ,String fileArray,String orderFromId
            ){
            AjaxDomain ajaxDomain=new AjaxDomain();
        try {
            String fileArrayArray []=null;
            if (fileArray!=null) {
                fileArrayArray = fileArray.split(",");
            }
            shippingExCommissionBusin.saveShippingExCommission(shippingExCommission,taskTypeId, getCurrSup().getCompanyInfo().getComwuliu(),orderFromId);
            frameworkBusin.saveUploadFileOwner(shippingExCommission,fileArrayArray);
            String taskId=shippingExCommission.getTaskId();
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
               map.put("orderFromId", shippingExCommission.getId());
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
     * 根据id得到一条空海运出口委托书信息并进行展示
     * @param id
     * @return
     */
    @RequestMapping(value = "/viewShippingExCommission/{id}/{nodeType}/{flowId}/{isCompany}")
    public String viewShippingExCommission(@PathVariable String id,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany) {
        TropShippingExCommission shippingExCommission =shippingExCommissionBusin.getShippingExCommission(id);
        model.addAttribute("shippingExCommission",shippingExCommission);
       return "/sup/sec/viewShippingExCommission";
    }
    
    /**
     * 根据id得到一条空海运出口委托书信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/editTropShippingExCommission/{id}/{nodeType}/{flowId}/{isCompany}")
    public String editTropShippingExCommission(@PathVariable String id,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany) {
        TropShippingExCommission tsc =shippingExCommissionBusin.getObject(id);
        VocationalWorkEnum wlzffs = VocationalWorkEnum.WLZFFS;
        VocationalWorkEnum wltdlx = VocationalWorkEnum.WLTDLX;
        List<Enumitems> wlzffsList = enumBusin.getEnumTypeIdByEnumitemList(wlzffs.getId());
        List<Enumitems> wltdlxList = enumBusin.getEnumTypeIdByEnumitemList(wltdlx.getId());
        SupCompanyInfo sci = getCurrSup().getSupCompanyInfo();
        if (sci!=null) {
            model.addAttribute("supCompanyInFo", "1");
        }else {
            model.addAttribute("supCompanyInFo", "0");
        }
        model.addAttribute("wlzffsList", wlzffsList);
        model.addAttribute("wltdlxList", wltdlxList);
        model.addAttribute("tsc",tsc);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
       return "/sup/order/customer/editTropShippingExCommission";
    }
}
