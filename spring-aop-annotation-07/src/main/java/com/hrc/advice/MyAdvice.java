package com.hrc.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * 在通知内部获取目标方法的信息
 */
@Component
@Aspect
public class MyAdvice {
    /**
     * 前置通知
     */
    @Before(value = "execution(* com.hrc.service.imp.*.*(..))")
    public void start(JoinPoint joinPoint){
        //通过连接点获取目标方法的签名
        Signature signature = joinPoint.getSignature();
        //通过签名获取方法的名字
        String name = signature.getName();
        System.out.println(name);
        //获取修饰符
        int modifiers = signature.getModifiers();
        System.out.println(Modifier.toString(modifiers));
        // 3.通过JoinPoint对象获取外界调用目标方法时传入的实参列表
        Object[] args = joinPoint.getArgs();
        String arg = Arrays.toString(args);
        System.out.println("[AOP前置通知] " + name + "方法开始了，参数列表：" + arg);
    }

    /**
     * 返回通知
     */
    @AfterReturning(value = "execution(* com.hrc.service.imp.*.*(..))",returning = "ret")
    public void returning(JoinPoint joinPoint,Object ret){
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("[AOP返回通知] "+name+"方法成功结束了，返回值是：" + ret);
    }

    /**
     * 异常抓取通知
     */
    @AfterThrowing(value = "execution(* com.hrc.service.imp.*.*(..))",throwing = "throwable")
    public void throwing(JoinPoint joinPoint,Throwable throwable){
        String name = joinPoint.getSignature().getName();
        System.out.println("[AOP异常通知] "+name+"方法抛异常了，异常类型是：" + throwable.getClass().getName());
    }

    /**
     * 执行完方法后执行
     */
    @After("execution(* com.hrc.service.imp.*.*(..))")
    public void finalEnd(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("方法调用结束通知");
    }
}
