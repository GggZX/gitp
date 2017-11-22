package com.xzc.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @ClassName: MainViewPager
 * @author gaozexin
 */
public class MainViewPager extends ViewPager {
	private boolean isSlipping = true;

	public MainViewPager(Context context) {
		super(context);
	}

	public MainViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (!isSlipping) {
			return false;
		}
		return super.onInterceptTouchEvent(arg0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (!isSlipping) {
			return false;
		}
		return super.onTouchEvent(arg0);
	}

	/**
	 *@Title: setSlipping
	 *@param isSlipping
	 */
	public void setSlipping(boolean isSlipping) {
		this.isSlipping = isSlipping;
	}
}
