package com.xzc.activity;

import java.util.List;

import com.xzc.adapter.CategoryAdapter;
import com.xzc.adapter.ResultListAdapter;
import com.xzc.entities.AllInfo;
import com.xzc.entities.MenuData;
import com.xzc.utils.ACacheUtils;
import com.xzc.utils.AppKey;
import com.xzc.utils.MyHttpUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends Activity {
	ListView listView;
	List<MenuData> list;
	Intent in;
	String menuId;
	ImageButton btn_back;
	CategoryAdapter adapter;
	String result;
	MyHttpUtils utils;
	ACacheUtils acache;
	MenuData data;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			list = (List<MenuData>) msg.obj;
			adapter = new CategoryAdapter(CategoryActivity.this, list);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(itemClick);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_layout);
		btn_back = (ImageButton) findViewById(R.id.cate_img_back);
		acache = ACacheUtils.get(this, "index");
		in = getIntent();
		menuId = in.getStringExtra("menuId");
		Log.i("menuID", "============" + menuId + "");
		final String URL = "http://apis.juhe.cn/cook/index?key=" + AppKey.KEY
				+ "&cid=" + menuId + "";
		utils = new MyHttpUtils();
		listView = (ListView) findViewById(R.id.result_listview);
		btn_back.setOnClickListener(click);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if ((acache.getAsString("result" + menuId)) == null) {
					result = utils.getRes(URL);
					acache.put("result" + menuId, result, ACacheUtils.TIME_HOUR);
				} else {
					result = acache.getAsString("result" + menuId);
				}
				AllInfo info = utils.getInfo(result);
				list = utils.getMenuData(result);
				Message msg = new Message();
				msg.obj = list;
				handler.sendMessage(msg);
			}
		}).start();
	}

	OnItemClickListener itemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			data = list.get(arg2);
			in = new Intent(CategoryActivity.this, StepsActivity.class);
			in.putExtra("stepsId", data.getId());
			startActivity(in);
		}
	};
	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	};
}
