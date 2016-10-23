package com.newsClient.custom;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.newsClient.activity.R;

/**
 * 
 */

public class CustomSimpleAdapter extends SimpleAdapter
{

	public CustomSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
	{
		super(context, data, resource, from, to);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = super.getView(position, convertView, parent);
		//更新第一个TextView的背景
		if (position==0)
		{
			TextView categoryTitle = (TextView)v;
			categoryTitle.setBackgroundResource(R.drawable.categorybar_item_background);
			categoryTitle.setTextColor(0XFFFFFFFF);
		}
		return v;
	}
}
