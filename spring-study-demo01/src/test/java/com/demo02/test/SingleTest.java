package com.demo02.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.demo02.configuration.StudentSpringConfiguration;
import com.demo02.dao.StudentDao;
import com.demo02.entity.Student;

public class SingleTest {
	public static void main(String[] args) {
		ApplicationContext ct1=new AnnotationConfigApplicationContext(StudentSpringConfiguration.class);
		ApplicationContext ct2=new AnnotationConfigApplicationContext(StudentSpringConfiguration.class);
//		ct1.getBean(StudentDao.class);
//		ct2.getBean(StudentDao.class);
		Student s1=ct1.getBean(Student.class);
		Student s2=ct2.getBean(Student.class);
		s1.getArea().setCity("韶关");
		s2.getArea().setCity("广州");
		s1.setName("123456");
		s2.setName("456");
		System.out.println(s1);
		System.out.println(s2);
	}
}
