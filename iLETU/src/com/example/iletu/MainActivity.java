package com.example.iletu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter());
    }
    
    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    	final int PAGE_COUNT = 5;
    	private SparseArray<Fragment> frags =
        		new SparseArray<Fragment>(PAGE_COUNT);
    	
    	public SampleFragmentPagerAdapter(){
    		super(getSupportFragmentManager());
    	}
    	
    	@Override
    	public int getCount(){
    		return PAGE_COUNT;
    	}
    	
    	@Override
    	public Fragment getItem(int position){
    		if(frags.get(position)==null)
        		frags.put(position, create(position));
        	return frags.get(position);
    	}
    	
    	@Override
    	public CharSequence getPageTitle(int position) {
    		switch(position){
    		case 0: return "Announcements"; 
    		case 1: return "Calendar";
    		case 2: return "Saga";
    		case 3: return "Online";
    		case 4: return "Campus Bullet";
    		default: return "Tab";
    		}
            //return "Page " + (position + 1);
        }
    	
    	public Fragment create(int page) {
            Bundle args = new Bundle();
            //args.putInt(ARG_PAGE, page);
            Fragment fragment;
            switch(page){
            case 0:
            	fragment = new NewsFragment();
            	break;
//            case 1:
//            	fragment = new MediaFeedFragment();
//            	break;
//            case 2:
//            	fragment = new CamListFragment();
//            	break;
//            case 3:
//            	fragment = new PortListFragment();
//            	break;
//            case 4:
//            	fragment = setupMap();
//            	break;
            default:
            	fragment = new PageFragment();
            }//end switch
            fragment.setArguments(args);
            return fragment;
        }//end create
    }//end SampleFragmentPagerAdapter
    
    public static class PageFragment extends Fragment {
    	public static final String ARG_PAGE = "ARG_PAGE";
    	
    	private int mPage;
    	
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
    		mPage = getArguments().getInt(ARG_PAGE);
    	}
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
    			Bundle savedInstanceState) {
    		View view = inflater.inflate(R.layout.fragment_page, container, false);
    		TextView textView = (TextView)view;
    		textView.setText("Fragment #" + mPage);
    		return view;
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
//public class MainActivity extends Activity {
//	/** 
//	 * This method creates main application view
//	 */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		// Set view
//		setContentView(R.layout.main);
//
//		try {
//			// Create RSS reader
//			RssReader rssReader = new RssReader("http://www.itcuties.com/feed/");
//			// Get a ListView from main view
//			ListView itcItems = (ListView) findViewById(R.id.listMainView);
//			
//			// Create a list adapter
//			ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(this,android.R.layout.simple_list_item_1, rssReader.getItems());
//			// Set list adapter for the ListView
//			itcItems.setAdapter(adapter);
//			
//			// Set list view item click listener
//			itcItems.setOnItemClickListener(new ListListener(rssReader.getItems(), this));
//			
//		} catch (Exception e) {
//			Log.e("ITCRssReader", e.getMessage());
//		}
//		
//	}
//	
//}