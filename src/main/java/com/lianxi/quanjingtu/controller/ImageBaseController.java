package com.lianxi.quanjingtu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 17:02 2018/7/5
 */
@Controller
public class ImageBaseController {
    @RequestMapping("/test")
    public ModelAndView test01(){
        //System.out.println("+_++++");
        ModelAndView mv=new ModelAndView("vr.html");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uploadId", Integer.parseInt("1"));
        map.put("title","nihao");
        map.put("vid",2);
        mv.addAllObjects(map);
        // mv.setViewName("share");
        return  mv ;
    }


    @RequestMapping("share")
    public ModelAndView share(Model model, HttpServletRequest request,
                              @RequestParam(value = "file") MultipartFile file) {

        /**


         //做处理
         String newGilePath = path + "\\"+ upload.getId() + "\\";
         FileUtil.moveFile(filePath, newGilePath);
         String dpath = request.getSession().getServletContext().getRealPath("vshow");
         String[] fn1 = {};
         String[] fn2 = {};
         String title = "xxxxx";
         String music = "vshow\\backgroundmusic\\default.mp3";
         String uploadId = upload.getId()+"";
         try {
         CmdBat.setKrpano(dpath, uploadId , path + "/", fn1, fn2, title,music);
         } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }
         try {
         Thread.sleep(4000);
         mv = new ModelAndView();
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("uploadId", Integer.parseInt(uploadId));
         map.put("title",title);
         mv.addAllObjects(map);
         mv.setViewName("share");
         } catch (NumberFormatException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }

         return mv;


         *
         */
        return null;
    }
}
