package com.example.demo1.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "appointmentID")
    private Appointment appointment;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDate dateSent;

    public Notification() {
    }

    public int getNotificationID() {
        return notificationID;
    }

    public User getUser() {
        return user;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getDateSent() {
        return dateSent;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateSent(LocalDate dateSent) {
        this.dateSent = dateSent;
    }
}