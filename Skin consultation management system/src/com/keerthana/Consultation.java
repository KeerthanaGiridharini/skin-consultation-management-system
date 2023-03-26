//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.
//Student ID: w1867636
//Date: 08/01/2023

package com.keerthana;


import java.io.Serializable;
import java.util.Date;

// Class to represent a booked consultation with a specific doctor from a patient
class Consultation implements Serializable {
    private Patient patient;
    private Doctor doctor;
    // Date and time slot for the consultation
    private Date dateTime;
    private Date time;

    // Cost of the consultation
    private int cost;

    // Notes about the consultation
    private String notes;

    // hours about the consultation
    private int hours;
    // Constructor to initialize dateTime, cost and notes
    public Consultation(Doctor doctor, Patient patient, Date dateTime, Date time,int hours, int cost,  String notes) {
        this.doctor=doctor;
        this.patient=patient;
        this.dateTime = dateTime;
        this.time = time;
        this.hours = hours;
        this.cost = cost;
        this.notes = notes;

    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    // Getters and setters for dateTime, cost and notes
    public Date getDateTime() {
        return this.dateTime;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getTime(){return this.time;}
    public void setTime(Date time){this.time=time;}

    public int getCost() {
        return this.cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHours() {
        return this.hours;
    }
    public void setHours(int cost) {
        this.hours = hours;
    }

    public String getNotes() {
        return this.notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}

