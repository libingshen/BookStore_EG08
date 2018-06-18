package junit.test;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;

public class TestCart {
	
	@Test
	public void testDou(){
		
		/*double a = 1.0;
		double b = 0.9;
		
		System.out.println(a-b);*/
		
		//BigInteger 处理整型
		//BigDecimal 处理浮点类型
		
		//int a = 1;
		/*BigInteger a = BigInteger.valueOf(1);
		for(int i=1 ; i<=1000 ; i++){
			//a *=i ;
			
			a = a.multiply(BigInteger.valueOf(i));
		}
		System.out.println(a);*/
		
		BigDecimal a = new BigDecimal("1.0");
		BigDecimal b = new BigDecimal("0.9");
		
		a = a.subtract(b);
		
		System.out.println(a);
		
		
	}

	@Test
	public void testCart() {
		
		//创建三本书
		Book book = new Book(1,"你好","再见",10.20,10,10,"mmm");
		Book book2 = new Book(2,"你好1","再见",10.90,10,10,"mmm");
		Book book3 = new Book(3,"你好2","再见",10.00,10,10,"mmm");
		
		//创建一个购物车对象
		Cart cart = new Cart();
		
		//添加一本
		cart.addBook2Cart(book3);
		cart.addBook2Cart(book3);
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book);
		
		//
		//cart.delItem("3");
		
		//cart.updateCount("1", "10");
		
		//cart.clear();
		
		//输出数量
		System.out.println(cart.getTotalAmount());
		
		
	}

}
