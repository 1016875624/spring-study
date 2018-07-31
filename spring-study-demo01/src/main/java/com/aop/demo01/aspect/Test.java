package com.aop.demo01.aspect;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aop.demo01.configuration.SpringAopConfiguration;
import com.aop.demo01.entity.User;

public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext aca=new AnnotationConfigApplicationContext(SpringAopConfiguration.class);
		UserAspect userAspect=aca.getBean(UserAspect.class);
		userAspect.show(new User());
	}
	
}
