package com.atguigu.bookstore.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.util.JDBCUtils;

/**
 * 定义DAO基本操作
 * @author lilichao
 *
 * @param <T>
 */
public class BaseDao<T> {

	private QueryRunner runner = new QueryRunner();
	
	private Class<T> type;
	
	
	
	
	public BaseDao() {
		//这个构造函数时被子类调用，获取泛型类型
		//获取当前对象的Class,并获取它的带泛型的父类
		Type type = this.getClass().getGenericSuperclass();
		
		//将type强转为ParameterizedType
		ParameterizedType pt = (ParameterizedType) type;
		
		//获取泛型的类型
		Type[] types = pt.getActualTypeArguments();
		
		//将types[0]强转为Class类型
		this.type = (Class<T>) types[0];
	}

	public List<Map<String,Object>> getMap(String sql , Object ... params){
		
		List<Map<String,Object>> list = null;
		
		Connection conn = JDBCUtils.getConnection();
		
		try {
			list = runner.query(conn, sql, new MapListHandler(), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * 查询一个数据的集合
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> getBeanList(String sql , Object ... params){
		
		List<T> list = null;
		
		Connection conn = JDBCUtils.getConnection();
		
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			//JDBCUtils.releaseConnection(conn);
		}
		return list;
		
	}
	
	
	/**
	 * 获取一个对象的方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public T getBean(String sql , Object ... params){
		
		T t = null;
		Connection conn = JDBCUtils.getConnection();
		try {
			t = runner.query(conn, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			//JDBCUtils.releaseConnection(conn);
		}
		return t;
		
	}
	
	/**
	 * 更新数据
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql , Object ... params){
		
		//count表示修改的数据的条数
		int count = 0;
		Connection conn = JDBCUtils.getConnection();
		try {
			count = runner.update(conn, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			//JDBCUtils.releaseConnection(conn);
		}
		return count;
	}
	
	/**
	 * 查询一个单个的值
	 * @param sql
	 * @param param
	 * @return
	 */
	public Object getSingleValue(String sql  , Object ... param){
		Connection conn = JDBCUtils.getConnection();
		Object obj = null;
		
		try {
			obj = runner.query(conn, sql, new ScalarHandler(), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			//JDBCUtils.releaseConnection(conn);
		}
		return obj;
		
	}
	
	public void batch(String sql , Object[][]params){
		
		Connection conn = JDBCUtils.getConnection();
		
		try {
			runner.batch(conn,sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			//JDBCUtils.releaseConnection(conn);
		}
		
	}
}
