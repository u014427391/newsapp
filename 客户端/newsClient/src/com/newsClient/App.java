package com.newsClient;

import android.app.Application;

import com.newsClient.db.SQLHelper;
/**
 * Application类 
 */
public class App extends Application{
	  
	private static App mAppApplication;
	private SQLHelper sqlHelper;

	@Override
	public void onCreate() {
	   super.onCreate();
	    mAppApplication = this;
	        //initImageLoader(getApplicationContext());
	}

	/** 获取Application */
	public static App getApp() {
	    return mAppApplication;
	}

	 /** 获取数据库Helper */
    public SQLHelper getSQLHelper() {
	    if (sqlHelper == null)
	        sqlHelper = new SQLHelper(mAppApplication);
	        return sqlHelper;
	}

    @Override
    public void onTerminate() {
	if (sqlHelper != null)
	    sqlHelper.close();
	    super.onTerminate();
	    // 整体摧毁的时候调用这个方法
	}

}
