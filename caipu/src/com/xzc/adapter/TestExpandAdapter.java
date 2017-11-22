package com.xzc.adapter;

import java.util.List;

import com.xzc.entities.CateList;
import com.xzc.entities.Category;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class TestExpandAdapter extends BaseExpandableListAdapter {
	Context con;
	List<CateList> list;
	List<Category> li;

	public TestExpandAdapter(Context con, List<CateList> list) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.list = list;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		li = (List<Category>) list.get(arg0);
		return li.get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		// TODO Auto-generated method stub
		TextView tv = new TextView(con);
		Category c = list.get(arg0).getList().get(arg1);
		tv.setText(c.getName());
		return tv;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		li = list.get(arg0).getList();
		return li.size();
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		TextView tv = new TextView(con);
		CateList cl = list.get(arg0);
		tv.setText(cl.getName());
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
