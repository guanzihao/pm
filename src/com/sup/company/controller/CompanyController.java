package com.sup.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import com.pm.framework.models.CheckSubmit;
import com.pm.noticeinformed.busin.NoticeInformedBusin;
import com.pm.right.models.FunAuth;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.sysconfig.busin.EnumBusin;
import com.sup.company.busin.CompanyInfoUserBusin;

/**
 * 公司人员管理
 */

@Controller
@RequestMapping("/sup/company/company")
public class CompanyController extends SupBaseController {

    @Autowired
    public CompanyInfoUserBusin companyInfoUserBusin;

    @Autowired
    public CompanyInfoBusin companyInfoBusin;
    
    @Autowired
    private NoticeInformedBusin noticeInformedBusin;
    
    @Autowired
    private EnumBusin enumBusin;
    /**
     * 查询公告通知列表,只查看对所有人公开，和平台公开的公告
     */
    @RequestMapping("/listNoticeInformed")
    public String listNoticeInformed(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.noticeTitle, a.noticeText, b.userName, a.updateDate from NoticeInformed a inner join a.userAccount b");
        pageBean.addQuerySQL("where a.noticeType in (1,3) and a.noticeState=1");
        pageBean.addQueryStr("a.noticeTitle,b.userName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/sup/company/company/listNoticeInformed";
    }
    
    /**
     * 显示信息
     * 
     * @return viewNoticeInformed
     */
    @FunAuth("noticeinformed_editComNoticeInformed")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/viewComNoticeInformed/{id}")
    public String viewNoticeInformed(@PathVariable String id) {
        model.addAttribute("noticeInformed", noticeInformedBusin.getNoticeInformed(id));
        return "/sup/company/company/viewComNoticeInformed";
    }
    
    
    /**
     * 显示客户公司信息
     * 
     * @return viewCompanyInfo
     */
    @FunAuth("company_viewCompanyInfo")
    @RequestMapping("/viewCompanyInfo")
    public String viewCompanyInfo() {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        int count = businApi.getQueryPageSize("select a.id from UploadFileOwner a where className = ? and metaColums = ? and metaId = ?", new Object[] { "com.pm.company.bean.CompanyInfo","colums",companyInfoUser.getCompanyInfo().getId()});
        if(count<1){
            return "redirect:/sup/company/company/editCompanyInfo";
        }
        if(companyInfoUser.getCompanyInfo().getComAddress()==null|| companyInfoUser.getCompanyInfo().getComLink()==null
                && companyInfoUser.getCompanyInfo().getComTel()==null|| companyInfoUser.getCompanyInfo().getComEmail()==null
                && companyInfoUser.getCompanyInfo().getTaxnum()==null || companyInfoUser.getUserState().equals("C")){
                return "redirect:/sup/company/company/editCompanyInfo";
        }
        
        if (companyInfoUser != null) {
            model.addAttribute("companyInfo", companyInfoUser.getCompanyInfo());
          //更具id查询出供应商的名称
            if(companyInfoUser.getCompanyInfo().getComwaimao()!=null){
                model.addAttribute("supCompanyInfowaimao",companyInfoBusin.getSupCompanyInfo(companyInfoUser.getCompanyInfo().getComwaimao()));
            }
            if(companyInfoUser.getCompanyInfo().getComcangchu()!=null){
                model.addAttribute("supCompanyInfocangchu",companyInfoBusin.getSupCompanyInfo(companyInfoUser.getCompanyInfo().getComcangchu()));
            }
            if(companyInfoUser.getCompanyInfo().getCombaoguan()!=null){
                model.addAttribute("supCompanyInfobaoguan",companyInfoBusin.getSupCompanyInfo(companyInfoUser.getCompanyInfo().getCombaoguan()));
            }
            if(companyInfoUser.getCompanyInfo().getComwuliu()!=null){
                model.addAttribute("supCompanyInfowuliu",companyInfoBusin.getSupCompanyInfo(companyInfoUser.getCompanyInfo().getComwuliu()));
            }
        }
        return "/sup/company/company/viewCompanyInfo";
    }

    /**
     * 编辑公司信息
     * 
     * @return editCompanyInfo
     */
    @FunAuth("company_editCompanyInfo")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editCompanyInfo")
    public String editCompanyInfo() {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
       
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
        if (companyInfoUser != null) {
            CompanyInfo companyInfo = companyInfoUser.getCompanyInfo();
            if (companyInfo != null) {
                model.addAttribute("companyInfo", companyInfo);
            }
        }
        return "/sup/company/company/editCompanyInfo";
    }

    /**
     * 保存公司信息
     */
    @FunAuth("company_editCompanyInfo")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/saveCompanyInfo")
    public String saveCompanyInfo(CompanyInfo companyInfo, String[] typeIds, String[] infoFiles) {
        CompanyInfo info = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId()).getCompanyInfo();
        if (info != null ) {
            if(this.getCurrSup().getUserState().equals("Y")){
                if(info.getComState().equals("Y")||info.getComState().equals("W")){
                    info.setComAddress(companyInfo.getComAddress());
                    info.setComLink(companyInfo.getComLink());
                    info.setComTel(companyInfo.getComTel());
                    info.setComEmail(companyInfo.getComEmail());
                }else{
                    info.setComName(companyInfo.getComName());
                    info.setComAddress(companyInfo.getComAddress());
                    info.setComLink(companyInfo.getComLink());
                    info.setComTel(companyInfo.getComTel());
                    info.setComEmail(companyInfo.getComEmail());
                    info.setTaxnum(companyInfo.getComcustomcode());
                }
               /* info.setCombaoguan(companyInfo.getCombaoguan());
                info.setComwaimao(companyInfo.getComwaimao());
                info.setComwuliu(companyInfo.getComwuliu());
                info.setComcangchu(companyInfo.getComcangchu());*/
               /* && companyInfo.getCombaoguan()!=null&& companyInfo.getComwaimao()!=null
                        && companyInfo.getComwuliu()!=null&& companyInfo.getComcangchu()!=null*/
                
                if(info.getComState().equals("Y")){
                    
                }else{
                    if(info.getComAddress()!=null&& info.getComLink()!=null
                            && info.getComTel()!=null&& info.getComEmail()!=null
                            && info.getTaxnum()!=null&& info.getComName()!=null
                            ){
                        
                            info.setComState(Contexts.L);
                       
                    }else{
                        info.setComState(Contexts.C);
                    }
                }
                businApi.save(info);
                frameworkBusin.saveUploadFileOwner(info, infoFiles);
            }
        }
        return "redirect:/sup/company/company/viewCompanyInfo";
    }
    
    
    
