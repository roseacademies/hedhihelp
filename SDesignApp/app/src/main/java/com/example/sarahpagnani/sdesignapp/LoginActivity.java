//package com.example.sarahpagnani.sdesignapp;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.text.TextUtils;
//import android.util.Log;
//import android.util.Patterns;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//import static android.content.ContentValues.TAG;
//
//public class LoginActivity extends MainActivity implements View.OnClickListener{
//    private FirebaseAuth mAuth;
//    private TextView mStatusTextView;
//    private TextView mDetailTextView;
//    private EditText mEmailField;
//    private EditText mPasswordField;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Views
////        mStatusTextView = findViewById(R.id.status);
//        mEmailField = findViewById(R.id.email_form);
//        mPasswordField = findViewById(R.id.password_form);
//        //Buttons
//        findViewById(R.id.loginbutton).setOnClickListener(this);
//        findViewById(R.id.create_account).setOnClickListener(this);
//        findViewById(R.id.logoutbutton).setOnClickListener(this);
//        // findViewById(R.id.verify_email_button).setOnClickListener(this); //<- this might be good to have later
//
//        mAuth = FirebaseAuth.getInstance();
//
//    }
//
//    public final static boolean isValidEmail(CharSequence target) {
//        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
//    }
//
//    public void createAccount(String email, String password) {
//
//        if (!isValidEmail(email)) throw new AssertionError();
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    // sign in successful
//                    Log.d(TAG, "createUserWithEmail:success");
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    //updateUI(user);
//                } else {
//
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        setContentView(R.layout.activity_main);
//        // Check if user is signed in (non-null) and update UI
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        mEmailField = findViewById(R.id.email_form);
//        mPasswordField = findViewById(R.id.password_form);
//        //Buttons
//        findViewById(R.id.loginbutton).setOnClickListener(this);
//        findViewById(R.id.create_account).setOnClickListener(this);
//        findViewById(R.id.logoutbutton).setOnClickListener(this);
//
//        updateUI(currentUser);
//    }
//
//    public void signIn(String email, String password) {
//        Log.d(TAG, "signIn:"+email);
//
//        isValidEmail(email);
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//
//                        if (!task.isSuccessful()) {
//                            mStatusTextView.setText(R.string.auth_failed);
//                        }
//                        hideProgressDialog();
//                    }
//                });
//    }
//
//
//    private void signOut() {
//        mAuth.signOut();
//        updateUI(null);
//    }
//
//
//    private void updateUI(FirebaseUser user) {
//        hideProgressDialog();
//        if (user != null) {
////            mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
////            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
//
//            findViewById(R.id.loginbutton).setVisibility(View.GONE);
//            findViewById(R.id.logoutbutton).setVisibility(View.VISIBLE);
//        } else {
////            mStatusTextView.setText(R.string.signed_out);
////            mDetailTextView.setText(null);
//
//            findViewById(R.id.loginbutton).setVisibility(View.VISIBLE);
//            findViewById(R.id.logoutbutton).setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void onClick(View v){
//        int i = v.getId();
//
//    }
//}