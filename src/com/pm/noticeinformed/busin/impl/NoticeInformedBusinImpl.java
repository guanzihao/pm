package com.pm.noticeinformed.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.pm.noticeinformed.bean.NoticeInformed;
import com.pm.noticeinformed.busin.NoticeInformedBusin;

/**
 * 公告通知提醒Service
 * 
 * @author ZZY_SIVE
 *
 */

@Service
public class NoticeInformedBusinImpl implements NoticeInformedBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public NoticeInformed getNoticeInformed(String id) {
        return (NoticeInformed) businApi.get(NoticeInformed.class, id);
    }

}
