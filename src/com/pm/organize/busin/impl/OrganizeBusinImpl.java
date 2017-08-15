package com.pm.organize.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.busin.BusinApi;
import com.pm.core.model.Contexts;
import com.pm.core.util.StringUtil;
import com.pm.notice.bean.Notice;
import com.pm.noticeinformed.bean.NoticeInformed;
import com.pm.organize.bean.OrgDept;
import com.pm.organize.bean.OrgDeptUser;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.bean.OrgRoleUser;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.busin.OrganizeBusin;

/**
 * 组织结构管理Service
 * 
 * @author FYL
 */

@Service
@SuppressWarnings("unchecked")
public class OrganizeBusinImpl implements OrganizeBusin {

    @Autowired
    private BusinApi businApi;

    public UserAccount getUserAccount(String id) {
        return (UserAccount) businApi.get(UserAccount.class, id);
    }

    public UserAccount getUserAccountByMail(String userMail) {
        return (UserAccount) businApi.getQueryObject("from UserAccount where userMail = ?", new Object[] { userMail });
    }

    public UserAccount getUserAccountByPhone(String userTel) {
        return (UserAccount) businApi.getQueryObject("from UserAccount where userTel = ?", new Object[] { userTel });
    }

    public boolean checkUserAccount(String userId, String userMail, String userTel) {
        int count = businApi.getQueryPageSize("select a.loginName from UserAccount a where id <> ? and (userMail = ? or userTel = ?)", new Object[] { userId, userMail, userTel });
        return count > 0 ? false : true;
    }

    public List<UserAccount> getUserAccountList() {
        return businApi.getList(UserAccount.class);
    }
    
    
    public List<CompanyInfoUser> getCompanyInfoUser() {
        return businApi.getQueryList("from CompanyInfoUser a where a.userState = ?", new Object[]{Contexts.Y});
    }


    public OrgDept getOrgDept(String id) {
        return (OrgDept) businApi.get(OrgDept.class, id);
    }

    public List<OrgDept> getOrgDeptList() {
        return businApi.getQueryList("from OrgDept where parent is NULL", null);
    }

    public OrgDeptUser getOrgDeptUser(UserAccount userAccount) {
        return (OrgDeptUser) businApi.getQueryObject("from OrgDeptUser where userAccount.id = ?", new Object[] { userAccount.getId() });
    }

    public List<OrgDeptUser> getOrgDeptUserList(OrgDept orgDept) {
        return businApi.getQueryList("from OrgDeptUser where orgDept.id = ?", new Object[] { orgDept.getId() });
    }

    public void saveOrgDeptUser(UserAccount userAccount, String deptId) {
        OrgDept orgDept = getOrgDept(deptId);
        if (orgDept != null) {
            OrgDeptUser orgDeptUser = getOrgDeptUser(userAccount);
            if (orgDeptUser == null) {
                orgDeptUser = new OrgDeptUser();
                orgDeptUser.setUserAccount(userAccount);
            }
            orgDeptUser.setOrgDept(orgDept);
            businApi.save(orgDeptUser);
        }
    }

