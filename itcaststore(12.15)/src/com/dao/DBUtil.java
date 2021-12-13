package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public static Connection conn=null;//创建链接对象
	public static PreparedStatement ptmt=null;//创建预编译语句对象
	public static Statement stmt=null;//创建语句对象
	public static ResultSet rs=null;//创建结果集对象
	/**
	 * 数据库连接方法
	 * @return
	 */
	public static Connection getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");//驱动
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/itcaststore?useUnicode=true&characterEncoding=utf-8","root","123456");//mysql链接，转字符集，账号密码
		} catch (ClassNotFoundException e) {//处理异常
			// TODO Auto-generated catch block
			System.out.println("加载异常");
			e.printStackTrace();
		} catch (SQLException e) {//处理异常
			// TODO Auto-generated catch block
			System.out.println("连接异常");
			e.printStackTrace();
		}
		return conn;//返回连接对象
	}
	/**
	 * 数据库关闭方法
	 */
	public static void closeAll(){
		try {
			if(rs!=null){rs.close();}//关闭结果集
			if(ptmt!=null){ptmt.close();}//关闭预编译语句对象
			if(stmt!=null){stmt.close();}//关闭语句对象
			if(conn!=null){conn.close();}//关闭数据库连接
		} catch (SQLException e) {//处理异常
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
