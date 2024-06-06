package com.hrc.ioc02.controller;

import com.hrc.ioc02.service.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 学习使用注解注入属性
 */
@Controller
public class SoldierController {
    @Autowired
    private SoldierService service;

    public void getMessage(){
        service.getMessage();
    }

}
