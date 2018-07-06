package com.lianxi.quanjingtu.entry;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 17:14 2018/7/5
 */
public class PanoramaDO {
    private String id;
    private String baseFileName;  //文件名
    private String title;//主体

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseFileName() {
        return baseFileName;
    }

    public void setBaseFileName(String baseFileName) {
        this.baseFileName = baseFileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "PanoramaDO{" +
                "id='" + id + '\'' +
                ", baseFileName='" + baseFileName + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
