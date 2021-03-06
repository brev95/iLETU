package com.example.iletu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * parent class of announcements, calendar, and campusbullet fragments.
 * handles the parsing object and listener
 */

public class NewsFragment extends Fragment {
	ListListener listener;
	private String url;
	ArrayAdapter<RssItem> adapter;
	RssReader rssReader;
	
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
		return inflater.inflate(R.layout.news_fragment, container, false);
	}
	
    @Override
    public void onStart() {
        super.onStart();
        
        //create and add the adaptor with all the correct content
        if (url != null) {
	        try {
	            ListView items = (ListView) getView().findViewById(R.id.listMainView);
	            
	            //sets adapter and onclick listener of listview in fragment
	            if(adapter == null) { 
	            	rssReader = new RssReader(url);
	            	adapter = new ArrayAdapter<RssItem>(getActivity(),android.R.layout.simple_list_item_1, rssReader.getItems());
		            listener = new ListListener(rssReader.getItems(), getActivity());
	            }
	            items.setOnItemClickListener(listener);
	            items.setAdapter(adapter);
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
        }
    }
}