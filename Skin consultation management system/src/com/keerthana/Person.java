//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867636
//Date: 08/01/2023
package com.keerthana;

import java.io.Serializable;
import java.util.Date;

// Superclass to represent a person
class Person implements Serializable {
    // Name of the person
    private String name;

    // Surname of the person
    private String surname;

    // Date of birth of the person
    private Date dateOfBirth;

    // Mobile number of the person
    private String mobileNumber;

    // Constructor to initialize name, surname, dateOfBirth and mobileNumber
    public Person(String name, String surname, Date dateOfBirth, String mobileNumber) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
    }

    // Getters and setters for name, surname, dateOfBirth and mobileNumber
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return this.surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
