package com.pm.company.busin.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.ComRoleUser;
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.busin.BusinApi;
import com.pm.core.model.Contexts;
import com.pm.core.util.DateUtil;
import com.pm.core.util.SqlserverConnUtil;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.framework.util.GlobalUtil;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.bean.UserAccount;

/**
 * 公司信息Service
 * 
 * @author FYL
 */

@Service
@SuppressWarnings("unchecked")
public class CompanyInfoBusinImpl implements CompanyInfoBusin {

    @Autowired
    private BusinApi businApi;

    @Autowired
    public FrameworkBusin frameworkBusin;

    public String getCompanyInfoCode() {
        return frameworkBusin.getNumberLog(GlobalUtil.getValue("company.code"));
    }

    public CompanyInfo getCompanyInfo(String id) {
        return (CompanyInfo) businApi.get(CompanyInfo.class, id);
    }

    public CompanyInfo getCompanyInfoName(String comName) {
        return (CompanyInfo) businApi.getQueryObject("from CompanyInfo where comName = ?", new Object[] { comName });
    }

    public CompanyInfo getCompanyInfoCode(String comCode) {
        return (CompanyInfo) businApi.getQueryObject("from CompanyInfo where comCode = ?", new Object[] { comCode });
    }

    @Override
    public CompanyInfoUser getCompanyInfoUserAdmin(CompanyInfo companyInfo) {
        return (CompanyInfoUser) businApi.getQueryObject("from CompanyInfoUser where companyInfo.id = ? and userAdmin = 1", new Object[] { companyInfo.getId()});
    }

    @Override
    public CompanyInfoUser getCompanyInfoUserByUserTel(String userTel) {
        return (CompanyInfoUser) businApi.getQueryObject("from CompanyInfoUser where userTel = ?", new Object[] { userTel});
    }

    @Override
    public CompanyInfoUser getCompanyInfoUser(String id) {
        return (CompanyInfoUser) businApi.get(CompanyInfoUser.class, id);
    }

    @Override
    public SupCompanyInfo getSupCompanyInfo(String id) {
        return (SupCompanyInfo) businApi.get(SupCompanyInfo.class, id);
    }

    @Override
    public ComRoleUser getComRoleUser( CompanyInfoUser companyInfoUser) {
        return (ComRoleUser) businApi.getQueryObject("from ComRoleUser where companyInfoUser.id = ?", new Object[] { companyInfoUser.getId()});
    }

    @Override
    public List<ComRoleUser> getComRoleUserList(CompanyInfoUser companyInfoUser) {
        return businApi.getQueryList("from ComRoleUser where companyInfoUser.id = ?", new Object[] { companyInfoUser.getId() });
    }

    @Override
    public CompanyInfoUser getSupCompanyInfoUserAdmin(SupCompanyInfo supCompanyInfo) {
        
        return (CompanyInfoUser) businApi.getQueryObject("from CompanyInfoUser where supCompanyInfo.id = ? and userAdmin = 1", new Object[] { supCompanyInfo.getId()});
    }

    @Override
    public List<ComRoleUser> getComRoleUserList(OrgRole orgRole) {
        return businApi.getQueryList("from ComRoleUser where orgRole.id = ?", new Object[] { orgRole.getId() });
    }

    @Override
    public List<SupCompanyInfo> getSupCompanyInfoList() {
        return  businApi.getQueryList("from SupCompanyInfo where comState = ?", new Object[] {Contexts.Y });
    }

    @Override
    public List<CompanyInfo> getAllCompanyInfoList(String comState) {
        return  businApi.getQueryList("from CompanyInfo where comState = ?", new Object[] {comState });
    }

    @Override
    public List<SupCompanyInfo> getAllSupCompanyInfoList(String comState) {
        return  businApi.getQueryList("from SupCompanyInfo where comState = ?", new Object[] {comState });
    }

    @Override
    public OrgRole getOrgRole(String roleCode) {
       
        return (OrgRole)businApi.getQueryObject("from OrgRole where roleCode = ?", new Object[]{roleCode});
    }

    @Override
    public CompanyInfo getCompanyInfocutcomcode(String cutcomcode) {
        
        return (CompanyInfo) businApi.getQueryObject("from CompanyInfo where taxnum=? ", new Object[] { cutcomcode});
    }

    @Override
    public CompanyInfoUser getCompanyInfouser(String mail) {
       
        return (CompanyInfoUser) businApi.getQueryObject("from CompanyInfoUser where userMail=? ", new Object[] { mail});
         }

    @Override
    public List<Integer> getNewRegisterMember( Date startDate,Date endDate) {
            List<Integer> list = new ArrayList<Integer>();
        
            Connection con = null;
            ResultSet rs = null;
            CallableStatement proc =null;
            try {
                con = SqlserverConnUtil.getConnection();
                 proc = con.prepareCall("{call pro_newRegisterMember(?,?)}");
                proc.setDate(1, new java.sql.Date(startDate.getTime()));
                proc.setDate(2, new java.sql.Date(endDate.getTime()));
                rs = proc.executeQuery();
                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();
                while (rs != null && rs.next()) {
                    list.add(rs.getInt(1));
                }
               
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                SqlserverConnUtil.closeConnect(rs, null, con); 
                if (proc!=null) {
                    try {
                        proc.close();
                    } catch (SQLException e) {
                    }
                }
            }
        return list;
    }

    @Override
    public List<Integer> getNewCheckMember(  Date startDate,Date endDate) {
        List<Integer> list = new ArrayList<Integer>();
        
        Connection con = null;
        ResultSet rs = null;
        CallableStatement proc =null;
        try {
            con = SqlserverConnUtil.getConnection();
             proc = con.prepareCall("{call pro_newCheckMember(?,?)}");
            proc.setDate(1, new java.sql.Date(startDate.getTime()));
            proc.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = proc.executeQuery();
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCount = rsMeta.getColumnCount();
            while (rs != null && rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
        }finally {
            SqlserverConnUtil.closeConnect(rs, null, con); 
            if (proc!=null) {
                try {
                    proc.close();
                } catch (SQLException e) {
                }
            }
        }
    return list;
    }

    @Override
    public UserAccount getUserAccount(String id) {
        return (UserAccount)businApi.get(UserAccount.class, id);
    }
}
