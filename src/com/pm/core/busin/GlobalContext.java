package com.pm.core.busin;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获得Spring的Bean
 * 
 * @author FYL
 */

@Component
public class GlobalContext implements ApplicationContextAware {

    private static ApplicationContext SPRINGCONTEXT;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SPRINGCONTEXT = applicationContext;
    }

    public static ApplicationContext getSpringContext() {
        return SPRINGCONTEXT;
    }

    public static BusinApi getBusinApi() {
        return (BusinApi) getSpringContext().getBean(BusinApi.BUSINAPI);
    }

    public static Object getBean(String name) {
        return getSpringContext().getBean(name);
    }
}
