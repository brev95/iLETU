package com.example.iletu;

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
