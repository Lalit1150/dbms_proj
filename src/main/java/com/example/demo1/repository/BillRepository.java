package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Bill;

import java.util.List;
import java.util.Optional;

public interface BillRepository {
    List<Bill> findAll();
    Optional<Bill> findById(int billID);
    int save(Bill bill);
    int update(Bill bill);
    int deleteById(int billID);
}
