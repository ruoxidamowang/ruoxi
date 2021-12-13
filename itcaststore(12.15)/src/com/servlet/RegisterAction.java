package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;

import com.entity.User;
import com.service.UserService;
/**
 * Servlet implementation class RegisterAction
 */
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAction() {
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
		HttpSession session=request.getSession(true);
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		String email=request.getParameter("email");
		String telephone=request.getParameter("telephone");
		String introduce=request.getParameter("introduce");
		
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setGender(gender);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setIntroduce(introduce);
		
		UserService userservice=new UserService();
		boolean c=userservice.register(user);
		if(c)
		{
			response.sendRedirect((request.getContextPath())+"/client/registersuccess.jsp");
		}
		else{
			request.setAttribute("registerFailmessage","用户名已被注册，请重新输入！");
			request.getRequestDispatcher("/client/register.jsp").forward(request, response);
		}
	}

}
