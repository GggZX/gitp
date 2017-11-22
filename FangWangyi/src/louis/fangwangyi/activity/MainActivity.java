package louis.fangwangyi.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import louia.fangwangyi.activity.R;
import louis.fangwangyi.adapter.AreaAdapter;
import louis.fangwangyi.adapter.NewListAdapter;
import louis.fangwangyi.adapter.ShouyeAdapter;
import louis.fangwangyi.info.Info_news_area;
import louis.fangwangyi.info.Info_shouye;
import louis.fangwangyi.util.CircularImage;
import louis.fangwangyi.util.CommonUtil;
import louis.fangwangyi.util.HttpUtils;
import louis.fangwangyi.view.AdvViewPager;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity implements OnClickListener {

	public static final int HTTP_REQUEST_SUCCESS = -1;
	public static final int HTTP_REQUEST_ERROR = 0;

	private ViewPager vpViewPager = null;
	private List<View> views = null;

	private int offset; // ���
	private int cursorWidth; // �α�ĳ���
	private int originalIndex = 0;
	private ImageView cursor = null;
	private CircularImage circularImage=null;

	private Animation animation = null;

	private PullToRefreshListView ptrlvHeadLineNews = null;
	private PullToRefreshListView ptrlvEntertainmentNews = null;
	private PullToRefreshListView ptrlvFinanceNews = null;
	private PullToRefreshListView ptrlvlist4 = null;
	private NewListAdapter newAdapter = null;
	private SlidingMenu slidingMenu = null;
	private Timer timer = null;
	private TimerTask timeTask = null;
	private boolean isExit = false; // ����Ƿ�Ҫ�˳�
	private AdvViewPager vpAdv = null;
	private ViewGroup vg = null;
	private ImageView[] imageViews = null;
	private List<View> advs = null;
	private int currentPage = 0;
	String url1=null;
	ArrayList<Info_shouye> list;
	ArrayList<Info_news_area> list1;
	HttpUtils httpUtils;
	String result1 = "",result2="";
	String url2=null;
	int s=10, s1=10,s2=10,s3=10;
	
//	ShouyeAdapter adapter;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			result1=(String) msg.obj;
			list = getShouye(result1);
			Log.i("vv", "____________list_in-handler___________" + result1);
			ShouyeAdapter	 adapter = new ShouyeAdapter(MainActivity.this, list);
			ptrlvHeadLineNews.setAdapter(adapter);
			
		}
	};
	
	Handler handler2=new Handler(){
		public void handleMessage(Message msg) {
			result2=(String) msg.obj;
			list1=getShouye1(result2);
			AreaAdapter areaAdapter=new AreaAdapter(MainActivity.this, list1);
			ptrlvEntertainmentNews.setAdapter(areaAdapter);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		httpUtils = new HttpUtils();
		setContentView(R.layout.activity_main);
//          adapter=new ShouyeAdapter();
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		String d = simpleDateFormat.format(date);
		url1 = "https://route.showapi.com/738-1?n="+s+"&showapi_appid=8539&showapi_timestamp="
				+ d + "&showapi_sign=804c7bf586024c72ae3533295fb49ed3";
        url2="https://route.showapi.com/170-47?areaId=&areaName=北京&page=1&showapi_appid=8539&showapi_timestamp="+d+"&title=&showapi_sign=804c7bf586024c72ae3533295fb49ed3";
		timer = new Timer();

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				result1 = httpUtils.getResult(url1);
     
				Log.i("2222", "--------result1-in-thread------"+result2);
				Message msg = new Message();
				msg.obj = result1;
				handler.sendMessage(msg);
			}
		});
		thread.start();
		
		
		Thread thread2=new Thread(new Runnable() {
			public void run() {
				result2=httpUtils.getResult(url2);
				Log.i("result2", "------result2in-thread2-------"+result2);
				list1=getShouye1(result2);
				Message msg = new Message();
				msg.obj = result2;
				handler2.sendMessage(msg);
			}
		});
		thread2.start();

		findViewById(R.id.bNew).setOnClickListener(this);// ��˵�����
		findViewById(R.id.bPersonal).setOnClickListener(this);// �ҵİ���
		((TextView) findViewById(R.id.tvTag1)).setOnClickListener(this);// ͷ��
		((TextView) findViewById(R.id.tvTag2)).setOnClickListener(this);// ����
		((TextView) findViewById(R.id.tvTag3)).setOnClickListener(this);// �ƾ�
		((TextView) findViewById(R.id.tvTag4)).setOnClickListener(this);
		// List<View> views ��Ӳ���
		views = new ArrayList<View>();
		views.add(LayoutInflater.from(this).inflate(R.layout.head_lines, null));// ͷ��listview
		views.add(LayoutInflater.from(this).inflate(R.layout.entertainment,// ����Listview
				null));
		views.add(LayoutInflater.from(this).inflate(R.layout.finance, null));// �ƾ�Listview
		views.add(LayoutInflater.from(this).inflate(R.layout.frame_4, null));
		vpViewPager = (ViewPager) findViewById(R.id.vpViewPager1);
		vpViewPager.setAdapter(new MyPagerAdapter(views));
		vpViewPager.setOnPageChangeListener(new MyOnPageChangeListener());

		initCursor(views.size());// ����view����������tag������

		// ���ó���˵�
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// ��˵�
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN); // �����߽��ϳ��˵�
		slidingMenu.setMenu(R.layout.slidingmenu_left);
		slidingMenu.setSecondaryMenu(R.layout.slidingmenu_right);// ��menu
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// ������˵�����ҳ���������
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		circularImage=(CircularImage) slidingMenu.findViewById(R.id.imageView11);
		circularImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "circularImage", 1).show();
				Intent intent=new Intent(MainActivity.this,LoginActivity.class);
			    startActivity(intent);
			}
		});

		MyPagerAdapter myPagerAdapter = (MyPagerAdapter) vpViewPager
				.getAdapter();
		View v1 = myPagerAdapter.getItemAtPosition(0);
		View v2 = myPagerAdapter.getItemAtPosition(1);
		View v3 = myPagerAdapter.getItemAtPosition(2);
		View v4 = myPagerAdapter.getItemAtPosition(3);
		ptrlvHeadLineNews = (PullToRefreshListView) v1
				.findViewById(R.id.ptrlvHeadLineNews);
		ptrlvEntertainmentNews = (PullToRefreshListView) v2
				.findViewById(R.id.ptrlvEntertainmentNews);
		ptrlvFinanceNews = (PullToRefreshListView) v3
				.findViewById(R.id.ptrlvFinanceNews);
		ptrlvlist4 = (PullToRefreshListView) v4
				.findViewById(R.id.ptrlvFinanceNews1);
