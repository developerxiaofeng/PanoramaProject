package com.lianxi.quanjingtu.controller;

import com.lianxi.quanjingtu.entry.PanoramaDO;
import com.lianxi.quanjingtu.krpano.Room;
import com.lianxi.quanjingtu.util.CmdBat;
import com.lianxi.quanjingtu.util.ConstantBank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 17:09 2018/7/5
 */
@Controller
public class FileLibraryController {
         /**
             *    @Description:  从文件中获取全景图
             *    @Date:  17:13  2018/7/5
             *    @Params:   * @param null
             */

    @RequestMapping("/all")
    public ModelAndView list(Model model){
       // List<File> wjList = new ArrayList<File>();//新建一个文件集合
        List<PanoramaDO> list=new ArrayList<>();
        File file=new File(ConstantBank.PANORAMA_BANK_URL);
        ModelAndView mv=new ModelAndView("list.html");
        File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {//判断是否为文件
              //  wjList.add(fileList[i]);
                String directoryName=fileList[i].getName();

                if (!directoryName.equals(ConstantBank.EXCLUSIVE_SECOND_DIR_NAEM)&&!directoryName.equals(ConstantBank.EXCLUSIVE_FIRST_DIR_NAME)){
                    //分割文件名
                    String title =directoryName.substring(directoryName.indexOf(",")+1);
                    PanoramaDO panoramaDO=new PanoramaDO();
                    panoramaDO.setId(directoryName);
                    if (title==null||title.equals("")){
                        panoramaDO.setTitle("未命名");
                    }else {
                        panoramaDO.setTitle(title);
                    }
                    list.add(panoramaDO);
                    //System.out.println(directoryName);
                    //System.out.println(title);
                }


            }
        }
        System.out.println(list);
        mv.addObject("list",list);
        return mv;

    }

    @RequestMapping("/{id}/temp")
    public ModelAndView jump(@PathVariable("id") String vid){

        ModelAndView mv =new ModelAndView("vr.html");
        //mv.getView().
        mv.addObject("vid",vid);
        String title =vid.substring(vid.indexOf(",")+1);
        if (title==null||title.equals("")){
            mv.addObject("title","未命名");
        }else {
            mv.addObject("title",title);
        }
        System.out.println("===>"+vid);
       return mv;
    }

    @RequestMapping("/compound/{fileName}/{title}")
    public ModelAndView compound(@PathVariable("fileName") String fileName,
                            @PathVariable("title") String title){

        ModelAndView mv =new ModelAndView("redirect:/all");
        //mv.getView().
        Room r = new Room();
        //项目的位置
        String dpath = "D:\\tupian\\vshow";
        //全景图的文件名
        String file = fileName;
        String[] fn1 = { "2",
                "3" };
        String[] fn2 = { "客厅", "卧室","大客厅" };
        //String title = "哈哈哈哈哈哈哈哈";
        String music = "vshow/backgroundmusic/default.mp3";
        try {
            CmdBat.setKrpano(r,dpath, file, fn1, fn2, title,music);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("上传失败");
        }



        return mv;
    }


}
