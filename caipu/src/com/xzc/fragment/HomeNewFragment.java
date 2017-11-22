package com.xzc.fragment;

import com.xzc.activity.MainActivity;
import com.xzc.activity.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class HomeNewFragment extends Fragment {
	ImageButton upsendbtn, searchbtn, lookmebtn, tuijian1, tuijian2, tuijian3;
	RadioButton breakfast, lunch, dinner, firstbtn1, firstbtn2, firstbtn3,
			firstbtn4, quanquanbtn1, quanquanbtn2, quanquanbtn3, quanquanbtn4;
	Gallery firstgallery, quanquangallery;
	RadioGroup firstgroup, quanquangroup;
	ImageView imageView;
	ListView listView;
	View v;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.homenew_layout, null);
		inputinfo();
		return v;
	}
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub                          
		final MainActivity activity = ((MainActivity) getActivity());
		View fragment0View = activity.list.get(0).getView();
		activity.pager.setSlipping(false);
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	/*
	 * ур©ь╪Ч
	 */
	private void inputinfo(){
		upsendbtn=(ImageButton) v.findViewById(R.id.homenew_imagebutton_upsendbtn);
		searchbtn=(ImageButton) v.findViewById(R.id.homenew_imagebutton_searchbtn);
		lookmebtn=(ImageButton) v.findViewById(R.id.homenew_imagebutton_lookmebtn);
		tuijian1=(ImageButton) v.findViewById(R.id.homenew_imagebutton_caiyao);
		tuijian2=(ImageButton) v.findViewById(R.id.homenew_imagebutton_gongxiao);
		tuijian3=(ImageButton) v.findViewById(R.id.homenew_imagebutton_shuichanlei);
		breakfast=(RadioButton) v.findViewById(R.id.homenew_radioButton_breakfast);
		lunch=(RadioButton) v.findViewById(R.id.homenew_radioButton_lunch);
		dinner=(RadioButton) v.findViewById(R.id.homenew_radioButton_dinner);
		firstbtn1=(RadioButton) v.findViewById(R.id.homenew_radiobutton_firstradiobutton_1);
		firstbtn2=(RadioButton) v.findViewById(R.id.homenew_radiobutton_firstradiobutton_2);
		firstbtn3=(RadioButton) v.findViewById(R.id.homenew_radiobutton_firstradiobutton_3);
		firstbtn4=(RadioButton) v.findViewById(R.id.homenew_radiobutton_firstradiobutton_4);
		quanquanbtn1=(RadioButton) v.findViewById(R.id.homenew_radiobutton_quanquanradiobutton_q1);
		quanquanbtn2=(RadioButton) v.findViewById(R.id.homenew_radiobutton_quanquanradiobutton_q2);
		quanquanbtn3=(RadioButton) v.findViewById(R.id.homenew_radiobutton_quanquanradiobutton_q3);
		quanquanbtn4=(RadioButton) v.findViewById(R.id.homenew_radiobutton_quanquanradiobutton_q4);
		firstgallery=(Gallery)v.findViewById(R.id.homenew_gallery_firstgallery);
		quanquangallery=(Gallery)v.findViewById(R.id.homenew_gallery_quanquangallery);
		firstgroup=(RadioGroup)v.findViewById(R.id.homenew_radiogroup_firstgroup);
		quanquangroup=(RadioGroup)v.findViewById(R.id.homenew_radiogroup_quanquangroup);
		imageView=(ImageView)v.findViewById(R.id.homenew_imageview_first);
		listView=(ListView)v.findViewById(R.id.homenew_listview_homelist);
	}
}
