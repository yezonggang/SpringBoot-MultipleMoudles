package com.mine.createThread;

public class RunnableThread {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new TestRunnable(), "A");
        thread1.start();
        Thread thread2 = new Thread(new TestRunnable(), "B");
        thread2.start();


    }
    public static class TestRunnable implements Runnable{
        public static int thingNum = 5;
        @Override
        public void run() {
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