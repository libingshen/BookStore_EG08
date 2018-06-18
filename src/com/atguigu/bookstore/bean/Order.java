package com.atguigu.bookstore.bean;

import java.util.Date;
import java.util.List;

/**
 * String id 编号 Date date 日期 int state 发货状态 userId int 用户ID amount 总价
 * 
 * @author lilichao
 * 
 */
public class Order {

	private String id;
	private Date date;
	private double amount;
	private int state;
	private int userId;
	private List<OrderItem> orderItems;

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Order(String id, Date date, double amount, int state, int userId) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.state = state;
		this.userId = userId;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", amount=" + amount
				+ ", state=" + state + ", userId=" + userId + ", orderItems="
				+ orderItems + "]";
	}

}
