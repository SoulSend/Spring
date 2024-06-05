package com.hrc.dao;

import com.hrc.pojo.Student;

import java.util.List;

/**
 * 持久层接口通过jdbc实现对数据库的操作只规定一个查询所有学生信息的方法
 */
public interface StudentDao {
     List<Student> queryAll();
}
