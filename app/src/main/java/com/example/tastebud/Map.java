package com.example.tastebud;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Intent;
import android.widget.TextView;

public class Map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    Intent intent = getIntent();

    Bundle extras = intent.getExtras();

    String m1 = extras.getString("m1");
    String m2 = extras.getString("m2");

    TextView textView = findViewById(R.id.textView);
    textView.setText(m1);

    TextView textView2 = findViewById(R.id.textView2);
    textView2.setText(m2);


    }

}
