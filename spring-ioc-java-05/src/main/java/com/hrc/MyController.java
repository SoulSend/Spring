package com.hrc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private MyService service;
    public void getMessage(){
        List<Student> message = service.getMessage();
        System.out.println(message.toString());
    }
}
