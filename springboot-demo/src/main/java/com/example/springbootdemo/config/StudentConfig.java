package com.example.springbootdemo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


//@Configuration
@MapperScan(basePackages = "org.example.mapper.students",sqlSessionFactoryRef = "studentFactory")
public class StudentConfig {
    @Primary
    @Bean(name="studentDataSource")
    @ConfigurationProperties("spring.datasource.druid.student")
    public DataSource studentDataSource(){return DruidDataSourceBuilder.create().build();}

    @Primary
    @Bean(name="studentDataSourceFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("studentDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/students/*.xml"));
        return sessionFactoryBean.getObject();
    }

}
