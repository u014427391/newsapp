package com.newsService.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newsService.dao.NewsDao;
import com.newsService.dao.impl.NewsDaoImpl;
import com.newsService.po.News;
import com.newsService.utils.URLUtiluty;

public class GetNewsByIdServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		request.setCharacterEncoding("UTF-8");
		
		String nid=request.getParameter("nid");
		//String title=request.getParameter("title");
		
		//Category cate = new Category();
		//cate.setCid(Integer.valueOf(cid));
		//cate.setTitle(title);
		
		HttpSession session=request.getSession();
		
		NewsDao dao = new NewsDaoImpl();
		
		News news = dao.getNewsById(Integer.parseInt(nid));
			
		request.getSession().setAttribute("new", news);
		response.sendRedirect(URLUtiluty.NEWS_UPDATE_URL);
		
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
