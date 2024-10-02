package com.example.demo1.services;

import com.example.demo1.model.Patient;
import com.example.demo1.model.Staff;
import com.example.demo1.model.User;
import com.example.demo1.repository.PatientRepository; // Import the PatientRepository
import com.example.demo1.repository.StaffRepository;
import com.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository; // Inject the PatientRepository

    public User saveuser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient); // Save the Patient object
    }

    public User signIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // User authenticated
        }
        return null; // Authentication failed
    }
    @Autowired
    private StaffRepository staffRepository;


    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

}
