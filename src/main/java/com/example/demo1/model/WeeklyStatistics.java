package com.example.demo1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Weekly_Statistics")
public class WeeklyStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statID;

    private String week;
    private int totalAppointments;
    private double totalIncome;
    private int totalUsersRegistered;

    public WeeklyStatistics() {
    }

    public int getStatID() {
        return statID;
    }

    public String getWeek() {
        return week;
    }

    public int getTotalAppointments() {
        return totalAppointments;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public int getTotalUsersRegistered() {
        return totalUsersRegistered;
    }

    public void setStatID(int statID) {
        this.statID = statID;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public void setTotalUsersRegistered(int totalUsersRegistered) {
        this.totalUsersRegistered = totalUsersRegistered;
    }
}