package com.pm.sysconfig.busin.impl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.sysconfig.bean.Systemplate;
import com.pm.sysconfig.busin.SystemplateBusin;

/**
 * 邮件信息模板Service
 * 
 * @author hly
 */

@Service
@SuppressWarnings("unchecked")
public class SystemplateBusinImpl implements SystemplateBusin {

    @Autowired
    private BusinApi businApi;

    @Autowired
    public FrameworkBusin frameworkBusin;

    @Override
    public Systemplate getSystemplate(String id) {
        
        return (Systemplate) businApi.get(Systemplate.class, id);
    }

    @Override
    public Systemplate getSystemplatetype(String type) {
        return (Systemplate)  businApi.getQueryObject("from Systemplate where temptype = ?", new Object[] {type});
    }

  
}
