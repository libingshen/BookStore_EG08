package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;

public interface OrderItemDao {
	
	int saveOrderItem(OrderItem orderItem);
	
	List<OrderItem> getOrderItemByOrderId(String orderId);
	
	void batchSaveOrderItem(Object[][] params);
}
