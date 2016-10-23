package com.newsClient.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.newsClient.activity.ChannelActivity;
import com.newsClient.activity.NewsDetailsActivity;
import com.newsClient.activity.R;
import com.newsClient.custom.Category;
import com.newsClient.custom.CustomSimpleAdapter;
import com.newsClient.service.SyncHttp;
import com.newsClient.util.DensityUtil;
import com.newsClient.util.StringUtil;

/**
 * NewsFragment
 *  fragment是3.0以后的东西，为了在低版本中使用fragment就要用到android-support-v4.jar兼容包,
 * 而fragmentActivity就是这个兼容包里面的，它提供了操作fragment的一些方法，其功能跟3.0及以后的版本的Activity的功能一样。
 *
 */
public class NewsFragment extends Fragment{

    private View view;
   // private PageIndicator mPageindicator;
   //private ViewPager mViewpager;
   // private PagerAdapter mPageAdapter;
   // private String[] mTitles;
    
    
    private ListView listView;
    
    private boolean isLoading = true;
    
    private Activity activity;
    
    private int page = 0;
    
    private final int COLUMNWIDTHPX = 90;
	private final int FLINGVELOCITYPX = 800; // 滚动距离
	private final int NEWSCOUNT = 5; //返回新闻数目
	private final int SUCCESS = 0;//加载成功
	private final int NONEWS = 1;//该栏目下没有新闻
	private final int NOMORENEWS = 2;//该栏目下没有更多新闻
	private final int LOADERROR = 3;//加载失败
	
	private int mColumnWidthDip;
	private int mFlingVelocityDip;
	private int mCid;
	private String mCatName;
	private ArrayList<HashMap<String, Object>> mNewsData;
	private ListView mNewsList;
	private SimpleAdapter mNewsListAdapter;
	private LayoutInflater mInflater;
	private Button mTitlebarRefresh;
	private ProgressBar mLoadnewsProgress;
	private Button mLoadMoreBtn;
	
	private Button moreCategoryButton;
	
	private LoadNewsAsyncTask loadNewsAsyncTask;
	
	
//	 @ViewById(R.id.mColumnHorizontalScrollView)
//	 protected ColumnHorizontalScrollView mColumnHorizontalScrollView;
//	 @ViewById(R.id.mRadioGroup_content)
//	 protected LinearLayout mRadioGroup_content;
//	 @ViewById(R.id.ll_more_columns)
//	 protected LinearLayout ll_more_columns;
//	 @ViewById(R.id.rl_column)
//	 protected RelativeLayout rl_column;
//	 @ViewById(R.id.button_more_columns)
//	 protected ImageView button_more_columns;
//	 @ViewById(R.id.shade_left)
//	 protected ImageView shade_left;
//	 @ViewById(R.id.shade_right)
//	 protected ImageView shade_right;
	 
//	 @ViewById(R.id.button_more_columns)
//    protected ImageView moreImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
    	view = inflater.inflate(R.layout.fragment_news,container,false);
        this.activity = getActivity();
        initView(view);
        //initData();
        return view;
    }
    
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//    	
//    	new GetNewsAsynTask().execute(page);
//    	
//    	listView = (ListView)activity.findViewById(R.id.fragment_news_listview);
//    	adapter = new NewsListViewAdapter(activity, null);
//    	listView.addFooterView(View.inflate(activity, R.layout.view_foot, null));	
//		listView.setAdapter(adapter);
//		listView.setOnScrollListener(this);
//		listView.setOnItemClickListener(this);
//		
//		super.onActivityCreated(savedInstanceState);
//    }
//    

