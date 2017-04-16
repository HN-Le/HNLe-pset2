package com.example.madlibs;

import android.content.Context;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import static com.example.madlibs.R.id.word_counter;


public class FirstActivity extends AppCompatActivity {
    TextView word_counter;
    private Context context = this;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        textView = (TextView) findViewById(R.id.word_counter);

        AssetManager assetManager = context.getAssets();

        try
        {
            InputStream inputStream = assetManager.open("madlib0_simple.txt");
            Scanner scanner = new Scanner(inputStream);
            String output = "";

            while (scanner.hasNextLine()) {
                output += scanner.nextLine() + '\n';
            }

            Story text_new = new Story(inputStream);
            text_new.getPlaceholderCount();

            textView.setText(output);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}

