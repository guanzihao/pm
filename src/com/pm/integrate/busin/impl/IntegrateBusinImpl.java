package com.pm.integrate.busin.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.integrate.bean.Integrate;
import com.pm.integrate.busin.IntegrateBusin;

/**
 * 公司信息Service
 * 
 * @author FYL
 */

@Service
@SuppressWarnings("unchecked")
public class IntegrateBusinImpl implements IntegrateBusin {

    @Autowired
    private BusinApi businApi;

    @Autowired
    public FrameworkBusin frameworkBusin;

    public Integrate getIntegrate(String id) {
        return (Integrate) businApi.get(Integrate.class, id);
    }

    @Override
    public List<Integrate> getIntegrate() {
        return (List<Integrate>) businApi.getList(Integrate.class);
    }

  
}
