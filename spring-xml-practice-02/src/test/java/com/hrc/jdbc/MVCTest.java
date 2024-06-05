package com.hrc.jdbc;

import com.hrc.controller.Controller;
import com.hrc.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 测试通过ioc容器整合三层架构
 */
public class MVCTest {
    @Test
    public void query(){
        //先初始化一个ioc容器 同时读取配置文件 生成bean对象和属性注入
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-bean-02.xml");
        //获取controller的bean组件
        Controller bean = context.getBean(Controller.class);
        //实现你的逻辑
        List<Student> list = bean.query();
        System.out.println(list.toString());
        //手动关闭ioc容器
        context.close();
    }
}
