package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.util.JDBCUtils;

public class TransactionFilter implements Filter {


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		Connection connection = JDBCUtils.getConnection();
		try {
			connection.setAutoCommit(false);
			chain.doFilter(request, response);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
				req.getRequestDispatcher("/pages/error/err.jsp").forward(request, response);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			JDBCUtils.releaseConnection(connection);
		}
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
