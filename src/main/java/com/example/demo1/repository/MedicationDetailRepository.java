package com.example.demo1.repository;

import com.example.demo1.model.MedicationDetail;
import java.util.List;

public interface MedicationDetailRepository {

    // Save a MedicationDetail
    MedicationDetail save(MedicationDetail medicationDetail);

    // Update a MedicationDetail
    MedicationDetail update(MedicationDetail medicationDetail);

    // Find MedicationDetail by ID
    MedicationDetail findById(Long id);

    // Find all MedicationDetails for a specific prescription
    List<MedicationDetail> findByPrescriptionId(Long prescriptionId);

    // Delete a MedicationDetail by ID
    void deleteById(Long id);

    // Find all MedicationDetails
    List<MedicationDetail> findAll();
}
