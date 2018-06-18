package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;

public interface OrderDao {

	int saveOrder(Order order);
	
	Order getOrderById(String id);
	
	int updateOrder(Order order);
	
	List<Order> getOrderList();
	
	List<Order> getOrderByUserId(String userId);
	
	int updateOrderState(String orderId , int state);
}
