package com.pm.integrate.busin;

import java.util.List;

import com.pm.integrate.bean.Integrate;


public interface IntegrateBusin {

    /**
     * SpringBean的名称
     */
    public static String COMPANYINFOBUSIN = "integrateBusinImpl";


    /**
     * 根据ID查询系统配置
     * 
     * @param id ID
     * @return CompanyInfo
     */
    Integrate getIntegrate(String id);
    
    /**
     * 根据查询所有系统配置
     * 
     * @param id ID
     * @return CompanyInfo
     */
    List<Integrate> getIntegrate();
    
   
}
