package com.example.iletu;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class IntramuralActivity extends Activity{
	private String url = "http://www.imleagues.com/School/Portal.aspx?SchID=39d7b8cb08db465ab4f27a061607a7c4&Portal=SchOfDay";
	String html;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_activity);
		WebView wv = (WebView)findViewById(R.id.webview);
		wv.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            return (false);
	        }
		});
		wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setSupportZoom(true);
		wv.getSettings().setJavaScriptEnabled(true);
		if (html == null) {
	        org.jsoup.nodes.Document doc;
			try {
				doc = Jsoup.connect(url).get();
				Elements ele = doc.select("div#divGameSearchResult");
				String html = ele.toString();
				String mime = "text/html";
				String encoding = "utf-8";
				wv.loadData(html, mime, encoding);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
        	String mime = "text/html";
			String encoding = "utf-8";
			wv.loadData(html, mime, encoding);
        }
	}
}
