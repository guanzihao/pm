package com.sup.company.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.pm.notice.bean.Notice;
import com.pm.notice.bean.NoticeUser;
import com.pm.notice.busin.NoticeBusin;
import com.pm.organize.bean.UserAccount;
import com.sup.company.busin.ComNoticeBusin;

/**
 * 通知提醒Service
 * 
 * @author FYL
 */

@SuppressWarnings("unchecked")
@Service
public class ComNoticeBusinImpl implements ComNoticeBusin {

    @Autowired
    private BusinApi businApi;

    public Notice getNotice(String id) {
        return (Notice) businApi.get(Notice.class, id);
    }

    public void saveNotice(Notice notice, String[] userIds) {
        businApi.save(notice);
        businApi.remove(notice.getNoticeUserList());
        if (userIds != null) {
            for (String id : userIds) {
                UserAccount userAccount = (UserAccount) businApi.get(UserAccount.class, id);
                if (userAccount != null) {
                    NoticeUser noticeUser = new NoticeUser();
                    noticeUser.setNotice(notice);
                    noticeUser.setUserAccount(userAccount);
                    businApi.save(noticeUser);
                }
            }
        }
    }

    public List<UserAccount> getUserAccountList(String noticeId) {
        return businApi.getQueryList(
                "from UserAccount where id in (select userAccount.id from NoticeUser where notice.id = ?)",
                new Object[] { noticeId });
    }

    public List<Notice> getNoticeList(UserAccount userAccount, boolean noticeIsRead) {
        return businApi
                .getQueryList(
                        "from Notice a where a.noticeIsRead = ? and a.noticeDraft = ? and a.id in (select notice.id from NoticeUser where userAccount.id = ?)",
                        new Object[] { noticeIsRead, false, userAccount.getId() });
    }

    public int countListNotice(String userId) {
        return businApi
                .getQueryPageSize(
                        "select a.id from Notice a where a.noticeIsRead = ? and a.noticeDraft = ? and a.id in (select notice.id from NoticeUser where userAccount.id = ?)",
                        new Object[] { false, false, userId });
    }

 
}
