package com.xzc.utils;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.xzc.entities.UserInfo;

public class DBOprerator {
	
	//��ѯ���ݿ�ȫ������
	public static ArrayList<UserInfo> chazhao(SQLiteDatabase db){
		ArrayList<UserInfo> list=new ArrayList<UserInfo>();
		Cursor cursor=db.rawQuery("select * from tb_jishiben", null);
		while(cursor.moveToNext()){
			int index=0;
			UserInfo info=new UserInfo();
			info.setId(cursor.getInt(index++));
			info.setUsername(cursor.getString(index++));
			info.setPassword(cursor.getString(index++));
			list.add(info);
		}
		db.close();
		return list;
	}
	
	//�����ݿ����������
	public static void adde(SQLiteDatabase db,UserInfo info){
		ContentValues values=new ContentValues();
		values.put("_username", info.getUsername());
		values.put("_passworld", info.getPassword());
		db.insert("tb_user", null, values);
	} 
	
	//�޸����ݿ�Ķ���
	public static void xiugai(SQLiteDatabase db,UserInfo info){
		ContentValues values=new ContentValues();
		values.put("_username", info.getUsername());
		values.put("_passworld", info.getPassword());
		db.update("tb_user", values, "_id=?", new String[]{""+info.getId()});
		db.close();
	}
	
	//ɾ�����ݿ��е�ĳ������
	public static void delete(SQLiteDatabase db,UserInfo info){
		db.delete("tb_user", "_id=?", new String[]{""+info.getId()});
		db.close();
	}
}
