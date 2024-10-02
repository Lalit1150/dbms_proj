package com.example.demo1.repository;

import com.example.demo1.model.TimeSlot;
import java.util.List;

public interface TimeSlotRepository {
    int save(TimeSlot timeSlot);
    int update(TimeSlot timeSlot);
    TimeSlot findById(Long id);
    List<TimeSlot> findAll();
    int deleteById(Long id);
}
