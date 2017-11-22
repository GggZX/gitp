package com.xzc.activity;

import java.util.ArrayList;
import java.util.List;

import com.xzc.adapter.SearchHistoryAdapter;
import com.xzc.adapter.SearchListAdapter;
import com.xzc.entities.Collections;
import com.xzc.entities.Info_Search;
import com.xzc.utils.CollDataBaseUtils;
import com.xzc.utils.CollMyHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity {
	ListView listView;
	ImageButton button_back, btn_search;
	EditText ed_search;
	CollDataBaseUtils baseUtils;
	Collections coll;
	List<Collections> list;
	SearchHistoryAdapter adapter;
	Button btn_clear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		init();
		list = baseUtils.queryColl("searchHistory");
		btn_search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String menuName = ed_search.getText().toString();
				coll.setMenuName(menuName);
				coll.setName("searchHistory");
				baseUtils.addmenuId(coll);
				Intent in = new Intent(SearchActivity.this,
						ResultActivity.class);
				in.putExtra("menuName", menuName);
				startActivity(in);
			}
		});
		button_back.setOnClickListener(click);
		btn_clear.setOnClickListener(click);
		adapter = new SearchHistoryAdapter(this, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(itemClick);
	}
    OnItemClickListener itemClick=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent in=new Intent(SearchActivity.this,ResultActivity.class);
			in.putExtra("menuName", list.get(arg2).getMenuName());
			startActivity(in);
		}
	};
	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.btn_clearSearch:
				baseUtils.removeCollByName("searchHistory");
				list = baseUtils.queryColl("searchHistory");
				adapter = new SearchHistoryAdapter(SearchActivity.this, list);
				adapter.notifyDataSetChanged();
				listView.setAdapter(adapter);
				break;
			case R.id.search_finish:
				finish();
				break;
			default:
				break;
			}
		}
	};

	public void init() {
		listView = (ListView) findViewById(R.id.search_listview);
		ed_search = (EditText) findViewById(R.id.home_edittext_search2);
		btn_search = (ImageButton) findViewById(R.id.home_imagebutton_login2);
		button_back = (ImageButton) findViewById(R.id.search_finish);
		baseUtils = new CollDataBaseUtils(this);
		coll = new Collections();
		btn_clear = (Button) findViewById(R.id.btn_clearSearch);
	}
}
