package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.PageBean;
import com.entity.Products;
import com.service.ProductsService;

/**
 * Servlet implementation class ShowProductByPage
 */
@WebServlet(name = "showProductByPage", urlPatterns = { "/showProductByPage" })
public class ShowProductByPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductByPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取商品的分类
		String category="全部商品";
		String _category=request.getParameter("category");
		if(_category!=null){
			category=new String(_category.getBytes("ISO-8859-1"),"UTF-8");
		}
		//获取当前页数
		int currentPage=1;
		String _currentPage=request.getParameter("currentPage");
		if(_currentPage!=null)
		{
			currentPage=Integer.parseInt(_currentPage);
		}
		
		int currentCount=4;
		
		ProductsService productsService=new ProductsService();
		
		ArrayList<Products> plist=productsService.showProducts(currentPage, currentCount, category);
		PageBean pg=new PageBean();
		pg.setCategory(category);
		pg.setPlist(plist);
		pg.setCurrentPage(currentPage);
		pg.setCurrentCount(currentCount);
		int totalCount=productsService.productCount(category);//总个数
		pg.setTotalCount(totalCount);
		int totalPage=(int)Math.ceil(totalCount*1.0/currentCount);//总页数
		pg.setTotalPage(totalPage);
		request.setAttribute("pg", pg);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
