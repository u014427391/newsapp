package com.newsService.dao;

import java.util.ArrayList;
import java.util.List;

import com.newsService.po.News;

public interface NewsDao {
	public News getNews(int nid) ;
	public ArrayList<News> getSpecifyCategoryNews(int tid, int startNid, int count);
	public List<News> getAllNews();
	public boolean addNews(News news);
	public News getNewsById(int nid);
	public boolean deleteNews(int nid);
	public boolean updateNews(News news);
}
