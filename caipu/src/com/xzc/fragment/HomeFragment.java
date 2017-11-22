package com.xzc.fragment;

import java.util.ArrayList;
import java.util.List;

import org.taptwo.android.widget.CircleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xzc.activity.MainActivity;
import com.xzc.activity.R;
import com.xzc.activity.RegisterActivity;
import com.xzc.activity.SearchActivity;
import com.xzc.activity.StepsActivity;
import com.xzc.adapter.HomeGalleryAdapter;
import com.xzc.adapter.HomeGridAdapter;
import com.xzc.entities.AllInfo;
import com.xzc.entities.MenuData;
import com.xzc.utils.ACacheUtils;
import com.xzc.utils.AppKey;
import com.xzc.utils.AsynGridView;
import com.xzc.utils.MyHttpUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class HomeFragment extends Fragment {
	private ViewFlow viewFlow;
	ImageButton search;
	ImageButton login;
	RadioGroup group;
	AsynGridView gridView;
	HomeGridAdapter gridAdapter;
	RadioButton radioButton1, radioButton2, radioButton3, radioButton4,
			radioButton5, radioButton6;
	ListView listView;
	MyHttpUtils utils;
	ACacheUtils acache;
	String result;
	Intent intent;
	List<MenuData> list;
	MenuData menuData;
	List<String> listImg;
	HomeGalleryAdapter adapter;
	String url = "http://apis.juhe.cn/cook/index?key=" + AppKey.KEY
			+ "&cid=244";//http://apis.juhe.cn/cook/index?key=728df953a2aa5c693553ca82f6b5fc3c&cid=244
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			list = (List<MenuData>) msg.obj;
			listImg = new ArrayList<String>();
			for (int i = 0; i < 5; i++) {
				String img = list.get(i).getAlbums().get(0);
				listImg.add(img);
			}
			gridAdapter = new HomeGridAdapter(getActivity(), list);
			adapter = new HomeGalleryAdapter(getActivity(), listImg);
			viewFlow.setAdapter(adapter);
			gridView.setAdapter(gridAdapter);
			super.handleMessage(msg);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.home_layout, container, false);
		viewFlow = (ViewFlow) v.findViewById(R.id.viewflow);
		// viewFlow.setAdapter(new ImageAdapter(getActivity()));
		viewFlow.setmSideBuffer(5);
		gridView = (AsynGridView) v.findViewById(R.id.home_gridview);
		gridView.setFocusable(false);
		gridView.setOnItemClickListener(itemClick);
		CircleFlowIndicator indic = (CircleFlowIndicator) v
				.findViewById(R.id.viewflowindic);
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4500);
		viewFlow.setSelection(5 * 1000);
		viewFlow.startAutoFlowTimer();
		search = (ImageButton) v.findViewById(R.id.home_edittext_search);
		login = (ImageButton) v.findViewById(R.id.home_imagebutton_login);
		list = new ArrayList<MenuData>();
		utils = new MyHttpUtils();
		acache = ACacheUtils.get(getActivity(), "queryHome");
		new Thread(new Runnable() {
			@Override
			public void run() {
				if ((acache.getAsString("resultCooks")) == null) {
					result = utils.getRes(url);
					acache.put("resultCooks", result, acache.TIME_DAY);
				} else {
					result = acache.getAsString("resultCooks");
				}
				list = (List<MenuData>) utils.getMenuList(result);
				Message message = new Message();
				message.obj = list;
				handler.sendMessage(message);
			}
		}).start();

		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent(getActivity(), SearchActivity.class);
				startActivity(intent);
			}
		});
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),
						RegisterActivity.class);
				startActivity(intent);
			}
		});
		return v;
	}

	OnItemClickListener itemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			intent = new Intent(getActivity(), StepsActivity.class);
			intent.putExtra("stepsId", list.get(arg2).getId());
			startActivity(intent);
		}
	};

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final MainActivity activity = ((MainActivity) getActivity());
		View fragment0View = activity.list.get(0).getView();
		activity.pager.setSlipping(false);
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
/*		list = null;
		if (adapter.bitmap != null && !(adapter.bitmap.isRecycled())) {
			adapter.bitmap.recycle();
			adapter.bitmap = null;
		}
		System.gc();*/
		super.onDestroy();
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
/*		list = null;
		if (adapter.bitmap != null && !(adapter.bitmap.isRecycled())) {
			adapter.bitmap.recycle();
			adapter.bitmap = null;
		}
		System.gc();*/
		super.onDestroyView();
	}
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}
}
