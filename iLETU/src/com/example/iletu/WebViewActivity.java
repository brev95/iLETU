package com.example.iletu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {
	Intent intent;
	WebView wView;
	String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_activity);
		intent = getIntent();
		
		wView = (WebView) findViewById(R.id.webview);
		wView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            return (false);
	        }
		});
		wView.getSettings().setBuiltInZoomControls(true);
        wView.getSettings().setSupportZoom(true);
        wView.getSettings().setJavaScriptEnabled(true);
        wView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        
        wView.loadUrl(intent.getExtras().getString("url"));
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		super.onDestroy();
	}
}
