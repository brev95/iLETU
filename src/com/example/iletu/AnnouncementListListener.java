package com.example.iletu;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/*
 * custom onclick listener to bring up detail activity when announcement is clicked on
 */

public class AnnouncementListListener implements OnItemClickListener {
	List<RssItem> listItems;
    Activity activity;
    
    //We will set those references in our constructor
    public AnnouncementListListener(List<RssItem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity  = anActivity;
    }
 
    //Start a browser with url from the rss item.
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        Intent i = new Intent(view.getContext(), DetailWebActivity.class);
        //sends the title and description of the list item to the detailview activity
        i.putExtra("title", listItems.get(pos).getTitle());
        i.putExtra("description", listItems.get(pos).getDescription());
        activity.startActivity(i);
    }
}
