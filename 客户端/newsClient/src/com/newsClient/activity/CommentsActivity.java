package com.newsClient.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.newsClient.service.SyncHttp;

/**
 * 处理评论的Activity类
 */
public class CommentsActivity extends Activity
{
	//数组列表，用于存放，返回的评论信息
	private List<HashMap<String, Object>> mCommsData;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comments);
		//获取新闻编号
		Intent intent = getIntent();
		int nid = intent.getIntExtra("nid", 0);
		mCommsData = new ArrayList<HashMap<String, Object>>();
		//获取新闻回复内容
		getComments(nid);
		//显示新闻回复信息
		SimpleAdapter commentsAdapter = new SimpleAdapter(this, mCommsData, R.layout.comments_list_item, new String[]
		{ "commentator_from", "comment_ptime", "comment_content" }, new int[]
		{ R.id.commentator_from, R.id.comment_ptime, R.id.comment_content });
		ListView commentsList = (ListView) findViewById(R.id.comments_list);
		commentsList.setAdapter(commentsAdapter);
		
		//
		Button commsTitlebarNews =(Button)findViewById(R.id.comments_titlebar_news);
		commsTitlebarNews.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	/**
	 * 获取新闻回复内容
	 * @param nid 新闻编号
	 */
	private void getComments(int nid)
	{
		String url = "http://172.16.107.7:8080/newsService/getComments";
		String params = "nid=" + nid + "&startnid=0&count=10";
		SyncHttp http = new SyncHttp();
		try
		{
			String retStr = http.httpGet(url, params);
			JSONObject jsonObject = new JSONObject(retStr);
			int retCode = jsonObject.getInt("ret");
			if (retCode == 0)
			{
				JSONObject dataObject = jsonObject.getJSONObject("data");
				// 获取返回数目
				int totalnum = dataObject.getInt("totalnum");
				if (totalnum > 0)
				{
					// 获取返回新闻集合
					JSONArray commsList = dataObject.getJSONArray("commentslist");
					for (int i = 0; i < commsList.length(); i++)
					{
						JSONObject commsObject = (JSONObject) commsList.opt(i);
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("cid", commsObject.getInt("cid"));
						hashMap.put("commentator_from", commsObject.getString("region"));
						hashMap.put("comment_content", commsObject.getString("content"));
						hashMap.put("comment_ptime", commsObject.getString("ptime"));
						mCommsData.add(hashMap);
					}
				}
				else
				{
					Toast.makeText(CommentsActivity.this, R.string.no_comments, Toast.LENGTH_LONG).show();
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			Toast.makeText(CommentsActivity.this, R.string.get_comms_failure, Toast.LENGTH_LONG).show();
		}
	}
}
