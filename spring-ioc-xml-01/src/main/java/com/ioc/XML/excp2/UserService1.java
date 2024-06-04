package com.ioc.XML.excp2;

/**
 * di依赖注入
 * 准备组件类
 */
public class UserService1 {
    private  UserDao userDao;
    public UserService1(UserDao userDao){
        this.userDao=userDao;
    }
}
