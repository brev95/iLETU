package com.example.iletu;

public class CalenderFragment extends NewsFragment {
	
	public CalenderFragment() {
		super();
		setUrl("http://www.letu.edu/calfeedcache/v1.0/rssDays/7/list-rss/no--filter.rss");
		//setUrl(String.format("http://www.letu.edu/calfeedcache/v1.0/rssRange/%d0%d%d/%d0%d%d/list-rss/%%28catuid%%21%%3D%%27ff808081-2b0c934c-012b-3b0b5901-00002805%%27%%26catuid%%21%%3D%%27ff808081-2b95f5c5-012b-ac344e60-000063a5%%27%%29.rss",2013,4,16,2013,4,23));
	}
}
