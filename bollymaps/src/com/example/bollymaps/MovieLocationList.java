package com.example.bollymaps;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/* ListView Activity of the Locations
 * */
public class MovieLocationList extends ListActivity {

	private ArrayList<String> locationList = new ArrayList<String>();
	private MovieDBAdapter dbAdapter;
	private String[] columns = new String[] {MovieDBAdapter.COL_ROWID, MovieDBAdapter.COL_NAME, MovieDBAdapter.COL_LOCATION, MovieDBAdapter.COL_LATITUDE, MovieDBAdapter.COL_LONGITUDE};
	private Cursor c;
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
        Intent intent = new Intent(this, mapslayout.class);
        String locationName = locationList.get(position);
		
	    String where = "MovieLocation='" + locationName + "'";
		c = dbAdapter.GetCursor(columns, where, null, null, null);
		
	    c.moveToFirst();
	    String lat = c.getString(c.getColumnIndex(MovieDBAdapter.COL_LATITUDE));
	    String lng = c.getString(c.getColumnIndex(MovieDBAdapter.COL_LONGITUDE));
	    String movie = c.getString(c.getColumnIndex(MovieDBAdapter.COL_NAME));
        String location = c.getString(c.getColumnIndex(MovieDBAdapter.COL_LOCATION));

	    // Pass arguments to mapslayout class
		intent.putExtra("lat", lat); // Lattitude
		intent.putExtra("lng", lng); // Longitude
		intent.putExtra("movie", movie); // Movie name
        intent.putExtra("location", location); // Location
		
		// Starts the Maps Activity
        startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bundle extras = getIntent().getExtras();
		String value = extras.getString("movieName");

		dbAdapter = MainActivity.GetAdapterInstance();
	      
	    String[] columns = new String[] {MovieDBAdapter.COL_ROWID, MovieDBAdapter.COL_LOCATION, MovieDBAdapter.COL_NAME};
	    String where = "Name='" + value + "'";
	    locationList = dbAdapter.GetArrayList(MovieDBAdapter.COL_LOCATION, columns, where, null, null, null);
		 	
		if(locationList.isEmpty() == false)	
		{
			setListAdapter(new ArrayAdapter<String>(MovieLocationList.this, android.R.layout.simple_list_item_1, locationList));	
		}
	}

}
