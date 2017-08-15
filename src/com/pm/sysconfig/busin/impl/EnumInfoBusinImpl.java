package com.pm.sysconfig.busin.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;
import com.pm.sysconfig.busin.EnumBusin;

/**
 * 公司信息Service
 * 
 * @author FYL
 */

@Service
@SuppressWarnings("unchecked")
public class EnumInfoBusinImpl implements EnumBusin {

    @Autowired
    private BusinApi businApi;

    @Autowired
    public FrameworkBusin frameworkBusin;

    @Override
    public Enumtype getEnumtype(String id) {
      
        return (Enumtype) businApi.get(Enumtype.class, id);
    }

    @Override
    public Enumitems getEnumitems(String id) {
        return (Enumitems) businApi.get(Enumitems.class, id);
    }

    @Override
    public Enumtype getEnumtypeByName(String name) {
        
        return (Enumtype) businApi.getQueryObject("from Enumtype where name = ?", new Object[] { name});
    }

    @Override
    public List<Enumitems> getEnumitemList(Enumtype enumtype) {
        return businApi.getQueryList("from Enumitems where typeID = ?", new Object[] { enumtype.getId()});
    }

    @Override
    public Enumitems getEnumitem(Enumtype enumtype, String name) {
       
        return (Enumitems) businApi.getQueryObject("from Enumitems where enumtype = ? and name = ?", new Object[] { enumtype.getId(),name});
    }
    @Override
    public List<Enumitems> getEnumTypeIdByEnumitemList(String enumTypeId) {
        return businApi.getQueryList("from Enumitems where typeID = ?", new Object[] {enumTypeId});
    }
}
