package com.example.bollymaps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;



public class mapslayout extends Activity {

	private MovieDBAdapter dbAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);	
	      
		Bundle extras = getIntent().getExtras();		
		String value = extras.getString("locationName");
		
	    dbAdapter = MainActivity.GetAdapterInstance();
		String[] columns = new String[] {MovieDBAdapter.COL_ROWID, MovieDBAdapter.COL_LOCATION, MovieDBAdapter.COL_LATITUDE, MovieDBAdapter.COL_LONGITUDE};
	    String where = "MovieLocation='" + value + "'";
		Cursor c = dbAdapter.GetCursor(columns, where, null, null, null);
		
	    c.moveToFirst();
	    double lat = c.getDouble(c.getColumnIndex(MovieDBAdapter.COL_LATITUDE));
	    double lng = c.getDouble(c.getColumnIndex(MovieDBAdapter.COL_LONGITUDE));
        
        GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        LatLng srkInKHNH = new LatLng(lat, lng);
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(srkInKHNH, 13));

        map.addMarker(new MarkerOptions()
                .title("ACBD in Kal Ho Na Ho")
                .snippet("Title Song was shot here")
                .position(srkInKHNH));
	}
}
