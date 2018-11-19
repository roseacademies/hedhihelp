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
    Button lessonThreeButton;
    Button lessonFourButton;
    Button lessonFiveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        lessonOneButton = findViewById(R.id.lessonOneButton);
        lessonOneButton.setOnClickListener(this);

        lessonTwoButton = findViewById(R.id.lessonTwoButton);
        lessonTwoButton.setOnClickListener(this);

        lessonThreeButton = findViewById(R.id.lessonThreeButton);
        lessonThreeButton.setOnClickListener(this);

        lessonFourButton = findViewById(R.id.lessonFourButton);
        lessonFourButton.setOnClickListener(this);

        lessonFiveButton = findViewById(R.id.lessonFiveButton);
        lessonFiveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int module_num = 0;
        if (id == R.id.lessonOneButton) {
            module_num = 1;
        }
        else if (id == R.id.lessonTwoButton) {
            module_num = 2;
        }
        else if (id == R.id.lessonThreeButton) {
            module_num = 3;
        }
        else if (id == R.id.lessonFourButton) {
            module_num = 4;
        }
        else if (id == R.id.lessonFiveButton) {
            module_num = 5;
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
