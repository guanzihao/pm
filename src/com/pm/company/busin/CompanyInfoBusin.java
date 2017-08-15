package com.pm.company.busin;

import java.util.Date;
import java.util.List;

import com.pm.company.bean.ComRoleUser;
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.bean.UserAccount;
public interface CompanyInfoBusin {

    /**
     * SpringBean的名称
     */
    public static String COMPANYINFOBUSIN = "companyInfoBusinImpl";

    /**
     * 生成新公司编号
     * 
     * @return 编号
     */
    String getCompanyInfoCode();
    
    
    UserAccount getUserAccount(String id);
    
    
    /**
     * 生成新公司编号
     * 
     * @return 编号
     */
    CompanyInfoUser getCompanyInfouser(String mail);

    /**
     * 根据ID查询公司
     * 
     * @param id ID
     * @return CompanyInfo
     */
    CompanyInfo getCompanyInfo(String id);
    
    
    /**
     * 根据纳税识别码查询公司
     * 
     * @param cutcomcode 纳税识别码
     * @return CompanyInfo
     */
    CompanyInfo getCompanyInfocutcomcode(String cutcomcode);
    
    
    /**
     * 根据ID查询供应商
     * 
     * @param id ID
     * @return CompanyInfo
     */
    SupCompanyInfo getSupCompanyInfo(String id);
    
    

    /**
     * 根据Name查询公司
     * 
     * @param comName 名称
     * @return CompanyInfo
     */
    CompanyInfo getCompanyInfoName(String comName);

    /**
     * 根据CODE查询公司
     * 
     * @param comCode code
     * @return CompanyInfo
     */
    CompanyInfo getCompanyInfoCode(String comCode);
    
    /**
     * 根据ID查询公司用户
     * @param id
     * @return
     */
    CompanyInfoUser getCompanyInfoUser(String id);
    
    /**
     * 查询公司主联系人
     * @param companyInfo
     * @return
     */
    CompanyInfoUser getCompanyInfoUserAdmin(CompanyInfo companyInfo);
    /**
     * 查询供应商主联系人
     * @param companyInfo
     * @return
     */
    CompanyInfoUser getSupCompanyInfoUserAdmin(SupCompanyInfo supCompanyInfo);
    
    /**
     * 通过手机号码查询公司系人
     * @param userTel
     * @return
     */
    CompanyInfoUser getCompanyInfoUserByUserTel(String userTel);
    
    /**
     * 通过角色和用户查询用户角色关系
     * @param orgRole
     * @param companyInfoUser
     * @return
     */
    ComRoleUser getComRoleUser( CompanyInfoUser companyInfoUser);
    
    /**
     * 通过用户查询用户所有的角色关系
     * @param companyInfoUser
     * @return
     */
    List<ComRoleUser> getComRoleUserList(CompanyInfoUser companyInfoUser);
    
    /**
     * 根据角色查询
     * 
     * @param orgRole orgRole
     * @return List<OrgRoleUser>
     */
    List<ComRoleUser> getComRoleUserList(OrgRole orgRole);
    
    
    /**
     * 根据类型查询供应商信息
     * 
     * @param SupCompanyInfo SupCompanyInfo
     * @return List<SupCompanyInfo>
     */
    List<SupCompanyInfo> getSupCompanyInfoList();
    
    /**
     * 查询所有客户
     * @return
     */
    List<CompanyInfo> getAllCompanyInfoList(String comState);
    
    /**
     * 查询所有供应商
     * @return
     */
    List<SupCompanyInfo> getAllSupCompanyInfoList(String comState);
    
    /**
     * 更具roleCode查询数据
     * @return
     */
    OrgRole getOrgRole(String roleCode);

    /**
     * 新注册会员
     * @param startDate
     * @param endDate
     * @return
     */
    List<Integer> getNewRegisterMember(Date startDate, Date endDate);

    /**
     * 新认证会员数
     * @param startDate
     * @param endDate
     * @return
     */
    List<Integer> getNewCheckMember(Date startDate, Date endDate);


  
}
