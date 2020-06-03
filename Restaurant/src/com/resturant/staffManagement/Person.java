package com.resturant.staffManagement;

import com.resturant.enums.Gender;
import com.resturant.exceptions.InvalidNationalCodeException;

import java.io.InvalidClassException;
import java.util.Date;

public abstract class Person {
    private String name;
    private String address;
    private Date birthDate;
    private String nationalCode;
    private Gender gender;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public Gender getGender() {
        return gender;
    }

    //National Code validation
    private boolean isNationalCodeValid(String nationalCode){
        return nationalCode.length() == 10;
    }

//    public Person createPerson(String name, String address, Date birthDate, String nationalCode, Gender gender){
//        return new Person(name, address, birthDate, nationalCode, gender);
//    }

    public Person(String name, String address, Date birthDate, String nationalCode, Gender gender) throws InvalidNationalCodeException {
        if(!isNationalCodeValid(nationalCode)){
            throw new InvalidNationalCodeException();
        }
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.nationalCode = nationalCode;
        this.gender = gender;
    }

    public Person(String name, Date birthDate, Gender gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
