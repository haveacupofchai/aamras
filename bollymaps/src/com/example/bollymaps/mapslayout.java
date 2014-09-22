package com.example.bollymaps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;



public class mapslayout extends Activity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);	
	      
		Bundle extras = getIntent().getExtras();		
		String valueOfLat = extras.getString("lat");  // Get Lattitude
		String valueOfLng = extras.getString("lng");  // Get Longitude

		// Convert String to Double
		double lat = Double.parseDouble(valueOfLat);
		double lng = Double.parseDouble(valueOfLng);

		// Create the map
		GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

		// Dummy markers and names - ToDo
        LatLng srkInKHNH = new LatLng(lat, lng);
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(srkInKHNH, 13));

        map.addMarker(new MarkerOptions()
                .title("ACBD in Kal Ho Na Ho")
                .snippet("Title Song was shot here")
                .position(srkInKHNH));
	}
}
