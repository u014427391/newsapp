package com.newsService.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.newsService.dao.impl.CommentDaoImpl;
import com.newsService.po.Comment;
import com.newsService.utils.TextUtility;

/**
 */
public class GetCommentsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1001921981411122350L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		String nidStr = request.getParameter("nid");
		String startCidStr = request.getParameter("startnid");
		String countStr = request.getParameter("count");
		int nid = 0;
		int startCid = 0;
		int count = 0;
		nid = TextUtility.String2Int(nidStr);
		startCid = TextUtility.String2Int(startCidStr);
		count = TextUtility.String2Int(countStr);
		JSONObject jObject = new JSONObject();
		try
		{
			CommentDaoImpl commentDAO = new CommentDaoImpl();
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			ArrayList<Comment> retList = new ArrayList<Comment>();
			retList = commentDAO.getComments(nid, startCid, count);
			for (Comment comment : retList)
			{
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("cid", comment.getCid());
				map.put("ptime", comment.getPtime());
				map.put("region", comment.getRegion());
				map.put("content", comment.getContent());
				map.put("supportCount", comment.getSupportCount());
				map.put("opposeCount", comment.getOpposeCount());
				list.add(map);
			}
			JSONObject jObject2 = new JSONObject();
			jObject2.put("totalnum", retList.size());
			jObject2.put("commentslist", list);

			jObject.put("ret", 0);
			jObject.put("msg", "ok");
			jObject.put("data", jObject2);
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				jObject.put("ret", 1);
				jObject.put("msg", e.getMessage());
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		throw new NotImplementedException();
	}
}
