package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository {
    List<Feedback> findAll();
    Optional<Feedback> findById(int feedbackID);
    int save(Feedback feedback);
    int update(Feedback feedback);
    int deleteById(int feedbackID);
}
