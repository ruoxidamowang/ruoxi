package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import com.entity.Products;
import com.entity.User;

public class ProductsDAO {
	public static Connection conn=null;
	public static PreparedStatement ptmt=null;
	public static Statement stmt=null;
	public static ResultSet rs=null;
	/**
	 * 商品分页功能
	 * 每页显示四种商品
	 * @param currentPage
	 * @param currentCount
	 * @param category
	 * @return
	 */
	public ArrayList<Products> selectAll(int currentPage,int currentCount,String category){
		ArrayList<Products> plist=new ArrayList<Products>();//创建数组对象
		conn=DBUtil.getConn();
		try {
			String sql="";
			if(category.equals("全部商品")){
				sql="select * from products limit ?,?";//第一个问号从哪一个开始，第二个问号往后取几个值
				ptmt=conn.prepareStatement(sql);
				ptmt.setInt(1, (currentPage-1)*currentCount);//（当前页-1）*每页显示数据个数
				ptmt.setInt(2, currentCount);//每页显示多少个数据就往第二个问号填多少
			}
			else{
				sql="select * from products where category=? limit ?,?";//第一个问号传需要查询的种类
				ptmt=conn.prepareStatement(sql);
				ptmt.setString(1, category);//取哪个种类，值从用户点击的value值获取
				ptmt.setInt(2, (currentPage-1)*currentCount);//（当前页-1）*每页显示数据个数
				ptmt.setInt(3, currentCount);//每页显示多少个数据就往第二个问号填多少
			}
			rs=ptmt.executeQuery();//返回结果集存入结果集
			while(rs.next()){//查到如果有值就把值的每一个字段存到一个对象里面
				Products p=new Products();//实例化对象
				p.setId(rs.getString(1));
				p.setName(rs.getString(2));
				p.setPrice(rs.getDouble(3));
				p.setCategory(rs.getString(4));
				p.setPnum(rs.getInt(5));
				p.setImgurl(rs.getString(6));
				p.setDescription(rs.getString(7));
				plist.add(p);//将实例化的对象存入数组
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DBUtil.closeAll();
		}
		return plist;//返回数组对象
	}
	/**
	 * 前端根据用户选择的种类显示此类商品
	 * @param category
	 * @return
	 */
	public int selectProductCount(String category){
		int totalCount=0;
		conn=DBUtil.getConn();
		String sql="";
		if(category.equals("全部商品")){
			sql="select count(*) from products";
		}else{
			sql="select count(*) from products where category='"+category+"'";
		}
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				totalCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DBUtil.closeAll();
		}
		return totalCount;
	}
	/**
	 * 查询全部商品
	 * 前后端公用此功能
	 * @return
	 */
	public ArrayList<Products> selectAll(){
		ArrayList<Products> plist=new ArrayList<Products>();
		conn=DBUtil.getConn();
		try {
			String sql="select * from products";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				Products p=new Products();
				p.setId(rs.getString(1));
				p.setName(rs.getString(2));
				p.setPrice(rs.getDouble(3));
				p.setCategory(rs.getString(4));
				p.setPnum(rs.getInt(5));
				p.setImgurl(rs.getString(6));
				p.setDescription(rs.getString(7));
				plist.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DBUtil.closeAll();
		}
		return plist;
	}
	/**
	 * 添加商品
	 * @param products
	 */
	public void insertProducts(Products products){
		conn=DBUtil.getConn();
		//语句
		try {
			//Statement stmt=conn.createStatement();
			String sql="insert into products(id,name,price,category,pnum,description) values(?,?,?,?,?,?)";
			ptmt=conn.prepareStatement(sql);
			String uid=UUID.randomUUID().toString();
			ptmt.setString(1, uid);
			ptmt.setString(2, products.getName());
			ptmt.setDouble(3, products.getPrice());
			ptmt.setString(4, products.getCategory());
			ptmt.setInt(5, products.getPnum());
			ptmt.setString(6, products.getDescription());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();
		}
	}
	/**
	 * 根据ID删除此商品
	 * @param id
	 */
	public void delProductsById(String id){
		conn=DBUtil.getConn();
		try {
			String sql="delete from products where id='"+id+"'";
			stmt=conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();
		}
	}
	/**
	 * 根据用户选择的条件查询对应商品
	 * @param id
	 * @param name
	 * @param category
	 * @param minprice
	 * @param maxprice
	 * @return
	 */
	public ArrayList<Products> selectProductByManyCondition(String id,String name,String category,double minprice,double maxprice){
		ArrayList<Products> plist=new ArrayList<Products>();
		conn=DBUtil.getConn();
		try {
			String sql="";
			sql="select * from products where 1=1";
			if(!id.equals("") && id!=null){
				sql=sql+" and id='"+id+"'";
			}
			if(!category.equals("") && category!=null){
				sql=sql+" and category='"+category+"'";
			}
			if(!name.equals("") && name!=null){
				sql=sql+" and name='"+name+"'";
			}
			if(minprice>0){
				sql=sql+" and price>"+minprice;
			}
			if(maxprice>0){
				sql=sql+" and price<"+maxprice;
			}
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				Products p=new Products();
				p.setId(rs.getString(1));
				p.setName(rs.getString(2));
				p.setPrice(rs.getDouble(3));
				p.setCategory(rs.getString(4));
				p.setPnum(rs.getInt(5));
				p.setImgurl(rs.getString(6));
				p.setDescription(rs.getString(7));
				plist.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DBUtil.closeAll();
		}
		return plist;
	}
	/**
	 * 根据id找到商品数据并存到Products对象并返回此对象
	 * @param id
	 * @return
	 */
	public Products findProductsById(String id){
		Products p=new Products();
		conn=DBUtil.getConn();
		try {
			String sql="select * from products where id='"+id+"'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				p.setId(rs.getString(1));
				p.setName(rs.getString(2));
				p.setPrice(rs.getDouble(3));
				p.setCategory(rs.getString(4));
				p.setPnum(rs.getInt(5));
				p.setImgurl(rs.getString(6));
				p.setDescription(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();
		}
		return p;
	}
	/**
	 * 修改书本信息
	 * @param products
	 */
	public void updateProducts(Products products){
		conn=DBUtil.getConn();
		try {
			String sql="update products set name=?,price=?,category=?,pnum=?,description=? where id=?";
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, products.getName());
			ptmt.setDouble(2, products.getPrice());
			ptmt.setString(3, products.getCategory());
			ptmt.setInt(4, products.getPnum());
			ptmt.setString(5, products.getDescription());
			ptmt.setString(6, products.getId());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();
		}
	}
}
