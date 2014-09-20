package com.example.bollymaps;

import android.os.Bundle;
import android.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
    
    public static MovieDBAdapter GetAdapterInstance()
    {
    	return dbAdapter;
    }        
}
