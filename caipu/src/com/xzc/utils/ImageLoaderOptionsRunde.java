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
				.showStubImage(R.anim.img)// ����ͼƬ����ʱ��ͼƬ
				.showImageForEmptyUri(R.drawable.douguo_smile)// ����ͼƬ����Ϊ��ʱ��ͼƬ
				.showImageOnFail(R.drawable.douguo_friends)// ͼƬ����ʧ��ʱ��ͼƬ
				.cacheInMemory(false).cacheOnDisc(true)// �����Ƿ񻺴����ڴ��� �Ƿ񻺴���Ӳ��
				.displayer(new RoundedBitmapDisplayer(100)).build();// ����ͼƬԲ������
		// animationDrawable=options.getImageOnLoading(R.anim.img);
		// animationDrawable.start();
		return options;
	}

}
