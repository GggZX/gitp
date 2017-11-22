package com.xzc.activity;

import com.xzc.adapter.QuanquanListAdapter.Hander;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashActivity extends Activity {
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent in = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(in);
			    SplashActivity.this.finish();
			}
		}, 3000);
	}
}
