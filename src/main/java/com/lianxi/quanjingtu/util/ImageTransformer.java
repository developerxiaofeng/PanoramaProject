package com.lianxi.quanjingtu.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 15:18 2018/7/4
 */
public class ImageTransformer {

    /**
     * 指定图片宽度和高度和压缩比例对图片进行压缩
     * @param imageFile
     *            目标图片
     * @param outPath
     *            输出文件地
     * @param
     *
     * @param comBase
     *            压缩基数宽度
     * @param scale
     *            压缩的比例
     */
    public static void reduceImg(File imageFile, String outPath, double comBase, double scale) {
        try {
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(imageFile);
            int srcHeight = src.getHeight(null);
            int srcWidth = src.getWidth(null);
            int deskHeight = 0;// 缩略图高
            int deskWidth = 0;// 缩略图宽
            double srcScale = (double) srcHeight / srcWidth;
            /**缩略图宽高算法*/
            if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
                if (srcScale >= scale || 1 / srcScale > scale) {
                    if (srcScale >= scale) {
                        deskHeight = (int) comBase;
                        deskWidth = srcWidth * deskHeight / srcHeight;
                    } else {
                        deskWidth = (int) comBase;
                        deskHeight = srcHeight * deskWidth / srcWidth;
                    }
                } else {
                    if ((double) srcHeight > comBase) {
                        deskHeight = (int) comBase;
                        deskWidth = srcWidth * deskHeight / srcHeight;
                    } else {
                        deskWidth = (int) comBase;
                        deskHeight = srcHeight * deskWidth / srcWidth;
                    }
                }
            } else {
                deskHeight = srcHeight;
                deskWidth = srcWidth;
            }

            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage((int) deskWidth, (int) deskHeight, BufferedImage.TYPE_INT_RGB);
            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            tag.getGraphics().drawImage(src.getScaledInstance(deskWidth, deskHeight, Image.SCALE_SMOOTH), 0, 0, null);

            String formatName = outPath.substring(outPath.lastIndexOf(".") + 1);
            File outFile = new File(outPath);
            FileOutputStream fos = new FileOutputStream(outFile);
            ImageIO.write(tag, /*"GIF"*/ formatName /* format desired */ , fos /* target */ );
            fos.close();
        } catch (Exception ef) {
            ef.printStackTrace();
        }
    }
}

