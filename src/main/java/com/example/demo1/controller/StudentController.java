package com.example.demo1.controller;

import com.example.demo1.model.Student;
import com.example.demo1.services.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Make sure to include this annotation
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentServiceInterface studentService;

    @PostMapping("/add")
    public String add(@RequestBody Student student) {
        studentService.savestudent(student);
        return "New student is added";
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Test endpoint
    @GetMapping("/test")
    public String test() {
        return "The application is working!";
    }



}