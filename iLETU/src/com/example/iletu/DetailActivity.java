package com.example.iletu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity{
	Intent intent;
	TextView tView, dView, lView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_activity);
		intent = getIntent();
		
		tView = (TextView) findViewById(R.id.title);
		dView = (TextView) findViewById(R.id.details);
		//lView = (TextView) findViewById(R.id.link);
		
		tView.setText(intent.getExtras().getString("title"));
		dView.setText(intent.getExtras().getString("description"));
		//lView.setText(intent.getExtras().getString("link"));
	}
}
