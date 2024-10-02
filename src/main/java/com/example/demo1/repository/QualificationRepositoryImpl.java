package com.example.demo1.repository;

import com.example.demo1.model.Qualification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QualificationRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    // Save a qualification
    public Qualification save(Qualification qualification) {
        entityManager.persist(qualification);
        return qualification;
    }

    // Update a qualification
    public Qualification update(Qualification qualification) {
        return entityManager.merge(qualification);
    }

    // Find qualification by ID
    public Qualification findById(Long id) {
        return entityManager.find(Qualification.class, id);
    }

    // Find qualifications by doctor ID
    public List<Qualification> findByDoctorId(Long doctorId) {
        return entityManager.createQuery("SELECT q FROM Qualification q WHERE q.doctor.id = :doctorId", Qualification.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }

    // Delete a qualification by ID
    public void deleteById(Long id) {
        Qualification qualification = entityManager.find(Qualification.class, id);
        if (qualification != null) {
            entityManager.remove(qualification);
        }
    }

    // Find all qualifications
    public List<Qualification> findAll() {
        return entityManager.createQuery("SELECT q FROM Qualification q", Qualification.class)
                .getResultList();
    }
}
