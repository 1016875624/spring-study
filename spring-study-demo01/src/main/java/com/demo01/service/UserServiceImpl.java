package com.demo01.service;

import com.demo01.dao.UserDao;
import com.demo01.entity.User;

import lombok.NoArgsConstructor;
//这里要调用到dao层的方法
//所以这里依赖dao层
//依赖注入主要有三种 1set注入 2.构造注入 3.spring的 自动注入
//由于自动注入要用到注解，这里还是主要用xml配置的 所以先不写
@NoArgsConstructor
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	//构造注入
	public UserServiceImpl(UserDao userDao) {
		this.userDao=userDao;
	}
	//set 注入
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public User get(Integer id) {
		System.out.println("service get");
		return userDao.get(id);
	}

	@Override
	public void delete(Integer id) {
		System.out.println("service delete");
		userDao.delete(id);
	}

	@Override
	public void update(User user) {
		System.out.println("service update");
		userDao.update(user);
	}

	@Override
	public void save(User user) {
		System.out.println("service save");
		userDao.save(user);
	}

	@Override
	public boolean regist(User user) {
		System.out.println("service regist user");
		return false;
	}

	@Override
	public boolean regist(String username, String password) {
		System.out.println("service regist username password");
		return false;
	}

}
