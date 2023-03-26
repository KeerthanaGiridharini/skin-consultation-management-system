//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867636
//Date: 08/01/2023
package com.keerthana;

import java.util.Date;

// Subclass of Person to represent a patient
class Patient extends Person {
    // Unique id of the patient
    private String patientId;

    // Constructor to initialize patientId
    public Patient(String name, String surname, Date dateOfBirth, String mobileNumber, String patientId) {
        // Call the superclass constructor to initialize name, surname, dateOfBirth and mobileNumber
        super(name, surname, dateOfBirth, mobileNumber);
        this.patientId = patientId;
    }

    // Getters and setters for patientId
    public String getPatientId() {
        return this.patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
