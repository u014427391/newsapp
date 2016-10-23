package com.newsService.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.newsService.dao.ManagerDao;
import com.newsService.po.Manager;
import com.newsService.utils.DBConnectionUtil;

public class ManagerDAOImpl implements ManagerDao{

	Connection conn;
	PreparedStatement sta;
	ResultSet res;
	
	/**
	 * 管理员登录验证
	 */
	public boolean checkLogin(String id,String pwd) {
		boolean flag=false;
		conn=new DBConnectionUtil().getConnection();
		String sql="select * from t_manager where account=?";
		
		try {
			sta=conn.prepareStatement(sql);
			sta.setInt(1, Integer.parseInt(id));
			res=sta.executeQuery();
			if(res.next()){
				String userID=String.valueOf(res.getInt("account"));
				String userPwd=res.getString("password");
				if(id.equals(userID)&&pwd.equals(userPwd)){
					flag=true;
				}else{
					flag=false;
				}
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
	
	/**
	 * 通过管理员号获取管理员个人信息
	 */
	public Manager getManagerInfo(String id) {
		Manager a=new Manager();
		
		conn=new DBConnectionUtil().getConnection();
		
		String sql="select * from t_manager where account=?";
		
		try {
			sta=conn.prepareStatement(sql);
			sta.setInt(1, Integer.parseInt(id));
			
			res=sta.executeQuery();
			
			if(res.next()){
				a.setAccount(String.valueOf(res.getInt("account")));
				a.setRank(res.getString("rank"));
				a.setName(res.getString("name"));
				a.setPassword(res.getString("password"));
			}else{
				a=null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		return a;
	}
	
}
