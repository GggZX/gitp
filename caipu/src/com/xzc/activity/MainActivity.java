package com.xzc.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.xzc.adapter.MyFragmentAdapter;
import com.xzc.fragment.FriendsFragment;
import com.xzc.fragment.HomeFragment;
import com.xzc.fragment.HomeNewFragment;
import com.xzc.fragment.MenuFragment;
import com.xzc.fragment.MineFragment;
import com.xzc.utils.MainViewPager;
import com.xzc.utils.CollMyHelper;

import android.R.bool;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	public MainViewPager pager;//���ɻ���viewpager
	public List<Fragment> list;
	FragmentManager frageManager;
	MyFragmentAdapter adapter;
	RadioGroup foot_group;
    SQLiteDatabase db;
    private boolean isExit=false;//
    private Timer timer=null;
    private TimerTask timerTask=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		add();
		init();
		timer=new Timer();
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(3);
		foot_group.setOnCheckedChangeListener(check);
		CollMyHelper helper = new CollMyHelper(this);
		db=helper.getReadableDatabase();
	}

	OnCheckedChangeListener check = new OnCheckedChangeListener() {
		int pos = 0;

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			switch (arg1) {
			case R.id.foot_home:
				pos = 0;
				break;
			case R.id.foot_menu:
				pos = 1;
				break;
			case R.id.foot_friends:
				pos = 2;
				break;
			case R.id.foot_mine:
				pos = 3;
				break;
			default:
				break;
			}
			pager.setCurrentItem(pos);
		}
	};

	private void init() {
		// TODO Auto-generated method stub
		pager = (MainViewPager) findViewById(R.id.main_pager);
		frageManager = getSupportFragmentManager();
		foot_group = (RadioGroup) findViewById(R.id.foot_group);
		adapter = new MyFragmentAdapter(frageManager, list);
	}

	private void add() {
		// TODO Auto-generated method stub
		list = new ArrayList<Fragment>();
		list.add(new HomeFragment());
		list.add(new MenuFragment());
		list.add(new FriendsFragment());
		list.add(new MineFragment());
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(isExit){
			finish();
		}else{
			isExit=true;
			Toast.makeText(MainActivity.this, "�ٵ��һ���˳�", 0).show();
			timerTask=new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					isExit=false;
				}
			};
			timer.schedule(timerTask, 2000);
		}
	}
}
