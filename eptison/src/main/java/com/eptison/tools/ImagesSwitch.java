package com.eptison.tools;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;

/**
 * Date: 2025/3/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ImagesSwitch {

    public static void main(String[] args) {

        String imagesUrl4 = "/Users/xiazhenyu/Desktop/Eptison/图片/1.jpg";
        String imagesUrl5 = "/Users/xiazhenyu/Desktop/Eptison/图片/2.jpg";
        String imagesUrl6 = "/Users/xiazhenyu/Desktop/Eptison/图片/3.jpg";
        String imagesUrl1="/Users/xiazhenyu/Desktop/Eptison/图片/4.jpg";
        String imagesUrl2="/Users/xiazhenyu/Desktop/Eptison/图片/绣标.jpeg";
//        String imagesUrl3="/Users/xiazhenyu/Desktop/Eptison/图片/异常图片.png";
        String imagesUrl3="/Users/xiazhenyu/Desktop/Eptison/图片/EOT.jpg";
        String combinedImagesUrl = "/Users/xiazhenyu/Desktop/Eptison/图片/combined.jpg";
        //定义图片之间的空白间隔
        int gap = 50;
        try {
            // 读取 6 张图片
            BufferedImage[] images = new BufferedImage[6];
//            for (int i = 0; i < 6; i++) {
//                images[i] = ImageIO.read(new File("imagesUrl" + (i+1)));
//            }

            images[0] = ImageIO.read(new File(imagesUrl1));
            images[1] = ImageIO.read(new File(imagesUrl2));
            images[2] = ImageIO.read(new File(imagesUrl3));
            images[3] = ImageIO.read(new File(imagesUrl6));
            images[4] = ImageIO.read(new File(imagesUrl5));
            images[5] = ImageIO.read(new File(imagesUrl4));
//
            // 为下标预留空间
            int labelHeight = 50;

            // 计算每行图片的最大高度
            int maxHeightRow1 = Math.max(images[0].getHeight(), Math.max(images[1].getHeight(), images[2].getHeight()));
            int maxHeightRow2 = Math.max(images[3].getHeight(), Math.max(images[4].getHeight(), images[5].getHeight()));
            int totalHeight = maxHeightRow1 + maxHeightRow2 + labelHeight + labelHeight;
//            int totalHeight = maxHeightRow1 + labelHeight;

            // 计算每行图片的总宽度
            int currentRowWidth = 0;
            int totalWidth=0;
            for (int i = 0; i < 3; i++) {
                currentRowWidth += images[i].getWidth();
            }
            totalWidth=Math.max(currentRowWidth, totalWidth);
            currentRowWidth=0;
            for (int i = 3; i < 6; i++) {
                currentRowWidth += images[i].getWidth();
            }
            totalWidth=Math.max(currentRowWidth, totalWidth);
            totalWidth += 2 * gap;

            // 创建新的 BufferedImage 对象用于拼接后的图片
            BufferedImage combined = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);


            // 获取 Graphics2D 对象用于绘制图片
            Graphics2D g2d = combined.createGraphics();

            // 设置背景颜色
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, totalWidth, totalHeight);

            // 绘制第一行图片
            int currentX = 0;
            for (int i = 0; i < 3; i++) {
                System.out.println("图片:"+i+",ImageType:"+images[i].getType()+"，ColorModel："+images[i].getColorModel());

//                if (images[i].getType() != BufferedImage.TYPE_INT_ARGB) {
//                    BufferedImage convertedImage = new BufferedImage(images[i].getWidth(), images[i].getHeight(), BufferedImage.TYPE_INT_ARGB);
//                    Graphics2D g = convertedImage.createGraphics();
//                    g.drawImage(images[i], 0, 0, null);
//                    g.dispose();
//                    images[i] = convertedImage;
//                }
                g2d.drawImage(images[i], currentX, 0, null);
                currentX += images[i].getWidth() + gap;
            }

            // 绘制第二行图片
            currentX = 0;
//            for (int i = 3; i < 6; i++) {
//                System.out.println("图片:"+i+",ImageType:"+images[i].getType()+"，ColorModel："+images[i].getColorModel());
//
//                if (images[i].getType() != BufferedImage.TYPE_INT_ARGB) {
//                    BufferedImage convertedImage = new BufferedImage(images[i].getWidth(), images[i].getHeight(), BufferedImage.TYPE_INT_ARGB);
//                    Graphics2D g = convertedImage.createGraphics();
//                    g.drawImage(images[i], 0, 0, null);
//                    g.dispose();
//                    images[i] = convertedImage;
//                }
//
//                g2d.drawImage(images[i], currentX, maxHeightRow1 + labelHeight, null);
//                currentX += images[i].getWidth() + gap;
//            }
            // 设置字体和颜色
            g2d.setFont(new Font("Arial", Font.PLAIN, 30));
            g2d.setColor(Color.BLACK);

            //绘制下标
            currentX = 0;
            for (int i = 0; i < 3; i++) {
                // 绘制下标
                int halfWidth = images[i].getWidth() / 2;
                g2d.drawString("图片" + (i + 1), currentX + halfWidth, images[i].getHeight() + labelHeight - 10);
                currentX += images[i].getWidth() + gap;
            }
            currentX = 0;
//            for (int i = 3; i < 6; i++) {
//                // 绘制下标
//                int halfWidth = images[i].getWidth() / 2;
//                g2d.drawString("图片" + (i + 1), currentX + halfWidth, images[i].getHeight() + maxHeightRow1 + labelHeight + labelHeight - 10);
//                currentX += images[i].getWidth() + gap;
//            }

            // 释放 Graphics2D 对象
            g2d.dispose();

            // 保存拼接后的图片
//            File output = new File(combinedImagesUrl);
//            ImageIO.write(combined, "jpg", output);
            // 保存合成后的图片
            Thumbnails.of(combined)
                    .scale(1.0) // 保持原尺寸
                    .toFile(new File(combinedImagesUrl));


            System.out.println("6 张图片拼接、添加下标成功！");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}