package com.newsService.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newsService.dao.MemberDao;
import com.newsService.dao.impl.MemberDaoImpl;
import com.newsService.po.Member;
import com.newsService.utils.URLUtiluty;

public class UpdateMemberInfoServlet extends HttpServlet {
	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		request.setCharacterEncoding("UTF-8");
		
		String account=request.getParameter("account");
		String accIsValid=request.getParameter("accIsValid");
		
		if(accIsValid.equals("0")){
			accIsValid = "1";
		}else if(accIsValid.equals("1")){
			accIsValid = "0";
		}
	
		Member member = new Member();
		
		member.setAccount(account);
		member.setAccIsValid(accIsValid);
		
		HttpSession session=request.getSession();
	
		MemberDao dao=new MemberDaoImpl();
			
		boolean flag=dao.updateInfo(member);
			
		if(flag){
		 request.getSession().setAttribute("update", "修改成功!");
		 response.sendRedirect(URLUtiluty.MEMBERINFO_MANAGER_SERVLETURL);
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
