package com.pm.company.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.ComRoleUser;
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.Contexts;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.framework.models.CheckSubmit;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;
import com.pm.organize.util.MD5Util;
import com.pm.right.models.FunAuth;
import com.pm.sysconfig.busin.EnumBusin;

/**
 * 公司管理
 * 
 * @author youliang.fang
 */

@Controller
@RequestMapping("/company/info")
public class CompanyInfoController extends OrganizeBaseController {

    @Autowired
    private CompanyInfoBusin companyInfoBusin;
    
    @Autowired
    private EnumBusin enumBusin;

    /**
     * AJAX检查公司是否冲突
     * 
     * @param id ComID
     * @param comName 公司名称
     * @param comCode 公司编号
     */
    @RequestMapping(value = "/ajaxCheckCompanyInfo")
    @ResponseBody
    public void ajaxCheckCompanyInfo(String id, String comName, String comCode) {
        int count = businApi.getQueryPageSize("select a.id from CompanyInfo a where id <> ? and (comName = ? or comCode = ?) and comState = ?", new Object[] { id, comName, comCode,Contexts.Y });
        writer(count > 0 ? "false" : "true");
    }

    /**
     * 查询人员List列表
     * 
     * @param searchBean
     * @return
     */
    @FunAuth("info_listCompanyInfo")
    @RequestMapping("/listCompanyInfo")
    public String listCompanyInfo(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, a.comCode, a.comName, a.comState, a.createDate from CompanyInfo a where comState <> ? and comState <> ?");
        pageBean.addParams(Contexts.C);
        pageBean.addParams(Contexts.L);
        pageBean.addQueryStr("a.comState", searchBean.getSearchName1(), true);
        pageBean.addQueryStr("a.comName", searchBean.getSearchName2(), true);
        pageBean.addQueryDate("a.createDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.createDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/company/info/listCompanyInfo";
    }
    
    
    /**
     * 查看不同类型的供应商
     * 
     * @param searchBean
     * @return
     */
    @FunAuth("info_listSupCompanyInfouser")
    @RequestMapping("/listSupCompanyInfouser/{id}/{name}")
    public String listCompanyInfoUser(@PathVariable String id,@PathVariable String name,SearchBean searchBean){
            PageBean pageBean = new PageBean(searchBean, businApi);
            pageBean.addQuerySQL("select a.id, a.userName,a.userTel from CompanyInfoUser a left join  a.supCompanyInfo b where b.id=? and a.userState = ?");
            pageBean.addParams(id);
            pageBean.addParams(Contexts.Y);
            pageBean.addQueryStr("a.userTel,a.userName", searchBean.getSearchName2(), true);
            pageBean.query();
            model.addAttribute("pageBean", pageBean);
            model.addAttribute("id", id);
            model.addAttribute("name", name);
            
        return "/company/info/listsupCompanyInfo";
    }

    /**
     * 查看供应商
     */
    @FunAuth("info_listCompanyInfo")
    @RequestMapping("/viewCompanyInfo/{id}")
    public String viewCompanyInfo(@PathVariable String id) {
        CompanyInfo companyInfo=companyInfoBusin.getCompanyInfo(id);
        if(companyInfo.getBaoguanuser()!=null&&companyInfo.getWuliuuser()!=null&&companyInfo.getCangchuuser()!=null
                &&companyInfo.getWaimaouser()!=null){
            CompanyInfoUser companyInfoUserbaoguan=(CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfo.getBaoguanuser());
            CompanyInfoUser companyInfoUserwuliu=(CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfo.getWuliuuser());
            CompanyInfoUser companyInfoUsercangchu=(CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfo.getCangchuuser());
            CompanyInfoUser companyInfoUserwaimao=(CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfo.getWaimaouser());
            model.addAttribute("baoguan", companyInfoUserbaoguan);
            model.addAttribute("wuliu", companyInfoUserwuliu);
            model.addAttribute("cangchu", companyInfoUsercangchu);
            model.addAttribute("waimao", companyInfoUserwaimao);
        }
        //更具id查询出供应商的名称
        if(companyInfo.getComwaimao()!=null){
            model.addAttribute("supCompanyInfowaimao",companyInfoBusin.getSupCompanyInfo(companyInfo.getComwaimao()));
        }
        if(companyInfo.getComcangchu()!=null){
            model.addAttribute("supCompanyInfocangchu",companyInfoBusin.getSupCompanyInfo(companyInfo.getComcangchu()));
        }
        if(companyInfo.getCombaoguan()!=null){
            model.addAttribute("supCompanyInfobaoguan",companyInfoBusin.getSupCompanyInfo(companyInfo.getCombaoguan()));
        }
        if(companyInfo.getComwuliu()!=null){
            model.addAttribute("supCompanyInfowuliu",companyInfoBusin.getSupCompanyInfo(companyInfo.getComwuliu()));
        }
        model.addAttribute("companyInfo",companyInfo);
        return "/company/info/viewCompanyInfo";
    }
    
    /**
     * 设置供应商主联系人
     */
    @FunAuth("info_editCompanyInfoUser")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/saveCompanyInfoUserAdmin/{comId}/{id}")
    public String saveCompanyInfoUserAdmin(@PathVariable String comId, @PathVariable String id) {
        CompanyInfo companyInfo = companyInfoBusin.getCompanyInfo(comId);
        if (companyInfo != null) {
            CompanyInfoUser companyInfoUser_edit = (CompanyInfoUser)businApi.get(CompanyInfoUser.class, id);
            if(companyInfoUser_edit != null){
                CompanyInfoUser companyInfoUserAdmin = companyInfoBusin.getCompanyInfoUserAdmin(companyInfo);
                if(companyInfoUserAdmin != null){
                    companyInfoUserAdmin.setUserAdmin(false);
                    businApi.save(companyInfoUserAdmin);
                }
                companyInfoUser_edit.setUserAdmin(true);
                businApi.save(companyInfoUser_edit);
            }
        }
        model.addAttribute("companyInfo", companyInfo);
        return "redirect:/company/info/viewCompanyInfo/" + companyInfo.getId();
    }
    

    /**
     * 修改客户信息
     */
    @FunAuth("info_editCompanyInfo")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editCompanyInfo/{id}")
    public String editCompanyInfo(@PathVariable String id) {
        CompanyInfo companyInfo = companyInfoBusin.getCompanyInfo(id);
        if (companyInfo != null) {
            model.addAttribute("companyInfo", companyInfo);
            if( companyInfo.getBaoguanuser()!=null){
                CompanyInfoUser companyInfoUserbaoguan=(CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfo.getBaoguanuser());
                model.addAttribute("baoguan", companyInfoUserbaoguan);
            }
            if( companyInfo.getWuliuuser()!=null){
                CompanyInfoUser companyInfoUserwuliu=(CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfo.getWuliuuser());
                model.addAttribute("wuliu", companyInfoUserwuliu);
            }
            if( companyInfo.getCangchuuser()!=null){
                CompanyInfoUser companyInfoUsercangchu=(CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfo.getCangchuuser());
                model.addAttribute("cangchu", companyInfoUsercangchu);
            }
            if( companyInfo.getWaimaouser()!=null){
                CompanyInfoUser companyInfoUserwaimao=(CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfo.getWaimaouser());
                model.addAttribute("waimao", companyInfoUserwaimao);
            }
          /*  //查询供应商的枚举
            Enumtype enumType = enumBusin.getEnumtypeByName("供应商枚举信息");
            if(enumType != null){
                //查询出所有供应商的类型
                List<Enumitems> enumitems = enumBusin.getEnumitemList(enumType);
                
                for (Enumitems enumitems2 : enumitems) {*/
                   /* VocationalWorkEnum WL=VocationalWorkEnum.WL;
                    VocationalWorkEnum BG=VocationalWorkEnum.BG;
                    VocationalWorkEnum WM=VocationalWorkEnum.WM;
                    VocationalWorkEnum CC=VocationalWorkEnum.CC;*/
                    //查询出不同类型的供应商
                    //if(enumitems2.getId() != null && enumitems2.getId().equals(WL.getId())){
                    /*//}
                    //if(enumitems2.getId() != null && enumitems2.getId().equals(CC.getId())){
                        List<SupCompanyInfo> supCompanyInfocangchu=companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                        model.addAttribute("supCompanyInfocangchu", supCompanyInfocangchu);
                    ///}
                    //if(enumitems2.getId() != null && enumitems2.getId().equals(BG.getId())){
                        List<SupCompanyInfo> supCompanyInfobaoguan=companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                        model.addAttribute("supCompanyInfobaoguan", supCompanyInfobaoguan);
                   // }
                    //if(enumitems2.getId() != null && enumitems2.getId().equals(WM.getId())){
                        List<SupCompanyInfo> supCompanyInfowaimao=companyInfoBusin.getSupCompanyInfoList(enumitems2.getId());
                        model.addAttribute("supCompanyInfowaimao", supCompanyInfowaimao);
                    //}
*/                }
        List<SupCompanyInfo> supCompanyInfowuliu=companyInfoBusin.getSupCompanyInfoList();
        model.addAttribute("supCompanyInfowuliu", supCompanyInfowuliu);
            //}
       // }     
        return "/company/info/editCompanyInfo";
    }

    /**
     * 保存公司信息
     */
    @FunAuth("info_editCompanyInfo")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveCompanyInfo")
    public String saveCompanyInfo(CompanyInfo companyInfo, String[] typeIds, String[] infoFiles) {
        CompanyInfo info = companyInfoBusin.getCompanyInfo(companyInfo.getId());
        if (info != null) {
            info.setComName(companyInfo.getComName());
            info.setComAddress(companyInfo.getComAddress());
            info.setComTel(companyInfo.getComTel());
            info.setComLink(companyInfo.getComLink());
            info.setComAssets(companyInfo.getComAssets());
            info.setComBank(companyInfo.getComBank());
            info.setComBankaccount(companyInfo.getComBankaccount());
            info.setComCapital(companyInfo.getComCapital());
            info.setComDutynum(companyInfo.getComDutynum());
            info.setComFoundingtime(companyInfo.getComFoundingtime());
            info.setComPerson(companyInfo.getComPerson());
            info.setComWebsite(companyInfo.getComWebsite());
            info.setComwuliu(companyInfo.getComwuliu());
            info.setComcangchu(companyInfo.getComcangchu());
            info.setComwaimao(companyInfo.getComwaimao());
            info.setCombaoguan(companyInfo.getCombaoguan());
            info.setComEmail(companyInfo.getComEmail());
            info.setBaoguanuser(companyInfo.getBaoguanuser());
            info.setCangchuuser(companyInfo.getCangchuuser());
            info.setWaimaouser(companyInfo.getWaimaouser());
            info.setWuliuuser(companyInfo.getWuliuuser());
            info.setBeizhu(companyInfo.getBeizhu());
            frameworkBusin.saveUploadFileOwner(info, infoFiles);
            businApi.save(info);
        } else {
            companyInfo.setComCode(companyInfoBusin.getCompanyInfoCode());
            companyInfo.setComState(Contexts.Y);
            frameworkBusin.saveUploadFileOwner(companyInfo, infoFiles);
            businApi.save(companyInfo);
        }
        
        return "redirect:/company/info/viewCompanyInfo/" + companyInfo.getId();
    }

    /**
     * 启用供应商
     */
    @FunAuth("info_enableCompanyInfo")
    @RequestMapping("/enableCompanyInfo")
    @ResponseBody
    public void enableCompanyInfo(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfo companyInfo = companyInfoBusin.getCompanyInfo(id);
                    if(companyInfo.getCombaoguan()!=null &&companyInfo.getCombaoguan().length()>0 &&
                        companyInfo.getComwuliu()!=null &&companyInfo.getComwuliu().length()>0 &&
                        companyInfo.getComwaimao()!=null &&companyInfo.getComwaimao().length()>0 &&
                        companyInfo.getComcangchu()!=null &&companyInfo.getComcangchu().length()>0){
                        if (companyInfo != null) {
                            companyInfo.setComState(Contexts.Y);
                            businApi.save(companyInfo);
                            for(CompanyInfoUser companyInfoUser : companyInfo.getCompanyInfoUsers()){
                                if(companyInfoUser.getUserState().equals("Y")){
                                    companyInfoUser.setUserState(Contexts.Y);
                                    if(companyInfoUser.getId().equals(companyInfo.getCjuser())){
                                        companyInfoUser.setUserAdmin(true);
                                    }
                                    businApi.save(companyInfoUser);
                                    OrgRole orgRole = organizeBusin.getOrgRole("003ce32a-6ff1-4865-92c2-dcbce664e38a");
                                    if(orgRole != null){
                                        ComRoleUser comRoleUser = companyInfoBusin.getComRoleUser(companyInfoUser);
                                        comRoleUser.setCompanyInfoUser(companyInfoUser);
                                        comRoleUser.setOrgRole(orgRole);
                                        businApi.save(comRoleUser);
                                    }
                                }
                            }
                            JSONObject jo = new JSONObject();
                            jo.put("id", id);
                            jsonArray.put(jo);
                        }
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

    /**
     * 禁用供应商
     */
    @FunAuth("info_disableCompanyInfo")
    @RequestMapping("/disableCompanyInfo")
    @ResponseBody
    public void disableCompanyInfo(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfo companyInfo = companyInfoBusin.getCompanyInfo(id);
                    if (companyInfo != null) {
                        companyInfo.setComState(Contexts.N);
                        businApi.save(companyInfo);
                        
                        for(CompanyInfoUser companyInfoUser : companyInfo.getCompanyInfoUsers()){
                            companyInfoUser.setUserState(Contexts.N);
                            businApi.save(companyInfoUser);
                        }

                        JSONObject jo = new JSONObject();
                        jo.put("id", id);
                        jsonArray.put(jo);
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

    /**
     * AJAX检查联系人是否冲突
     * 
     * @param id UserID
     * @param userMail 邮箱地址
     * @param userTel 电话
     */
    @RequestMapping(value = "/ajaxCheckCompanyInfoUser")
    @ResponseBody
    public void ajaxCheckCompanyInfoUser(String id, String userMail, String userTel) {
        int count = businApi.getQueryPageSize("select a.id from CompanyInfoUser a where id <> ? and (userMail = ? or userTel = ?)", new Object[] { id, userMail, userTel });
        writer(count > 0 ? "false" : "true");
    }

    /**
     * 修改联系人
     */
    @FunAuth("info_editCompanyInfoUser")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editCompanyInfoUser/{comId}/{id}")
    public String editCompanyInfoUser(@PathVariable String comId, @PathVariable String id) {
        CompanyInfo companyInfo = companyInfoBusin.getCompanyInfo(comId);
        if (companyInfo != null) {
            CompanyInfoUser companyInfoUser = companyInfoBusin.getCompanyInfoUser(id);
            if(companyInfoUser != null){
                model.addAttribute("companyInfoUser", companyInfoUser);
                List<ComRoleUser> comRoleUserList = companyInfoBusin.getComRoleUserList(companyInfoUser);
                if(!comRoleUserList.isEmpty() && comRoleUserList.size() > 0){
                    model.addAttribute("comRoleUser", comRoleUserList.get(0));
                }
            }
        }
        model.addAttribute("companyInfo", companyInfo);
        model.addAttribute("orgRoleList", organizeBusin.getOrgRoleList(2));
        
        return "/company/info/editCompanyInfoUser";
    }

    /**
     * 保存联系人
     */
    @FunAuth("info_editCompanyInfoUser")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveCompanyInfoUser")
    public String saveCompanyInfoUser(CompanyInfoUser companyInfoUser, String comId, String orgRoleId) {
        CompanyInfo companyInfo = companyInfoBusin.getCompanyInfo(comId);
        if (companyInfo != null) {
            CompanyInfoUser infoUser = (CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfoUser.getId());
            if (infoUser != null) {
                infoUser.setCompanyInfo(companyInfo);
                infoUser.setUserFax(companyInfoUser.getUserFax());
                infoUser.setUserMail(companyInfoUser.getUserMail());
                infoUser.setUserName(companyInfoUser.getUserName());
                infoUser.setUserTel(companyInfoUser.getUserTel());
                infoUser.setUserNumber(companyInfoUser.getUserNumber());
                infoUser.setUserState(Contexts.Y);
                businApi.save(infoUser);
            } else {
                companyInfoUser.setLoginPwd(MD5Util.sign(companyInfoUser.getLoginPwd()));
                companyInfoUser.setCompanyInfo(companyInfo);
                companyInfoUser.setUserState(Contexts.Y);
                businApi.save(companyInfoUser);
            }
            OrgRole orgRole = organizeBusin.getOrgRole(orgRoleId);
            if(orgRole != null){
                ComRoleUser comRoleUser = companyInfoBusin.getComRoleUser( companyInfoUser);
                if(comRoleUser==null){
                    comRoleUser=new ComRoleUser();
                    comRoleUser.setCompanyInfoUser(companyInfoUser);
                    comRoleUser.setOrgRole(orgRole);
                    businApi.save(comRoleUser);
                }else{
                    comRoleUser.setCompanyInfoUser(companyInfoUser);
                    comRoleUser.setOrgRole(orgRole);
                    businApi.save(comRoleUser);
                }
            }
        }
        return "redirect:/company/info/viewCompanyInfo/" + comId;
    }

    /**
     * 修改联系人密码
     */
    @FunAuth("info_editCompanyInfoUserPwd")
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editCompanyInfoUserPwd/{comId}/{id}")
    public String editCompanyInfoUserPwd(@PathVariable String comId, @PathVariable String id) {
        CompanyInfo companyInfo = companyInfoBusin.getCompanyInfo(comId);
        if (companyInfo != null) {
            model.addAttribute("companyInfoUser", businApi.get(CompanyInfoUser.class, id));
        }
        model.addAttribute("companyInfo", companyInfo);
        return "/company/info/editCompanyInfoUserPwd";
    }

    /**
     * 保存联系人密码
     */
    @FunAuth("info_editCompanyInfoUserPwd")
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveCompanyInfoUserPwd")
    public String saveCompanyInfoUserPwd(CompanyInfoUser companyInfoUser, String comId) {
        CompanyInfo companyInfo = companyInfoBusin.getCompanyInfo(comId);
        if (companyInfo != null) {
            CompanyInfoUser infoUser = (CompanyInfoUser) businApi.get(CompanyInfoUser.class, companyInfoUser.getId());
            if (infoUser != null) {
                infoUser.setLoginPwd(MD5Util.sign(companyInfoUser.getLoginPwd()));
                businApi.save(infoUser);
            }
        }
        return "redirect:/company/info/viewCompanyInfo/" + comId;
    }

    /**
     * 删除用户信息
     */
    @FunAuth("info_removeCompanyInfoUser")
    @RequestMapping("/removeCompanyInfoUser")
    @ResponseBody
    public void removeCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) businApi.get(CompanyInfoUser.class, id);
                    if (infoUser != null) {
                        ComRoleUser comRoleUser = companyInfoBusin.getComRoleUser( infoUser);
                        businApi.remove(comRoleUser);
                        businApi.remove(infoUser);
                        JSONObject jo = new JSONObject();
                        jo.put("id", id);
                        jsonArray.put(jo);
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

    /**
     * 启用联系人
     */
    @FunAuth("info_enableCompanyInfoUser")
    @RequestMapping("/enableCompanyInfoUser")
    @ResponseBody
    public void enableCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) businApi.get(CompanyInfoUser.class, id);
                    if (infoUser != null) {
                        infoUser.setUserState(Contexts.Y);
                        businApi.save(infoUser);

                        JSONObject jo = new JSONObject();
                        jo.put("id", id);
                        jsonArray.put(jo);
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

    /**
     * 禁用联系人
     */
    @FunAuth("info_disableCompanyInfoUser")
    @RequestMapping("/disableCompanyInfoUser")
    @ResponseBody
    public void disableCompanyInfoUser(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    CompanyInfoUser infoUser = (CompanyInfoUser) businApi.get(CompanyInfoUser.class, id);
                    if (infoUser != null) {
                        infoUser.setUserState(Contexts.N);
                        businApi.save(infoUser);

                        JSONObject jo = new JSONObject();
                        jo.put("id", id);
                        jsonArray.put(jo);
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    /**
     * 查询所有用户登录记录列表
     */
    @FunAuth("info_listAllComLoginLog")
    @RequestMapping("/listAllComLoginLog")
    public String listAllComLoginLog(SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select a.id, c.comName, b.userName, b.userMail, a.ipInfo, a.hostInfo, a.userBrowse, a.updateDate from ComLoginLog a join a.companyInfoUser b join b.companyInfo c");
        pageBean.addQueryStr("a.ipInfo,a.hostInfo,a.userBrowse,b.userName,b.userMail,c.comName", searchBean.getSearchName1(), true);
        pageBean.addQueryDate("a.updateDate", searchBean.getSearchDate1(), searchBean.getSearchDate2());
        pageBean.addOrderBy("a.updateDate", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        return "/company/info/listAllComLoginLog";
    }
    
    /**
     * 个人信息
     * 
     * @return viewUser
     */
    @RequestMapping("/viewUser")
    public String viewUser() {
        UserAccount userAccount= (UserAccount) businApi.get(UserAccount.class, this.getCurrUser().getId());
        if (userAccount != null) {
            model.addAttribute("UserAccount", userAccount);
        }
        return "/company/info/viewUser";
    }
    
   
    /**
    *修改 个人信息
    * 
    * @return editUser
    */
   @RequestMapping("/editUser")
   public String editUser() {
       UserAccount userAccount= (UserAccount) businApi.get(UserAccount.class, this.getCurrUser().getId());
       if (userAccount != null) {
           model.addAttribute("UserAccount", userAccount);
       }
       return "/company/info/editUser";
   }
   
   /**
    * 保存个人信息
    */
   @RequestMapping("/saveUser")
   public String saveUser(UserAccount userAccount1) {
       UserAccount userAccount= (UserAccount) businApi.get(UserAccount.class, this.getCurrUser().getId());
       if (userAccount != null) {
           userAccount.setUserName(userAccount1.getUserName());
           userAccount.setUserTel(userAccount1.getUserTel());
           userAccount.setLoginPwd(MD5Util.sign(userAccount1.getLoginPwd()));
           businApi.save(userAccount);
       }
       return "redirect:/company/info/viewUser";
   }
}
