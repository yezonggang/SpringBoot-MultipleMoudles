package com.mine.createThread;

import java.util.concurrent.*;

public class CompletableTest {
    public static void main(String[] args) {

        System.out.println("----------------起床吃苹果");

        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            int thingNum = 5;
            System.out.println("-------------分身取吃苹果");
            while (thingNum >= 0) {
                System.out.println(String.format("thread %s |  %s  |  %s  |  剩下: %s",
                        Thread.currentThread().getId(),
                        Thread.currentThread().getName(),
                        System.currentTimeMillis(),
                        thingNum--));
            }
            return thingNum+1;
        });


        System.out.println(String.format("吃饱了，剩下%s个苹果，取干别的事情；",cf.join()));


    }
}
