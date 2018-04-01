package com.example.brandonhussey.testcourseregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CourseDetail extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("User");

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        Bundle data = getIntent().getExtras();
        final Course course = (Course)data.getSerializable("Course");
        Button regButton = (Button)findViewById(R.id.regButton);

        TextView courseName = (TextView)findViewById(R.id.courseName);
        TextView description = (TextView)findViewById(R.id.description);
        TextView actual = (TextView) findViewById(R.id.actual);
        TextView capacity = (TextView) findViewById(R.id.capacity);

        courseName.setText(course.getName());
        description.setText(course.getDescription());
        actual.setText(String.valueOf(course.getActual()));
        capacity.setText(String.valueOf(course.getCapacity()));

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {;

                reference.child(currentUser.getUid().toString()).child("Enrolled").child(course.getName()).setValue(course);
                Intent intent = new Intent(CourseDetail.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
