package com.example.demo1.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackID;

    @ManyToOne
    @JoinColumn(name = "appointmentID")
    private Appointment appointment;

    private int rating;

    @Column(columnDefinition = "TEXT")
    private String comments;

    private LocalDate feedbackDate;

    public Feedback() {
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }
}