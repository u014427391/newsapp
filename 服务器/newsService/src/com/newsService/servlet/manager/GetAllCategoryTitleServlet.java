package com.newsService.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newsService.dao.CategoryDao;
import com.newsService.dao.impl.CategoryDaoImpl;
import com.newsService.po.Category;
import com.newsService.utils.URLUtiluty;

public class GetAllCategoryTitleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		//String cid = request.getParameter("cid");
		
		CategoryDao dao;
	
		dao = new CategoryDaoImpl();
		
		List<Category> list = dao.getAllTypes();
			
		HttpSession session=request.getSession();
		session.setAttribute("categoryInfos", list);
			
		response.sendRedirect(URLUtiluty.NEWS_ADD_URL);
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
