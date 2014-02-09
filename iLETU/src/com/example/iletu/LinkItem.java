package com.example.iletu;

/*
 * basic item for each list of links in fragments of app.
 * contains the title and url of each item.
 */
public class LinkItem {
	public String title, url;
	
	public LinkItem(String title, String url) {
		this.title = title;
		this.url = url;
	}
	
	@Override
	public String toString(){
		return title;
	}
}
