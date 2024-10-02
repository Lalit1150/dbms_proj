package com.example.demo1.services;

import com.example.demo1.model.Student;

import java.util.List;

public interface StudentServiceInterface  {
    public Student savestudent(Student student);
    public List<Student> getAllStudents();

}
