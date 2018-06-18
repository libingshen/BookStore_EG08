package com.atguigu.bookstore.bean;

import java.math.BigDecimal;

/**
 * 封装购物项的类
 * 
 * @author lilichao
 * 
 */
public class CartItem {

	/**
	 * 图书信息
	 */
	private Book book;

	/**
	 * 图书的数量
	 */
	private int count;

	/**
	 * 图书金额小计
	 */
	// private double amount;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		
		//使用BigDecimal解决运算精度问题
		BigDecimal price = new BigDecimal(book.getPrice()+"");
		BigDecimal count = new BigDecimal(getCount()+"");
		
		BigDecimal amount = price.multiply(count);
		
		return amount.doubleValue();
	}

	public CartItem() {
	}

}
