package com.lianxi.quanjingtu.util;


import java.io.UnsupportedEncodingException;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 8:51 2018/6/26
 */
public class OpenCVUtil {
    static {
     //这里的system.load用来加载C++生成的动态链接库，加载实际自己也可以决定不一定非的是静态的。
        System.loadLibrary("OpenCVUtil");
    }

    public static native String changeArrValue(String str);
    public static String change(String url) throws UnsupportedEncodingException {
        /*String base="D:/quanjingtu/aa"+"/";
        int length=6;
        String url="";

        for (int i=1;i<=length;i++){
            if (i==1){
                url=url+base+i+".jpg";
            }else {
                url=url+","+base+i+".jpg";
            }

        }*/
        //url="E:/demo/d50742b7-3add-4dbd-bd26-a12e9495f4fa/1.jpg,E:/demo/d50742b7-3add-4dbd-bd26-a12e9495f4fa/2.jpg,E:/demo/d50742b7-3add-4dbd-bd26-a12e9495f4fa/3.jpg,E:/demo/d50742b7-3add-4dbd-bd26-a12e9495f4fa/4.jpg,E:/demo/d50742b7-3add-4dbd-bd26-a12e9495f4fa/5.jpg,E:/demo/d50742b7-3add-4dbd-bd26-a12e9495f4fa/6.jpg";
        //System.out.println(url);
        String temp =new String(changeArrValue(url).getBytes(),"GBK");
        System.out.println(temp);

        return temp;
    }
}
