package com.example.iletu;

import android.os.Bundle;
import android.util.SparseArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new SampleFragmentPagerAdapter());
    }
    
    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    	final int PAGE_COUNT = 5;
    	private SparseArray<Fragment> frags =
        		new SparseArray<Fragment>(PAGE_COUNT); //holds the fragments
    	
    	public SampleFragmentPagerAdapter(){
    		super(getSupportFragmentManager());
    	}
    	
    	@Override
    	public int getCount(){
    		return PAGE_COUNT;
    	}
    	
    	@Override
    	public Fragment getItem(int position){
    		
    		//returns the fragment for the tab index
    		if(frags.get(position)==null)
        		frags.put(position, create(position));
        	return frags.get(position);
    	}
    	
    	@Override
    	public CharSequence getPageTitle(int position) {
    		
    		//returns the titles for each fragment
    		switch(position){
    		case 0: return "Announcements"; 
    		case 1: return "Calendar";
    		case 2: return "Saga";
    		case 3: return "Online";
    		case 4: return "Campus Bullet";
    		//case 5: return "Intramurals";
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
//            case 5:
//            	fragment = new IntramuralFragment();
//            	fragment = new PageFragment();
//            	break;
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
    	
//    	@Override
//    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//    			Bundle savedInstanceState) {
//    		View view = inflater.inflate(R.layout.fragment_page, container, false);
//    		TextView textView = (TextView)view;
//    		textView.setText("Fragment #" + mPage);
//    		return view;
//    	}
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
}