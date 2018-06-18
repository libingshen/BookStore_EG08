package com.atguigu.bookstore.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.util.WEBUtils;

/**
 * 处理用户相关请求的Servlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	protected void checkUserName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		
		boolean isExist = userService.checkUsername(username);
		
		if(isExist){
			response.getWriter().print("1");
		}else{
			response.getWriter().print("0");
		}
		
	}
	
	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//获取Session对象
		HttpSession session = request.getSession();
		
		//封装User对象
		User u = WEBUtils.param2Bean(request, new User());

		// 调用UserService来验证是否登录成功
		User loginUser = userService.login(u);

		// 当loginUser为null时表示，用户名或密码错误
		if (loginUser == null) {
			// 设置一个错误消息
			// 如果想通过request域来传递属性必须通过转发
			request.setAttribute("msg", "用户名或密码错误");
			// 失败让他转发到login.html，重新输入
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);
			

		} else {
			
			//登录成功将loginUser放入到session域中。
			session.setAttribute("loginUser", loginUser);
			
			if(request.getHeader("Referer").contains("OrderClientServlet")){
				response.sendRedirect(request.getContextPath()
						+ "/pages/cart/cart.jsp");
			}else{
				// 登录成功，重定向到login_success.html，欢迎光临
				response.sendRedirect(request.getContextPath()
						+ "/pages/user/login_success.jsp");
			};
			
		}
		

	}

	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取Session对象
		HttpSession session = request.getSession();
		//获取系统验证码
		String sysCode = (String) session.getAttribute("sysCode");
		//获取用户输入的验证码
		String userCode = request.getParameter("userCode");
		//比对验证码
		if(userCode == null || !userCode.equals(sysCode)){
			//设置错误消息
			request.setAttribute("msg", "验证码输入错误");
			
			//注册失败，转发到regist.html
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			
			return;
		}
		
		//调用工具类封装User对象
		User user = WEBUtils.param2Bean(request, new User());
		//将user存入数据库
		boolean isRegist = userService.regist(user);
		
		//如果isRegist为true注册成功
		if(isRegist){
			//重定向到regist_success.html
			response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
			
		}else{
			
			//设置错误消息
			request.setAttribute("msg", "用户名已存在");
			
			//注册失败，转发到regist.html
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}
	
	/**
	 * 用户注销的方法
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取Session
		HttpSession session = req.getSession();
		//强制session失效
		session.invalidate();
		//重定向到首页
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
	}

}
