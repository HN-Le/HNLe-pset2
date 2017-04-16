package com.example.madlibs;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.R.attr.tag;
import static android.R.id.message;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

////    loading text files in
//
//    public void read (Context) throws IOException {
//
//        AssetManager am = context.getAssets();
//        InputStream inputStream = openFileInput("madlib0_simple.txt");
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferdReader = new BufferedReader(inputStreamReader);
//
//        StringBuilder total = new StringBuilder();
//        String text;
//        while ((text = bufferdReader.readLine()) != null) {
//            total.append(text).append('\n');
//        }
//    }
//
//    private Context context = this;
}
