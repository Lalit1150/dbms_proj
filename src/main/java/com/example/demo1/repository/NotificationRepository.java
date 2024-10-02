package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    List<Notification> findAll();
    Optional<Notification> findById(int notificationID);
    int save(Notification notification);
    int update(Notification notification);
    int deleteById(int notificationID);
}
