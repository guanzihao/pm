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
import com.sup.order.bean.ImContractDetail;
import com.sup.order.bean.ImportContract;
import com.sup.order.bean.OrderFrom;
import com.sup.order.busin.ImportContractBusin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 进口合同业务
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/sup/order/ic")
public class ImportContractController extends SupBaseController {

    @Autowired
    private ImportContractBusin importContractBusin;

    @Autowired
    private TaskBusin taskBusin;
    /**
     * @Description 进口合同列表查询
     * @author Chu Zhaocheng
     * @date 2017年6月26日 下午6:50:21
     * @action listImportContract
     * @return String
     */
    @RequestMapping("/viewCustomsDeAgreement/{orderid}/{nodeType}/{flowId}/{isCompany}")
    public String listImportContract(SearchBean searchBean,@PathVariable String orderid,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany){
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
       
        TreeModel treeModel = taskBusin.getOrderTreeModel(orderid);
        model.addAttribute("treeData",JSONObject.fromObject(treeModel));
        if(ImportContrac!=null){
           
            List imContractDetail= businApi.getQueryList("select p.productName as commodityName,d.model,d.quantity,d.unitPrice,d.totalAmount from ImContractDetail d  , Product p where d.commodityId=p.id  and d.imContractId = ?", new Object[]{ImportContrac.getId()});
            model.addAttribute("imContractDetail", imContractDetail);
        }
        String usertype="";
        if(this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
            
        }else if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }
        model.addAttribute("usertype", usertype);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("order_code", orderFrom);
        return "/sup/order/ic/listCustomsDeAgreement";
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
        return taskBusin.OrderDispose(id,hierarchy,taskTypeId);
    }
    /**
     * @Description 编辑进口合同信息
     * @author Chu Zhaocheng
     * @date 2017年6月26日 下午6:50:51
     * @action editImportContract
     * @return String
     */
    @RequestMapping("/editImportContract/{icId}/{nodeType}/{flowId}/{isCompany}")
    public String editImportContract(@PathVariable String icId,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany) {
        ImportContract importContract = importContractBusin.getObject(icId);
        if (importContract!=null) {
            List<ImContractDetail> imContractDetails = importContractBusin.getImContractDetail(importContract.getId());
           JSONArray jsonArray = JSONArray.fromObject(imContractDetails);
            model.addAttribute("imContractDetails", jsonArray.toString());
        }
        SupCompanyInfo sci = getCurrSup().getSupCompanyInfo();
        if (sci!=null) {
            model.addAttribute("supCompanyInFo", "1");
        }else {
            model.addAttribute("supCompanyInFo", "0");
        }
        model.addAttribute("importContract", importContract);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
        return "/sup/order/customer/editImportContract";
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
    public AjaxDomain saveImportContract(ImportContract importContract,String commodityNameId, String commodityName, String models, String quantity, 
            String unitPrice, String totalAmount, String taskTypeId,String fileArray,String orderFromId) {
        AjaxDomain ajaxDomain=new AjaxDomain();
        try {
            String fileArrayArray []=null;
            if (fileArray!=null) {
                fileArrayArray = fileArray.split(",");
            }
            importContractBusin.saveImContract(importContract,taskTypeId, getCurrSup().getCompanyInfo().getComwaimao(),commodityNameId, commodityName, models, quantity, unitPrice, totalAmount,orderFromId);
            frameworkBusin.saveUploadFileOwner(importContract,fileArrayArray);
            String taskId=importContract.getTaskId();
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
               map.put("orderFromId", importContract.getId());
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
    public String saveObject(ImportContract importContract,String fileArray[],@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany
            ,String ids[],String commodityName[],String models[],String quantity[],String unitPrice[],String totalAmount[]
              ,String commodityId[]
            ){
        importContractBusin.saveObject(importContract);
        frameworkBusin.saveUploadFileOwner(importContract,fileArray);
        importContractBusin.saveObjectDetail(importContract.getId(), ids, commodityName, models, quantity, unitPrice, totalAmount,commodityId);
        return "redirect:/sup/order/ic/viewCustomsDeAgreement/"+importContract.getOrderFromId()+"/"+nodeType+"/"+flowId+"/"+isCompany;
    }
}