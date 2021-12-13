package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.entity.User;

public class UserDAO {
	public static Connection conn=null;
	public static PreparedStatement ptmt=null;
	public static Statement stmt=null;
	public static ResultSet rs=null;
	/**
	 * 用户注册成功后将数据插入数据库库
	 * @param user
	 */
	public void insertUser(User user){
		conn=DBUtil.getConn();
		//语句
		try {
			//Statement stmt=conn.createStatement();
			String sql="insert into user(username,password,gender,email,telephone,introduce) values(?,?,?,?,?,?)";
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, user.getUsername());
			ptmt.setString(2, user.getPassword());
			ptmt.setString(3, user.getGender());
			ptmt.setString(4, user.getEmail());
			ptmt.setString(5, user.getTelephone());
			ptmt.setString(6, user.getIntroduce());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();
		}
	}
	/**
	 * 根据用户输入判断此账户是否存在
	 * @param user
	 * @return
	 */
	public User selectUserByUsernameAndPassword(User user){
		User user2=null;//方法全局中创建一个空的user2对象
		conn=DBUtil.getConn();//加载，连接
		String sql="select * from user where username=? and password=?";//查询语句
		try {
			ptmt = conn.prepareStatement(sql);//预编译语句
			ptmt.setString(1, user.getUsername());//将参数user中的Username属性中的值提取出来放入第一个问号
			ptmt.setString(2, user.getPassword());//将参数user中的Password属性中的值提取出来放入第二个问号
			rs=ptmt.executeQuery();//执行sql语句并返回结果集存入rs对象中
			if(rs.next()){//sql语句执行完后结果中的下一行是否有数据
				user2=new User();//方法局部创建一个user2对象
				user2.setId(rs.getInt("id"));
				user2.setUsername(rs.getString("username"));
				user2.setPassword(rs.getString("password"));
				user2.setGender(rs.getString("gender"));
				user2.setEmail(rs.getString("email"));
				user2.setTelephone(rs.getString("telephone"));
				user2.setIntroduce(rs.getString("introduce"));
				user2.setActiveCode(rs.getString("activeCode"));
				user2.setState(rs.getInt("state"));
				user2.setRole(rs.getString("role"));
				user2.setRegistTime(rs.getString("registtime"));
				//上面一整块：如果读取到数据则将读取到的所有数据存入user2对象中
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();//释放语句
		}
		return user2;//返回user2对象
	}
	/**
	 * 根据用户名查询此用户是否已注册
	 * @param user
	 * @return
	 */
	public boolean selectUserByUsername(User user){
		boolean b=false;
		conn=DBUtil.getConn();
		String sql="select * from user where username=? ";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, user.getUsername());
			rs=ptmt.executeQuery();
			if(rs.next()){
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();
		}
		return b;
	}
}
