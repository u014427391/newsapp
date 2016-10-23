package com.newsService.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.newsService.dao.CommentDao;
import com.newsService.po.Comment;
import com.newsService.utils.DBConnectionUtil;
import com.newsService.utils.DBHelperUtil;

/**
 */
public class CommentDaoImpl implements CommentDao{
	DBHelperUtil manager;
	String sql = "";
	ResultSet rs;
	
//	public CommentDaoImpl() throws IOException, ClassNotFoundException
//	{
//		manager = DBHelperUtil.createInstance();
//	}
//	
	/**
	 * 获取回复信息
	 * @param nid 新闻编号
	 * @param startRid 起始ID
	 * @param count 返回数量
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Comment> getComments(int nid,int startCid,int count) 
	{
		ArrayList<Comment> list = new ArrayList<Comment>();
		sql = "select cid,nid,ptime,region,content,supportcount,opposecount from t_comment where nid=? and deleted=false order by ptime desc limit ?,?";
		Object[] params = new Object[]{ nid, startCid, count };
		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			rs = manager.executeQuery(sql, params);
			while (rs.next())
			{
				Comment comment = new Comment();
				comment.setCid(rs.getInt("cid"));
				comment.setNid(rs.getInt("nid"));
				comment.setPtime(rs.getString("ptime"));
				comment.setRegion(rs.getString("region"));
				comment.setContent(rs.getString("content"));
				comment.setSupportCount(rs.getInt("supportcount"));
				comment.setOpposeCount(rs.getInt("opposecount"));
				list.add(comment);
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
	
	/**
	 * 保存新评论
	 * @param nid 新闻编号
	 * @param ptime 发表时间
	 * @param region 地区
	 * @param content 内容
	 * @throws SQLException
	 */
	public void addComment(int nid,String ptime,String region,String content) 
	{
		sql = "INSERT INTO t_comment (nid,ptime,region,content) VALUES (?,?,?,?)";
		Object[] params = new Object[] { nid, ptime, region,content };
		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			manager.executeUpdate(sql, params);
			manager.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取指定新闻评论数量
	 * @param nid
	 * @return
	 * @throws SQLException
	 */
	public long getSpecifyNewsCommentsCount(int nid)
	{
		long count = 0;
		sql = "select count(cid) as count from t_comment where nid=?";
		Object[] params = new Object[]{ nid };
		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			rs = manager.executeQuery(sql, params);
			if (rs.next())
			{
				count = rs.getLong("count");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	public List<Comment> getAllComments() {
		
		List<Comment> list = new ArrayList<Comment>();
		
		Connection conn = new DBConnectionUtil().getConnection();
		
		String sql = "select * from t_comment";
		
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet res = sta.executeQuery();
			while(res.next()){
				Comment c = new Comment();
				c.setCid(res.getInt("cid"));
				c.setNid(res.getInt("nid"));
				c.setPtime(res.getString("ptime"));
				c.setRegion(res.getString("region"));
				c.setContent(res.getString("content"));
				c.setSupportCount(res.getInt("supportcount"));
				c.setOpposeCount(res.getInt("opposecount"));
				list.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		
		return list;
	}

	public boolean removeInfo(int cid) {
		boolean flag = false;
		Connection conn = new DBConnectionUtil().getConnection();
		
		String sql = "delete from t_comment where cid=?";
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1, cid);
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
