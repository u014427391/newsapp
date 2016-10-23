package com.newsClient.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newsClient.activity.LoginActivity;
import com.newsClient.activity.R;
import com.newsClient.listener.IBtnCallListener;

/**
 * MyFragment
 *  fragment是3.0以后的东西，为了在低版本中使用fragment就要用到android-support-v4.jar兼容包,
 * 而fragmentActivity就是这个兼容包里面的，它提供了操作fragment的一些方法，其功能跟3.0及以后的版本的Activity的功能一样。
 *
 */
public class MyFragment extends Fragment {
    private View view;

    private LinearLayout layout;
    
//  private TextView textView;
    
//    private Bundle bundle;
//	/*IBtnCallListener的对象*/
//	IBtnCallListener mbtnListener;  
//	
//	//账号
//	private String account="用户请先登录！";
    
    //@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my,container,false);
        initView(view);
        return view;
    }
    
    private void initView(View view) {
    	
//    	textView = (TextView)view.findViewById(R.id.textView1);
//    	textView.setText(account);
    	
    	
    	layout = (LinearLayout)view.findViewById(R.id.linearLayout);
    	layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
			}
		});
    }
    
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//    	super.onActivityCreated(savedInstanceState);
//    }
//    
//    @Override
//    public void onAttach(Activity activity) {
//    	try {  
//    		mbtnListener=(IBtnCallListener) activity;  
//    	} catch (Exception e) {  
//    		// TODO: handle exception   
//    		throw new ClassCastException(activity.toString() + "must implement mbtnListener");  
//    	} 
//    	super.onAttach(activity);
//    }
//    
//
//	@Override
//	public void myFragmentTransfermsg() {
//		bundle = getArguments();
//		account = bundle.getString("account");
//		System.out.println("由Activity传输过来的信息");  
//	}
    
}
