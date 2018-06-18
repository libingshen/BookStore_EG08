package com.atguigu.bookstore.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

	@Override
	public int saveOrder(Order order) {

		String sql = "INSERT INTO bs_order (id , date , amount , state , user_id) values(?,?,?,?,?)";

		return this.update(sql, order.getId(), order.getDate(),
				order.getAmount(), order.getState(), order.getUserId());

	}

	@Override
	public Order getOrderById(String id) {

		String sql = "SELECT id , date , amount , state , user_id userId FROM bs_order WHERE id=?";
		return this.getBean(sql, id);
	}

	@Override
	public int updateOrder(Order order) {

		String sql = "UPDATE bs_order set date=? , state=? , user_id=? , amount=? WHERE id=?";

		return this.update(sql, order.getDate(), order.getState(),
				order.getUserId(), order.getId(), order.getAmount());
	}

	@Override
	public List<Order> getOrderList() {

		String sql = "SELECT id , date , amount ,  state , user_id userId FROM bs_order ORDER BY date desc";

		return this.getBeanList(sql);
	}

	@Override
	public List<Order> getOrderByUserId(String userId) {
		String sql = "SELECT id , date , amount ,  state , user_id userId FROM bs_order WHERE user_id = ? ORDER BY date desc";
		List<Order> orders = this.getBeanList(sql, userId);

		for (Order order : orders) {

			order.setOrderItems(getOrderItems(order.getId()));
		}

		return orders;
	}

	private List<OrderItem> getOrderItems(String orderId) {

		String sql = "SELECT item.id `itemId` , item.count , item.amount , item.book_id bookId , "
				+ "item.order_id , book.title , book.author , book.price , book.sales , book.stock , "
				+ "book.img_path imgPath FROM bs_order_item item , bs_book book "
				+ "WHERE item.book_id = book.id AND order_id=?";

		List<Map<String, Object>> list = this.getMap(sql, orderId);
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		for (Map<String, Object> map : list) {
			
			OrderItem orderItem = getOrderItem(map);
			
			orderItems.add(orderItem);
		}
		
		return orderItems;
	}

	private OrderItem getOrderItem(Map<String, Object> map) {
		//img_path=/static/img/default.jpg, amount=20.0, id=25, author=唐德刚, 
		//title=从晚清到民国, stock=100, price=39.9, count=1, 
		//sales=100, book_id=8, order_id=14370974290861
		
		String imgPath = (String) map.get("img_path");
		double amount = (double) map.get("amount");
		int itemId = (int) map.get("id");
		String author = (String) map.get("author");
		String title = (String) map.get("title");
		int stock = (int)((long) map.get("stock"));
		double price = (double) map.get("price");
		int count = (int) map.get("count");
		int sales = (int) map.get("sales");
		int bookId = (int) map.get("book_id");
		String orderId = (String) map.get("order_id");
		
		OrderItem orderItem  = new OrderItem(itemId, count, amount, bookId, orderId);
		Book book = new Book(bookId, title, author, price, sales, stock, imgPath);
		orderItem.setBook(book);
		
		return orderItem;
	}

	@Override
	public int updateOrderState(String orderId, int state) {
		String sql = "UPDATE bs_order set state=? WHERE id=?";
		return this.update(sql, state, orderId);
	}

}
