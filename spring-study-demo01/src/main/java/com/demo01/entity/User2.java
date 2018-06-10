package com.demo01.entity;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

//这里用的是lombok的属性,作用是自动生成tostring get set hashcode 等函数
@Data
public class User2 {
	//获取id值
	@Value("${id}")
	private Integer id;
	//获取username值
	@Value("${username}")
	private String username;
	//获取password值
	@Value("${password}")
	private String password;
}
