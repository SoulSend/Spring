package com.hrc.service;

import com.hrc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrc.pojo.Student;

import java.util.List;
@Service
public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentDao dao;
    @Override
    public List<Student> findAll() {
        return dao.queryAll();
    }
}
