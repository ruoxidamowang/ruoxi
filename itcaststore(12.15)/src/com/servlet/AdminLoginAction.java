package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.User;
import com.service.UserService;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet(name = "adminLogin", urlPatterns = { "/adminLoginAction" })
public class AdminLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("loginName");
		String password=request.getParameter("loginPwd");
		HttpSession session=request.getSession(true);
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		
		UserService userservice=new UserService();
		User user2=userservice.login(user);
		
		if(user2!=null && user2.getRole().equals("超级用户"))
		{
			session.setAttribute("user", user2);
			response.sendRedirect((request.getContextPath())+"/admin/login/home.jsp");
		}
		else{
			request.setAttribute("loginFailmessage","用户名或密码输入错误，请重试！");
			request.getRequestDispatcher("/admin/login/login.jsp").forward(request, response);
		}
	}

}
