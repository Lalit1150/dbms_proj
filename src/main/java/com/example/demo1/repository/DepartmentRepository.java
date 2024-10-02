package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {
    List<Department> findAll();
    Optional<Department> findById(int departmentID);
    int save(Department department);
    int update(Department department);
    int deleteById(int departmentID);
}
