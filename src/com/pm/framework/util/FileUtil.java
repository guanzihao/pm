package com.pm.framework.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;

import com.pm.core.util.StringUtil;

public class FileUtil {
    private static Logger LOGGER = Logger.getLogger(FileUtil.class);

    /**
     * 获得服务器文件所在路径
     * 
     * @return 路径
     */
    public static String getFilePath() {
        String path = GlobalUtil.getValue("upfile.path");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * 获得服务器临时文件所在路径
     * 
     * @return 临时路径
     */
    public static String getTempFilePath() {
        String path = GlobalUtil.getValue("tempfile.path");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * 获得服务器文件
     * 
     * @param fileName 文件名
     * @return 文件
     */
    public static File getFile(String fileName) {
        try {
            return new File(getFilePath() + File.separator + fileName);
        } catch (Exception e) {
            LOGGER.error(e, e);
            return null;
        }
    }

    /**
     * 删除服务器文件
     * 
     * @param fileName 文件名
     */
    public static void removeFile(String fileName) {
        try {
            File file = getFile(fileName);
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
    }

    /**
     * 复制文件
     * 
     * @param inFile 输入
     * @param outFile 输出
     * @param flush 是否强制转换
     */
    public static void copyFile(File inFile, File outFile, boolean flush) {
        try {
            if (flush) {
                FileCopyUtils.copy(inFile, outFile);
            } else {
                if (!outFile.exists()) {
                    FileCopyUtils.copy(inFile, outFile);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
    }

    /**
     * 判断文件后缀是否合法
     * 
     * @param fileName 文件名称
     * @return true
     */
    public static boolean checkFileType(String fileName) {
        String fileTypes = GlobalUtil.getValue("upfile.filetype");
        if (!StringUtil.isEmpty(fileTypes)) {
            String[] fileType = fileTypes.split(";");
            for (String type : fileType) {
                if (!StringUtil.isEmpty(type)) {
                    if (fileName.indexOf(type.replace("*", "")) > -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获得随机文件名称
     * 
     * @return YYYYMMDD + 5个随机数
     */
    public static String getFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        return str + rannum;
    }
}
