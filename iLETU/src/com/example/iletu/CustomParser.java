package com.example.iletu;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class CustomParser extends RssParser{
	
	public CustomParser(String feedUrl) {
		super(feedUrl);
	}
	
	public List<RssItem> parse() {
		List<RssItem> rssItems = null;
		
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(false);
			parser = factory.newPullParser();
			
			parser.setInput(this.getInputStream(), null);
			
			int eventType = parser.getEventType();
			
			RssItem currentItem = null;
			boolean done = false;
			
			while(eventType != XmlPullParser.END_DOCUMENT && !done) {
				String name = null;
				
				switch(eventType) {
				case XmlPullParser.START_DOCUMENT:
					rssItems = new ArrayList<RssItem>();
					break;
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if(name.equalsIgnoreCase(T_ITEM)) 
						currentItem = new RssItem();
					else if(currentItem != null) {
						if(name.equalsIgnoreCase(T_LINK)) {
							currentItem.setLink(parser.nextText());
						}
						else if(name.equalsIgnoreCase(T_DESCRIPTION)) {
							currentItem.setDescription(parser.nextText());
						}
						else if(name.equalsIgnoreCase(T_TITLE)) {
							currentItem.setTitle(parser.nextText());
						}
					}
					break;
				case XmlPullParser.END_TAG:
					name = parser.getName();
					if(name.equalsIgnoreCase(T_ITEM) && currentItem != null) {
						rssItems.add(currentItem);
					}
					else if(name.equalsIgnoreCase(T_CHANNEL)) {
						done = true;
					}
					break;
				}
				
				eventType = parser.next();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return rssItems;
	}
}
