package com.pm.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.core.controller.BaseController;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.bean.MethodLog;
import com.pm.right.models.FunAuth;

@Controller
@RequestMapping("/framework/methodlog")
public class MethodLogController extends BaseController {

    /**
     * 查询系统访问日志
     * 
     * @return listMethodLog
     */
    @FunAuth("methodlog_listMethodLog")
    @RequestMapping("/listMethodLog")
    public String listMethodLog(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL(
                "select a.id, a.ipInfo, a.logMethod, b.userName, a.createDate from MethodLog a left join a.userAccount b");
        pageBean.addQueryDate("a.createDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/framework/methodlog/listMethodLog";
    }

    /**
     * 显示具体访问参数
     * 
     * @param id Id
     * @return viewMethodLog
     */
    @FunAuth("methodlog_listMethodLog")
    @RequestMapping("/viewMethodLog/{id}")
    public String viewMethodLog(@PathVariable String id) {
        model.addAttribute("methodLog", businApi.get(MethodLog.class, id));
        return "/framework/methodlog/viewMethodLog";
    }
}
