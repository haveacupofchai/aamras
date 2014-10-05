package com.example.bollymaps;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

	static private MovieDBAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		dbAdapter = new MovieDBAdapter(this);
		dbAdapter.initializeDatabase("/data/data/com.example.bollymaps/databases/");
		dbAdapter.open();
	}

	public void sendMessage(View view) {
		Intent intent = new Intent(this, MovieNameList.class);
		startActivity(intent);
	}

	public void byOther(View buttonId)
	{
		String parameter;
	      switch(buttonId.getId()){
	       case R.id.bActorName :
	    	   parameter=MovieDBAdapter.COL_ACTOR;
	    	   break;
	       case R.id.bActressName :
	    	   parameter=MovieDBAdapter.COL_ACTRESS;
	    	   break;
	       case R.id.bDirectorName :
	    	   parameter=MovieDBAdapter.COL_DIRECTOR;
	    	   break;
	       case R.id.bYear :
	    	   parameter=MovieDBAdapter.COL_YEAR;
	    	   break;
	       default:
	    	   parameter=MovieDBAdapter.COL_NAME;	   
	      }
	   		Intent intent = new Intent(this, OtherList.class);
			intent.putExtra("paraName", parameter);
	   		startActivity(intent);
	}
	
	public static MovieDBAdapter GetAdapterInstance() {
		return dbAdapter;
	}
}
