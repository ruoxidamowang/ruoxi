package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.entity.User;
import com.service.UserService;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet(name = "loginAction", urlPatterns = { "/clientloginAction" })
public class ClientLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientLoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//将字符集转换为UTF-8
		String username=request.getParameter("username");//获取登录表单中用户输入的用户名并存入username字符串中
		String password=request.getParameter("password");//获取登录表单中用户输入的密码并存入password字符串中
		HttpSession session=request.getSession(true);//创建一个session对象
		User user=new User();//创建一个user对象
		user.setUsername(username);//将上面获取到的用户名封装到user对象的Username属性中
		user.setPassword(password);//将上面获取到的用户名封装到user对象的Password属性中
		
		UserService userservice=new UserService();//创建一个userservice对象
		User user2=userservice.login(user);
		//使用userservice对象调用service中的login方法并将上面的user对象作为参数传过去并用一个user对象来接收此方法的返回值
		if(user2!=null)//判断上面接受到的返回值是否为空
		{
			//不为空执行以下代码
			session.setAttribute("user", user2);//将上面得到的返回值存入session对象中并命名为user
			response.sendRedirect((request.getContextPath())+"/client/index.jsp");//跳转到网站主页index
		}
		else{//user2为空时执行以下代码
			request.setAttribute("loginFailmessage","用户名或密码输入错误，请重试！");
			//将"用户名或密码输入错误，请重试！"存入request并命名为loginFailmessage
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);//跳转到登录页面，也就是当前页面
		}
	}

}