package junit.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class TestBookDao {
	
	BookDaoImpl bd = new BookDaoImpl();
	
	@Test
	public void test002(){
		Book b = bd.getBookById("11");
		
		System.out.println(b);
		
		b.setStock(-100);
		
		bd.updateBook(b);
	}
	
	
	@Test
	public void testFindBook(){
		
		Page<Book> page = new Page<>();
		page.setPageNumber(2);
		page.setPageSize(3);
		
		Page<Book> page2 = bd.findBook(page);
		
		for(Book book : page2.getData()){
			
			System.out.println(book);
		}
		
	}
	
	@Test
	public void testSingle(){
		
		String sql = "SELECT count(*) FROM bs_book";
		
		long totalRecord = (long) bd.getSingleValue(sql);
		
		System.out.println(totalRecord);
	}
	
	@Test
	public void testAddBook() {
		
		Book book = new Book(null, "水浒传", "施耐庵", 50.00, 200, 100, "/static/img/default.jpg");
		
		int count = bd.addBook(book);
		
		System.out.println(count);
		
	}
	
	@Test
	public void testGetBookById(){
		
		Book book = bd.getBookById("2");
		
		System.out.println(book);
		
	}
	
	@Test
	public void testGetBookList(){
		
		List<Book> list = bd.getBookList();
		
		System.out.println(list);
	}
	
	@Test
	public void testUpdateBook(){
		
		Book book = new Book(2, "105个男人和3个女人的故事", "施耐庵", 50.00, 200, 100, "/static/img/default.jpg");
		
		int count = bd.updateBook(book);
		
		System.out.println(count);
	}
	
	@Test
	public void testDelBook(){
		
		int count = bd.delBook("2");
		
		System.out.println(count);
		
	}

}
