package com.hrc.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.hrc.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTemplateTest {
    public JdbcTemplate createJdbc(){
        //创建一个ioc容器
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-bean-01.xml");
        //获取druid bean对象
        DruidDataSource druid = context.getBean(DruidDataSource.class);
        //获取jdbcTemplate bean对象
        return context.getBean(JdbcTemplate.class);
    }
    @Test
   public void testDML(){
       //调用封装的create获取连接
       JdbcTemplate jdbc = createJdbc();

       //通过jdbc来操作数据库
       //插入一条数据 student报错不用理会，这里我们使用的是德鲁伊连接池连接的数据库
       String sql ="insert into students values(?,?,?,?,?) ";
       int rows=jdbc.update(sql,11,"胡某人","男",20,"三年二班");
       System.out.println("sows:"+rows);
   }

    /**
     * 根据id查寻记录
     */
   @Test
   public void testDql(){
       //调用封装的create获取连接
       JdbcTemplate jdbc = createJdbc();

       String sql = "select id , name , age , gender , class as classes from students where id = ? ";

       Student student = jdbc.queryForObject(sql, (rs, rowNum) -> {
                   Student stu = new Student();
                   stu.setId(rs.getInt("id"));
                   stu.setName(rs.getString("name"));
                   stu.setAge(rs.getInt("age"));
                   stu.setGender(rs.getString("gender"));
                   stu.setClasses(rs.getString("classes"));
                   return stu;
               }
               , 2);
       System.out.println(student);
   }


   @Test
   public void testDqlList(){
       //调用封装的create获取连接
       JdbcTemplate jdbc = createJdbc();

       String sql="select id , name , age , gender , class as classes from students ";

       List<Student> list = jdbc.query(sql, new BeanPropertyRowMapper<>(Student.class));
       System.out.println(list);
   }
}
