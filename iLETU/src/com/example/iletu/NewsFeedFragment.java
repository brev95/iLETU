package com.example.iletu;

import java.util.List;

import android.support.v4.app.ListFragment;

public class NewsFeedFragment extends ListFragment{
	
	public void onStart() {
		super.onStart();
		CustomParser parser = new CustomParser("http://letustartpage.blogspot.com/feeds/posts/default?alt=rss");
		//CustomParser parser = new CustomParser("http://expertnotfound.wordpress.com/feed/");
		List<RssItem> messages = parser.parse();
		setListAdapter(new FeedListAdapter(getActivity(), messages));
	}
}
