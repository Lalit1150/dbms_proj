package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffRepository {
    List<Staff> findAll();
    Optional<Staff> findById(int staffID);
    int save(Staff staff);
    int update(Staff staff);
    int deleteById(int staffID);
}
