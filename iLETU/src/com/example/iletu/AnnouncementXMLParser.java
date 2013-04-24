package com.example.iletu;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/*
 * this class will parse an announcement source and return a list of the RssItems from the data
 */
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
            
            // as long as the parsing is not at the end of the document
            while(eventType != XmlPullParser.END_DOCUMENT) {

                String tagName = parser.getName();
                
                switch(eventType) {
	                
                    case XmlPullParser.START_TAG:
                    	
                        // if the next tag is an item create a new item
                        if (tagName.equalsIgnoreCase("item")) {
                            item = new RssItem();
                            isCreatingItem = true;
                        }
                        
                        // if the next tag is a title add the title to the current item
                        else if(tagName.equalsIgnoreCase("title") && isCreatingItem) {
                            item.setTitle(parser.nextText());
                        }
                        
                        // if the next tag is a description add the description to the current item
                        else if(tagName.equalsIgnoreCase("description") && isCreatingItem) {
                            item.setDescription(parser.nextText());
                        }
                        
                        // if the next tag is a link add the link to the current item
                        else if(tagName.equalsIgnoreCase("link") && isCreatingItem) {
                            item.setLink(parser.nextText());
                        }
                        break;
                        
                    // if the tag is the ending of an item add the item to the list
                    case XmlPullParser.END_TAG:
                    	
                    	if (tagName.equalsIgnoreCase("item")) {
                    		list.add(item);
                    		isCreatingItem = false;
                    	}
                    	break;
                }
                eventType = parser.next();
            }
        
        // handle the exceptions
        } catch (XmlPullParserException e) {
            item = null;
        } catch (IOException e) {
            item = null;
        }
        
        // return the filled list
        return list;
    }
}
