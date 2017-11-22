package com.xzc.activity;

import com.xzc.entities.UserInfo;
import com.xzc.utils.DBOprerator;
import com.xzc.utils.UserHeper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterZhuceActivity extends Activity{
	ImageButton fanhui,zhuce;
	EditText username,password,querenpassword;
	SQLiteDatabase db;
	UserHeper heper;
	UserInfo info;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerzhuce_layout);
		fanhui=(ImageButton)findViewById(R.id.registerzhuce_button_ruturnbtn);
		zhuce=(ImageButton)findViewById(R.id.registerzhuce_button_nextworkbtn);
		username=(EditText)findViewById(R.id.registerzhuce_edittext_username);
		password=(EditText)findViewById(R.id.registerzhuce_edittext_password);
		querenpassword=(EditText)findViewById(R.id.registerzhuce_edittext_querenpassword);
		heper=new UserHeper(this);
		db=heper.getWritableDatabase();
		fanhui.setOnClickListener(onClickListener);
		zhuce.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener=new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.registerzhuce_button_ruturnbtn:
				finish();
				break;
			case R.id.registerzhuce_button_nextworkbtn:
				Cursor cursor=db.query("tb_user", null, "_username=?", new String[]{username.getText().toString()}, null, null,null);
				int a=cursor.getCount();
				if(username.getText().toString().equals("")){
					Toast.makeText(RegisterZhuceActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
				}else if(password.getText().toString().equals("")||querenpassword.getText().toString().equals("")){
					Toast.makeText(RegisterZhuceActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
				}else if(!password.getText().toString().equals(querenpassword.getText().toString())){
					Toast.makeText(RegisterZhuceActivity.this, "两次密码不正确", Toast.LENGTH_SHORT).show();
				}else if(a>0){
					Toast.makeText(RegisterZhuceActivity.this, "账号已存在", 0).show();
				}else{
					info=new UserInfo();
					info.setUsername(username.getText().toString());
					info.setPassword(password.getText().toString());
					DBOprerator.adde(db, info);
					Toast.makeText(RegisterZhuceActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(RegisterZhuceActivity.this,RegisterActivity.class);
					startActivity(intent);
					finish();
				}		
				break;
			}
		}
	};
}
