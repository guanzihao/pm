package com.pm.cms.busin;

import com.pm.cms.bean.CMSBoard;

public interface CMSBoardBusin {
    
    CMSBoard getCMSBoard(String id);
    
    void saveCMSBoard(CMSBoard cmsBoard);

    void updateCMSBoard(String id);
}
