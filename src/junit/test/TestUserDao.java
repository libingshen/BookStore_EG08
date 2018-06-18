package junit.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class TestUserDao {
	
	UserDao ud = new UserDaoImpl();

	@Test
	public void testSaveUser() {
		
		User user = new User(null, "sunwukong", "123123", "swk@atguigu.com");
		
		int count = ud.saveUser(user);
		
		System.out.println(count);
	}
	
	@Test
	public void testLogin(){
		User user = new User(null, "sunwukong", "0000", "swk@atguigu.com");
		
		User loginUser = ud.getUserForLogin(user);
		
		System.out.println(loginUser);
	}

}
