package com.newsService.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.newsService.dao.impl.CommentDaoImpl;
import com.newsService.utils.TextUtility;

/**
 */
public class PostCommentServlet extends HttpServlet
{
	private static final long serialVersionUID = -7811568044252827351L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		throw new NotImplementedException();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		String nidStr = request.getParameter("nid");
		String region = request.getParameter("region");
		String content = request.getParameter("content");
		int nid = TextUtility.String2Int(nidStr);
		region = TextUtility.toUTF8(region);
		content = TextUtility.toUTF8(content);
		String ptime = TextUtility.formatDate(new Date());
		CommentDaoImpl commentDAO;
		JSONObject jObject = new JSONObject();
		try
		{
			commentDAO = new CommentDaoImpl();
			commentDAO.addComment(nid, ptime, region, content);
				jObject.put("ret", 0);
				jObject.put("msg", "ok");
				jObject.put("data", "");
		} catch (Exception e)
		{
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
