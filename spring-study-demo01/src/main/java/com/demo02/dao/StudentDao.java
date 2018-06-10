package com.demo02.dao;

import org.springframework.stereotype.Repository;

import com.demo02.entity.Student;

@Repository
public interface StudentDao {
	void save(Student s);
	void delete(Student s);
}
