package com.example.iletu;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * this class is opened whenever a calendar or campus bullet item from listview is clicked.
 * it contains a view for a title, details, and an imageview for a campus bullet picture
 */
public class DetailActivity extends Activity{
	Intent intent;
	TextView tView, dView, lView;
	ImageView imgView;
	String link, postNum, baseUrl, finalUrl;
	Drawable drawable;
	URL url;
    InputStream in = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_activity);
		intent = getIntent();
		
		//inflates the textviews from the activity layout xml
		tView = (TextView) findViewById(R.id.title);
		dView = (TextView) findViewById(R.id.details);
		
		//gets title, link, and description from the listener that called it
		tView.setText(intent.getExtras().getString("title"));
		dView.setText(intent.getExtras().getString("description"));
		link = intent.getExtras().getString("link");
		
		//initializes imageview and gets the post number of the campus bullet item
		imgView =(ImageView)findViewById(R.id.image_view);
		baseUrl= "http://www.campusbullet.net/images/posts/?q=";
		postNum = link.substring(38);
		
		try {
			//tries to get the image of an item from campus bullet
			finalUrl = baseUrl+postNum+"-1";
			url = new URL(finalUrl);
			if(URLUtil.isValidUrl(finalUrl)); {
				drawable =LoadImageFromWeb(url);
				imgView.setImageDrawable(drawable);	
			}
		} catch(MalformedURLException e) {
			System.out.println("Exc= "+e);
		}
	}
	
	//destroys activity on backpress to save memory
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		super.onDestroy();
	}
	
	//creates a drawable object from the url to post image 
	private Drawable LoadImageFromWeb(URL url) {
		try {
			in = (InputStream)url.getContent();
			Drawable d = Drawable.createFromStream(in, "src name");
			return d;
		} catch(Exception e) {
			return null;
		}
	}
}
