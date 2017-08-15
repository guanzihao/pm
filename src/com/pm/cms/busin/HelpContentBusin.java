package com.pm.cms.busin;

import org.springframework.web.multipart.MultipartFile;

import com.pm.cms.bean.HelpContent;

/**
 * 网站内容
 * @author Administrator
 *
 */
public interface HelpContentBusin {
    
    HelpContent getCMSContent(String id);
    
    void saveCMSContent(HelpContent cmsContent,String columnID, MultipartFile img,MultipartFile bigImg);
    
    void updateCMSContent(HelpContent cmsContent,String columnID, MultipartFile img,MultipartFile bigImg);
    
    void removeCMSContent(String id);
}
