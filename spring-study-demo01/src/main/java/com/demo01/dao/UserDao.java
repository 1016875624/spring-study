package com.demo01.dao;

import com.demo01.entity.User;
//dao层主要是对简单实体类(pojo)进行增删改查操作
//一般这里的操作都要进行对操作数据库的操作，这里就简单意思意思就好了
public interface UserDao {
	User get(Integer id);
	void delete(Integer id);
	void update(User user);
	void save(User user);
}
