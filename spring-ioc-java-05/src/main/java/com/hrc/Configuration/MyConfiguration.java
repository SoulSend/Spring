package com.hrc.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Configuration 标注这是一个配置类
 */
@Configuration
//包扫描
@ComponentScan(basePackages = "com.hrc")
//配置文件扫描
@PropertySource("classpath:practice.properties")
public class MyConfiguration {
    @Value("${atguigu.url}")
    private String url;
    @Value("${atguigu.driver}")
    private String driver;
    @Value("${atguigu.username}")
    private String username;
    @Value("${atguigu.password}")
    private String password;

    //在这里面进行bean ioc配置和di配置
    /**
     * 使用@bean标签来标注一个组件，这个注解放在一个方法上，方法的返回值就是bean的类型，方法名就是bean的id
     * 可以使用Bean注解的value属性来修改id和生命周期，
     * 使用scope来修改单例还是多例
     */
    @Bean
    public DruidDataSource druidDataSource(){
        //直接调用set方法实现注入,在这个方法里实现实例化
        DruidDataSource druid=new DruidDataSource();
        //通过使用类的
        druid.setUrl(url);
        druid.setDriverClassName(driver);
        druid.setUsername(username);
        druid.setPassword(password);
        return druid;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbc=new JdbcTemplate();
        //如果注入的对象同样是bean组件对象，可以直接调用这个方法
        jdbc.setDataSource(druidDataSource());
        return jdbc;
    }

}
