package com.sup.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.TreeModel;
import com.pm.task.bean.AjaxDomain;
import com.pm.task.bean.Task;
import com.pm.task.busin.TaskBusin;
import com.sup.company.controller.SupBaseController;
import com.sup.order.bean.ExContractDetail;
import com.sup.order.bean.ExportContract;
import com.sup.order.bean.OrderFrom;
import com.sup.order.busin.ExContractDetailBusin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/sup/order/ex")
public class ExportContractController extends SupBaseController {

    @Autowired
    private ExContractDetailBusin exContractDetailBusin;

    @Autowired
    private TaskBusin taskBusin;

    /**
     * @Description 合同列表查询
     * @author Chu Zhaocheng
     * @date 2017年6月26日 下午6:50:21
     * @action listImportContract
     * @return String
     */
    @RequestMapping("/viewCustomsDeAgreement/{orderid}/{nodeType}/{flowId}/{isCompany}")
    public String listImportContract(SearchBean searchBean, @PathVariable String orderid, @PathVariable String nodeType, @PathVariable String flowId, @PathVariable String isCompany) {
        OrderFrom orderFrom = (OrderFrom) businApi.get(OrderFrom.class, orderid);
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql = "select c.obj_id,ci.com_Name,o.order_code,o.task_id,ci.obj_updateDate,c.contract_date,c.contract_no" 
        + " ,c.sellers,c.s_address,c.s_telphone,c.s_fax,c.buyers,c.b_address" 
        + " ,c.b_telphone,c.b_fax,c.shipment_time,c.delivery_time,c.port_shipment,c.port_destination" 
        + " ,c.packing,c.insurance,c.manufactory,c.term_payment,c.inspection" 
        + " ,c.arbitration,c.others,c.obj_createDate,c.is_delegation,c.start_date,c.end_date,c.explain,c.consignor"

        + " from PM_EXPORT_CONTRACT c ,PM_OrderFrom o,PM_COMPANY_CompanyInfo ci" + " where  c.order_from_id=o.obj_id" + " and c.user_id=ci.obj_id  and o.obj_id='" + orderid + "' ";
        pageBean.addQuerySQL(sql);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        ExportContract exportContract = (ExportContract) businApi.getQueryObject("from ExportContract where orderFromId = ? ", new Object[] { orderid });
        List imContractDetail;
        if (exportContract != null) {
            imContractDetail = businApi.getQueryList("select p.productName as commodityName,d.model,d.quantity,d.unitPrice,d.totalAmount from ExContractDetail d  , Product p where d.commodityId=p.id  and d.exContractId = ?", new Object[] { exportContract.getId() });
            model.addAttribute("imContractDetail", imContractDetail);
        }
        CompanyInfoUser CompanyInfoUser = (CompanyInfoUser) businApi.getQueryObject("from CompanyInfoUser where id=?", new Object[] { this.getCurrSup().getId() });
        model.addAttribute("username", CompanyInfoUser);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany", isCompany);
        String usertype = "";
        if (this.getCurrSup().getCompanyInfo() != null) {
            usertype = "com";

        } else if (this.getCurrSup().getSupCompanyInfo() != null) {
            usertype = "sup";
        }
        model.addAttribute("usertype", usertype);
        model.addAttribute("order_code", orderFrom);
        TreeModel treeModel = taskBusin.getOrderTreeModel(orderid);
        model.addAttribute("treeData", JSONObject.fromObject(treeModel));
        return "/sup/order/ex/listCustomsDeAgreement";
    }

    /**
     * 跳转单据详情列表页面
     * 
     * @param id
     * @param hierarchy
     * @param taskTypeId
     * @return
     */
    @RequestMapping("/listWmExccOrderDesc/{id}/{hierarchy}/{taskTypeId}")
    public String listWmExccOrderDetail(@PathVariable String id, @PathVariable String hierarchy, @PathVariable String taskTypeId) {
        return taskBusin.OrderDispose(id, hierarchy, taskTypeId);
    }

