package com.xzc.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyCommentHelper extends SQLiteOpenHelper {
	String CREATE = "create table tb_comment(id integer primary key,username text,pushtime text,comments text,img text,zan integer,ping integer,type integer)";

	public MyCommentHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "comment.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
