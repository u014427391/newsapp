package com.newsClient.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.newsClient.activity.R;


/**
 * FoundFragment类
 * fragment是3.0以后的东西，为了在低版本中使用fragment就要用到android-support-v4.jar兼容包,
 * 而fragmentActivity就是这个兼容包里面的，它提供了操作fragment的一些方法，其功能跟3.0及以后的版本的Activity的功能一样。
 *
 */
public class FoundFragment extends Fragment implements OnItemClickListener{
	private Activity activity;
	private ListView listView;
	private boolean isLoading = true; 
	private int page = 0;
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_found, container, false);
		return view;
	}

//	public void onActivityCreated(Bundle savedInstanceState) {
//		activity = getActivity();
//		adapter = new PostbarListViewAdapter(activity,null);
//		listView = (ListView)activity.findViewById(R.id.fragment_postbar_listview);
//		listView.addFooterView(View.inflate(activity, R.layout.view_foot, null));
//		listView.setAdapter(adapter);
//		listView.setOnScrollListener(this);
//		listView.setOnItemClickListener(this);
//		//new MyAsyncTaskGetPoatbar().execute(0);
//		super.onActivityCreated(savedInstanceState);
//	}

//	/**
//	 * 异步获取贴吧列表类
//	 * @author linpeng123l
//	 *
//	 */
//	public class MyAsyncTaskGetPoatbar extends AsyncTask<Integer, String, List<Postbar>>{
//		@Override
//		protected List<Postbar> doInBackground(Integer... pages) {
//			List<Postbar> postbars = GetPostbarService.getPostbarsByPage("谷歌",pages[0]);
//			return postbars;
//		}
//		@Override
//		protected void onPostExecute(List<Postbar> newPostbars) {
//		
//			if(newPostbars!=null&&newPostbars.size()>0){
//				page ++ ;
//				adapter.addPostbars(newPostbars);
//				adapter.notifyDataSetChanged();
//			}
//			isLoading = false;
//		}
//	}

//	@Override
//	public void onScrollStateChanged(AbsListView view, int scrollState) {
//	}
//	@Override
//	public void onScroll(AbsListView view, int firstVisibleItem,
//			int visibleItemCount, int totalItemCount) {
//		if(totalItemCount<=firstVisibleItem+visibleItemCount+1&&!isLoading){
//			new MyAsyncTaskGetPoatbar().execute(page);
//			isLoading = true;
//		}
//	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(activity, "你点击了item", Toast.LENGTH_SHORT).show();
	}
}
