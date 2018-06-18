package com.atguigu.bookstore.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.util.WEBUtils;

/**
 * 处理图书相关请求的Servlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	//创建一个BookService
	private BookService bookService = new BookServiceImpl();
	
	/**
	 * 返回图书列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void bookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//通过bookService获取图书列表
		List<Book> list = bookService.getBookList();
		
		//将list放到request请求域中
		request.setAttribute("list", list);
		
		//转发到/pages/manager/book_manager.jsp
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
	/**
	 * 处理添加图书请求的方法
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//post请求的编码一定要在第一次获取请求参数之前设置！！！
		
		//封装一个book对象
		Book book = WEBUtils.param2Bean(request, new Book());
		
		//当id为0时做添加操作
		if(book.getId() == 0){
			//将book插入到数据库中
			bookService.addBook(book);
		}else{
			//当具有id时做更新做
			bookService.updateBook(book);
		}
		
		
		//返回到图书列表页面
		response.sendRedirect(request.getContextPath()+"/manager/BookManagerServlet?method=findBookPage");
	}
	
	/**
	 * 处理删除图书请求的方法
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取图书的ID
		String bookId = request.getParameter("bookId");
		
		//从数据库中删除图书
		bookService.delBook(bookId);
		
		String referer = request.getHeader("referer");
		
		System.out.println(referer);
		
		//返回到图书列表页面
		response.sendRedirect(referer);
		
	}
	
	/**
	 * 去修改图书的页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toEditUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取图书的ID
		String bookId = request.getParameter("bookId");
		
		//从数据库中查询图书
		Book book = bookService.getBookById(bookId);
		
		//把book对象放到request域中
		request.setAttribute("book", book);
		
		//转发到pages/manager/book_edit.jsp页面
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}
	
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
		Page<Book> page = bookService.findBook(pageNumber, 5);
		
		//设置请求路径
		page.setPath(WEBUtils.getPagePath(request));
		
		//3.将page设置进属性域中
		request.setAttribute("page", page);
		//4.派发页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
		
	}

}
