package com.sup.integrate.busin;

import com.pm.integrate.bean.Integrate;
import com.sup.integrate.bean.Integrates;

public interface IntegrateBusins {

    /**
     * SpringBean的名称
     */
    public static String COMPANYINFOBUSIN = "integrateBusinImpl";


    /**
     * 根据ID查询登录
     * 
     * @param id ID
     * @return CompanyInfo
     */
    Integrates getIntegrate(String id);
    
    /**
     * 根据用户查询配置的单点登录
     * 
     * @param id ID
     * @return CompanyInfo
     */
    Integrates getIntegrates(String userid,String interType);
    
    /**
     * 根据用户查询配置的单点登录
     * 
     * @param id ID
     * @return CompanyInfo
     */
    Integrate getIntegrateName(String id);
}
