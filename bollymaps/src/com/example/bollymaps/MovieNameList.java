package com.example.bollymaps;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MovieNameList extends ListActivity{
	
	private ArrayList<String> nameList = new ArrayList<String>();
	private MovieDBAdapter dbAdapter;

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String movieName = nameList.get(position);
		Intent intent = new Intent(this, MovieLocationList.class);
		intent.putExtra("movieName", movieName);
        startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 	
	    dbAdapter = MainActivity.GetAdapterInstance();
	      
	    String[] columns = new String[] {MovieDBAdapter.COL_ROWID, MovieDBAdapter.COL_LOCATION, MovieDBAdapter.COL_NAME};
	    nameList = dbAdapter.GetArrayList(MovieDBAdapter.COL_NAME, columns, null, null, MovieDBAdapter.COL_NAME, null);
		 	
		if(nameList.isEmpty() == false)	
		{
			setListAdapter(new ArrayAdapter<String>(MovieNameList.this, android.R.layout.simple_list_item_1, nameList));	
		}
	}
}
