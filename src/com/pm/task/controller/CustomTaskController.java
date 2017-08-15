package com.pm.task.controller;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.DateUtil;
import com.pm.core.util.StringUtil;
import com.pm.organize.controller.OrganizeBaseController;

/**
 * 客服的任务列表,订单列表,票据列表
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/customtask")
public class CustomTaskController extends OrganizeBaseController {
    /**
     * 分页显示客服票据列表
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCustomImccList")
    public String findCustomImccCustomTask(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql = "SELECT obj_Id,task_id,task_name,com_Name,issue_date,is_issue from v_accountbill";
        StringBuffer select = new StringBuffer(sql);
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            select.append(" where is_issue=?");
        }
        pageBean.addQuerySQL(select.toString());
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            pageBean.addParams(searchBean.getSearchName1());
        }
        Calendar a = Calendar.getInstance();
        String currentYear = String.valueOf(a.get(Calendar.YEAR));
        if (!StringUtil.isEmpty(searchBean.getSearchDate1())) {
            if (currentYear.equals(searchBean.getSearchDate1())) {
                pageBean.addQueryDate("issue_date", currentYear + "-01-01", currentYear + "-12-31");
            } else {
                Integer month = Integer.parseInt(searchBean.getSearchDate1());
                if (month < 9) {
                    String startDate = currentYear + "-0" + searchBean.getSearchDate1() + "-01";
                    String dates = DateUtil.getLastDate(a.get(Calendar.YEAR), month).substring(8, 10);
                    String endDate = currentYear + "-0" + searchBean.getSearchDate1() + "-" + dates;
                    pageBean.addQueryDate("issue_date", startDate, endDate);
                } else {
                    pageBean.addQueryDate("issue_date", currentYear + "-" + searchBean.getSearchDate1() + "-01", currentYear + "-" + searchBean.getSearchDate1() + "-" + DateUtil.getLastDate(a.get(Calendar.YEAR), month));
                }
            }
        }
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/task/customImcc";

    }

    /**
     * 查询客服订单列表
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCustomOrderList")
    public String findCustomOrder(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql = "SELECT task.obj_Id,task.task_id,task.task_name,"
        + "com.com_Name,task.issue_date,task.is_issue"
        + " FROM PM_OrderFrom o" 
        + " left JOIN PM_Task task ON o.task_id=task.obj_Id" 
        + " left JOIN PM_COMPANY_CompanyInfo com ON task.user_id = com.obj_Id";
        //StringBuffer select = new StringBuffer(sql);
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            sql+=" where task.is_issue=?";
        }
        pageBean.addQuerySQL(sql);
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            pageBean.addParams(searchBean.getSearchName1());
        }
        Calendar a = Calendar.getInstance();
        String currentYear = String.valueOf(a.get(Calendar.YEAR));
        if (!StringUtil.isEmpty(searchBean.getSearchDate1())) {
            if (currentYear.equals(searchBean.getSearchDate1())) {
                pageBean.addQueryDate("task.issue_date", currentYear + "-01-01", currentYear + "-12-31");
            } else {
                Integer month = Integer.parseInt(searchBean.getSearchDate1());
                if (month < 9) {
                    String startDate = currentYear + "-0" + searchBean.getSearchDate1() + "-01";
                    String dates = DateUtil.getLastDate(a.get(Calendar.YEAR), month).substring(8, 10);
                    String endDate = currentYear + "-0" + searchBean.getSearchDate1() + "-" + dates;
                    pageBean.addQueryDate("task.issue_date", startDate, endDate);
                } else {
                    pageBean.addQueryDate("task.issue_date", currentYear + "-" + searchBean.getSearchDate1() + "-01", currentYear + "-" + searchBean.getSearchDate1() + "-" + DateUtil.getLastDate(a.get(Calendar.YEAR), month));
                }
            }
        }
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/task/customOrder";
    }

    /**
     * 查询客服任务列表
     * 
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCustomTaskList")
    public String findCustomTask(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        String sql="SELECT task.obj_Id,task.task_id,task.task_name,"
                + "com.com_Name,task.issue_date,task.is_issue"
                + " FROM PM_Task task"
                + " left JOIN PM_COMPANY_CompanyInfo com ON task.user_id = com.obj_Id";
        StringBuffer select = new StringBuffer(sql);
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            select.append(" where task.is_issue=?");
        }
        pageBean.addQuerySQL(select.toString());
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            pageBean.addParams(searchBean.getSearchName1());
        }
        Calendar a = Calendar.getInstance();
        String currentYear = String.valueOf(a.get(Calendar.YEAR));
        if (!StringUtil.isEmpty(searchBean.getSearchDate1())) {
            if (currentYear.equals(searchBean.getSearchDate1())) {
                pageBean.addQueryDate("task.issue_date", currentYear + "-01-01", currentYear + "-12-31");
            } else {
                Integer month = Integer.parseInt(searchBean.getSearchDate1());
                if (month < 9) {
                    String startDate = currentYear + "-0" + searchBean.getSearchDate1() + "-01";
                    String dates = DateUtil.getLastDate(a.get(Calendar.YEAR), month).substring(8, 10);
                    String endDate = currentYear + "-0" + searchBean.getSearchDate1() + "-" + dates;
                    pageBean.addQueryDate("task.issue_date", startDate, endDate);
                } else {
                    pageBean.addQueryDate("task.issue_date", currentYear + "-" + searchBean.getSearchDate1() + "-01", currentYear + "-" + searchBean.getSearchDate1() + "-" + DateUtil.getLastDate(a.get(Calendar.YEAR), month));
                }
            }
        }
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/task/customTask";
    }
}
