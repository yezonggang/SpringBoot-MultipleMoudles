package com.example.springcloudLogin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author y25958
 */
@Slf4j   //日志
@Component   // 一定不要忘记把处理器加到IOC容器中 实现bean的注入
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override       //插入时的填充策略
    public void insertFill(MetaObject metaObject) {
        log.info("开始插入填充");
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
 
    @Override      //更新时的时的填充策略
    public void updateFill(MetaObject metaObject) {
        log.info("开始更新填充");
        this.setFieldValByName("updateTime",new Date(),metaObject);
 
    }
}