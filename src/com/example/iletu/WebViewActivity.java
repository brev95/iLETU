package com.example.iletu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/*
 * activity for viewing webpages.
 */
public class WebViewActivity extends Activity {
	Intent intent;
	WebView wView;
	String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//inflates webview activity layout xml
		setContentView(R.layout.web_view_activity);
		//gets intent from listener that called it
		intent = getIntent();
		//inflates webview from activity layout
		wView = (WebView) findViewById(R.id.webview);
		//keeps activity from trying to open phone's default browser
		wView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            return (false);
	        }
		});
		//allows javascript and zooming for the webview
		wView.getSettings().setBuiltInZoomControls(true);
        wView.getSettings().setSupportZoom(true);
        wView.getSettings().setJavaScriptEnabled(true);
        wView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //gets the url of the list item that was clicked, so it can load the right page
        wView.loadUrl(intent.getExtras().getString("url"));
	}
	
	//destroys the activity on back press to save memory
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		super.onDestroy();
	}
}
