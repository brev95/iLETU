/*
 * Andrew Pollard and Aidan Wolter
 * Personal Project
 * COSC2103
 * 4/23/2013
 * Android version of iLETU app
 */

package com.example.iletu;

import android.os.Bundle;
import android.util.SparseArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    //sets what to be done when app first opened
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inflates the main activity layout xml
        setContentView(R.layout.activity_main);
        //sets viewpager which controls tab interface
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(4);
        //sets viewpageradapter to return proper fragments to each tab
        viewPager.setAdapter(new MyFragmentPagerAdapter());
    }
    
	//page adapter controls which fragments to show in each tab of main view
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    	//sets number of pages
    	final int PAGE_COUNT = 5;
    	//array of all fragments
    	private SparseArray<Fragment> frags =
        		new SparseArray<Fragment>(PAGE_COUNT); //holds the fragments
    	
    	public MyFragmentPagerAdapter(){
    		super(getSupportFragmentManager());
    	}
    	
    	@Override
    	public int getCount(){
    		return PAGE_COUNT;
    	}
    	
    	//creates a new fragment to fill tab if one doesn't already exist. if it does,
    	//it returns the fragment in the sparse array
    	@Override
    	public Fragment getItem(int position){
    		//returns the fragment for the tab index
    		if(frags.get(position)==null)
        		frags.put(position, create(position));
        	return frags.get(position);
    	}
    	
    	//sets the names of each tab
    	@Override
    	public CharSequence getPageTitle(int position) {
    		//returns the titles for each fragment
    		switch(position){
    		case 0: return "Announcements"; 
    		case 1: return "Calendar";
    		case 2: return "Saga";
    		case 3: return "Online";
    		case 4: return "Campus Bullet";
    		default: return "Tab";
    		}
        }
    	
    	public Fragment create(int page) {
    		//creates the fragment corresponding the the correct tab index
            Bundle args = new Bundle();
            Fragment fragment;
            switch(page){
            case 0:
            	fragment = new AnnouncementsFragment();
            	break;
            case 1:
            	fragment = new CalenderFragment();
            	break;
            case 2:
            	fragment = new SagaFragment();
            	break;
            case 3:
            	fragment = new LinksFragment();
            	break;
            case 4:
            	fragment = new BulletFragment();
            	break;
            default:
            	fragment = new PageFragment();
            }//end switch
            fragment.setArguments(args);
            return fragment;
        }//end create
    }//end SampleFragmentPagerAdapter
    
    
    //default fragment in the paging activity
    public static class PageFragment extends Fragment {
    	public static final String ARG_PAGE = "ARG_PAGE";
    	
    	
    	public static PageFragment create(int page) {
    		Bundle args = new Bundle();
    		args.putInt(ARG_PAGE, page);
    		PageFragment fragment = new PageFragment();
    		fragment.setArguments(args);
    		return fragment;
    	}
    	
    	@Override
    	public void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
    	}
    }
}