package com.xzc.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xzc.activity.CollectionsActivity;
import com.xzc.activity.R;
import com.xzc.activity.ReadHistoryActivity;
import com.xzc.utils.CircularImage;
import com.xzc.utils.CornerListView;
import com.xzc.utils.EmailLinkTextview;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MineFragment extends Fragment {
	TextView titletext;
	CircularImage image;
	View v;
	RadioButton coll,ping, history, clear, setting, more;
	Intent in;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.mine_layout, null);
		init();
		image.setImageResource(R.drawable.touxiang1);
		coll.setOnClickListener(click);
		ping.setOnClickListener(click);
		history.setOnClickListener(click);
		clear.setOnClickListener(click);
		setting.setOnClickListener(click);
		more.setOnClickListener(click);
		return v;
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.mine_imagebutton_img1:
				in = new Intent(getActivity(), CollectionsActivity.class);
				startActivity(in);
				break;
			case R.id.mine_imagebutton_img2:
				in = new Intent(getActivity(), CollectionsActivity.class);
				startActivity(in);
				break;
			case R.id.mine_imagebutton_img3:
				in = new Intent(getActivity(), ReadHistoryActivity.class);
				startActivity(in);
				break;
			case R.id.mine_imagebutton_img4:
				in = new Intent(getActivity(), CollectionsActivity.class);
				startActivity(in);
				break;
			case R.id.mine_imagebutton_img5:
				in = new Intent(getActivity(), CollectionsActivity.class);
				startActivity(in);
				break;
			case R.id.mine_imagebutton_img6:
				AlertDialog.Builder builder=new Builder(getActivity());
				/*builder.setTitle("请联系我");
				builder.setMessage("15017932129");
				
				builder.create();
				builder.show();*/
				AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();  
				alertDialog.show();  
				Window window = alertDialog.getWindow();  
				window.setContentView(R.layout.dialog_contact);  
				TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);  
				tv_title.setText("请联系我");  
				EmailLinkTextview tv_message = (EmailLinkTextview) window.findViewById(R.id.tv_dialog_message);  
				tv_message.setText("电话:15017932129 \nEmail:grantzxin@gmail.com");  
				/*TextView tv_email = (TextView) window.findViewById(R.id.email);  
				tv_message.setText("email:grantzxin@gmail.com");  */
				break;
			default:
				break;
			}

		}
	};

	private void init() {
		// TODO Auto-generated method stub/
		// ping,history,clear,setting,more
		image=(CircularImage) v.findViewById(R.id.mine_imageview_touxiang);
		coll = (RadioButton) v.findViewById(R.id.mine_imagebutton_img1);
		ping = (RadioButton) v.findViewById(R.id.mine_imagebutton_img2);
		history = (RadioButton) v.findViewById(R.id.mine_imagebutton_img3);
		clear = (RadioButton) v.findViewById(R.id.mine_imagebutton_img4);
		setting = (RadioButton) v.findViewById(R.id.mine_imagebutton_img5);
		more = (RadioButton) v.findViewById(R.id.mine_imagebutton_img6);
	}
}
