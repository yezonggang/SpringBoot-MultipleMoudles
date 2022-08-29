package com.completablefuture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

/**
 * 小工具
 */
public class SmallTool {

    public static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printTimeAndThread(String tag) {
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        Date nowTime = new Date(System.currentTimeMillis());
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        String result = new StringJoiner("\t|\t")
                .add(retStrFormatNowDate)
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(Thread.currentThread().getName())
                .add(tag)
                .toString();
        System.out.println(result);
    }

}
