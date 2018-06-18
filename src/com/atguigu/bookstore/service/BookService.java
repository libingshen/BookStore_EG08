package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 处理图书相关业务的类
 * @author lilichao
 *
 */
public interface BookService {

	/**
	 * 添加图书的业务
	 * @param book
	 * @return
	 */
	int addBook(Book book);
	
	/**
	 * 更新图书的业务
	 * @param book
	 * @return
	 */
	int updateBook(Book book);
	
	/**
	 * 删除图书的业务
	 * @param bookId
	 * @return
	 */
	int delBook(String bookId);
	
	/**
	 * 根据ID查找图书
	 * @param bookId
	 * @return
	 */
	Book getBookById(String bookId);
	
	/**
	 * 查找所有图书
	 * @return
	 */
	List<Book> getBookList();
	
	/**
	 * 分页查找图书
	 * @return
	 */
	Page<Book> findBook(String pageNumber , int pageSize);
	
	/**
	 * 根据价格分页查找图书
	 * @return
	 */
	Page<Book> findBookByPrice(String pageNumber , int pageSize , String max , String min);
	
	void setImg(String bookId , String imgPath);
}
