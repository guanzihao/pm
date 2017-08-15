package com.pm.framework.scheduled;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.core.busin.BusinApi;
import com.pm.core.util.DateUtil;
import com.pm.framework.util.GlobalUtil;

/**
 * 自动清除系统日志
 * 
 * @author youliang.fang
 */

@Component
public class MethodLogScheduled {
    private static Logger LOGGER = Logger.getLogger(MethodLogScheduled.class);
    private static final String LOGDAYS = "log.days";

    @Autowired
    private BusinApi businApi;
    
    
    
    public void clearLog() {
        int logDays = Integer.valueOf(GlobalUtil.getValue(LOGDAYS));
        Date date = DateUtil.getDate(DateUtil.getDate(), -logDays);
        Query query = businApi.getQuery("delete from MethodLog where createDate < ?", new Object[] { date });
        int count = query.executeUpdate();

        LOGGER.info("delete from MethodLog count:" + count + ", createDate < "
                + DateUtil.getStringDate(date, DateUtil.DateFormat1));
    }
 
}
