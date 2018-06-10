package com.demo01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.demo01.dao.UserDao;
import com.demo01.dao.UserDaoImpl;

@Configuration
@Component
//这里表示整个类的bean都是在dev模式下才启用的bean
@Profile("dev")
public class ProfileExample {
	@Bean
	//这里表示这个类在模式为dev1下才启用的bean
	@Profile("dev1")
	UserDao userDao() {
		return new UserDaoImpl();
	}
}
