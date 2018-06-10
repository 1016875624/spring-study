package com.demo02.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Student {
	private Integer stu_no;
	private String name;
	private Integer sex;
	private String department;
	@Autowired
	private Area area;
}
