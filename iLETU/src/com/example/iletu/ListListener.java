package com.example.iletu;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ListListener implements OnItemClickListener {
    // Our listener will contain a reference to the list of RSS Items
    // List item's reference
    List<RssItem> listItems;
    // And a reference to a calling activity
    // Calling activity reference
    Activity activity;
    /** We will set those references in our constructor.*/
    public ListListener(List<RssItem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity  = anActivity;
    }
 
    /** Start a browser with url from the rss item.*/
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        //create an Intent which is going to display data
        Intent i = new Intent(view.getContext(), DetailActivity.class);
        //send title, description, and link of rssItem to activity
        i.putExtra("title", listItems.get(pos).getTitle());
        i.putExtra("description", listItems.get(pos).getDescription());
        i.putExtra("link", listItems.get(pos).getLink());
        //start activity with Intent
        activity.startActivity(i);
    }
}