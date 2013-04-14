package com.example.iletu;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FeedListAdapter extends BaseAdapter{
	private LayoutInflater li;
	private List<RssItem> messages = new ArrayList<RssItem>();
	
	public FeedListAdapter(Context context, List<RssItem> items){
		li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(items != null) {
			messages = items;
		}
	}

	@Override
	public int getCount() {
		//return messages.size();
		return 1;
	}

	@Override
	public Object getItem(int position) {
		//return messages.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		//return position;
		return 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		//final RssItem message = messages.get(position);
		if(v == null) {
			v = li.inflate(R.layout.news_feed_list, null);
		}
		final TextView mTitle = (TextView) v.findViewById(R.id.mLine1);
		//mTitle.setText(message.getTitle());
		mTitle.setText("test title");
		final TextView mAddress = (TextView) v.findViewById(R.id.mLine2);
		//mAddress.setText(message.getDescription());
		mAddress.setText("test description");
		return v;
	}

}
