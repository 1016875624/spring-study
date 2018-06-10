package com.demo02.dao;

import org.springframework.stereotype.Component;

import com.demo02.entity.Student;

@Component
public class StudentDaoImpl implements StudentDao {

	@Override
	public void save(Student s) {
		System.out.println("学生已经被保存"+s);
	}

	@Override
	public void delete(Student s) {
		System.out.println("学生已经被删除"+s);
	}
	public StudentDaoImpl() {
		System.out.println("我被创建了");
	}
}
