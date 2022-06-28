package com.example.springbootdemo;

import java.util.concurrent.*;

import static org.junit.Assert.*;


public class CompletedFutureTest {

    public static void main(String[] args) {
        //completedFutureExample();

        //runAsyncExample();

        // then开头说明是个计算链路，apply说明要处理，但是同步的，默认等待
        //thenApplyExample();

        // Async结尾的方法是异步链路
        //thenApplyAsyncExample();

        // 通过自定义线程池
        //thenApplyAsyncWithExecutorExample();

        //果下一阶段接收了当前阶段的结果，但是在计算的时候不需要返回值(它的返回类型是void)， 那么它可以不应用一个函数，而是一个消费者， 调用方法也变成了thenAccept:
        //thenAcceptExample();

        //同样，可以使用thenAcceptAsync方法， 串联的CompletableFuture可以异步地执行。
        //thenAcceptAsyncExample();

    }
    static void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }
    static void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
                randomSleep();
        });
        assertFalse(cf.isDone());
        assertTrue(cf.isDone());
    }

    static void randomSleep()  {
        try {
            System.out.println("beging to sleep thread.....");
            Thread.sleep(1000);
            System.out.println("end to sleep thread......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    static void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow(null));
    }

    static void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        });
        assertNull(cf.getNow(null));
        System.out.println("begin to assertEquals, with join..");
        //assertEquals("MESSAGE", cf);
        assertEquals("MESSAGE", cf.join());
        System.out.println("end to assertEquals,with join..");
    }


    static ExecutorService executor = Executors.newFixedThreadPool(30, new ThreadFactory() {
        int count = 3;
        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    static void thenApplyAsyncWithExecutorExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
            assertFalse(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        }, executor);

        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
        System.out.println("-------end-------");
    }

    static void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(s -> result.append(s));
        assertTrue("Result was empty", result.length() > 0);
    }

    static void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> result.append(s));
        cf.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    static void runAfterBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                () -> result.append("done"));
        assertTrue("Result was empty", result.length() > 0);
    }

    static void thenAcceptBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1 + s2));
        assertEquals("MESSAGEmessage", result.toString());
    }


}
