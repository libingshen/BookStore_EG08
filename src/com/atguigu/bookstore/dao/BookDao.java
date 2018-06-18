package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 定义图书相关的数据库操作
 * 
 * @author lilichao
 * 
 */
public interface BookDao {
	
	/**
	 * 查询所有图书的方法
	 * @return
	 */
	List<Book> getBookList();

	/**
	 * 根据bookId查找图书
	 * @param bookId
	 * @return
	 */
	Book getBookById(String bookId);

	/**
	 * 添加图书
	 * @param book
	 * @return
	 */
	int addBook(Book book);

	/**
	 * 根据ID删除图书
	 * @param bookId
	 * @return
	 */
	int delBook(String bookId);

	/**
	 * 更新图书
	 * @param book
	 * @return
	 */
	int updateBook(Book book);
	
	/**
	 * 分页查询图书的方法
	 * @param page
	 * @return
	 */
	Page<Book> findBook(Page<Book> page);
	
	/**
	 * 根据价格分页查询图书的方法
	 * @param page
	 * @return
	 */
	Page<Book> findBookByPrice(Page<Book> page  , double min , double max);
	
	void updateBookStock(Object[][] params);
	
	void setImg(String bookId , String imgPath);

}
