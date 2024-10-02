package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {
    List<Doctor> findAll();
    Optional<Doctor> findById(int doctorID);
    int save(Doctor doctor);
    int update(Doctor doctor);
    int deleteById(int doctorID);
}
