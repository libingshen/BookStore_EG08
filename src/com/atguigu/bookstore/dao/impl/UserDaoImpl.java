package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.UserDao;

/**
 * UseDao的实现类
 * @author lilichao
 *
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	/**
	 * 向数据库添加用户
	 */
	@Override
	public int saveUser(User user) {
		
		String sql = "INSERT INTO bs_user (username , password ,email) values(?,?,?)";
		
		return this.update(sql, user.getUsername() , user.getPassword() , user.getEmail());
		
	}

	/**
	 * 根据用户名和密码查询用户
	 */
	@Override
	public User getUserForLogin(User user) {
		
		String sql = "SELECT id , username , password ,email from bs_user WHERE username=? AND password=?";
		
		return this.getBean(sql, user.getUsername() , user.getPassword());
		
	}

	@Override
	public User getUserByUsername(String username) {
		
		String sql = "SELECT id , username , password ,email from bs_user WHERE username=?";
		
		return this.getBean(sql, username);
	}

}
