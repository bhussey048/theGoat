package com.example.brandonhussey.testcourseregistration;

/**
 * Created by brandonhussey on 2018-03-22.
 */

public class Course {

    private int Actual;
    private int Capacity;
    private String Name;

    public Course() {
    }

    public Course (int actual, int capacity, String name)    {

        Actual = actual;
        Capacity = capacity;
        Name = name;
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
}
