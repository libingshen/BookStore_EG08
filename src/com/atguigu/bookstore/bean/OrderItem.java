package com.atguigu.bookstore.bean;

import java.util.List;

/**
 * int id int bookId int count double amount String oredrId
 * 
 * @author lilichao
 * 
 */
public class OrderItem {

	private Integer id;
	private int count;
	private double amount;
	private int bookId;
	private String orderId;
	private Book book;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderItem(Integer id, int count, double amount, int bookId,
			String orderId) {
		super();
		this.id = id;
		this.count = count;
		this.amount = amount;
		this.bookId = bookId;
		this.orderId = orderId;
	}

	public OrderItem() {
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", count=" + count + ", amount="
				+ amount + ", bookId=" + bookId + ", orderId=" + orderId
				+ ", book=" + book + "]";
	}

}
