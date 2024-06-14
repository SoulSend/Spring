package com.hrc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionContextManager;

import javax.sql.DataSource;

//配置ioc容器
@Configuration
//扫描配置
@PropertySource("classpath:jdbc.properties")
//包扫描
@ComponentScan("com.hrc")
//记得打开事务的开关，不然无效
@EnableTransactionManagement
public class myConfig {
    @Value("${atguigu.url}")
    private String url;
    @Value("${atguigu.driver}")
    private String driver;
    @Value("${atguigu.username}")
    private String username;
    @Value("${atguigu.password}")
    private String password;


    //配置德鲁伊连接池
    @Bean
    public DataSource dataSource(){
        //配置
        DruidDataSource druid=new DruidDataSource();
        druid.setUrl(url);
        druid.setDriverClassName(driver);
        druid.setUsername(username);
        druid.setPassword(password);
        return druid;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return  new JdbcTemplate(dataSource());
    }

    /**
     * 要使用事务，就要在ioc容器里面放一个事务管理器
     */
    @Bean
    public TransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}
