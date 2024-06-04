package com.hrc.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateTest {
    @Test
   public void jdbcTemplateTest(){
       //创建一个ioc容器
       ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-bean.xml");
       //获取druid bean对象
       DruidDataSource druid = context.getBean(DruidDataSource.class);
       //获取jdbcTemplate bean对象
       JdbcTemplate jdbc = context.getBean(JdbcTemplate.class);

       //通过jdbc来操作数据库
       //插入一条数据 student报错不用理会，这里我们使用的是德鲁伊连接池连接的数据库
       String sql ="insert into students values(?,?,?,?,?)";
       int rows=jdbc.update(sql,11,"胡某人","男",20,"三年二班");
       System.out.println("sows:"+rows);
   }

}
