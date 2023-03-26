//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867636 & 20210676
//Date: 08/01/2023

package com.keerthana;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Class to manage skin consultations at Westminster consultation center
public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    // Maximum number of doctors that can be allocated at the centre
    private static final int MAXDOC_LIMIT = 10;
    public static ArrayList<Consultation> consultationArrayList=new ArrayList<>();
    public static ArrayList<Patient> patientArrayList = new ArrayList<>();

    // List of doctors at the centre
    public static ArrayList<Doctor> doctors;


    // Constructor to initialize the list of doctors
    public WestminsterSkinConsultationManager() {
        this.doctors = new ArrayList<>();
    }

    // Method to add a new doctor to the system
    @Override
    public Doctor addDoctor(Doctor doctor) {
        if (doctors.size() < MAXDOC_LIMIT) {
            doctors.add(doctor);
            System.out.println("Doctor added successfully.");
        } else {
            System.err.println("Cannot add more doctors. Maximum limit reached.");
        }


        return doctor;
    }

    // Method to delete a doctor from the system
    @Override
    public void deleteDoctor(String medicalLicenceNumber) {
        // Search for the doctor with the given medical licence number
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getMedicalLicenceNumber().equals(medicalLicenceNumber)) {
                // Remove the doctor and display a message
                Doctor deletedDoctor = doctors.remove(i);
                System.out.println("Doctor with Medical Licence Number " + deletedDoctor.getMedicalLicenceNumber()
                        + " Deleted Successfully.");
                System.out.println("No of Doctors in the center = " + doctors.size());
                return;
            }

        }
        // If the doctor is not found, display a message
        System.err.println("Doctor with Medical Licence Number " + medicalLicenceNumber + " Not Found.");
        System.out.println();
    }

    // Method to print the list of doctors in the consultation centre
    @Override
    public void printDoctors() {
        // Sort the list of doctors alphabetically according to the doctor's surname
        doctors.sort(new Comparator<Doctor>() {
            @Override
            public int compare(Doctor doc1, Doctor doc2) {
                return doc1.getSurname().compareTo(doc2.getSurname());
            }
        });

        // Print the details of each doctor
        int i = 1;
        for (Doctor doctor : doctors) {
            System.out.println("-------------------- Doctor " + i++ + " --------------------");
            System.out.println("Name: " + doctor.getName());
            System.out.println("Surname: " + doctor.getSurname());
            // Simple Date Format for format the output to DD/YY/MMM format.
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Date of Birth: "+ sdf.format(doctor.getDateOfBirth()));
            System.out.println("Medical Licence Number: " + doctor.getMedicalLicenceNumber());
            System.out.println("Specialisation: " + doctor.getSpecialisation());
            System.out.println("Mobile Number: " + doctor.getMobileNumber());
            System.out.println();
        }
    }


    // Method to save all the information entered by the user so far in a file
    @Override
    public void saveInformation()   {
//
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("INFO.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (int x=0; x<10; x++) {
                objectOutputStream.writeObject(patientArrayList);
                objectOutputStream.writeObject(consultationArrayList);
                objectOutputStream.writeObject(doctors);
            }

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Could not save information........................");
        }
    }

    public ArrayList<Doctor> readSavedInformation() {
//
        try{
            FileInputStream fileInputStream = new FileInputStream("INFO.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            for (int x=0; x<10; x++) {
                patientArrayList = (ArrayList<Patient>) objectInputStream.readObject();
                consultationArrayList = (ArrayList<Consultation>) objectInputStream.readObject();
                doctors = (ArrayList<Doctor>) objectInputStream.readObject();
            }
            objectInputStream.close();
            fileInputStream.close();

        }catch (Exception e){
            System.out.println("Could not load information.........................");
        }

        return null;
    }


    public ArrayList<Doctor> returnArray(){
        return doctors;
    }
    //prints out the menu options

    public static void menu() {
        System.out.println("\n>>>>>>>>>>> Welcome to Westminster Skin Consultation Center  <<<<<<<<<<");
        System.out.println("\t\t+---------------------------------------------------+");
        System.out.println("\t\t|   No   |             Description                  |");
        System.out.println("\t\t+--------+------------------------------------------+");
        System.out.println("\t\t|    1   |  Add a new Doctor                        |");
        System.out.println("\t\t|    2   |  Delete a Doctor                         |");
        System.out.println("\t\t|    3   |  Print the List of Doctors               |");
        System.out.println("\t\t|    4   |  Save Data                               |");
        System.out.println("\t\t|    5   |  GUI                                     |");
        System.out.println("\t\t|    0   |  Quit                                    |");
        System.out.println("\t\t=====================================================");

    }
    public  void displayMenu () {
        // Create an instance of the WestminsterSkinConsultationManager class
        SkinConsultationManager manager = new WestminsterSkinConsultationManager();
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        // Read Previous Saved Information
        try {
            manager.readSavedInformation();
        } catch (IOException e) {
            System.err.println("Error while reading information to file: " + e.getMessage());
        }
        try {


            boolean condition = true; //boolean variable which controls the while loop below
            int option;
            while (condition) {
                menu();
                System.out.print("\nSelect an option :");
                option = new Scanner(System.in).nextInt();

                switch (option) {
                    case 1:
                        // Read the input for the new doctor
                        System.out.println(">>>> Add a new doctor <<<<");
                        System.out.print("Enter name: ");
                        String name = scanner.next();
                        System.out.print("Enter surname: ");
                        String surname = scanner.next();
                        System.out.print("Enter date of birth (dd/mm/yyyy): ");
                        String dobString = scanner.next();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String medicalLicenceNumber;
                        try {
                            // Parse the date of birth string
                            Date dob = sdf.parse(dobString);

                            System.out.print("Enter mobile number: ");
                            String mobileNumber = scanner.next();
                            System.out.print("Enter medical licence number: ");
                            medicalLicenceNumber = scanner.next();
                            System.out.print("Enter specialisation: ");
                            String specialisation = scanner.next();

                            // Create a new Doctor object and add it to the system
                            Doctor doctor = new Doctor(name, surname, dob, mobileNumber, medicalLicenceNumber, specialisation);
                            manager.addDoctor(doctor);
                        } catch (ParseException e) {
                            // If an exception is thrown, the date of birth string is not in the correct format
                            System.err.println("Invalid date of birth");
                        }

                        break;

                    case 2:
                        // Read the input for the medical licence number of the doctor to delete
                        System.out.println(">>>> Delete a doctor <<<<");
                        System.out.print("Enter medical licence number: ");
                        medicalLicenceNumber = scanner.next();
                        manager.deleteDoctor(medicalLicenceNumber);
                        break;
                    case 3:
                        // Print the list of doctors
                        System.out.println("\t\t\t>>>> List of doctors <<<<\n");
                        manager.printDoctors();
                        break;
                    case 4:
                        // Save the information entered by the user so far
                        manager.saveInformation();
                        break;
                    case 5:
                        try {
                            new GUI(manager.returnArray());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 0:
                        manager.saveInformation();
                        System.err.println(" Exiting from Programme");
                        condition = false;
                        break;
                    default:
                        // Invalid choice
                        System.err.println("Invalid choice. Please try again.");
                        break;
                }
            }
            // Close the scanner
        }catch (InputMismatchException e) {
            // Print an error message and continue with the loop
            System.err.println("Invalid input. Please enter a valid option.");
            displayMenu();
        }
        scanner.close();

    }
}
