package com.newsService.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newsService.dao.CategoryDao;
import com.newsService.dao.MemberDao;
import com.newsService.dao.impl.CategoryDaoImpl;
import com.newsService.dao.impl.MemberDaoImpl;
import com.newsService.po.Category;
import com.newsService.po.Member;
import com.newsService.utils.URLUtiluty;

public class UpdateCategoryServlet extends HttpServlet {

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
		String title=request.getParameter("title");
		
		Category cate = new Category();
		cate.setCid(Integer.valueOf(cid));
		cate.setTitle(title);
		
		HttpSession session=request.getSession();
	
		CategoryDao dao = new CategoryDaoImpl();
		
		boolean flag=dao.update(cate);
			
		if(flag){
			 request.getSession().setAttribute("update", "修改成功!");
			 response.sendRedirect(URLUtiluty.CATEGORY_MANAGER_SERVLETURL);
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
