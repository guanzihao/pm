package com.sup.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.models.CheckSubmit;
import com.pm.noticeinformed.busin.NoticeInformedBusin;
import com.pm.right.models.FunAuth;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.busin.EnumBusin;
import com.sup.company.busin.CompanyInfoUserBusin;

/**
 * 供应商人员管理
 */

@Controller
@RequestMapping("/sup/supcompany/supcompany")
public class SupCompanyController extends SupBaseController {

    @Autowired
    public CompanyInfoUserBusin companyInfoUserBusin;

    @Autowired
    public CompanyInfoBusin companyInfoBusin;
    
    @Autowired
    private EnumBusin enumBusin;
    
    @Autowired
    private NoticeInformedBusin noticeInformedBusin;
    /**
     * 查询公告通知列表,只查看对所有人公开，和平台公开的公告
     */
    @RequestMapping("/listSupNoticeInformed")
    public String listNoticeInformed(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.noticeTitle, a.noticeText, b.userName, a.updateDate from NoticeInformed a inner join a.userAccount b");
        pageBean.addQuerySQL("where a.noticeType in (1,4) and a.noticeState=1");
        pageBean.addQueryStr("a.noticeTitle,b.userName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/sup/supcompany/supcompany/listSupNoticeInformed";
    }
    
    /**
     * 显示信息
     * 
     * @return viewNoticeInformed
     */
    @FunAuth("noticeinformed_editSupNoticeInformed")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/viewSupNoticeInformed/{id}")
    public String viewNoticeInformed(@PathVariable String id) {
        model.addAttribute("noticeInformed", noticeInformedBusin.getNoticeInformed(id));
        return "/sup/supcompany/supcompany/viewSupNoticeInformed";
    }
    
    /**
     * 显示供应商信息
     * 
     * @return viewCompanyInfo
     */
    @FunAuth("company_viewSupCompanyInfo")
    @RequestMapping("/viewSupCompanyInfo")
    public String viewCompanyInfo() {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if (companyInfoUser != null) {
            SupCompanyInfo supCompanyInfo=companyInfoUser.getSupCompanyInfo();
            model.addAttribute("supcompanyInfo",supCompanyInfo );
            if(supCompanyInfo.getComType()!=null){
                Enumitems enumitems=enumBusin.getEnumitems(supCompanyInfo.getComType());
                model.addAttribute("enumitems", enumitems);
            }
        }
        return "/sup/supcompany/supcompany/viewSupCompanyInfo";
    }

    /**
     * 编辑供应商信息
     * 
     * @return editCompanyInfo
     */
    @FunAuth("company_editSupCompanyInfo")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editSupCompanyInfo")
    public String editCompanyInfo() {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if (companyInfoUser != null) {
            SupCompanyInfo supcompanyInfo = companyInfoUser.getSupCompanyInfo();
            if (supcompanyInfo != null) {
                model.addAttribute("supcompanyInfo", supcompanyInfo);
            }
        }
        return "/sup/supcompany/supcompany/editSupCompanyInfo";
    }

    /**
     * 保存供应商信息
     */
    @FunAuth("company_editSupCompanyInfo")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/saveSupCompanyInfo")
    public String saveCompanyInfo(SupCompanyInfo supcompanyInfo, String[] typeIds) {
        SupCompanyInfo info = companyInfoUserBusin.getSupCompanyInfoUser(getCurrSup().getId()).getSupCompanyInfo();
        if (info != null) {
            info.setComAddress(supcompanyInfo.getComAddress());
            info.setComTel(supcompanyInfo.getComTel());
            info.setComEmail(supcompanyInfo.getComEmail());
            businApi.save(info);
        }
        return "redirect:/sup/supcompany/company/viewCompanyInfo";
    }
}
