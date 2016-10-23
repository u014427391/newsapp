package com.newsService.po;

/**
 *新闻评论Model
 */
public class Comment
{
	//评论的ID号
	private int cid;
	//新闻的ID号
	private int nid;
	//发布时间
	private String ptime;
	//评论人地区
	private String region;
	//评论内容
	private String content;
	//支持人数
	private int supportCount;
	//反对人数
	private int opposeCount;
	private boolean deleted;

	public Comment()
	{
		super();
	}

	public Comment(int cid, int nid, String ptime, String region, String content, int supportCount, int opposeCount, boolean deleted)
	{
		super();
		this.cid = cid;
		this.nid = nid;
		this.ptime = ptime;
		this.region = region;
		this.content = content;
		this.supportCount = supportCount;
		this.opposeCount = opposeCount;
		this.deleted = deleted;
	}

	public int getCid()
	{
		return cid;
	}

	public void setCid(int cid)
	{
		this.cid = cid;
	}

	public int getNid()
	{
		return nid;
	}

	public void setNid(int nid)
	{
		this.nid = nid;
	}

	public String getPtime()
	{
		return ptime;
	}

	public void setPtime(String ptime)
	{
		this.ptime = ptime;
	}

	public String getRegion()
	{
		return region;
	}

	public void setRegion(String region)
	{
		this.region = region;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public int getSupportCount()
	{
		return supportCount;
	}

	public void setSupportCount(int supportCount)
	{
		this.supportCount = supportCount;
	}

	public int getOpposeCount()
	{
		return opposeCount;
	}

	public void setOpposeCount(int opposeCount)
	{
		this.opposeCount = opposeCount;
	}

	public boolean isDeleted()
	{
		return deleted;
	}

	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}
}
