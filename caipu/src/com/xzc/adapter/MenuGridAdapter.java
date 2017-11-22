package com.xzc.adapter;

import java.util.List;

import com.xzc.activity.CategoryActivity;
import com.xzc.activity.R;
import com.xzc.activity.ResultActivity;
import com.xzc.activity.StepsActivity;
import com.xzc.entities.CateList;
import com.xzc.entities.Category;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

public class MenuGridAdapter extends BaseAdapter {
	List<Category> list;
	List<CateList> li;
	Context con;
	int pos;
	Category c;
	ViewHolder holder = null;

	public MenuGridAdapter(Context con, List<CateList> li, int pos) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.li = li;
		this.pos = pos;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		list = li.get(pos).getList();
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		list = li.get(pos).getList();
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int gpos, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (v == null) {
			holder = new ViewHolder();
			v = LayoutInflater.from(con).inflate(R.layout.ex_grid_item, null);
			holder.grid_btn_cate = (Button) v.findViewById(R.id.grid_btn_cate);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		c = li.get(pos).getList().get(gpos);
		holder.grid_btn_cate.setText(c.getName());
		holder.grid_btn_cate.setId(gpos);
		holder.grid_btn_cate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stubs
				c = li.get(pos).getList().get(gpos);
				Intent in = new Intent(con, CategoryActivity.class);
				in.putExtra("menuId", c.getId());
				Log.i("adapterID", c.getId()+"");
				con.startActivity(in);
			}
		});
		return v;
	}

	class ViewHolder {
		Button grid_btn_cate = null;
	}
}
