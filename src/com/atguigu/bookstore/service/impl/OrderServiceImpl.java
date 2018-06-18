package com.atguigu.bookstore.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	private BookDao bookDao = new BookDaoImpl();

	@Override
	public String createOrder(Cart cart , User user) {
		
		String id = System.currentTimeMillis()+user.getId().toString();
		
		Date date = new Date();
		
		double amount = cart.getTotalAmount();
		
		int state = 0;
		
		int userId = user.getId();
		
		Order order = new Order( id , date, amount, state, userId);
		
		orderDao.saveOrder(order);
		
		Collection<CartItem> cartItems = cart.getCartItem();
		
		Object[][] params = new Object[cartItems.size()][];
		Object[][] bookParams = new Object[cartItems.size()][];
		
		int size = 0;
		
		for (CartItem cartItem : cartItems) {
			Book book = cartItem.getBook();
			Object[] param = new Object[]{cartItem.getCount(),cartItem.getAmount(),book.getId(), id};
			Object[] bookParam = new Object[]{book.getSales()+cartItem.getCount(),book.getStock()-cartItem.getCount() , book.getId()};
			params[size] = param;
			bookParams[size++] = bookParam;
			
		}
		
		bookDao.updateBookStock(bookParams);
		orderItemDao.batchSaveOrderItem(params);

		
		cart.clear();
		
		return id;

	}

	@Override
	public List<Order> getOrderList(String userId) {
		return orderDao.getOrderByUserId(userId);
	}

	@Override
	public void deliver(String orderId) {
		orderDao.updateOrderState(orderId, 1);
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(String orderId) {
		return orderItemDao.getOrderItemByOrderId(orderId);
	}

	@Override
	public List<Order> getOrderList() {
		return orderDao.getOrderList();
	}

	@Override
	public void take(String orderId) {
		orderDao.updateOrderState(orderId, 2);
	}


}
