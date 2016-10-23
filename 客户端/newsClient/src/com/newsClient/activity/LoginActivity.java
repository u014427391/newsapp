package com.newsClient.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.newsClient.encoder.AESEncryptor;
import com.newsClient.model.Parameter;
import com.newsClient.service.SyncHttp;

public class LoginActivity extends Activity implements OnClickListener{

	private Button registerButton;
	
	private Button loginButton;
	
	private Button returnButton;
	
	//账号文本框
	private EditText accountEditText;
	//密码文本框
	private EditText passwordEditText;
	
	//账号
	private String account;
	//密码
	private String password;
	
	private String accountValue;
	private String passwordValue;
	
	//进度条
	private ProgressDialog pd = null;
	
	//记住账号的CheckBox
	private CheckBox savedAccountCheckBox;
	//自动登录的CheckBox
	private CheckBox autoLoginCheckBox;
	
	//SharePreferences对象，用于记住账号
	private SharedPreferences sp;
	
	//记住账号的标志常数
	private final String MAK = "innoview";
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//账号的文本框
		accountEditText = (EditText)this.findViewById(R.id.et_account);
		//密码的文本框
		passwordEditText = (EditText)this.findViewById(R.id.et_password);
		//保存账号的CheckBox
		savedAccountCheckBox = (CheckBox)findViewById(R.id.cb_savedAccount);
		//自动登录的CheckBox
		autoLoginCheckBox = (CheckBox)findViewById(R.id.cb_autoLogin);
		//获取保存在SharePreferences里面的账号信息，实现自动登录
		sp = getSharedPreferences("accountInfo",Context.MODE_WORLD_READABLE);
		
		if(sp.getBoolean("ISCHECK", false)){
			
			savedAccountCheckBox.setChecked(true);
			
			try{
				 accountValue = sp.getString("ACCOUNTVALUE","");
				 System.out.println("<<<<<<<<<<<<"+"加密后的账号"+accountValue);
                 account= AESEncryptor.decrypt(MAK, accountValue);
                 System.out.println("<<<<<<<<<<<<"+"解密后的账号"+account);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			accountEditText.setText(account);
			
			try{
				 passwordValue = sp.getString("PASSWORDVALUE","");
				 System.out.println("<<<<<<<<<<<<"+"加密后的密码"+passwordValue);
                password= AESEncryptor.decrypt(MAK, passwordValue);
                System.out.println("<<<<<<<<<<<<"+"解密后的密码"+password);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			passwordEditText.setText(password);
			
			if(sp.getBoolean("AUTO_ISCHECK", false)){
				 autoLoginCheckBox.setChecked(true);
				 initLogin();
		    }
		}
		
		savedAccountCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
			if (savedAccountCheckBox.isChecked()) {
			                     
				System.out.println("记住账号框未选中状态");
				sp.edit().putBoolean("ISCHECK", true).commit();

			}else {

				System.out.println("记住账号框未选中");
				sp.edit().putBoolean("ISCHECK", false).commit();

			}

			}
		});
		
		autoLoginCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
	        	if (autoLoginCheckBox.isChecked()) {
	        		System.out.println("自动登录功能以启用");
	        		sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

	        	} else {
	        		System.out.println("自动登录已关闭");
	        		sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
	        	}
	        }
		});
		
		initView();
	}
	
	/**
	 * 初始化View
	 */
	public void initView(){
		registerButton = (Button)findViewById(R.id.bt_register);
		registerButton.setOnClickListener(this);
		
		loginButton = (Button)findViewById(R.id.bt_login);
		loginButton.setOnClickListener(this);
		
		returnButton = (Button)findViewById(R.id.btn_black);
		returnButton.setOnClickListener(this);
	}

	/**
	 * 登录验证
	 */
	public void initLogin(){
		//显示进度条
		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage("数据加载...");
		pd.show();
		
		account = accountEditText.getText().toString();
		password = passwordEditText.getText().toString();
		
		if(savedAccountCheckBox.isChecked())
		{
		 
		 Editor editor = sp.edit();

		    try {
	            editor.putString("ACCOUNTVALUE", AESEncryptor.encrypt(MAK,account));
		        System.out.println("<<<<<<<<"+"加密后的账号"+AESEncryptor.encrypt(MAK,account));
		    } catch (Exception e) {
		        Toast.makeText(LoginActivity.this,"账号加密异常",Toast.LENGTH_SHORT).show();
		        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		    }
		    
		    try {
	            editor.putString("PASSWORDVALUE", AESEncryptor.encrypt(MAK,password));
		        System.out.println("<<<<<<<<"+"加密后的密码"+AESEncryptor.encrypt(MAK,password));
		    } catch (Exception e) {
		        Toast.makeText(LoginActivity.this,"密码加密异常",Toast.LENGTH_SHORT).show();
		        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		    }
		    
		   editor.commit();
		}
		loginButton.post(new LoginThread());
	}
	
	
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login:
			initLogin();
			break;
	
		case R.id.bt_register:
			Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent); 
			break;
		
		case R.id.btn_black:
			LoginActivity.this.finish();
			break;
			
		default:
			break;
		}
	}
	/**
	 * 实现登录验证的线程
	 */
	public class LoginThread extends Thread{
		
		@Override
		public void run() {
			super.run();
			//同步加载的类
			SyncHttp  syncHttp = new SyncHttp();
			//接收登录验证的请求链接
			String url = "http://172.16.107.7:8080/newsService/LoginServlet";
			//数组列表
			List<Parameter> params = new ArrayList<Parameter>();
			params.add(new Parameter("account",account));
			params.add(new Parameter("password",password));
			try{
				//获取返回的数据
				String retStr = syncHttp.httpPost(url, params);
				//JSONObject类
				JSONObject jsonObject = new JSONObject(retStr);
				int retCode = jsonObject.getInt("ret");
				//请求成功
				if(0 == retCode){
					Toast.makeText(getApplicationContext(), "登录成功!", 1000).show();
					Intent intent = new Intent(LoginActivity.this,MainActivity.class);
					//intent.putExtra("account", account);
					startActivity(intent);
					LoginActivity.this.finish();
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			Toast.makeText(getApplicationContext(), "账号密码不正确!", 1000).show();
			pd.dismiss();
		}
	}

}
