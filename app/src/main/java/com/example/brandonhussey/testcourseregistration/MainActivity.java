package com.example.brandonhussey.testcourseregistration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    //firebase declaration
    private FirebaseAuth mAuth;
    private TextView emailField;
    private TextView passwordField;
    private TextView mStatusTextView;
    private TextView mDetailTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        mStatusTextView = findViewById(R.id.status);
        mDetailTextView = findViewById(R.id.detail);


        findViewById(R.id.signIn).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.classSearch).setOnClickListener(this);

        //initialize db
        mAuth = FirebaseAuth.getInstance();
        
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private boolean validateForm() {

        boolean validUser = true;

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailField.setError("Email required.");
            validUser = false;
        } else {
            emailField.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            passwordField.setError("Password Required");
            validUser = false;
        } else {
            passwordField.setError(null);
        }

        return validUser;
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    currentUser.getEmail(), currentUser.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, currentUser.getUid()));

            emailField.setVisibility(View.GONE);
            passwordField.setVisibility(View.GONE);
            findViewById(R.id.signIn).setVisibility(View.GONE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
            findViewById(R.id.classSearch).setVisibility(View.VISIBLE);


        } else {
            mStatusTextView.setText(null);
            mDetailTextView.setText(null);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
            findViewById(R.id.classSearch).setVisibility(View.GONE);

        }
    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.signIn) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.sign_out_button) {
            signOut();
        } else if (i == R.id.classSearch)   {
            Intent classSearchIntent = new Intent(MainActivity.this, ClassSearch.class);
            startActivity(classSearchIntent);
        }
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);

        emailField.setVisibility(View.VISIBLE);
        passwordField.setVisibility(View.VISIBLE);
        findViewById(R.id.signIn).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_out_button).setVisibility(View.GONE);
    }
}
