package com.xzc.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xzc.activity.R;
import com.xzc.entities.MenuSteps;
import com.xzc.utils.ImageLoaderOptionsUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StepsAdapter extends BaseAdapter {
	Context con;
	List<MenuSteps> list;
	ImageLoaderOptionsUtils utils;
	DisplayImageOptions options;

	public StepsAdapter(Context con, List<MenuSteps> list) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.list = list;
		utils = new ImageLoaderOptionsUtils();
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
	public View getView(int pos, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (v == null) {
			holder = new ViewHolder();
			v = LayoutInflater.from(con)
					.inflate(R.layout.steps_list_item, null);
			holder.tv_steps = (TextView) v.findViewById(R.id.list_tv_steps);
			holder.img_steps = (ImageView) v.findViewById(R.id.list_img_steps);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		MenuSteps steps = list.get(pos);
		holder.tv_steps.setText(steps.getStep());
		ImageLoader loader = ImageLoader.getInstance();
		options = utils.init();
		loader.init(ImageLoaderConfiguration.createDefault(con));
		loader.displayImage(steps.getImg(), holder.img_steps, options);
		return v;
	}

	class ViewHolder {
		TextView tv_steps = null;
		ImageView img_steps = null;
	}
}
