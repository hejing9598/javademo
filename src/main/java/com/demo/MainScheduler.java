package com.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Administrator on 2020-9-18.
 */
public class MainScheduler {

    //创建调度器
    public static Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }


    public static void schedulerJob() throws SchedulerException{
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(GuiCamera.class).withIdentity("job1", "group1").build();
        //创建触发器 每3秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
        Scheduler scheduler = getScheduler();
        //将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        //调度器开始调度任务
        scheduler.start();

    }

    public static void main(String[] args) throws SchedulerException {
        MainScheduler mainScheduler = new MainScheduler();
        mainScheduler.schedulerJob();

        System.out.println("JAVA");
    }

}