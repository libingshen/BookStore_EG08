package com.atguigu.bookstore.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;

/**
 * Servlet implementation class OrderManagerServlet
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	private OrderService orderService = new OrderServiceImpl();
	
	protected void deliver(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String orderId = request.getParameter("orderId");
		
		orderService.deliver(orderId);
		
		response.sendRedirect(request.getContextPath()+"/manager/OrderManagerServlet?method=orderList");
	}
	
	protected void orderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		List<Order> orders = orderService.getOrderList();
		
		request.setAttribute("orders", orders);
		
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}
}
