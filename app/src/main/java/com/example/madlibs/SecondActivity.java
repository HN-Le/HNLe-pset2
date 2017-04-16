package com.example.madlibs;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.R.attr.tag;
import static android.R.id.message;
import static com.example.madlibs.R.id.word_counter;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    //        AssetManager am = context.getAssets();
//
//        InputStream inputStream = openFileInput("madlib1_tarzan.txt");
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferdReader = new BufferedReader(inputStreamReader);
//
//        Story text_new = new Story(inputStream);
//        text_new.getPlaceholderCount();
//        word_counter  = (TextView)findViewById(R.id.word_counter);
//        word_counter.setText((CharSequence) text_new);

//        StringBuilder total = new StringBuilder();
//        String text;
//        while ((text = bufferdReader.readLine()) != null) {
//            total.append(text).append('\n');
//        }
//        private Context context = this;

}
