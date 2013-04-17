package com.example.iletu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class IntramuralFragment extends Fragment{
	private String url = "http://www.imleages.com";
	private WebView wv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = (View)inflater.inflate(R.layout.web_view_fragment, container, false);
		wv = (WebView) view.findViewById(R.id.webview);
		wv.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            return (false);
	        }
		});
		wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setSupportZoom(true);
		wv.loadUrl(url);
		return view;
	}
}
