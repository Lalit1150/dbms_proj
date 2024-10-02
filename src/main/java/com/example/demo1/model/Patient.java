package com.example.demo1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String diseases;

    @Column(columnDefinition = "TEXT")
    private String emergencyContactAddress;

    private String emergencyContactPhone;
    private String emergencyContactName;

    public int getPatientID() {
        return patientID;
    }

    public User getUser() {
        return user;
    }

    public String getDiseases() {
        return diseases;
    }

    public String getEmergencyContactAddress() {
        return emergencyContactAddress;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public void setEmergencyContactAddress(String emergencyContactAddress) {
        this.emergencyContactAddress = emergencyContactAddress;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public Patient() {
    }
}