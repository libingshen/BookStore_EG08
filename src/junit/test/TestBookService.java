package junit.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

public class TestBookService {

	BookService bs = new BookServiceImpl();
	
	@Test
	public void testFindBookByPrice(){
		Page<Book> page = bs.findBookByPrice("1", 10, "30", "29");
		
		for(Book book : page.getData()){
			
			System.out.println(book);
		}
		
	}
	
	@Test
	public void testFindBook() {
		Page<Book> page = bs.findBook("100", 3);
		
		for(Book book : page.getData()){
			
			System.out.println(book);
		}
	}
	

}
