package com.hrc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hrc.pojo.Student;
import java.util.List;
@Repository
public class StudentDaoImp implements StudentDao{
    /**
     * 现在的第三方类还是要使用xml文件来配置bean和di
     */
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Student> queryAll() {
        String sql="select id,name,gender,age,class classes from students";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }
}
