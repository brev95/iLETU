package com.example.iletu;

/*sets url of the rss feed from letu calendar site.
 * displays all items in listview
 */
public class CalenderFragment extends NewsFragment {
	
	public CalenderFragment() {
		super();
		setUrl("http://www.letu.edu/calfeedcache/v1.0/rssDays/7/list-rss/no--filter.rss");
	}
}
