package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.entity.Notice;

public class NoticesDAO {
	public static Connection conn=null;
	public static PreparedStatement ptmt=null;
	public static Statement stmt=null;
	public static ResultSet rs=null;
	/**
	 * 用户使用主页显示的公告
	 * @return
	 */
	public Notice showNewNotice(){
		Notice n=new Notice();
		conn=DBUtil.getConn();
		try {
			String sql="select * from notice ORDER BY n_time desc";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				n.setDetails(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();
		}
		return n;
	}
	/**
	 * 在管理员页面显示公告所有信息
	 * @return
	 */
	public ArrayList<Notice> selectAllNotices(){//查询所有公告的方法，返回值为Notice数组对象
		ArrayList<Notice> notice=new ArrayList<Notice>();////创建一个Notice类型的数组对象名为notice
		conn=DBUtil.getConn();//加载，连接
		try {
			String sql="select * from notice";//查询数据库中notice表里所有的公告的sql语句
			stmt=conn.createStatement();//创建语句
			rs=stmt.executeQuery(sql);//执行语句并将返回的结果集存入rs对象中
			while(rs.next()){//循环查看执行完语句后的结果中是否有下一行数据，读取完所有数据后就结束循环
				Notice n=new Notice();//创建一个Notice对象名为n
				n.setN_id(rs.getInt(1));//将读取到的第一列id数据存入对象n的N_id的属性中
				n.setTitle(rs.getString(2));//将读取到的第二列标题数据存入对象n的Title属性中
				n.setDetails(rs.getString(3));//将读取到的第三列内容数据存入对象n的Details属性中
				n.setN_time(rs.getString(4));//将读取到的第四列时间数据存入对象n的N_time属性中
				notice.add(n);//将对象n存入数组对象中
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();//释放
		}
		return notice;//返回notice数组对象
	}
	/**
	 * 管理员公告添加功能
	 * @param notice
	 */
	public void insertNotices(Notice notice){
		conn=DBUtil.getConn();
		try {
			String sql="insert into notice(title,details,n_time) values(?,?,?)";
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, notice.getTitle());
			ptmt.setString(2, notice.getDetails());
			Date date=new Date();//生成date对象产生时间
			String Nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//转换时间格式再转为字符串
			ptmt.setString(3, Nowtime);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();
		}
	}
	/**
	 * 管理员删除公告功能
	 * @param id
	 */
	public void delNotices(String id){
		conn=DBUtil.getConn();
		try {
			String sql="delete from notice where n_id='"+id+"'";
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
	 * 管理员修改商品使用的根据商品ID返回需要修改公告的标题和新闻
	 * @param id
	 * @return
	 */
	public Notice selectNoticesByID(String id){
		Notice n=new Notice();
		conn=DBUtil.getConn();
		try {
			String sql="select * from notice where n_id='"+id+"'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				n.setN_id(rs.getInt(1));
				n.setTitle(rs.getString(2));
				n.setDetails(rs.getString(3));
				n.setN_time(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();
		}
		return n;
	}
	/**
	 * 管理员修改完之后在数据库里面更新
	 * @param notice
	 */
	public void updateNotices(Notice notice){
		conn=DBUtil.getConn();
		try {
			String sql="update notice set title=?,details=?,n_time=? where n_id=?";
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, notice.getTitle());
			ptmt.setString(2, notice.getDetails());
			Date date=new Date();//生成date对象产生时间
			String Nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//转换时间格式再转为字符串
			ptmt.setString(3, Nowtime);
			ptmt.setInt(4, notice.getN_id());
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll();
		}
	}
}
