package com.newsClient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

import com.newsClient.fragment.FoundFragment;
import com.newsClient.fragment.MessageFragment;
import com.newsClient.fragment.MyFragment;
import com.newsClient.fragment.NewsFragment;
import com.newsClient.listener.IBtnCallListener;

/**
 * 主界面类
 * fragment是3.0以后的东西，为了在低版本中使用fragment就要用到android-support-v4.jar兼容包,
 * 而fragmentActivity就是这个兼容包里面的，它提供了操作fragment的一些方法，其功能跟3.0及以后的版本的Activity的功能一样。
 *
 */
//@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity{

	//@ViewById(R.id.action_settings)
    private RadioButton mNews;
    private RadioButton mMessage;
    private RadioButton mFound;
    private RadioButton mMy;

    //Fragment类
    private NewsFragment mNewsFragment;
    private MessageFragment mMessageFragment;
    private FoundFragment mFoundFrament;
    private MyFragment mMyFragment;

   // private Bundle sendBundle;
    
   // private IBtnCallListener mBtnCallListener; 
    
    FragmentTransaction ft;
    
   // private String account;
    
   // private Bundle bundle;
    
    //@ViewById(R.id.button_more_columns)
   // protected ImageView moreImageView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initView();
        //设置默认选择的那个Fragment
        setSelection(3);

    }
 
    private void initView() {
        mNews = (RadioButton) findViewById(R.id.rb_news);
        mMessage = (RadioButton)findViewById(R.id.rb_message);
        mFound = (RadioButton) findViewById(R.id.rb_found);
        mMy = (RadioButton) findViewById(R.id.rb_me);
        
    }
    

    private void setSelection(int index){
        resetImg();
        ft = getSupportFragmentManager().beginTransaction();
        hideFragments(ft);
        switch (index){
            case 0:
            	//点击实现变色和图片变换
                mNews.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_news_pressed_icon, 0, 0);
                mNews.setTextColor(mNews.getResources().getColor(R.color.topbar_bg));
                if(mNewsFragment== null){
                    mNewsFragment = new NewsFragment();
                    ft.add(R.id.fg_content,mNewsFragment);
                }else{
                    ft.show(mNewsFragment);
                }
                break;
            case 1:
            	 mMessage.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_message_pressed_icon, 0, 0);
            	 mMessage.setTextColor(mMessage.getResources().getColor(R.color.topbar_bg));
            	 if(mMessageFragment== null){
                     mMessageFragment = new MessageFragment();
                     ft.add(R.id.fg_content,mMessageFragment);
                 }else{
                     ft.show(mMessageFragment);
                 }
            	break;
            case 2:
                mFound.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_found_pressed_icon, 0, 0);
                mFound.setTextColor(mFound.getResources().getColor(R.color.topbar_bg));
                if(mFoundFrament == null){
                    mFoundFrament  = new FoundFragment();
                    ft.add(R.id.fg_content,mFoundFrament);
                }
                ft.show(mFoundFrament);
                break;
            case 3:
                mMy.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_my_pressed_icon, 0, 0);
                mMy.setTextColor(mFound.getResources().getColor(R.color.topbar_bg));
                if(mMyFragment == null ){
                    mMyFragment = new MyFragment();
                    ft.add(R.id.fg_content,mMyFragment);
                }else{
                    ft.show(mMyFragment);
                }
                break;
        }
        ft.commit();
    }
    
    /**
     *  恢复默认图片
     */
    private void resetImg() {
        mNews.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.tab_news_icon, 0, 0);
        mNews.setTextColor(mNews.getResources().getColor(R.color.tab_text_bg));
        mMessage.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.tab_message_icon, 0, 0);
        mMessage.setTextColor(mMessage.getResources().getColor(R.color.tab_text_bg));
        mFound.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.tab_found_icon, 0, 0);
        mFound.setTextColor(mFound.getResources().getColor(R.color.tab_text_bg));
        mMy.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.tab_my_icon, 0, 0);
        mMy.setTextColor(mMy.getResources().getColor(R.color.tab_text_bg));
    }

    private void hideFragments(FragmentTransaction ft){
        if(mNewsFragment != null){
            ft.hide(mNewsFragment);
        }
        if(mMessageFragment != null){
            ft.hide(mMessageFragment);
        }
        if(mFoundFrament != null){
            ft.hide(mFoundFrament);
        }
        if(mMyFragment != null){
            ft.hide(mMyFragment);
        }
       
    }

    //点击新闻Fragment
    public void onNewsClicked(View view){
        setSelection(0);
    }
    
    //点击消息Fragment
    public void onMessageClicked(View view){
    	setSelection(1);
    }
    //点击发现Fragment
    public void onFoundClicked(View view){
        setSelection(2);
    }
    //点击MyFragment
    public void onMyClicked(View view){
        setSelection(3);
    }


//    /**
//     * 重载的onAttachFragment方法
//     */
//    @Override
//    public void onAttachFragment(Fragment fragment) {
//    	try {  
//        	 mBtnCallListener=(IBtnCallListener) fragment; 
//        } catch (Exception e) {  
//
//        } 
//    	super.onAttachFragment(fragment);
//    }
//    
//    
//    /**
//     * 实现IBtnCallListener接口的抽象方法，实现Activity和Fragment之间的通讯
//     */
//	@Override
//	public void myFragmentTransfermsg() {
//		mMy.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_found_pressed_icon, 0, 0);
//        mMy.setTextColor(mFound.getResources().getColor(R.color.topbar_bg));
//        if(mMyFragment == null){
//            mMyFragment  = new MyFragment();
//            //通过Bundle向Fragment类传送参数
//			//设置Arguments
//            sendBundle = new Bundle();
//			sendBundle.putString("account", account);
//            mMyFragment.setArguments(sendBundle);
//            
//            ft.add(R.id.fg_content,mMyFragment);
//        }
//        ft.show(mMyFragment);
//	}
}