    /**
     * 检查客户补充公司信息，纳税识别码是否相同
     * @param comcustomcode
     * @throws IOException
     */
    @RequestMapping(value = "/ajaxCheckCompanyInfocustoment")
    @ResponseBody
    public void ajaxCheckCompanyInfoCustoment(String comcustomcode) throws IOException {
        int count = businApi.getQueryPageSize("select a.id from CompanyInfo a where taxnum = ?  ", new Object[] { comcustomcode});
        if(count==0){
            PrintWriter out = response.getWriter();
            out.println(1);
        }else{
            CompanyInfo com= companyInfoBusin.getCompanyInfocutcomcode(comcustomcode);
            if(com.getId().equals(this.getCurrSup().getCompanyInfo().getId())){
               
            }else{
                CompanyInfoUser companyInfoUser =(CompanyInfoUser)businApi.get(CompanyInfoUser.class, this.getCurrSup().getId());
                companyInfoUser.setCompanyInfo(com);
                companyInfoUser.setUserState(Contexts.W);
                businApi.save(companyInfoUser);
                CompanyInfo companyInfo =(CompanyInfo)businApi.get(CompanyInfo.class,this.getCurrSup().getCompanyInfo().getId());
                businApi.remove(companyInfo);
                PrintWriter out = response.getWriter();
                out.println(0);
                
            }
        }
    }

    
    /**
     * 提交客户公司信息
     */
    @FunAuth("company_editCompanyInfo")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/tijioaCompanyInfo")
    public String tijioaCompanyInfo(String state, String[] typeIds) {
        CompanyInfo info = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId()).getCompanyInfo();
        if (state.equals(Contexts.L)) {
            info.setComState(Contexts.W);
            info.setCjuser(this.getCurrSup().getId());
            businApi.save(info);
        }
        return "redirect:/sup/company/company/viewCompanyInfo";
    }
}
