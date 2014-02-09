package com.example.iletu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/*
 * activity is called by links in the online fragment.
 * contains a webview that loads the url sent to it by the clicklistener of list items
 */
public class DetailWebActivity extends Activity {
	Intent intent;
	TextView tView;
	WebView wv;
	String link;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_web_activity);
		intent = getIntent();
		
		//inflates the text and weview from activity layout xml
		tView = (TextView) findViewById(R.id.title);
		wv = (WebView) findViewById(R.id.webview);
		
		//gets title from listener that called it
		tView.setText(intent.getExtras().getString("title"));
		
		//create the webview
		wv = (WebView) findViewById(R.id.webview);
		wv.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            return (false);
	        }
		});
        
        String mime = "text/html";
		String encoding = "utf-8";
        wv.loadData(intent.getExtras().getString("description"), mime, encoding);
	}
	
	//destroys the activity on backpress to save memory
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		super.onDestroy();
	}
}
