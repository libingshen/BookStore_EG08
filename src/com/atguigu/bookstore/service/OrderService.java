package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;

public interface OrderService {

	String createOrder(Cart cart , User user);
	
	List<Order> getOrderList(String userId);
	
	List<Order> getOrderList();
	
	void deliver(String orderId);
	
	void take(String orderId);
	
	List<OrderItem> getOrderItemsByOrderId(String orderId);
}
