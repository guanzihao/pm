package com.pm.task.controller;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.Contexts;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.model.TaskState;
import com.pm.core.util.DateUtil;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.TreeModel;
import com.pm.order.busin.OrderFromBusin;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.sysconfig.busin.EnumBusin;
import com.pm.task.bean.Task;
import com.pm.task.busin.TaskBusin;
import com.sup.flow.bean.FlowType;
import com.sup.order.bean.CustomsDeAgreement;
import com.sup.order.bean.ExportContract;
import com.sup.order.bean.ExportCustomsDeAgreement;
import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.ExportShippingExCommission;
import com.sup.order.bean.ImportContract;
import com.sup.order.bean.PurchaseOrderProduction;
import com.sup.order.bean.ShippingExCommission;
import com.sup.order.bean.TropShippingExCommission;

import net.sf.json.JSONObject;
@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/task")
public class TaskController extends OrganizeBaseController {

    @Autowired
    private TaskBusin taskBusin;
    
    @Autowired
    private CompanyInfoBusin companyInfoBusin;
    
    @Autowired
    private EnumBusin enumBusin;
    
    @Autowired
    private OrderFromBusin orderFromBusin;
  
    /**
     * 查询所有的任务
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping("/listTask")
    public String listTask(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("SELECT  a.id,a.taskName,a.issueDate,a.isIssue ,a.createDate,a.updateDate  FROM Task a");
        pageBean.addQueryStr("a.taskName", searchBean.getSearchName1(), true);
        pageBean.addOrderBy("a.createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/task/listTask";
    }
    
    /**
     * 查询所有的任务并对任务进行分配
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping("/listAllocationTask/{close}")
    public String listAllocationTaskAJ(SearchBean searchBean,@PathVariable String close) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="SELECT a.obj_Id,a.obj_createDate,a.order_state,a.order_code ,c.com_Name,b.issue_date,pt.flow_name" + 
                " from PM_OrderFrom a ,PM_Task b,PM_COMPANY_CompanyInfo c,PM_FlowType pt" + 
                " where a.task_id=b.obj_Id and b.user_id=c.obj_Id and pt.obj_Id=a.tack_type_id"
                + " and (a.order_state=? or a.order_state=?)";
        pageBean.addQuerySQL(sql);
        pageBean.addParams(TaskState.WFP);
        pageBean.addParams(TaskState.FWSYJJ);
        pageBean.addQueryStr("a.order_code", searchBean.getSearchName1(), true);
        pageBean.addOrderBy("a.order_code", true);
        pageBean.sqlquerys();
        model.addAttribute("pageBean", pageBean);
        if ("close".equals(close)) {
            model.addAttribute("close", close);
        }
        return "/task/listAllocationTask";
    }
    
    
    /**
     * 查看一个任务的详细信息
     * @param task
     * @return
     */
    
    @RequestMapping("/viewTask/{id}")
    public String viewTask(@PathVariable String id){
        Task task=(Task) businApi.get(Task.class, id);
        String flowTypeName="";
        if (task!=null) {
            String oldAllId = task.getTackType();
            String[] newAllId = oldAllId.split(",");
            
            for (String string : newAllId) {
                FlowType flowType=(FlowType) businApi.get(FlowType.class, string);
                flowTypeName+=flowType.getFlowName()+" ";
            }
        }
       model.addAttribute("task", task);
       model.addAttribute("flowTypeName", flowTypeName);
        return "/task/viewTask";
    }
    
    /**
     * 保存一个任务
     * @param task
     * @return
     */
    @RequestMapping("/saveTask")
    public String saveTask(Task task,String [] tackFiles,String userId){
        taskBusin.saveTask(task,userId);
        frameworkBusin.saveUploadFileOwner(task, tackFiles);
        return "redirect:/task/viewTask/"+task.getId();
    }
    
    /**
     * 提交一个任务信息
     * @param id
     */
    @RequestMapping("/submitTask/{id}")
    public String submitTask(@PathVariable String id) {
        
            orderFromBusin.saveTaskIdOrderFrom(id);
            taskBusin.submitTask(id);
       return "redirect:/task/viewTask/"+id;
    }
    
    /**
     * 删除一个人任务信息
     * 
     * @param ids IDS
     * @return removeOrgRole
     */
    @RequestMapping("/removeTask")
    @ResponseBody
    public void removeTask(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    taskBusin.removeTask(id);
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    /**
     * 添加任务初始化数据
     * @return
     */
    @RequestMapping("/initSzveTask/{id}")
    public String initSzveTask(@PathVariable String id){
        //查询供应商的枚举
        Enumtype enumType = enumBusin.getEnumtypeByName("供应商枚举信息");
        if(enumType != null){
            //查询出所有供应商的类型
            List<Enumitems> enumitems = enumBusin.getEnumitemList(enumType);
            for (Enumitems enumitems2 : enumitems) {
                //查询出不同类型的供应商
                VocationalWorkEnum WL=VocationalWorkEnum.WL;
                VocationalWorkEnum BG=VocationalWorkEnum.BG;
                VocationalWorkEnum WM=VocationalWorkEnum.WM;
                VocationalWorkEnum CC=VocationalWorkEnum.CC;
                //查询出不同类型的供应商
                /*if(enumitems2.getId() != null && enumitems2.getId().equals(WL.getId())){
                    List<SupCompanyInfo> supCompanyInfowuliu=companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                    model.addAttribute("supCompanyInfowuliu", supCompanyInfowuliu);
                }
                if(enumitems2.getId() != null && enumitems2.getId().equals(CC.getId())){
                    List<SupCompanyInfo> supCompanyInfocangchu=companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                    model.addAttribute("supCompanyInfocangchu", supCompanyInfocangchu);
                }
                if(enumitems2.getId() != null && enumitems2.getId().equals(BG.getId())){
                    List<SupCompanyInfo> supCompanyInfobaoguan=companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                    model.addAttribute("supCompanyInfobaoguan", supCompanyInfobaoguan);
                }
                if(enumitems2.getId() != null && enumitems2.getId().equals(WM.getId())){
                    List<SupCompanyInfo> supCompanyInfowaimao=companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                    model.addAttribute("supCompanyInfowaimao", supCompanyInfowaimao);
                }*/
            }
        }
        List<CompanyInfo> companyInfos = businApi.getList(CompanyInfo.class);
        model.addAttribute("companyInfos", companyInfos);
        List<FlowType> flowTypes = businApi.getList(FlowType.class);
        String taskId = taskBusin.getTaskId();
        Task task=(Task) businApi.get(Task.class, id);
        
        model.addAttribute("task", task);
        model.addAttribute("flowTypes", flowTypes);
        model.addAttribute("taskId", taskId);
        return "/task/editTask";
    }
    
    /**
     * 得到所有的客户
     * @return
     */
    @RequestMapping("/listCompanyInfo")
   public String listCompanyInfo(SearchBean searchBean){
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.comCode, a.comName, a.comState, a.createDate from CompanyInfo a where comState = ? ");
        pageBean.addParams(Contexts.Y);
        pageBean.addQueryStr("a.comState", searchBean.getSearchName1(), false);
        pageBean.addQueryStr("a.comCode,a.comName", searchBean.getSearchName2(), true);
        pageBean.addQueryDate("a.createDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/task/listCompanyInfo";
   }
}
