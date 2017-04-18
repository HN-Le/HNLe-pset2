package com.example.madlibs;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static android.R.attr.tag;
import static android.R.id.message;
import static com.example.madlibs.R.id.textView;
import static com.example.madlibs.R.id.word_counter;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String text_final = intent.getStringExtra("text");

        TextView final_verhaal = (TextView) findViewById(R.id.verhaal);
        final_verhaal.setText(text_final);

    }


}
