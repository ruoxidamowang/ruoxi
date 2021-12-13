package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Notice;
import com.service.NoticeService;

/**
 * Servlet implementation class FindOrders
 */
@WebServlet(name = "findOrders", urlPatterns = { "/showAllNoticeAction" })
public class ShowAllNoticesAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllNoticesAction() {
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
		NoticeService noticeService=new NoticeService();//创建一个noticeservice对象
		ArrayList<Notice> notices=noticeService.findAllNotices();
		//用noticeService调用service中的方法并用notice类型的数组来接收方法中的返回值
		request.setAttribute("notices", notices);//将接受到的返回值存入request中并命名为notices
		request.getRequestDispatcher("/admin/notices/list.jsp").forward(request, response);//跳转到商品列表页面
	}

}
