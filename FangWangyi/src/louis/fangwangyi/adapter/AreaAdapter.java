package louis.fangwangyi.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import louia.fangwangyi.activity.R;
import louis.fangwangyi.adapter.NewListAdapter.ViewHolder;
import louis.fangwangyi.info.Info_news_area;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AreaAdapter extends BaseAdapter{
  List<Info_news_area> list;
  Context context;
	private ImageLoader imageLoader = null;
	private	DisplayImageOptions options = null;
	
	static class ViewHolder {
		ImageView imageView;
		TextView tvTitle;
		WebView tvContent;
		TextView update;
	}
	
  public AreaAdapter(Context context, List<Info_news_area> list){
	  this.list=list;
	  this.context=context;
	  
	  imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		options = new DisplayImageOptions.Builder()
		.displayer(new RoundedBitmapDisplayer(0xff000000, 10))
		.cacheInMemory()
		.cacheOnDisc()
		.build();
	  
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
		ViewHolder holder = null;
		if(arg1 == null) {
			arg1 = LayoutInflater.from(context).inflate(R.layout.areaitem, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) arg1.findViewById(R.id.area_item_im);
			holder.tvTitle = (TextView) arg1.findViewById(R.id.area_item_title);
			holder.tvContent = (WebView) arg1.findViewById(R.id.area_item_desc);
			holder.update = (TextView) arg1.findViewById(R.id.area_item_update);
			
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
//		imageLoader.displayImage(R.drawable.btn_new, holder.imageView, options);
		holder.tvTitle.setText(list.get(arg0).getTitle());
		holder.tvContent.loadDataWithBaseURL(null,list.get(arg0).getDesc(), "text/html","utf-8",null);
		holder.update.setText(list.get(arg0).getPubDate());
//		Uri t = null;
//		holder.imageView.setImageURI(t);
		
		return arg1;
	}

}
