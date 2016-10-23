package com.newsService.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newsService.dao.NewsDao;
import com.newsService.dao.impl.NewsDaoImpl;
import com.newsService.po.News;
import com.newsService.utils.URLUtiluty;

public class AddNewsServlet extends HttpServlet {

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
		//nid,cid,title,digest,body,source,ptime
		String cid = request.getParameter("category");
		String title=request.getParameter("title");
		String digest=request.getParameter("digest");
		String body=request.getParameter("body");
		String source=request.getParameter("source");
		
		Calendar cal=new GregorianCalendar();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH);
		int day=cal.get(Calendar.DAY_OF_MONTH);
			
		String yearStr=String.valueOf(year);
		String monthStr=String.valueOf(month);
		String dayStr=String.valueOf(day);
			
		String ptime=yearStr+"-"+monthStr+"-"+dayStr;

		News news = new News();		
		news.setTitle(title);
		news.setBody(body);
		news.setDigest(digest);
		news.setPtime(ptime);
		news.setSource(source);
		news.setCid(Integer.parseInt(cid));
		
		HttpSession session=request.getSession();
	
		NewsDao dao = new NewsDaoImpl();
		
		boolean flag=dao.addNews(news);
			
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
