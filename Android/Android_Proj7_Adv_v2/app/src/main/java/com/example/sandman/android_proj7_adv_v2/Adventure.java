package com.example.sandman.android_proj7_adv_v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// Open program, set view, and call Panel()
public class Adventure extends Activity {
    Context thisContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisContext = this;
        setContentView(R.layout.start_screen);
        Button easy = (Button)findViewById(R.id.btnEasy);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_adventure);
                setContentView(new GameChar(thisContext, 5));
            }
        });
        Button med = (Button)findViewById(R.id.btnMedium);
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_adventure);
                setContentView(new GameChar(thisContext, 8));
            }
        });
        Button hard = (Button)findViewById(R.id.btnHard);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_adventure);
                setContentView(new GameChar(thisContext, 11));
            }
        });
        Button imp = (Button)findViewById(R.id.btnImpossible);
        imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_adventure);
                setContentView(new GameChar(thisContext, 14));
            }
        });
        Button how = (Button)findViewById(R.id.btnHowTo);
        how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Reach the treasure before the monsters reach you.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}