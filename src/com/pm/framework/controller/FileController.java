package com.pm.framework.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pm.framework.bean.UploadFile;
import com.pm.framework.util.FileUtil;

@Controller
@RequestMapping("/framework/file")
public class FileController extends FileBaseController {
    private static Logger LOGGER = Logger.getLogger(FileController.class);

    /**
     * AJAX上传文件
     * 
     * @return 文件ID
     */
    @RequestMapping(value = "/ajaxUploadFile")
    @ResponseBody
    public void ajaxUploadFile(String metaColums) {
        String upFileId = "";
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                UploadFile uploadFile = addUploadFile(entity.getValue(), metaColums != null ? metaColums : "");
                if (uploadFile != null) {
                    if (upFileId.length() > 0) {
                        upFileId += ",";
                    }
                    upFileId += uploadFile.getId();
                }
            }
        } catch (Exception e) {
            LOGGER.info(e, e);
        }
        writer(upFileId);
    }

    /**
     * 删除文件
     * 
     * @param id 文件ID
     */
    @RequestMapping("/removeFile/{id}")
    @ResponseBody
    public void removeFile(@PathVariable String id) {
        String upFileId = "0";
        try {
            removeUploadFile(id);
            upFileId = "1";
        } catch (Exception e) {
            LOGGER.info(e, e);
        }
        writer(upFileId);
    }

    /**
     * 下载附件
     * 
     * @param id 文件ID
     */
    @RequestMapping("/downFile/{id}")
    public void downFile(@PathVariable String id) {
        downUploadFile(id);
    }
}
