package com.xzc.activity;

import java.util.List;

import com.xzc.adapter.CollectionsAdapter;
import com.xzc.entities.Collections;
import com.xzc.utils.CollDataBaseUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ReadHistoryActivity extends Activity {
	ListView view;
	ImageButton btn_back;
	CollDataBaseUtils collUtils;
	List<Collections> list;
	CollectionsAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readhistory_layout);
		init();
		collUtils = new CollDataBaseUtils(this);
		list = collUtils.queryColl("history");
		adapter = new CollectionsAdapter(this, list);
		view.setAdapter(adapter);
		view.setOnItemClickListener(itemClick);
		btn_back.setOnClickListener(click);
	}

	OnItemClickListener itemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Collections coll = list.get(arg2);
			Intent in = new Intent(ReadHistoryActivity.this,
					StepsActivity.class);
			in.putExtra("stepsId", coll.getMenuId());
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

	private void init() {
		// TODO Auto-generated method stub
		view = (ListView) findViewById(R.id.read_listview);
		btn_back = (ImageButton) findViewById(R.id.read_btn_back);
	}
}
