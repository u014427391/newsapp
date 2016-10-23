package com.newsService.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 数据库连接的工具类
 * @version 1.0.0
 */
public class DBConnectionUtil {
	
	/**
	 * 驱动
	 */
	private String DRIVER="com.mysql.jdbc.Driver";
	
	/**
	 * 链接
	 */
	private String URL="jdbc:mysql://localhost:3306/db_news?useUnicode=true&characterEncoding=UTF-8";
	
	/**
	 * 用户名
	 */
	private String USER="root";
	
	/**
	 * 密码
	 */
	private String PWD="111";
	
	Connection conn=null;
	
	PreparedStatement sta=null;
	
	ResultSet res=null;
	
	public DBConnectionUtil(){
		
	}
	
	/**
	 * 连接数据库
	 */
	public Connection getConnection(){
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭数据库,释放内存
	 */
	public void close(){
		try {
			if(res!=null){
				res.close();
			}
			if(sta!=null){
				sta.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
