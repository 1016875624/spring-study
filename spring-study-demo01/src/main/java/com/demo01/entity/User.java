package com.demo01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//POJO 类 简单实体类
//自动生成get方法
@Getter
//自动生成setter方法
@Setter
//自动生成tostring 方法
@ToString
//自动生成无参构造器
@NoArgsConstructor
public class User {
	private Integer id;
	private String username;
	private String password;
}
