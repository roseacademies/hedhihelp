package org.roseacademies.hedhihelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LessonActivity extends AppCompatActivity implements View.OnClickListener{

    Button lessonOneButton;
    Button lessonTwoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(HomeActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView7);
        textView.setText(message);

        lessonOneButton = (Button) findViewById(R.id.lessonOneButton);
        lessonOneButton.setOnClickListener(this);

        lessonTwoButton = (Button) findViewById(R.id.lessonTwoButton);
        lessonTwoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int module_num = 0;
        if (id == R.id.lessonOneButton) {
            module_num = 1;
        } else if (id == R.id.lessonTwoButton) {
            module_num = 2;
        }
        startModule(module_num);
    }

    public void startModule(int moduleNum) {
        Intent i = new Intent(this, FullscreenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("moduleNum", moduleNum);
        i.putExtras(bundle);
        startActivity(i);
    }
}
