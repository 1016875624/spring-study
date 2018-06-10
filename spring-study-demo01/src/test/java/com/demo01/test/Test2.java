package com.demo01.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo01.entity.User;
import com.demo01.service.UserService;
import com.demo01.service.UserServiceImpl;

import lombok.Cleanup;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:application.xml"})
public class Test2 {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	User user;
	@Test
	public void name() {
		userService.delete(1);
		System.out.println(user);
	}
	@Test
	public void propertiesTest() {
		Properties props=new Properties();
		user.setId(1);
		user.setPassword("123456");
		user.setUsername("admin");
		props.put("user", user);
		try(FileOutputStream fos= new FileOutputStream("a.properties")){
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(Test2.class.getResource("Test2.class").toString());
		System.out.println(Test2.class.getClassLoader().getResource("").getPath());
		System.out.println(Test2.class.getResource("").getPath());
		System.out.println(new File("").getAbsolutePath());
	}
}
