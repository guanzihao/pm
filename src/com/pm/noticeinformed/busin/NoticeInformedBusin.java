package com.pm.noticeinformed.busin;

import com.pm.noticeinformed.bean.NoticeInformed;

public interface NoticeInformedBusin {

    /**
     * SpringBean的名称
     */
    public static String NOTICEINFORMEDBUSIN = "noticeInformedBusinImpl";

    /**
     * 获得NoticeInformed
     * 
     * @param id ID
     * @return Notice
     */
    NoticeInformed getNoticeInformed(String id);
}
