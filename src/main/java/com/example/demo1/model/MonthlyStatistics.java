package com.example.demo1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Monthly_Statistics")
public class MonthlyStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statID;

    private String monthName;
    private int totalAppointments;
    private double totalIncome;
    private int totalUsersRegistered;

    public MonthlyStatistics() {
    }

    public void setStatID(int statID) {
        this.statID = statID;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
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

    public int getStatID() {
        return statID;
    }

    public String getMonthName() {
        return monthName;
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
}