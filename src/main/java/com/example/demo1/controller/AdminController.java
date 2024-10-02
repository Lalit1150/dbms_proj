package com.example.demo1.controller;

import com.example.demo1.dto.StaffSignup;
import com.example.demo1.model.Staff;
import com.example.demo1.model.User;
import com.example.demo1.services.UserServiceImp;
import com.example.demo1.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserServiceImp userService;

    @PostMapping("/staff/signup")
    public ResponseEntity<String> staffSignup(@RequestBody StaffSignup staffSignup) {
        // Create and save User
        User user = new User();
        user.setUsername(staffSignup.getUsername());
        user.setPassword(staffSignup.getPassword()); // Consider hashing
        user.setFirstName(staffSignup.getFirstName());
        user.setLastName(staffSignup.getLastName());
        user.setEmail(staffSignup.getEmail());
        user.setContactInfo(staffSignup.getContactInfo());
        user.setYearOfBirth(staffSignup.getYearOfBirth());
        user.setMonthOfBirth(staffSignup.getMonthOfBirth());
        user.setDayOfBirth(staffSignup.getDayOfBirth());
        user.setAddress(staffSignup.getAddress());
        user.setRole(User.Role.DOCTOR); // Set role to STAFF

        userService.saveuser(user);

        // Create and save Staff
        Staff staff = new Staff();
        staff.setUser(user);
        staff.setDesignation(staffSignup.getDesignation());
        staff.setSalary(staffSignup.getSalary());

        // Assuming you have a method in UserServiceImp to save staff
        userService.saveStaff(staff);

        return ResponseEntity.ok("Staff registered successfully!");
    }

}
