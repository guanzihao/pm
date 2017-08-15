package com.sup.integrate.busin.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.pm.core.model.Contexts;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.integrate.bean.Integrate;
import com.sup.integrate.bean.Integrates;
import com.sup.integrate.busin.IntegrateBusins;


/**
 * 公司信息Service
 * 
 * @author FYL
 */

@Service
public class IntegrateBusinImpls implements IntegrateBusins {

    @Autowired
    private BusinApi businApi;

    @Autowired
    public FrameworkBusin frameworkBusin;

    public Integrates getIntegrate(String id) {
        return (Integrates) businApi.get(Integrates.class, id);
    }

    @Override
    public Integrates getIntegrates(String userid, String interType) {
        
        return (Integrates) businApi.getQueryObject("from Integrates a where a.userAccount = ? and a.integLogins = ? and a.loginstate=?", new Object[]{userid,interType,Contexts.Y});
    }

    @Override
    public Integrate getIntegrateName(String id) {
        return (Integrate) businApi.getQueryObject("from Integrate a where  a.id = ? and a.loginstate=?", new Object[]{id,Contexts.Y});
    }
   
}
