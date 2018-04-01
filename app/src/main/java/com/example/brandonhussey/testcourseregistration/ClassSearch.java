package com.example.brandonhussey.testcourseregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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




    //set up database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;


    private ListView courseList;

  private ArrayList<Course> arrayList = new ArrayList<>();
  private ArrayAdapter<Course> adapter;



  Course course = new Course();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_class_search);

      String filter = getIntent().getStringExtra("key");

      reference = database.getReference().child(filter);

      adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, arrayList);
      courseList = (ListView) findViewById(R.id.courseList);



      reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            getData(dataSnapshot, arrayList);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

            Log.w(TAG, "Failed to read value.", databaseError.toException());

        }
    });

      courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Course detail = (Course) adapterView.getItemAtPosition(i);
              Intent intent = new Intent(ClassSearch.this, CourseDetail.class);
              intent.putExtra("Course", detail);
              startActivity(intent);
          }
      });
  }

  public void getData(DataSnapshot dataSnapshot, ArrayList<Course> courses) {

      courses.clear();

      for(DataSnapshot ds: dataSnapshot.getChildren())    {
          course = ds.getValue(Course.class);
          arrayList.add(course);

      }
      courseList.setAdapter(adapter);
  }
}