//    private void initData() {
//        mTitles = view.getResources().getStringArray(R.array.news_arrays);
//        childFragments  = new ArrayList<Fragment>();
//        childFragments.add(NewsChildFragment.newInstance(mTitles[0]));
//        childFragments.add(HotChildFragment.newInstance(mTitles[1]));
//        childFragments.add(BlogChildFragment.newInstance(mTitles[2]));
//        childFragments.add(ReCommandChildFragment.newInstance(mTitles[3]));
//        mPageAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return childFragments.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return childFragments.size();
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return mTitles[position];
//            }
//        };
//        mViewpager.setAdapter(mPageAdapter);
//        mPageindicator.setViewPager(mViewpager);
//
//    }

    private void initView(View view) {
    	
//    	 moreImageView = (ImageView)view.findViewById(R.id.button_more_columns);
//         moreImageView.setOnClickListener(new OnClickListener() {
// 			
// 			@Override
// 			public void onClick(View v) {
// 				Intent intent = new Intent(getActivity(),ChannelActivity.class);
// 				startActivity(intent);
// 			}
// 		});
    	
    	
    	
    	
    	mInflater = getActivity().getLayoutInflater();
		mNewsData = new ArrayList<HashMap<String,Object>>();
		mNewsList = (ListView)view.findViewById(R.id.newslist);
		mTitlebarRefresh = (Button)view.findViewById(R.id.titlebar_refresh);
		mLoadnewsProgress = (ProgressBar)view.findViewById(R.id.loadnews_progress);
		mTitlebarRefresh.setOnClickListener(loadMoreListener);
		
		//把px转换成dip
		mColumnWidthDip = DensityUtil.px2dip(getActivity(), COLUMNWIDTHPX);
		mFlingVelocityDip = DensityUtil.px2dip(getActivity(), FLINGVELOCITYPX);
		
		//获取新闻分类
		String[] categoryArray = getResources().getStringArray(R.array.categories);
		//把新闻分类保存到List中
		final List<HashMap<String, Category>> categories = new ArrayList<HashMap<String, Category>>();
		//分割新闻类型字符串
		for(int i=0;i<categoryArray.length;i++)
		{
			String[] temp = categoryArray[i].split("[|]");
			if (temp.length==2)
			{
				int cid = StringUtil.String2Int(temp[0]);
				String title = temp[1];
				Category type = new Category(cid, title);
				HashMap<String, Category> hashMap = new HashMap<String, Category>();
				hashMap.put("category_title", type);
				categories.add(hashMap);
			}
		}
		//默认选中的新闻分类
		mCid = 1;
		mCatName ="国内";
		//创建Adapter，指明映射字段
		CustomSimpleAdapter categoryAdapter = new CustomSimpleAdapter(getActivity(), categories, R.layout.category_title, new String[]{"category_title"}, new int[]{R.id.category_title});
		
		GridView category = new GridView(getActivity());
		category.setColumnWidth(mColumnWidthDip);//每个单元格宽度
		category.setNumColumns(GridView.AUTO_FIT);//单元格数目
		category.setGravity(Gravity.CENTER);//设置对其方式
		//设置单元格选择是背景色为透明，这样选择时就不现实黄色北京
		//category.setSelector(new ColorDrawable(Color.TRANSPARENT));
		//根据单元格宽度和数目计算总宽度
		int width = mColumnWidthDip * categories.size();
		LayoutParams params = new LayoutParams(width, LayoutParams.FILL_PARENT);
		//更新category宽度和高度，这样category在一行显示
		category.setLayoutParams(params);
		//设置适配器
		category.setAdapter(categoryAdapter);
		//把category加入到容器中
		LinearLayout categoryList = (LinearLayout) view.findViewById(R.id.category_layout);
		categoryList.addView(category);
		//添加单元格点击事件
		category.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				TextView categoryTitle;
				//恢复每个单元格背景色
				for (int i = 0; i < parent.getChildCount(); i++)
				{
					categoryTitle = (TextView) (parent.getChildAt(i));
					categoryTitle.setBackgroundDrawable(null);
					categoryTitle.setTextColor(0XFFADB2AD);
				}
				//设置选择单元格的背景色
				categoryTitle = (TextView) (parent.getChildAt(position));
				categoryTitle.setBackgroundDrawable(null);
				categoryTitle.setTextColor(0XFFADB2AD);
				//获取选中的新闻分类id
				mCid = categories.get(position).get("category_title").getCid();
				mCatName = categories.get(position).get("category_title").getTitle();
				//获取该栏目下新闻
				//getSpeCateNews(mCid,mNewsData,0,true);
				//通知ListView进行更新
				//mNewsListAdapter.notifyDataSetChanged();
				loadNewsAsyncTask = new LoadNewsAsyncTask();
				loadNewsAsyncTask.execute(mCid,0,true);
			}
		});
		
		// 箭头
		final HorizontalScrollView categoryScrollview = (HorizontalScrollView) view.findViewById(R.id.category_scrollview);
