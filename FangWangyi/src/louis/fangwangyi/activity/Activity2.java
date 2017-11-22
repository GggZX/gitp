package louis.fangwangyi.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import louia.fangwangyi.activity.R;
import louis.fangwangyi.info.Info_province;
import louis.fangwangyi.util.HttpUtils;
import louis.fangwangyi.util.test;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Activity2 extends Activity{
	HttpUtils province;
	Info_province info_province;
	String url=null;
	test t;
   @Override
protected void onCreate(Bundle savedInstanceState) {
	   Date date = new Date();
	   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	   String d = simpleDateFormat.format(date);
//	   url="http://route.showapi.com/170-48"+ "appid"+"secret";
	   url="https://route.showapi.com/170-48?showapi_appid=8539&showapi_timestamp="+d+"&showapi_sign=804c7bf586024c72ae3533295fb49ed3";
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_2);
	info_province=new Info_province();
	province=new HttpUtils();
	t=new test();
	Thread thread=new Thread(new Runnable() {
		public void run() {
			String s=province.getResult(url);
			    t.getCity(s);
				Log.i("aaa", t.getCity(s).get(0).getAreaName()+"");
				
		}
	});
	thread.start();
	
}
}
