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
		b.setTitle("�����׺��YӍ");
		b.setPositiveButton("ȷ��", null);
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
	startActivity(Intent.createChooser(intent, "�׺��YӍ"));
	return true;
 
	}
	
	
	public static String TEST_IMAGE;
	private void showGrid(boolean silent) {
		Intent i = new Intent(getActivity(), ShareAllGird.class);
		// ����ʱNotification��ͼ��
		i.putExtra("notif_icon", R.drawable.ic_launcher);
		// ����ʱNotification�ı���
		i.putExtra("notif_title", this.getString(R.string.app_name));

		// title���⣬��ӡ��ʼǡ����䡢��Ϣ��΢�ţ��������Ѻ�����Ȧ������������QQ�ռ�ʹ�ã�������Բ��ṩ
		i.putExtra("title", this.getString(R.string.share));
		// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ�ã�������Բ��ṩ
		i.putExtra("titleUrl", "http://sharesdk.cn");
		// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		i.putExtra("text", this.getString(R.string.share_content));
		// imagePath�Ǳ��ص�ͼƬ·��������ƽ̨��֧������ֶΣ����ṩ�����ʾ������ͼƬ
		i.putExtra("imagePath", TEST_IMAGE);
		// url����΢�ţ��������Ѻ�����Ȧ����ʹ�ã�������Բ��ṩ
		i.putExtra("url", "http://sharesdk.cn");
		// thumbPath������ͼ�ı���·��������΢�ţ��������Ѻ�����Ȧ����ʹ�ã�������Բ��ṩ
		i.putExtra("thumbPath", TEST_IMAGE);
		// appPath�Ǵ�����Ӧ�ó���ı���·��������΢�ţ��������Ѻ�����Ȧ����ʹ�ã�������Բ��ṩ
		i.putExtra("appPath", TEST_IMAGE);
		// comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ�ã�������Բ��ṩ
		i.putExtra("comment", this.getString(R.string.share));
		// site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ�ã�������Բ��ṩ
		i.putExtra("site", this.getString(R.string.app_name));
		// siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ�ã�������Բ��ṩ
		i.putExtra("siteUrl", "http://sharesdk.cn");

		// �Ƿ�ֱ�ӷ���
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
		//����ShareSDK��ͳ�ƹ��ܲ��ͷ���Դ
		AbstractWeibo.stopSDK(getActivity());
		super.onDestroy();
	}
	
	*//**
	 * ��ʼ�������ͼƬ
	 *//*
	private void initImagePath() {
		try {//�ж�SD�����Ƿ���ڴ��ļ���
			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
					&& Environment.getExternalStorageDirectory().exists()) {
				TEST_IMAGE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/pic.png";
			}
			else {
				TEST_IMAGE = getActivity().getApplication().getFilesDir().getAbsolutePath() + "/pic.png";
			}
			File file = new File(TEST_IMAGE);
			//�ж�ͼƬ�Ƿ����ļ�����
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
		// �岥�ӿڵ���

		// ���ز岥��Դ
		SpotManager.getInstance(getActivity()).loadSpotAds();
		// �������ֶ���Ч����0:ANIM_NONEΪ�޶�����1:ANIM_SIMPLEΪ�򵥶���Ч����2:ANIM_ADVANCEΪ�߼�����Ч��
		SpotManager.getInstance(getActivity()).setAnimationType(
				SpotManager.ANIM_ADVANCE);
		// ���ò��������ĺ�����չʾ��ʽ����������˺����������й����Դ������»�������ʹ�ú���ͼ��
		SpotManager.getInstance(getActivity()).setSpotOrientation(
				SpotManager.ORIENTATION_PORTRAIT);
		
		spotBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// չʾ�岥��棬���Բ�����loadSpot����ʹ��
				SpotManager.getInstance(getActivity()).showSpotAds(getActivity(),
						new SpotDialogListener() {
							@Override
							public void onShowSuccess() {
								Log.i("YoumiAdDemo", "չʾ�ɹ�");
							}

							@Override
							public void onShowFailed() {
								Log.i("YoumiAdDemo", "չʾʧ��");
							}

							@Override
							public void onSpotClosed() {
								Log.i("YoumiAdDemo", "չʾ�ر�");
							}

							@Override
							public void onSpotClick() {
								Log.i("YoumiAdDemo", "�������");
							}

						}); // //

			}
		});
	}
	
	
	public MenuRightFragment() {
		 
	}
}
*/