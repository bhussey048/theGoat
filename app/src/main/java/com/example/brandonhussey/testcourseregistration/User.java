package com.example.brandonhussey.testcourseregistration;

import java.io.Serializable;

/**
 * Created by brandonhussey on 2018-03-28.
 */

public class User implements Serializable {

    private String firstName;
    private String lastName;

    public User() {

    }

    public User(String first, String last)  {

        firstName = first;
        lastName = last;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
