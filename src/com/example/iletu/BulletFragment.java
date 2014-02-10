package com.example.iletu;

/*campus bullet fragment for the most part is exactly like the calendar fragment.
 * it gets info from rss and displays it in listview with an onclick listener that opens
 * a detail activity*/
public class BulletFragment extends NewsFragment{
	
	public BulletFragment() {
		super();
		setUrl("http://www.campusbullet.net/home/category/all/?feed");
	}	
}
