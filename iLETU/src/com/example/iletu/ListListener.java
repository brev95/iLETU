package com.example.iletu;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/*
 * custom onclick listener for campusbullet and calendar lists
 */

public class ListListener implements OnItemClickListener {
    List<RssItem> listItems;
    Activity activity;
    
    /** We will set those references in our constructor.*/
    public ListListener(List<RssItem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity  = anActivity;
    }
 
    /** Start a browser with url from the rss item.*/
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
    	//makes new intent
        Intent i = new Intent(view.getContext(), DetailActivity.class);
        
        //sends the title, description, and link of the item clicked to the detail activity
        i.putExtra("title", listItems.get(pos).getTitle());
        i.putExtra("description", listItems.get(pos).getDescription());
        i.putExtra("link", listItems.get(pos).getLink());
        
        //start activity
        activity.startActivity(i);
    }
}