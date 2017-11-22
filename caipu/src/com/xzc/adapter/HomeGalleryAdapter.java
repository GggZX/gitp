package com.xzc.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xzc.activity.R;
import com.xzc.utils.ImageLoaderOptionsUtils;

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

public class HomeGalleryAdapter extends BaseAdapter {
	Context context;
	List<String> list;
	int count;
	ImageView imageView;
	BitmapFactory.Options options;
	public Bitmap bitmap;
	int img[] = { R.drawable.meishitupian01, R.drawable.meishitupian02,
			R.drawable.meishitupian03, R.drawable.meishitupian04,
			R.drawable.meishitupian05 };

	public HomeGalleryAdapter(Context context, List<String> list) {
		this.context = context;
		this.list = list;
		// utils = new ImageLoaderOptionsUtils();
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return img[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		imageView = new ImageView(context);
		imageView.setAdjustViewBounds(true);
		imageView.setScaleType(ScaleType.FIT_XY);
		options = new BitmapFactory.Options();
		options.inSampleSize = 2;
		bitmap = BitmapFactory.decodeResource(context.getResources(),
				img[position % img.length], options);
		imageView.setImageBitmap(bitmap);
		/*
		 * options = utils.init(); ImageLoader loader =
		 * ImageLoader.getInstance(); ImageLoader imageLoader =
		 * ImageLoader.getInstance();
		 * imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		 * imageLoader.displayImage(img[position%list.size()]+"",
		 * imageView,options);
		 */
		// if(position<0){
		// imageView.setImageResource(img[img.length-1]);
		// }else{
		// imageView.setImageResource(img[position%4]);
		// }
		return imageView;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
}
