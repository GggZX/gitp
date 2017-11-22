package com.xzc.adapter;

import java.util.ArrayList;

import com.xzc.activity.R;
import com.xzc.entities.QuanquanListInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class QuanquanListAdapter extends BaseAdapter {
	Context context;
	ArrayList<QuanquanListInfo> list;

	public QuanquanListAdapter(Context context, ArrayList<QuanquanListInfo> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		Hander hander = new Hander();
		if (arg1 == null) {
			arg1 = LayoutInflater.from(context).inflate(
					R.layout.quanquanfragmentlist_layout, null);
			hander.content = (TextView) arg1
					.findViewById(R.id.quanquanfragmentlist_textview_content);
			hander.contentimg = (ImageView) arg1
					.findViewById(R.id.quanquanfragmentlist_imageview_img);
			hander.name = (TextView) arg1
					.findViewById(R.id.quanquanfragmentlist_textview_name);
			hander.time = (TextView) arg1
					.findViewById(R.id.quanquanfragmentlist_textview_time);
			hander.touxiang = (ImageView) arg1
					.findViewById(R.id.quanquanfragmentlist_imageview_photo);
			hander.zan = (TextView) arg1
					.findViewById(R.id.quanquanfragmentlist_textview_zantv);
			arg1.setTag(hander);
		}
		hander = (Hander) arg1.getTag();
		QuanquanListInfo info = list.get(arg0);
		hander.content.setText(info.getContent());
		hander.contentimg.setImageResource(info.getContentimg());
		hander.name.setText(info.getName());
		hander.time.setText(info.getTime());
		hander.touxiang.setImageResource(info.getImg());
		hander.zan.setText("(" + info.getZan() + ")");
		return arg1;
	}

	public class Hander {
		ImageView touxiang;
		ImageView contentimg;
		TextView name;
		TextView time;
		TextView content;
		TextView zan;
		TextView ping;
	}
}
