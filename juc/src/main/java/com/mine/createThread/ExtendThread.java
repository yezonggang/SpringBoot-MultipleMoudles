package com.mine.createThread;

public class ExtendThread {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadTest(), "A");
        thread1.start();
        Thread thread2 = new Thread(new ThreadTest(), "B");
        thread2.start();
    }

    public static class ThreadTest extends Thread {
        public static int thingNum = 5;

        @Override
        public synchronized void run() {
            while (thingNum >= 0) {
                System.out.println(String.format("thread %s |  %s  |  %s  |  thingNum: %s",
                        Thread.currentThread().getId(),
                        Thread.currentThread().getName(),
                        System.currentTimeMillis(),
                        thingNum--));
            }
        }
    }
}
