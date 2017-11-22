package com.xzc.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xzc.activity.R;
import com.xzc.adapter.StepsAdapter.ViewHolder;
import com.xzc.entities.MenuSteps;
import com.xzc.utils.ImageLoaderOptionsUtils;

import android.content.Context;
import android.graphics.Path.FillType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RadioGroup.LayoutParams;
import android.widget.TextView;

public class StepsPagerAdapter extends BaseAdapter {
	Context con;
	List<MenuSteps> list;
	ImageLoaderOptionsUtils utils;
	DisplayImageOptions options;
	ImageLoader loader;
	public StepsPagerAdapter(Context con, List<MenuSteps> list) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.list = list;
		utils = new ImageLoaderOptionsUtils();
		loader = ImageLoader.getInstance();
		loader.init(ImageLoaderConfiguration.createDefault(con));
		options = utils.init();
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
		ImageView image = new ImageView(con);
		image.setLayoutParams(new Gallery.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		image.setScaleType(ScaleType.FIT_XY);

		loader.displayImage(list.get(arg0).getImg(), image,options);
		return image;
	}
}
