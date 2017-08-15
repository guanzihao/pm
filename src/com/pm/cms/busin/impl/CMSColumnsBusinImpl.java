package com.pm.cms.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pm.cms.bean.CMSColumns;
import com.pm.cms.bean.CMSContent;
import com.pm.cms.busin.CMSColumnsBusin;
import com.pm.cms.util.FileUtils;
import com.pm.core.bean.MetaObject;
import com.pm.core.busin.BusinApi;
import com.pm.framework.util.GlobalUtil;

@Service
@SuppressWarnings("unchecked")
public class CMSColumnsBusinImpl implements CMSColumnsBusin {
    @Autowired
    private BusinApi businApi;

    @Override
    public void saveCMSColumns(CMSColumns cmsColumns, MultipartFile multipartFile) {
        String imgFileName = FileUtils.transferToFile(multipartFile, "/columns");
        cmsColumns.setImg("columns/"+imgFileName);
        int num = 0;
        Query query = businApi.getQuery("SELECT  MAX(columnSort) FROM CMSColumns ", null);
        try {
            List<Integer> list = query.list();
            if (list != null) {
                num = list.get(0) + 1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        cmsColumns.setColumnSort(num);
        businApi.save(cmsColumns);
    }
    @Override
    public void updateCMSColumns(CMSColumns cmsColumns, MultipartFile multipartFile) {

        String imgFileName = FileUtils.transferToFile(multipartFile, "/columns");
        if (!"".equals(imgFileName)) {
            FileUtils.deleteFile("/columns", cmsColumns.getImg());
            cmsColumns.setImg("columns/"+imgFileName);
        }
        businApi.save(cmsColumns);
    }
    @Override
    public void removeCMSColumns(String id) {

        CMSColumns cmsColumns = (CMSColumns) businApi.get(CMSColumns.class, id);
        if (cmsColumns != null) {
            businApi.remove(cmsColumns.getCmsContents());
            businApi.remove(cmsColumns);
        }
    }

    @Override
    public CMSColumns getCMSColumns(String id) {
        return (CMSColumns) businApi.get(CMSColumns.class, id);
    }

}
