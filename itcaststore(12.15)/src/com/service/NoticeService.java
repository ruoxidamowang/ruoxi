package com.service;

import java.util.ArrayList;

import com.dao.NoticesDAO;
import com.entity.Notice;

public class NoticeService {
	public NoticesDAO noticedao=new NoticesDAO();
	
	public Notice showNewNotice()
	{
		Notice n=new Notice();
		n=noticedao.showNewNotice();
		return n;
	}
	
	public ArrayList<Notice> findAllNotices(){//查找所有公告方法，返回值为Notice类型的数组
		ArrayList<Notice> notice=new ArrayList<Notice>();//创建一个Notice类型的数组对象名为notice
		notice=noticedao.selectAllNotices();//调用dao里的查询所有公告的方法并用上面创建的数组对象接受dao里查询所有公告方法的返回值
		return notice;//返回接收到的dao返回的数组对象
	}
	
	public void addNotices(Notice notice){
		noticedao.insertNotices(notice);
	}
	
	public void delNotices(String id){
		noticedao.delNotices(id);
	}
	
	public Notice findNoticesByID(String id){
		Notice notice=new Notice();
		notice=noticedao.selectNoticesByID(id);
		return notice;
	}
	
	public void editNotices(Notice notice){
		noticedao.updateNotices(notice);
	}
}
