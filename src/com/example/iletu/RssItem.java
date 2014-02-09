package com.example.iletu;

/*
 * a single rss data item.
 * contains the link, title, and description associated with rss feed item
 */

public class RssItem {
	private String title, link;
	private String description;
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getDescription(){
		return description;
	}
	
	@Override
	public String toString(){
		return title;
	}
}
