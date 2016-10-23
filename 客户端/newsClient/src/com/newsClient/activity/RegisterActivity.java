package com.newsClient.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.newsClient.model.Parameter;
import com.newsClient.service.SyncHttp;

/**
 * 注册的Activity类
 *
 */
public class RegisterActivity extends Activity implements OnClickListener{

	//注册按钮
	private Button registerButton;
	
	//返回按钮
	private Button returnButton;
	
	//标题栏的返回按钮
	private Button tReturnButton;
	
	//账号文本框
	private EditText accountEditText;
	
	//密码文本框
	private EditText passwordEditText;
	
	//账号
	private String account;
	
	//密码
	private String password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
	}

	public void initView(){
		registerButton = (Button)findViewById(R.id.bt_register);
		returnButton = (Button)findViewById(R.id.bt_return);
		tReturnButton = (Button)findViewById(R.id.btn_black);
		
		//添加事件监听器
		registerButton.setOnClickListener(this);
		returnButton.setOnClickListener(this);
		tReturnButton.setOnClickListener(this);
		
		//实例化文本框
		accountEditText = (EditText)findViewById(R.id.et_account);
		passwordEditText = (EditText)findViewById(R.id.et_password);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//注册
		case R.id.bt_register:
			//点击按钮实现注册，发送请求
			registerButton.post(new RegisterThread());
			break;
	    //返回按钮
		case R.id.bt_return:
			RegisterActivity.this.finish();
			break;
		//标题栏的返回按钮
		case R.id.btn_black:
			RegisterActivity.this.finish();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 实现注册功能的线程
	 *
	 */
	public class RegisterThread extends Thread{
		
		@Override
		public void run() {
			super.run();
			//获取账号、密码框的值
			account = accountEditText.getText().toString();
			password = passwordEditText.getText().toString();
			
			//同步加载
			SyncHttp syncHttp = new SyncHttp();
			//请求的Servlet链接
			String url = "http://172.16.107.7:8080/newsService/RegisterServlet";
			//数组列表
			List<Parameter> params = new ArrayList<Parameter>();
			params.add(new Parameter("account",account));
			params.add(new Parameter("password",password));
			params.add(new Parameter("credits","0"));
			params.add(new Parameter("rank","LV1"));
			params.add(new Parameter("accIsValid","1"));
			
			try {
				//获取服务器返回的数据
				String retStr = syncHttp.httpPost(url, params);
				JSONObject jsonObject = new JSONObject(retStr);
				//获取返回码
				int retCode = jsonObject.getInt("ret");
				//返回码为0说明请求成功
				if(retCode == 0){
					//请求成功，页面跳转
					Toast.makeText(getApplicationContext(), "注册成功!", 1000).show();
					Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
					startActivity(intent);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Toast.makeText(getApplicationContext(), "注册失败！", 1000).show();
			
		}
	}
	
}
