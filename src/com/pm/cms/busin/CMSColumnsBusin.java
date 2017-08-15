package com.pm.cms.busin;
/**
 * 网站栏目表业务处理
 * @author Administrator
 *
 */

import org.springframework.web.multipart.MultipartFile;

import com.pm.cms.bean.CMSColumns;

public interface CMSColumnsBusin {
    
    CMSColumns getCMSColumns(String id);
    
    void saveCMSColumns(CMSColumns cmsColumns,MultipartFile multipartFile);
    
    void updateCMSColumns(CMSColumns cmsColumns,MultipartFile multipartFile);
    
    void removeCMSColumns(String id);
    
    
    
    
    
}
