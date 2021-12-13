package com.service;

import com.dao.UserDAO;
import com.entity.User;

public class UserService {
	public UserDAO userdao=new UserDAO();
	//调用查看用户名密码是否输入正确
	public User login(User user)//登录方法，参数为servlet传过来的user对象，返回值为user对象
	{
		User user2=userdao.selectUserByUsernameAndPassword(user);//调用dao里的查询用户名和密码的方法并用user2对象来接收返回值
		return user2;//返回一个user2对象
	}
	//首先调用查询要注册的用户名是否已被注册 没注册则返回true 注册过则返回false
	public boolean register(User user)
	{
		boolean c=false;
		boolean b=userdao.selectUserByUsername(user);
		if(!b)
		{
			c=true;
			userdao.insertUser(user);
		}
		return c;
	}
}
