package com.newsService.dao;

import java.util.List;

import com.newsService.po.Member;

public interface MemberDao {

	public List<Member> getAllInfo();
	
	/**
	 * 修改会员信息
	 */
	public boolean updateInfo(Member member);
	
	/**
	 * 会员注册
	 */
	public boolean register(String account,String password,int credits,String rank,String accIsValid);

	/**
	 * 登录验证
	 */
	public boolean loginCheck(String account,String password,String accIsValid);
}
