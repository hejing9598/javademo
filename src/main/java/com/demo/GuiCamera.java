package com.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2020-9-9.
 */

//屏幕截图
//实现Job类，并实现唯一接口
public class GuiCamera implements Job {

    private String fileName;//文件的前缀
    private String defaultName = "GuiCamera";
    static int serialNum = 0;
    private String imageFormat;//图像文件的格式
    private String defaultImageFormat = "png";
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * 默认的文件前缀为“GuiCamera”，文件的格式为png
     * Dimension：Java的一个类，封装了一个构件的高度和宽度
     */
    public GuiCamera() {
        fileName = defaultName;
        imageFormat = defaultImageFormat;
    }

    public GuiCamera(String s, String format) {
        fileName = s;
        imageFormat = format;
    }


    /**
     * 对屏幕进行拍照
     */
    public void snapShot() {

        try {
            //拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(new
                    Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
            serialNum++;
            //根据文件前缀变量和文件格式变量，自动生成文件名
            String name = fileName + String.valueOf(serialNum) + "." + imageFormat;
            File f = new File(name);
            System.out.println("Save File " + name);
            //将screenshot对象写入图像文件
            ImageIO.write(screenshot, imageFormat, f);
            System.out.println("..Finished!\n");
            System.out.println("SIZE:" + d.getSize());
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            System.out.println(ex);
        }
    }


    /**
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     * 将定时任务明细写在该方法里
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        System.out.println("Time:"+time);
        GuiCamera cam = new GuiCamera("d:\\screenshot\\"+time, "png");
        cam.snapShot();

    }
}
