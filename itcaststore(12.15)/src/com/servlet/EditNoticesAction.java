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
 * Servlet implementation class EditNoticesAction
 */
@WebServlet("/editNoticesAction")
public class EditNoticesAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditNoticesAction() {
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
		request.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		String title=request.getParameter("title");
		String details=request.getParameter("details");
		
		Notice notice=new Notice();
		notice.setN_id(id);
		notice.setTitle(title);
		notice.setDetails(details);
		
		NoticeService noticeService=new NoticeService();
		noticeService.editNotices(notice);
		
		ArrayList<Notice> notices=noticeService.findAllNotices();
		request.setAttribute("notices", notices);
		request.getRequestDispatcher("/admin/notices/list.jsp").forward(request, response);
	}

}
