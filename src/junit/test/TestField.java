package junit.test;

import java.lang.reflect.Field;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;

public class TestField {

	@Test
	public void test() {
		
		//获取User中的所有属性对象
		Field[] fields = User.class.getDeclaredFields();
		
		//获取所有的属性名
		for(Field field : fields){
			System.out.println(field.getName());
		}
		
	}

}
