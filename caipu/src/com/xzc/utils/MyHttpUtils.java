package com.xzc.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.view.Menu;
import com.google.gson.Gson;
import com.xzc.entities.AllInfo;
import com.xzc.entities.CateInfo;
import com.xzc.entities.CateList;
import com.xzc.entities.Category;
import com.xzc.entities.MenuData;
import com.xzc.entities.MenuResult;
import com.xzc.entities.MenuResultById;
import com.xzc.entities.MenuSteps;

public class MyHttpUtils {
	/**
	 * 获取Json数据的result
	 * 
	 * @param url
	 * @return 返回json数据的结果
	 */
	public String getRes(String url) {
		String result = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public AllInfo getInfo(String result) {
		Gson gson = new Gson();
		AllInfo info = gson.fromJson(result, AllInfo.class);
		return info;
	}

	/**
	 * 根据传来的result数据解析得到菜的类型
	 * 
	 * @param result
	 * @return 返回List<MenuData>
	 */
	public List<MenuData> getMenuList(String result) {
		List<MenuData> list = new ArrayList<MenuData>();
		Gson gson = new Gson();
		AllInfo all = gson.fromJson(result, AllInfo.class);
		list = all.getResult().getData();
		return list;
	}

	/**
	 * 获取做菜的步骤
	 * 
	 * @param result json数据
	 * @param pos 父控件的位置
	 * @return 获得的List<MenuSteps>
	 */
	public List<MenuSteps> getMenuSteps(String result, int pos) {
		List<MenuSteps> list = new ArrayList<MenuSteps>();
		Gson gson = new Gson();
		AllInfo all = gson.fromJson(result, AllInfo.class);
		list = all.getResult().getData().get(pos).getSteps();
		return list;
	}

	/**
	 * 获取按id查询到的菜
	 * 
	 * @param result
	 *            解析得到的result数据
	 * @return 返回id查询大的菜的LIst
	 */
	public List<MenuData> getMenuData(String result) {
		Gson gson = new Gson();
		List<MenuData> menuList = new ArrayList<MenuData>();
		AllInfo all = gson.fromJson(result, AllInfo.class);
		menuList = all.getResult().getData();
		return menuList;
	}

	/**
	 * 获得菜的类型的List方法
	 * 
	 * @param result
	 *            Json数据
	 * @return 返回List<CateList>
	 */
	public List<CateList> getCategoryList(String result) {
		List<CateList> list = new ArrayList<CateList>();
		Gson gson = new Gson();
		CateInfo all = gson.fromJson(result, CateInfo.class);
		list = all.getResult();
		return list;
	}

	/**
	 * 获取细分的菜的类型列表
	 * 
	 * @param result
	 *            Json数据
	 * @param pos
	 *            父控件的位置
	 * @return 返回List<Category>
	 */
	public List<Category> getCategory(String result, int pos) {
		List<Category> list = new ArrayList<Category>();
		Gson gson = new Gson();
		CateInfo all = gson.fromJson(result, CateInfo.class);
		list = all.getResult().get(pos).getList();
		return list;
	}
}
