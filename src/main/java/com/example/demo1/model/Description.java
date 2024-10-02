package com.example.demo1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Descriptions")
public class Description {

    @Id
    @ManyToOne
    @JoinColumn(name = "appointmentID")
    private Appointment appointment;

    @Column(columnDefinition = "TEXT")
    private String problem;

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public String getProblem() {
        return problem;
    }

    public Description() {
    }
}