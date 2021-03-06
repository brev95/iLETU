package com.example.iletu;

import java.net.URL;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * fragment that shows all announcement items in a listview.
 * sets announcementslistlistener as the onclick listener for the list
 */

public class AnnouncementsFragment extends Fragment {
	
	AnnouncementXMLParser parser = new AnnouncementXMLParser();
	private String url;
	ArrayAdapter<RssItem> adapter;
	AnnouncementListListener listener;
	
	//set the url for the feed
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	//inflates the layout xml for the news fragment
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		//inflates the news fragment layout xml
		return inflater.inflate(R.layout.news_fragment, container, false);
	}
	
	 @Override
    public void onStart() {
        super.onStart();
        url = "http://letustartpage.blogspot.com/feeds/posts/default?alt=rss";
        
        //create and add the adaptor with all the correct content
        if (url != null) {
	        try {
	            ListView items = (ListView) getView().findViewById(R.id.listMainView);
	            
	            if(adapter == null) {
	            	URL urlLink = new URL(url);
	            	urlLink.openConnection();
	            	adapter = new ArrayAdapter<RssItem>(getActivity(),android.R.layout.simple_list_item_1, parser.parse(urlLink.openStream()));
		            listener = new AnnouncementListListener(parser.parse(urlLink.openStream()), getActivity());
	            }
	            //sets onclick listener as the announcementlistlistener
	            items.setOnItemClickListener(listener);
	            items.setAdapter(adapter);
	        } catch (Exception e) {
	        	//send something to console?
	        }
        }
    }
}
