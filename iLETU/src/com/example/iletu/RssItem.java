package com.example.iletu;

import java.net.MalformedURLException;
import java.net.URL;

public class RssItem {
	private String title, link;
	private String description;
	//private URL link;
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setLink(String link) {
//		try {
//			this.link = new URL(link);
//		}catch(MalformedURLException e){
//			e.printStackTrace();
//		}
		this.link = link;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
//	public URL getLink(){
//		return link;
//	}
	
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
