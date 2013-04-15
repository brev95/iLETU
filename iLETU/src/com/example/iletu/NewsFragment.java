package com.example.iletu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
/**
 * Main application activity.
* @author ITCuties
*/
public class NewsFragment extends Fragment {
	ArrayAdapter<RssItem> adapter;
    /**
     * This method creates main application view
     */
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.news_fragment, container, false);
	}
	
    @Override
    public void onStart() {
        super.onStart();
        try {
            // Create RSS reader
            // Get a ListView from main view
            ListView itcItems = (ListView) getView().findViewById(R.id.listMainView);
            // Create a list adapter
            if(adapter == null) {
            	RssReader rssReader = new RssReader("http://letustartpage.blogspot.com/feeds/posts/default?alt=rss");
            	adapter = new ArrayAdapter<RssItem>(getActivity(),android.R.layout.simple_list_item_1, rssReader.getItems());
            }
            // Set list adapter for the ListView
            itcItems.setAdapter(adapter);
            // Set list view item click listener
            //itcItems.setOnItemClickListener(new ListListener(rssReader.getItems(), getActivity()));
        } catch (Exception e) {
            Log.e("RssReader", e.getMessage());
        }
    }
}