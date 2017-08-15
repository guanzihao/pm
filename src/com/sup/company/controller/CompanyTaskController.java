package com.sup.company.controller;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.DateUtil;
import com.pm.core.util.StringUtil;

/**
 * 客户公司的任务列表,票据列表,订单列表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/sup/company/companytasklist")
public class CompanyTaskController extends SupBaseController {
    /**
     * 查询客户单据列表
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCompanyImccList") 
    public String findImccCompanyTask(SearchBean searchBean){
        PageBean pageBean =new PageBean(searchBean, businApi);
        String sql="SELECT obj_Id,task_id,task_name,com_Name,issue_date,is_issue from v_accountbill where user_id=?";
        StringBuffer select = new StringBuffer(sql);
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            select.append(" and is_issue=?");
        }
        pageBean.addQuerySQL(select.toString());
        CompanyInfoUser user=getCurrSup();
        pageBean.addParams(user.getCompanyInfo().getId());
        if(!StringUtil.isEmpty(searchBean.getSearchName1())){
            pageBean.addParams(searchBean.getSearchName1());
        }
        Calendar a=Calendar.getInstance();
        String currentYear=String.valueOf(a.get(Calendar.YEAR));
        if(!StringUtil.isEmpty(searchBean.getSearchDate1())){
            if(currentYear.equals(searchBean.getSearchDate1())){
                pageBean.addQueryDate("issue_date",currentYear+"-01-01",currentYear+"-12-31");
            }else{
                Integer month=Integer.parseInt(searchBean.getSearchDate1());
                if(month<9){
                    String startDate=currentYear+"-0"+searchBean.getSearchDate1()+"-01";
                    String dates=DateUtil.getLastDate(a.get(Calendar.YEAR),month).substring(8, 10);
                    String endDate=currentYear+"-0"+searchBean.getSearchDate1()+"-"+dates;
                    pageBean.addQueryDate("issue_date",startDate,endDate);
                }else{
                    pageBean.addQueryDate("issue_date",currentYear+"-"+searchBean.getSearchDate1()+"-01",currentYear+"-"+searchBean.getSearchDate1()+"-"+DateUtil.getLastDate(a.get(Calendar.YEAR),month));
                }
            }
        }
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/sup/company/company/companyImcc";
    }
    
    /**
     * 查询客户订单列表
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCompanyOrderList")
    public String findCompanyOrder(SearchBean searchBean){
        PageBean pageBean =new PageBean(searchBean, businApi);
        String sql="SELECT task.obj_Id,task.task_id,task.task_name,"
                  +" com.com_Name,task.issue_date,task.is_issue"
                  +" FROM PM_OrderFrom o "
                  +" inner join PM_Task task on o.task_id=task.obj_Id"
                  +" inner JOIN PM_COMPANY_CompanyInfo com ON task.user_id = com.obj_Id"
                  +" where task.user_id=?";
        StringBuffer select = new StringBuffer(sql);
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            select.append(" and task.is_issue=?");
        }
        pageBean.addQuerySQL(select.toString());
        CompanyInfoUser user=getCurrSup();
        pageBean.addParams(user.getCompanyInfo().getId());
        if(!StringUtil.isEmpty(searchBean.getSearchName1())){
            pageBean.addParams(searchBean.getSearchName1());
        }
        Calendar a=Calendar.getInstance();
        String currentYear=String.valueOf(a.get(Calendar.YEAR));
        if(!StringUtil.isEmpty(searchBean.getSearchDate1())){
            if(currentYear.equals(searchBean.getSearchDate1())){
                pageBean.addQueryDate("task.issue_date",currentYear+"-01-01",currentYear+"-12-31");
            }else{
                Integer month=Integer.parseInt(searchBean.getSearchDate1());
                if(month<9){
                    String startDate=currentYear+"-0"+searchBean.getSearchDate1()+"-01";
                    String dates=DateUtil.getLastDate(a.get(Calendar.YEAR),month).substring(8, 10);
                    String endDate=currentYear+"-0"+searchBean.getSearchDate1()+"-"+dates;
                    pageBean.addQueryDate("task.issue_date",startDate,endDate);
                }else{
                    pageBean.addQueryDate("task.issue_date",currentYear+"-"+searchBean.getSearchDate1()+"-01",currentYear+"-"+searchBean.getSearchDate1()+"-"+DateUtil.getLastDate(a.get(Calendar.YEAR),month));
                }
            }
        }
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/sup/company/company/companyOrder";
    }
    /**
     * 分页显示客户的任务列表
     * @param searchBean
     * @return
     */
    @RequestMapping(value = "/findCompanyTaskList")
    public String findCompanyTask(SearchBean searchBean){
        PageBean pageBean =new PageBean(searchBean, businApi);
        String sql="SELECT task.obj_Id,task.task_id,task.task_name,"
                  +" com.com_Name,task.issue_date,task.is_issue"
                  +" FROM PM_Task task"
                  +" inner JOIN PM_COMPANY_CompanyInfo com ON task.user_id = com.obj_Id"
                  +" where task.user_id=?";
        StringBuffer select = new StringBuffer(sql);
        if(!StringUtils.isEmpty(searchBean.getSearchName1())){
            select.append(" and task.is_issue=?");
        }
        pageBean.addQuerySQL(select.toString());
        CompanyInfoUser user=getCurrSup();
        pageBean.addParams(user.getCompanyInfo().getId());
        if(!StringUtil.isEmpty(searchBean.getSearchName1())){
            pageBean.addParams(searchBean.getSearchName1());
        }
        Calendar a=Calendar.getInstance();
        String currentYear=String.valueOf(a.get(Calendar.YEAR));
        if(!StringUtil.isEmpty(searchBean.getSearchDate1())){
            if(currentYear.equals(searchBean.getSearchDate1())){
                pageBean.addQueryDate("task.issue_date",currentYear+"-01-01",currentYear+"-12-31");
            }else{
                Integer month=Integer.parseInt(searchBean.getSearchDate1());
                if(month<9){
                    String startDate=currentYear+"-0"+searchBean.getSearchDate1()+"-01";
                    String dates=DateUtil.getLastDate(a.get(Calendar.YEAR),month).substring(8, 10);
                    String endDate=currentYear+"-0"+searchBean.getSearchDate1()+"-"+dates;
                    pageBean.addQueryDate("task.issue_date",startDate,endDate);
                }else{
                    pageBean.addQueryDate("task.issue_date",currentYear+"-"+searchBean.getSearchDate1()+"-01",currentYear+"-"+searchBean.getSearchDate1()+"-"+DateUtil.getLastDate(a.get(Calendar.YEAR),month));
                }
            }
        }
        pageBean.sqlquery();
        model.addAttribute("pageBean", pageBean);
        return "/sup/company/company/companyTask";
    }
  
}
