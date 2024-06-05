package com.hrc.service;

import com.hrc.dao.StudentDao;
import com.hrc.pojo.Student;

import java.util.List;

public class StudentServiceImp implements StudentService{
    //service层调用dao层的接口方法，实现服务，使用ioc容器注入dao层的bean
    private StudentDao dao;

    public void setDao(StudentDao dao) {
        this.dao = dao;
    }
    //实现service的服务逻辑

    @Override
    public List<Student> getList() {
        return dao.queryAll();
    }
}
