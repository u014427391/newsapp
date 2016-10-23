package com.newsService.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newsService.dao.NewsDao;
import com.newsService.dao.impl.NewsDaoImpl;
import com.newsService.po.News;
import com.newsService.utils.URLUtiluty;

public class GetAllNewsServlet extends HttpServlet {
	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		List<News> list=new ArrayList<News>();
		
		NewsDao dao;
		dao = new NewsDaoImpl();
		list=dao.getAllNews();
		
		HttpSession session=request.getSession();
		session.setAttribute("news", list);
		response.sendRedirect(URLUtiluty.NEWS_MANAGER_URL);
		
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
