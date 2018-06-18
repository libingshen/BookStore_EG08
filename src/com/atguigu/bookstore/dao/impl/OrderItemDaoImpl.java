package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements
		OrderItemDao {

	@Override
	public int saveOrderItem(OrderItem orderItem) {
		
		String sql = "INSERT INTO bs_order_item (count , amount , book_id , order_id) VALUES(?,?,?,?)";
		
		return this.update(sql, orderItem.getCount() , orderItem.getAmount() , orderItem.getBookId() , orderItem.getOrderId());
	}

	@Override
	public List<OrderItem> getOrderItemByOrderId(String orderId) {
		
		String sql = "SELECT id , count , amount , book_id bookId ,order_id orderId FROM bs_order_item WHERE order_id=?";
		
		return this.getBeanList(sql, orderId);
	}

	@Override
	public void batchSaveOrderItem(Object[][] params) {
		String sql = "INSERT INTO bs_order_item (count , amount , book_id , order_id) VALUES(?,?,?,?)";

		this.batch(sql, params);
	}

}
