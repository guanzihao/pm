package com.pm.cms.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pm.cms.bean.CMSColumns;
import com.pm.cms.bean.CMSContent;
import com.pm.cms.busin.CMSContentBusin;
import com.pm.cms.util.FileUtils;
import com.pm.core.busin.BusinApi;
import com.pm.framework.util.GlobalUtil;

@Service
@SuppressWarnings("unchecked")
public class CMSContentBusinImpl implements CMSContentBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public void saveCMSContent(CMSContent cmsContent, String columnID, MultipartFile img, MultipartFile bigImg) {

        CMSColumns columns = new CMSColumns();
        columns.setId(columnID);
        cmsContent.setCmsColumns(columns);
        String imgFileName = FileUtils.transferToFile(img, "/content");
        cmsContent.setImg("content/"+imgFileName);
        String bigImgFileName = FileUtils.transferToFile(bigImg, "/content");
        cmsContent.setBigImg("content/"+bigImgFileName);
        int num = 0;
        Query query = businApi.getQuery("SELECT  MAX(sort) FROM CMSContent ", null);
        try {
            List<Integer> list = query.list();
            if (list != null) {
                num = list.get(0) + 1;
            }
        } catch (Exception e) {
            
        }
        cmsContent.setSort(num);
        businApi.save(cmsContent);
    }

    @Override
    public void updateCMSContent(CMSContent cmsContent, String columnID, MultipartFile img, MultipartFile bigImg) {
        CMSColumns columns = new CMSColumns();
        columns.setId(columnID);
        cmsContent.setCmsColumns(columns);
        String imgFileName = FileUtils.transferToFile(img, "/content");
        if (!"".equals(imgFileName)) {
            FileUtils.deleteFile("/content", cmsContent.getImg());
            cmsContent.setImg("content/"+imgFileName);
        }
        String bigImgFileName = FileUtils.transferToFile(bigImg, "/content");
        if (!"".equals(bigImgFileName)) {
            FileUtils.deleteFile("/content", cmsContent.getBigImg());
            cmsContent.setBigImg("content/"+bigImgFileName);
        }
        businApi.save(cmsContent);
    }

    @Override
    public void removeCMSContent(String id) {
        CMSContent content = (CMSContent) businApi.get(CMSContent.class, id);
        if (content != null) {
            businApi.remove(content);
        }
    }

    @Override
    public CMSContent getCMSContent(String id) {
        return (CMSContent) businApi.get(CMSContent.class, id);
    }

}
