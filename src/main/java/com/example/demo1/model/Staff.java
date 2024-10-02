package com.example.demo1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private String designation;
    private double salary;

    public Staff() {
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getStaffID() {
        return staffID;
    }

    public User getUser() {
        return user;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }
}