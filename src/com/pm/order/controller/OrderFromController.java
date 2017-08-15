package com.pm.order.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.Contexts;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.DateUtil;
import com.pm.core.util.StringUtil;
import com.pm.order.bean.BgEcExpDomain;
import com.pm.order.bean.BgIcExpDomain;
import com.pm.order.bean.CcEcExpDomain;
import com.pm.order.bean.CcIcExpDomain;
import com.pm.order.bean.WlEcExpDomain;
import com.pm.order.bean.WlIcExpDomain;
import com.pm.order.bean.WmEcExpDomain;
import com.pm.order.bean.WmIcExpDomain;
import com.pm.order.busin.OrderFromBusin;
import com.pm.order.util.PoiExcelUtils;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.task.bean.AjaxDomain;
import com.pm.task.bean.Task;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.OrderFrom;

@Controller
@RequestMapping("/order")
public class OrderFromController extends OrganizeBaseController {
    
    @Autowired
    private OrderFromBusin orderFromBusin;
    
    @Autowired
    private CompanyInfoBusin companyInfoBusin;
    
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
    
    /**
     * 按类型保存一个订单根据任务Id
     * 
     * @param taskId
     * @return
     */
    @RequestMapping("/saveTaskIdOrderFrom")
    public String saveTaskIdOrderFrom(@PathVariable String taskId) {
        orderFromBusin.saveTaskIdOrderFrom(taskId);
        return "redirect:/task/listTask";
    }

    /**
     * 按类型保存一个订单根据任务Id
     * 
     * @param taskId
     * @return
     */
    @RequestMapping("/saveOrderFrom")
    public String saveOrderFrom(OrderFrom orderFrom, String suppersId, String[] orderFromFile, String flowTypeId, String taskId) {
        if (orderFrom!=null) {
            OrderFrom of=(OrderFrom) businApi.get(OrderFrom.class, orderFrom.getId());
            of.setOrderOpinion(orderFrom.getOrderOpinion());
            orderFromBusin.saveOrderFrom(of, suppersId, flowTypeId, taskId,getCurrUser().getId());
            frameworkBusin.saveUploadFileOwner(orderFrom, orderFromFile);
        }
        return "redirect:/task/listAllocationTask/close";
    }
    
    /**
     * 按类型保存一个订单根据任务Id
     * 
     * @param taskId
     * @return
     */
    @RequestMapping("/viewOrderFrom/{id}")
    public String viewOrderFrom(@PathVariable String id) {
        OrderFrom orderFrom=(OrderFrom) businApi.get(OrderFrom.class, id);
        if (orderFrom!=null) {
            String userName="尚未分配";
            Task task=orderFrom.getTask();
            model.addAttribute("task", task);
            SupCompanyInfo supper = orderFrom.getSuppers();
            model.addAttribute("supper", supper);
            String audiorId = orderFrom.getOrderAuditor();
            if (audiorId!=null) {
                UserAccount userAccount= (UserAccount) businApi.get(UserAccount.class , audiorId);
                if (userAccount!=null) {
                    userName=userAccount.getUserName();
                }
            }
            model.addAttribute("userName", userName);
        }
        
        model.addAttribute("orderFrom", orderFrom);
        return "/order/viewOrderFrom";
    }
    
    /**
     * 根据订单Id得到一条订单记录
     * 
     * @param taskId
     * @return
     */
    @RequestMapping("/editOrderFrom/{id}")
    public String getOrderFrom(@PathVariable String id) {
        OrderFrom orderFrom = orderFromBusin.getOrderFrom(id);
        model.addAttribute("orderFrom", orderFrom);
        if (orderFrom != null) {
            Task task = orderFrom.getTask();
            if (task != null) {
                model.addAttribute("info", task.getCompanyInfo());
            }
            model.addAttribute("task", task);
            model.addAttribute("flowType", orderFrom.getFlowType());
        }
        return "/order/editAllocationTask";
    }

    /**
     * @Description 根据任务ID查询多个订单
     * @author Chu Zhaocheng
     * @date 2017年6月16日 下午6:23:28
     * @action listOrderFrom
     * @return String
     */
    @RequestMapping("/listOrderFrom/{taskId}")
    public String listOrderFrom(@PathVariable String taskId) {
        model.addAttribute("orderFromList", orderFromBusin.listOrderFrom(taskId));
        return "/task/listOrderFrom";
    }

    /**
     * 查看一个任务的详细信息
     * 
     * @param task
     * @return
     */

    @RequestMapping("/viewTask/{id}")
    public String viewTask(@PathVariable String id) {
        Task task = (Task) businApi.get(Task.class, id);
        String flowTypeName = "";
        if (task != null) {
            String oldAllId = task.getTackType();
            String[] newAllId = oldAllId.split(",");

            for (String string : newAllId) {
                FlowType flowType = (FlowType) businApi.get(FlowType.class, string);
                flowTypeName += flowType.getFlowName() + " ";
            }
        }
        model.addAttribute("task", task);
        model.addAttribute("flowTypeName", flowTypeName);
        return "/task/viewTask";
    }

    /**
     * 更具任务查询订单
     * @param id 任务id
     * @return
     */
    @RequestMapping("/listOrder/{id}")
    public String listOrder(@PathVariable String id,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="select o.obj_id,o.order_num,f.flow_name,supName,t.task_name,supName,o.node_id,o.node_date,f.obj_id as ob from PM_OrderFrom o "
                + " inner join PM_FlowType f on f.obj_id=o.tack_type_id"
                + " inner join PM_task t on t.obj_id=o.task_id "
                //+ " inner join PM_FlowNode n on n.obj_id = o.node_id"
                + " where o.task_id= ?";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(id);
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/task/order/listOrder";
    }
    
