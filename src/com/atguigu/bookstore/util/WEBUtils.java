package com.atguigu.bookstore.util;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.User;

/**
 * 操作WEB相关的工具类
 * 
 * @author lilichao
 * 
 */
public class WEBUtils {

	/**
	 * 将参数封装到一个对象中
	 */
	public static <T> T param2Bean(HttpServletRequest request, T t) {
		// 获取User的所有属性对象
		Field[] fields = t.getClass().getDeclaredFields();
		// 遍历
		for (Field field : fields) {
			// 获取所有的属性名
			String name = field.getName();
			String value = request.getParameter(name);
			// 通过BeanUtils将属性封装到对象中
			try {
				if(value!=null){
					// copyProperty方法用来将指定值赋值对象的属性
					BeanUtils.copyProperty(t, name, value);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public static String getPagePath(HttpServletRequest request){
		//测试获取请求路径和查询字符串
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String queryString = request.getQueryString();
		
		String path = contextPath+servletPath+"?"+queryString;
		
		//判断当前地址中是否包含pageNumber请求参数，若果给剪掉
		if(path.contains("&pageNumber=")){
			path = path.substring(0, path.indexOf("&pageNumber="));
		}
		
		return path;
	}

	/**
	 * 获取购物车的工具方法
	 * @param request
	 * @return
	 */
	public static Cart getCart(HttpServletRequest request) {
		//获取session
		HttpSession session = request.getSession();
		//获取购物车,属性名叫cart
		 Cart cart = (Cart) session.getAttribute("cart");
		 //判断购物车是否为空
		 if(cart == null){
			 //创建一个购物车对象
			 cart = new Cart();
			 //放到session域中
			 session.setAttribute("cart", cart);
		 }
		return cart;
	}

	public static User getUser(HttpServletRequest request) {
		
		return (User) request.getSession().getAttribute("loginUser");
		
	}

	public static void goBack(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//获取请求的来源
		 String path = request.getHeader("Referer");
		 
		 response.sendRedirect(path);
		
	}


}
