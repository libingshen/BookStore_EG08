package com.atguigu.bookstore.servlet.client;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.util.WEBUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookClientServlet
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService = new BookServiceImpl();
	

	/**
	 * 分页返回Book信息的方法
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findBookPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.获取页码
		String pageNumber = request.getParameter("pageNumber");
		//2.调用BookService 查询数据
		Page<Book> page = bookService.findBook(pageNumber, 4);
		
		//设置请求路径
		page.setPath(WEBUtils.getPagePath(request));
		
		//3.将page设置进属性域中
		request.setAttribute("page", page);
		//4.派发页面
		request.getRequestDispatcher("/pages/book/book_list.jsp").forward(request, response);
		
	}
	
	
	/**
	 * 根据价格分页返回Book信息的方法
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findBookPageByPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.获取页码
		String pageNumber = request.getParameter("pageNumber");
		
		//获取最大价格和最小价格
		String max = request.getParameter("max");
		String min = request.getParameter("min");
		
		//2.调用BookService 查询数据
		Page<Book> page = bookService.findBookByPrice(pageNumber, 4, max, min);
		
		//设置请求路径
		page.setPath(WEBUtils.getPagePath(request));
		//System.out.println(WEBUtils.getPagePath(request));
		
		//3.将page设置进属性域中
		request.setAttribute("page", page);
		//4.派发页面
		request.getRequestDispatcher("/pages/book/book_list.jsp").forward(request, response);
		
	}


}
