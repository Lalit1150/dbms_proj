package com.example.demo1.repository;

import com.example.demo1.model.Description;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DescriptionRepositoryImpl implements DescriptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Description save(Description description) {
        entityManager.persist(description);
        return description;
    }

    @Override
    public Description update(Description description) {
        return entityManager.merge(description);
    }

    @Override
    public Description findById(Long id) {
        return entityManager.find(Description.class, id);
    }

    @Override
    public List<Description> findByAppointmentId(Long appointmentId) {
        return entityManager.createQuery("SELECT d FROM Description d WHERE d.appointment.id = :appointmentId", Description.class)
                .setParameter("appointmentId", appointmentId)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Description description = entityManager.find(Description.class, id);
        if (description != null) {
            entityManager.remove(description);
        }
    }

    @Override
    public List<Description> findAll() {
        return entityManager.createQuery("SELECT d FROM Description d", Description.class)
                .getResultList();
    }
}
