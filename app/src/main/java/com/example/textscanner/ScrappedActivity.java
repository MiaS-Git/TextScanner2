package com.example.textscanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import webScraper.*;

public class ScrappedActivity extends AppCompatActivity {

    TextView tv;
    String st;
   Intent in = new Intent(ScrappedActivity.this, JsoupRun.class) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrapped);
        tv = findViewById(R.id.textView);

        st = getIntent().getExtras().getString("Value");
        tv.setText(st);
    }
}