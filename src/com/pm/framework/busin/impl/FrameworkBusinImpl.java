package com.pm.framework.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.bean.MetaObject;
import com.pm.core.busin.BusinApi;
import com.pm.core.util.DateUtil;
import com.pm.core.util.StringUtil;
import com.pm.framework.bean.IdentifyingCode;
import com.pm.framework.bean.MailConfig;
import com.pm.framework.bean.MailLog;
import com.pm.framework.bean.NumberLog;
import com.pm.framework.bean.UploadFile;
import com.pm.framework.bean.UploadFileOwner;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.framework.util.FileUtil;
import com.pm.framework.util.MailUtil;

/**
 * FrameWork管理Service
 * 
 * @author FYL
 */

@SuppressWarnings("unchecked")
@Service
public class FrameworkBusinImpl implements FrameworkBusin {

    @Autowired
    private BusinApi businApi;

    public String getNumberLog(String code) {
        NumberLog numberLog = (NumberLog) businApi.getQueryObject("from NumberLog where numberCode = ?", new Object[] { code });
        if (numberLog != null) {
            String numStr = numberLog.getNumberFormat();
            if (StringUtil.indexOf(numStr, "{date}")) {
                String dateStr = DateUtil.getStringDate(DateUtil.getDate(), DateUtil.DateFormat3);
                numStr = numStr.replace("{date}", dateStr);
            }
            if (StringUtil.indexOf(numStr, "{num}")) {
                String numLen = String.valueOf(Integer.valueOf(numberLog.getNumberLast()) + 1);
                while (numLen.length() < numberLog.getNumberLength()) {
                    numLen = "0" + numLen;
                }
                numberLog.setNumberLast(numLen);
                numStr = numStr.replace("{num}", numLen);
                businApi.save(numberLog);
            }
            return numStr;
        }
        return code;
    }

    public MailConfig getMailConfig() {
        return (MailConfig) businApi.getQueryListObject("from MailConfig", null);
    }

    public void sendMailLog(MailLog mailLog) {
        if (mailLog != null) {
            businApi.save(mailLog);
            mailLog.setMailAttFiles(getUploadFileList(mailLog,""));
            MailUtil.sendMail(getMailConfig(), mailLog);
        }
    }

    public void removeUploadFile(UploadFile uploadFile) {
        if (uploadFile != null) {
            try {
                FileUtil.removeFile(uploadFile.getFilePathName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            businApi.remove(uploadFile);
        }
    }

    public void removeUploadFileOwnerList(MetaObject metaObject) {
        if (metaObject != null) {
            String className = metaObject.getClass().getName();
            if (className.indexOf("_$$_") > 0) {
                className = className.substring(0, className.indexOf("_$$_"));
            }
            List<UploadFileOwner> uploadFileOwners = businApi.getQueryList("from UploadFileOwner where className = ? and metaId = ?", new Object[] { className, metaObject.getId() });
            businApi.remove(uploadFileOwners);
        }
    }

    public void removeUploadFileList(UploadFile uploadFile) {
        if (uploadFile != null) {
            List<UploadFileOwner> uploadFileOwners = businApi.getQueryList("from UploadFileOwner where uploadFile.id = ?", new Object[] { uploadFile.getId() });
            businApi.remove(uploadFileOwners);
            this.removeUploadFile(uploadFile);
        }
    }

    public List<UploadFile> getUploadFileList(MetaObject metaObject, String metaColums) {
        if (metaObject != null) {
            String className = metaObject.getClass().getName();
            if (className.indexOf("_$$_") > 0) {
                className = className.substring(0, className.indexOf("_$$_"));
            }
            return businApi.getQueryList("from UploadFile where id in (select uploadFile.id from UploadFileOwner where className = ? and metaId = ? and metaColums = ?)", new Object[] { className, metaObject.getId() , metaColums});
        }
        return null;
    }

    public void saveUploadFileOwner(MetaObject metaObject, String[] ids) {
        if (metaObject != null) {
            String className = metaObject.getClass().getName();
            if (className.indexOf("_$$_") > 0) {
                className = className.substring(0, className.indexOf("_$$_"));
            }
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    UploadFile uploadFile = (UploadFile) businApi.get(UploadFile.class, id);
                    if (uploadFile != null) {
                        UploadFileOwner uploadFileOwner = (UploadFileOwner) businApi.getQueryObject("from UploadFileOwner where className = ? and metaId = ? and uploadFile.id = ? and metaColums = ?", new Object[] { className, metaObject.getId(), uploadFile.getId(), uploadFile.getMetaColums() });
                        if (uploadFileOwner == null) {
                            uploadFileOwner = new UploadFileOwner();
                        }
                        uploadFileOwner.setClassName(className);
                        uploadFileOwner.setMetaId(metaObject.getId());
                        uploadFileOwner.setUploadFile(uploadFile);
                        uploadFileOwner.setMetaColums(uploadFile.getMetaColums());
                        businApi.save(uploadFileOwner);
                    }
                }
            }
        }
    }

    @Override
    public IdentifyingCode getIdentifyingCode(String emailName) {
        return (IdentifyingCode) businApi.getQueryObject("from IdentifyingCode where registemail = ?", new Object[] { emailName });
    }
}
