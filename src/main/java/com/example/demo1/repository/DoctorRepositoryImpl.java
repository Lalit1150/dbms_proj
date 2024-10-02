package com.first_project.clinicman.repository;

import com.first_project.clinicman.model.Doctor;
import com.first_project.clinicman.model.Department;
import com.first_project.clinicman.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepositoryImpl implements DoctorRepository {

    private final JdbcTemplate jdbcTemplate;

    public DoctorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map database rows to Doctor objects
    private static final class DoctorRowMapper implements RowMapper<Doctor> {
        @Override
        public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Doctor doctor = new Doctor();
            doctor.setDoctorID(rs.getInt("doctorID"));

            User user = new User(); // Assuming User class exists and has a proper constructor
            user.setUserID(rs.getInt("userID")); // Assuming User has a setUserID method
            doctor.setUser(user);

            doctor.setSpecialization(rs.getString("specialization"));

            Department department = new Department(); // Assuming Department class exists and has a proper constructor
            department.setDepartmentID(rs.getInt("departmentID")); // Assuming Department has a setDepartmentID method
            doctor.setDepartment(department);

            doctor.setFees(rs.getDouble("fees"));
            doctor.setRating(rs.getInt("rating"));

            return doctor;
        }
    }

    @Override
    public List<Doctor> findAll() {
        String sql = "SELECT * FROM Doctors";
        return jdbcTemplate.query(sql, new DoctorRowMapper());
    }

    @Override
    public Optional<Doctor> findById(int doctorID) {
        String sql = "SELECT * FROM Doctors WHERE doctorID = ?";
        return jdbcTemplate.query(sql, new DoctorRowMapper(), doctorID)
                .stream()
                .findFirst();
    }

    @Override
    public int save(Doctor doctor) {
        String sql = "INSERT INTO Doctors (userID, specialization, departmentID, fees, rating) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                doctor.getUser().getUserID(),  // Assuming User has a getUserID()
                doctor.getSpecialization(),
                doctor.getDepartment().getDepartmentID(), // Assuming Department has getDepartmentID()
                doctor.getFees(),
                doctor.getRating());
    }

    @Override
    public int update(Doctor doctor) {
        String sql = "UPDATE Doctors SET userID = ?, specialization = ?, departmentID = ?, fees = ?, rating = ? WHERE doctorID = ?";
        return jdbcTemplate.update(sql,
                doctor.getUser().getUserID(),
                doctor.getSpecialization(),
                doctor.getDepartment().getDepartmentID(),
                doctor.getFees(),
                doctor.getRating(),
                doctor.getDoctorID());
    }

    @Override
    public int deleteById(int doctorID) {
        String sql = "DELETE FROM Doctors WHERE doctorID = ?";
        return jdbcTemplate.update(sql, doctorID);
    }
}
