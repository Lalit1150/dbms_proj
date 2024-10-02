package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Prescription;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    private final JdbcTemplate jdbcTemplate;

    public PrescriptionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Prescription objects
    private static final class PrescriptionRowMapper implements RowMapper<Prescription> {
        @Override
        public Prescription mapRow(ResultSet rs, int rowNum) throws SQLException {
            Prescription prescription = new Prescription();
            prescription.setPrescriptionID(rs.getInt("prescriptionID"));
            prescription.setDosage(rs.getString("dosage"));
            // Assuming Appointment can be fetched from its ID later if needed.
            return prescription;
        }
    }

    @Override
    public List<Prescription> findAll() {
        String sql = "SELECT * FROM Prescriptions";
        return jdbcTemplate.query(sql, new PrescriptionRowMapper());
    }

    @Override
    public Optional<Prescription> findById(int prescriptionID) {
        String sql = "SELECT * FROM Prescriptions WHERE prescriptionID = ?";
        return jdbcTemplate.query(sql, new PrescriptionRowMapper(), prescriptionID)
                .stream()
                .findFirst();
    }

    @Override
    public int save(Prescription prescription) {
        String sql = "INSERT INTO Prescriptions (appointmentID, dosage) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                prescription.getAppointment().getAppointmentID(), // Assuming Appointment has a method to get the appointment ID
                prescription.getDosage());
    }

    @Override
    public int update(Prescription prescription) {
        String sql = "UPDATE Prescriptions SET appointmentID = ?, dosage = ? WHERE prescriptionID = ?";
        return jdbcTemplate.update(sql,
                prescription.getAppointment().getAppointmentID(),
                prescription.getDosage(),
                prescription.getPrescriptionID());
    }

    @Override
    public int deleteById(int prescriptionID) {
        String sql = "DELETE FROM Prescriptions WHERE prescriptionID = ?";
        return jdbcTemplate.update(sql, prescriptionID);
    }
}
