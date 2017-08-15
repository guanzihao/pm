package com.pm.inter.busin;


import java.util.List;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.inter.bean.Interface;
import com.pm.inter.bean.InterfaceConfig;
import com.pm.inter.bean.InterfaceConfigModel;
import com.pm.sysconfig.bean.Enumitems;
/**
 * 客户供应商接口表业务处理
 * @author Administrator
 *
 */
public interface InterfaceConfigBusin {
    /**
     * 保存客户供应商接口表
     * @param interConfig
     */
    public  void saveInterfaceConfig(InterfaceConfig interConfig);
    /**
     * 删除客户供应商接口表
     * @param id
     */
    public void deleteIntefaceConfig(String  id);
    /**
     * 根据id查询接口配置信息
     * @param id
     * @return
     */
    public InterfaceConfig getInterfaceConfig(String id); 
    /**
     * 查询枚举项目信息
     * @return
     */
    public List<Interface> findAllUseInterface();
    /**
     * 查询客户信息
     * @return
     */
    public List<CompanyInfo> findAllUseCompanyInfo();
    /**
     * 查询供应商信息
     * @return
     */
    public List<SupCompanyInfo> findAllUseSup();
    /**
     * 根据id查询枚举项目
     * @param id
     * @return
     */
    public List<Enumitems> findAllUseEnumitems(String id);
    /**
     * 根据ID查询枚举项目
     * @param id
     * @return
     */
    public Enumitems getEnumitems(String id);
    /**
     * 根据ID查询接口配置详情信息
     * @param id
     * @return
     */
    public InterfaceConfigModel getInterfaceConfigModel(String id);
   
}
