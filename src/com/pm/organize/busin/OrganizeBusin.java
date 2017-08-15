package com.pm.organize.busin;

import java.util.List;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.notice.bean.Notice;
import com.pm.noticeinformed.bean.NoticeInformed;
import com.pm.organize.bean.OrgDept;
import com.pm.organize.bean.OrgDeptUser;
import com.pm.organize.bean.OrgRole;
import com.pm.organize.bean.OrgRoleUser;
import com.pm.organize.bean.UserAccount;


public interface OrganizeBusin {

    /**
     * SpringBean的名称
     */
    public static String ORGANIZATIONBUSIN = "organizeBusinImpl";

    /**
     * 查询账号信息
     * 
     * @param id userId
     * @return UserAccount
     */
    UserAccount getUserAccount(String id);

    /**
     * 查询账号信息
     * 
     * @param userMail userMail
     * @return UserAccount
     */
    UserAccount getUserAccountByMail(String userMail);

    /**
     * 查询账号信息
     * 
     * @param userTel 手机号码
     * @return UserAccount
     */
    UserAccount getUserAccountByPhone(String userTel);
    
    /**
     * 查询客服所能看到的站内消息数量
     * @param userId
     * @return
     */
    Integer getAdminNoticeCount(String userId);

    /**
     * 检查人员是否存在
     * 
     * @param userId
     * @param userMail
     * @param userTel
     * @return false:存在
     */
    boolean checkUserAccount(String userId, String userMail, String userTel);

    /**
     * 获得所有用户信息
     * 
     * @return List<UserAccount>
     */
    List<UserAccount> getUserAccountList();
    
    /**
     * 获得所有用户信息
     * 
     * @return List<UserAccount>
     */
    List<CompanyInfoUser> getCompanyInfoUser();

    /**
     * 获得部门
     * 
     * @param id ID
     * @return OrgDept
     */
    OrgDept getOrgDept(String id);

    /**
     * 查询所有部门
     * 
     * @return List<OrgDept>
     */
    List<OrgDept> getOrgDeptList();

    /**
     * 根据用户查询用户部门关系
     * 
     * @param userAccount UserAccount
     * @return OrgDeptUser
     */
    OrgDeptUser getOrgDeptUser(UserAccount userAccount);

    /**
     * 根据部门查询
     * 
     * @param orgDept OrgDept
     * @return List<OrgDeptUser>
     */
    List<OrgDeptUser> getOrgDeptUserList(OrgDept orgDept);

    /**
     * 保存人员部门
     * 
     * @param userAccount 人员
     * @param deptId 部门ID
     */
    void saveOrgDeptUser(UserAccount userAccount, String deptId);

    /**
     * 对比集合中是否存在
     * 
     * @param deptUsers 集合
     * @param userAccount 人员
     * @return
     */
    boolean checkOrgDeptUser(List<OrgDeptUser> deptUsers, UserAccount userAccount);

    /**
     * 查询角色
     * 
     * @param id ID
     * @return 角色
     */
    OrgRole getOrgRole(String id);

    /**
     * 查询所有角色
     * 
     * @return List<OrgRole>
     */
    List<OrgRole> getOrgRoleList(int roleType);

    /**
     * 查询角色人员关系
     * 
     * @param userAccount 人员
     * @param orgRole 角色
     * @return OrgRoleUser
     */
    OrgRoleUser getOrgRoleUser(UserAccount userAccount, OrgRole orgRole);

    /**
     * 根据用户查询
     * 
     * @param userAccount userAccount
     * @return List<OrgRoleUser>
     */
    List<OrgRoleUser> getOrgRoleUserList(UserAccount userAccount);

    /**
     * 根据角色查询
     * 
     * @param orgRole orgRole
     * @return List<OrgRoleUser>
     */
    List<OrgRoleUser> getOrgRoleUserList(OrgRole orgRole);

    /**
     * 保存用户对于的角色
     * 
     * @param userAccount 人员
     * @param roleIds 角色IDS
     */
    void saveOrgRoleUser(UserAccount userAccount, String[] roleIds);

    /**
     * 对比集合中是否存在
     * 
     * @param deptUsers 角色
     * @param userAccount 人员
     * @return
     */
    boolean checkOrgRoleUser(List<OrgRoleUser> roleUsers, UserAccount userAccount);

    /**
     * 根据用户岗位查询下属所有用户
     * 
     * @param userAccount 人员
     * @return List<UserAccount> List
     */
    List<UserAccount> getUserAccountList(UserAccount userAccount);

    /**
     * 获得组织结构级别，查询自己所有可以查看的内容
     * 
     * @param userAccount 人员
     * @return String SQL
     */
    String getOrgDeptUserSql(UserAccount userAccount);

    /**
     * 根据部门获得最上级部门
     */
    OrgDept getParentOrgDeptByChidOrgDept(OrgDept orgDept);

    
    /**
     * 查询公告通知最近五条记录
     */
    List<NoticeInformed> getNoticeInformeds();
    /**
     * 查询公告通知最近五条记录
     * @return
     */
    public List<Notice> getNotice();
    
    /**
     * 查询已提交的统计订单数
     */
    Integer  getTotalCountByYtj(Integer orderState);
    /**
     * 查询已完成的订单数
     * @return
     */
    Integer getTotalCountByYwc(Integer orderState);
    /**
     * 查询已分配的订单数
     * @return
     */
    Integer getTotalCountByYfp(Integer orderState);
    /**
     * 查询总订单条数
     * @return
     */
    Integer getOrderNumber();
    
    
    /**
     * 按照本日汇总订单数 
     */
    List<Object[]> getNumByDay();
    /**
     * 按照本周汇总订单数
     * @return
     */
    List<Object []> getOrderNumberByWeek();
    /**
     * 按照本月来汇总订单数
     */
    List<Object[]> getOrderNumberByMonth();
    /**
     * 按照本季度汇总订单数
     * @return
     */
    List<Object[]> getOrderNumberByQua();
    /**
     * 统计各类型的单据在总任务订单数所占的比重
     * @return
     */
    List<Object[]> getBillProportion();


}
