package com.pm.cms.busin;

import org.springframework.web.multipart.MultipartFile;

import com.pm.cms.bean.CMSContent;

/**
 * 网站内容
 * @author Administrator
 *
 */
public interface CMSContentBusin {
    
    CMSContent getCMSContent(String id);
    
    void saveCMSContent(CMSContent cmsContent,String columnID, MultipartFile img,MultipartFile bigImg);
    
    void updateCMSContent(CMSContent cmsContent,String columnID, MultipartFile img,MultipartFile bigImg);
    
    void removeCMSContent(String id);
}
