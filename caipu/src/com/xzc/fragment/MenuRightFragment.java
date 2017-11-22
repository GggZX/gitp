/*package com.ehzx.ui;

import java.io.File;
import java.io.FileOutputStream;

import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.sharesdk.framework.AbstractWeibo;
import cn.sharesdk.onekeyshare.ShareAllGird;

import com.wyz.ehzx.R;
import com.wyz.ui.FeedbackActivity;
import com.wyz.ui.SplashSpotActivity;

public class MenuRightFragment extends Fragment {
	private View mView;
	TextView feedback_tv_info;
	TextView about_tv_ehzx;
	TextView share_app_all;
	TextView ad_tv_ehzx;
	TextView spotBtn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	 if (mView == null) {
			mView = inflater.inflate(R.layout.right_menu, container, false);
			feedback_tv_info = (TextView) mView.findViewById(R.id.feedback_tv_info);
			about_tv_ehzx = (TextView) mView.findViewById(R.id.about_tv_ehzx);
			share_app_all = (TextView) mView.findViewById(R.id.share_app_all);
		//	ad_tv_ehzx = (TextView) mView.findViewById(R.id.ad_tv_ehzx);
			spotBtn = (TextView) mView.findViewById(R.id.ad_tv_ehzx);
			feedback_tv_info.setOnClickListener(listener);
			about_tv_ehzx.setOnClickListener(listener);
			share_app_all.setOnClickListener(listener);
			spotBtn.setOnClickListener(listener);
		 }
		
		AbstractWeibo.initSDK(getActivity());	
		
		initImagePath();					
		
	 
		
		return mView;
	}
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		 switch (v.getId()) {
		case R.id.feedback_tv_info:
			Intent intent = new Intent(getActivity(),FeedbackActivity.class);
			startActivity(intent);
			break;
		case R.id.about_tv_ehzx:
			showAboutDialog();
			break;
		case R.id.share_app_all:
			showGrid(false);
			break;
		case R.id.ad_tv_ehzx:
	//		setSpotAd();
			Intent ad = new Intent(getActivity(),SplashSpotActivity.class);
			startActivity(ad);
			break;

		default:
			break;
		}
			
		}
	};
	

	public void showAboutDialog() {
		AlertDialog.Builder b = new Builder(getActivity());

		View view = getActivity().getLayoutInflater().inflate(
				R.layout.activity_about, null);
		b.setTitle("关于易合資訊");
		b.setPositiveButton("确认", null);
		b.setView(view);
		b.create();
		b.show();
	}
	public boolean shareApp() {
	Intent intent=new Intent(Intent.ACTION_SEND);
	intent.setType("image/*");
	intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
	intent.putExtra(Intent.EXTRA_TEXT, "I have successfully share my message through my app");
	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	startActivity(Intent.createChooser(intent, "易合資訊"));
	return true;
 
	}
	
	
	public static String TEST_IMAGE;
	private void showGrid(boolean silent) {
		Intent i = new Intent(getActivity(), ShareAllGird.class);
		// 分享时Notification的图标
		i.putExtra("notif_icon", R.drawable.ic_launcher);
		// 分享时Notification的标题
		i.putExtra("notif_title", this.getString(R.string.app_name));

		// title标题，在印象笔记、邮箱、信息、微信（包括好友和朋友圈）、人人网和QQ空间使用，否则可以不提供
		i.putExtra("title", this.getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用，否则可以不提供
		i.putExtra("titleUrl", "http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		i.putExtra("text", this.getString(R.string.share_content));
		// imagePath是本地的图片路径，所有平台都支持这个字段，不提供，则表示不分享图片
		i.putExtra("imagePath", TEST_IMAGE);
		// url仅在微信（包括好友和朋友圈）中使用，否则可以不提供
		i.putExtra("url", "http://sharesdk.cn");
		// thumbPath是缩略图的本地路径，仅在微信（包括好友和朋友圈）中使用，否则可以不提供
		i.putExtra("thumbPath", TEST_IMAGE);
		// appPath是待分享应用程序的本地路劲，仅在微信（包括好友和朋友圈）中使用，否则可以不提供
		i.putExtra("appPath", TEST_IMAGE);
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
		i.putExtra("comment", this.getString(R.string.share));
		// site是分享此内容的网站名称，仅在QQ空间使用，否则可以不提供
		i.putExtra("site", this.getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用，否则可以不提供
		i.putExtra("siteUrl", "http://sharesdk.cn");

		// 是否直接分享
		i.putExtra("silent", silent);
		this.startActivity(i);
	}
	
	public static String actionToString(int action) {
		switch (action) {
			case AbstractWeibo.ACTION_AUTHORIZING: return "ACTION_AUTHORIZING";
			case AbstractWeibo.ACTION_GETTING_FRIEND_LIST: return "ACTION_GETTING_FRIEND_LIST";
			case AbstractWeibo.ACTION_FOLLOWING_USER: return "ACTION_FOLLOWING_USER";
			case AbstractWeibo.ACTION_SENDING_DIRECT_MESSAGE: return "ACTION_SENDING_DIRECT_MESSAGE";
			case AbstractWeibo.ACTION_TIMELINE: return "ACTION_TIMELINE";
			case AbstractWeibo.ACTION_USER_INFOR: return "ACTION_USER_INFOR";
			case AbstractWeibo.ACTION_SHARE: return "ACTION_SHARE";
			default: {
				return "UNKNOWN";
			}
		}
	}
	@Override
	public void onDestroy() {
		//结束ShareSDK的统计功能并释放资源
		AbstractWeibo.stopSDK(getActivity());
		super.onDestroy();
	}
	
	*//**
	 * 初始化分享的图片
	 *//*
	private void initImagePath() {
		try {//判断SD卡中是否存在此文件夹
			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
					&& Environment.getExternalStorageDirectory().exists()) {
				TEST_IMAGE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/pic.png";
			}
			else {
				TEST_IMAGE = getActivity().getApplication().getFilesDir().getAbsolutePath() + "/pic.png";
			}
			File file = new File(TEST_IMAGE);
			//判断图片是否存此文件夹中
			if (!file.exists()) {
				file.createNewFile();
				Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
				FileOutputStream fos = new FileOutputStream(file);
				pic.compress(CompressFormat.JPEG, 100, fos);
				fos.flush();
				fos.close();
			}
		} catch(Throwable t) {
			t.printStackTrace();
			TEST_IMAGE = null;
		}
	}

	private void setSpotAd() {
		// 插播接口调用

		// 加载插播资源
		SpotManager.getInstance(getActivity()).loadSpotAds();
		// 插屏出现动画效果，0:ANIM_NONE为无动画，1:ANIM_SIMPLE为简单动画效果，2:ANIM_ADVANCE为高级动画效果
		SpotManager.getInstance(getActivity()).setAnimationType(
				SpotManager.ANIM_ADVANCE);
		// 设置插屏动画的横竖屏展示方式，如果设置了横屏，则在有广告资源的情况下会是优先使用横屏图。
		SpotManager.getInstance(getActivity()).setSpotOrientation(
				SpotManager.ORIENTATION_PORTRAIT);
		
		spotBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// 展示插播广告，可以不调用loadSpot独立使用
				SpotManager.getInstance(getActivity()).showSpotAds(getActivity(),
						new SpotDialogListener() {
							@Override
							public void onShowSuccess() {
								Log.i("YoumiAdDemo", "展示成功");
							}

							@Override
							public void onShowFailed() {
								Log.i("YoumiAdDemo", "展示失败");
							}

							@Override
							public void onSpotClosed() {
								Log.i("YoumiAdDemo", "展示关闭");
							}

							@Override
							public void onSpotClick() {
								Log.i("YoumiAdDemo", "插屏点击");
							}

						}); // //

			}
		});
	}
	
	
	public MenuRightFragment() {
		 
	}
}
*/