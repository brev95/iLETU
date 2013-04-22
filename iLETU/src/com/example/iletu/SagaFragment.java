package com.example.iletu;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SagaFragment extends Fragment {
	private String url = "http://www.cafebonappetit.com/menu/your-cafe/letu/cafes/details/147";
	private WebView wv;
	private String html;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//set up the view and the webview
		View view = (View)inflater.inflate(R.layout.web_view_fragment, container, false);
		wv = (WebView) view.findViewById(R.id.webview);
		wv.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            return (false);
	        }
		});
		
		//get the saga schedule from the website
		if (html == null) {
			org.jsoup.nodes.Document doc;
			try {
				doc = Jsoup.connect(url).get();
				Elements ele = doc.select("div#menu-items");
				html = ele.toString();
				String mime = "text/html";
				String encoding = "utf-8";
				//wv.loadData(html, mime, encoding);
				wv.loadUrl("file:///android_asset/newMenu.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String mime = "text/html";
			String encoding = "utf-8";
			//wv.loadData(html, mime, encoding);
			wv.loadUrl("file:///android_asset/newMenu.html");
		}
		return view;
	}
}
