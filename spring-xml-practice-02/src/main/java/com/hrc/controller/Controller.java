package com.hrc.controller;

import com.hrc.pojo.Student;
import com.hrc.service.StudentService;

import java.util.List;

/**
 * 直接再controller层调用service服务，
 * 响应前端的调用，这里用测试方法模拟
 */

public class Controller {
    private StudentService service;

    public void setService(StudentService service) {
        this.service = service;
    }

    //使用service实现controller的逻辑
    public List<Student> query(){
        return service.getList();
    }

}
