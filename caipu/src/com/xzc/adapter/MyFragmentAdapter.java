package com.xzc.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyFragmentAdapter extends FragmentStatePagerAdapter {
	List<Fragment> list;

	public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
		super(fm);
		this.list = list;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	
	@Override
	public Object instantiateItem(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		return super.instantiateItem(arg0, arg1);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}
    
}
