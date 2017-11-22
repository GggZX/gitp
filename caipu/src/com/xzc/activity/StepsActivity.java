package com.xzc.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.AbstractWeibo;
import cn.sharesdk.onekeyshare.ShareAllGird;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xzc.adapter.BurdenAdapter;
import com.xzc.adapter.IngredientsAdapter;
import com.xzc.adapter.StepsAdapter;
import com.xzc.entities.Collections;
import com.xzc.entities.MenuData;
import com.xzc.entities.MenuSteps;
import com.xzc.utils.ACacheUtils;
import com.xzc.utils.AppKey;
import com.xzc.utils.AsynListView;
import com.xzc.utils.CollDataBaseUtils;
import com.xzc.utils.ImageLoaderOptionsUtils;
import com.xzc.utils.MyHttpUtils;

import android.R.menu;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StepsActivity extends Activity {
	int flag = 0;
	ImageLoaderOptionsUtils imageUtils;
	DisplayImageOptions options;
	ImageButton btn_back, btn_coll;
	MyHttpUtils utils;
	CollDataBaseUtils dbUtils;
	Collections coll;
	Collections history;
	String result;
	String stepId;
	ACacheUtils acache;
	ImageView steps_img_header;
	TextView steps_tv_title, steps_tv_imtro;
	AsynListView steps_list_main,//主要食材
	             steps_list_support,//你要准备
	             steps_listview;//开始做菜
	ImageLoader imageLoader;
	MenuData menuData;
	List<MenuData> list;
	List<String> ingredientsList;
	List<String> burdenList;
	List<MenuSteps> listSteps;
	BurdenAdapter burdenAdapter;
	StepsAdapter stepsAdapter;
	ImageButton steps_share;
	Intent in;
	IngredientsAdapter ingredientsAdapter;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			list = (List<MenuData>) msg.obj;
			menuData = list.get(0);
			String ingredients = menuData.getIngredients();
			Log.i("ingred", "==========" + ingredients);
			for (String str : ingredients.split(";")) {
				ingredientsList.add(str);
			}
			String burden = menuData.getBurden();
			for (String strBurden : burden.split(";")) {
				burdenList.add(strBurden);
			}
			listSteps = menuData.getSteps();
			ingredientsAdapter = new IngredientsAdapter(StepsActivity.this,
					ingredientsList);
			steps_list_main.setAdapter(ingredientsAdapter);
			burdenAdapter = new BurdenAdapter(StepsActivity.this, burdenList);
			steps_list_support.setAdapter(burdenAdapter);
			stepsAdapter = new StepsAdapter(StepsActivity.this, listSteps);
			steps_listview.setAdapter(stepsAdapter);
			steps_listview.setOnItemClickListener(itemClick);
			imageLoader.init(ImageLoaderConfiguration
					.createDefault(StepsActivity.this));
			steps_tv_title.setText(menuData.getTitle());
			imageLoader.displayImage(menuData.getAlbums().get(0),
					steps_img_header, options);
			steps_tv_imtro.setText(menuData.getImtro());
			addsMenuId();
			if (dbUtils.queryByNameMenuId("小当家", menuData.getId())) {

			} else {
				flag = 1;
				btn_coll.setImageResource(R.drawable.btn_collected_recipe_selected);
			}
			if (dbUtils.queryByNameMenuId("history", menuData.getId())) {
				addHistory();
				dbUtils.addmenuId(history);
			} else {

			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.steps_layout);
		imageUtils = new ImageLoaderOptionsUtils();
		options = imageUtils.init();
		dbUtils = new CollDataBaseUtils(this);
		coll = new Collections();
		acache = ACacheUtils.get(this, "queryid");
		in = getIntent();
		stepId = in.getStringExtra("stepsId");
		final String URL = "http://apis.juhe.cn/cook/queryid?key=" + AppKey.KEY
				+ "&id=" + stepId + "";
		steps_share = (ImageButton) findViewById(R.id.steps_share);
		steps_share.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showGrid(false);
			}
		});
		AbstractWeibo.initSDK(this);

		initImagePath();
		init();
		btn_coll.setOnClickListener(click);
		btn_back.setOnClickListener(click);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if ((acache.getAsString("result" + stepId)) == null) {
					result = utils.getRes(URL);
					acache.put("result" + stepId, result, acache.TIME_DAY);
				} else {
					result = acache.getAsString("result" + stepId);
					Log.i("acacheQUERYID", result + "");
				}
				list = utils.getMenuData(result);
				Message msg = new Message();
				msg.obj = list;
				handler.sendMessage(msg);
			}
		}).start();
	}

	OnItemClickListener itemClick = new OnItemClickListener() {//开始做点点击事件 转至图片模式

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent in = new Intent(StepsActivity.this, StepsImgActivity.class);
			in.putExtra("listSteps", (Serializable) listSteps);
			in.putExtra("current", arg2);
			startActivity(in);
		}
	};
	OnClickListener click = new OnClickListener() {

		@SuppressLint("ShowToast")
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.steps_btn_back:
				finish();
				break;
			case R.id.steps_btn_coll:
				switch (flag) {
				case 0:
					dbUtils.addmenuId(coll);
					Toast.makeText(StepsActivity.this, "收藏成功", 0).show();
					btn_coll.setImageResource(R.drawable.btn_collected_recipe_selected);
					flag = 1;
					break;
				case 1:
					int i = dbUtils.removeColl(coll.getMenuId(), "小当家");
					Toast.makeText(StepsActivity.this, "取消收藏" + i, 0).show();
					btn_coll.setImageResource(R.drawable.btn_collected_recipe_normal);
					flag = 0;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}
	};

	private void addHistory() {
		// TODO Auto-generated method stub
		history = new Collections();
		history.setImg(menuData.getAlbums().get(0));
		history.setMenuId(menuData.getId());
		history.setMenuName(menuData.getTitle());
		history.setStep(menuData.getSteps().size()+"");
		history.setTags(menuData.getTags());
		history.setName("history");
	}

	private void addsMenuId() {
		// TODO Auto-generated method stub
		coll.setMenuId(menuData.getId());
		coll.setMenuName(menuData.getTitle());
		coll.setImg(menuData.getAlbums().get(0));
		coll.setStep(menuData.getSteps().size()+"");
		coll.setTags(menuData.getTags());
		coll.setName("小当家");
	}

	private void init() {
		// TODO Auto-generated method stub
		btn_back = (ImageButton) findViewById(R.id.steps_btn_back);
		utils = new MyHttpUtils();
		steps_img_header = (ImageView) findViewById(R.id.steps_img_header);
		btn_coll = (ImageButton) findViewById(R.id.steps_btn_coll);
		steps_tv_imtro = (TextView) findViewById(R.id.steps_tv_imtro);
		steps_tv_title = (TextView) findViewById(R.id.steps_tv_title);
		steps_list_main = (AsynListView) findViewById(R.id.steps_list_main);
		steps_list_support = (AsynListView) findViewById(R.id.steps_list_support);
		steps_listview = (AsynListView) findViewById(R.id.steps_listview);
		imageLoader = ImageLoader.getInstance();
		ingredientsList = new ArrayList<String>();
		burdenList = new ArrayList<String>();
	}

	public boolean shareApp() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
		intent.putExtra(Intent.EXTRA_TEXT,
				"I have successfully share my message through my app");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(intent, "跟我学做菜吧"));
		return true;

	}

	public static String TEST_IMAGE;

	private void showGrid(boolean silent) {
		Intent i = new Intent(this, ShareAllGird.class);
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

	private void initImagePath() {
		try {// 判断SD卡中是否存在此文件夹
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())
					&& Environment.getExternalStorageDirectory().exists()) {
				TEST_IMAGE = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/pic.png";
			} else {
				TEST_IMAGE = getApplication().getFilesDir().getAbsolutePath()
						+ "/pic.png";
			}
			File file = new File(TEST_IMAGE);
			// 判断图片是否存此文件夹中
			if (!file.exists()) {
				file.createNewFile();
				Bitmap pic = BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher);
				FileOutputStream fos = new FileOutputStream(file);
				pic.compress(CompressFormat.JPEG, 100, fos);
				fos.flush();
				fos.close();
			}
		} catch (Throwable t) {
			t.printStackTrace();
			TEST_IMAGE = null;
		}
	}

	public static String actionToString(int action) {
		switch (action) {
		case AbstractWeibo.ACTION_AUTHORIZING:
			return "ACTION_AUTHORIZING";
		case AbstractWeibo.ACTION_GETTING_FRIEND_LIST:
			return "ACTION_GETTING_FRIEND_LIST";
		case AbstractWeibo.ACTION_FOLLOWING_USER:
			return "ACTION_FOLLOWING_USER";
		case AbstractWeibo.ACTION_SENDING_DIRECT_MESSAGE:
			return "ACTION_SENDING_DIRECT_MESSAGE";
		case AbstractWeibo.ACTION_TIMELINE:
			return "ACTION_TIMELINE";
		case AbstractWeibo.ACTION_USER_INFOR:
			return "ACTION_USER_INFOR";
		case AbstractWeibo.ACTION_SHARE:
			return "ACTION_SHARE";
		default: {
			return "UNKNOWN";
		}
		}
	}
}
