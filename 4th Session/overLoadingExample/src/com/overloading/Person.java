package com.overloading;

public class Person {
    private String firstName;   //Optional
    private String lastName; //Required
    private String phoneNumber; //Optional

    Person(String lastName){
        this.lastName = lastName;
    }

    Person(String lastName, String firstName){
        this.lastName = lastName;
        this.firstName = firstName;
    }

    Person(String lastName, String phoneNumber, String firstName){
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
    }

}
