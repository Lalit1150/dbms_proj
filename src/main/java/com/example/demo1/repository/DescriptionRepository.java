package com.example.demo1.repository;

import com.example.demo1.model.Description;
import java.util.List;

public interface DescriptionRepository {

    // Save a description
    Description save(Description description);

    // Update a description
    Description update(Description description);

    // Find description by ID
    Description findById(Long id);

    // Find all descriptions for a specific appointment
    List<Description> findByAppointmentId(Long appointmentId);

    // Delete a description by ID
    void deleteById(Long id);

    // Find all descriptions
    List<Description> findAll();
}
