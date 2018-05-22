package com.demo01.service;

import com.demo01.entity.User;
//service层主要进行业务逻辑的处理
//这里进行各种业务的编写，各种繁重的业务都在这里写

public interface UserService {
	User get(Integer id);
	void delete(Integer id);
	void update(User user);
	void save(User user);
	boolean regist(User user);
	boolean regist(String username,String password);
}
