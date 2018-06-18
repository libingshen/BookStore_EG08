package com.atguigu.bookstore.servlet.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService = new BookServiceImpl();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		
		response.setContentType("text/html;charset=utf-8");
		
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			String bookId = "";
			for (FileItem fileItem : list) {
				
				if(fileItem.isFormField()){
					String fieldName = fileItem.getFieldName();
					if(fieldName.equals("bookId")){
						bookId = fileItem.getString("utf-8");
					}
				}else{
					//获取文件名
				    String fileName = fileItem.getName();
				    //获取上传路径
				    String realPath = getServletContext().getRealPath("/static/book_img");
				    //检查upload文件夹是否存在，如果不存在则创建
				    File f = new File(realPath);
				    if(!f.exists()){
					    f.mkdir();
				    };
				    //为避免重名生成一个uuid作为文件名的前缀
				    String prefix = UUID.randomUUID().toString().replace("-", "");
				    //将文件写入到服务器中
				    fileItem.write(new File(realPath+"/"+prefix+"_"+fileName));
				    
				    bookService.setImg(bookId, "/static/book_img"+"/"+prefix+"_"+fileName);
				    
				    //清楚文件缓存
				    fileItem.delete();
				    
				    response.getWriter().print("<h1>图片上传成功</h1>");
				}
				
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
