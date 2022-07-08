package com.mine.createThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new TestCallable());
        new Thread(futureTask, "B").start();

        FutureTask futureTask2 = new FutureTask(new TestCallable());
        new Thread(futureTask2, "A").start();

        System.out.println(futureTask.get());
        System.out.println(futureTask2.get());
    }


    public static class TestCallable implements Callable<Integer> {
        public static int appleNum = 5;

        @Override
        public synchronized Integer call() {
            while (appleNum >= 0) {
                System.out.println(String.format("thread %s |  %s  |  %s  |  appleNum: %s",
                        Thread.currentThread().getId(),
                        Thread.currentThread().getName(),
                        System.currentTimeMillis(),
                        appleNum--));
            }
            return appleNum ;
        }
    }

}