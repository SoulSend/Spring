package com.hrc.controller;

import com.hrc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.hrc.pojo.Student;

import java.util.List;

@Controller
public class StudentController {
    /**
     * 注入service的bean
     */
    @Autowired
    private StudentService service;

    /**
     * 表述层调用service层处理数据后显示
     */
    public void findAll(){
        List<Student> all = service.findAll();
        System.out.println(all.toString());
    }
}
