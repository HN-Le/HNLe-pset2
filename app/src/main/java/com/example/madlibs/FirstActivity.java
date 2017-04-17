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

import static com.example.madlibs.R.id.editText;
import static com.example.madlibs.R.id.word_counter;


public class FirstActivity extends AppCompatActivity {

    private Context context = this;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        textView = (TextView) findViewById(R.id.layout_word_counter);

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
            Story text_new = new Story(stream);

            //  Extract the placeholder amount from the text file through the story class
            int placeholder_counter = text_new.getPlaceholderCount();

            //  Set the hint of the editText to noun/verb/whatever
            ((TextView) findViewById(R.id.user_input)).setHint(text_new.getNextPlaceholder());

            //  The amount of words left counter
            textView.setText((String.valueOf(placeholder_counter) + " words left"));

            //  Save input from EditText into a string
            EditText user_input = (EditText) findViewById(R.id.user_input);
            String input = user_input.getText().toString();

            //  Fill in the placeholder in the text with the input from user
            text_new.fillInPlaceholder(input);

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }

    //  When "USE" button is clicked
    public void use_clicked(View view) {

        //  Save input from EditText into a string
        EditText user_input = (EditText) findViewById(R.id.user_input);
        String input = user_input.getText().toString();
        Log.d("THIS", input);

    }

    //  When "STORY" is clicked
    public void goToSecond(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}

