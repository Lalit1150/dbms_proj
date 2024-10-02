package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {
    List<Prescription> findAll();
    Optional<Prescription> findById(int prescriptionID);
    int save(Prescription prescription);
    int update(Prescription prescription);
    int deleteById(int prescriptionID);
}
