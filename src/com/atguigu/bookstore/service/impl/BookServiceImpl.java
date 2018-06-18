package com.atguigu.bookstore.service.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

/**
 * 用户业务的实现类
 * @author lilichao
 *
 */
public class BookServiceImpl implements BookService {

	//创建一个BookDao对象
	private BookDao bookDao = new BookDaoImpl();
	
	@Override
	public int addBook(Book book) {
		return bookDao.addBook(book);
	}

	@Override
	public int updateBook(Book book) {
		return bookDao.updateBook(book);
	}

	@Override
	public int delBook(String bookId) {
		return bookDao.delBook(bookId);
	}

	@Override
	public Book getBookById(String bookId) {
		return bookDao.getBookById(bookId);
	}

	@Override
	public List<Book> getBookList() {
		return bookDao.getBookList();
	}

	@Override
	public Page<Book> findBook(String pageNumber, int pageSize) {
		
		//创建一个page对象
		Page<Book> page = new Page<>();
		//将pageNumber设置进page对象
		//指定一个默认的页码
		int pn = 1;
		
		//为pn赋值，如果有异常，赋值将不能成功，则使用默认值，异常不需要处理
		try {
			pn = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e) {
		}
		
		//设置pageNumber
		page.setPageNumber(pn);
		//设置pageSize
		page.setPageSize(pageSize);
		
		//调用DAO来查询数据库
		return bookDao.findBook(page);
	}

	@Override
	public Page<Book> findBookByPrice(String pageNumber, int pageSize,
			String max, String min) {
		//定义两个默认价格
		double minPrice = 0;
		double maxPrice = Double.MAX_VALUE;
		
		//强转max和min
		try {
			minPrice = Double.parseDouble(min);
			maxPrice = Double.parseDouble(max);
		} catch (NumberFormatException e1) {
		}
		
		//创建一个page对象
		Page<Book> page = new Page<>();
		//将pageNumber设置进page对象
		//指定一个默认的页码
		int pn = 1;
		
		//为pn赋值，如果有异常，赋值将不能成功，则使用默认值，异常不需要处理
		try {
			pn = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e) {
		}
		
		//设置pageNumber
		page.setPageNumber(pn);
		//设置pageSize
		page.setPageSize(pageSize);
		
		return bookDao.findBookByPrice(page, minPrice, maxPrice);
	}

	@Override
	public void setImg(String bookId, String imgPath) {
		
		bookDao.setImg(bookId, imgPath);
	}

}
