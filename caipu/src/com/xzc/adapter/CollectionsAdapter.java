package com.xzc.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xzc.activity.R;
import com.xzc.adapter.CategoryAdapter.Viewholder;
import com.xzc.entities.Collections;
import com.xzc.entities.MenuData;
import com.xzc.utils.ImageLoaderOptionsUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CollectionsAdapter extends BaseAdapter {
	List<Collections> list;
	Context con;
	LayoutInflater inflater;
	ImageLoaderOptionsUtils utils;
	DisplayImageOptions options;
	ImageLoader loader;
	public CollectionsAdapter(Context con, List<Collections> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.con = con;
		inflater = LayoutInflater.from(con);
		utils = new ImageLoaderOptionsUtils();
		loader= ImageLoader.getInstance();
		loader.init(ImageLoaderConfiguration.createDefault(con));
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
	public View getView(int arg0, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Viewholder holder = null;
		if (v == null) {
			holder = new Viewholder();
			v = inflater.from(con).inflate(R.layout.result_list_item, null);
			holder.result_img_icon = (ImageView) v
					.findViewById(R.id.result_listitem_icon);
			holder.result_tv_name = (TextView) v
					.findViewById(R.id.result_listitem_title);
			holder.result_tv_count = (TextView) v
					.findViewById(R.id.result_listitem_count);
			holder.tag_tv=(TextView) v.findViewById(R.id.resukt_listview_tag);
			v.setTag(holder);
		} else {
			holder = (Viewholder) v.getTag();
		}
		Collections c = list.get(arg0);
		options = utils.init();

		loader.displayImage(c.getImg(), holder.result_img_icon, options);
		holder.result_tv_name.setText(c.getMenuName());
		holder.result_tv_count.setText(c.getStep() + "");
		holder.tag_tv.setText(c.getTags().split(";")[0]);
		return v;
	}

	class Viewholder {
		ImageView result_img_icon = null;
		TextView result_tv_name = null;
		TextView result_tv_count = null;
		TextView tag_tv=null;
	}
}
