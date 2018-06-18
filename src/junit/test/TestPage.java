package junit.test;

import java.awt.print.Book;

import org.junit.Test;

import com.atguigu.bookstore.bean.Page;

public class TestPage {

	@Test
	public void testPage() {
		
		Page<Book> page = new Page<>();
		
		//设置页码
		page.setPageNumber(2);
		//设置每页的条数
		page.setPageSize(2);
		//设置总记录
		page.setTotalRecord(9);
		
		System.out.println(page.getTotalPage());
		System.out.println(page.getIndex());
		
	}

}
