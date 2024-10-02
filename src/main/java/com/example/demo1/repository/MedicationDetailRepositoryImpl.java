package com.example.demo1.repository;

import com.example.demo1.model.MedicationDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicationDetailRepositoryImpl implements MedicationDetailRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MedicationDetail save(MedicationDetail medicationDetail) {
        entityManager.persist(medicationDetail);
        return medicationDetail;
    }

    @Override
    public MedicationDetail update(MedicationDetail medicationDetail) {
        return entityManager.merge(medicationDetail);
    }

    @Override
    public MedicationDetail findById(Long id) {
        return entityManager.find(MedicationDetail.class, id);
    }

    @Override
    public List<MedicationDetail> findByPrescriptionId(Long prescriptionId) {
        return entityManager.createQuery("SELECT md FROM MedicationDetail md WHERE md.prescription.id = :prescriptionId", MedicationDetail.class)
                .setParameter("prescriptionId", prescriptionId)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        MedicationDetail medicationDetail = entityManager.find(MedicationDetail.class, id);
        if (medicationDetail != null) {
            entityManager.remove(medicationDetail);
        }
    }

    @Override
    public List<MedicationDetail> findAll() {
        return entityManager.createQuery("SELECT md FROM MedicationDetail md", MedicationDetail.class)
                .getResultList();
    }
}
