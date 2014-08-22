package com.example.bollymaps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class mapslayout extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    // Get the message from the intent
	    
        GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        LatLng srkInKHNH = new LatLng(40.705, -73.9964);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(srkInKHNH, 13));

        map.addMarker(new MarkerOptions()
                .title("SRK in Kal Ho Na Ho")
                .snippet("Title Song was shot here")
                .position(srkInKHNH));
        
        LatLng dhoom3 = new LatLng(41.88, -87.628);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(dhoom3, 13));

        map.addMarker(new MarkerOptions()
                .title("Dhoom 3")
                .snippet("Major part of movie shot in Chicago")
                .position(dhoom3));
        


	}
}
