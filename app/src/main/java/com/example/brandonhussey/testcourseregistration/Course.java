package com.example.brandonhussey.testcourseregistration;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brandonhussey on 2018-03-22.
 */

public class Course implements Serializable{

    private int Actual;
    private int Capacity;
    private String Name;
    private String Description;

    public Course() {
    }

    public Course (int actual, int capacity, String name, String description)    {

        Actual = actual;
        Capacity = capacity;
        Name = name;
        Description = description;
    }

    //getters and setters
    public int getActual() {
        return Actual;
    }

    public void setActual(int actual) {
        Actual = actual;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription()  { return Description; }

    public void setDescription(String description)  { Description = description; }

    /*@Exclude
    public Map<String, Object>  toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Name", Name);

        return result;
    }*/
}
