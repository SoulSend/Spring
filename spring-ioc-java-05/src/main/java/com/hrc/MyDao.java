package com.hrc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyDao {
    @Autowired
    private JdbcTemplate jdbc;
    public List<Student> getMessage(){
        String sql="select id,name,gender,age,class classes from students";
        return jdbc.query(sql,new BeanPropertyRowMapper<>(Student.class));
    }
}
