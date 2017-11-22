package com.xzc.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xzc.activity.R;
import com.xzc.entities.MenuData;
import com.xzc.utils.ImageLoaderOptionsRunde;
import com.xzc.utils.ImageLoaderOptionsUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeGridAdapter extends BaseAdapter {
	Context con;
	List<MenuData> list;
	ImageLoaderOptionsRunde utils;
	DisplayImageOptions options;
	ImageLoader loader;
	public HomeGridAdapter(Context con, List<MenuData> list) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.list = list;
		utils = new ImageLoaderOptionsRunde();
		loader = ImageLoader.getInstance();
		loader.init(ImageLoaderConfiguration.createDefault(con));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
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
		ViewHolder holder = null;
		if (v == null) {
			holder=new ViewHolder();
			v = LayoutInflater.from(con).inflate(R.layout.home_grid_item, null);
			holder.tv = (TextView) v.findViewById(R.id.home_tv_title);
			holder.img = (ImageView) v.findViewById(R.id.home_grid_img);
			
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		options = utils.init();

		loader.displayImage(list.get(arg0).getAlbums().get(0), holder.img,options);
		holder.tv.setText(list.get(arg0).getTitle());
		return v;
	}

	class ViewHolder {
		ImageView img = null;
		TextView tv = null;
	}
}
