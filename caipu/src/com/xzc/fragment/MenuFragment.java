package com.xzc.fragment;

import java.util.List;

import com.xzc.activity.CollectionsActivity;
import com.xzc.activity.R;
import com.xzc.activity.SearchActivity;
import com.xzc.activity.StepsActivity;
import com.xzc.adapter.ExpandableAdapter;
import com.xzc.entities.CateList;
import com.xzc.utils.ACacheUtils;
import com.xzc.utils.AppKey;
import com.xzc.utils.CollMyHelper;
import com.xzc.utils.MyHttpUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuFragment extends Fragment {
	static String URL = "http://apis.juhe.cn/cook/category?key="+AppKey.KEY+"";
	ExpandableListView expand;
	ExpandableAdapter adapter;
	int img[]={R.drawable.mmdrpicclick, R.drawable.sd113, R.drawable.da1,
			R.drawable.asd33, R.drawable.f3, R.drawable.dsa1, R.drawable.sd113,
			R.drawable.jieripicclick, R.drawable.zhushipicclick, R.drawable.d2,
			R.drawable.das13, R.drawable.dasfsa, R.drawable.f132,
			R.drawable.dsa1, R.drawable.asd33, R.drawable.asd33,
			R.drawable.asd333, R.drawable.asd33, R.drawable.asd33,
			R.drawable.asd33, R.drawable.asd33, R.drawable.asd33 ,
			R.drawable.asd33,R.drawable.dsa1, R.drawable.sd113,
			R.drawable.dsa1, R.drawable.sd113,R.drawable.dsa1,
			R.drawable.sd113,R.drawable.dsa1, R.drawable.sd113 };
	
	List<CateList> list;
	ACacheUtils acache;
	MyHttpUtils utils;
	Button btn_cate;
	String result;
	ImageButton btn_search;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			list = (List<CateList>) msg.obj;
			adapter = new ExpandableAdapter(getActivity(), list,img);
			expand.setOnGroupExpandListener(expandListener);
			expand.setAdapter(adapter);
		};
	};
	OnGroupExpandListener expandListener = new OnGroupExpandListener() {

		@Override
		public void onGroupExpand(int arg0) {
			// TODO Auto-generated method stub
			for (int i = 0, count = expand.getExpandableListAdapter()
					.getGroupCount(); i < count; i++) {
				if (arg0 != i) {// 关闭其他分组
					expand.collapseGroup(i);
				}
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		/*list=null;
    	if(adapter.bitmap!=null && !(adapter.bitmap.isRecycled())){
    		adapter.bitmap.recycle();
    		adapter.bitmap=null;
    	}
    	System.gc();*/
		super.onDestroyView();
	}
    @Override
    public void onDetach() {
    	// TODO Auto-generated method stub
    	super.onDetach();
    	
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.menu_layout, null);
		expand = (ExpandableListView) v.findViewById(R.id.menu_ex_catelist);
		btn_search = (ImageButton) v.findViewById(R.id.menu_btn_search);
		btn_search.setOnClickListener(click);//收藏按钮 此处暂时已屏蔽
		utils = new MyHttpUtils();
		acache = ACacheUtils.get(getActivity(), "category");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if ((acache.getAsString("resultCate")) == null) {
					result = utils.getRes(URL);
					acache.put("resultCate", result, ACacheUtils.TIME_DAY);
				} else {
					result = acache.getAsString("resultCate");
					Log.i("acacheCategory", result + "");
				}
				list = utils.getCategoryList(result);
				Message msg = new Message();
				msg.obj = list;
				handler.sendMessage(msg);
			}
		}).start();
		return v;
	}
    
	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent in = new Intent(getActivity(), CollectionsActivity.class);
			startActivity(in);
		}
	};
}
