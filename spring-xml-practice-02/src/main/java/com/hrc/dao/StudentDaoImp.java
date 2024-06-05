package com.hrc.dao;

import com.hrc.pojo.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StudentDaoImp implements StudentDao{
    //要使用jdbc 需要容器注入 把这个jdbc定义为类变量，然后通过setter方法注入
    private JdbcTemplate jdbc;

    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    //重写接口方法
    @Override
    public List<Student> queryAll() {
        String sql="select id,name,gender,age,class classes from students";
        return jdbc.query(sql,new BeanPropertyRowMapper<>(Student.class));
    }
}