    /**
     * @Description 编辑进口合同信息
     * @author Chu Zhaocheng
     * @date 2017年6月26日 下午6:50:51
     * @action editImportContract
     * @return String
     */
    @RequestMapping("/editExportContract/{exId}/{nodeType}/{flowId}/{isCompany}")
    public String editImportContract(@PathVariable String exId, @PathVariable String nodeType, @PathVariable String flowId, @PathVariable String isCompany) {
        ExportContract importContract = exContractDetailBusin.getObject(exId);
        if (importContract != null) {
            List<ExContractDetail> exContractDetails = exContractDetailBusin.getExContractDetail(importContract.getId());
            JSONArray jsonArray = JSONArray.fromObject(exContractDetails);
            model.addAttribute("exContractDetails", jsonArray.toString());
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
        model.addAttribute("isCompany", isCompany);
        return "/sup/order/customer/editExportContract";
    }

    /**
     * @Description 保存进口合同信息
     * @author Chu Zhaocheng
     * @date 2017年6月26日 下午6:51:13
     * @action saveImportContract
     * @return String
     */
    @RequestMapping("/saveExportContract")
    @ResponseBody
    public AjaxDomain saveImportContract(ExportContract exportContract, String commodityNameId, String commodityName, String models, String quantity, String unitPrice, String totalAmount, String taskTypeId, String fileArray,String orderFromId) {
        AjaxDomain ajaxDomain = new AjaxDomain();
        try {
            String fileArrayArray[] = null;
            if (fileArray != null) {
                fileArrayArray = fileArray.split(",");
            }
            exContractDetailBusin.saveExportContract(exportContract, taskTypeId, getCurrSup().getCompanyInfo().getComwaimao(), commodityNameId, commodityName, models, quantity, unitPrice, totalAmount,orderFromId);
            frameworkBusin.saveUploadFileOwner(exportContract, fileArrayArray);
            String taskId = exportContract.getTaskId();
            String taskType = "";
            String oneTaskId = "";
            if (!StringUtil.isEmpty(taskId)) {
                Task task = (Task) businApi.get(Task.class, taskId);
                if (task != null) {
                    String type = task.getTackType();
                    String[] types = type.split(",");
                    for (String str : types) {
                        if ("".equals(oneTaskId)) {
                            oneTaskId = str;
                        }
                        List list = businApi.getQueryList("FROM OrderFrom a where a.flowType.id=? and a.task.id=?", new Object[] { str, taskId });
                        if (list != null && list.size() == 0) {
                            if ("".equals(taskType)) {
                                taskType = str;
                                break;
                            }
                        }
                    }
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            if ("".equals(taskType)) {
                taskType = "1";
                map.put("oneTaskId", oneTaskId);
            }
            map.put("isTaskType", taskType);
            if (orderFromId!=null&&!"".equals(orderFromId)) {
                map.put("orderFromId", orderFromId);
             
            }else{
                map.put("orderFromId", exportContract.getId());
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
     * @Description 保存进口合同信息
     * @author Chu Zhaocheng
     * @date 2017年6月26日 下午6:51:13
     * @action saveImportContract
     * @return String
     */

    @RequestMapping("/saveObject/{nodeType}/{flowId}/{isCompany}")
    public String saveObject(ExportContract exportContract, String fileArray[], @PathVariable String nodeType, @PathVariable String flowId, @PathVariable String isCompany, String ids[], String commodityName[], String models[], String quantity[], String unitPrice[], String totalAmount[], String commodityId[]) {
        exContractDetailBusin.saveObject(exportContract);
        frameworkBusin.saveUploadFileOwner(exportContract, fileArray);
        exContractDetailBusin.saveObjectDetail(exportContract.getId(), ids, commodityName, models, quantity, unitPrice, totalAmount, commodityId);
        return "redirect:/sup/order/ex/viewCustomsDeAgreement/" + exportContract.getOrderFromId() + "/" + nodeType + "/" + flowId + "/" + isCompany;
    }

}
