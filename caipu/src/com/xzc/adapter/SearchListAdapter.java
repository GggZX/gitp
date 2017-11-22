package com.xzc.adapter;

import java.util.ArrayList;

import com.xzc.activity.R;
import com.xzc.entities.Info_Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchListAdapter extends BaseAdapter{
   ArrayList<Info_Search> list;
   Context context;
   LayoutInflater inflater;
   public SearchListAdapter( Context context, ArrayList<Info_Search> list){
	   this.context=context;
	   this.list=list;
		inflater = LayoutInflater.from(context);
   }
   @Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		arg1=inflater.from(context).inflate(R.layout.searchhistoryitem, null);
		TextView textView=(TextView) arg1.findViewById(R.id.searchhistory_text);
		ImageView imageView=(ImageView) arg1.findViewById(R.id.searchhistory_icon);
		textView.setText(list.get(arg0).getText());
		imageView.setImageResource(R.drawable.icon_history);
		return arg1;
	}
    
}
