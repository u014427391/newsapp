package com.newsService.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newsService.dao.CategoryDao;
import com.newsService.dao.CommentDao;
import com.newsService.dao.impl.CategoryDaoImpl;
import com.newsService.dao.impl.CommentDaoImpl;
import com.newsService.po.Category;
import com.newsService.utils.URLUtiluty;

public class DeleteCommentServlet extends HttpServlet {

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
		
		String cid=request.getParameter("cid");
		
		HttpSession session=request.getSession();
		
		CommentDao dao = new CommentDaoImpl();
		
		boolean flag=dao.removeInfo(Integer.parseInt(cid));
			
		if(flag){
			 request.getSession().setAttribute("update", "修改成功!");
			 response.sendRedirect(URLUtiluty.COMMENT_MANAGER_SERVLETURL);
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
