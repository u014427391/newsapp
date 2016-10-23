package com.newsService.dao;

import java.util.ArrayList;

import com.newsService.po.Category;

public interface CategoryDao {

	public ArrayList<Category> getTypes(int startTid,int count);
	
	public boolean add(Category category);
	
	public boolean update(Category category);
	
	public void delete(int tid);
	
	public ArrayList<Category> getAllTypes();
	
	public Category getInfoById(String cid);
}
