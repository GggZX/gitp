package com.xzc.adapter;

import java.util.List;

import com.xzc.activity.R;
import com.xzc.entities.Collections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchHistoryAdapter extends BaseAdapter {
	Context con;
	List<Collections> list;
	LayoutInflater inflater;

	public SearchHistoryAdapter(Context con, List<Collections> list) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.list = list;
		inflater = LayoutInflater.from(con);
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
		// TODO Auto-generated method stub
		arg1 = inflater.from(con).inflate(R.layout.searchhistoryitem, null);
		TextView textView = (TextView) arg1
				.findViewById(R.id.searchhistory_text);
		ImageView imageView = (ImageView) arg1
				.findViewById(R.id.searchhistory_icon);
		textView.setText(list.get(arg0).getMenuName());
		imageView.setImageResource(R.drawable.icon_history);
		return arg1;
	}

}
