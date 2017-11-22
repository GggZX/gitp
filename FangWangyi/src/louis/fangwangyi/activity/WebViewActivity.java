package louis.fangwangyi.activity;

import louia.fangwangyi.activity.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity{
	WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.web);
    	Intent intent=getIntent();
    	String url=intent.getStringExtra("url");
    	webView=(WebView) this.findViewById(R.id.webview);
    	Toast.makeText(WebViewActivity.this, url, Toast.LENGTH_LONG).show();
       webView.loadUrl(url);
       webView.setWebViewClient(new WebViewClient(){
           @Override
           public boolean shouldOverrideUrlLoading(WebView view, String url){
                    
                   return false;
                    
           }
   });
    }
    
}
