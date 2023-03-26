//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867636
//Date: 08/01/2023
package com.keerthana;
import java.util.Date;


// Subclass of Person to represent a doctor
class Doctor extends Person {
    // Medical licence number of the doctor
    private String medicalLicenceNumber;

    // Specialisation of the doctor (e.g. cosmetic dermatology, medical dermatology, paediatric dermatology, etc.)
    private String specialisation;

    // Constructor to initialize medicalLicenceNumber and specialisation
    public Doctor(String name, String surname, Date dateOfBirth, String mobileNumber, String medicalLicenceNumber, String specialisation) {
        // Call the superclass constructor to initialize name, surname, dateOfBirth and mobileNumber
        super(name, surname, dateOfBirth, mobileNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
    }

    // Getters and setters for medicalLicenceNumber and specialisation
    public String getMedicalLicenceNumber() {
        return this.medicalLicenceNumber;
    }
    public void setMedicalLicenceNumber(String medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }
    public String getSpecialisation() {
        return this.specialisation;
    }
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    @Override
    public boolean equals(Object o) {
        Doctor d = (Doctor) o;
        if(d.getMedicalLicenceNumber().equals(this.medicalLicenceNumber)){
            return true;
        }else{
            return false;
        }
    }

}
