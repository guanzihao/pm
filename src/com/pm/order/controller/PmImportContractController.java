package com.pm.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sup.order.bean.ImContractDetail;
import com.sup.order.bean.ImportContract;
import com.sup.order.bean.OrderFrom;
import com.pm.order.busin.PmImportContractBusin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 进口合同业务
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/pm/order/ic")
public class PmImportContractController extends SupBaseController {

    @Autowired
    private PmImportContractBusin importContractBusin;
    
    @Autowired
    private TaskBusin taskBusin;


    /**
     * @Description 进口合同列表查询
     * @author Chu Zhaocheng
     * @date 2017年6月26日 下午6:50:21
     * @action listImportContract
     * @return String
     */
    @RequestMapping("/viewCustomsDeAgreement/{orderid}")
    public String listImportContract(SearchBean searchBean,@PathVariable String orderid){
        OrderFrom orderFrom=(OrderFrom) businApi.get(OrderFrom.class, orderid);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="select c.obj_id,ci.com_Name,o.order_code,o.task_id,ci.obj_updateDate,c.contract_date,c.contract_no"
                + " ,c.sellers,c.s_address,c.s_telphone,c.s_fax,c.buyers,c.b_address"
                + " ,c.b_telphone,c.b_fax,c.shipment_time,c.delivery_time,c.port_shipment,c.port_destination"
                + " ,c.packing,c.insurance,c.manufactory,c.term_payment,c.inspection"
                + " ,c.arbitration,c.others,c.is_delegation,c.start_date,c.end_date,c.explain,c.obj_createDate,c.consignor"
                + " from PM_IMPORT_CONTRACT c ,PM_OrderFrom o,PM_COMPANY_CompanyInfo ci"
                + " where c.order_from_id=o.obj_id"
                + " and c.user_id=ci.obj_id  and o.obj_id='"+orderid+"'  ";
        pageBean.addQuerySQL(sql);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        ImportContract ImportContrac=(ImportContract) businApi.getQueryObject("from ImportContract where orderFromId = ? ", new Object[]{orderid});
        if(ImportContrac!=null){
            List imContractDetail= businApi.getQueryList("select p.productName as commodityName,d.model,d.quantity,d.unitPrice,d.totalAmount from ImContractDetail d  , Product p where d.commodityName=p.id  and d.imContractId = ?", new Object[]{ImportContrac.getId()});
            model.addAttribute("imContractDetail", imContractDetail);
        }
        model.addAttribute("order_code", orderFrom);
        TreeModel treeModel = taskBusin.getOrderTreeModel(orderid);
        model.addAttribute("treeData",JSONObject.fromObject(treeModel));
        return "/order/ic/listCustomsDeAgreement";
    }
    
    
    /**
     * 跳转单据详情列表页面
     * @param id
     * @param hierarchy
     * @param taskTypeId
     * @return
     */
    @RequestMapping("/listWmImccOrderDesc/{id}/{hierarchy}/{taskTypeId}")
    public String listWmImccOrderDetail(@PathVariable String id,@PathVariable String hierarchy,@PathVariable String taskTypeId){
        return taskBusin.OrderDisposeByPm(id,hierarchy,taskTypeId);
    }
    /**
     * @Description 编辑进口合同信息
     * @author Chu Zhaocheng
     * @date 2017年6月26日 下午6:50:51
     * @action editImportContract
     * @return String
     */
    @RequestMapping("/editImportContract/{icId}")
    public String editImportContract(@PathVariable String icId) {
        ImportContract importContract = importContractBusin.getImportContract(icId);
        List<ImContractDetail> imContractDetails = importContractBusin.getImContractDetail(icId);
        model.addAttribute("importContract", importContract);
        model.addAttribute("imContractDetails", imContractDetails);
        return "/order/ic/editImportContract";
    }

    /**
     * @Description 保存进口合同信息
     * @author Chu Zhaocheng
     * @date 2017年6月26日 下午6:51:13
     * @action saveImportContract
     * @return String
     */
    @RequestMapping("/saveImportContract")
    @ResponseBody
    public AjaxDomain saveImportContract(ImportContract importContract, String commodityName, String models, String quantity, String unitPrice, String totalAmount, String taskTypeId,String accessory) {
        AjaxDomain ajaxDomain=new AjaxDomain();
        try {
            JSONArray commodityNameArray =null;
            if (accessory!=null) {
                 commodityNameArray = JSONArray.fromObject(accessory);
            }
            importContractBusin.saveImContract(importContract,taskTypeId, getCurrSup().getCompanyInfo().getComwaimao(), commodityName, models, quantity, unitPrice, totalAmount);
            frameworkBusin.saveUploadFileOwner(importContract, (String[])commodityNameArray.toArray());
            String taskId=importContract.getTaskId();
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

}

