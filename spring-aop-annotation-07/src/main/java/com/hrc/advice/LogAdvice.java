package com.hrc.advice;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 通知类放入ioc容器才能实现aop功能
 */
@Component
//@Aspect
public class LogAdvice {

    /**
     * 这就是一个具体的通知，根据通知的位置来实现具体的逻辑
     * 上面的注解就是通知执行的时间
     * 注解里面的属性就是切入点的位置
     */
    @Before(value = "execution(* com.hrc.service.imp.CalculatorImp.add(int ,int))")
    public void  start(){
        System.out.println("方法开始了时间是："+ LocalDateTime.now());
    }
    @AfterReturning(value = "execution(* com.hrc.service.imp.CalculatorImp.add(int ,int))")
    public void end(){
        System.out.println("方法结束了时间是："+LocalDateTime.now());
    }
}
