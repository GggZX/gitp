package com.xzc.activity;

import com.xzc.utils.UserHeper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	ImageButton qq,weibo,register,login,zhucetextview;
	EditText username,password;
	TextView zhaohui;
	SQLiteDatabase db;
	UserHeper heper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		register=(ImageButton)findViewById(R.id.register_button_ruturnbtn);
		login=(ImageButton)findViewById(R.id.register_button_logoinbtn);
		qq=(ImageButton)findViewById(R.id.register_imagbtn_QQ);
		weibo=(ImageButton)findViewById(R.id.register_imagbtn_weibo);
		username=(EditText)findViewById(R.id.register_edittext_username);
		password=(EditText)findViewById(R.id.register_edittext_password);
		zhucetextview=(ImageButton)findViewById(R.id.register_textview_registertv);
		zhaohui=(TextView)findViewById(R.id.register_edittext_zhaohuipassword);
		heper=new UserHeper(this);
		db=heper.getReadableDatabase();
		login.setOnClickListener(onClickListener);
		zhucetextview.setOnClickListener(onClickListener);
		qq.setOnClickListener(onClickListener);
		weibo.setOnClickListener(onClickListener);
		register.setOnClickListener(onClickListener);
		zhaohui.setOnClickListener(onClickListener);
	}
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View position) {
			switch (position.getId()) {
			case R.id.register_button_ruturnbtn:
				finish();
				break;
			case R.id.register_button_logoinbtn:
				String n=username.getText().toString();
				String p=password.getText().toString();
				Cursor cursor=db.query("tb_user", null, "_username=? and _passworld=?", new String[]{n,p}, null, null,null);
				int a=cursor.getCount();
				if(n.equals("")){
					Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
				}else if(p.equals("")){
					Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
				}else if(a>0){
					Toast.makeText(RegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
					startActivity(intent);
				}else if(a<=0){
					Toast.makeText(RegisterActivity.this, "账号密码不正确", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.register_textview_registertv:
				Intent intent=new Intent(RegisterActivity.this,RegisterZhuceActivity.class);
				startActivity(intent);
				break;
			case R.id.register_imagbtn_QQ:
				
				break;
			case R.id.register_imagbtn_weibo:
				
				break;
			case R.id.register_edittext_zhaohuipassword:
				Toast.makeText(RegisterActivity.this, "暂不提供此功能", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
}
