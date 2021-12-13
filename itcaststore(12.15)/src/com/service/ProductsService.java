package com.service;

import java.util.ArrayList;

import com.dao.ProductsDAO;
import com.entity.Products;
import com.entity.User;

public class ProductsService {
	
	public ProductsDAO productsdao=new ProductsDAO();
	//前端显示所有商品 参数为：当前页，每页显示商品个数，种类
	public ArrayList<Products> showProducts(int currentPage,int currentCount,String category)
	{
		ArrayList<Products> plist=new ArrayList<Products>();
		plist=productsdao.selectAll(currentPage, currentCount, category);
		return plist;
	}
	//查询所有的方法 前后端通用
	public ArrayList<Products> findProducts()
	{
		ArrayList<Products> plist=new ArrayList<Products>();
		plist=productsdao.selectAll();
		return plist;
	}
	//根据种类返回一共有多少个商品
	public int productCount(String category)
	{
		int totalCount=productsdao.selectProductCount(category);
		return totalCount;
	}
	//添加商品
	public void addProducts(Products products)
	{
		productsdao.insertProducts(products);
	}
	//删除商品
	public void delProducts(String id)
	{
		productsdao.delProductsById(id);
	}
	//根据条件查询需要的商品
	public ArrayList<Products> findProductByManyCondition(String id,String name,String category,double minprice,double maxprice)
	{
		ArrayList<Products> plist=new ArrayList<Products>();
		plist=productsdao.selectProductByManyCondition(id, name, category, minprice, maxprice);
		return plist;
	}
	//根据id查询某个商品
	public Products findProductsByID(String id)
	{
		Products p=new Products();
		p=productsdao.findProductsById(id);
		return p;
	}
	//后台使用的编辑商品功能
	public void editProducts(Products products)
	{
		productsdao.updateProducts(products);
	}
}
