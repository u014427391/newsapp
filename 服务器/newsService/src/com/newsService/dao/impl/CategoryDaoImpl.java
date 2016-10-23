package com.newsService.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.newsService.dao.CategoryDao;
import com.newsService.po.Category;
import com.newsService.utils.DBConnectionUtil;
import com.newsService.utils.DBHelperUtil;

/**
 */
public class CategoryDaoImpl implements CategoryDao{
	DBHelperUtil manager;
	String sql = "";
	ResultSet rs;
	
//	public CategoryDaoImpl() throws IOException, ClassNotFoundException
//	{
//		manager = DBHelperUtil.createInstance();
//	}
	
	/**
	 * 获取新闻类型
	 * @param startTid 起始类型编号
	 * @param count 数量
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Category> getTypes(int startTid,int count)
	{
		
		ArrayList<Category> list = new ArrayList<Category>();
		sql = "select cid,title from t_category where deleted = false order by sequnce LIMIT ?,?";
		Object[] params = new Object[]{startTid,count};
		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			rs = manager.executeQuery(sql, params);
			while(rs.next())
			{
				Category category = new Category(rs.getInt("cid"), rs.getString("title"));
				list.add(category);
			}
			manager.closeDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean add(Category category)
	{
		boolean flag = false;
		Connection conn = new DBConnectionUtil().getConnection();
		
		String sql = "insert into t_category(title,sequnce) values(?,?)";
		
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setString(1, category.getTitle());
			sta.setInt(2, category.getSequnce());
			
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
	
	public boolean update(Category category)
	{
		boolean flag=false;
		Connection conn=new DBConnectionUtil().getConnection();
		
		String sql="update t_category set title=? where cid=?";
		
		try {
			PreparedStatement sta=conn.prepareStatement(sql);
			sta.setString(1, category.getTitle());
			sta.setInt(2, category.getCid());
			int i=sta.executeUpdate();
			if(i>0){
				flag=true;
			}else{
				flag=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		return flag;
	}
	
	public void delete(int tid)
	{
		throw new NotImplementedException();
	}
	
	public ArrayList<Category> getAllTypes()
	{
		ArrayList<Category> list = new ArrayList<Category>();
		Connection conn = new DBConnectionUtil().getConnection();
		
		String sql = "select * from t_category";
		
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			
			ResultSet res = sta.executeQuery();
			while(res.next()){
				Category cate = new Category();
				cate.setTitle(res.getString("title"));
				cate.setCid(res.getInt("cid"));
				cate.setSequnce(res.getInt("sequnce"));
				list.add(cate);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		
		return list;
	}

	public Category getInfoById(String cid) {
		// TODO Auto-generated method stub
		Category category = new Category();
		Connection conn = new DBConnectionUtil().getConnection();
		String sql = "select * from t_category where cid=?";
		
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1, Integer.parseInt(cid));
			ResultSet res = sta.executeQuery();
			while(res.next()){
				category.setCid(res.getInt("cid"));
				category.setTitle(res.getString("title"));
				category.setSequnce(res.getInt("sequnce"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		
		return category;
	}
}
