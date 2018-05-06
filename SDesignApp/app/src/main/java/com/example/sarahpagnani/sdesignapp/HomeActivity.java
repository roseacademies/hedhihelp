package com.example.sarahpagnani.sdesignapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_MESSAGE = "com.example.SDesignApp.MESSAGE";
    Button mCalendarButton;
    Button mForumButton;
    Button mClinicsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mCalendarButton = (Button) findViewById(R.id.calendar_button);
        mCalendarButton.setOnClickListener(this);

        mForumButton = (Button) findViewById(R.id.button8);
        mForumButton.setOnClickListener(this);

        mClinicsButton = (Button) findViewById(R.id.button7);
        mClinicsButton.setOnClickListener(this);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView6);
//        textView.setText(message);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, LessonActivity.class);
        Button button = (Button) findViewById(R.id.lessonbutton);
        String message = button.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.calendar_button) { // This is the button for the calendar
            startActivity(new Intent(this, CalendarActivity.class));
        }
        if (id == R.id.lessonbutton) {
            startActivity(new Intent(this, FullscreenActivity.class));
        }
        if (id == R.id.button7) {
            startActivity(new Intent(this, ClinicsActivity.class));
        }
        if (id == R.id.button8) {
            startActivity(new Intent(this, Forum.class));
        }
    }
}
