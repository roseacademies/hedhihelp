package com.example.sarahpagnani.sdesignapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class AccountActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mEmailField;
    private EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mEmailField = (EditText) findViewById(R.id.create_account_email_form);
        mPasswordField = (EditText) findViewById(R.id.create_account_password_form);
        mAuth = FirebaseAuth.getInstance();
//        // Get the Intent that started this activity and extract the string
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//
//        // Capture the layout's TextView and set the string as its text
//        TextView textView = findViewById(R.id.textView);
//        textView.setText(message);
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidPassword(CharSequence target) {
        return (!TextUtils.isEmpty(target));
    }

    public void createAccount(String email, String password) {
        if (!isValidEmail(email)) throw new AssertionError();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // sign in successful
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    //updateUI(user);
                } else {

                }
            }
        });
    }

    public void create_account(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        Toast.makeText(AccountActivity.this, "Creating account...", Toast.LENGTH_SHORT).show();
        if (!isValidEmail(email)) Toast.makeText(AccountActivity.this, "Please put in a valid email.",
                Toast.LENGTH_SHORT).show();
        else if (!isValidPassword(password)) Toast.makeText(AccountActivity.this, "Empty passwords are not valid.",Toast.LENGTH_SHORT).show();
        else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // sign in successful
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        //updateUI(user);
                        finish();
                    } else {
                        Toast.makeText(AccountActivity.this, "Sign in unsuccessful!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
