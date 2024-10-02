package com.example.demo1.services;

import com.example.demo1.model.User;
import com.example.demo1.model.Patient;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User user);
    List<User> getAllUsers();
    Patient savePatient(Patient patient);
    User signIn(String username, String password); // Add this line
}