//		Button categoryArrowRight = (Button) view.findViewById(R.id.category_arrow_right);
//		categoryArrowRight.setOnClickListener(new OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				categoryScrollview.fling(DensityUtil.px2dip(getActivity(), mFlingVelocityDip));
//			}
//		});
		moreCategoryButton = (Button)view.findViewById(R.id.category_arrow_right);
    	
    	moreCategoryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),ChannelActivity.class);
				startActivity(intent);
			}
		});
		//获取指定栏目的新闻列表
		//getSpeCateNews(mCid,mNewsData,0,true);
		mNewsListAdapter = new SimpleAdapter(getActivity(), mNewsData, R.layout.newslist_item, 
										new String[]{"newslist_item_title","newslist_item_digest","newslist_item_source","newslist_item_ptime"}, 
										new int[]{R.id.newslist_item_title,R.id.newslist_item_digest,R.id.newslist_item_source,R.id.newslist_item_ptime});
		View loadMoreLayout = mInflater.inflate(R.layout.loadmore, null);
		mNewsList.addFooterView(loadMoreLayout);
		mNewsList.setAdapter(mNewsListAdapter);
		mNewsList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
				//把需要的信息放到Intent中
				intent.putExtra("newsDate", mNewsData);
				intent.putExtra("position", position);
				intent.putExtra("categoryName", mCatName);
				startActivity(intent);
			}
		});
		
		mLoadMoreBtn = (Button)view.findViewById(R.id.loadmore_btn);
		mLoadMoreBtn.setOnClickListener(loadMoreListener);
		
		loadNewsAsyncTask = new LoadNewsAsyncTask();
		loadNewsAsyncTask.execute(mCid,0,true);

    }

