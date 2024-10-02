package com.example.demo1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Medication_Detail")
public class MedicationDetail {

    @Id
    @ManyToOne
    @JoinColumn(name = "prescriptionID")
    private Prescription prescription;

    @Column(columnDefinition = "TEXT")
    private String advice;

    public MedicationDetail() {
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public String getAdvice() {
        return advice;
    }
}