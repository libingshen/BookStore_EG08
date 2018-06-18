package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

/**
 * UserDao的接口
 * @author lilichao
 *
 */
public interface UserDao {

	
	int saveUser(User user);
	
	User getUserForLogin(User user);
	
	User getUserByUsername(String username);
	
}
