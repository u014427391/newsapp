package com.newsService.po;
/**
 * 会员信息的实体类
 *
 */
public class Member {

	
	/**
	 * 账号
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 积分
	 */
	private int credits;
	
	/**
	 * 等级
	 */
	private String rank;
	
	/**
	 * 是否有效
	 */
	private String accIsValid;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getAccIsValid() {
		return accIsValid;
	}

	public void setAccIsValid(String accIsValid) {
		this.accIsValid = accIsValid;
	}
	
	
}
