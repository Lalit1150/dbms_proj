package com.example.demo1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prescriptionID;

    @ManyToOne
    @JoinColumn(name = "appointmentID")
    private Appointment appointment;

    @Column(columnDefinition = "TEXT")
    private String dosage;

    public int getPrescriptionID() {
        return prescriptionID;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public String getDosage() {
        return dosage;
    }

    public void setPrescriptionID(int prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Prescription() {
    }
}