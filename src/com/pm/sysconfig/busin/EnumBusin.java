package com.pm.sysconfig.busin;

import java.util.List;

import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;

/**
 * 网站栏目表业务处理
 * @author Administrator
 *
 */



public interface EnumBusin {
    
    
    /**
     * 根据ID查询枚举
     * 
     * @param id ID
     * @return CompanyInfo
     */
    Enumtype getEnumtype(String id);
    
    /**
     * 更具名称查询枚举表信息
     * @param id
     * @return
     */
    Enumtype getEnumtypeByName(String name);
    
    /**
     * 根据ID查询枚举明细信息
     * 
     * @param id ID
     * @return CompanyInfo
     */
    Enumitems getEnumitems(String id);
    
    /**
     * 更具枚举表信息查询枚举明细表信息
     * @param enumtype
     * @return
     */
    List<Enumitems> getEnumitemList(Enumtype enumtype);
    
    /**
     * 根据类型id得到所有的枚举类型明细
     * @param enumtype
     * @return
     */
    List<Enumitems> getEnumTypeIdByEnumitemList(String enumTypeId);
    
    
    /**
     * 更具枚举表信息,以及类型名称，查询枚举明细表信息
     * @param enumtype
     * @return
     */
    Enumitems getEnumitem(Enumtype enumtype,String name);
}
