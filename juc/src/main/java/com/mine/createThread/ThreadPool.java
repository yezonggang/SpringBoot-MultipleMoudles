package com.mine.createThread;

import java.util.concurrent.*;

public class ThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());  //队列满了，尝试去和最早的竞争，也不会抛出异常！

        // 如果不想拿到结果，两者都可以传Runnable
        threadPool.submit(new TestRunnable());
        threadPool.execute(new TestRunnable());

        // submit传入callable可以拿到结果
        Future<Integer> integer = threadPool.submit(new TestCallable());
        System.out.println(String.format("xxxx,the result:%s", integer.get()));

        // submit也可以通过FutureTask能拿到线程执行结果，FutureTask把callable转成runnable
        FutureTask futureTask = new FutureTask(new TestCallable());
        threadPool.submit(futureTask);
        int result = (int) futureTask.get();
        System.out.println(String.format("xxxx,the result:%s,isdone:%s", result, futureTask.isDone()));


        // countDownLatch 等待线程执行完
        CountDownLatch countDownLatch = new CountDownLatch(5);
        int count = (int) countDownLatch.getCount();
        for (int i = 0; i < count; i++) {
            threadPool.submit(new TestRunnable2(countDownLatch));
        }
        countDownLatch.await();
        System.out.println("xxxxxxxxxxx----count:" + countDownLatch.getCount());



    }

    public static class TestCallable implements Callable<Integer> {
        public static int thingNum = 5;

        @Override
        public synchronized Integer call() {
            while (thingNum >= 3) {
                System.out.println(String.format("thread %s |  %s  |  %s  |  thingNum: %s",
                        Thread.currentThread().getId(),
                        Thread.currentThread().getName(),
                        System.currentTimeMillis(),
                        thingNum--));
            }
            return thingNum;
        }
    }

    public static class TestRunnable implements Runnable {
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

    public static class TestRunnable2 implements Runnable {

        CountDownLatch countDownLatch;

        public TestRunnable2(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            countDownLatch.countDown();
            System.out.println(countDownLatch.getCount());
        }
    }


}
