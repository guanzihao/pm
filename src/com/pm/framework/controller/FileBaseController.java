package com.pm.framework.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pm.core.controller.BaseController;
import com.pm.core.util.DoubleUtil;
import com.pm.core.util.StringUtil;
import com.pm.framework.bean.UploadFile;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.framework.util.FileUtil;
import com.pm.framework.util.GlobalUtil;

public class FileBaseController extends BaseController {
    private static Logger LOGGER = Logger.getLogger(FileBaseController.class);

    @Autowired
    public FrameworkBusin frameworkBusin;

    /**
     * 获取新的文件名
     * 
     * @return
     */
    public String getFileName() {
        return frameworkBusin.getNumberLog(GlobalUtil.getValue("upfile.code"));
    }

    /**
     * 获得服务器临时文件路径
     * 
     * @param filePath
     * @return 文件路径
     */
    public String getFilePath(String filePath) {
        return request.getSession().getServletContext().getRealPath("/upfile") + File.separator + filePath;
    }

    /**
     * 统一上传文件
     * 
     * @param file
     * @return
     */
    public UploadFile addUploadFile(MultipartFile file, String metaColums) {
        try {
            if (FileUtil.checkFileType(file.getOriginalFilename())) {
                String fileName = getFileName();
                FileCopyUtils.copy(file.getBytes(), FileUtil.getFile(fileName));
                UploadFile uploadFile = new UploadFile();
                uploadFile.setFileName(file.getOriginalFilename());
                uploadFile.setFilePathName(fileName);
                uploadFile.setFileSize(DoubleUtil.getDouble(Double.valueOf(file.getSize()) / 1024 / 1024));
                uploadFile.setMetaColums(metaColums);
                businApi.save(uploadFile);
                return uploadFile;
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }

    /**
     * 统一删除文件
     * 
     * @param id
     */
    public void removeUploadFile(String id) {
        UploadFile upFile = (UploadFile) businApi.get(UploadFile.class, id);
        if (upFile != null) {
            frameworkBusin.removeUploadFileList(upFile);
        }
    }

    /**
     * 下载附件
     * 
     * @param id
     */
    public void downUploadFile(String id) {
        try {
            UploadFile upFile = (UploadFile) businApi.get(UploadFile.class, id);
            if (upFile != null) {
                String encoded = new String(upFile.getFileName().getBytes("GBK"), "ISO-8859-1");
                response.addHeader("Content-Disposition", "attachment; filename=" + encoded);
                FileInputStream fis = new FileInputStream(FileUtil.getFile(upFile.getFilePathName()));
                FileCopyUtils.copy(fis, response.getOutputStream());
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
    }

    /**
     * 下载附件
     * 
     * @param id
     */
    public void downUploadFile(String fileName, InputStream is) {
        try {
            String encoded = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=" + encoded);
            FileCopyUtils.copy(is, response.getOutputStream());
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
    }

    /**
     * 附件转换成可显示临时文件
     * 
     * @param id 文件ID
     * @param flush 是否强制替换
     * @return /upfile
     */
    public File changeUploadFile(UploadFile upFile, boolean flush) {
        try {
            if (upFile != null) {
                String postfix = StringUtil.getPostfix(upFile.getFileName());
                File file = FileUtil.getFile(upFile.getFilePathName());
                if (file != null && file.exists()) {
                    String tmpPath = getFilePath(upFile.getFilePathName() + StringUtil.POINT + postfix);
                    File outFile = new File(tmpPath);
                    FileUtil.copyFile(file, outFile, flush);
                    return outFile;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }

    /**
     * 下载文件 GC
     * 
     * @param path
     * @tag 2016年10月10日
     */
    public void responseDownUploadFile(String path) {
        try {
            String filename = path.substring(path.lastIndexOf("\\") + 1);
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            FileInputStream fis = new FileInputStream(path);
            FileCopyUtils.copy(fis, response.getOutputStream());
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
    }

    /**
     * 显示图片
     * 
     * @param id
     */
    public void downUploadImage(String imgName) {
        try {
            if (imgName != null) {
                String encoded = new String(imgName.getBytes("GBK"), "ISO-8859-1");
                response.addHeader("Content-Disposition", "attachment; filename=" + encoded);
                FileInputStream fis = new FileInputStream(FileUtil.getFile(imgName));
                FileCopyUtils.copy(fis, response.getOutputStream());
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
    }
}
