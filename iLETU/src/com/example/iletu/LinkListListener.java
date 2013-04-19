package com.example.iletu;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
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
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent i = new Intent(view.getContext(), WebViewActivity.class);
        i.putExtra("title", listItems.get(pos).title);
        i.putExtra("url", listItems.get(pos).url);
        activity.startActivity(i);
	}
}
