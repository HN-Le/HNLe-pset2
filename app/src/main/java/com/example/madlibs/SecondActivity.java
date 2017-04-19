package com.example.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get finished text from prev screen
        Intent intent = getIntent();
        String text_final = intent.getStringExtra("text");

        // Set text view to final story
        TextView final_story = (TextView) findViewById(R.id.story);
        final_story.setText(text_final);

    }

    // When clicked on "Make Another Story" button go back to first screen
    public void startOver(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
