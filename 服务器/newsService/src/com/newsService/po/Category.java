package com.newsService.po;

/**
 * 新闻分类Category
 */
public class Category
{
	//种类ID号
	private int cid;
	//种类标题
	private String title;
	private int sequnce;
	private boolean deleted;

	public Category()
	{
		super();
	}

	public Category(int cid, String title)
	{
		this(cid, title, 0, false);
	}
	
	public Category(int cid, String title, int sequnce, boolean deleted)
	{
		super();
		this.cid = cid;
		this.title = title;
		this.sequnce = sequnce;
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

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getSequnce()
	{
		return sequnce;
	}

	public void setSequnce(int sequnce)
	{
		this.sequnce = sequnce;
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
