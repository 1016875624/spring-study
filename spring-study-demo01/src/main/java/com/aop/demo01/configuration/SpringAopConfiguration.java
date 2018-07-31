package com.aop.demo01.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.aop.demo01")
@EnableAspectJAutoProxy
public class SpringAopConfiguration {
	
}
