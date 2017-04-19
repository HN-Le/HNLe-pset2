package com.example.madlibs;

import android.content.Context;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;



public class FirstActivity extends AppCompatActivity {

    private Context context = this;

    Story story;
    TextView textView;
    TextView text_reminder;
    TextView user_input;
    String input;
    String text_done;
    int placeholder_counter;
    String holder;
    InputStream inputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        textView = (TextView) findViewById(R.id.layout_word_counter);
        text_reminder = (TextView) findViewById(R.id.reminder);
        user_input = (TextView) findViewById(R.id.user_input);


        if(savedInstanceState != null){
            story = (Story) savedInstanceState.getSerializable("story");
            textView.setText((String.valueOf(placeholder_counter) + " words left"));
            placeholder_counter = savedInstanceState.getInt("counter");
            textView.setText(placeholder_counter + " words left");
            text_reminder.setText(("Please fill in a " + story.getNextPlaceholder()));
            user_input.setHint(story.getNextPlaceholder());

        }

        else {

            try {

                inputStream = getAssets().open("madlib0_simple.txt");

                //  Make a new story object and load in the stream (the converted text file)
                story = new Story(inputStream);

                //  Extract the placeholder amount from the text file through the story class
                placeholder_counter = story.getPlaceholderRemainingCount();

                user_input = (TextView) findViewById(R.id.user_input);

                holder = story.getNextPlaceholder();

                //  Set the hint of the editText to noun/verb/whatever
                user_input.setHint(holder);

                // Set reminder to what to fill in
                text_reminder.setText("Please fill in a " + holder);

                //  The amount of words left counter
                textView.setText((String.valueOf(placeholder_counter) + " words left"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("story", story);
        outState.putInt("counter", placeholder_counter);
    }


    //  When "USE" button is clicked
    public void use_clicked(View view) {

        EditText user_input = (EditText) findViewById(R.id.user_input);
        input = user_input.getText().toString();


        if (!input.matches("")) {

            if (!story.isFilledIn()) {
                //  Save input from EditText into a string

                story.fillInPlaceholder(input);

                ((EditText) findViewById(R.id.user_input)).setText("");

                ((EditText) findViewById(R.id.user_input)).setHint(story.getNextPlaceholder());

                textView.setText("");

                placeholder_counter = story.getPlaceholderRemainingCount();

                textView.setText((String.valueOf(placeholder_counter) + " words left"));

                // Set reminder to what to fill in
                text_reminder.setText(("Please fill in a " + story.getNextPlaceholder()));

            }

            if (story.isFilledIn()) {

                text_done = story.toString();

                Intent intent = new Intent(this, SecondActivity.class);

                intent.putExtra("text", text_done);

                startActivity(intent);
                finish();
            }
        }

        else {
            // Set reminder to what to fill in
            text_reminder.setText(("Please fill in a " + story.getNextPlaceholder()));
        }

    }

}

