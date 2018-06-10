package com.demo01.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;

import com.demo01.entity.User2;
//声明为配置类
@ContextConfiguration
//导入资源文件
@PropertySource("classpath:user.properties")
public class PlachHolderConfig {
	@Autowired
	//自动获取spring Environment 
	//当然我们也可以通过这个方法获得 properties 的值 profile 的值等等
	
	Environment env;
	//注册bean
	@Bean
	User2 user2() {
		return new User2();
	}
}