    @RequestMapping(value="/listCompanyInfo")
    public String listCompanyInfo(SearchBean searchBean
            ){
       
            PageBean pageBean = new PageBean(searchBean, businApi);
            pageBean.addQuerySQL("select a.id, a.comCode, a.comName,a.comTel,a.comAddress,a.comState,a.createDate from SupCompanyInfo a where comState = ?");
            pageBean.addParams(Contexts.Y);
            pageBean.addQueryStr("a.comState", searchBean.getSearchName1(), false);
            pageBean.addQueryStr("a.comCode,a.comName", searchBean.getSearchName2(), true);
            pageBean.addQueryDate("a.createDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
            pageBean.addOrderBy("a.createDate", true);
            pageBean.query();
            model.addAttribute("pageBean", pageBean);
            
        return "/order/listCompanyInfo";
    }
    
    @RequestMapping(value="/listStatusOrder")
    public String listStatusOrder(SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        StringBuffer sql=new StringBuffer("SELECT o.obj_Id,o.obj_createDate,o.order_code,o.order_state,ft.flow_name,ci.com_Name ,suci.comName,o.node_date,o.node_state FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id  WHERE o.order_state!=5 ");
        
        if (!"88".equals(searchBean.getSearchName1())) {
            sql.append("and order_state=?");
            pageBean.addParams(searchBean.getSearchName1());
        }
        pageBean.addQuerySQL(sql.toString());
        pageBean.addQueryStr("o.order_code,ft.flow_name,supName,ci.com_Name ,suci.comName", searchBean.getSearchName2(), true);
        pageBean.addOrderBy("o.order_code", true);
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        return "/order/listStatusOrder";
    }
    
    /**
     * 客服对订单进行终止
     * 
     * @param taskId
     * @return
     */
    @RequestMapping("/terminationOrderFrom/{id}")
    @ResponseBody
    public void terminationOrderFrom(@PathVariable String id) {
        JSONArray jsonArray = new JSONArray();
        try {
           orderFromBusin.terminationOrderFrom(id);
           jsonArray.put(id);
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    /**
     * 首页订单数据点击查看详情进行跳转的页面
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping("/toPageDetailUrl/{orderFromId}")
    public String toPageDetailUrl(@PathVariable String orderFromId){
        StringBuffer url=new StringBuffer();
        OrderFrom orderFrom = orderFromBusin.getOrderFrom(orderFromId);
        if (orderFrom!=null&&orderFrom.getFlowType()!=null) {
            url.append("redirect:");
            String typeId = orderFrom.getFlowType().getId();
            VocationalWorkEnum bgImcc = VocationalWorkEnum.BG_IMCC;
            VocationalWorkEnum bgExcc = VocationalWorkEnum.BG_EXCC;
            VocationalWorkEnum wmImcc = VocationalWorkEnum.WM_IMCC;
            VocationalWorkEnum wmExcc = VocationalWorkEnum.WM_EXCC;
            VocationalWorkEnum wlImcc = VocationalWorkEnum.WL_IMCC;
            VocationalWorkEnum wlExcc = VocationalWorkEnum.WL_EXCC;
            VocationalWorkEnum wlTransport = VocationalWorkEnum.WL_TRANSPORT;
            VocationalWorkEnum ccImcc = VocationalWorkEnum.CC_IMCC;
            VocationalWorkEnum ccExcc = VocationalWorkEnum.CC_EXCC;
            if (StringUtil.isEmpty(typeId, bgImcc.getId())) {
                url.append("/pm/order/CustomsDeAgreementimcc/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, bgExcc.getId())) {
                url.append("/pm/order/ExportCustomsDeAgreementexcc/viewExportCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, wmImcc.getId())) {
                url.append("/pm/order/ex/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, wmExcc.getId())) {
                url.append("/pm/order/ic/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, wlImcc.getId())) {
                url.append("/pm/wuliuimcc/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, wlExcc.getId())) {
                url.append("/pm/wuliuexcc/viewShippingExCommission/");
            } else if (StringUtil.isEmpty(typeId, wlTransport.getId())) {
                url.append("/pm/wuliutrop/viewShippingExCommission/");
            } else if (StringUtil.isEmpty(typeId, ccImcc.getId())) {
                url.append("/pm/order/OrderFrom/viewCustomsDeAgreement/");
            } else if (StringUtil.isEmpty(typeId, ccExcc.getId())) {
                url.append("/pm/order/ExportOrderFrom/viewCustomsDeAgreement/");
            }
            url.append(orderFromId);
        }
        return url.toString();
    }
    
    /**
     * 平台订单统计表
     * @return
     */
    @RequestMapping("/reportFormStatistics")
    public String reportFormStatistics(String name,String supName,String startDate,String endDate){
        //flag -1:提前 0:按时 1: 延迟 2:同类型的全部订单
        try {
            DecimalFormat   format   =   new   DecimalFormat("##0.00"); 
            VocationalWorkEnum bgIc = VocationalWorkEnum.BG_IMCC;
            int bgIcCount=orderFromBusin.getOrderStatusCount(bgIc.getId(),"2",name,supName,startDate,endDate);
            int bgIcCountTQ=orderFromBusin.getOrderStatusCount(bgIc.getId(),"-1",name,supName,startDate,endDate);
            int bgIcCountAS=orderFromBusin.getOrderStatusCount(bgIc.getId(),"0",name,supName,startDate,endDate);
            int bgIcCountYC=orderFromBusin.getOrderStatusCount(bgIc.getId(),"1",name,supName,startDate,endDate);
            if (bgIcCount!=0) {
                model.addAttribute("bgIcCountTQ1", format.format((bgIcCountTQ / (double) bgIcCount) * 100) + "%");
                model.addAttribute("bgIcCountAS1", format.format((bgIcCountAS / (double) bgIcCount) * 100) + "%");
                model.addAttribute("bgIcCountYC1", format.format((bgIcCountYC / (double) bgIcCount) * 100) + "%");
            }else {
                model.addAttribute("bgIcCountTQ1", "0%");
                model.addAttribute("bgIcCountAS1", "0%");
                model.addAttribute("bgIcCountYC1", "0%");
            }
            model.addAttribute("bgIcCount", bgIcCount);
            model.addAttribute("bgIcCountTQ2", bgIcCountTQ);
            model.addAttribute("bgIcCountAS2", bgIcCountAS);
            model.addAttribute("bgIcCountYC2", bgIcCountYC);
            
            VocationalWorkEnum bgEc = VocationalWorkEnum.BG_EXCC;
            int bgEcCount=orderFromBusin.getOrderStatusCount(bgEc.getId(),"2",name,supName,startDate,endDate);
            int bgEcCountTQ=orderFromBusin.getOrderStatusCount(bgEc.getId(),"-1",name,supName,startDate,endDate);
            int bgEcCountAS=orderFromBusin.getOrderStatusCount(bgEc.getId(),"0",name,supName,startDate,endDate);
            int bgEcCountYC=orderFromBusin.getOrderStatusCount(bgEc.getId(),"1",name,supName,startDate,endDate);
            if (bgEcCount!=0) {
                model.addAttribute("bgEcCountTQ1", format.format((bgEcCountTQ / (double) bgEcCount) * 100) + "%");
                model.addAttribute("bgEcCountAS1", format.format((bgEcCountAS / (double) bgEcCount) * 100) + "%");
                model.addAttribute("bgEcCountYC1", format.format((bgEcCountYC / (double) bgEcCount) * 100) + "%");
            }else {
                model.addAttribute("bgEcCountTQ1", "0%");
                model.addAttribute("bgEcCountAS1", "0%");
                model.addAttribute("bgEcCountYC1", "0%");
            }
            model.addAttribute("bgEcCount", bgEcCount);
            model.addAttribute("bgEcCountTQ2", bgEcCountTQ);
            model.addAttribute("bgEcCountAS2", bgEcCountAS);
            model.addAttribute("bgEcCountYC2", bgEcCountYC);
            
            VocationalWorkEnum wmIc = VocationalWorkEnum.WM_IMCC;
            int wmIcCount=orderFromBusin.getOrderStatusCount(wmIc.getId(),"2",name,supName,startDate,endDate);
            int wmIcCountTQ=orderFromBusin.getOrderStatusCount(wmIc.getId(),"-1",name,supName,startDate,endDate);
            int wmIcCountAS=orderFromBusin.getOrderStatusCount(wmIc.getId(),"0",name,supName,startDate,endDate);
            int wmIcCountYC=orderFromBusin.getOrderStatusCount(wmIc.getId(),"1",name,supName,startDate,endDate);
            if (wmIcCount!=0) {
                model.addAttribute("wmIcCountTQ1", format.format((wmIcCountTQ / (double) wmIcCount) * 100) + "%");
                model.addAttribute("wmIcCountAS1", format.format((wmIcCountAS / (double) wmIcCount) * 100) + "%");
                model.addAttribute("wmIcCountYC1", format.format((wmIcCountYC / (double) wmIcCount) * 100) + "%");
            }else {
                model.addAttribute("wmIcCountTQ1", "0%");
                model.addAttribute("wmIcCountAS1", "0%");
                model.addAttribute("wmIcCountYC1", "0%");
            }
            model.addAttribute("wmIcCount", wmIcCount);
            model.addAttribute("wmIcCountTQ2", wmIcCountTQ);
            model.addAttribute("wmIcCountAS2", wmIcCountAS);
            model.addAttribute("wmIcCountYC2", wmIcCountYC);
            
            VocationalWorkEnum wmEc = VocationalWorkEnum.WM_EXCC;
            int wmEcCount=orderFromBusin.getOrderStatusCount(wmEc.getId(),"2",name,supName,startDate,endDate);
            int wmEcCountTQ=orderFromBusin.getOrderStatusCount(wmEc.getId(),"-1",name,supName,startDate,endDate);
            int wmEcCountAS=orderFromBusin.getOrderStatusCount(wmEc.getId(),"0",name,supName,startDate,endDate);
            int wmEcCountYC=orderFromBusin.getOrderStatusCount(wmEc.getId(),"1",name,supName,startDate,endDate);
            if (wmEcCount!=0) {
                model.addAttribute("wmEcCountTQ1", format.format((wmEcCountTQ / (double) wmEcCount) * 100) + "%");
                model.addAttribute("wmEcCountAS1", format.format((wmEcCountAS / (double) wmEcCount) * 100) + "%");
                model.addAttribute("wmEcCountYC1", format.format((wmEcCountYC / (double) wmEcCount) * 100) + "%");
               
            }else {
                model.addAttribute("wmEcCountTQ1", "0%");
                model.addAttribute("wmEcCountAS1", "0%");
                model.addAttribute("wmEcCountYC1", "0%");
            }
            model.addAttribute("wmEcCount", wmEcCount);
            model.addAttribute("wmEcCountTQ2", wmEcCountTQ);
            model.addAttribute("wmEcCountAS2", wmEcCountAS);
            model.addAttribute("wmEcCountYC2", wmEcCountYC);
            
            VocationalWorkEnum wlIc = VocationalWorkEnum.WL_IMCC;
            int wlIcCount=orderFromBusin.getOrderStatusCount(wlIc.getId(),"2",name,supName,startDate,endDate);
            int wlIcCountTQ=orderFromBusin.getOrderStatusCount(wlIc.getId(),"-1",name,supName,startDate,endDate);
            int wlIcCountAS=orderFromBusin.getOrderStatusCount(wlIc.getId(),"0",name,supName,startDate,endDate);
            int wlIcCountYC=orderFromBusin.getOrderStatusCount(wlIc.getId(),"1",name,supName,startDate,endDate);
            if (wlIcCount!=0) {
                model.addAttribute("wlIcCountTQ1", format.format((wlIcCountTQ / (double) wlIcCount) * 100) + "%");
                model.addAttribute("wlIcCountAS1", format.format((wlIcCountAS / (double) wlIcCount) * 100) + "%");
                model.addAttribute("wlIcCountYC1", format.format((wlIcCountYC / (double) wlIcCount) * 100) + "%");
            }else {
                model.addAttribute("wlIcCountTQ1", "0%");
                model.addAttribute("wlIcCountAS1", "0%");
                model.addAttribute("wlIcCountYC1", "0%");
            }
            model.addAttribute("wlIcCount", wlIcCount);
            model.addAttribute("wlIcCountTQ2", wlIcCountTQ);
            model.addAttribute("wlIcCountAS2", wlIcCountAS);
            model.addAttribute("wlIcCountYC2", wlIcCountYC);
            
            VocationalWorkEnum wlEc = VocationalWorkEnum.WL_EXCC;
            int wlEcCount=orderFromBusin.getOrderStatusCount(wlEc.getId(),"2",name,supName,startDate,endDate);
            int wlEcCountTQ=orderFromBusin.getOrderStatusCount(wlEc.getId(),"-1",name,supName,startDate,endDate);
            int wlEcCountAS=orderFromBusin.getOrderStatusCount(wlEc.getId(),"0",name,supName,startDate,endDate);
            int wlEcCountYC=orderFromBusin.getOrderStatusCount(wlEc.getId(),"1",name,supName,startDate,endDate);
            if (wlEcCount!=0) {    
                model.addAttribute("wlEcCountTQ1",format.format((wlEcCountTQ/(double)wlEcCount)*100)+"%");
                model.addAttribute("wlEcCountAS1",format.format((wlEcCountAS/(double)wlEcCount)*100)+"%");
                model.addAttribute("wlEcCountYC1",format.format((wlEcCountYC/(double)wlEcCount)*100)+"%");
            }else {
                model.addAttribute("wlEcCountTQ1","0%");
                model.addAttribute("wlEcCountAS1","0%");
                model.addAttribute("wlEcCountYC1","0%");
            }
            model.addAttribute("wlEcCount",wlEcCount );
            model.addAttribute("wlEcCountAS2",wlEcCountAS);
            model.addAttribute("wlEcCountTQ2",wlEcCountTQ);
            model.addAttribute("wlEcCountYC2",wlEcCountYC);
            
            VocationalWorkEnum wlTransport = VocationalWorkEnum.WL_TRANSPORT;
            int wlTransportCount=orderFromBusin.getOrderStatusCount(wlTransport.getId(),"2",name,supName,startDate,endDate);
            int wlTransportCountTQ=orderFromBusin.getOrderStatusCount(wlTransport.getId(),"-1",name,supName,startDate,endDate);
            int wlTransportCountAS=orderFromBusin.getOrderStatusCount(wlTransport.getId(),"0",name,supName,startDate,endDate);
            int wlTransportCountYC=orderFromBusin.getOrderStatusCount(wlTransport.getId(),"1",name,supName,startDate,endDate);
            if (wlTransportCount!=0) {
                model.addAttribute("wlTransportCountTQ1",format.format((wlTransportCountTQ/(double)wlTransportCount)*100)+"%");
                model.addAttribute("wlTransportCountAS1",format.format((wlTransportCountAS/(double)wlTransportCount)*100)+"%");
                model.addAttribute("wlTransportCountYC1",format.format((wlTransportCountYC/(double)wlTransportCount)*100)+"%");
            }else{
                model.addAttribute("wlTransportCountTQ1","0%");
                model.addAttribute("wlTransportCountAS1","0%");
                model.addAttribute("wlTransportCountYC1","0%");
            }
            model.addAttribute("wlTransportCount",wlTransportCount );
            model.addAttribute("wlTransportCountTQ2",wlTransportCountTQ);
            model.addAttribute("wlTransportCountAS2",wlTransportCountAS);
            model.addAttribute("wlTransportCountYC2",wlTransportCountYC);
            
            VocationalWorkEnum ccIc = VocationalWorkEnum.CC_IMCC;
            int ccIcCount=orderFromBusin.getOrderStatusCount(ccIc.getId(),"2",name,supName,startDate,endDate);
            int ccIcCountTQ=orderFromBusin.getOrderStatusCount(ccIc.getId(),"-1",name,supName,startDate,endDate);
            int ccIcCountAS=orderFromBusin.getOrderStatusCount(ccIc.getId(),"0",name,supName,startDate,endDate);
            int ccIcCountYC=orderFromBusin.getOrderStatusCount(ccIc.getId(),"1",name,supName,startDate,endDate);
            if (ccIcCount!=0) {
                model.addAttribute("ccIcCountTQ1",format.format((ccIcCountTQ/(double)ccIcCount)*100)+"%");
                model.addAttribute("ccIcCountAS1",format.format((ccIcCountAS/(double)ccIcCount)*100)+"%");
                model.addAttribute("ccIcCountYC1",format.format((ccIcCountYC/(double)ccIcCount)*100)+"%");
            }else{
                model.addAttribute("ccIcCountTQ1","0%");
                model.addAttribute("ccIcCountAS1","0%");
                model.addAttribute("ccIcCountYC1","0%");
            }
            model.addAttribute("ccIcCount",ccIcCount );
            model.addAttribute("ccIcCountTQ2",ccIcCountTQ);
            model.addAttribute("ccIcCountAS2",ccIcCountAS);
            model.addAttribute("ccIcCountYC2",ccIcCountYC);
            
            VocationalWorkEnum ccEc = VocationalWorkEnum.CC_EXCC;
            int ccEcCount=orderFromBusin.getOrderStatusCount(ccEc.getId(),"2",name,supName,startDate,endDate);
            int ccEcCountTQ=orderFromBusin.getOrderStatusCount(ccEc.getId(),"-1",name,supName,startDate,endDate);
            int ccEcCountAS=orderFromBusin.getOrderStatusCount(ccEc.getId(),"0",name,supName,startDate,endDate);
            int ccEcCountYC=orderFromBusin.getOrderStatusCount(ccEc.getId(),"1",name,supName,startDate,endDate);
            if (ccEcCount!=0) {
                model.addAttribute("ccEcCountTQ1",format.format((ccEcCountTQ/(double)ccEcCount)*100)+"%");
                model.addAttribute("ccEcCountAS1",format.format((ccEcCountAS/(double)ccEcCount)*100)+"%");
                model.addAttribute("ccEcCountYC1",format.format((ccEcCountYC/(double)ccEcCount)*100)+"%");
            }else{
                model.addAttribute("ccEcCountTQ1","0%");
                model.addAttribute("ccEcCountAS1","0%");
                model.addAttribute("ccEcCountYC1","0%");
            }
            model.addAttribute("ccEcCount",ccEcCount );
            model.addAttribute("ccEcCountYC2",ccEcCountYC);
            model.addAttribute("ccEcCountTQ2",ccEcCountTQ);
            model.addAttribute("ccEcCountAS2",ccEcCountAS);
            
            model.addAttribute("bgIc", bgIc.getName());
            model.addAttribute("bgEc", bgEc.getName());
            model.addAttribute("wmIc", wmIc.getName());
            model.addAttribute("wmEc", wmEc.getName());
            model.addAttribute("wlIc", wlIc.getName());
            model.addAttribute("wlEc", wlEc.getName());
            model.addAttribute("wlTransport", wlTransport.getName());
            model.addAttribute("ccIc", ccIc.getName());
            model.addAttribute("ccEc", ccEc.getName());

            model.addAttribute("bgIcId", bgIc.getId());
            model.addAttribute("bgEcId", bgEc.getId());
            model.addAttribute("wmIcId", wmIc.getId());
            model.addAttribute("wmEcId", wmEc.getId());
            model.addAttribute("wlIcId", wlIc.getId());
            model.addAttribute("wlEcId", wlEc.getId());
            model.addAttribute("wlTransportId", wlTransport.getId());
            model.addAttribute("ccIcId", ccIc.getId());
            model.addAttribute("ccEcId", ccEc.getId());
            
        } catch (Exception e) {
        }
        model.addAttribute("endDate",endDate );
        model.addAttribute("startDate",startDate );
        model.addAttribute("name",name );
        model.addAttribute("supName",supName );
        return "/order/reportFormStatistics";
    }
    @RequestMapping("/listReportFormStatistics/{typeId}/{flag}/{name}/{supName}/{startDate}/{endDate}")
    public String listReportFormStatistics(SearchBean searchBean,@PathVariable String typeId,@PathVariable String flag
            ,@PathVariable String name,@PathVariable String supName,@PathVariable String startDate,@PathVariable String endDate
            ){
        PageBean pageBean = new PageBean(searchBean, businApi);
       StringBuffer sql=new StringBuffer();
       sql.append("SELECT o.order_code,ci.com_Name,suci.comName,o.obj_createDate,o.project_accomplish_date,o.accomplish_date ");
       sql.append("FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ");
       sql.append("ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t  ");
       sql.append("ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci  ");
       sql.append("ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci  ");
       sql.append("ON o.supplier=suci.obj_Id   ");
       sql.append("WHERE o.order_state!=5 AND o.accomplish_date IS NOT NULL  ");
       sql.append("AND o.tack_type_id=?  ");
       pageBean.addParams(typeId);
       String statusName="";
     //flag -1:提前 0:按时 1: 延迟 2:同类型的全部订单
       switch (flag) {
       case "-1":
           sql.append(" AND convert(varchar(10),accomplish_date,121)<convert(varchar(10),project_accomplish_date,121)");
           statusName="提前";
           break;
       case "0":
           sql.append(" AND convert(varchar(10),accomplish_date,121)=convert(varchar(10),project_accomplish_date,121)");
           statusName="按时";
           break;
       case "1":
           sql.append(" AND convert(varchar(10),accomplish_date,121)>convert(varchar(10),project_accomplish_date,121)");
           statusName="延迟";
           break;
       }
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       if (name!=null&&!"".equals(name)&&!"0".equals(name)) {
           sql.append(" AND ci.com_Name=?" );
           pageBean.addParams(name);
       }
       if (supName!=null&&!"".equals(supName)&&!"0".equals(supName)) {
           sql.append(" AND suci.comName=? ");
           pageBean.addParams(supName);
       }
       if (startDate!=null&&!"".equals(startDate)&&!"0".equals(startDate)) {
           try {
               Date date=sdf.parse(startDate);
               sql.append(" AND o.obj_createDate >=?");
               pageBean.addParams(date);
           } catch (ParseException e) {
           }
          
       }
       if (endDate!=null&&!"".equals(endDate)&&!"0".equals(endDate)) {
           try {
               Date date=sdf.parse(endDate);
               sql.append(" AND o.obj_createDate<=?");
               pageBean.addParams(date);
           } catch (ParseException e) {
               e.printStackTrace();
           }
       }
       
       
        pageBean.addQuerySQL(sql.toString());
        pageBean.addQueryStr("o.order_code,ci.com_Name,suci.comName", searchBean.getSearchName1(), true);
        pageBean.addOrderBy("o.order_code", true);
        pageBean.sqlquerys();
        model.addAttribute("statusName", statusName);
        model.addAttribute("pageBean", pageBean);
        return "/order/listReportFormStatistics";
    }
    /**
     * 平台用户统计表
     * @return
     */
    @RequestMapping("/listUserReportStatistics/{valueDate}")
    public String userReportStatistics(Date startDate,Date endDate,@PathVariable String valueDate){
        List<String> listDate =new ArrayList<String>();
        Date date=DateUtil.getDate();
        Date sDate=null;
        if (startDate!=null&&endDate!=null) {
            model.addAttribute("startDate", DateUtil.getStringDate(startDate, "yyyy-MM-dd"));
            model.addAttribute("endDate", DateUtil.getStringDate(endDate, "yyyy-MM-dd"));
        }
        List<Integer> newRegisterMemberList=new ArrayList<Integer>();
        List<Integer> newCheckMemberList=new ArrayList<Integer>();
        List<Integer> orderMemberNumList=new ArrayList<Integer>();
        switch (valueDate) {
        case "0":
            listDate = DateUtil.getDatesBetweenTwoDate(startDate, endDate);
            newRegisterMemberList=companyInfoBusin.getNewRegisterMember(startDate,endDate);
            newCheckMemberList=companyInfoBusin.getNewCheckMember(startDate,endDate);
            orderMemberNumList=orderFromBusin.getOrderMemberNum(startDate,endDate);
            break;
        case "1":
            sDate=DateUtil.formatDate(DateUtil.getDate(DateUtil.getLastMonth(DateUtil.getDate(),-1)), "yyyy-MM-dd");
            listDate = DateUtil.getDatesBetweenTwoDate(sDate , date );
            newRegisterMemberList=companyInfoBusin.getNewRegisterMember(sDate,date);
            newCheckMemberList=companyInfoBusin.getNewCheckMember(sDate,date);
            orderMemberNumList=orderFromBusin.getOrderMemberNum(sDate,date);
            break;
        case "2":
             sDate=DateUtil.formatDate(DateUtil.getDate(DateUtil.getLastMonth(DateUtil.getDate(),-2)), "yyyy-MM-dd");
            listDate = DateUtil.getDatesBetweenTwoDate( sDate, date);
            newRegisterMemberList=companyInfoBusin.getNewRegisterMember(sDate,date);
            newCheckMemberList=companyInfoBusin.getNewCheckMember(sDate,date);
            orderMemberNumList=orderFromBusin.getOrderMemberNum(sDate,date);
            break;
        case "3":
            sDate=DateUtil.formatDate(DateUtil.getDate(DateUtil.getLastMonth(DateUtil.getDate(),-3)), "yyyy-MM-dd");
            listDate = DateUtil.getDatesBetweenTwoDate( sDate, date);
            newRegisterMemberList=companyInfoBusin.getNewRegisterMember(sDate,date);
            newCheckMemberList=companyInfoBusin.getNewCheckMember(sDate,date);
            orderMemberNumList=orderFromBusin.getOrderMemberNum(sDate,date);
            break;
        }
        model.addAttribute("valueDate", valueDate);
        model.addAttribute("orderMemberNum", orderMemberNumList);
        model.addAttribute("newRegisterMember", newRegisterMemberList);
        model.addAttribute("newCheckMember", newCheckMemberList);
        model.addAttribute("listDate", listDate);
        
        return "/order/userReportStatistics";
    }
    
    @SuppressWarnings("unused")
    @RequestMapping("/getOrderExecl/{typeId}")
    public void getWachatExpInfoListAll(@PathVariable String typeId,String orderCode,String startDate,String endDate,String supName,String name){
        PrintWriter pw = null;
        OutputStream os = null;
        try {
           
            VocationalWorkEnum bgImcc = VocationalWorkEnum.BG_IMCC;
            VocationalWorkEnum bgExcc = VocationalWorkEnum.BG_EXCC;
            VocationalWorkEnum wmImcc = VocationalWorkEnum.WM_IMCC;
            VocationalWorkEnum wmExcc = VocationalWorkEnum.WM_EXCC;
            VocationalWorkEnum wlImcc = VocationalWorkEnum.WL_IMCC;
            VocationalWorkEnum wlExcc = VocationalWorkEnum.WL_EXCC;
            VocationalWorkEnum ccImcc = VocationalWorkEnum.CC_IMCC;
            VocationalWorkEnum ccExcc = VocationalWorkEnum.CC_EXCC;
            
            List<String> listTitle = new ArrayList<String>();
            StringBuffer sheetNameSb = new StringBuffer();
            sheetNameSb.append("平台统计报表");
            response.setCharacterEncoding("gbk");
            Date date = new Date();
            Workbook wb = null;
            AjaxDomain ajaxDomain = new AjaxDomain();
            ajaxDomain.setStatusCode("200");
            String fileName;
            if (StringUtil.isEmpty(typeId, bgExcc.getId())) {
                List<BgEcExpDomain> listContents = orderFromBusin.getBgEmccOrderExecl(typeId,orderCode,startDate,endDate,supName,name);
                listTitle.add("客户名称");
                listTitle.add("业务流水号");
                listTitle.add("业务类型");
                listTitle.add("报关单号");
                listTitle.add("提运单号");
                listTitle.add("船名/航次");
               // listTitle.add("运输工具");
                listTitle.add("品名");
                listTitle.add("件数");
                listTitle.add("毛重");
               // listTitle.add("体积");
                listTitle.add("接单时间");
                listTitle.add("查验时间");
                listTitle.add("放行时间");
                listTitle.add("预期完成日期");
             //   listTitle.add("经营单位");
                fileName= "报关出口订单-"+sdf1.format(date) + ".xls";
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.getBytes("gbk"), "ISO-8859-1"));
                String sheetName = sheetNameSb.toString();
                Object[] oarr = listTitle.toArray();
                int contentBeginRowIndex = 1;
                wb = PoiExcelUtils.outPutExcelBgEc(oarr,listContents, contentBeginRowIndex,sheetName);
            } else if (StringUtil.isEmpty(typeId, bgImcc.getId())) {
         
                List<BgIcExpDomain> listContents = orderFromBusin.getBgImccOrderExecl(typeId,orderCode,startDate,endDate,supName,name);
               
                listTitle.add("客户名称");
                listTitle.add("业务流水号");
                listTitle.add("业务类型");
                listTitle.add("报关单号");
                listTitle.add("提运单号");
                listTitle.add("船名/航次");
                //listTitle.add("运输工具");
                listTitle.add("品名");
                listTitle.add("件数");
                listTitle.add("毛重");
               // listTitle.add("体积");
                listTitle.add("接单时间");
                listTitle.add("查验时间");
                listTitle.add("放行时间");
                listTitle.add("预期完成日期");
               // listTitle.add("经营单位");
                 
                fileName= "报关进口订单-"+sdf1.format(date) + ".xls";
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.getBytes("gbk"), "ISO-8859-1"));
                String sheetName = sheetNameSb.toString();
                Object[] oarr = listTitle.toArray();
                int contentBeginRowIndex = 1;
                wb = PoiExcelUtils.outPutExcelBgIc(oarr,listContents, contentBeginRowIndex,sheetName);
              
            } else if (StringUtil.isEmpty(typeId, wmImcc.getId())) {
                
              
                listTitle.add("客户名称");
                listTitle.add("业务流水号");
                listTitle.add("业务类型");
                listTitle.add("品名");
                listTitle.add("合同号");
                listTitle.add("合同币值");
                listTitle.add("合同金额");
                listTitle.add("单证制作时间");
                listTitle.add("信用证开证时间");
                listTitle.add("收货款时间");
                listTitle.add("付货款时间");
                listTitle.add("进口清关时间");
                listTitle.add("业务结算时间");
                listTitle.add("预期完成日期");
                
                List<WmIcExpDomain> listContents = new ArrayList<WmIcExpDomain>();
               
                fileName= "外贸进口订单-"+sdf1.format(date) + ".xls";
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.getBytes("gbk"), "ISO-8859-1"));
                String sheetName = sheetNameSb.toString();
                Object[] oarr = listTitle.toArray();
                int contentBeginRowIndex = 1;
                wb = PoiExcelUtils.outPutExcelWmIc(oarr,listContents, contentBeginRowIndex,sheetName);
            } else if (StringUtil.isEmpty(typeId, wmExcc.getId())) {
               
                listTitle.add("客户名称");
                listTitle.add("业务流水号");
                listTitle.add("业务类型");
                listTitle.add("品名");
                listTitle.add("合同号");
                listTitle.add("合同币值");
                listTitle.add("合同金额");
                listTitle.add("单证制作时间");
                listTitle.add("信用证开证时间");
                listTitle.add("收汇时间");
                listTitle.add("出口清关时间");
                listTitle.add("结算开票时间");
                listTitle.add("退税申请时间");
                listTitle.add("预期完成日期");
                
                List<WmEcExpDomain> listContents = orderFromBusin.getWmEmccOrderExecl(typeId, orderCode, startDate, endDate,supName,name);
               
                fileName= "外贸出口订单-"+sdf1.format(date) + ".xls";
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.getBytes("gbk"), "ISO-8859-1"));
                String sheetName = sheetNameSb.toString();
                Object[] oarr = listTitle.toArray();
                int contentBeginRowIndex = 1;
                wb = PoiExcelUtils.outPutExcelWmEc(oarr,listContents, contentBeginRowIndex,sheetName);
                
            } else if (StringUtil.isEmpty(typeId, wlExcc.getId())) {
               
                listTitle.add("客户名称");
                listTitle.add("业务流水号");
                listTitle.add("业务类型");
                listTitle.add("品名");
                listTitle.add("件数");
                listTitle.add("毛重");
                listTitle.add("体积");
                listTitle.add("送货地址");
                listTitle.add("接单日期");
                listTitle.add("报关放行日期");
                listTitle.add("送货日期");
                //listTitle.add("经营单位");
                listTitle.add("预期完成日期");
                
                List<WlEcExpDomain> listContents=orderFromBusin.getWlEmccOrderExecl(typeId, orderCode, startDate, endDate,supName,name);
                fileName= "物理出口订单-"+sdf1.format(date) + ".xls";
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.getBytes("gbk"), "ISO-8859-1"));
                String sheetName = sheetNameSb.toString();
                Object[] oarr = listTitle.toArray();
                int contentBeginRowIndex = 1;
               
                wb = PoiExcelUtils.outPutExcelWlEc(oarr,listContents, contentBeginRowIndex,sheetName);
                
            } else if (StringUtil.isEmpty(typeId, wlImcc.getId())) {
                
                listTitle.add("客户名称");
                listTitle.add("业务流水号");
                listTitle.add("业务类型");
                listTitle.add("品名");
                listTitle.add("件数");
                listTitle.add("毛重");
                listTitle.add("体积");
                listTitle.add("送货地址");
                listTitle.add("接单日期");
                listTitle.add("换单日期");
                listTitle.add("报关放行日期");
                listTitle.add("送货日期");
                //listTitle.add("经营单位");
                listTitle.add("预期完成日期");
                
                List<WlIcExpDomain> listContents = orderFromBusin.getWlImccOrderExecl(typeId, orderCode, startDate, endDate,supName,name);
               
                fileName= "物流进口订单-"+sdf1.format(date) + ".xls";
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.getBytes("gbk"), "ISO-8859-1"));
                String sheetName = sheetNameSb.toString();
                Object[] oarr = listTitle.toArray();
                int contentBeginRowIndex = 1;
                wb = PoiExcelUtils.outPutExcelWlIc(oarr,listContents, contentBeginRowIndex,sheetName);
            
            } else if (StringUtil.isEmpty(typeId, ccImcc.getId())) {
               listTitle.add("客户名称");
                listTitle.add("业务流水号");
                listTitle.add("业务类型");
                listTitle.add("报关单号");
                listTitle.add("品名");
                listTitle.add("件数");
                listTitle.add("毛重");
                //listTitle.add("体积");
                listTitle.add("船名");
                listTitle.add("航次");
                listTitle.add("提运单号");
                listTitle.add("建单时间");
                listTitle.add("车辆到达时间");
                listTitle.add("入库签收时间");
                //listTitle.add("经营单位");
                listTitle.add("预期完成日期");
                List<CcIcExpDomain> listContents = orderFromBusin.getCcImccOrderExecl(typeId, orderCode, startDate, endDate,supName,name);
                fileName= "仓储入库订单-"+sdf1.format(date) + ".xls";
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.getBytes("gbk"), "ISO-8859-1"));
                String sheetName = sheetNameSb.toString();
                Object[] oarr = listTitle.toArray();
                int contentBeginRowIndex = 1;
                wb = PoiExcelUtils.outPutExcelCcIc(oarr,listContents, contentBeginRowIndex,sheetName);
            
            } else if (StringUtil.isEmpty(typeId, ccExcc.getId())) {
                listTitle.add("客户名称");
                listTitle.add("业务流水号");
                listTitle.add("业务类型");
                listTitle.add("报关单号");
                listTitle.add("品名");
                listTitle.add("件数");
                listTitle.add("毛重");
                //listTitle.add("体积");
                listTitle.add("船名");
                listTitle.add("航次");
                listTitle.add("提运单号");
                listTitle.add("建单时间");
                listTitle.add("车辆到达时间");
                listTitle.add("出库司机签收时间");
                //listTitle.add("经营单位");
                listTitle.add("预期完成日期");
                List<CcEcExpDomain> listContents = orderFromBusin.getCcEmccOrderExecl(typeId, orderCode, startDate, endDate,supName,name);
                fileName= "仓储出库订单-"+sdf1.format(date) + ".xls";
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.getBytes("gbk"), "ISO-8859-1"));
                String sheetName = sheetNameSb.toString();
                Object[] oarr = listTitle.toArray();
                int contentBeginRowIndex = 1;
                wb = PoiExcelUtils.outPutExcelCcEc(oarr,listContents, contentBeginRowIndex,sheetName);
            }
            os = response.getOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
            
        }finally {
            try {
                if (os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping("/listorderadd/{orderid}")
    public String listorderadd(@PathVariable String orderid,SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.orderID, a.ordertype, a.createuser, a.createDate,a.content,o.orderCode from OrderAddtion a "
                + "  inner join OrderFrom o on o.id=a.orderID where  orderID = ?");
        pageBean.addParams(orderid);
        pageBean.addOrderBy("a.createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/company/info/listCompanyInfo";
    }
}