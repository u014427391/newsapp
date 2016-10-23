package com.newsClient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsClient.activity.R;

/**
 * 
 * MessageFragment
 *  fragment是3.0以后的东西，为了在低版本中使用fragment就要用到android-support-v4.jar兼容包,
 * 而fragmentActivity就是这个兼容包里面的，它提供了操作fragment的一些方法，其功能跟3.0及以后的版本的Activity的功能一样。
 *
 *
 */
public class MessageFragment extends Fragment {

	 private View view;

	    //@Nullable
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        view = inflater.inflate(R.layout.fragment_message,container,false);
	        return view;
	    }
}
