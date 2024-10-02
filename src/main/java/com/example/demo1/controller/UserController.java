package com.example.demo1.controller;

import com.example.demo1.dto.UserSignup;
import com.example.demo1.model.Patient;
import com.example.demo1.model.User;
import com.example.demo1.model.UserSignin;
import com.example.demo1.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userService;


    // User signup
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignup userSignup) {
        // Create a new User object
        User user = new User();
        user.setUsername(userSignup.getUsername());
        user.setPassword(userSignup.getPassword()); // Consider hashing the password
        user.setEmail(userSignup.getEmail());
        user.setContactInfo(userSignup.getContactInfo());
        user.setYearOfBirth(userSignup.getYearOfBirth());
        user.setMonthOfBirth(userSignup.getMonthOfBirth());
        user.setDayOfBirth(userSignup.getDayOfBirth());
        user.setBloodGroup(userSignup.getBloodGroup());
        user.setFirstName(userSignup.getFirstName());
        user.setLastName(userSignup.getLastName());
        user.setAge(userSignup.getAge());
        user.setAddress(userSignup.getAddress());

        // Set default role as PATIENT
        user.setRole(User.Role.PATIENT);

        // Save user
        userService.saveuser(user);

        // Create a new Patient object
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setDiseases(userSignup.getDiseases());
        patient.setEmergencyContactAddress(userSignup.getEmergencyContactAddress());
        patient.setEmergencyContactPhone(userSignup.getEmergencyContactPhone());
        patient.setEmergencyContactName(userSignup.getEmergencyContactName());

        // Save patient (Assuming you have a method in UserServiceImp for this)
        userService.savePatient(patient); // You will need to implement this method

        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World!");
    }

    // User signin
    @PostMapping("/signin")
    public ResponseEntity<User> signin(@RequestBody UserSignin userSignin) {
        User user = userService.signIn(userSignin.getUsername(), userSignin.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user); // Return user data on successful login
        } else {
            return ResponseEntity.status(401).body(null); // Unauthorized
        }
    }

}
