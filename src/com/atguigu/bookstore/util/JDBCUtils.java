package com.atguigu.bookstore.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取数据库连接的工具类
 * @author lilichao
 *
 */
public class JDBCUtils {

	private static DataSource dataSource = new ComboPooledDataSource("webDataSource");
	
	private static ThreadLocal<Connection> CONN = new ThreadLocal<Connection>();
	
	/**
	 * 获取数据库连接的方法
	 * @return
	 */
	public static Connection getConnection(){
		
		Connection connection = null;
		
		try {
			if(CONN.get() == null){
				connection = dataSource.getConnection();
				CONN.set(connection);
			}else{
				connection = CONN.get();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	/**
	 * 释放数据库连接的方法
	 */
	public static void releaseConnection(Connection conn){
		
		if(conn!=null){
			try {
				conn.close();
				CONN.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
