package com.newsService.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newsService.dao.ManagerDao;
import com.newsService.dao.impl.ManagerDAOImpl;

public class LoginCheckServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String LOGIN_URL="/newsServer/login.jsp";
	
	private static final String MAIN_URL="/newsService/index.jsp";

	ManagerDao adao;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		//用户输入的验证码
		String authCode=request.getParameter("j_captcha");
		
		//String userType=request.getParameter("userType");
		
		//通过账号获取用户名
		adao=new ManagerDAOImpl();
		
		HttpSession session=request.getSession();
		
		//自动生成的验证码信息
		String code=(String)session.getAttribute("rand");
		
		if(account==null||account.equals("")){
			response.sendRedirect(LOGIN_URL);
			session.setAttribute("errorType", "账号不可以为空!");
		}else if(password==null||password.equals("")){
			response.sendRedirect(LOGIN_URL);
			session.setAttribute("errorType", "密码不可以为空!");
		}else if(authCode==null||authCode.equals("")){
			response.sendRedirect(LOGIN_URL);
			session.setAttribute("errorType", "验证码不可以为空!");
		}else{
				if(adao.checkLogin(account, password)&&code.equals(authCode)){
					String id=request.getParameter("account");
					session.setAttribute("adminID", id);
					response.sendRedirect(MAIN_URL);
				}else if(!code.equals(authCode)){
					response.sendRedirect(LOGIN_URL);
					session.setAttribute("errorType", "验证码输入错误!");
				}else if(!adao.checkLogin(account, password)){
					response.sendRedirect(LOGIN_URL);
					session.setAttribute("errorType", "账号或密码输入错误!");
				}
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
