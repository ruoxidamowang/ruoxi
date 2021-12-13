package com.entity;

import java.util.ArrayList;

public class PageBean {
	private int currentCount;//每一页显示的数据个数
	private int currentPage;//当前页
	private int totalCount;//总个数
	private int totalPage;//总页数
	private String category;//种类
	private ArrayList<Products> plist;//每页商品的集合
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public ArrayList<Products> getPlist() {
		return plist;
	}
	public void setPlist(ArrayList<Products> plist) {
		this.plist = plist;
	}
	public PageBean(int currentCount, int currentPage, int totalCount, int totalPage, String category,
			ArrayList<Products> plist) {
		super();
		this.currentCount = currentCount;
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.category = category;
		this.plist = plist;
	}
	public PageBean() {
		super();
	}
	
	
	
}
