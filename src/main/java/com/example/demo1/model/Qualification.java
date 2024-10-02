package com.example.demo1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Qualification")
public class Qualification {

    @Id
    @ManyToOne
    @JoinColumn(name = "doctorID")
    private Doctor doctor;

    @Column(nullable = false)
    private String degree;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDegree() {
        return degree;
    }

    public Qualification() {
    }
}