package com.example.iletu;

import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssReader {
    private String rssUrl;
   
    //constructor
    public RssReader(String rssUrl) {
        this.rssUrl = rssUrl;
    }
    
    //get the parsing results
    public List<RssItem> getItems() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        RssParseHandler handler = new RssParseHandler();
        saxParser.parse(rssUrl, handler);
        
        return handler.getItems();
    }
}