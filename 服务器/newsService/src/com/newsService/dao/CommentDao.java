package com.newsService.dao;

import java.util.ArrayList;
import java.util.List;

import com.newsService.po.Comment;

public interface CommentDao {
	
	public ArrayList<Comment> getComments(int nid,int startCid,int count);

	public void addComment(int nid,String ptime,String region,String content); 

	public long getSpecifyNewsCommentsCount(int nid);
	
	public List<Comment> getAllComments();
	
	public boolean removeInfo(int cid);
}
