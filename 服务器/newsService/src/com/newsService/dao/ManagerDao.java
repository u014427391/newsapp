package com.newsService.dao;

import com.newsService.po.Manager;

public interface ManagerDao {
	/**
	 * 管理员登录验证
	 */
	public boolean checkLogin(String id,String pwd);
	
	/**
	 * 根据ID获取信息
	 * @param id
	 * @return
	 */
	public Manager getManagerInfo(String id);
}
