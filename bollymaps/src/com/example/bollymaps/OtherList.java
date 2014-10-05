package com.example.bollymaps;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OtherList extends ListActivity {
	
	private ArrayList<String> otherIdList = new ArrayList<String>();
	private MovieDBAdapter dbAdapter;
    private String what;
    
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String otherIdName = otherIdList.get(position);
		Intent intent = new Intent(this, MovieNameList.class);
		intent.putExtra("otherName", otherIdName);
        intent.putExtra("otherColumn", what);
		startActivity(intent);
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 	
	    dbAdapter = MainActivity.GetAdapterInstance();
	      
	    Bundle extras = getIntent().getExtras();
	    
	    what = extras.getString("paraName");
		String[] columns = new String[] {MovieDBAdapter.COL_ROWID, MovieDBAdapter.COL_NAME, what};
	    
	    otherIdList = dbAdapter.GetArrayList(what, columns, null, null, what, null);
		 	
		if(otherIdList.isEmpty() == false)	
		{
			setListAdapter(new ArrayAdapter<String>(OtherList.this, android.R.layout.simple_list_item_1, otherIdList));	
		}
	}

}
