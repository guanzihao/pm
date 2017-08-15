package com.pm.sysconfig.busin;
/**
 * 网站栏目表业务处理
 * @author Administrator
 *
 */

import org.springframework.web.multipart.MultipartFile;

import com.pm.sysconfig.bean.Interface;;

public interface SysInterfaceService {
    
    Interface getInterface(String id);
    
    void saveInterface(Interface cmsColumns,MultipartFile multipartFile);
    
    void updateInterface(Interface cmsColumns,MultipartFile multipartFile);
    
    void removeInterface(String id);
    
    
    
    
    
}
