package com.newsService.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newsService.dao.CommentDao;
import com.newsService.dao.NewsDao;
import com.newsService.dao.impl.CommentDaoImpl;
import com.newsService.dao.impl.NewsDaoImpl;
import com.newsService.utils.URLUtiluty;

public class DeleteNewsServlet extends HttpServlet {

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
		
		HttpSession session=request.getSession();
			
		NewsDao dao = new NewsDaoImpl();
		
		boolean flag=dao.deleteNews(Integer.parseInt(nid));
			
		if(flag){
			 request.getSession().setAttribute("update", "修改成功!");
			 response.sendRedirect(URLUtiluty.NEWS_MANAGER_SERVLETURL);
		}
		
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
