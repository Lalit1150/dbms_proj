package com.first_project.clinicman.repository;
import com.first_project.clinicman.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    List<Patient> findAll();
    Optional<Patient> findById(int id);
    int save(Patient patient);
    int update(Patient patient);
    int deleteById(int id);
}
