package com.example.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // When clicked on "START" go to next screen
    public void goToFirst(View view) {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }
}

