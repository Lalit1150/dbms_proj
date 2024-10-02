package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    List<Appointment> findAll();
    Optional<Appointment> findById(int appointmentID);
    int save(Appointment appointment);
    int update(Appointment appointment);
    int deleteById(int appointmentID);
}
