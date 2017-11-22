package com.xzc.activity;

import java.util.List;

import com.xzc.adapter.StepsPagerAdapter;
import com.xzc.entities.MenuSteps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.TextView;

public class StepsImgActivity extends Activity {
	Gallery steps_gallery;
	List<MenuSteps> list;
	Intent in;
	StepsPagerAdapter adapter;
	TextView tv_current, tv_all, tv_step;
	int current;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stepsimg_layout);
		init();
		in = getIntent();
		list = (List<MenuSteps>) in.getSerializableExtra("listSteps");
		current = in.getIntExtra("current", 0);
		adapter = new StepsPagerAdapter(this, list);
		steps_gallery.setAdapter(adapter);
		steps_gallery.setSelection(current);
		tv_current.setText((steps_gallery.getSelectedItemPosition() + 1) + "");
		tv_all.setText(list.size() + "");
		tv_step.setText(list.get(steps_gallery.getSelectedItemPosition())
				.getStep());
		steps_gallery.setOnItemSelectedListener(selected);
	}

	private void init() {
		// TODO Auto-generated method stub
		steps_gallery = (Gallery) findViewById(R.id.stepsimg_gallery);
		tv_current = (TextView) findViewById(R.id.steps_tv_current);
		tv_all = (TextView) findViewById(R.id.steps_tv_all);
		tv_step = (TextView) findViewById(R.id.steps_tv);
	}

	OnItemSelectedListener selected = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			tv_current.setText((arg2 + 1) + "");
			tv_step.setText(list.get(arg2).getStep());
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};
}
