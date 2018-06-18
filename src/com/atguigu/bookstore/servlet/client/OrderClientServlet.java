package com.atguigu.bookstore.servlet.client;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.util.WEBUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderClientServlet
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();
	
	protected void take(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String orderId = request.getParameter("orderId");
		
		orderService.take(orderId);
		
		request.getRequestDispatcher("/client/OrderClientServlet?method=orderList").forward(request, response);
		
	}
	
	protected void orderInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String orderId = request.getParameter("orderId");
		
		List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);
		
		request.setAttribute("orderItems", orderItems);
		
		request.getRequestDispatcher("/pages/order/order_info.jsp").forward(request, response);
		
	}
	
	protected void orderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = WEBUtils.getUser(request);
		
		List<Order> orders = orderService.getOrderList(user.getId().toString());
		
		request.setAttribute("orders", orders);
		
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
		
	}

	protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cart cart = WEBUtils.getCart(request);
		User user = WEBUtils.getUser(request);
		
		String orderId = orderService.createOrder(cart, user);
		
		request.setAttribute("orderId" , orderId);
		
		request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
		
	}


}
