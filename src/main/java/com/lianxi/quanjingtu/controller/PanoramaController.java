package com.lianxi.quanjingtu.controller;

import com.lianxi.quanjingtu.util.Imgeupload;
import com.lianxi.quanjingtu.util.OpenCVUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 13:44 2018/7/4
 */
@Controller
public class PanoramaController {


    @RequestMapping("/upload")
    public ModelAndView login07(@RequestParam(value = "file", required = false)MultipartFile[] files,
                          @RequestParam(value = "title",defaultValue = "未命名") String title,HttpServletRequest request) {
        ModelAndView mv=new ModelAndView("success.html");
        MultipartFile tempMultipartFile;
        //基本路径
        StringBuffer sb=new StringBuffer();
        String baseURL="E:/demo";
        String tempStr = "/" + UUID.randomUUID().toString();
        for (int j = 0; j < files.length; j++) {
            tempMultipartFile = files[j];
            // 获得上传的文件名称-带有后缀
            String fileNameAndSuffixName = tempMultipartFile.getOriginalFilename();
            // 获取上传的文件名称
            //String fileName = fileNameAndSuffixName.substring(0, fileNameAndSuffixName.lastIndexOf("."));
            String urlPath;
            urlPath = Imgeupload.fileUpdata(tempMultipartFile, "E:/demo", "" + tempStr);
            System.out.println("=====" + urlPath);
            if (j==files.length-2){
                sb.append(baseURL+tempStr+"/"+urlPath);
                break;
            }else if (j<files.length-2){
                sb.append(baseURL+tempStr+"/"+urlPath+",");
            }else {
                System.out.println();
            }

        }
        /*try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //拼接URL
        System.out.println("拼接URL"+sb.toString());
        //调用native
        String result= OpenCVUtil.changeArrValue(sb.toString());

        if(!result.contains(",")){
            mv.setViewName("failure.html");
            return mv;
        }
        //System.out.println(result);
        //复制图片
        String basedirNew=UUID.randomUUID().toString()+","+title;
        String dirNew="D:\\tupian\\img\\"+basedirNew+"\\";
        try {
            copyFile(new File("D:/result.jpg"),new File(dirNew+title+".jpg"),dirNew);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mv.addObject("imgUrl","/img/"+basedirNew+"/"+title+".jpg");
        mv.addObject("title",title);
        mv.addObject("fileName",basedirNew);
        return mv;
    }

    @RequestMapping("/index")
    public String login06() {
        return "upload.html";
    }

     /**
         *    @Description:  复制生成的图片到全景图静态区
         *    @Date:  9:45  2018/7/5
         *    @Params:   * @param null
         */

    public void copyFile(File fromFile, File toFile,String fromUrl) throws IOException {
        File file=new File(fromUrl);
        if (!file.exists()) {
            file.mkdir();
        }
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n=0;
        while((n=ins.read(b))!=-1){
            out.write(b, 0, n);
        }

        ins.close();
        out.close();
    }

}
