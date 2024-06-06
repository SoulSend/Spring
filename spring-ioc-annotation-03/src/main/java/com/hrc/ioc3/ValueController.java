package com.hrc.ioc3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * 学习value注解的使用
 */
@Controller
public class ValueController {
    @Value("${value.username}")
    private  String username;
    @Value("${value.password}")
    private  String password;

    public void printName(){
        System.out.println(username);
    }
    public void printPassword(){
        System.out.println(password);
    }
}
