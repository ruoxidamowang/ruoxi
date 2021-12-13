package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Products;
import com.service.ProductsService;

/**
 * Servlet implementation class FindProductByManyCondition
 */
@WebServlet(name = "findProductByManyCondition", urlPatterns = { "/findProductByManyCondition" })
public class FindProductByManyCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindProductByManyCondition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		double minprice=0;
		String _minprice=request.getParameter("minprice");
		if(_minprice != null && !(_minprice.equals(""))){
			minprice=Double.parseDouble(_minprice);
		}
		double maxprice=0;
		String _maxprice=request.getParameter("maxprice");
		if(_maxprice != null && !(_maxprice.equals(""))){
			maxprice=Double.parseDouble(_maxprice);
		}
		String category=request.getParameter("category");
		
		ProductsService productsService=new ProductsService();
		ArrayList<Products> plist=productsService.findProductByManyCondition(id, name, category, minprice, maxprice);
		request.setAttribute("productslist", plist);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
	}

}
