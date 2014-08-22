package com.example.bollymaps;

import android.os.Bundle;
import android.app.Activity;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }
        public void sendMessage(View view) {
            Intent intent = new Intent(this, mapslayout.class);
            startActivity(intent);
        }
}
