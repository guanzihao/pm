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
import com.pm.core.busin.BusinApi;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.TreeModel;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.task.bean.AjaxDomain;
import com.pm.task.bean.Task;
import com.pm.task.busin.TaskBusin;
import com.sup.company.controller.SupBaseController;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.ShippingExCommission;
import com.pm.order.busin.PmShippingExCommissionBusin;
import com.pm.order.busin.PmSupOrderFromBusin;


/**
 * 空海运委托单(物流进口)
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/pm/wuliuimcc")
public class PmShippingExCommissionController extends SupBaseController   {
    
    @Autowired
    private PmShippingExCommissionBusin shippingExCommissionBusin;
    
    @Autowired
    private BusinApi businApi;
    
    @Autowired
    private TaskBusin taskBusin;

    /**
     * 分页查询空海运出口委托书
     * @param searchBean
     * @return
     */
    @RequestMapping("/viewCustomsDeAgreement/{orderid}")
    public String listImportContract(SearchBean searchBean,@PathVariable String orderid){
        OrderFrom orderFrom=(OrderFrom) businApi.get(OrderFrom.class, orderid);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="select c.obj_id,ci.com_Name,o.order_code,o.task_id,ci.obj_updateDate,c.shipper,c.s_address"
                + " ,c.departure_port,c.discharge_port,c.destination_port,c.xhs_no,c.consignee,c.c_address"
                + " ,c.arrival_date,c.pay_way,c.container,c.blt,c.notifier,c.item"
                + " ,c.weight,c.volume,c.marks,c.goods_name,c.trans_expense_charge"
                + " ,c.trans_clause,c.contact_person"
                + " ,c.cp_phone,c.cp_fax,c.cp_mail,c.pre_flight,c.self_full,c.special_notes,c.signature,c.is_delegation,c.start_date,c.end_date ,c.consignor,c.explain,c.obj_createDate"
                + " from SHIPPING_EXPORT_COMMISSION c ,PM_OrderFrom o,PM_COMPANY_CompanyInfo ci"
                + " where c.order_from_id=o.obj_id"
                + " and c.user_id=ci.obj_id  and o.obj_id='"+orderid+"'";
        pageBean.addQuerySQL(sql);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        System.out.println( orderFrom.getOrderCode()+"sadasdas");
        model.addAttribute("order_code", orderFrom);
        TreeModel treeModel = taskBusin.getOrderTreeModel(orderid);
        model.addAttribute("treeData",JSONObject.fromObject(treeModel));
        return "/order/wlimcc/listCustomsDeAgreement";
    }
    
    /**
     * 跳转单据详情列表页面
     * @param id
     * @param hierarchy
     * @param taskTypeId
     * @return
     */
    @RequestMapping("/listWlImccOrderDesc/{id}/{hierarchy}/{taskTypeId}")
    public String listWlTransportOrderDetail(@PathVariable String id,@PathVariable String hierarchy,@PathVariable String taskTypeId){
        return taskBusin.OrderDisposeByPm(id,hierarchy,taskTypeId);
    }
    /**
     *保存空海运出口委托书
     * @param shippingExCommission
     * @return
     */
    @RequestMapping(value="/saveShippingExCommission")
    @ResponseBody
    public AjaxDomain saveShippingExCommission(ShippingExCommission shippingExCommission,String taskTypeId){
        AjaxDomain ajaxDomain=new AjaxDomain();
        try {
            shippingExCommissionBusin.saveShippingExCommission(shippingExCommission,taskTypeId, getCurrSup().getCompanyInfo().getComwaimao());
            String taskId=shippingExCommission.getTaskId();
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
     
    /**
     * 根据id得到一条空海运出口委托书信息并进行展示
     * @param id
     * @return
     */
    @RequestMapping(value = "/viewShippingExCommission/{id}")
    public String viewShippingExCommission(@PathVariable String id) {
        ShippingExCommission shippingExCommission =shippingExCommissionBusin.getShippingExCommission(id);
        model.addAttribute("shippingExCommission",shippingExCommission);
       return "/sup/sec/viewShippingExCommission";
    }
    
    /**
     * 根据id得到一条空海运出口委托书信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/editShippingExCommission/{id}")
    public String editShippingExCommission(@PathVariable String id) {
        ShippingExCommission shippingExCommission =shippingExCommissionBusin.getShippingExCommission(id);
        model.addAttribute("shippingExCommission",shippingExCommission);
       return "/sup/sec/editShippingExCommission";
    }
}
