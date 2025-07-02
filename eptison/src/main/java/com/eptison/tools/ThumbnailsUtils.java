package com.eptison.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Coordinate;

/**
 * Date: 2025/3/21
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Slf4j
public class ThumbnailsUtils {


    public static void main(String[] args) throws IOException {
        String imagesUrl4 = "/Users/xiazhenyu/Desktop/Eptison/图片/1.jpg";
        String imagesUrl5 = "/Users/xiazhenyu/Desktop/Eptison/图片/2.jpg";
        String imagesUrl6 = "/Users/xiazhenyu/Desktop/Eptison/图片/3.jpg";
        String imagesUrl1 = "/Users/xiazhenyu/Desktop/Eptison/图片/4.jpg";
        String imagesUrl2 = "/Users/xiazhenyu/Desktop/Eptison/图片/绣标.jpeg";
//        String imagesUrl3="/Users/xiazhenyu/Desktop/Eptison/图片/异常图片.png";
        String imagesUrl3 = "/Users/xiazhenyu/Desktop/Eptison/图片/EOT.jpg";
        String combinedImagesUrl = "/Users/xiazhenyu/Desktop/Eptison/图片/combined.jpg";
        String combinedImagesUrl1 = "/Users/xiazhenyu/Desktop/Eptison/图片/combined1.jpg";
        // 读取 6 张图片
        BufferedImage[] images = new BufferedImage[6];
//        images[0] = ImageIO.read(new File(imagesUrl1));
//        images[1] = ImageIO.read(new File(imagesUrl2));
//        images[2] = ImageIO.read(new File(imagesUrl3));
//        images[3] = ImageIO.read(new File(imagesUrl6));
//        images[4] = ImageIO.read(new File(imagesUrl5));
//        images[5] = ImageIO.read(new File(imagesUrl4));

        images[0] = BufferedImageBuilder.toBufferedImage(Toolkit.getDefaultToolkit().getImage(imagesUrl1));//Image to BufferedImage
        images[1] = BufferedImageBuilder.toBufferedImage(Toolkit.getDefaultToolkit().getImage(imagesUrl2));//Image to BufferedImage
        images[2] = BufferedImageBuilder.toBufferedImage(Toolkit.getDefaultToolkit().getImage(imagesUrl3));//Image to BufferedImage
        images[3] = BufferedImageBuilder.toBufferedImage(Toolkit.getDefaultToolkit().getImage(imagesUrl4));//Image to BufferedImage
        images[4] = BufferedImageBuilder.toBufferedImage(Toolkit.getDefaultToolkit().getImage(imagesUrl5));//Image to BufferedImage
        images[5] = BufferedImageBuilder.toBufferedImage(Toolkit.getDefaultToolkit().getImage(imagesUrl6));//Image to BufferedImage

//        for (int i=0;i<6;i++) {
//            // 将图片转换为不透明格式（RGB）
//            BufferedImage opaqueImage = new BufferedImage(images[i].getWidth(), images[i].getHeight(), BufferedImage.TYPE_INT_RGB);
//            Graphics2D g = opaqueImage.createGraphics();
//            g.drawImage(images[i], 0, 0, null);
//            g.dispose();
//            images[i] = opaqueImage;
//        }

//        images[0] = Thumbnails.of(images[0]).scale(1).outputQuality(1).imageType(BufferedImage.TYPE_INT_ARGB).asBufferedImage();
//        images[1] = Thumbnails.of(images[1]).scale(1).outputQuality(1).imageType(BufferedImage.TYPE_INT_ARGB).asBufferedImage();
//        images[2] = Thumbnails.of(images[2]).scale(1).outputQuality(1).imageType(BufferedImage.TYPE_INT_ARGB).asBufferedImage();
//        images[3] = Thumbnails.of(images[3]).scale(1).outputQuality(1).imageType(BufferedImage.TYPE_INT_ARGB).asBufferedImage();
//        images[4] = Thumbnails.of(images[4]).scale(1).outputQuality(1).imageType(BufferedImage.TYPE_INT_ARGB).asBufferedImage();
//        images[5] = Thumbnails.of(images[5]).scale(1).outputQuality(1).imageType(BufferedImage.TYPE_INT_ARGB).asBufferedImage();

        // 为下标预留空间
        int labelHeight = 50;
        //定义图片之间的空白间隔
        int gap = 50;

        // 计算每行图片的最大高度
        int maxHeightRow1 = Math.max(images[0].getHeight(), Math.max(images[1].getHeight(), images[2].getHeight()));
        int maxHeightRow2 = Math.max(images[3].getHeight(), Math.max(images[4].getHeight(), images[5].getHeight()));
        int totalHeight = maxHeightRow1 + maxHeightRow2 + labelHeight + labelHeight;

        // 计算每行图片的总宽度
        int currentRowWidth = 0;
        int totalWidth = 0;
        for (int i = 0; i < 3; i++) {
            currentRowWidth += images[i].getWidth();
        }
        totalWidth = Math.max(currentRowWidth, totalWidth);
        currentRowWidth = 0;
        for (int i = 3; i < 6; i++) {
            currentRowWidth += images[i].getWidth();
        }
        totalWidth = Math.max(currentRowWidth, totalWidth);
        totalWidth += 2 * gap;

        // 动态创建空白背景图片（白色背景）
        BufferedImage background = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = background.createGraphics();
        g2d.setColor(Color.WHITE); // 设置背景颜色
        g2d.fillRect(0, 0, totalWidth, totalHeight); // 填充背景
        g2d.dispose();

        // 将空白背景保存为临时文件
        File backgroundFile = new File(combinedImagesUrl);
        ImageIO.write(background, "jpg", backgroundFile);

        Builder<File> resultBuilder = Thumbnails.of(backgroundFile);

        // 绘制第一行图片
        int currentX = 0;
        for (int i = 0; i < 3; i++) {
            System.out.println("图片:" + i + ",ImageType:" + images[i].getType() + "，ColorModel：" + images[i].getColorModel());

            resultBuilder.watermark(new Coordinate(currentX, 0), images[i], 1f);
            currentX += images[i].getWidth() + gap;
        }

        // 绘制第二行图片
        currentX = 0;
        for (int i = 3; i < 6; i++) {
            System.out.println("图片:" + i + ",ImageType:" + images[i].getType() + "，ColorModel：" + images[i].getColorModel());

            resultBuilder.watermark(new Coordinate(currentX, maxHeightRow1 + labelHeight), images[i], 1f);
            currentX += images[i].getWidth() + gap;
        }
        // 设置字体和颜色
//        g2d.setFont(new Font("Arial", Font.PLAIN, 30));
//        g2d.setColor(Color.BLACK);

        //绘制下标
//        currentX = 0;
//        for (int i = 0; i < 3; i++) {
//            // 绘制下标
//            int halfWidth = images[i].getWidth() / 2;
//            g2d.drawString("图片" + (i + 1), currentX + halfWidth, images[i].getHeight() + labelHeight - 10);
//            currentX += images[i].getWidth() + gap;
//        }
        currentX = 0;
//            for (int i = 3; i < 6; i++) {
//                // 绘制下标
//                int halfWidth = images[i].getWidth() / 2;
//                g2d.drawString("图片" + (i + 1), currentX + halfWidth, images[i].getHeight() + maxHeightRow1 + labelHeight + labelHeight - 10);
//                currentX += images[i].getWidth() + gap;
//            }

        resultBuilder.scale(1).outputFormat("jpg")
                .toFile(combinedImagesUrl1);
        System.out.println("图片合成成功");

    }

    public static class BufferedImageBuilder {

        public static BufferedImage toBufferedImage(Image image) {
            if (image instanceof BufferedImage) {
                return (BufferedImage) image;
            }
            // This code ensures that all the pixels in the image are loaded
            image = new ImageIcon(image).getImage();
            //wait for the image to load complete
            MediaTracker tracker = new MediaTracker(new Component() {});
            tracker.addImage(image, 0);
            try {
                tracker.waitForID(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //check if the image loaded successfully
            if (tracker.isErrorID(0)) {
                log.error("Image loading failed.");
                return null;
            }

            BufferedImage bimage = null;
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            try {
                int transparency = Transparency.OPAQUE;
                GraphicsDevice gs = ge.getDefaultScreenDevice();
                GraphicsConfiguration gc = gs.getDefaultConfiguration();
                bimage = gc.createCompatibleImage(image.getWidth(null),
                        image.getHeight(null), transparency);
            } catch (HeadlessException e) {
                // The system does not have a screen
            }
            if (bimage == null) {
                // Create a buffered image using the default color model
                int type = BufferedImage.TYPE_INT_RGB;
                bimage = new BufferedImage(image.getWidth(null),
                        image.getHeight(null), type);
            }
            // Copy image to buffered image
            Graphics g = bimage.createGraphics();
            // Paint the image onto the buffered image
            g.drawImage(image, 0, 0, null);
            g.dispose();
            return bimage;
        }
    }

}