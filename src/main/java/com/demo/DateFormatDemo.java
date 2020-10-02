package com.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2020-9-16.
 */
public class DateFormatDemo {

    public static void main(String[] args) {

        String thisTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        System.out.println(thisTime);

        System.gc();

    }

}
