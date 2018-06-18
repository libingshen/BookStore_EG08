package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

/**
 * 用户相关业务的接口
 * @author lilichao
 *
 */
public interface UserService {

	
	User login(User user);
	
	boolean regist(User user);
	
	boolean checkUsername(String username);
	
}
