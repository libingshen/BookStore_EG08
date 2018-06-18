package com.atguigu.bookstore.servlet.client;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.util.WEBUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 处理购物车相关业务的Servlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService = new BookServiceImpl();

	/**
	 * 添加图书到购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addBook2Cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
		 Cart cart = WEBUtils.getCart(request);
		 
		 //获取一个bookId
		 String bookId = request.getParameter("bookId");
		 //通过BookService来查找图书
		 Book book = bookService.getBookById(bookId);
		
		 //将book添加到购物车
		 cart.addBook2Cart(book);
		 
		 //获取请求的来源
		 //String path = request.getHeader("Referer");
		 
		 //回显图书名
		 //session.setAttribute("title", book.getTitle());
		 
		 //转发会带来重复提交的问题，所以使用重定向
		 //request.getRequestDispatcher("/index.jsp").forward(request, response);
		 //从哪来回哪去
		 //response.sendRedirect(path);
		 //response.getWriter().print("");
		 
		 response.getWriter().print(cart.getTotalCount()+"");
		 
		
	}
	
	/**
	 * 清空购物车
	 */
	protected void clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取购物车对象
		Cart cart = WEBUtils.getCart(request);
		//清空购物车
		cart.clear();
		//返回购物车页面
		response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
		
		
	}
	
	/**
	 * 删除购物项
	 */
	
	protected void delCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取购物车对象
		Cart cart = WEBUtils.getCart(request);
		//获取要删除购物项的id(bookId)
		String bookId = request.getParameter("bookId");
		//删除购物项
		cart.delItem(bookId);
		
		double totalAmount = cart.getTotalAmount();
		int totalCount = cart.getTotalCount();
		
		Gson gson = new Gson();
		
		String json = gson.toJson(cart);
		
		//返回购物车页面
		//response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
		response.getWriter().print("{\"amount\":"+totalAmount+",\"count\":"+totalCount+"}");
		
	}
	
	/**
	 * 修改购物项的数量
	 */
	
	protected void updateCount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取购物车
		Cart cart = WEBUtils.getCart(request);
		//获取bookId，还有数量
		String bookId = request.getParameter("bookId");
		String countStr = request.getParameter("count");
		
		//修改购物项的数量
		cart.updateCount(bookId, countStr);
		
		//返回购物车页面
		//response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("item_amount", cart.getMap().get(bookId).getAmount());
		map.put("amount", cart.getTotalAmount());
		map.put("count", cart.getTotalCount());
		
		response.getWriter().print(new Gson().toJson(map));
		
	}
	


}
