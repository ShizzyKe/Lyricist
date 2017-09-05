package com.example.ian.lyricist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by Ian on 9/5/2017.
 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

    public class AboutActivity extends AppCompatActivity {
        WebView eweb;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_about);

            eweb = (WebView) findViewById(R.id.eweb);
            eweb.loadUrl("file:///android_asset/about.html");

        }
    }


