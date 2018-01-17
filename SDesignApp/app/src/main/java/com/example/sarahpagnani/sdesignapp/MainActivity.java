package com.example.sarahpagnani.sdesignapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.SDesignApp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button button0 = (Button) findViewById(R.id.loginbutton);
//        Button button1 = (Button) findViewById(R.id.button3);
//        button0.setOnClickListener((View.OnClickListener) this);
//        button1.setOnClickListener((View.OnClickListener) this);
    }

    public void sendMessage(View view) {
        switch (view.getId()) {
            case R.id.loginbutton:
                Intent intent0 = new Intent(this, HomeActivity.class);
                Button button0 = (Button) findViewById(R.id.loginbutton);
                String message0 = button0.getText().toString();
                intent0.putExtra(EXTRA_MESSAGE, message0);
                startActivity(intent0);
                break;
            case R.id.button3:
                Intent intent1 = new Intent(this, AccountActivity.class);
                Button button1 = (Button) findViewById(R.id.button3);
                String message1 = button1.getText().toString();
                intent1.putExtra(EXTRA_MESSAGE, message1);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
