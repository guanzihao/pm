package com.sup.company.controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.model.TaskState;
import com.pm.core.util.StringUtil;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.task.bean.AjaxDomain;
import com.sup.company.busin.CompanyInfoUserBusin;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.CustomsDeAgreement;
import com.sup.order.bean.ExportContract;
import com.sup.order.bean.ExportCustomsDeAgreement;
import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.ExportShippingExCommission;
import com.sup.order.bean.ImportContract;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.PurchaseOrderProduction;
import com.sup.order.bean.ShippingExCommission;
import com.sup.order.bean.TropShippingExCommission;

@Controller
@RequestMapping("/sup/supcompany/order")
public class SupCompanyOrderController extends SupBaseController {
    @Autowired
    public CompanyInfoUserBusin companyInfoUserBusin;
    
    

    /**
     * 跳转至报关出口订单页面
     * 
     * @return
     */
    @RequestMapping(value = "/toBgFlowExccOrderPage/{isCompany}")
    public String toBgFlowExccOrderPage(@PathVariable String isCompany) {
        model.addAttribute("flowId", VocationalWorkEnum.BG_EXCC.getId());
        model.addAttribute("complete", TaskState.YWC);
        model.addAttribute("handle", TaskState.CLZ);
        model.addAttribute("cansole", TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        model.addAttribute("isCompany",isCompany);
        return "/sup/flow/bgexcc/listBgFlowExccOrder";
    }
    /**
     * 跳转至订单列表
     * @param isCompany
     * @return
     */
    @RequestMapping(value = "/toOrderListPage/{isCompany}")
    public  String toOrderListPage(@PathVariable String isCompany){
        String result="";
        if("0".equals(isCompany)){
            SupCompanyInfo supCompanyInFo = getCurrSup().getSupCompanyInfo();
            if (supCompanyInFo!=null) {
                String comType = supCompanyInFo.getComType();
                VocationalWorkEnum bg=VocationalWorkEnum.BG;
                VocationalWorkEnum wm=VocationalWorkEnum.WM;
                VocationalWorkEnum wl=VocationalWorkEnum.WL;
                VocationalWorkEnum cc=VocationalWorkEnum.CC;
                if(comType.equals(bg.getId())){
                    result="redirect:/sup/supcompany/order/toFlowImccPage/"+isCompany;
                }else if(comType.equals(wl.getId())){
                    result= "redirect:/sup/supcompany/order/toWlFlowImccOrder/"+isCompany;
                }else if(comType.equals(wm.getId())){
                    result="redirect:/sup/supcompany/order/toWmFlowImccOrderPage/"+isCompany;
                }else if(comType.equals(cc.getId())){
                    result="redirect:/sup/supcompany/order/toCCFlowImccOrder/"+isCompany;
                }
            }
        }
        return result;
    }

    /**
     * 显示报关出口订单列表信息
     * 
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/listBgFlowExccOrder/{nodeType}/{flowId}/{isCompany}")
    public String showBgFlowExccOrder(@PathVariable String nodeType,@PathVariable String isCompany, @PathVariable String flowId, SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql = new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name \n");
        sql.append(" from PM_OrderFrom o ");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        String user_id=null;
        CompanyInfoUser user = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if(isCompany.equals("0")){
            sql.append(" where  o.supplier=? and o.order_state not in(1,5,12) and b.flow_name=?\n");
            user_id=user.getSupCompanyInfo().getId();
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id");
            sql.append(" where  task.user_id=?  and b.flow_name=?\n");
            user_id=user.getCompanyInfo().getId();
        }
        if (!StringUtils.isEmpty(searchBean.getSearchName1())) {
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate1())) {
            sql.append(" and o.obj_createDate>=?");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate2())) {
            sql.append(" and o.obj_createDate<=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName2())){
            sql.append(" and ci.com_Name like'%"+searchBean.getSearchName2()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName3())){
            sql.append(" and sup.comName like'%"+searchBean.getSearchName3()+"%'");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        pageBean.addParams(user_id);
        VocationalWorkEnum enums = VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if (!StringUtils.isEmpty(searchBean.getSearchDate1())) {
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate2())) {
            pageBean.addParams(searchBean.getSearchDate2()+" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        String usertype="";
        if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }else if (this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
        }
        model.addAttribute("usertype",usertype);
        model.addAttribute("searchBean", searchBean);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("pageBean", pageBean);
        return "/sup/flow/bgexcc/listBgFlowExccOrderDetail";
    }
     
    
    /**
     * 跳转至进口报关订单页面
     * 
     * @return
     */
    @RequestMapping(value = "/toFlowImccPage/{isCompany}")
    public String toImccOrderPage(@PathVariable String isCompany) {
        model.addAttribute("flowId", VocationalWorkEnum.BG_IMCC.getId());
        model.addAttribute("complete", TaskState.YWC);
        model.addAttribute("handle", TaskState.CLZ);
        model.addAttribute("cansole", TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        model.addAttribute("isCompany",isCompany);
        return "/sup/flow/bgimcc/listBgFlowImccOrder";
    }

    /**
     * 查询进口报关订单列表
     * isCompany定义：供应商0，，客户1
     * @param searchBean 
     * @return
     */
    @RequestMapping(value = "/listFlowImccOrder/{nodeType}/{flowId}/{isCompany}")
    public String showFlowImccOrder(@PathVariable String nodeType,@PathVariable String isCompany, @PathVariable String flowId, SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql = new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name \n");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        if(isCompany.equals("0")){
            sql.append(" where  o.supplier=? and o.order_state not in(1,5,12)  and b.flow_name=?\n");
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id");
            sql.append(" where  task.user_id=?  and b.flow_name=?\n");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchName1())) {
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate1())) {
            sql.append(" and o.obj_createDate>=?");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate2())) {
            sql.append(" and o.obj_createDate<=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName2())){
            sql.append(" and ci.com_Name like'%"+searchBean.getSearchName2()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName3())){
            sql.append(" and sup.comName like'%"+searchBean.getSearchName3()+"%'");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        //ompanyInfoUser user = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if(isCompany.equals("0")){
            pageBean.addParams(getCurrSup().getSupCompanyInfo().getId());
        }else{
            pageBean.addParams(getCurrSup().getCompanyInfo().getId());
        }
        VocationalWorkEnum enums = VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if (!StringUtils.isEmpty(searchBean.getSearchDate1())) {
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate2())) {
            pageBean.addParams(searchBean.getSearchDate2()+" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        String usertype="";
        if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }else if (this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
        }
        model.addAttribute("usertype",usertype);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("searchBean", searchBean);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("pageBean", pageBean);
        return "/sup/flow/bgimcc/listBgFlowImccOrderDetail";
    }

    /**
     * 跳转至仓储出库订单页面
     * 
     * @return
     */
    @RequestMapping("/toCCFlowExccOrder/{isCompany}")
    public String toCcFlowExccOrderPage(@PathVariable String isCompany) {
        model.addAttribute("flowId", VocationalWorkEnum.CC_EXCC.getId());
        model.addAttribute("complete", TaskState.YWC);
        model.addAttribute("handle", TaskState.CLZ);
        model.addAttribute("cansole", TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        model.addAttribute("isCompany",isCompany);
        return "/sup/flow/ccexcc/listCcFlowExccOrder";
    }
    

    /**
     * 显示仓储出库订单列表信息
     * 
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/showCcFlowExccOrderList/{nodeType}/{flowId}/{isCompany}")
    public String showCcFlowExccOrderList(@PathVariable String nodeType,@PathVariable String isCompany, @PathVariable String flowId, SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql = new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name \n");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id \n");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        if(isCompany.equals("0")){
            sql.append(" where  o.supplier=?  and o.order_state not in(1,5,12) and  b.flow_name=?\n");
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id");
            sql.append(" where  task.user_id=?  and b.flow_name=?\n");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchName1())) {
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate1())) {
            sql.append(" and o.obj_createDate>=?");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate2())) {
            sql.append(" and o.obj_createDate<=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName2())){
            sql.append(" and ci.com_Name like'%"+searchBean.getSearchName2()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName3())){
            sql.append(" and sup.comName like'%"+searchBean.getSearchName3()+"%'");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        CompanyInfoUser user = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if(isCompany.equals("0")){
            pageBean.addParams(user.getSupCompanyInfo().getId());
        }else{
            pageBean.addParams(user.getCompanyInfo().getId());
        }
        VocationalWorkEnum enums = VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if (!StringUtils.isEmpty(searchBean.getSearchDate1())) {
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate2())) {
            pageBean.addParams(searchBean.getSearchDate2() +" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        String usertype="";
        if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }else if (this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
        }
        model.addAttribute("usertype",usertype);
        model.addAttribute("flowId", flowId);
        model.addAttribute("searchBean", searchBean);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("pageBean", pageBean);
        return "/sup/flow/ccexcc/listCcFlowExccOrderDetail";

    }

    /**
     * 跳转至仓储入库订单页面
     * 
     * @return
     */
    @RequestMapping("/toCCFlowImccOrder/{isCompany}")
    public String toCcFlowImccOrderPage(@PathVariable String isCompany) {
        model.addAttribute("flowId", VocationalWorkEnum.CC_IMCC.getId());
        model.addAttribute("complete", TaskState.YWC);
        model.addAttribute("handle", TaskState.CLZ);
        model.addAttribute("cansole", TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        model.addAttribute("isCompany",isCompany);
        return "/sup/flow/ccimcc/listCcFlowImccOrder";
    }

    /**
     * 显示仓储入库订单列表信息
     * 
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/showCcFlowImccOrderList/{nodeType}/{flowId}/{isCompany}")
    public String showCcFlowImccOrderList(@PathVariable String nodeType,@PathVariable String isCompany, @PathVariable String flowId, SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql = new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name \n");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id \n");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        if(isCompany.equals("0")){
            sql.append(" where  o.supplier=? and o.order_state not in(1,5,12) and b.flow_name=?\n");
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id");
            sql.append(" where  task.user_id=? and b.flow_name=?\n");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchName1())) {
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate1())) {
            sql.append(" and o.obj_createDate>=?");
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate2())) {
            sql.append(" and o.obj_createDate<=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName2())){
            sql.append(" and ci.com_Name like'%"+searchBean.getSearchName2()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName3())){
            sql.append(" and sup.comName like'%"+searchBean.getSearchName3()+"%'");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        CompanyInfoUser user = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if(isCompany.equals("0")){
            pageBean.addParams(user.getSupCompanyInfo().getId());
        }else{
            pageBean.addParams(user.getCompanyInfo().getId());
        }
        VocationalWorkEnum enums = VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if (!StringUtils.isEmpty(searchBean.getSearchDate1())) {
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if (!StringUtils.isEmpty(searchBean.getSearchDate2())) {
            pageBean.addParams(searchBean.getSearchDate2()+" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        String usertype="";
        if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }else if (this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
        }
        model.addAttribute("usertype",usertype);
        model.addAttribute("searchBean", searchBean);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("pageBean", pageBean);
        return "/sup/flow/ccimcc/listCcFlowImccOrderDetail";

    }
    
    /**
     * 跳转至物流出口订单页面
     * @return
     */
    @RequestMapping("/toWlFlowExccOrder/{isCompany}")
    public String toWlFlowExccOrderPage(@PathVariable String isCompany){
        model.addAttribute("flowId",VocationalWorkEnum.WL_EXCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        model.addAttribute("isCompany",isCompany);
        return "/sup/flow/wlexcc/listWlFlowExccOrder";
    }
    /**
     * 显示物流出口订单列表信息
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listWlFlowExccOrder/{nodeType}/{flowId}/{isCompany}")
    public String showWlFlowExccOrderList(@PathVariable String nodeType,@PathVariable String isCompany,@PathVariable String flowId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc \n");
        sql.append(" ,o.node4_state,o.node4_desc,o.node5_state,o.node5_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name \n");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id \n");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        if(isCompany.equals("0")){
            sql.append(" where  o.supplier=?  and o.order_state not in(1,5,12) and b.flow_name=?\n");
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id");
            sql.append(" where  task.user_id=?  and b.flow_name=?\n");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            sql.append(" and task.issue_date>=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            sql.append(" and task.issue_date<=?");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        CompanyInfoUser user = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if(isCompany.equals("0")){
            pageBean.addParams(user.getSupCompanyInfo().getId());
        }else{
            pageBean.addParams(user.getCompanyInfo().getId());
        }
        VocationalWorkEnum enums=VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            pageBean.addParams(searchBean.getSearchDate2() +" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        String usertype="";
        if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }else if (this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
        }
        model.addAttribute("usertype",usertype);
        model.addAttribute("flowId",flowId);
        model.addAttribute("searchBean", searchBean);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("pageBean", pageBean);
        return "/sup/flow/wlexcc/listWlFlowExccOrderDetail";
        
    }
    
    /**
     * 跳转至物流进口订单页面
     * @return
     */
    @RequestMapping("/toWlFlowImccOrder/{isCompany}")
    public String toWlFlowImccOrderPage(@PathVariable String isCompany){
        model.addAttribute("flowId",VocationalWorkEnum.WL_IMCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        model.addAttribute("isCompany",isCompany);
        return "/sup/flow/wlimcc/listWlFlowImccOrder";
    }
    /**
     * 显示物流进口订单列表信息
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/listWlFlowImccOrder/{nodeType}/{flowId}/{isCompany}")
    public String showWlFlowImccOrderList(@PathVariable String nodeType,@PathVariable String isCompany,@PathVariable String flowId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc \n");
        sql.append(" ,o.node4_state,o.node4_desc,o.node5_state,o.node5_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name \n");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id \n");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        if(isCompany.equals("0")){
            sql.append(" where  o.supplier=? and o.order_state not in(1,5,12) and b.flow_name=?\n");
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id");
            sql.append(" where  task.user_id=? and b.flow_name=?\n");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            sql.append(" and o.obj_createDate>=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            sql.append(" and o.obj_createDate<=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName2())){
            sql.append(" and ci.com_Name like'%"+searchBean.getSearchName2()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName3())){
            sql.append(" and sup.comName like'%"+searchBean.getSearchName3()+"%'");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        CompanyInfoUser user = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if(isCompany.equals("0")){
            pageBean.addParams(user.getSupCompanyInfo().getId());
        }else{
            pageBean.addParams(user.getCompanyInfo().getId());
        }
        VocationalWorkEnum enums=VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            pageBean.addParams(searchBean.getSearchDate2()+" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        String usertype="";
        if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }else if (this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
        }
        model.addAttribute("usertype",usertype);
        model.addAttribute("flowId",flowId);
        model.addAttribute("searchBean", searchBean);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("pageBean", pageBean);
        return "/sup/flow/wlimcc/listWlFlowImccOrderDetail";
    }
    
    /**
     * 跳转至物流运输订单页面
     * @return
     */
    @RequestMapping(value="/toWlTransportOrder/{isCompany}")
    public String toWlTransportOrderPage(@PathVariable String isCompany){
        model.addAttribute("flowId",VocationalWorkEnum.WL_TRANSPORT.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        model.addAttribute("isCompany",isCompany);
        return "/sup/flow/wltransport/listWlFlowTransportOrder";
    }
    /**
     *  显示物流运输的列表信息
     * @param nodeType   状态类型ID
     * @param searchBean 参数列表
     * @return
     */
    @RequestMapping(value="/showWlTransportOrderList/{nodeType}/{flowId}/{isCompany}")
    public  String showWlTransportOrderlist(@PathVariable String nodeType,@PathVariable String isCompany,@PathVariable String flowId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,o.order_state,sup.comName ,o.order_rate,ci.com_Name \n");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id \n");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        if(isCompany.equals("0")){
            sql.append(" where  o.supplier=? and o.order_state not in(1,5,12) and b.flow_name=?\n");
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id");
            sql.append(" where  task.user_id=? and b.flow_name=?\n");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            sql.append(" and o.obj_createDate>=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            sql.append(" and o.obj_createDate<=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName2())){
            sql.append(" and ci.com_Name like'%"+searchBean.getSearchName2()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName3())){
            sql.append(" and sup.comName like'%"+searchBean.getSearchName3()+"%'");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        CompanyInfoUser user = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if(isCompany.equals("0")){
            pageBean.addParams(user.getSupCompanyInfo().getId());
        }else{
            pageBean.addParams(user.getCompanyInfo().getId());
        }
        VocationalWorkEnum enums=VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            pageBean.addParams(searchBean.getSearchDate2()+" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        String usertype="";
        if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }else if (this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
        }
        model.addAttribute("usertype",usertype);
        model.addAttribute("searchBean", searchBean);
        model.addAttribute("flowId",flowId);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("pageBean", pageBean);
        return "/sup/flow/wltransport/listWlFlowTransportOrderDetail";
        
    }
    
    /**
     * 跳转至外贸出口订单页面
     * @return
     */
    @RequestMapping("/toWmFlowExccOrderPage/{isCompany}")
    public  String toWmFlowExccOrderPage(@PathVariable String isCompany){
        model.addAttribute("flowId",VocationalWorkEnum.WM_EXCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        model.addAttribute("isCompany",isCompany);
        return "/sup/flow/wmexcc/listWmFlowExccOrder";
    }
    /**
     * 显示外贸出口订单列表信息
     * @param nodeType
     * @param flowId
     * @param searchBean
     * @return
     */
    @RequestMapping(value="/showWmFlowExccOrder/{nodeType}/{flowId}/{isCompany}")
    public String showWmFlowExccOrder(@PathVariable String nodeType,@PathVariable String isCompany,@PathVariable String flowId,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,\n");
        sql.append(" o.node4_state,o.node4_desc,o.node5_state,o.node5_desc ");
        sql.append(" ,o.node6_state,o.node6_desc,o.node7_state,o.node7_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name ");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id \n");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        if(isCompany.equals("0")){
            sql.append(" where  o.supplier=? and o.order_state not in(1,5,12) and b.flow_name=?\n");
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id");
            sql.append(" where  task.user_id=? and b.flow_name=?\n");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            sql.append(" and o.obj_createDate>=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            sql.append(" and o.obj_createDate<=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName2())){
            sql.append(" and ci.com_Name like'%"+searchBean.getSearchName2()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName3())){
            sql.append(" and sup.comName like'%"+searchBean.getSearchName3()+"%'");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        CompanyInfoUser user = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if(isCompany.equals("0")){
            pageBean.addParams(user.getSupCompanyInfo().getId());
        }else{
            pageBean.addParams(user.getCompanyInfo().getId());
        }
        VocationalWorkEnum enums=VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            pageBean.addParams(searchBean.getSearchDate2()+" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        String usertype="";
        if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }else if (this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
        }
        model.addAttribute("usertype",usertype);
        model.addAttribute("searchBean", searchBean);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("pageBean", pageBean);
        return "/sup/flow/wmexcc/listWmFlowExccOrderDetail";
    }
    
    /**
     * 跳转至外贸进口订单页面
     * @return
     */
    @RequestMapping("/toWmFlowImccOrderPage/{isCompany}")
    public  String toWmFlowImccOrderPage(@PathVariable String isCompany){
        model.addAttribute("flowId",VocationalWorkEnum.WM_IMCC.getId());
        model.addAttribute("complete",TaskState.YWC);
        model.addAttribute("handle",TaskState.CLZ);
        model.addAttribute("cansole",TaskState.QX);
        model.addAttribute("draft", TaskState.CG);
        model.addAttribute("isCompany",isCompany);
        return "/sup/flow/wmimcc/listWmFlowImccOrder";
    }
    /**
     * 查询外贸进口订单列表信息
     * @param nodeType 节点类型
     * @param searchBean 参数
     * @return
     */
    @RequestMapping(value="/listWmFlowImccOrder/{nodeType}/{flowId}/{isCompany}")
    public String showWmFlowImccOrder(@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("select o.obj_Id,o.order_code, \n");
        sql.append(" o.node1_state,o.node1_desc,o.node2_state \n");
        sql.append(" ,o.node2_desc,o.node3_state,o.node3_desc,\n");
        sql.append(" o.node4_state,o.node4_desc,o.node5_state,o.node5_desc ");
        sql.append(" ,o.node6_state,o.node6_desc,o.node7_state,o.node7_desc,o.order_state,sup.comName,o.order_rate,ci.com_Name ");
        sql.append(" from PM_OrderFrom o \n");
        sql.append(" left join PM_FlowType b on   o.tack_type_id=b.obj_Id \n");
        sql.append(" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id");
        sql.append(" INNER JOIN PM_Task t  ON o.task_id=t.obj_Id ");
        sql.append(" INNER JOIN PM_COMPANY_CompanyInfo ci ON t.user_id=ci.obj_Id ");
        if(isCompany.equals("0")){
            sql.append(" where  o.supplier=? and o.order_state not in(1,5,12) and b.flow_name=?\n");
        }else{
            sql.append(" left join PM_Task task on o.task_id=task.obj_Id");
            sql.append(" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id");
            sql.append(" where  task.user_id=? and b.flow_name=?\n");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            sql.append(" and o.order_code like'%"+searchBean.getSearchName1()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            sql.append(" and o.obj_createDate>=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            sql.append(" and o.obj_createDate<=?");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName2())){
            sql.append(" and ci.com_Name like'%"+searchBean.getSearchName2()+"%'");
        }
        if(!StringUtils.isEmpty(searchBean.getSearchName3())){
            sql.append(" and sup.comName like'%"+searchBean.getSearchName3()+"%'");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                sql.append(" and o.order_state!=?");
            }else{
                sql.append(" and o.order_state=?");
            }
            
        } 
        pageBean.addQuerySQL(sql.toString());
        CompanyInfoUser user = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if(isCompany.equals("0")){
            pageBean.addParams(user.getSupCompanyInfo().getId());
        }else{
            pageBean.addParams(user.getCompanyInfo().getId());
        }
        VocationalWorkEnum enums=VocationalWorkEnum.getName(flowId);
        pageBean.addParams(enums.getName());
        if(!StringUtils.isEmpty(searchBean.getSearchDate1())){
            pageBean.addParams(searchBean.getSearchDate1());
        }
        if(!StringUtils.isEmpty(searchBean.getSearchDate2())){
            pageBean.addParams(searchBean.getSearchDate2()+" 23:59:59");
        }
        if (!nodeType.equals("0")) {
            if(nodeType.equals("6")){
                pageBean.addParams(TaskState.YWC);
            }else{
                pageBean.addParams(nodeType);
            }
        } 
        pageBean.addOrderBy("o.obj_createDate", true);
        pageBean.sqlquerys();
        String usertype="";
        if(this.getCurrSup().getSupCompanyInfo()!=null){
            usertype="sup";
        }else if (this.getCurrSup().getCompanyInfo()!=null){
            usertype="com";
        }
        model.addAttribute("usertype",usertype);
        model.addAttribute("searchBean", searchBean);
        model.addAttribute("nodeType", nodeType);
        model.addAttribute("flowId", flowId);
        model.addAttribute("isCompany",isCompany);
        model.addAttribute("pageBean", pageBean);
        return "/sup/flow/wmimcc/listWmFlowImccOrderDetail";
    }
    
    /**
     * 如果订单没有被供应商确认接收，则可以取消订单
     * @param id
     * @return
     */
    @RequestMapping("/updateorder")
    @ResponseBody
    public AjaxDomain updatestateorder(String id) {
        
        AjaxDomain ajaxDomain=new AjaxDomain();
        try {
            if (id != null ) {
                OrderFrom orderFrom =(OrderFrom) businApi.get(OrderFrom.class, id);
                if (orderFrom != null && orderFrom.getOrderState()!=null ) {
                    if(orderFrom.getOrderState()==TaskState.WFP||orderFrom.getOrderState()==TaskState.FWSYJJ||orderFrom.getOrderState()==TaskState.YTJ){
                        orderFrom.setOrderState(TaskState.CG);
                        businApi.save(orderFrom);
                    }
                }
            }
            ajaxDomain.setStatusCode("200");
         } catch (Exception e) {
             ajaxDomain.setStatusCode("400");
            
         }
     return ajaxDomain;
    }
    
    
    
   /**
    * 拒绝请求
    * @param id 订单ID
    * @param type 订单类型
    * @param nodeType 。。
    * @param flowId  。。
    * @param isCompany 。。
    * @return
    */
    @ResponseBody
   @RequestMapping("/supupdateorder")
   public AjaxDomain supupdatestateorder(String id) {
       AjaxDomain ajaxDomain=new AjaxDomain();
       try {
           if (id != null ) {
               OrderFrom orderFrom =(OrderFrom) businApi.get(OrderFrom.class, id);
               if (orderFrom != null ) {
                   orderFrom.setOrderState(TaskState.FWSYJJ);
                   businApi.save(orderFrom);
               }
           }
           ajaxDomain.setStatusCode("200");
        } catch (Exception e) {
            ajaxDomain.setStatusCode("400");
           
        }
    return ajaxDomain;
    }
   
   
   /**
    * 接收请求
    * @param id 订单ID
    * @param type 订单类型
    * @param nodeType 。。
    * @param flowId  。。
    * @param isCompany 。。
    * @return
    */
 
   @RequestMapping("/supupdateorderok")
   @ResponseBody
   public AjaxDomain supupdatestateorderok( String id) {
       AjaxDomain ajaxDomain=new AjaxDomain();
       try {
           if (id != null ) {
               OrderFrom orderFrom =(OrderFrom) businApi.get(OrderFrom.class, id);
               if (orderFrom != null ) {
                   orderFrom.setOrderState(TaskState.FWSYJS);
                   businApi.save(orderFrom);
               }
           }
           ajaxDomain.setStatusCode("200");
        } catch (Exception e) {
            ajaxDomain.setStatusCode("400");
           
        }
    return ajaxDomain;
    }

   /**
    * 提交
    * @param id 订单ID
    * @param type 订单类型
    * @param nodeType 。。
    * @param flowId  。。
    * @param isCompany 。。
    * @return
    */
   @RequestMapping("/tijiaoupdatestateorder")
   @ResponseBody
   public AjaxDomain tijiaoupdatestateorder(String id) {
       AjaxDomain ajaxDomain=new AjaxDomain();
       try {
           if (id != null ) {
               OrderFrom orderFrom =(OrderFrom) businApi.get(OrderFrom.class, id);
               if (orderFrom != null ) {
                   if (orderFrom!=null&&orderFrom.getSuppers()!=null) {
                       orderFrom.setOrderState(TaskState.YTJ);
                   }else {
                       orderFrom.setOrderState(TaskState.WFP);
                   }
                   FlowType flowType = orderFrom.getFlowType();
                   Date endDate =null;
                   if (flowType!=null) {
                       VocationalWorkEnum bgImcc = VocationalWorkEnum.BG_IMCC;
                       VocationalWorkEnum bgExcc = VocationalWorkEnum.BG_EXCC;
                       VocationalWorkEnum wmImcc = VocationalWorkEnum.WM_IMCC;
                       VocationalWorkEnum wmExcc = VocationalWorkEnum.WM_EXCC;
                       VocationalWorkEnum wlImcc = VocationalWorkEnum.WL_IMCC;
                       VocationalWorkEnum wlExcc = VocationalWorkEnum.WL_EXCC;
                       VocationalWorkEnum wlTransport = VocationalWorkEnum.WL_TRANSPORT;
                       VocationalWorkEnum ccImcc = VocationalWorkEnum.CC_IMCC;
                       VocationalWorkEnum ccExcc = VocationalWorkEnum.CC_EXCC;
                       String typeId=flowType.getId();
                       
                       if (StringUtil.isEmpty(typeId, bgImcc.getId())) {
                           CustomsDeAgreement cda=(CustomsDeAgreement) businApi.getQueryObject("FROM CustomsDeAgreement a WHERE a.orderFromId=?", new Object[]{id});
                           if (cda!=null) {
                               endDate=cda.getEndDate();
                           }
                       } else if (StringUtil.isEmpty(typeId, bgExcc.getId())) {
                           ExportCustomsDeAgreement ecda= (ExportCustomsDeAgreement) businApi.getQueryObject("FROM ExportCustomsDeAgreement a WHERE a.orderFromId=?", new Object[]{id});
                           if (ecda!=null) {
                               endDate=ecda.getEndDate();
                           }
                       } else if (StringUtil.isEmpty(typeId, wmImcc.getId())) {
                           ImportContract ic= (ImportContract) businApi.getQueryObject("FROM ImportContract a WHERE a.orderFromId=?", new Object[]{id});
                           if (ic!=null) {
                               endDate=ic.getEndDate();
                           }
                       } else if (StringUtil.isEmpty(typeId, wmExcc.getId())) {
                           ExportContract ec= (ExportContract) businApi.getQueryObject("FROM ExportContract a WHERE a.orderFromId=?", new Object[]{id});
                           if (ec!=null) {
                               endDate=ec.getEndDate();
                           }
                       } else if (StringUtil.isEmpty(typeId, wlImcc.getId())) {
                           ShippingExCommission se= (ShippingExCommission) businApi.getQueryObject("FROM ShippingExCommission a WHERE a.orderFromId=?", new Object[]{id});
                           if (se!=null) {
                               endDate=se.getEndDate();
                           }
                       } else if (StringUtil.isEmpty(typeId, wlExcc.getId())) {
                           ExportShippingExCommission ese=  (ExportShippingExCommission) businApi.getQueryObject("FROM ExportShippingExCommission a WHERE a.orderFromId=?", new Object[]{id});
                           if (ese!=null) {
                               endDate=ese.getEndDate();
                           }
                       } else if (StringUtil.isEmpty(typeId, wlTransport.getId())) {
                           TropShippingExCommission tsc=  (TropShippingExCommission) businApi.getQueryObject("FROM TropShippingExCommission a WHERE a.orderFromId=?", new Object[]{id});
                           if (tsc!=null) {
                               endDate=tsc.getEndDate();
                           }
                       } else if (StringUtil.isEmpty(typeId, ccImcc.getId())) {
                           PurchaseOrderProduction pop= (PurchaseOrderProduction) businApi.getQueryObject("FROM PurchaseOrderProduction a WHERE a.orderFromId=?", new Object[]{id});
                           if (pop!=null) {
                               endDate=pop.getEndDate();
                           }
                       } else if (StringUtil.isEmpty(typeId, ccExcc.getId())) {
                           ExportPurchaseOrderProduction epop=  (ExportPurchaseOrderProduction) businApi.getQueryObject("FROM ExportPurchaseOrderProduction a WHERE a.orderFromId=?", new Object[]{id});
                           if (epop!=null) {
                               endDate=epop.getEndDate();
                           }
                       }
                   }
                   orderFrom.setProjectAccomplishDate(endDate);
                   businApi.save(orderFrom);
               }
           }
           ajaxDomain.setStatusCode("200");
        } catch (Exception e) {
            ajaxDomain.setStatusCode("400");
           
        }
    return ajaxDomain;
       
    }
   
   /**
    * 服务商拒绝
    * @param id
    * @param type
    * @param nodeType
    * @param flowId
    * @param isCompany
    * @return
    */
   @RequestMapping("/supupdateorders/{id}/{nodeType}/{flowId}/{isCompany}/{type}")
   public String supupdatestateorder(@PathVariable String id,@PathVariable String type,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany) {
       if (id != null ) {
           OrderFrom orderFrom =(OrderFrom) businApi.get(OrderFrom.class, id);
           if (orderFrom != null ) {
               orderFrom.setOrderState(TaskState.FWSYJJ);
               businApi.save(orderFrom);
           }
       }
       switch (type) {
       case "bgim":
           //listFlowImccOrder
           return "redirect:/sup/supcompany/order/toFlowImccPage/0";
       case "bgex":
           //listBgFlowExccOrder
           return "redirect:/sup/supcompany/order/toBgFlowExccOrderPage/0";
       case "wmim":
           //listWmFlowImccOrder
           return "redirect:/sup/supcompany/order/toWmFlowImccOrderPage/0";
       case "wmex":
           //showWmFlowExccOrder
           return "redirect:/sup/supcompany/order/toWmFlowExccOrderPage/0";
       case "wlim":
             //listWlFlowImccOrder
           return "redirect:/sup/supcompany/order/toWlFlowImccOrder/0";
       case "wlex":
             //listWlFlowExccOrder
           return "redirect:/sup/supcompany/order/toWlFlowExccOrder/0";
       case "wltrop":
             //showWlTransportOrderList
           return "redirect:/sup/supcompany/order/toWlTransportOrder/0";
       case "ccim":
             //showCcFlowImccOrderList
           return "redirect:/sup/supcompany/order/toCCFlowImccOrder/0";
       case "ccex":
             //showCcFlowExccOrderList
           return "redirect:/sup/supcompany/order/toCCFlowExccOrder/0";
       }
       return "";
    }
   
   /**
    * 服务商已接收
    * @param id
    * @param type
    * @param nodeType
    * @param flowId
    * @param isCompany
    * @return
    */
   @RequestMapping("/supupdateorderoks/{id}/{nodeType}/{flowId}/{isCompany}/{type}")
   public String supupdatestateorderok(@PathVariable String id,@PathVariable String type,@PathVariable String nodeType,@PathVariable String flowId,@PathVariable String isCompany) {
       if (id != null ) {
           OrderFrom orderFrom =(OrderFrom) businApi.get(OrderFrom.class, id);
           if (orderFrom != null ) {
               orderFrom.setOrderState(TaskState.FWSYJS);
               businApi.save(orderFrom);
           }
       }
       switch (type) {
       case "bgim":
           //listFlowImccOrder
           return "redirect:/sup/supcompany/order/toFlowImccPage/0";
       case "bgex":
           //listBgFlowExccOrder
           return "redirect:/sup/supcompany/order/toBgFlowExccOrderPage/0";
       case "wmim":
           //listWmFlowImccOrder
           return "redirect:/sup/supcompany/order/toWmFlowImccOrderPage/0";
       case "wmex":
           //showWmFlowExccOrder
           return "redirect:/sup/supcompany/order/toWmFlowExccOrderPage/0";
       case "wlim":
             //listWlFlowImccOrder
           return "redirect:/sup/supcompany/order/toWlFlowImccOrder/0";
       case "wlex":
             //listWlFlowExccOrder
           return "redirect:/sup/supcompany/order/toWlFlowExccOrder/0";
       case "wltrop":
             //showWlTransportOrderList
           return "redirect:/sup/supcompany/order/toWlTransportOrder/0";
       case "ccim":
             //showCcFlowImccOrderList
           return "redirect:/sup/supcompany/order/toCCFlowImccOrder/0";
       case "ccex":
             //showCcFlowExccOrderList
           return "redirect:/sup/supcompany/order/toCCFlowExccOrder/0";
       }
       return "";
    }
   
}
