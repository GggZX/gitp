package com.xzc.utils;

import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import com.xzc.entities.Collections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 收藏的数据库操作
 * 
 * @author Administrator
 * 
 */
public class CollDataBaseUtils {
	CollMyHelper helper;
	SQLiteDatabase db;
	Context con;

	/**
	 * 初始化数据操作类
	 * 
	 * @param con
	 */
	public CollDataBaseUtils(Context con) {
		// TODO Auto-generated constructor stub
		this.con = con;
		helper = new CollMyHelper(con);
	}

	/**
	 * 添加收藏
	 */
	public void addmenuId(Collections coll) {
		db = helper.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("menuId", coll.getMenuId());
		values.put("menuName", coll.getMenuName());
		values.put("step", coll.getStep());
		values.put("img", coll.getImg());
		values.put("tags", coll.getTags());
		values.put("name", coll.getName());
		db.insert("tb_collection", null, values);
		db.close();
	}

	/**
	 * 按名字查看收藏
	 * 
	 * @return
	 */
	public List<Collections> queryColl(String name) {
		db = helper.getReadableDatabase();
		List<Collections> list = new ArrayList<Collections>();
		Cursor c = db.query("tb_collection", null, "name=?",
				new String[] { name }, null, null, null);
		while (c.moveToNext()) {
			Collections coll = new Collections();
			coll.setId(c.getInt(0));
			coll.setMenuId(c.getString(1));
			coll.setMenuName(c.getString(2));
			coll.setImg(c.getString(3));
			coll.setStep(c.getString(4));
			coll.setTags(c.getString(5));
			coll.setName(c.getString(6));
			list.add(coll);
		}
		db.close();
		return list;
	}

	/**
	 * 按名称和菜单id查询
	 * 
	 * @param name
	 * @param menuId
	 * @return
	 */
	public boolean queryByNameMenuId(String name, String menuId) {
		db = helper.getReadableDatabase();
		List<Collections> list = new ArrayList<Collections>();
		Cursor c = db.query("tb_collection", null, "menuId=? and name=?",
				new String[] { menuId, name }, null, null, null);
		if (c.getCount() == 0) {
			db.close();
			return true;
		} else {
			db.close();
			return false;
		}

	}

	/**
	 * 按menuId删除收藏
	 */
	public int removeColl(String menuId,String name) {
		db = helper.getReadableDatabase();
		int i=db.delete("tb_collection", "menuId=? and name=?", new String[] { menuId,name });
		db.close();
		return i;
	}
	/**
	 * 按用户名删除
	 */
	public void removeCollByName(String name){
		db = helper.getReadableDatabase();
		int i=db.delete("tb_collection", "name=?", new String[] { name });
		db.close();
	}
}
