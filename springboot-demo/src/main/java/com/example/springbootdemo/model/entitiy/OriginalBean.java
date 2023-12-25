package com.example.springbootdemo.model.entitiy;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OriginalBean implements InitializingBean, BeanNameAware, ApplicationContextAware // , BeanFactoryAware
{

    public String beanName;
    public ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public String getBeanName() {
        return beanName;
    }

    @PostConstruct
    public void ini() {
        System.out.println("@PostConstruct注解在------->构造方法后、依赖注入前执行，是java自带的注解。");

    }

    // 无参构造器
    public OriginalBean() {
        System.out.println("LearnBean------->无参构造器。");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean接口在------->构造方法后、依赖注入后执行。");
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
        System.out.println(String.format("BeanNameAware 接口提供的能力，获取到自己的beanName是 %s", beanName));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println(String.format("ApplicationContextAware 接口提供的能力，获取到applicationContext是%s", applicationContext));
    }

//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//
//    }
}
