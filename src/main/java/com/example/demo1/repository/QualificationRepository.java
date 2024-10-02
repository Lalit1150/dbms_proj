package com.example.demo1.repository;

import com.example.demo1.model.Qualification;
import java.util.List;

public interface QualificationRepository {


    Qualification save(Qualification qualification);


    Qualification update(Qualification qualification);


    Qualification findById(Long id);


    List<Qualification> findByDoctorId(Long doctorId);


    void deleteById(Long id);


    List<Qualification> findAll();
}
