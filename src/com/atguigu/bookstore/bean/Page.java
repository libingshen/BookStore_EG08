package com.atguigu.bookstore.bean;

import java.util.List;

public class Page<T> {

	/**
	 * 当前的索引
	 * 需要计算
	 */
	//private int index;
	
	/**
	 * 每页显示的条数,通过Servlet传过来
	 */
	private int pageSize;
	
	/**
	 * 当前的页码,通过Servlet传过来
	 */
	private int pageNumber;
	
	/**
	 * 总记录数，数据库查询
	 * select count(*) from bs_book
	 */
	private int totalRecord;
	
	/**
	 * 总页数  ，需要计算
	 */
	//private int totalPage;
	
	/**
	 * 要显示的数据 ，通过数据库查询获得
	 */
	private List<T> data;
	
	/**
	 * 保存请求的路径
	 * 不同的页面要发送请求Servlet不同，所以创建一个path来保存不同的路径
	 */
	private String path;

	public int getIndex() {
		
		/**
		 * select * from bs_book limit 0 , 3
		 * 0 , 3   从0开始显示3条（pageSize）    第1页（pageNumber）
		 * 3 , 3   从3开始显示3条    第2页
		 * 6 , 3   从6开始显示3条    第3页
		 * 
		 * (pageNumber-1)*pageSize
		 * 
		 */
		
		return (getPageNumber()-1)*getPageSize();
	}


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		/**
		 * 对pageNumber合法性判断
		 * 如果小于1 则返回1
		 * 如果超过最大页码 则 返回最大
		 * 否则正常返回
		 */
		if(pageNumber < 1){
			return 1;
		}else if(pageNumber > getTotalPage()){
			return getTotalPage();
		}else{
			return pageNumber;
		}
		
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalPage() {
		
		/**
		 * 总记录数                 每页的条数                    总页数
		 *  10            2           5          10/2
		 *  9             2           5          9/2 + 1
		 *  8             2           4          8/2
		 *  7             2           4          7/2+1
		 */
		
		if(getTotalRecord()%getPageSize() == 0){
			return getTotalRecord()/getPageSize();
		}else{
			return getTotalRecord()/getPageSize()+1;
		}
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Page() {
		
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	
	
}
