package com.example.springbootdemo.config.Schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
// 定时任务
@EnableScheduling
public class ScheduleTaskOne {
    private final Logger logger= LoggerFactory.getLogger(ScheduleTaskOne.class);
    // 秒分时日月周
    @Scheduled(cron = "0/55 * * * * ?")
    private void configureTasks() {
        logger.info("定时任务每55秒打印: " + LocalDateTime.now());
    }
}