//		newAdapter = new NewListAdapter(this, getSimulationNews(result1, 10));
		initPullToRefreshListView1(ptrlvHeadLineNews);
		initPullToRefreshListView1(ptrlvEntertainmentNews);
		initPullToRefreshListView1(ptrlvFinanceNews);
		initPullToRefreshListView1(ptrlvlist4);
		ptrlvHeadLineNews.setOnItemClickListener(clickListener);
	}
	
	OnItemClickListener clickListener=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,WebViewActivity.class);
			intent.putExtra("url", list.get(arg2-2).getUrl());
			startActivity(intent);
			
		}
	};

	// ��ҳ���ݽ���Json
	public ArrayList<Info_shouye> getShouye(String result) {
		ArrayList<Info_shouye> list = new ArrayList<Info_shouye>();
		try {
			JSONObject object = new JSONObject(result);
			String c = object.optString("showapi_res_body");
			Log.i("obj3", "------c-in-getShouye----------"+c);
			JSONObject object1 = new JSONObject(c);
			String c1=object1.optString("pagebean");
			JSONObject object3=new JSONObject(c);
			Log.i("obj3", "------------------"+object3);
			JSONArray array = object3.optJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				JSONObject object2 = array.optJSONObject(i);
				String url = object2.optString("url");
				String areaName = object2.optString("title");
				Info_shouye info = new Info_shouye();
				info.setTitle(areaName);
				Log.i("111111111111", "--------------------"+areaName);
				info.setUrl(url);
				list.add(info);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<Info_news_area> getShouye1(String result) {
		ArrayList<Info_news_area> list = new ArrayList<Info_news_area>();
		try {
			JSONObject object = new JSONObject(result);
			String c = object.optString("showapi_res_body");
			JSONObject object1 = new JSONObject(c);
			String c1=object1.optString("pagebean");
			JSONObject object2=new JSONObject(c1);
			JSONArray array = object2.optJSONArray("contentlist");
			Log.i("array", "---array---------"+array);
			String arr=object2.optString("contentlist");
			for (int i = 0; i < array.length(); i++) {
				JSONObject object3 = array.optJSONObject(i);
				String link = object3.optString("link");
				String areaName = object3.optString("areaName");
				String title=object3.optString("title");
				String desc=object3.optString("desc");
//				String imurl=object3.optString("imageurls");
				String update=object3.optString("pubDate");
				Info_news_area info = new Info_news_area();
				info.setTitle(title);
				info.setLink(link);
				info.setAreaName(areaName);
				info.setPubDate(update);
				info.setDesc(desc);
				list.add(info);
				Log.i("2222", "------desc--------"+desc);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void onBackPressed() {
		if (isExit) {
			finish();
		} else {
			isExit = true;
			Toast.makeText(MainActivity.this, "�ٰ�һ���˳���������", Toast.LENGTH_SHORT)
					.show();
			timeTask = new TimerTask() {

				@Override
				public void run() {
					isExit = false;
				}
			};
			timer.schedule(timeTask, 2000);
		}
	}

	// ������ǩ��viewpager����
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvTag1:
			vpViewPager.setCurrentItem(0);
			break;
		case R.id.tvTag2:
			vpViewPager.setCurrentItem(1);
			break;
		case R.id.tvTag3:
			vpViewPager.setCurrentItem(2);
			break;
		case R.id.tvTag4:
			vpViewPager.setCurrentItem(3);
			break;
		case R.id.bNew:
			slidingMenu.showMenu();
			break;
		case R.id.bPersonal:
			slidingMenu.showSecondaryMenu();
			break;
		}
	}

	public void click1(View v) {
		Toast.makeText(MainActivity.this, "����", Toast.LENGTH_SHORT).show();
		vpViewPager.setCurrentItem(0);
	}

	public void click2(View v) {
		Toast.makeText(MainActivity.this, "����", Toast.LENGTH_SHORT).show();
		vpViewPager.setCurrentItem(1);
	}

	public void click3(View v) {
		Toast.makeText(MainActivity.this, "����", Toast.LENGTH_SHORT).show();
		vpViewPager.setCurrentItem(3);
	}

	public void click4(View v) {
		Toast.makeText(MainActivity.this, "����", Toast.LENGTH_SHORT).show();
	}

	public void click5(View v) {
		Toast.makeText(MainActivity.this, "ͼƬ", Toast.LENGTH_SHORT).show();
	}

	/**
	 * ����tagd��������ʼ���α��λ��
	 * 
	 * @param tagNum
	 */
	public void initCursor(int tagNum) {
		cursorWidth = BitmapFactory.decodeResource(getResources(),
				R.drawable.cursor).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		offset = ((dm.widthPixels / tagNum) - cursorWidth) / 2;

		cursor = (ImageView) findViewById(R.id.ivCursor);
		Matrix matrix = new Matrix();
		matrix.setTranslate(offset, 0);
		cursor.setImageMatrix(matrix);
	}

	/**
	 * ��ȡN��ģ�����������<br>
	 * �����ArrayList����
	 * 
	 * @return
	 */
	/*public ArrayList<HashMap<String, String>> getSimulationNews(String result,
			int n) {
		ArrayList<HashMap<String, String>> ret = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hm;
		ArrayList<Info_shouye> list = new ArrayList<Info_shouye>();
		try {
			JSONObject object = new JSONObject(result);
			String c = object.optString("showapi_res_body");
			JSONObject object1 = new JSONObject(c);
			JSONArray array = object1.optJSONArray("list");
			n = array.length();
			for (int i = 0; i < n; i++) {
				hm = new HashMap<String, String>();
				JSONObject object2 = array.optJSONObject(i);
				String url = object2.optString("url");
				String areaName = object2.optString("title");
				hm.put("title", areaName);
				hm.put("uri", "http://images.china.cn/attachement/jpg/site1000/20131029/001fd04cfc4813d9af0118.jpg");
				hm.put("review", i + "����");
				ret.add(hm);
				Log.i("11111111111111", "+++++++++++++" + hm);
				
				 * Info_shouye info = new Info_shouye();
				 * info.setTitle(areaName); info.setUrl(url); list.add(info);
				 
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*
		 * for (int i = 0; i < n; i++) {
		 * 
		 * if (i % 2 == 0) { hm.put("uri",
		 * "http://images.china.cn/attachement/jpg/site1000/20131029/001fd04cfc4813d9af0118.jpg"
		 * ); } else { hm.put("uri",
		 * "http://photocdn.sohu.com/20131101/Img389373139.jpg"); }
		 * hm.put("title", "���ڳ�Ʒ�ͼ����������ɶ���"); hm.put("content",
		 * "���ڳ�Ʒ�ͽ���ӭ���۴��ڣ�����Ԥ��ÿ������0.1Ԫ��"); hm.put("review", i + "����");
		 * ret.add(hm); }
		
		return ret;
	} */
	
	public void initPullToRefreshListView1(PullToRefreshListView rtflv) {
		rtflv.setMode(Mode.BOTH);
		rtflv.setOnRefreshListener(new MyOnRefreshListener2(rtflv));

		if (rtflv.getId() == R.id.ptrlvHeadLineNews) {
			RelativeLayout rlAdv = (RelativeLayout) LayoutInflater.from(this)
					.inflate(R.layout.sliding_advertisement, null);
			vpAdv = (AdvViewPager) rlAdv.findViewById(R.id.vpAdv);
			vg = (ViewGroup) rlAdv.findViewById(R.id.viewGroup);
			// ����viewpagerͼƬ
			advs = new ArrayList<View>();
			ImageView iv;
			iv = new ImageView(this);
			iv.setBackgroundResource(R.drawable.new_img1);
			advs.add(iv);

			iv = new ImageView(this);
			iv.setBackgroundResource(R.drawable.new_img2);
			advs.add(iv);

			iv = new ImageView(this);
			iv.setBackgroundResource(R.drawable.new_img3);
			advs.add(iv);

			iv = new ImageView(this);
			iv.setBackgroundResource(R.drawable.new_img4);
			advs.add(iv);

			vpAdv.setAdapter(new AdvAdapter());
			vpAdv.setOnPageChangeListener(new OnPageChangeListener() {

				@Override
				public void onPageSelected(int arg0) {
					currentPage = arg0;
					for (int i = 0; i < advs.size(); i++) {
						if (i == arg0) {
							imageViews[i]
									.setBackgroundResource(R.drawable.banner_dian_focus);
						} else {
							imageViews[i]
									.setBackgroundResource(R.drawable.banner_dian_blur);
						}
					}
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {

				}

				@Override
				public void onPageScrollStateChanged(int arg0) {

				}
			});

			imageViews = new ImageView[advs.size()];
			ImageView imageView;
			for (int i = 0; i < advs.size(); i++) {
				imageView = new ImageView(this);
				imageView.setLayoutParams(new LayoutParams(20, 20));
				imageViews[i] = imageView;
				if (i == 0) {
					imageViews[i]
							.setBackgroundResource(R.drawable.banner_dian_focus);
				} else {
					imageViews[i]
							.setBackgroundResource(R.drawable.banner_dian_blur);
				}
				vg.addView(imageViews[i]);
			}

			rtflv.getRefreshableView().addHeaderView(rlAdv, null, false);

			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					vpAdv.setCurrentItem(msg.what);
					super.handleMessage(msg);
				}
			};
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(5000);
							currentPage++;
							if (currentPage > advs.size() - 1) {
								currentPage = 0;
							}
							handler.sendEmptyMessage(currentPage);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
	
		}
	}


	/**
	 * ��ʼ��PullToRefreshListView<br>
	 * ��ʼ����PullToRefreshListView�е�ViewPager�����
	 * 
	 * @param rtflv
	 * @param adapter
	 */
	/*public void initPullToRefreshListView(PullToRefreshListView rtflv,
			NewListAdapter adapter) {
		rtflv.setMode(Mode.BOTH);
		rtflv.setOnRefreshListener(new MyOnRefreshListener2(rtflv));
		rtflv.setAdapter(adapter);

		if (rtflv.getId() == R.id.ptrlvHeadLineNews) {
			RelativeLayout rlAdv = (RelativeLayout) LayoutInflater.from(this)
					.inflate(R.layout.sliding_advertisement, null);
			vpAdv = (AdvViewPager) rlAdv.findViewById(R.id.vpAdv);
			vg = (ViewGroup) rlAdv.findViewById(R.id.viewGroup);
			// ����viewpagerͼƬ
			advs = new ArrayList<View>();
			ImageView iv;
			iv = new ImageView(this);
			iv.setBackgroundResource(R.drawable.new_img1);
			advs.add(iv);

			iv = new ImageView(this);
			iv.setBackgroundResource(R.drawable.new_img2);
			advs.add(iv);

			iv = new ImageView(this);
			iv.setBackgroundResource(R.drawable.new_img3);
			advs.add(iv);

			iv = new ImageView(this);
			iv.setBackgroundResource(R.drawable.new_img4);
			advs.add(iv);

			vpAdv.setAdapter(new AdvAdapter());
			vpAdv.setOnPageChangeListener(new OnPageChangeListener() {

				@Override
				public void onPageSelected(int arg0) {
					currentPage = arg0;
					for (int i = 0; i < advs.size(); i++) {
						if (i == arg0) {
							imageViews[i]
									.setBackgroundResource(R.drawable.banner_dian_focus);
						} else {
							imageViews[i]
									.setBackgroundResource(R.drawable.banner_dian_blur);
						}
					}
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {

				}

				@Override
				public void onPageScrollStateChanged(int arg0) {

				}
			});

			imageViews = new ImageView[advs.size()];
			ImageView imageView;
			for (int i = 0; i < advs.size(); i++) {
				imageView = new ImageView(this);
				imageView.setLayoutParams(new LayoutParams(20, 20));
				imageViews[i] = imageView;
				if (i == 0) {
					imageViews[i]
							.setBackgroundResource(R.drawable.banner_dian_focus);
				} else {
					imageViews[i]
							.setBackgroundResource(R.drawable.banner_dian_blur);
				}
				vg.addView(imageViews[i]);
			}

			rtflv.getRefreshableView().addHeaderView(rlAdv, null, false);

			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					vpAdv.setCurrentItem(msg.what);
					super.handleMessage(msg);
				}
			};
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(5000);
							currentPage++;
							if (currentPage > advs.size() - 1) {
								currentPage = 0;
							}
							handler.sendEmptyMessage(currentPage);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}*/

	class MyOnRefreshListener2 implements OnRefreshListener2<ListView> {

		private PullToRefreshListView mPtflv;

		public MyOnRefreshListener2(PullToRefreshListView ptflv) {
			this.mPtflv = ptflv;
		}

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			// ����ˢ��
			String label = DateUtils.formatDateTime(getApplicationContext(),
					System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
							| DateUtils.FORMAT_SHOW_DATE
							| DateUtils.FORMAT_ABBREV_ALL);

			refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
			new GetNewsTask(mPtflv).execute();

		}

		@Override
		public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
			// ��������
			new GetNewsTask(mPtflv).execute();
		}

	}

	class MyPagerAdapter extends PagerAdapter {

		private List<View> views;

		public MyPagerAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			// ��ָ����view��viewPager���Ƴ�
			((ViewPager) container).removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			// ��view��ӵ�viewPager��
			((ViewPager) container).addView(views.get(position));
			return views.get(position);
		}

		public View getItemAtPosition(int position) {
			return views.get(position);
		}

	}


	class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			int one = 2 * offset + cursorWidth;
			int two = one * 2;
			int three = one * 3;
			switch (originalIndex) {
			case 0:
				if (arg0 == 1) {
					animation = new TranslateAnimation(0, one, 0, 0);
				}
				if (arg0 == 2) {
					animation = new TranslateAnimation(0, two, 0, 0);
				}
				if (arg0 == 3) {
					animation = new TranslateAnimation(0, three, 0, 0);
				}
				break;
			case 1:
				if (arg0 == 0) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				}
				if (arg0 == 2) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				if (arg0 == 3) {
					animation = new TranslateAnimation(one, three, 0, 0);
				}
				break;
			case 2:
				if (arg0 == 1) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				if (arg0 == 0) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				if (arg0 == 3) {
					animation = new TranslateAnimation(two, three, 0, 0);
				}
				break;
			case 3:
				if (arg0 == 1) {
					animation = new TranslateAnimation(three, one, 0, 0);
				}
				if (arg0 == 0) {
					animation = new TranslateAnimation(three, 0, 0, 0);
				}
				if (arg0 == 2) {
					animation = new TranslateAnimation(three, two, 0, 0);
				}
				break;
			}
			animation.setFillAfter(true);
			animation.setDuration(300);
			cursor.startAnimation(animation);
			originalIndex = arg0;
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

	}

	/**
	 * ����������������Ϣ
	 * 
	 * @author Louis
	 * 
	 */
	class GetNewsTask extends AsyncTask<String, Void, Integer> {
		private PullToRefreshListView mPtrlv;

		public GetNewsTask(PullToRefreshListView ptrlv) {
			this.mPtrlv = ptrlv;
		}

		@Override
		protected Integer doInBackground(String... params) {
			if (CommonUtil.isWifiConnected(MainActivity.this)) {
				try {
					Thread.sleep(1000);
					return HTTP_REQUEST_SUCCESS;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return HTTP_REQUEST_ERROR;
		}
        
		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			switch (result) {
			case HTTP_REQUEST_SUCCESS:
				
				Toast.makeText(MainActivity.this, "�W�j�B�ӳɹ�", Toast.LENGTH_SHORT).show();
               if (mPtrlv.getId()==R.id.ptrlvHeadLineNews) {
//===========================			
				Thread thread = new Thread(new Runnable() {
					public void run() {
						s=s+3;
						Date date = new Date();
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
								"yyyyMMddHHmmss");
						String d = simpleDateFormat.format(date);
						url1 = "https://route.showapi.com/738-1?n="+s+"&showapi_appid=8539&showapi_timestamp="
								+ d + "&showapi_sign=804c7bf586024c72ae3533295fb49ed3";
						 result1 = httpUtils.getResult(url1);
						
//						newAdapter.addNews(getSimulationNews(resu, 10));
						// newAdapter.notifyDataSetChanged();
					}
				});
				thread.start();
}
               if (mPtrlv.getId()==R.id.ptrlvEntertainmentNews) {
				Thread  thread=new Thread(new Runnable() {
					public void run() {
						Date date = new Date();
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
								"yyyyMMddHHmmss");
						String d = simpleDateFormat.format(date);
						url2 = "https://route.showapi.com/170-47?areaId=&areaName=����&page=1&showapi_appid=8539&showapi_timestamp="+d+"&title=&showapi_sign=804c7bf586024c72ae3533295fb49ed3";
						 result2 = httpUtils.getResult(url2);
						 Log.i("result2", "-------------result2---------"+result2);
					}
				});
				thread.start();
			}
               Message msg = new Message();
				msg.obj = result1;
				handler.sendMessage(msg);
				break;
			case HTTP_REQUEST_ERROR:
				Toast.makeText(MainActivity.this, "��������", Toast.LENGTH_SHORT)
						.show();
				break;
			}
			mPtrlv.onRefreshComplete();
		}

	}

	class AdvAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return advs.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(advs.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(advs.get(position));
			return advs.get(position);
		}
	}
}
