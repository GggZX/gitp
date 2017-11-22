package com.xzc.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CollMyHelper extends SQLiteOpenHelper {
	String CREATE = "create table tb_collection(id integer primary key,menuId text,menuName text,img text,step text,tags text,name text)";

	public CollMyHelper(Context context) {
		super(context, "collections.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE);
		Log.i("Cretae", "oncreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
