package com.sup.company.busin;

import java.util.List;

import com.pm.notice.bean.Notice;
import com.pm.organize.bean.UserAccount;

public interface ComNoticeBusin {

    /**
     * SpringBean的名称
     */
    public static String NOTICEBUSIN = "comnoticeBusinImpl";
    
    

  
    /**
     * 获得Notice
     * 
     * @param id ID
     * @return Notice
     */
    Notice getNotice(String id);

    /**
     * 保存通知提醒
     * 
     * @param notice
     * @param userIds 发送人集合
     */
    void saveNotice(Notice notice, String[] userIds);

    /**
     * 查询所有收件人
     * 
     * @param notice 通知
     * @return List<UserAccount>
     */
    List<UserAccount> getUserAccountList(String noticeId);

    /**
     * 获得消息列表
     * 
     * @param userAccount 用户
     * @param noticeIsRead 是否阅读状态，true：已读
     * @return List<Notice>
     */
    List<Notice> getNoticeList(UserAccount userAccount, boolean noticeIsRead);

    /**
     * 获得未读消息条数
     * 
     * @param userId 用户ID
     * @return 条数
     */
    int countListNotice(String userId);
}
