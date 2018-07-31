package com.aop.demo01.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class User {
	private Integer id;
	private String name;
	private String sex;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date birthdate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getBirthdate() {
		return birthdate;
	}
}
