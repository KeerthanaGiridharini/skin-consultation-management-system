package com.keerthana;
//*SCM-System - Skin Consultation Management System
import java.io.IOException;
import java.util.ArrayList;

// Interface to define the methods for managing skin consultations
interface SkinConsultationManager {
    // Method to add a new doctor to the SCM-System
    Doctor addDoctor(Doctor doctor);

    // Method to delete a doctor from the SCM-System
    void deleteDoctor(String medicalLicenceNumber);

    // Method to print the list of doctors in the consultation centre
    void printDoctors();

    // Method to save all the information entered by the user so far in a file
    void saveInformation();


    //Method to read all the information that previously saved to the file.
    ArrayList<Doctor> readSavedInformation()throws IOException;
    ArrayList<Doctor> returnArray()throws IOException;

}


