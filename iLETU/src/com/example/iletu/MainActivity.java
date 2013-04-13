package com.example.iletu;

import android.os.Bundle;
import android.view.Menu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    	
    	public SampleFragmentPagerAdapter(){
    		super(getSupportFragmentManager());
    	}
    	
    	@Override
    	public int getCount(){
    		return PAGE_COUNT;
    	}
    	
    	@Override
    	public Fragment getItem(int position){
    		return PageFragment.create(position + 1);
    	}
    	
    	@Override
    	public CharSequence getPageTitle(int position) {
            return "Page " + (position + 1);
        }
    }
    
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
