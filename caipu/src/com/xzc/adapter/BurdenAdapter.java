package com.xzc.adapter;

import java.util.List;

import com.xzc.activity.R;
import com.xzc.adapter.IngredientsAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BurdenAdapter extends BaseAdapter {
	Context con;
	List<String> burden;

	public BurdenAdapter(Context con, List<String> burden) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.burden = burden;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return burden.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return burden.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (v == null) {
			holder = new ViewHolder();
			v = LayoutInflater.from(con)
					.inflate(R.layout.ready_list_item, null);
			holder.tv_main = (TextView) v.findViewById(R.id.ready_item_left);
			holder.tv_support = (TextView) v
					.findViewById(R.id.ready_item_right);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		String s[] = burden.get(arg0).split(",");
		holder.tv_main.setText(s[1]);
		holder.tv_support.setText(s[0]);
		return v;
	}

	class ViewHolder {
		TextView tv_main = null;
		TextView tv_support = null;
	}
}
