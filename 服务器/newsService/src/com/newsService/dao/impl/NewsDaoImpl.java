package com.newsService.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newsService.dao.NewsDao;
import com.newsService.po.News;
import com.newsService.utils.DBConnectionUtil;
import com.newsService.utils.DBHelperUtil;


/**
 * 
 */
public class NewsDaoImpl implements NewsDao{
	DBHelperUtil manager;
	String sql = "";
	ResultSet rs;

//	public NewsDaoImpl() throws IOException, ClassNotFoundException
//	{
//		manager = DBHelperUtil.createInstance();
//	}

	/**
	 * 获取新闻内容
	 * @param nid 新闻编号
	 * @return
	 * @throws SQLException
	 */
	public News getNews(int nid)
	{
		sql = "SELECT nid,cid,title,body,source,ptime,imgsrc FROM t_news WHERE nid=? and deleted=false";
		Object[] params = new Object[]{ nid };
		News news = new News();
		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			rs = manager.executeQuery(sql, params);
			
			if (rs.next())
			{
				news.setNid(rs.getInt("nid"));
				news.setCid(rs.getInt("cid"));
				news.setTitle(rs.getString("title"));
				news.setBody(rs.getString("body"));
				news.setSource(rs.getString("source"));
				news.setPtime(rs.getString("ptime"));
				news.setImgSrc(rs.getString("imgsrc"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				manager.closeDB();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return news;
	}

	/**
	 * 获取指定类别的新闻列表
	 * @param tid 新闻类型
	 * @param startNid 起始编号
	 * @param count 返回数量
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<News> getSpecifyCategoryNews(int tid, int startNid, int count){
		ArrayList<News> list = new ArrayList<News>();
		sql = "SELECT nid,cid,title,digest,source,ptime,imgsrc FROM t_news WHERE cid=? AND deleted=false ORDER BY ptime desc LIMIT ?,?";
		Object[] params = new Object[]{ tid, startNid, count };
		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			rs = manager.executeQuery(sql, params);
			while (rs.next())
			{
				News news = new News();
				news.setNid(rs.getInt("nid"));
				news.setCid(rs.getInt("cid"));
				news.setTitle(rs.getString("title"));
				news.setDigest(rs.getString("digest"));
				news.setSource(rs.getString("source"));
				news.setPtime(rs.getString("ptime"));
				news.setImgSrc(rs.getString("imgsrc"));
				list.add(news);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				manager.closeDB();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<News> getAllNews() {
		List<News> list = new ArrayList<News>();
		Connection conn = new DBConnectionUtil().getConnection();
		
		String sql = "select * from t_news";
		
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			
			ResultSet res = sta.executeQuery();
			while(res.next()){
				News news = new News();
				news.setTitle(res.getString("title"));
				news.setDigest(res.getString("digest"));
				news.setSource(res.getString("source"));
				news.setPtime(res.getString("ptime"));
				news.setImgSrc(res.getString("imgsrc"));
				news.setNid(res.getInt("nid"));
				list.add(news);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		
		return list;
	}

	public boolean addNews(News news) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection conn = new DBConnectionUtil().getConnection();
		
		String sql = "insert into t_news(cid,title,digest,body,source,ptime) values(?,?,?,?,?,?)";
		
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1, news.getCid());
			sta.setString(2, news.getTitle());
			sta.setString(3, news.getDigest());
			sta.setString(4, news.getBody());
			sta.setString(5, news.getSource());
			sta.setString(6, news.getPtime());
			
			int i = sta.executeUpdate();
			
			if(i>0){
				flag = true;
			}else{
				flag = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		
		return flag;
	}

	public News getNewsById(int nid) {
		// TODO Auto-generated method stub
		News news = new News();
		Connection conn = new DBConnectionUtil().getConnection();
		
		String sql = "select * from t_news where nid=?";
		
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1, nid);
			
			ResultSet res = sta.executeQuery();
			
			while(res.next()){
				news.setNid(res.getInt("nid"));
				news.setTitle(res.getString("title"));
				news.setDigest(res.getString("digest"));
				news.setPtime(res.getString("ptime"));
				news.setBody(res.getString("body"));
				news.setSource(res.getString("source"));
				//news.setCommentCount(res.getInt("commentCount"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		
		return news;
	}

	public boolean deleteNews(int nid) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		Connection conn = new DBConnectionUtil().getConnection();
		
		String sql = "delete from t_news where nid=?";
		PreparedStatement sta;
		try {
			sta = conn.prepareStatement(sql);
			sta.setInt(1, nid);
			int i = sta.executeUpdate();
			
			if(i>0){
				flag= true;
			}else{
				flag = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		
		return flag;
	}

	public boolean updateNews(News news) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection conn = new DBConnectionUtil().getConnection();
		String sql = "update t_news set title=?,digest=?,body=?,source=? where nid=?";
		
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setString(1, news.getTitle());
			sta.setString(2, news.getDigest());
			sta.setString(3, news.getBody());
			sta.setString(4, news.getSource());
			sta.setInt(5, news.getNid());
			
			int i = sta.executeUpdate();
			if(i>0){
				flag = true;
			}else{
				flag = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		
		return flag;
	}
}
