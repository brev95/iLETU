package com.example.iletu;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LinksFragment extends Fragment {
	List<LinkItem> linkItems;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		return inflater.inflate(R.layout.links_fragment, container, false);
	}
	
	@Override
    public void onStart() {
        super.onStart();
        if (linkItems == null) {
			linkItems = new ArrayList<LinkItem>();
			linkItems.add(new LinkItem("Email","https://mail.letu.edu"));
			linkItems.add(new LinkItem("BlackBoard", "https://courses.letu.edu"));
			linkItems.add(new LinkItem("Web Services", "https://my.letu.edu:91/_formauth/login.html"));
			linkItems.add(new LinkItem("Student Directory", "https://my.letu.edu:94/_formauth/login.html"));
			linkItems.add(new LinkItem("Chapel Attendance", "http://www.letu.edu/student_life/chapel/attendance.html"));
        }
        ListView items = (ListView) getView().findViewById(R.id.linksMainView);
        items.setOnItemClickListener(new LinkListListener(linkItems,getActivity()));
        items.setAdapter(new ArrayAdapter<LinkItem>(getActivity(),android.R.layout.simple_list_item_1, linkItems));
    }
}
