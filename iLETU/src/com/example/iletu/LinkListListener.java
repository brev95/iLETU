package com.example.iletu;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class LinkListListener implements OnItemClickListener {
	List<LinkItem> listItems;
    Activity activity;
	
	public LinkListListener(List<LinkItem> items, Activity anActivity) {
		activity = anActivity;
		listItems = items;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
