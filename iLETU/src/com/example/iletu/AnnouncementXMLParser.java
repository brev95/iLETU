package com.example.iletu;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class AnnouncementXMLParser {
	
	public List<RssItem> parse(InputStream is) {
		List<RssItem> list = new ArrayList<RssItem>();
		RssItem item = null;
 
        try {
            // get a new XmlPullParser object from Factory
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is, null);
            int eventType = parser.getEventType();
            boolean isCreatingItem = false;
            
            while(eventType != XmlPullParser.END_DOCUMENT) {

                String tagName = parser.getName();
                
                switch(eventType) {
	                
                    case XmlPullParser.START_TAG:
                    	
                        
                        if (tagName.equalsIgnoreCase("item")) {
                            item = new RssItem();
                            isCreatingItem = true;
                        }
                        else if(tagName.equalsIgnoreCase("title") && isCreatingItem) {
                            item.setTitle(parser.nextText());
                        }
                        else if(tagName.equalsIgnoreCase("description") && isCreatingItem) {
                            item.setDescription(parser.nextText());
                        }
                        else if(tagName.equalsIgnoreCase("link") && isCreatingItem) {
                            item.setLink(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                    	
                    	if (tagName.equalsIgnoreCase("item")) {
                    		list.add(item);
                    		isCreatingItem = false;
                    	}
                    	break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            item = null;
        } catch (IOException e) {
            item = null;
        }
 
        return list;
    }
}
