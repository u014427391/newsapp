package com.newsService.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.newsService.dao.MemberDao;
import com.newsService.dao.impl.MemberDaoImpl;
import com.newsService.utils.TextUtility;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementedException();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String creditStr = request.getParameter("credits");
		int credits = TextUtility.String2Int(creditStr);
		String rank = request.getParameter("rank");
		String accIsValid = request.getParameter("accIsValid");
		
		JSONObject jObject = new JSONObject();
		
		try {
			
			MemberDao dao = new MemberDaoImpl();
			dao.register(account, password, credits, rank, accIsValid);
			
			jObject.put("ret", 0);
			jObject.put("msg", "ok");
			jObject.put("data", "");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try
			{
				jObject.put("ret", 1);
				jObject.put("msg", "error");
				jObject.put("data", "");
			} catch (JSONException ex)
			{
				ex.printStackTrace();
			}
		}
		
		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}

}
