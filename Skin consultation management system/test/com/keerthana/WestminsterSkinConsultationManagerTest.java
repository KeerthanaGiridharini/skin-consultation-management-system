//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867636
//Date: 08/01/2023

package com.keerthana;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterSkinConsultationManagerTest {
    //test classes to test the methods of WestminsterSkinConsultation Class
    private WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

    //this test class tests the addDoctor method
    @Test
    void addDoctor() {
        // Add a doctor to the list of doctors
        Date testDateOfBirth = new Date();  // create a Date object with the current date and time
        Doctor testDoctor = new Doctor("Shenara", "Perera", testDateOfBirth, "1234567890", "abcdef", "Surgeon");
        // Test adding the doctor
        Doctor result = manager.addDoctor(testDoctor);
        assertEquals(testDoctor, result);
        assertEquals(1, WestminsterSkinConsultationManager.doctors.size());
    }

    //this test class tests the deleteDoctor Method
    @Test
    void DeleteDoctor() {

        Date testDateOfBirth = new Date();
        // Add a doctor to the list of doctors
        Doctor doctor1 = new Doctor("Shemini", "Jayalath", testDateOfBirth, "1234567899", "abcdef12", "Surgeon");
        WestminsterSkinConsultationManager.doctors.add(doctor1);

        // Test deleting the doctor
        manager.deleteDoctor("123456");

        // Assert that the doctor has been deleted from the list of doctors
        assertEquals(1, WestminsterSkinConsultationManager.doctors.size());
    }



    @Test
    void PrintDoctors() {
        Date testDateOfBirth1 = new Date();
        Doctor testDoctor1 = new Doctor("Shemini", "Jayalath", testDateOfBirth1, "1234567899", "abcdef12", "Surgeon");
        Date testDateOfBirth2 = new Date();
        Doctor testDoctor2 = new Doctor("Prarthana", "Bawadharini", testDateOfBirth2, "1234567890", "abcdef", "Surgeon");
        WestminsterSkinConsultationManager.doctors.add(testDoctor1);
        WestminsterSkinConsultationManager.doctors.add(testDoctor2);
        // check that the details of the doctors are printed in the correct format
        String expectedOutput = "-------------------- Doctor 1 --------------------\n"
                + "Name: John\n"
                + "Surname: Doe\n"
                + "Date of Birth: " + new SimpleDateFormat("dd/MM/yyyy").format(testDateOfBirth1) + "\n"
                + "Medical Licence Number: 1234567890\n"
                + "Specialisation: Surgeon\n"
                + "Mobile Number: abcdef\n";
    }

    @Test
    public void SaveInformation() {
    }


    @Test
    void readSavedInformation() {
    }
}