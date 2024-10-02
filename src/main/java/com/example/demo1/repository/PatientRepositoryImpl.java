package com.first_project.clinicman.repository;
import com.first_project.clinicman.model.Patient;


import com.first_project.clinicman.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private final JdbcTemplate jdbcTemplate;

    public PatientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for mapping rows of a ResultSet to the Patient object
    private static final class PatientRowMapper implements RowMapper<Patient> {
        @Override
        public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
            Patient patient = new Patient();
            patient.setPatientID(rs.getInt("patientID"));

            User user = new User();  // Assuming User has a similar structure
            user.setUserID(rs.getInt("userID")); // Assuming User has a field userID
            patient.setUser(user);

            patient.setDiseases(rs.getString("diseases"));
            patient.setEmergencyContactAddress(rs.getString("emergencyContactAddress"));
            patient.setEmergencyContactPhone(rs.getString("emergencyContactPhone"));
            patient.setEmergencyContactName(rs.getString("emergencyContactName"));
            return patient;
        }
    }

    @Override
    public List<Patient> findAll() {
        String sql = "SELECT * FROM Patients";
        return jdbcTemplate.query(sql, new PatientRowMapper());
    }

    @Override
    public Optional<Patient> findById(int patientID) {
        String sql = "SELECT * FROM Patients WHERE patientID = ?";
        return jdbcTemplate.query(sql, new PatientRowMapper(), patientID)
                .stream()
                .findFirst();
    }

    @Override
    public int save(Patient patient) {
        String sql = "INSERT INTO Patients (userID, diseases, emergencyContactAddress, emergencyContactPhone, emergencyContactName) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                patient.getUser().getUserID(),  // Assuming User has getUserID()
                patient.getDiseases(),
                patient.getEmergencyContactAddress(),
                patient.getEmergencyContactPhone(),
                patient.getEmergencyContactName());
    }

    @Override
    public int update(Patient patient) {
        String sql = "UPDATE Patients SET userID = ?, diseases = ?, emergencyContactAddress = ?, emergencyContactPhone = ?, emergencyContactName = ? " +
                "WHERE patientID = ?";
        return jdbcTemplate.update(sql,
                patient.getUser().getUserID(),
                patient.getDiseases(),
                patient.getEmergencyContactAddress(),
                patient.getEmergencyContactPhone(),
                patient.getEmergencyContactName(),
                patient.getPatientID());
    }

    @Override
    public int deleteById(int patientID) {
        String sql = "DELETE FROM Patients WHERE patientID = ?";
        return jdbcTemplate.update(sql, patientID);
    }
}

