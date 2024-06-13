package com.hrc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.hrc")
//开启Aspect注解支持
@EnableAspectJAutoProxy
public class AopConfig {

}
