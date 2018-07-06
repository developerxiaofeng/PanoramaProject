package com.lianxi.quanjingtu.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 15:15 2018/7/4
 */
public class Imgeupload {


    /**
     * @param multipartFile 文件名称
     * @param tomcatStaticPath tomcat绝对路径
     * @param appendPath (ex: /...)拼接的路径,不带文件名称
     * @return
     */
    public static String fileUpdata(MultipartFile multipartFile, String tomcatStaticPath, String appendPath) {
        if (multipartFile.isEmpty()){
            return "";
        }
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        appendPath += "/" + fileName;
        // 解决中文问题，liunx下中文路径，图片显示问题
        File dest = new File(tomcatStaticPath + "/" +appendPath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(dest);
            return fileName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @param multipartFile 文件名称
     * @param tomcatStaticPath tomcat路径
     * @param appendPath 文件路径
     * @param fileName 文件名称
     * @return
     */
    public static String fileUpdata(MultipartFile multipartFile,String tomcatStaticPath,String appendPath,String fileName){
        if (multipartFile.isEmpty()){
            return "";
        }
        String fileUrlPath = "";
        // 获取文件名
        String tempFileName = multipartFile.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = tempFileName.substring(tempFileName.lastIndexOf("."));
        // 文件上传后的路径
        appendPath += "/" + fileName;
        File dest = new File(tomcatStaticPath + appendPath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return "/files" + appendPath;
    }

    /**
     *
     * @param multipartFile 文件
     * @param tomcatStaticPath tomcat路径
     * @param appendPath  文件路径 /..
     * @param fileName 文件名称
     * @return
     */
    public static Map<String,String> fileUploadImage(MultipartFile multipartFile, String tomcatStaticPath, String appendPath, String fileName){
        if (multipartFile.isEmpty()){
            return null;
        }
        // 获取文件名
        String tempFileName = multipartFile.getOriginalFilename();
        if (!CustomRegEx.fileTypeImage(tempFileName))
        {
            return null;
        }
        Map<String,String> tempMap = new HashMap<>();
        String fileUrlPath = "";
        // 获取文件的后缀名
        String suffixName = tempFileName.substring(tempFileName.lastIndexOf("."));
        // 操作小图片路径
        String fileImageMin = appendPath + "/" + fileName+"Min"+suffixName;
        // 文件上传后的路径
        appendPath += "/" + fileName + suffixName;
        // 持久化原图
        File dest = new File(tomcatStaticPath + appendPath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(dest);
            tempMap.put("maxImageURL","/files"+appendPath);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        ImageTransformer.reduceImg(dest,tomcatStaticPath + fileImageMin,250.0,1.0);
        tempMap.put("minImageURL","/files"+fileImageMin);
        return tempMap;
    }
}
