package com.example.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.InputStream;

public class FirstActivity extends AppCompatActivity {

    Story story;
    int placeholder_counter;
    TextView layout_word_counter, reminder, user_input;
    String input, text_done, holder;
    InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // The things to update in layout
        layout_word_counter = (TextView) findViewById(R.id.layout_word_counter);
        reminder = (TextView) findViewById(R.id.reminder);
        user_input = (TextView) findViewById(R.id.user_input);

        // If there is something saved, retrieve that and update
        if(savedInstanceState != null){

            story = (Story) savedInstanceState.getSerializable("story");
            layout_word_counter.setText((String.valueOf(placeholder_counter) + " words left"));
            placeholder_counter = savedInstanceState.getInt("counter");
            layout_word_counter.setText(placeholder_counter + " words left");
            reminder.setText(("Please fill in a " + story.getNextPlaceholder()));
            user_input.setHint(story.getNextPlaceholder());

        }

        else {

            try {

                // Load textfile in
                inputStream = getAssets().open("madlib0_simple.txt");

                //  Make a new story object and load in the stream (the converted text file)
                story = new Story(inputStream);

                //  Extract the placeholder amount from the text file through the story class
                placeholder_counter = story.getPlaceholderRemainingCount();

                // Assign text view variable to the right text field
                user_input = (TextView) findViewById(R.id.user_input);

                // Saving the next placeholder in a variable
                holder = story.getNextPlaceholder();

                //  Set the hint of the editText to noun/verb/whatever
                user_input.setHint(holder);

                // Set reminder to what to fill in
                reminder.setText("Please fill in a " + holder);

                //  The amount of words left counter
                layout_word_counter.setText((String.valueOf(placeholder_counter) + " words left"));

            }

            catch (Exception e) {

                e.printStackTrace();

            }
        }
    }

    // Save the state
    @Override
    protected void onSaveInstanceState (Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putSerializable("story", story);
        outState.putInt("counter", placeholder_counter);

    }


    //  When "USE" button is clicked
    public void use_clicked(View view) {

        // Save the input from the user
        EditText user_input = (EditText) findViewById(R.id.user_input);
        input = user_input.getText().toString();

        // If something is filled in
        if (!input.matches("")) {

            // If there are still placeholders to fill in
            if (!story.isFilledIn()) {

                // Fill in the user input into the story (Replace placeholder for user input)
                story.fillInPlaceholder(input);

                // After pressing "use" button reset the field to blank
                ((EditText) findViewById(R.id.user_input)).setText("");

                // Set the hint to the next placeholder
                ((EditText) findViewById(R.id.user_input)).setHint(story.getNextPlaceholder());

                // Set the place holder counter in a variable
                placeholder_counter = story.getPlaceholderRemainingCount();

                // Set counter text in layout
                layout_word_counter.setText((String.valueOf(placeholder_counter) + " words left"));

                // Set reminder to what to fill in
                reminder.setText(("Please fill in a " + story.getNextPlaceholder()));

            }

            // If every placeholder is filled in
            if (story.isFilledIn()) {

                // Put the finished story in a string
                text_done = story.toString();

                // Go to the next screen
                Intent intent = new Intent(this, SecondActivity.class);

                // Pass the finished text through to the next screen
                intent.putExtra("text", text_done);

                // Go to the next screen and finish current one
                startActivity(intent);
                finish();

            }
        }

        // If nothing is filled in
        else {

            // Set reminder to what to fill in
            reminder.setText(("Please fill in a " + story.getNextPlaceholder()));

        }
    }

}

