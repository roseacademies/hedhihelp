package com.example.sarahpagnani.sdesignapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_MESSAGE = "com.example.SDesignApp.MESSAGE";
    private FirebaseAuth mAuth;
//    private TextView mStatusTextView;
//    private TextView mDetailTextView;
    private EditText mEmailField;
    private EditText mPasswordField;

    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    public void sendMessage(View view) {
        switch (view.getId()) {
            case R.id.loginbutton:
                Intent intent0 = new Intent(this, HomeActivity.class);
                Button button0 = findViewById(R.id.loginbutton);
                String message0 = button0.getText().toString();
                intent0.putExtra(EXTRA_MESSAGE, message0);
                startActivity(intent0);
                break;
            case R.id.create_account:
                Intent intent1 = new Intent(this, AccountActivity.class);
                Button button1 = findViewById(R.id.create_account);
                String message1 = button1.getText().toString();
                intent1.putExtra(EXTRA_MESSAGE, message1);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
//        mStatusTextView = findViewById(R.id.status);
        mEmailField = findViewById(R.id.email_form);
        mPasswordField = findViewById(R.id.password_form);
        //Buttons
        findViewById(R.id.loginbutton).setOnClickListener(this);
        findViewById(R.id.create_account).setOnClickListener(this);
        findViewById(R.id.logoutbutton).setOnClickListener(this);
        // findViewById(R.id.verify_email_button).setOnClickListener(this); //<- this might be good to have later

        mAuth = FirebaseAuth.getInstance();
//        if (mAuth.getCurrentUser() != null) {
//            Intent homeIntent = new Intent(this, HomeActivity.class);
//            homeIntent.putExtra(EXTRA_MESSAGE, mAuth.getCurrentUser().getUid());
//            startActivity(homeIntent);
//        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    @Override
    public void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);
        // Check if user is signed in (non-null) and update UI
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mEmailField = findViewById(R.id.email_form);
        mPasswordField = findViewById(R.id.password_form);
        //Buttons
        findViewById(R.id.loginbutton).setOnClickListener(this);
        findViewById(R.id.create_account).setOnClickListener(this);
        findViewById(R.id.logoutbutton).setOnClickListener(this);
//        if (currentUser!=null) startActivity(new Intent(this, HomeActivity.class));
        updateUI(currentUser);
    }

    public void signIn(String email, String password) {
        Log.d(TAG, "signIn:"+email);
        if (!isValidEmail(email)) Toast.makeText(MainActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
        else {
//            Toast.makeText(MainActivity.this, "Valid inputs...", Toast.LENGTH_SHORT).show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                Toast.makeText(MainActivity.this, "Sign in success!", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Sign in failed.", Toast.LENGTH_SHORT).show();
//                            mStatusTextView.setText(R.string.auth_failed);
                            }
                            hideProgressDialog();
                        }
                    });
//            if (mAuth.getCurrentUser() != null) {
//                startActivity(new Intent(this, HomeActivity.class));
//            }
        }
    }


    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }


    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
//            mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
//            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.loginbutton).setVisibility(View.GONE);
            findViewById(R.id.logoutbutton).setVisibility(View.VISIBLE);
        } else {
//            mStatusTextView.setText(R.string.signed_out);
//            mDetailTextView.setText(null);

            findViewById(R.id.loginbutton).setVisibility(View.VISIBLE);
            findViewById(R.id.logoutbutton).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i==R.id.loginbutton) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.create_account) {
            startActivity(new Intent(this, AccountActivity.class));

//            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.logoutbutton) {
            signOut();
        }
    }
}
