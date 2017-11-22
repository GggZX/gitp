package com.xzc.utils;

import android.graphics.drawable.AnimationDrawable;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xzc.activity.R;

public class ImageLoaderOptionsRunde {
	public DisplayImageOptions init() {
		AnimationDrawable animationDrawable = null;
		// TODO Auto-generated method stub
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showStubImage(R.anim.img)// 设置图片加载时的图片
				.showImageForEmptyUri(R.drawable.douguo_smile)// 设置图片加载为空时的图片
				.showImageOnFail(R.drawable.douguo_friends)// 图片加载失败时的图片
				.cacheInMemory(false).cacheOnDisc(true)// 设置是否缓存在内存中 是否缓存在硬盘
				.displayer(new RoundedBitmapDisplayer(100)).build();// 设置图片圆角属性
		// animationDrawable=options.getImageOnLoading(R.anim.img);
		// animationDrawable.start();
		return options;
	}

}
