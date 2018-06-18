package junit.test;

import java.sql.Connection;

import org.junit.Test;

import com.atguigu.bookstore.util.JDBCUtils;

public class TestJDBCUtils {

	@Test
	public void testGetConn() {
		
		Connection connection = JDBCUtils.getConnection();
		
		System.out.println(connection);
		
		JDBCUtils.releaseConnection(connection);
		
	}

}
