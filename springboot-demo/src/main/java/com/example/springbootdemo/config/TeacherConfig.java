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
@MapperScan(basePackages = "org.example.mapper.teachers",sqlSessionFactoryRef = "studentFactory")
public class TeacherConfig {
    @Primary
    @Bean(name="teacherDataSource")
    @ConfigurationProperties("spring.datasource.teacher")
    public DataSource studentDataSource(){return DruidDataSourceBuilder.create().build();}

    @Primary
    @Bean(name="teacherDataSourceFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("teacherDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/teacher/*.xml"));
        return sessionFactoryBean.getObject();
    }

}
