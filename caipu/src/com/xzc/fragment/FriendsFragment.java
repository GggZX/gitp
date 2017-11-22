package com.xzc.fragment;

import java.util.ArrayList;

import org.taptwo.android.widget.CircleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;
import org.w3c.dom.Text;


import com.xzc.activity.CategoryActivity;
import com.xzc.activity.R;
import com.xzc.activity.ResultActivity;
import com.xzc.adapter.QuanquanGalleryAdapter;
import com.xzc.adapter.QuanquanListAdapter;
import com.xzc.entities.QuanquanListInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FriendsFragment extends Fragment {
	ImageButton upsend, imageButton1, imageButton2, imageButton3, imageButton4;
	ViewFlow flow;
	RadioGroup group;
	RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
	ListView listView;
	ArrayList<QuanquanListInfo> list;
	int[] img = { R.drawable.meishi0, R.drawable.meishi1, R.drawable.meishi2,
			R.drawable.meishi3, R.drawable.meishi4};
	QuanquanListInfo info;
	QuanquanListAdapter adapter;
	QuanquanGalleryAdapter adapter2;
	Intent in;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.quanquanfragment_layout, null);
		upsend = (ImageButton) v
				.findViewById(R.id.quanquanfragment_imagebutton_upsend);//no use
		imageButton1 = (ImageButton) v
				.findViewById(R.id.quanquanfragment_imagebutton_1);
		imageButton2 = (ImageButton) v
				.findViewById(R.id.quanquanfragment_imagebutton_2);
		imageButton3 = (ImageButton) v
				.findViewById(R.id.quanquanfragment_imagebutton_3);
		imageButton4 = (ImageButton) v
				.findViewById(R.id.quanquanfragment_imagebutton_4);
		flow = (ViewFlow) v
				.findViewById(R.id.viewflow1);
		CircleFlowIndicator indic = (CircleFlowIndicator) v
				.findViewById(R.id.viewflowindic1);
		flow.setmSideBuffer(5);
		flow.setFlowIndicator(indic);
		flow.setTimeSpan(4500);
		flow.setSelection(5 * 1000);
		flow.startAutoFlowTimer();
		listView = (com.xzc.utils.AsynListView) v
				.findViewById(R.id.quanquanfragment_listview);
		listView.setFocusable(false);
		in = new Intent(getActivity(), CategoryActivity.class);
		imageButton1.setOnClickListener(click);
		imageButton2.setOnClickListener(click);
		imageButton3.setOnClickListener(click);
		imageButton4.setOnClickListener(click);
		ADD();
		adapter = new QuanquanListAdapter(getActivity(), list);
		listView.setAdapter(adapter);
		adapter2 = new QuanquanGalleryAdapter(getActivity(), img);
		flow.setAdapter(adapter2);
		return v;
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.quanquanfragment_imagebutton_1:
				in.putExtra("menuId", "277");
				break;
			case R.id.quanquanfragment_imagebutton_2:
				in.putExtra("menuId", "252");
				break;
			case R.id.quanquanfragment_imagebutton_3:
				in.putExtra("menuId", "205");
				break;
			case R.id.quanquanfragment_imagebutton_4:
				in.putExtra("menuId", "64");
				break;
			default:
				break;
			}
			startActivity(in);
		}
	};

	private void ADD() {
		list = new ArrayList<QuanquanListInfo>();
		info = new QuanquanListInfo();
		info.setContent("第一次写，不太会，就是想说，照着弄出来还不错，哈哈哈哈哈.....");
		info.setContentimg(R.drawable.meishi0);
		info.setImg(R.drawable.touxiang1);
		info.setName("李天龙");
		info.setPing(9527);
		info.setTime("2015-09-17发布  来自小米4");
		info.setZan(5843);
		list.add(info);
		info = new QuanquanListInfo();
		info.setContent("我爱海鲜");
		info.setContentimg(R.drawable.meishi6);
		info.setImg(R.drawable.touxiang2);
		info.setName("刘玉梅");
		info.setPing(2445);
		info.setTime("2015-09-24发布  来自魅族");
		info.setZan(2223);
		list.add(info);
		info = new QuanquanListInfo();
		info.setContent("昨天做的蛋糕");
		info.setContentimg(R.drawable.meishi5);
		info.setImg(R.drawable.touxiang3);
		info.setName("王翀");
		info.setPing(2445);
		info.setTime("2015-09-24发布  来自红米");
		info.setZan(2223);
		list.add(info);
	}
}
