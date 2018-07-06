package com.lianxi.quanjingtu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: CustomRegEx
 * @description: 正则表达式
 * @author: Ding
 * @create: 2018-04-28 14:03
 **/
public class CustomRegEx {

    //    private static String fileTypeImageRegEx = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
    // 匹配图片格式
    private static String fileTypeImageRegEx = ".+(.JPG|.jpg|.PNG|.png)$";
    // 匹配uuid
    private static String uuidRegEx = "^[0-9a-z]{8}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{12}$";

    /**
     * 传文件名 带后缀
     * @param str
     * @return
     */
    public static boolean fileTypeImage(String str)
    {
        // 编译正则表达式
        Pattern pattern = Pattern.compile(fileTypeImageRegEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        return matcher.find();
    }

    public static boolean fileTypeUUID(String uuid){
        // 编译正则表达式
        Pattern pattern = Pattern.compile(uuidRegEx);
        // 忽略大小写的写法
        Matcher matcher = pattern.matcher(uuid);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        return matcher.find();
    }
}

