package com.demo01.dao;

import com.demo01.entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User get(Integer id) {
		System.out.println("进行查找id为："+id+"的user对象");
		User user=new User();
		user.setId(id);
		return user;
	}

	@Override
	public void delete(Integer id) {
		System.out.println("删除id为"+id+"的user");
	}

	@Override
	public void update(User user) {
		System.out.println("进行更新user:"+user);
	}

	@Override
	public void save(User user) {
		System.out.println("进行保存user:"+user);
	}


}
