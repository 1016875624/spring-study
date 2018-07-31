package com.aop.demo01.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.aop.demo01.configuration.SpringAopConfiguration;
import com.aop.demo01.entity.User;

//@Aspect
@Component
public class UserAspect {
	
	
	@Pointcut("execution(* show(com.aop.demo01.entity.User) ) && args(user)")
	public void ss(User user) {}
	
	@Before("ss(user)")
	public void name(User user) {
		user.setId(1);
		user.setName("123");
		user.setSex("男");
		System.out.println("before show");
	}
	
	@After("ss(user)")
	public void aa(User user) {
		user.setId(1);
		user.setName("123");
		user.setSex("男");
		System.out.println("before show");
	}
	public void show(User user) {
		System.out.println(user);
	}
	public static void main(String[] args) {
		ApplicationContext aca=new AnnotationConfigApplicationContext(SpringAopConfiguration.class);
		UserAspect userAspect=aca.getBean(UserAspect.class);
		User user=aca.getBean(User.class);
		userAspect.show(user);
	}
	
}
