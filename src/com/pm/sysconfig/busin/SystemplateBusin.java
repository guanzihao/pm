package com.pm.sysconfig.busin;


import com.pm.sysconfig.bean.Systemplate;

/**
 * 邮件信息模板
 * @author hly
 *
 */



public interface SystemplateBusin {
    
    
    /**
     * 根据ID查询邮件信息模板
     * 
     * @param id ID
     */
    Systemplate getSystemplate(String id);
    
    /**
     * 根据类型查询邮件信息模板
     * 
     * @param id ID
     */
    Systemplate getSystemplatetype(String type);
    
}
