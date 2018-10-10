package org.roseacademies.hedhihelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SurveyActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    public static final String EXTRA_MESSAGE = "org.roseacademies.hedhihelp.MESSAGE";
    private View mControlsView;
    private boolean mVisible;
    private ImageView mContentView;
    private float[] lastTouchDownXY = new float[2];
    private int image_max = 2;
    private int image_count;
    private Integer images[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = (ImageView) findViewById(R.id.modules_image);

        mContentView.setOnClickListener(this);
        findViewById(R.id.modules_image).setOnTouchListener(this);

        images = new Integer[2];
        images[0] = getResources().getIdentifier("survey_1", "drawable", getPackageName());
        images[1] = getResources().getIdentifier("survey_2", "drawable", getPackageName());

        image_count = 0;
        setCurrentImage();

    }

    public void setCurrentImage()
    {
        final ImageView imageView = (ImageView) findViewById(R.id.modules_image);
        imageView.setImageResource(images[image_count]);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, LessonActivity.class);
        Button button = (Button) findViewById(R.id.lesson_button);
        String message = button.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        int i = view.getId();
        if (view == findViewById(R.id.end_module)) {
            finish();
        }

        if (view == mContentView) {
            float x = lastTouchDownXY[0];
            float y = lastTouchDownXY[1];

            if (x >= (height/2)) {
                image_count += 1;
            }
            else if (x < (height/2)) {
                image_count -= 1;
            }
            if (image_count < image_max && image_count >= 0)
                updateUI();
            else
                finish();

        }
    }

    public void updateUI() {
        setCurrentImage();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            lastTouchDownXY[0] = event.getX();
            lastTouchDownXY[1] = event.getY();
        }
        return false;
    }
}
