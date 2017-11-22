package com.xzc.adapter;

import com.xzc.activity.R;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class QuanquanGalleryAdapter extends BaseAdapter {
	Context context;
	int[] img = { R.drawable.meishi0, R.drawable.meishi1, R.drawable.meishi2,
			R.drawable.meishi3, R.drawable.meishi4};

	public QuanquanGalleryAdapter(Context context, int[] img) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return img[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ImageView imageView = new ImageView(context);
		imageView.setImageResource(img[arg0 % img.length]);
		imageView.setScaleType(ScaleType.FIT_XY);
		return imageView;
	}

}
