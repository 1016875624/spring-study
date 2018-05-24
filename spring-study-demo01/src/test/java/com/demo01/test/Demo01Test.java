package com.demo01.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo01.entity.User;
import com.demo01.service.UserService;
import com.demo01.service.UserServiceImpl;

public class Demo01Test {
	static ApplicationContext context=null;
//	UserService userService=context.getBean(UserServiceImpl.class);
	static UserService userService=null;
	@BeforeClass
	public static void init() {
		context=new ClassPathXmlApplicationContext("application.xml");
		userService=(UserService) context.getBean("userService1");
	}
	@Test
	public void get()
	{
		userService.get(1);
	}
	@Test
	public void delete()
	{
		userService.delete(1);
	}
	@Test
	public void update(){
		User user=new User();
		user.setId(1);
		user.setPassword("123456");
		user.setUsername("aaa123");
		userService.update(user);
	}
	@Test
	public void save() {
		User user=new User();
		user.setId(1);
		user.setPassword("123456");
		user.setUsername("aaa123");
		userService.save(user);
	}
	@Test
	public void regist()
	{
		User user=new User();
		user.setId(1);
		user.setPassword("123456");
		user.setUsername("aaa123");
		userService.regist(user);
	}
	@Test
	public void regist1()
	{
		userService.regist("aaa123", "123456");
	}
}
