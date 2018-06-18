/**
 * 
 */
package junit.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;

/**
 * @author lilichao
 *
 */
public class TestOrderDao {
	
	OrderDao od = new OrderDaoImpl();

	
	@Test
	public void test003(){
		od.getOrderByUserId("1");
	}
	
	/**
	 * Test method for {@link com.atguigu.bookstore.dao.impl.OrderDaoImpl#saveOrder(com.atguigu.bookstore.bean.Order)}.
	 */
	@Test
	public void testSaveOrder() {
		Order order = new Order(System.currentTimeMillis()+""+1, new Date(),100, 0, 1);
		
		od.saveOrder(order);
	}

	/**
	 * Test method for {@link com.atguigu.bookstore.dao.impl.OrderDaoImpl#getOrderById(java.lang.String)}.
	 */
	@Test
	public void testGetOrderById() {
		
		Order order = od.getOrderById("14370130181661");
		
		System.out.println(order);
		
	}

	/**
	 * Test method for {@link com.atguigu.bookstore.dao.impl.OrderDaoImpl#updateOrder(com.atguigu.bookstore.bean.Order)}.
	 */
	@Test
	public void testUpdateOrder() {
		
		Order order = new Order("14370130181661", new Date(), 100,2, 1);
		
		od.updateOrder(order);

	}

	/**
	 * Test method for {@link com.atguigu.bookstore.dao.impl.OrderDaoImpl#getOrderList()}.
	 */
	@Test
	public void testGetOrderList() {
		
		List<Order> list = od.getOrderList();
		
		System.out.println(list);
	}

	/**
	 * Test method for {@link com.atguigu.bookstore.dao.impl.OrderDaoImpl#getOrderByUserId(java.lang.String)}.
	 */
	@Test
	public void testGetOrderByUserId() {
		
		List<Order> list = od.getOrderByUserId("1");
		System.out.println(list);
		
	}

}