    public boolean checkOrgDeptUser(List<OrgDeptUser> deptUsers, UserAccount userAccount) {
        if (deptUsers != null && deptUsers.size() > 0 && userAccount != null) {
            for (OrgDeptUser deptUser : deptUsers) {
                if (deptUser.getUserAccount() != null && deptUser.getUserAccount().getId().equals(userAccount.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public OrgRole getOrgRole(String id) {
        return (OrgRole) businApi.get(OrgRole.class, id);
    }

    public List<OrgRole> getOrgRoleList(int roleType) {
        return businApi.getQueryList("from OrgRole where roleType = ?", new Object[] { roleType });
    }

    public OrgRoleUser getOrgRoleUser(UserAccount userAccount, OrgRole orgRole) {
        return (OrgRoleUser) businApi.getQueryObject("from OrgRoleUser where userAccount.id = ? and orgRole.id = ?", new Object[] { userAccount.getId(), orgRole.getId() });
    }

    public List<OrgRoleUser> getOrgRoleUserList(UserAccount userAccount) {
        return businApi.getQueryList("from OrgRoleUser where userAccount.id = ?", new Object[] { userAccount.getId() });
    }

    public List<OrgRoleUser> getOrgRoleUserList(OrgRole orgRole) {
        return businApi.getQueryList("from OrgRoleUser where orgRole.id = ?", new Object[] { orgRole.getId() });
    }

    public void saveOrgRoleUser(UserAccount userAccount, String[] roleIds) {
        if (roleIds != null && roleIds.length > 0) {
            businApi.remove(getOrgRoleUserList(userAccount));
            for (String id : roleIds) {
                OrgRole orgRole = getOrgRole(id);
                if (orgRole != null) {
                    OrgRoleUser orgRoleUser = new OrgRoleUser();
                    orgRoleUser.setOrgRole(orgRole);
                    orgRoleUser.setUserAccount(userAccount);
                    businApi.save(orgRoleUser);
                }
            }
        }
    }

    public boolean checkOrgRoleUser(List<OrgRoleUser> roleUsers, UserAccount userAccount) {
        if (roleUsers != null && roleUsers.size() > 0 && userAccount != null) {
            for (OrgRoleUser orgRoleUser : roleUsers) {
                if (orgRoleUser.getUserAccount() != null && orgRoleUser.getUserAccount().getId().equals(userAccount.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<UserAccount> getUserAccountList(UserAccount userAccount) {
        return businApi.getQueryList("from UserAccount where id in (" + getOrgDeptUserSql(userAccount) + ")", null);
    }

    public String getOrgDeptUserSql(UserAccount userAccount) {
        String sql = "select id from UserAccount";
        OrgDeptUser orgDeptUser = getOrgDeptUser(userAccount);
        if (orgDeptUser != null) {
            OrgDept orgDept = (OrgDept) businApi.get(OrgDept.class, orgDeptUser.getOrgDept().getId());
            List<OrgDept> orgDeptList = orgDept.getChildren();
            if (orgDeptList != null && orgDeptList.size() > 0) {
                String deptSql = getOrgDeptChildrenSql("", orgDeptList);
                sql += " where (id = " + StringUtil.getSqlId(userAccount.getId()) + " or id in (select userAccount.id from OrgDeptUser where orgDept.id in (" + deptSql + ")))";
            } else {
                sql += " where id = " + StringUtil.getSqlId(userAccount.getId());
            }
        } else {
            sql += " where id = " + StringUtil.getSqlId(userAccount.getId());
        }
        return sql;
    }

    private String getOrgDeptChildrenSql(String deptSql, List<OrgDept> orgDeptList) {
        if (orgDeptList != null) {
            for (OrgDept orgDept : orgDeptList) {
                if (deptSql.length() > 0) {
                    deptSql += ",";
                }
                deptSql += StringUtil.getSqlId(orgDept.getId());
                List<OrgDept> orgDeptList2 = orgDept.getChildren();
                if (orgDeptList2 != null && orgDeptList2.size() > 0) {
                    deptSql += getOrgDeptChildrenSql(deptSql, orgDeptList2);
                }
            }
        }
        return deptSql;
    }

    public OrgDept getParentOrgDeptByChidOrgDept(OrgDept orgDept) {
        if (orgDept != null) {
            if (orgDept.getParent() != null) {
                OrgDept parentOrgDept = this.getOrgDept(orgDept.getParent().getId());
                orgDept = getParentOrgDeptByChidOrgDept(parentOrgDept);
            }
        }
        return orgDept;
    }
    
    /**
     * 查询公告通知最近五条记录
     */
    public List<NoticeInformed> getNoticeInformeds(){
        String sql="select top 5 obj_Id,notice_Title,obj_createDate from PM_NOTICEINFORMED_NoticeInformed order by obj_createDate desc";
        return (List<NoticeInformed>)businApi.getSQLQuery(sql, null).list();
    }
    /**
     * 查询消息列表
     * 
     */
    public List<Notice> getNotice(){
        String sql="SELECT TOP 5 obj_Id,notice_Text,obj_createDate FROM PM_NOTICE_Notice"
                  +" ORDER BY convert(char(10),obj_createDate,120) DESC";
        return (List<Notice>)businApi.getSQLQuery(sql, null).list();
    }
    
    /**
     * 查询已提交的统计订单数
     */
    public Integer  getTotalCountByYtj(Integer orderState){
        String sql="SELECT COUNT(1) FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id WHERE o.order_state!=5 AND o.order_state=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{orderState}).uniqueResult();
    }
    /**
     * 查询已完成的订单数
     * @return
     */
    public Integer getTotalCountByYwc(Integer orderState){
        String sql="SELECT COUNT(1) FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id WHERE o.order_state!=5 AND o.order_state=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{orderState}).uniqueResult();
    }
    /**
     * 查询已分配的订单数
     * @return
     */
    public Integer getTotalCountByYfp(Integer orderState){
        String sql="SELECT COUNT(1) FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id WHERE o.order_state!=5 AND o.order_state=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{orderState}).uniqueResult();
    }
    /**
     * 查询总订单条数
     * @return
     */
    public Integer getOrderNumber(){
        String sql="SELECT COUNT(1) FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id WHERE o.order_state!=5";
        return (Integer)businApi.getSQLQuery(sql, null).uniqueResult();
    }
    
    
    
    /**
     * 按照本日汇总订单数 
     */
    public List<Object[]> getNumByDay(){
       /*
        String sql="select DATEPART(dd, o.obj_createDate) date,count(1) num"
                  +" FROM PM_OrderFrom o"
                  +" where datediff(week,o.obj_createDate,getdate())=0 and o.order_state!=5"
                  +" GROUP By DATEPART(dd, o.obj_createDate)";*/
        String sql="SELECT DATEPART(dd, o.obj_createDate) date,count(1) num FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id WHERE datediff(week,o.obj_createDate,getdate())=0 AND o.order_state!=5 GROUP By DATEPART(dd, o.obj_createDate)";
        
        
        return (List<Object[]>)businApi.getSQLQuery(sql, null).list();
    }
    /**
     * 按照本周汇总订单数
     * @return
     */
    public List<Object []> getOrderNumberByWeek(){
       /* String sql="select DATEPART(ww, o.obj_createDate) date,count(1) num"
                  +" FROM PM_OrderFrom o"
                  +"  where datediff(month,o.obj_createDate,getdate())=0 and o.order_state!=5"
                  +" GROUP By DATEPART(ww, o.obj_createDate)";*/
        String sql="SELECT DATEPART(ww, o.obj_createDate) date,count(1) num FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id WHERE datediff(month,o.obj_createDate,getdate())=0 AND o.order_state!=5 GROUP By DATEPART(ww, o.obj_createDate)";
        
        
        
        return  (List<Object []>)businApi.getSQLQuery(sql, null).list();         
    }
    /**
     * 按照本月来汇总订单数
     */
    public List<Object[]> getOrderNumberByMonth(){
        /*String sql="select DATEPART(mm, o.obj_createDate) date,count(1) num"
                  +" FROM PM_OrderFrom o"
                  +" where datediff(year,o.obj_createDate,getdate())=0 and o.order_state!=5"
                  +" GROUP By DATEPART(mm, o.obj_createDate)";*/
        String sql="SELECT DATEPART(mm, o.obj_createDate) date,count(1) num FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id WHERE datediff(year,o.obj_createDate,getdate())=0 AND o.order_state!=5 GROUP By DATEPART(mm, o.obj_createDate)";
        
        
        
        
        return  (List<Object[]>)businApi.getSQLQuery(sql, null).list();    
    }
    /**
     * 查询客服所能看到的站内消息数量
     * @param userId
     * @return
     */
    public Integer getAdminNoticeCount(String userId){
        StringBuffer sql=new StringBuffer("select count(1) FROM PM_NOTICE_Notice notice ");
        sql.append(" left join PM_COMPANY_CompanyInfoUser u on notice.companyInfoUser=u.obj_Id");
        sql.append(" where u.obj_Id=?");
        return (Integer)businApi.getSQLQuery(sql.toString(), new Object[]{userId}).uniqueResult();
    }
    /**
     * 按照季度来汇总订单数
     */
    public List<Object[]> getOrderNumberByQua(){
       /* String sql="select DATEPART(quarter, o.obj_createDate) date,count(1) num"
                  +" FROM PM_OrderFrom o"
                  +" WHERE datediff(year,o.obj_createDate,getdate())=0 and o.order_state!=5"
                  +" GROUP By DATEPART(quarter, o.obj_createDate)";*/
        String sql="SELECT DATEPART(quarter, o.obj_createDate) date,count(1) num FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id WHERE datediff(year,o.obj_createDate,getdate())=0 AND o.order_state!=5 GROUP By DATEPART(quarter, o.obj_createDate)";
        
        
        
         return (List<Object[]>)businApi.getSQLQuery(sql, null).list();
    }
    /**
     * 统计各个单据类型的订单所占比例
     */
    public List<Object[]> getBillProportion(){
        String  sql="select b.flow_name name ,count(*) data from PM_OrderFrom a"
                    +" inner join PM_FlowType b on   a.tack_type_id=b.obj_Id"
                    +" group by  b.flow_name";
          return (List<Object[]>)businApi.getSQLQuery(sql, null).list();
        
    }
}
