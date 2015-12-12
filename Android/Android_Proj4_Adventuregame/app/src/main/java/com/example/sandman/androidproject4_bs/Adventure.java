package com.example.sandman.androidproject4_bs;

import android.app.Activity;
import android.os.Bundle;

// Open program, set view, and call Panel()
public class Adventure extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);
        setContentView(new GameChar(this));
    }
}