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
 * Servlet implementation class EditProductsAction
 */
@WebServlet(name = "editProductsAction", urlPatterns = { "/editProductsAction" })
public class EditProductsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductsAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		double price=0;
		String _price=request.getParameter("price");
		if(_price != null && !(_price.equals(""))){
			price=Double.parseDouble(_price);
		}
		String category=request.getParameter("category");
		int pnum=0;
		String _pnum=request.getParameter("pnum");
		if(_pnum!=null && !(_pnum.equals(""))){
			pnum=Integer.parseInt(_pnum);
		}
		String description=request.getParameter("description");
		
		Products products=new Products();
		products.setId(id);
		products.setName(name);
		products.setPrice(price);
		products.setPnum(pnum);
		products.setCategory(category);
		products.setDescription(description);
		
		ProductsService productsService=new ProductsService();
		productsService.editProducts(products);
		
		ArrayList<Products> plist=productsService.findProducts();
		request.setAttribute("productslist", plist);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
	}

}
