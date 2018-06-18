package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public List<Book> getBookList() {

		String sql = "SELECT id , title , author ,price ,sales , stock , img_path imgPath FROM bs_book";

		return this.getBeanList(sql);

	}

	@Override
	public Book getBookById(String bookId) {

		String sql = "SELECT id , title , author ,price ,sales , stock , img_path imgPath FROM bs_book WHERE id=?";

		return this.getBean(sql, bookId);
	}

	@Override
	public int addBook(Book book) {
		String sql = "INSERT INTO bs_book (title , author ,price ,sales , stock , img_path) VALUES(?,?,?,?,? ,?)";
		return this.update(sql, book.getTitle(), book.getAuthor(),
				book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath());
	}

	@Override
	public int delBook(String bookId) {
		String sql = "DELETE FROM bs_book WHERE id=?";
		return this.update(sql, bookId);
	}

	@Override
	public int updateBook(Book book) {
		String sql = "UPDATE bs_book SET title=? , author=? , price=? , sales=? , stock=? , img_path=? WHERE id=?";
		return this.update(sql, book.getTitle(), book.getAuthor(),
				book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath(), book.getId());
	}

	@Override
	public Page<Book> findBook(Page<Book> page) {
		
		//page是Service传过来，已经设置pageNumber pageSize
		//需要设置totalRecord 总记录数
		String sql = "SELECT count(*) FROM bs_book";
		//获取总记录数
		long totalRecord = (long) this.getSingleValue(sql);
		//设置
		page.setTotalRecord((int)totalRecord);
		
		//查询数据
		sql = "SELECT id , title , author ,price ,sales , stock , img_path imgPath FROM bs_book LIMIT ? , ?";
		List<Book> list = this.getBeanList(sql, page.getIndex() , page.getPageSize());
		
		//将list设置进page
		page.setData(list);
		
		return page;
	}

	@Override
	public Page<Book> findBookByPrice(Page<Book> page, double min, double max) {
		
		//获取totalRecord
		String sql = "SELECT COUNT(*) FROM bs_book WHERE price >= ? AND price <= ?";
		long totalRecord = (long) this.getSingleValue(sql, min , max);
		//将totalRecord设置到page对象中
		page.setTotalRecord((int)totalRecord);
		
		//查询数据
		sql = "SELECT id , title , author ,price ,sales , stock , img_path imgPath FROM bs_book WHERE price >= ? AND price <= ? LIMIT ? , ?";
		List<Book> list = this.getBeanList(sql, min , max , page.getIndex() , page.getPageSize());
		
		//将list设置进page
		page.setData(list);
		
		return page;
	}

	@Override
	public void updateBookStock(Object[][] params) {
		
		String sql = "UPDATE bs_book SET sales=? , stock=? WHERE id=?";
		
		this.batch(sql, params);

		
	}

	@Override
	public void setImg(String bookId, String imgPath) {
		String sql = "UPDATE bs_book SET img_path=? WHERE id=?";
		
		this.update(sql,imgPath , bookId);
	}

}
