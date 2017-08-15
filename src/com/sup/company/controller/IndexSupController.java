package com.sup.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pm.company.bean.CompanyInfoUser;
import com.sup.company.busin.CompanyInfoUserBusin;

/**
 * 供应商首页面
 * 
 * @author guoce
 */

@Controller
@RequestMapping("/sup/index")
public class IndexSupController extends SupBaseController {
    
    @Autowired
    public CompanyInfoUserBusin companyInfoUserBusin;
    
    /**
     * 供应商首页
     * 
     * @return 首页
     */
    @RequestMapping("/supHome")
    public String supHome(HttpServletRequest request) {
        if (getCurrSup() != null) {
            CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
            if (companyInfoUser != null && companyInfoUser.getSupCompanyInfo() != null && (companyInfoUser.getSupCompanyInfo().getComState().equals("Y")|| companyInfoUser.getSupCompanyInfo().getComState().equals("C")|| companyInfoUser.getSupCompanyInfo().getComState().equals("L")|| companyInfoUser.getSupCompanyInfo().getComState().equals("W"))) {
                return "/sup/supmain";
            }else if(companyInfoUser != null && companyInfoUser.getCompanyInfo() != null && (companyInfoUser.getCompanyInfo().getComState().equals("Y")|| companyInfoUser.getCompanyInfo().getComState().equals("W")|| companyInfoUser.getCompanyInfo().getComState().equals("C")|| companyInfoUser.getCompanyInfo().getComState().equals("L"))){
                String url=request.getParameter("url");
                if(url!=null){
                    model.addAttribute("url", url);
                }
                return "/sup/main";
            }else{
                setCurrSup(companyInfoUser);
                return "redirect:/sup/index/viewCompanyInfo/" + companyInfoUser.getId();
            }
        }
        return "/index";
    }
    
    /**
     * 显示供应商公司信息
     * 
     * @return viewCompanyInfo
     */
    @RequestMapping("/viewCompanyInfo/{id}")
    public String viewCompanyInfo(@PathVariable String id) {
        CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(id);
        if (companyInfoUser != null) {
            model.addAttribute("companyInfo", companyInfoUser.getCompanyInfo());
            model.addAttribute("companyInfoUser", companyInfoUser);
        }
        return "/common/viewCompanyInfo";
    }
    /**
     * 获取供应商的用户信息
     */
    @RequestMapping("/getSuplierUser")
    @ResponseBody
    public void getSupUserInfo(){
        JSONObject obj=new JSONObject();
        if (getCurrSup() != null) {
            CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
            if (companyInfoUser != null && companyInfoUser.getSupCompanyInfo() != null && (companyInfoUser.getSupCompanyInfo().getComState().equals("Y")|| companyInfoUser.getSupCompanyInfo().getComState().equals("C")|| companyInfoUser.getSupCompanyInfo().getComState().equals("L")|| companyInfoUser.getSupCompanyInfo().getComState().equals("W"))) {
                //String userName=request.getParameter("userName");
                obj.put("userName", companyInfoUser.getUserName());
                Integer noticeCount=companyInfoUserBusin.getSupplierNoticeCount(companyInfoUser.getId());
                obj.put("noticeCount", noticeCount);
                obj.put("LoginName",companyInfoUser.getUserMail());
            }
        }
        writer(obj.toString());
        
    }
    /**
     * 获取客户的用户信息
     */
    @RequestMapping("/getCompanyUser")
    @ResponseBody
    public void getComUserInfo(){
        JSONObject obj=new JSONObject();
        if (getCurrSup() != null) {
            CompanyInfoUser companyInfoUser = companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
            if(companyInfoUser != null && companyInfoUser.getCompanyInfo() != null && (companyInfoUser.getCompanyInfo().getComState().equals("Y")|| companyInfoUser.getCompanyInfo().getComState().equals("W")|| companyInfoUser.getCompanyInfo().getComState().equals("C")|| companyInfoUser.getCompanyInfo().getComState().equals("L"))){
                obj.put("userName", companyInfoUser.getUserName());
                Integer noticeCount=companyInfoUserBusin.getCompanyNoticeCount(companyInfoUser.getId());
                obj.put("noticeCount", noticeCount);
                obj.put("LoginName",companyInfoUser.getUserMail());
            }
        }
        writer(obj.toString());
    }
}
