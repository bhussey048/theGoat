package com.example.brandonhussey.testcourseregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClassSearch extends AppCompatActivity {


    private static final String TAG = "ClassSearch";
    FirebaseDatabase db;
    DatabaseReference courseRef;

    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ListView courseList;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_search);

        course = new Course();

        //initialize the db
        db = FirebaseDatabase.getInstance();
        courseRef = db.getReference("Course");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        courseList = (ListView) findViewById(R.id.courseList);

        courseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren())    {

                    course = ds.getValue(Course.class);
                    arrayList.add(course.getName().toString() + "\tActual" + course.getActual() + "\tCapactiy" + course.getCapacity());
                }
                courseList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}
