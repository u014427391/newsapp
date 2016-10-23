package com.newsService.po;



/**
 *
 *新闻Model
 */
public class News
{
	//新闻ID号
	private int nid;
	//新闻种类ID号
	private int cid;
	//新闻标题
	private String title;
	//新闻摘要
	private String digest;
	//新闻正文
	private String body;
	//新闻来源
	private String source;
	//新闻评论人数
	private int commentCount;
	//新闻发布时间
	private String ptime;
	//新闻图片的路径
	private String imgSrc;
	private boolean deleted;

	public News()
	{
		super();
	}

	public News(int nid, int cid, String title, String digest, String body, String source, int commentCount, String ptime, String imgSrc, boolean deleted)
	{
		super();
		this.nid = nid;
		this.cid = cid;
		this.title = title;
		this.digest = digest;
		this.body = body;
		this.source = source;
		this.commentCount = commentCount;
		this.ptime = ptime;
		this.imgSrc = imgSrc;
		this.deleted = deleted;
	}

	public int getNid()
	{
		return nid;
	}

	public void setNid(int nid)
	{
		this.nid = nid;
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

	public String getDigest()
	{
		return digest;
	}

	public void setDigest(String digest)
	{
		this.digest = digest;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public int getReplyCount()
	{
		return commentCount;
	}

	public void setCommentCount(int replyCount)
	{
		this.commentCount = replyCount;
	}

	public String getPtime()
	{
		return ptime;
	}

	public void setPtime(String ptime)
	{
		this.ptime = ptime;
	}

	public String getImgSrc()
	{
		return imgSrc;
	}

	public void setImgSrc(String imgSrc)
	{
		this.imgSrc = imgSrc;
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
