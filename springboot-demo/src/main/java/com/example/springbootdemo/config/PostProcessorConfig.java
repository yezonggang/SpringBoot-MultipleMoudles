package com.example.springbootdemo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class PostProcessorConfig implements BeanFactoryPostProcessor, BeanPostProcessor {

    // BeanPostProcessor 接口提供的能力
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equalsIgnoreCase("learnBean")) {
            System.out.println(String.format("BeanPostProcessor接口的postProcessBeforeInitialization方法------------->,beanName is %s", beanName));
        }
        return bean;
    }

    // BeanPostProcessor 接口提供的能力
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equalsIgnoreCase("learnBean")) {
            System.out.println(String.format("BeanPostProcessor接口的postProcessAfterInitialization方法------------->,beanName is %s", beanName));
        }
        return bean;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for(String beanName : beanNames){
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if(beanDefinition.getBeanClassName() !=null && beanDefinition.getBeanClassName().equalsIgnoreCase("com.yzg.demo.entity.OriginalBean")){
                System.out.println("BeanFactoryPostProcessor接口提供的postProcessBeanFactory方法--------->");
            }
        }
    }
}
