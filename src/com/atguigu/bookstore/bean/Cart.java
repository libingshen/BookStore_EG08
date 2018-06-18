package com.atguigu.bookstore.bean;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装购物车信息
 * 
 * @author lilichao
 * 
 */
public class Cart {

	/**
	 * 保存购物项
	 */
	private Map<String, CartItem> map = new HashMap<>();

	/**
	 * 获取所有的CartItem
	 * 
	 * @return
	 */
	public Collection<CartItem> getCartItem() {
		Collection<CartItem> cartItems = map.values();
		return cartItems;
	}

	/**
	 * 返回map对象
	 * 
	 * @return
	 */
	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	/**
	 * 获取图书的总数量
	 * 
	 * @return
	 */
	public int getTotalCount() {
		Collection<CartItem> cartItems = map.values();
		int count = 0;
		for (CartItem cartItem : cartItems) {
			count += cartItem.getCount();
		}
		return count;
	}

	/**
	 * 获取图书的总金额
	 * 
	 * @return
	 */
	public double getTotalAmount() {

		Collection<CartItem> cartItems = map.values();
		
		//使用BigDecimal来处理计算精度
		BigDecimal totalAmount = new BigDecimal("0");
		for (CartItem cartItem : cartItems) {
			BigDecimal amount = new BigDecimal(cartItem.getAmount()+"");
			totalAmount = totalAmount.add(amount);
		}
		return totalAmount.doubleValue();
	}

	/* 操作购物车的方法 */

	/**
	 * 添加图书到购物车
	 */
	public void addBook2Cart(Book book) {
		
		//创建一个空的CartItem
		CartItem cartItem = null;
		
		//1.购物项存在
		boolean containsKey = map.containsKey(book.getId().toString());
		if(containsKey){
			cartItem = map.get(book.getId().toString());
		}else{
			//2.购物项不存在,创建一个新的CartItem对象
			cartItem = new CartItem();
			cartItem.setBook(book);
			map.put(book.getId().toString(), cartItem);
		}
		//修改数量
		int count = cartItem.getCount();
		cartItem.setCount(count+1);
	}

	/**
	 * 删除指定的购物项 根据bookId
	 */
	public void delItem(String bookId) {
		 this.map.remove(bookId);
	}

	/**
	 * 清空购物车
	 */
	public void clear() {
		this.map.clear();
	}

	/**
	 * 修改购物项的数量
	 */
	public void updateCount(String bookId, String countStr) {
		
		//获取CartItem
		CartItem cartItem = map.get(bookId);
		
		//判断CartItem是否为空
		if(cartItem != null){
			
			int count = 1;
			
			//将数量强转为int类型
			try {
				count = Integer.parseInt(countStr);
			} catch (NumberFormatException e) {
			}
			
			//设置为指定的数量
			cartItem.setCount(count);
			
		}

	}
}
