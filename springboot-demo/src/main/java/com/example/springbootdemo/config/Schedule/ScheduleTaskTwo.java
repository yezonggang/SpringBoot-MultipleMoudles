package com.example.springbootdemo.config.Schedule;

import com.example.springbootdemo.service.CrontableImpl;
import com.example.springbootdemo.service.TeacherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@Configuration
@EnableScheduling
public class ScheduleTaskTwo implements SchedulingConfigurer {

    @Autowired
    TeacherServiceImpl teacherimpl; //实际业务操作

    @Autowired
    CrontableImpl crontableImpl; //读取数据库crontab操作

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        final Logger logger= LoggerFactory.getLogger(ScheduleTaskTwo.class);

        taskRegistrar.addTriggerTask(
                () -> {
                    // 从数据库读取corntabl配置
                    logger.info("模拟读取数据库中的crontab配置： " + LocalDateTime.now().toLocalTime());
                    try {
                        // 执行定时的接口任务
                        logger.info(teacherimpl.getStudents().get().get(0).toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                },
                triggerContext -> {
                    String cron = crontableImpl.getCronInfo();
                    if (StringUtils.isEmpty(cron)) {
                    }
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

}