package com.example.brandonhussey.testcourseregistration;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by brandonhussey on 2018-03-24.
 */

public class AppData extends Application    {

    public DatabaseReference firebaseReference;
    public FirebaseDatabase firebaseDatabase;
}

