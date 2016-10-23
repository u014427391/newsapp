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

import com.newsService.dao.MemberDao;
import com.newsService.dao.impl.MemberDaoImpl;
import com.newsService.po.Member;
import com.newsService.utils.URLUtiluty;

public class GetMemberInfoServlet extends HttpServlet {

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
	
		String id=request.getParameter("account");
		List<Member> list = new ArrayList<Member>();
		
		MemberDao mdao = new MemberDaoImpl();
		
		list=mdao.getAllInfo();
		
		HttpSession session=request.getSession();
		session.setAttribute("membersInfo", list);
		
		response.sendRedirect(URLUtiluty.MEMBERINFO_MANAGER_URL);
		
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
