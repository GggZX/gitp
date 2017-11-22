package com.xzc.activity;

import java.util.List;

import com.xzc.adapter.ResultListAdapter;
import com.xzc.entities.AllInfo;
import com.xzc.entities.Info_Result;
import com.xzc.entities.MenuData;
import com.xzc.utils.ACacheUtils;
import com.xzc.utils.AppKey;
import com.xzc.utils.MyHttpUtils;
import com.xzc.utils.XListView;
import com.xzc.utils.XListView.IXListViewListener;

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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ResultActivity extends Activity {
	XListView mListView;
	List<MenuData> list;
	Intent in;
	ImageButton btn_back;
	private Handler mHandler;
	ResultListAdapter adapter;
	MyHttpUtils utils;
	ACacheUtils acache;
	String result;
	MenuData data;
	private int start = 0;
	int num = 5;
	String URL;
	String menuName;
	private static int refreshCnt = 0;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.obj.equals("抱歉，该菜没有收录")) {
				String m = (String) msg.obj;
				Toast.makeText(ResultActivity.this, "抱歉，该菜没有收录", 0).show();
			} else {
				list = (List<MenuData>) msg.obj;
				adapter = new ResultListAdapter(ResultActivity.this, list);
				mListView.stopRefresh();
				mListView.setAdapter(adapter);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results_layout);
		mListView = (XListView) findViewById(R.id.result_listview1);
		btn_back = (ImageButton) findViewById(R.id.cate_img_back);
		btn_back.setOnClickListener(click);
		mListView.setXListViewListener(listener);
		mListView.setPullLoadEnable(true);
		acache = ACacheUtils.get(this, "query");
		mHandler = new Handler();
		in = getIntent();
		menuName = in.getStringExtra("menuName");
		Log.i("res", menuName + "");
		num = 5;
		geneItems();
		utils = new MyHttpUtils();
	}

	private void geneItems() {
		URL = "http://apis.juhe.cn/cook/query?key=" + AppKey.KEY + "&menu="
				+ menuName + "&rn=" + num + "&pn=3";
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if ((acache.getAsString("results" + menuName + num)) == null) {
					result = utils.getRes(URL);
					acache.put("results" + menuName + num, result,
							acache.TIME_DAY);
				} else {
					result = acache.getAsString("results" + menuName + num);
				}
				AllInfo info = utils.getInfo(result);
				if (!(info.getResultCode().equals("200"))) {
					Message msg = new Message();
					msg.obj = "抱歉，该菜没有收录";
					handler.sendMessage(msg);
				} else {
					list = utils.getMenuData(result);
					Message msg = new Message();
					msg.obj = list;
					handler.sendMessage(msg);
				}
			}
		}).start();

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (arg2 == 0) {
				} else {
					data = list.get(arg2 - 1);
					in = new Intent(ResultActivity.this, StepsActivity.class);
					in.putExtra("stepsId", data.getId());
					startActivity(in);
				}
			}
		});

		/*
		 * mListView.setOnItemClickListener(click); OnItemClickListener click =
		 * new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> arg0, View arg1, int
		 * arg2, long arg3) { // TODO Auto-generated method stub data =
		 * list.get(arg2); in = new Intent(ResultActivity.this,
		 * StepsActivity.class); in.putExtra("stepsId", data.getId());
		 * startActivity(in); } };
		 */
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	};

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	IXListViewListener listener = new IXListViewListener() {

		@Override
		public void onRefresh() {
			num = num + 2;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					start = ++refreshCnt;
					geneItems();
					onLoad();
				}
			}, 2000);
		}

		@Override
		public void onLoadMore() {
			num = num + 2;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					geneItems();
					adapter.notifyDataSetChanged();
					onLoad();
				}
			}, 2000);
		}
	};

}
