package com.xzc.adapter;

import java.util.List;

import com.xzc.activity.R;
import com.xzc.entities.CateList;
import com.xzc.entities.Category;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ExpandableAdapter extends BaseExpandableListAdapter {
	int[] img;
	Context con;
	List<CateList> list;
	List<Category> li;
	MenuGridAdapter adapter;
    BitmapFactory.Options options;
	public Bitmap bitmap;
	public ExpandableAdapter(Context con, List<CateList> list, int[] img) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.list = list;
		this.img = img;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		li = list.get(arg0).getList();
		return li.get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View v,
			ViewGroup arg4) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (v == null) {
			holder = new ViewHolder();
			v = LayoutInflater.from(con).inflate(R.layout.menu_grid_item, null);
			holder.menu_grid = (GridView) v.findViewById(R.id.menu_grid);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		adapter = new MenuGridAdapter(con, list, arg0);
		holder.menu_grid.setAdapter(adapter);
		return v;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		li = list.get(arg0).getList();
		return 1;
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
	public View getGroupView(int arg0, boolean arg1, View v, ViewGroup arg3) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (v == null) {
			holder = new ViewHolder();
			v = LayoutInflater.from(con).inflate(R.layout.expand_group_item,
					null);
			holder.ex_header = (ImageView) v.findViewById(R.id.ex_img_header);
			holder.ex_tv_cate = (TextView) v.findViewById(R.id.ex_tv_cate);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		CateList cl = list.get(arg0);
		// holder.ex_header.setImageResource(img[arg0]);

		options = new BitmapFactory.Options();
		options.inSampleSize = 8;
		bitmap = BitmapFactory.decodeResource(con.getResources(),
				img[arg0], options);
		holder.ex_header.setImageBitmap(bitmap);
		holder.ex_tv_cate.setText(cl.getName());
		return v;
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

	class ViewHolder {
		ImageView ex_header = null;
		TextView ex_tv_cate = null;
		GridView menu_grid;
		Button grid_btn_cate = null;
	}
}
