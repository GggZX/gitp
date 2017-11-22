package louis.fangwangyi.adapter;

import java.util.List;
import java.util.zip.Inflater;

import louia.fangwangyi.activity.R;
import louis.fangwangyi.info.Info_province;
import louis.fangwangyi.info.Info_shouye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ShouyeAdapter extends BaseAdapter{
    List<Info_shouye> list;
    Context context;
    public ShouyeAdapter(Context context,List<Info_shouye> list){
    	this.list=list;
    	this.context=context;
    	
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		arg1=LayoutInflater.from(context).inflate(R.layout.shouye_item, null);
		TextView textView=(TextView) arg1.findViewById(R.id.shouye_item_text);
		textView.setText(list.get(arg0).getTitle());
		return arg1;
	}

}
