/*
package config.dataSource;


import com.mysql.cj.SimpleQuery;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
//@ConditionalOnProperty(prefix = "spring.datasource", value = "true")
@MapperScan(basePackages = "com.example.springbootmybatisplus.mapper",sqlSessionFactoryRef = "mysql_session_factory")
public class MysqlDataSourceConfiguration {
    public static final String DATASOURCE ="mysql_datasource";
    public static final String TEMPLATE ="mysql_template";
    public static final String TRANSACTION_MANAGER ="mysql_transaction_manager";
    public static final String SESSION_FACTORY ="mysql_session_factory";

    @Autowired
    MysqlDataSourceProperties mysqlDataSourceProperties;

    @Bean(DATASOURCE)
    @Primary
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .driverClassName(mysqlDataSourceProperties.getDriverClassName())
                .url(mysqlDataSourceProperties.getUrl())
                .username(mysqlDataSourceProperties.getUsername())
                .password(mysqlDataSourceProperties.getPassword())
                .build();
    }

    @Bean(TEMPLATE)
    @Primary
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean(TRANSACTION_MANAGER)
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(SESSION_FACTORY)
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setTypeAliasesPackage(mysqlDataSourceProperties.getEntityPackage());
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource(mysqlDataSourceProperties.getMapperLocation()));
        return bean.getObject();
    }



}
*/
