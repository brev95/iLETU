package com.example.iletu;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/*
 * custom onclicklistener for items in list of links for online links fragment
 */
public class LinkListListener implements OnItemClickListener {
	List<LinkItem> listItems;
    Activity activity;
	
	public LinkListListener(List<LinkItem> items, Activity anActivity) {
		activity = anActivity;
		listItems = items;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		//opens webview to imleagues if intramurals item is clicked
		if(pos == 5) {
			Intent i = new Intent(view.getContext(), IntramuralActivity.class);
			activity.startActivity(i);
		} 
		
		//opens new activity with webview of website
		else {
			Intent i = new Intent(view.getContext(), WebViewActivity.class);
	        //sends item title and url to new activity
			i.putExtra("title", listItems.get(pos).title);
	        i.putExtra("url", listItems.get(pos).url);
	        //starts new webview activity
	        activity.startActivity(i);
		}
	}
}
