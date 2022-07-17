package com.example.springbootdemo.service;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public class ThreadService implements Runnable {

    private int num ;

    public ThreadService(int num) {
        this.num = num;
    }

    @Override
    public synchronized void run() {

        while (this.num>=0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("thread info:name:%s|time:%s|num:%s",
                    Thread.currentThread().getName(),
                    System.currentTimeMillis(),
                    num--));
        }
    }
}
