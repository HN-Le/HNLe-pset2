package com.example.madlibs;

import android.content.Context;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;



public class FirstActivity extends AppCompatActivity {

    private Context context = this;
    private Story story;

    TextView textView;
    TextView text_reminder;
    String input;
    String text_done;
    int placeholder_counter;
    String reminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        textView = (TextView) findViewById(R.id.layout_word_counter);
        text_reminder = (TextView) findViewById(R.id.reminder);

        AssetManager assetManager = context.getAssets();

        try {
            //  Open text file and load it into scanner
            InputStream inputStream = assetManager.open("madlib1_tarzan.txt");
            Scanner scanner = new Scanner(inputStream);

            String output = "";

            //  Go over every line and save it into the string "output"
            while (scanner.hasNextLine()) {
                output += scanner.nextLine() + '\n';
            }

            //  Convert string to stream
            InputStream stream = new ByteArrayInputStream(output.getBytes(StandardCharsets.UTF_8));

            //  Make a new story object and load in the stream (the converted text file)
            story = new Story(stream);

            //  Extract the placeholder amount from the text file through the story class
            placeholder_counter = story.getPlaceholderCount();

            //  Set the hint of the editText to noun/verb/whatever
            ((TextView) findViewById(R.id.user_input)).setHint(story.getNextPlaceholder());

            // Set reminder to what to fill in
            text_reminder.setText(("Please fill in a " + story.getNextPlaceholder()));

            //  The amount of words left counter
            textView.setText((String.valueOf(placeholder_counter) + " words left"));

        }

        catch (Exception e) {
            e.printStackTrace();
        }

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

                String text_done = story.toString();

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

