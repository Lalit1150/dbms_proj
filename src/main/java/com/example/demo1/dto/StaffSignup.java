package com.example.demo1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StaffSignup {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String designation;

    @NotNull
    private double salary;

    // User details
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private int yearOfBirth;

    @NotNull
    private int monthOfBirth;

    @NotNull
    private int dayOfBirth;

    @NotBlank
    private String email;

    @NotBlank
    private String contactInfo;

    private String address;

    // Getters and Setters for all fields...


    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public void setDesignation(@NotBlank String designation) {
        this.designation = designation;
    }

    public void setSalary(@NotNull double salary) {
        this.salary = salary;
    }

    public void setFirstName(@NotBlank String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NotBlank String lastName) {
        this.lastName = lastName;
    }

    public void setYearOfBirth(@NotNull int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setMonthOfBirth(@NotNull int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public void setDayOfBirth(@NotNull int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public void setContactInfo(@NotBlank String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public @NotBlank String getDesignation() {
        return designation;
    }

    @NotNull
    public double getSalary() {
        return salary;
    }

    public @NotBlank String getFirstName() {
        return firstName;
    }

    public @NotBlank String getLastName() {
        return lastName;
    }

    @NotNull
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    @NotNull
    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    @NotNull
    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public @NotBlank String getContactInfo() {
        return contactInfo;
    }

    public String getAddress() {
        return address;
    }
}
