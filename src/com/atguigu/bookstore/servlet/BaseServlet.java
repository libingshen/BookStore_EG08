package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseServlet其他Servlet的父类
 * @author lilichao
 *
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//设置请求编码
		req.setCharacterEncoding("utf-8");
		
		//获取方法名
		String methodName = req.getParameter("method");
		try {
			//根据方法名获取方法对象
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class , HttpServletResponse.class );
			//设置访问权限
			method.setAccessible(true);
			//调用方法
			method.invoke(this,req, resp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