//	@Override
//	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		if(arg1.getId()!=R.id.foot_view){
//			Intent intent = new Intent(activity, NewsDetailsActivity.class);
//			intent.putExtra("url", adapter.getNewss().get(arg2).getUrl());
//			startActivity(intent);
//		}
//	}
//
//	@Override
//	public void onScroll(AbsListView view, int firstVisibleItem,
//			int visibleItemCount, int totalItemCount) {
//		if(totalItemCount <= firstVisibleItem+visibleItemCount+1&&isLoading==false){
//			isLoading = true;
//			new GetNewsAsynTask().execute(page);
//		}
//	}
//
//	@Override
//	public void onScrollStateChanged(AbsListView view, int scrollState) {
//		
//	}
//	
//	public class GetNewsAsynTask extends AsyncTask<Integer, String, List<New>>{
//
//		@Override
//		protected List<New> doInBackground(Integer... pages) {
//			List<New> news = GetNewsService.getNewsByPage("安卓Service", pages[0]);
//			return news;
//		}
//		
//		@Override
//		protected void onPostExecute(List<New> newss) {
//			super.onPostExecute(newss);
//			if(newss.size()>0){
//				adapter.addNews(newss);
//				adapter.notifyDataSetChanged();
//				page++;
//			}
//			isLoading = false;
//		}
//		
//		
//	}
    
   

	/**
	 * 获取指定类型的新闻列表
	 * @param cid 类型ID
	 * @param newsList 保存新闻信息的集合
	 * @param startnid 分页
	 * @param firstTimes	是否第一次加载
	 */
	private int getSpeCateNews(int cid,List<HashMap<String, Object>> newsList,int startnid,Boolean firstTimes)
	{
		if (firstTimes)
		{
			//如果是第一次，则清空集合里数据
			newsList.clear();
		}
		//请求URL和字符串
		String url = "http://172.16.107.7:8080/newsService/getSpecifyCategoryNews";
		String params = "startnid="+startnid+"&count="+NEWSCOUNT+"&cid="+cid;
		SyncHttp syncHttp = new SyncHttp();
		try
		{
			//以Get方式请求，并获得返回结果
			String retStr = syncHttp.httpGet(url, params);
			JSONObject jsonObject = new JSONObject(retStr);
			//获取返回码，0表示成功
			int retCode = jsonObject.getInt("ret");
			if (0==retCode)
			{
				JSONObject dataObject = jsonObject.getJSONObject("data");
				//获取返回数目
				int totalnum = dataObject.getInt("totalnum");
				if (totalnum>0)
				{
					//获取返回新闻集合
					JSONArray newslist = dataObject.getJSONArray("newslist");
					for(int i=0;i<newslist.length();i++)
					{
						JSONObject newsObject = (JSONObject)newslist.opt(i); 
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("nid", newsObject.getInt("nid"));
						hashMap.put("newslist_item_title", newsObject.getString("title"));
						hashMap.put("newslist_item_digest", newsObject.getString("digest"));
						hashMap.put("newslist_item_source", newsObject.getString("source"));
						hashMap.put("newslist_item_ptime", newsObject.getString("ptime"));
						hashMap.put("newslist_item_comments", newsObject.getString("commentcount"));
						newsList.add(hashMap);
					}
					return SUCCESS;
				}
				else
				{
					if (firstTimes)
					{
						return NONEWS;
					}
					else
					{
						return NOMORENEWS;
					}
				}
			}
			else
			{
				return LOADERROR;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return LOADERROR;
		}
	}
	
	private OnClickListener loadMoreListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			loadNewsAsyncTask = new LoadNewsAsyncTask();
			switch (v.getId())
			{
			case R.id.loadmore_btn:
				//获取该栏目下新闻
				//getSpeCateNews(mCid,mNewsData,mNewsData.size(),false);
				//通知ListView进行更新
				//mNewsListAdapter.notifyDataSetChanged();
				loadNewsAsyncTask.execute(mCid,mNewsData.size(),false);
				break;
			case R.id.titlebar_refresh:
				loadNewsAsyncTask.execute(mCid,0,true);
				break;
			}
			
		}
	};
	
	private class LoadNewsAsyncTask extends AsyncTask<Object, Integer, Integer>
	{
		
		@Override
		protected void onPreExecute()
		{
			//隐藏刷新按钮
			mTitlebarRefresh.setVisibility(View.GONE);
			//显示进度条
			mLoadnewsProgress.setVisibility(View.VISIBLE); 
			//设置LoadMore Button 显示文本
			mLoadMoreBtn.setText(R.string.loadmore_txt);
		}

		@Override
		protected Integer doInBackground(Object... params)
		{
			return getSpeCateNews((Integer)params[0],mNewsData,(Integer)params[1],(Boolean)params[2]);
		}

		@Override
		protected void onPostExecute(Integer result)
		{
			//根据返回值显示相关的Toast
			switch (result)
			{
			case NONEWS:
				Toast.makeText(getActivity(), R.string.no_news, Toast.LENGTH_LONG).show();
			break;
			case NOMORENEWS:
				Toast.makeText(getActivity(), R.string.no_more_news, Toast.LENGTH_LONG).show();
				break;
			case LOADERROR:
				Toast.makeText(getActivity(), R.string.load_news_failure, Toast.LENGTH_LONG).show();
				break;
			}
			mNewsListAdapter.notifyDataSetChanged();
			//显示刷新按钮
			mTitlebarRefresh.setVisibility(View.VISIBLE);
			//隐藏进度条
			mLoadnewsProgress.setVisibility(View.GONE); 
			//设置LoadMore Button 显示文本
			mLoadMoreBtn.setText(R.string.loadmore_btn);
		}
	}

}
