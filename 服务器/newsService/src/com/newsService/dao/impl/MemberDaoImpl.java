package com.newsService.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newsService.dao.MemberDao;
import com.newsService.po.Member;
import com.newsService.utils.DBConnectionUtil;

public class MemberDaoImpl implements MemberDao{

	Connection conn ;
	PreparedStatement sta;
	ResultSet res;
	
	public List<Member> getAllInfo() {
		// TODO Auto-generated method stub
		List<Member> list = new ArrayList<Member>();
		conn = new DBConnectionUtil().getConnection();
		String sql = "select * from t_members";
		
		try {
			sta = conn.prepareStatement(sql);
			res = sta.executeQuery();
			while(res.next()){
				Member member = new Member();
				member.setAccount(res.getString("account"));
				member.setCredits(res.getInt("credits"));
				member.setPassword(res.getString("password"));
				member.setRank(res.getString("rank"));
				member.setAccIsValid(res.getString("accIsValid"));
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			new DBConnectionUtil().close();
		}
		return list;
	}

	public boolean updateInfo(Member member) {
		boolean flag=false;
		conn=new DBConnectionUtil().getConnection();
		
		String sql="update t_members set accIsValid=? where account=?";
		
		try {
			sta=conn.prepareStatement(sql);
			sta.setString(1, member.getAccIsValid());
			sta.setString(2, member.getAccount());
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

	public boolean register(String account, String password,int credits,
			String rank, String accIsValid) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		Connection conn = new DBConnectionUtil().getConnection();
		
		String sql = "insert into t_members(account,password,credits,rank,accIsValid) values(?,?,?,?,?)";
		
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setString(1, account);
			sta.setString(2, password);
			sta.setInt(3, credits);
			sta.setString(4, rank);
			sta.setString(5, accIsValid);
			
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

	public boolean loginCheck(String account, String password, String accIsValid) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection conn = new DBConnectionUtil().getConnection();
		
		String sql = "select * from t_members where account=?";
		
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setString(1, account);
			
			ResultSet res = sta.executeQuery();
			if(res.next()){
				String pwd = res.getString("password");
				String aiv = res.getString("accIsValid");
				if(aiv.equals("1")&&password.equals(pwd)){
					flag = true;
				}else{
					flag = false;
				}
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

}
