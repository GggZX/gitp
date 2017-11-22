package com.xzc.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class EmailLinkTextview extends TextView{

	public EmailLinkTextview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public EmailLinkTextview(Context context, AttributeSet attrs){
		super(context, attrs);
	}
	
    public EmailLinkTextview(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
    }  
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	 try {
             return super.onTouchEvent(event);
         } catch (Exception e) {
             Toast.makeText(getContext(), "无有效应用打开Email", 1000).show();
         }
         return false;
    }

}
