package com.example.iletu;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssParseHandler extends DefaultHandler {
 
    // all items parsed
    private List<RssItem> rssItems;
    
    // item currently being parsed
    private RssItem currentItem;
    
    // indicators showing what is currently being parsed
    private boolean parsingTitle;
    private boolean parsingLink;
    private boolean parsingDescription;
 
    public RssParseHandler() {
        rssItems = new ArrayList<RssItem>();
    }
    
    // returns all the already parsed items
    public List<RssItem> getItems() {
        return rssItems;
    }
    
    // creates an empty rss item
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            currentItem = new RssItem();
        } else if ("title".equals(qName)) {
            parsingTitle = true;
        } else if ("link".equals(qName)) {
            parsingLink = true;
        } else if ("description".equals(qName)) {
        	parsingDescription = true;
        }
    }
    
    // adds the finished rss item to the list of rss items
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            rssItems.add(currentItem);
            currentItem = null;
        } else if ("title".equals(qName)) {
            parsingTitle = false;
        } else if ("link".equals(qName)) {
            parsingLink = false;
        } else if ("description".equals(qName)) {
        	parsingDescription = false;
        }
    }
    
    // fills the current rss item with content
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (parsingTitle) {
            if (currentItem != null)
                currentItem.setTitle(new String(ch, start, length));
        } else if (parsingLink) {
            if (currentItem != null) {
                currentItem.setLink(new String(ch, start, length));
                parsingLink = false;
            }
        } else if (parsingDescription) {
        	if (currentItem != null) {
        		currentItem.setDescription(new String(ch, start, length));
        		parsingDescription = false;
        	}
        }
    }
}