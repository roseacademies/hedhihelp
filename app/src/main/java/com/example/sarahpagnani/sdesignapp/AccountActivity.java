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

import com.example.sarahpagnani.sdesignapp.DataModels.AppUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

public class AccountActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mConfirmPassField;
    private EditText mNameField;
    private EditText mAgeField;
    private FirebaseDatabase fbdb;
    private DatabaseReference dbref;
    private FirebaseUser fuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //Views
        mEmailField = (EditText) findViewById(R.id.create_account_email_form);
        mPasswordField = (EditText) findViewById(R.id.create_account_password_form);
        mConfirmPassField = (EditText) findViewById(R.id.create_account_confirm_password_form);
        mNameField = findViewById(R.id.create_account_name_form);
        mAgeField = findViewById(R.id.create_account_age_form);

        //Firebase initialization
        mAuth = FirebaseAuth.getInstance();
        fbdb = FirebaseDatabase.getInstance();
        //Buttons


    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidPassword(CharSequence target) {
        return (!TextUtils.isEmpty(target) && !(target.length() < 8));
    }

    public void createAccount(String email, String password) {
        Toast.makeText(AccountActivity.this, "Creating account...", Toast.LENGTH_SHORT).show();
        if (!isValidEmail(email)) Toast.makeText(AccountActivity.this, "Please put in a valid email.",
                Toast.LENGTH_SHORT).show();
        else if (!isValidPassword(password)) Toast.makeText(AccountActivity.this, "Password must be at least 8 characters long.",Toast.LENGTH_SHORT).show();
        else if (!mConfirmPassField.getText().toString().equals(mPasswordField.getText().toString())) Toast.makeText(AccountActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(AccountActivity.this, "Creating account...", Toast.LENGTH_SHORT).show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // sign in successful
//                        Toast.makeText(AccountActivity.this, "UID: " + task.getResult(), Toast.LENGTH_SHORT).show();
                        fuser = task.getResult().getUser();
                        AppUser user = new AppUser(fuser != null ? fuser.getUid() : null, mNameField.getText().toString(), mEmailField.getText().toString(), mAgeField.getText().toString());
                        fbdb.getReference().child("users").child(user.UID).setValue(user);
                        Log.d(TAG, "createUserWithEmail:success");
                        finish();
                    } else {
                        Toast.makeText(AccountActivity.this, "Sign in unsuccessful!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void create_account(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        String password2 = mPasswordField.getText().toString();
        String name = mNameField.getText().toString();
        String age = mAgeField.getText().toString();
        createAccount(email, password);
//        fuser = FirebaseAuth.getInstance().getCurrentUser();

    }
}
